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
            "(:location IS NULL OR LOWER(p.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
            "(:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')))")
    List<Position> findByPropertyLikeIgnoreCase(@Param("location") String location, @Param("title") String title);

    Optional<Position> findById(Long id);
}
