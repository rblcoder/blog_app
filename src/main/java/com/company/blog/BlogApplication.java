package com.company.blog;

import com.company.blog.entity.Post;
import com.company.blog.entity.Tag;
import com.company.blog.repository.PostRepository;
import com.company.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class BlogApplication {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }


    public @PostConstruct
    void init() {

        Tag javaTag = new Tag("Java");
        Tag htmlTag = new Tag("Html");
        tagRepository.save(htmlTag);
        tagRepository.save(javaTag);
        tagRepository.save(new Tag("Database"));

        postRepository.save(new Post("Learning Html", "Found courses on FreecodeCamp", htmlTag));
        postRepository.save(new Post("Learning Java", "Attended Bootcamp", javaTag));


    }

}