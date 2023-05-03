package com.debasish.bookmyshow.repository;

import com.debasish.bookmyshow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    ShowSeat save(ShowSeat seat); // insert new record and update an existing record
}
