package com.company.blog;

import com.company.blog.repository.OnlineUserRepository;
import com.company.blog.repository.PostRepository;
import com.company.blog.repository.TagRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationPostTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private OnlineUserRepository onlineUserRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testViewPosts() throws Exception {

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testViewPostsSortByTitle() throws Exception {

        mockMvc.perform(get("/api/posts")
                        .param("sort", "title,desc"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testViewPostsByAuthorName() throws Exception {

        mockMvc.perform(get("/api/posts/search/findAllPostsByOnlineUserName")
                        .param("name", "Jack"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testViewPostsByTitle() throws Exception {

        mockMvc.perform(get("/api/posts/search/findByTitle")
                        .param("title", "Learning Html"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testViewPostsTitleLike() throws Exception {

        mockMvc.perform(get("/api/posts/search/findByTitleLike")
                        .param("title", "%Learning%"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testViewPostsTitleContains() throws Exception {

        mockMvc.perform(get("/api/posts/search/findByTitleContains")
                        .param("title", "Html"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }
}
