package com.swamini.repository;

import com.swamini.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Integer> {
    List<Marks> findByStudent_Id(int studentId);

    @Query("SELECT m.subject, ROUND(AVG(m.marks), 2), MAX(m.marks), MIN(m.marks) " +
            "FROM Marks m GROUP BY m.subject ORDER BY AVG(m.marks) DESC")
    List<Object[]> getMarksAnalytics();
}