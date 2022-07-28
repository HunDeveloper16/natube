package com.example.natube.Service;

import com.example.natube.dto.PostRequestDto;
import com.example.natube.dto.PostResponseDto;
import com.example.natube.exception.CustomException;
import com.example.natube.model.Post;
import com.example.natube.model.User;
import com.example.natube.repository.PostRepository;
import com.example.natube.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

import static com.example.natube.exception.ErrorCode.POST_NOT_FOUND_CODE;
import static com.example.natube.exception.ErrorCode.USER_NOT_FOUND_CODE;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
//    private final AmazonS3Client amazonS3Client;
//    private final S3Uploader s3Uploader;
//    private final ImageRepository imageRepository;
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;


    //게시글 작성
//    @Transactional
//    public String createPost(String username, PostRequestDto requestDto, MultipartFile multipartFile) throws IOException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(()-> new CustomException(USER_NOT_FOUND_CODE));
//
//        Image uploadImage = s3Uploader.upload(multipartFile, "static");

        @Transactional
        public String createPost(String username, PostRequestDto requestDto) throws IOException {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(()-> new CustomException(USER_NOT_FOUND_CODE));

            System.out.println(user.getUsername());


        Post post = Post.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
//                .imageFileName(requestDto.getImageFileName())
                .build();

//        uploadImage.setPost(post);
//
//        imageRepository.save(uploadImage);

        postRepository.save(post);
        return username;
    }

    //게시글 상세조회
    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Post postEntity = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND_CODE));

        return new PostResponseDto(postEntity);
    }

    //게시글 수정
    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND_CODE));

        post.update(requestDto);

        return post.getId();
    }

    //게시글 삭제
    public void deletePost(Long id) {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new CustomException(POST_NOT_FOUND_CODE);
        }
    }


}