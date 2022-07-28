package com.example.natube.controller;

import com.example.natube.Service.PostService;
import com.example.natube.dto.PostRequestDto;
import com.example.natube.dto.PostResponseDto;
import com.example.natube.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

//    //게시글 작성
//    @PostMapping("/api/post")
//    public String createPost(@RequestBody PostRequestDto requestDto,
//                             @RequestParam("data") MultipartFile multipartFile,
//                             @AuthenticationPrincipal UserDetailsImpl userDetails)throws IOException {
//
//        return postService.createPost(userDetails.getUsername(), requestDto,multipartFile);
//    }

    //게시글 작성
    @PostMapping("/api/post")
    public String createPost(@RequestBody PostRequestDto requestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails)throws IOException {

        System.out.println(userDetails.getUser());

        return postService.createPost(userDetails.getUsername(),requestDto);
    }

    //게시글 상세 조회
    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto dto = postService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);
    }

    // 게시글 수정
    @PutMapping("/api/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return id;
    }
}
