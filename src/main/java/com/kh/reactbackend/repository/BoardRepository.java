package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Reply;
import com.kh.reactbackend.enums.CommonEnums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepository {
    Long save(Board board);
    Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable);
    Optional<Board> findByBoardNo(Long boardNo);

}
