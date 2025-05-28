package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.dto.ReplyDto;
import com.kh.reactbackend.entity.Board;

import java.util.List;

public interface ReplyService {
    Long createReply(ReplyDto.Content replyDto);
    List<ReplyDto.Response> findByBoardNo(Long boardNo);
}
