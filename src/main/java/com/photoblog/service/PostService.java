package com.photoblog.service;

import com.photoblog.api.request.PostRequest;
import com.photoblog.api.response.PostResponse;
import com.photoblog.exception.PostNotFoundException;
import com.photoblog.exception.UnauthorizedException;
import com.photoblog.model.Account;
import com.photoblog.model.Post;
import com.photoblog.respository.PostRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PostService {

    private final PostRepository postRepository;
    private final AccountService accountService;

    @Autowired
    public PostService(PostRepository postRepository, AccountService accountService) {
        this.postRepository = postRepository;
        this.accountService = accountService;
    }

    public void save(PostRequest postRequest, String userName){

        postRepository.save(new Post(
                postRequest.getTitle(),
                postRequest.getBody(),
                accountService.getByEmail(userName),
                LocalDate.now()
        ));
    }

    public List<PostResponse> getAll(){
        return postRepository.findAll().stream().map(this::getPostResponse).collect(Collectors.toList());
    }

    public List<PostResponse> findBy(Map<String, String> params) {
        String createdBy = params.get("createdBy");
        String initialDate = params.get("initialDate");
        String finalDate = params.get("finalDate");

        List<Post> posts = new ArrayList<>();

        if(!StringUtils.isEmpty(initialDate)){

            if(StringUtils.isEmpty(finalDate)){

                if(StringUtils.isEmpty(createdBy)){

                    posts = postRepository.findByCreatedDateGreaterThanEqual(getDate(initialDate));

                }else{

                    posts = postRepository.findByCreatedBy_IdAndCreatedDateGreaterThanEqual(
                            Integer.valueOf(createdBy), getDate(initialDate));

                }

            }else {

                if(StringUtils.isEmpty(createdBy)){

                    posts = postRepository.findByCreatedDateBetween(getDate(initialDate), getDate(finalDate));

                }else{

                    posts = postRepository.findByCreatedBy_IdAndCreatedDateBetween(
                            Integer.valueOf(createdBy), getDate(initialDate), getDate(finalDate));
                }
            }

        }else if(!StringUtils.isEmpty(createdBy)){

            posts = postRepository.findByCreatedBy_Id(Integer.valueOf(createdBy));

        }

        return posts.stream().map(this::getPostResponse).collect(Collectors.toList());
    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date);
    }

    private PostResponse getPostResponse(Post p) {

        return new PostResponse(
                p.getId(),
                p.getTitle(),
                p.getBody(),
                p.getCreatedBy().getFullName(),
                p.getCreatedDate());
    }

    public void update(Integer postId, PostRequest postRequest, String userName) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        if(post.getCreatedBy().getEmail().equals(userName)){

            if(!StringUtils.isEmpty(postRequest.getBody())){
                post.setBody(postRequest.getBody());
            }

            if(!StringUtils.isEmpty(postRequest.getTitle())){
                post.setTitle(postRequest.getTitle());
            }

            postRepository.save(post);

        }else{

            throw new UnauthorizedException();

        }

    }
}
