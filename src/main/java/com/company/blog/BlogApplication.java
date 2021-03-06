package com.company.blog;

import com.company.blog.entity.*;
import com.company.blog.repository.OnlineUserRepository;
import com.company.blog.repository.PostRepository;
import com.company.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;


@SpringBootApplication
public class BlogApplication {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    OnlineUserRepository onlineUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }


    public @PostConstruct
    void init() {

        Tag javaTag = new Tag("Java");
        Tag htmlTag = new Tag("Html");
        Tag springbootTag = new Tag("Spring boot");
        tagRepository.save(htmlTag);
        tagRepository.save(javaTag);
        tagRepository.save(new Tag("Database"));
        tagRepository.save(springbootTag);

        Location location = new Location("Bengaluru", "Karnataka", "India");
        OnlineUser onlineUserJack = new OnlineUser("Jack", "jack@rmail.com", "jack123");
        onlineUserJack.setLocation(location);
        OnlineUser onlineUserPramita = new OnlineUser("Pramita", "pramita@rmail.com", "pramita123");
        onlineUserRepository.save(onlineUserJack);
        onlineUserRepository.save(onlineUserPramita);

        HashSet<Tag> javaRelatedTags = new HashSet<>();
        javaRelatedTags.add(javaTag);
        javaRelatedTags.add(springbootTag);
        HashSet<Tag> frontEndTags = new HashSet<>();
        frontEndTags.add(htmlTag);
        postRepository.save(new Post("Learning Html", "Found courses on FreecodeCamp", frontEndTags, onlineUserJack));
        postRepository.save(new Post("Build basic page using Html", "Found courses on FreecodeCamp", frontEndTags, onlineUserJack));
        HeaderImage headerImage = new HeaderImage();
        headerImage.setImageUrl("https://loremflickr.com/320/240/java,program");
        headerImage.setActive(true);
        Post javaPost = new Post("Learning Java", "Attended Bootcamp", javaRelatedTags, onlineUserPramita);
        javaPost.setHeaderImage(headerImage);
        postRepository.save(javaPost);


    }

}