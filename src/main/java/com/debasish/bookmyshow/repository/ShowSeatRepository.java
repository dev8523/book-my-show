package com.debasish.bookmyshow.repository;

import com.debasish.bookmyshow.model.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    //putting pessimistic lock on the below query
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findByIdIn(List<Long> showSeatIds); // select * from showSeat where id in (...);

    ShowSeat save(ShowSeat seat); // insert new record and update an existing record
}
