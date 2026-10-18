"""
Script kiểm thử Concurrency (Chống đặt trùng lịch đồng thời) - Nghiệm thu AT-05 / NFR-04
PRD: "Gửi 5 yêu cầu đồng thời cùng thiết bị và khoảng thời gian [start, end). Chỉ 1 yêu cầu được chấp nhận; các yêu cầu còn lại báo xung đột (409 Conflict)."

Cách chạy:
  python scripts/test_concurrency.py
"""

import asyncio
import aiohttp
import time
from datetime import datetime, timedelta

# Cấu hình API Endpoint và thông tin test
API_URL = "http://localhost:8080/api/v1/bookings"
EQUIPMENT_ID = 1  # ID của thiết bị kiểm thử

# Giả lập thời gian đặt: Ngày mai, từ 10:00 đến 12:00
tomorrow = datetime.now() + timedelta(days=1)
start_time = tomorrow.replace(hour=10, minute=0, second=0, microsecond=0).isoformat()
end_time = tomorrow.replace(hour=12, minute=0, second=0, microsecond=0).isoformat()

# Headers giả lập (hoặc gắn JWT Token của các Member khác nhau)
MEMBER_TOKENS = [
    "mock-token-member-1",
    "mock-token-member-2",
    "mock-token-member-3",
    "mock-token-member-4",
    "mock-token-member-5",
]

async def send_booking_request(session, req_id, token):
    payload = {
        "equipmentId": EQUIPMENT_ID,
        "startTime": start_time,
        "endTime": end_time,
        "purpose": f"Nghiệm thu đồng thời AT-05 - Request #{req_id}"
    }
    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json"
    }
    
    start_ts = time.time()
    try:
        async with session.post(API_URL, json=payload, headers=headers) as resp:
            data = await resp.json()
            latency = (time.time() - start_ts) * 1000
            return {
                "req_id": req_id,
                "status_code": resp.status,
                "response": data,
                "latency_ms": latency
            }
    except Exception as e:
        return {
            "req_id": req_id,
            "status_code": 0,
            "error": str(e),
            "latency_ms": (time.time() - start_ts) * 1000
        }

async def main():
    print(f"=== BẮT ĐẦU KIỂM THỬ ĐỒNG THỜI (AT-05) ===")
    print(f"Thiết bị ID: {EQUIPMENT_ID}")
    print(f"Khung giờ kiểm thử: {start_time} -> {end_time}")
    print(f"Bắn 5 request song song cùng lúc...\n")

    async with aiohttp.ClientSession() as session:
        tasks = [
            send_booking_request(session, i + 1, MEMBER_TOKENS[i])
            for i in range(5)
        ]
        results = await asyncio.gather(*tasks)

    success_count = 0
    conflict_count = 0

    for r in results:
        code = r["status_code"]
        print(f"Request #{r['req_id']} | Status: {code} | Latency: {r['latency_ms']:.1f}ms")
        if code in [200, 201]:
            success_count += 1
        elif code == 409:
            conflict_count += 1

    print("\n=== KẾT QUẢ NGHIỆM THU ===")
    print(f"Thành công (200/201): {success_count} (Yêu cầu: 1)")
    print(f"Bị từ chối do xung đột (409 Conflict): {conflict_count} (Yêu cầu: 4)")

    if success_count == 1 and conflict_count == 4:
        print(">>> ĐẠT CHUẨN NGHIỆM THU AT-05 (PASSED) <<<")
    else:
        print(">>> CHƯA ĐẠT! Cần kiểm tra lại Transaction hoặc Khóa CSDL (FAILED) <<<")

if __name__ == "__main__":
    asyncio.run(main())
