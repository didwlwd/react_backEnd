package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Reply;
import com.kh.reactbackend.enums.CommonEnums;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositoryImpl implements BoardRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(Board board) {
        em.persist(board);
        return board.getBoardNo();
    }

    @Override
    public Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable) {
        String query = "select b from Board b where b.status = :status";
        List<Board> boards = em.createQuery(query , Board.class)
                .setParameter("status", status)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        String qry = "select count(b) from Board b where b.status = :status";
        Long boardCount = em.createQuery(qry, Long.class)
                .setParameter("status", status)
                .getSingleResult();


        return new PageImpl<>(boards, pageable, boardCount);
    }

    @Override
    public Optional<Board> findByBoardNo(Long boardNo) {
        return Optional.ofNullable(em.find(Board.class, boardNo));
    }


}
