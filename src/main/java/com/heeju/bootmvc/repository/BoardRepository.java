package com.heeju.bootmvc.repository;

import com.heeju.bootmvc.domain.Board;
import com.heeju.bootmvc.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    @Query(value = "select now()", nativeQuery = true)
    String getTime();

}
