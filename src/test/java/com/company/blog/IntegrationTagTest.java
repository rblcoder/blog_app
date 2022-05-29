package com.company.blog;

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
public class IntegrationTagTest {

    @LocalServerPort
    int randomServerPort;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    public void testAddTagSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/tags";
        URI uri = new URI(baseUrl);
        Tag tag = new Tag(null, "Spring");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<Tag> request = new HttpEntity<>(tag, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);


        Assertions.assertEquals(201, result.getStatusCodeValue());

        Assertions.assertTrue(result.getBody().contains("http://localhost:" + randomServerPort + "/api/tags/" + 1));
    }


}
