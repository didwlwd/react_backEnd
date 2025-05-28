package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    Long saveReply(Reply reply);
    List<Reply> findByBoardNo(Long boardNo);
}
