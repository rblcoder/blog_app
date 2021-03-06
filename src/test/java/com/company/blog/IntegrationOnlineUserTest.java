package com.company.blog;

import com.company.blog.repository.OnlineUserRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationOnlineUserTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private OnlineUserRepository onlineUserRepository;


    @Test
    public void testGetUsers() throws Exception {

        mockMvc.perform(get("/api/users"))

                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testOnlineUserfindByLocationCountry() throws Exception {

        mockMvc.perform(get("/api/users/search/findByLocationCountry")
                        .param("country", "India"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    @DirtiesContext
    public void testCreateUser() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"Prachi\"," +
                                " \"emailId\": \"prachi@email.com\"," +
                                " \"password\": \"password\"}"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testfindUsersWithPostTitleLike() throws Exception {
        mockMvc.perform(get("/api/users/search/findUsersWithPostTitleLike")
                        .param("title", "%Java%"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void testGetPostsForAUserWithIdOne() throws Exception {
        mockMvc.perform(get("/api/users/1/posts"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void testGetPostWithIdOneForAUserWithIdOne() throws Exception {
        mockMvc.perform(get("/api/users/1/posts/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.title")
                        .value("Learning Html"))
                .andDo(print());
    }
}
