package com.kh.reactbackend.dto;

import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Reply;
import com.kh.reactbackend.enums.CommonEnums;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    @Getter
    @NoArgsConstructor
    public static class Write{
        String id;
        String title;
        String content;
        String thumbnail;

        public Board toEntity(){
            return Board.builder()
                    .title(title)
                    .content(content)
                    .thumbnail(thumbnail)
                    .build();
        }
    }


    @Getter
    @NoArgsConstructor
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long board_no;
        private String title;
        private String content;
        private String thumbnail;
        private LocalDateTime create_date;
        private LocalDateTime modify_date;
        private Long views;
        private Long likes;
        private CommonEnums.Status status;

        private String user_thumbnail;
        private String user_nikname;

        public static Response toDto(Board board){
            return Response.builder()
                    .board_no(board.getBoardNo())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .thumbnail(board.getThumbnail())
                    .create_date(board.getCreateDate())
                    .modify_date(board.getModifyDate())
                    .views(board.getViews())
                    .likes(board.getLikes())
                    .user_thumbnail(board.getUser().getThumbnail())
                    .user_nikname(board.getUser().getUserNikName())
                    .build();
        }
    }
}
