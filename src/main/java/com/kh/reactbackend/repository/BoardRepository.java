package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.enums.CommonEnums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepository {
    Long save(Board board);
    Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable);
}
