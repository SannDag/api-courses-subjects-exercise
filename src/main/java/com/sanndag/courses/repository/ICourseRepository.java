package com.sanndag.courses.repository;

import com.sanndag.courses.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE %:courseName%")
    List<Course> findBySimilarName(@Param("courseName") String courseName);

}
