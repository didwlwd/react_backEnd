package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.dto.ReplyDto;
import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Reply;
import com.kh.reactbackend.entity.Users;
import com.kh.reactbackend.repository.BoardRepository;
import com.kh.reactbackend.repository.MemberRepository;
import com.kh.reactbackend.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public Long createReply(ReplyDto.Content replyDto) {
        Users user = memberRepository.findOne(replyDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Board board = boardRepository.findByBoardNo(replyDto.getBoardNo())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

        Reply reply = replyDto.toEntity();
        reply.changeUsers(user);
        reply.changeBoard(board);

        return replyRepository.saveReply(reply);
    }

    @Override
    public List<ReplyDto.Response> findByBoardNo(Long boardNo) {


        Board board = boardRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

        return replyRepository.findByBoardNo(board.getBoardNo())
                .stream().map(ReplyDto.Response::toDto)
                .collect(Collectors.toList());
    }
}
