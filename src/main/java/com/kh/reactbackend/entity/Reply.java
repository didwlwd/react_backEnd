package com.kh.reactbackend.entity;

import com.kh.reactbackend.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long replyId;

    @Column(name = "REPLY_CONTENT")
    private String replyContent;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_BNO")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPLY_WRITER")
    private Users user;

    @PrePersist
    public void prePersist(){
        this.createDate = LocalDateTime.now();
        if(this.status == null){
            this.status = CommonEnums.Status.Y;
        }
    }

    public void changeUsers(Users user){
        this.user = user;
        if(!user.getReplies().contains(this)){
            user.getReplies().add(this);
        }
    }

    public void changeBoard(Board board){
        this.board = board;
        if(!board.getReplies().contains(this)){
            board.getReplies().add(this);
        }
    }
}
