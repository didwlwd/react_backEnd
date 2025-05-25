package com.kh.reactbackend.dto;

import com.kh.reactbackend.entity.Users;
import com.kh.reactbackend.enums.CommonEnums;
import lombok.*;

public class UsersDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        private String id;
        private String password;
        private String email;
        private String user_name;
        private String user_nikname;

        public Users toEntity(){
            return Users.builder()
                    .memberId(this.id)
                    .password(this.password)
                    .email(this.email)
                    .userName(this.user_name)
                    .userNikName(this.user_nikname)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String id;
        private String password;
        private String user_nikname;
        private String user_name;
        private String email;
        private String user_thumbnail;
        private CommonEnums.Status status;

        public static Response toDto(Users users){
            return Response.builder()
                    .id(users.getMemberId())
                    .user_nikname(users.getUserNikName())
                    .user_name(users.getUserName())
                    .email(users.getEmail())
                    .user_thumbnail(users.getThumbnail())
                    .status(users.getStatus())
                    .build();
        }

        public static Response toPassword(Users users){
            return Response.builder()
                    .password(users.getPassword())
                    .build();
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Login{
        private String id;
        private String password;

        public Users toDto(){
            return Users.builder()
                    .memberId(this.id)
                    .password(this.password)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update{
        private String id;

        private String email;
        private String user_name;
        private String user_nikname;
        private String user_thumbnail;

        private CommonEnums.Status status;

        public Users toDelete(){
            return Users.builder()
                    .memberId(this.id)
                    .status(this.status)
                    .build();
        }
    }
}
