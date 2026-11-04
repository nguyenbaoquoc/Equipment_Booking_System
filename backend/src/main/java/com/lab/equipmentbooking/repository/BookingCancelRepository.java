package com.lab.equipmentbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.equipmentbooking.entity.BookingCancellation;

@Repository
public interface BookingCancelRepository extends JpaRepository<BookingCancellation, Long> {

}
