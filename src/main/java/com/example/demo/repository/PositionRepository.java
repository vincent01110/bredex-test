package com.example.demo.repository;

import com.example.demo.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

//    @Query("SELECT * FROM position WHERE position.title LIKE %:title%")
//    List<Position> findByTitleLike(@Param("title") String title);
//
//    @Query("SELECT * FROM position WHERE position.location LIKE %:location%")
//    List<Position> findByLocationLike(@Param("location") String location);


    @Query("SELECT p FROM Position p WHERE " +
            "(:location IS NULL OR p.location LIKE %:location%) AND " +
            "(:title IS NULL OR p.title LIKE %:title%)")
    List<Position> findByPropertyLike(@Param("location") String location, @Param("title") String title);
}
