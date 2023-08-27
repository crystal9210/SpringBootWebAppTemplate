package com.webapp.testweb;

import com.webapp.testweb.Riddle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RiddleRepository extends JpaRepository<Riddle, Long> {

    @Query(value = "SELECT * FROM riddles WHERE category_id = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    List<Riddle> findRandomRiddleByCategoryId(Long categoryId);
}
