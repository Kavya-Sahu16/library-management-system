package com.kavya.library.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kavya.library.entity.Borrow;
import java.time.LocalDate;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByReturnedFalseAndDueDateBefore(LocalDate date);

    long countByReturnDateIsNull();

    long countByReturnedTrue(); 

    long countByDueDateBeforeAndReturnDateIsNull(LocalDate date);

    @Query("SELECT SUM(b.fine) FROM Borrow b")
    Double getTotalFineCollected();

    @Query("""
    SELECT b.book.title, COUNT(b)
    FROM Borrow b
    GROUP BY b.book.title
    ORDER BY COUNT(b) DESC
""")
List<Object[]> findMostBorrowedBook(Pageable pageable);
}