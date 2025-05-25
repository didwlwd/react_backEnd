package com.kh.reactbackend.entity;

import com.kh.reactbackend.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@DynamicUpdate
public class Users {
    @Id
    @Column(name = "ID", length = 20)
    private String memberId;

    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;

    @Column(name = "USER_NIKNAME", nullable = false, length = 20)
    private String userNikName;

    @Column(name = "USER_NAME", nullable = false, length = 20)
    private String userName;

    @Column(length = 254)
    private String email;

    @Column(name = "ENROLL_DATE")
    private LocalDateTime enrollDate;

    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    @Column(name = "USER_THUMBNAIL", length = 6000)
    private String thumbnail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    //회원정보수정
    public void updateMemberInfo(String id, String userNikName, String userName, String email, String thumbnail) {
        this.memberId = id;
        this.userNikName = userNikName;
        this.userName = userName;
        this.email = email;
        this.thumbnail = thumbnail;
    }

    //회원탈퇴
    public void deleteMemberInfo(CommonEnums.Status status) {
        this.status = status;
    }

    @PrePersist
    public void prePersist(){
        this.enrollDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        this.thumbnail = null;
        if(this.status == null){
            this.status = CommonEnums.Status.Y;
        }
    }

    @PreUpdate
    public void preUpdate(){
        this.modifyDate = LocalDateTime.now();
    }
}
