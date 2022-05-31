package com.company.blog;

import com.company.blog.repository.AuthorRepository;
import com.company.blog.repository.PostRepository;
import com.company.blog.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationAuthorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    public void testGetAuthors() throws Exception {

        mockMvc.perform(get("/api/authors"))

                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    @DirtiesContext
    public void testCreateAuthor() throws Exception {
        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"Prachi\"," +
                                " \"emailId\": \"prachi@email.com\"," +
                                " \"password\": \"password\"}"))
                .andExpect(status().is2xxSuccessful());
    }
}
