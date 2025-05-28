package com.kh.reactbackend.dto;

import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Reply;
import com.kh.reactbackend.enums.CommonEnums;
import lombok.*;

import java.time.LocalDateTime;

public class ReplyDto {

    @Getter
    @NoArgsConstructor
    public static class Content {
        String id;
        Long boardNo;
        String content;

        public Reply toEntity(){
            return Reply.builder()
                    .replyContent(content)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long reply_id;
        private String replyContent;
        private CommonEnums.Status status;
        private LocalDateTime create_date;

        private String userThumbnail;
        private String userNikName;

        public static Response toDto(Reply reply){
            return Response.builder()
                    .reply_id(reply.getReplyId())
                    .replyContent(reply.getReplyContent())
                    .status(reply.getStatus())
                    .create_date(reply.getCreateDate())
                    .userThumbnail(reply.getUser().getThumbnail())
                    .userNikName(reply.getUser().getUserNikName())
                    .build();
        }
    }

}
