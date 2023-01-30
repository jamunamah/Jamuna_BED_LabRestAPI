package com.greatlearning.labrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.labrestapi.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
