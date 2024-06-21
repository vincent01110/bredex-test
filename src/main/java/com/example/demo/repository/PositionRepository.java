package com.example.demo.repository;

import com.example.demo.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p FROM Position p WHERE " +
            "(:location IS NULL OR p.location LIKE %:location%) AND " +
            "(:title IS NULL OR p.title LIKE %:title%)")
    List<Position> findByPropertyLike(@Param("location") String location, @Param("title") String title);

    Optional<Position> findById(Long id);
}
