package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.dto.PageResponse;
import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Users;
import com.kh.reactbackend.enums.CommonEnums;
import com.kh.reactbackend.repository.BoardRepository;
import com.kh.reactbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createBoard(BoardDto.Write boardDto) {
       Users user = memberRepository.findOne(boardDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

       Board board = boardDto.toEntity();
       board.changeUsers(user);

        return boardRepository.save(board);
    }

    @Override
    public Page<BoardDto.Response> getBoardList(Pageable pageable) {
        Page<Board> page =  boardRepository.findByStatus(CommonEnums.Status.Y, pageable);
        return page.map(BoardDto.Response::toDto);
    }
}
