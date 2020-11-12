package com.kook1932.springboot.service;

import com.kook1932.springboot.domain.posts.Posts;
import com.kook1932.springboot.domain.posts.PostsRepository;
import com.kook1932.springboot.web.dto.PostsListResponseDto;
import com.kook1932.springboot.web.dto.PostsResponseDto;
import com.kook1932.springboot.web.dto.PostsSaveRequestDto;
import com.kook1932.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service

// 트랜잭션, 도메인 기능 간의 순서를 보장하는 Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시클이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
    // update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없다. 이게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    // 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다.
    // JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
                // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해
                // PostsListResponseDto 변환 -> List롤 반환하는 메소드
    }
}