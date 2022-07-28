package com.example.natube.dto;

import com.example.natube.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private String nickname;
    private String title;
    private String content;
    private String ytUrl;
    private LocalDateTime createdAt;
    private String hashTag;
//    private String imageFileName;

    public PostResponseDto(Post post){
        this.nickname = post.getUser().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.ytUrl = post.getYtUrl();
        this.createdAt = post.getCreatedTime();
        this.hashTag = post.getHashTag();
//        this.imageFileName = post.getImageFileName();
    }
}
