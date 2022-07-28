package com.example.natube.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String hashTag;
    private String ytUrl;
//    private String imageFileName;
}
