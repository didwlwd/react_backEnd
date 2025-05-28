package com.kh.reactbackend.entity;

import com.kh.reactbackend.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_NO")
    private Long boardNo;

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    @Column(name = "THUMBNAIL", length = 6000)
    private String thumbnail;

    @Column
    private Long views;

    @Column
    private Long likes;

    @Column
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,orphanRemoval = true)
    @Builder.Default
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_WRITER")
    private Users user;

    @PreUpdate
    public void preUpdate(){
        this.modifyDate = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        this.views = 0L;
        this.likes = 0L;
        this.thumbnail = null;
        if(this.status == null){
            this.status = CommonEnums.Status.Y;
        }
    }

    //게시판 user 정보 조회 (연관 관계 주입)
    public void changeUsers(Users user) {
        this.user = user;
        if(!user.getBoards().contains(this)){
            user.getBoards().add(this);
        }
    }

}
