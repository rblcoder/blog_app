package com.company.blog;

import com.company.blog.entity.Author;
import com.company.blog.entity.Post;
import com.company.blog.entity.Tag;
import com.company.blog.repository.AuthorRepository;
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

    @Autowired
    AuthorRepository authorRepository;

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

        Author authorJack = new Author("Jack", "jack@rmail.com", "jack123");
        Author authorPramita = new Author("Pramita", "pramita@rmail.com", "pramita123");
        authorRepository.save(authorJack);
        authorRepository.save(authorPramita);


        postRepository.save(new Post("Learning Html", "Found courses on FreecodeCamp", htmlTag, authorJack));
        postRepository.save(new Post("Learning Java", "Attended Bootcamp", javaTag, authorPramita));


    }

}