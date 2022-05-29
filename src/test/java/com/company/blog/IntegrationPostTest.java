package com.company.blog;

import com.company.blog.entity.Post;
import com.company.blog.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationPostTest {

    @LocalServerPort
    int randomServerPort;
    @Autowired
    private TestRestTemplate restTemplate;

    @DirtiesContext
    @Test
    public void testAddBlogSuccess() throws URISyntaxException {
        final String baseUrlTag = "http://localhost:" + randomServerPort + "/api/tags";
        URI uriTag = new URI(baseUrlTag);
        Tag tag = new Tag(null, "Java");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Tag> requestTag = new HttpEntity<>(tag, headers);

        ResponseEntity<String> resultTag = this.restTemplate.postForEntity(uriTag, requestTag, String.class);

        final String baseUrlPost = "http://localhost:" + randomServerPort + "/api/posts";

        tag.setId(1L);

        URI uriPost = new URI(baseUrlPost);

        Post post = new Post(null, "Blog Title", "Blog Text",
                tag);

        HttpEntity<Post> requestPost = new HttpEntity<>(post, headers);

        ResponseEntity<String> resultPost = this.restTemplate.postForEntity(uriPost, requestPost, String.class);

        Assertions.assertEquals(201, resultPost.getStatusCodeValue());

        Assertions.assertTrue(resultPost.getBody().contains("http://localhost:" + randomServerPort + "/api/posts/" + 1));

    }
}
