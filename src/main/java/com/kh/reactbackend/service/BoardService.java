package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.dto.ReplyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Long createBoard(BoardDto.Write boardDto);
    Page<BoardDto.Response> getBoardList(Pageable pageable);
    BoardDto.Response getBoardById(Long id);
}
