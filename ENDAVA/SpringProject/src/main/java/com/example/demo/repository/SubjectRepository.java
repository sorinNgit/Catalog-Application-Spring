package com.example.demo.repository;

import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByCreditPoints (Integer creditPoints); //select * from subject where credit_point == ...

    Long countByCreditPointsBetween(Integer creditStart, Integer creditEnd);
}