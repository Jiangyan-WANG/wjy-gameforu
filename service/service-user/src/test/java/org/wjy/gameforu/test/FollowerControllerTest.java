package org.wjy.gameforu.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.wjy.gameforu.ServiceUserApplication;
import org.wjy.gameforu.user.controller.FollowerController;
import org.wjy.gameforu.user.service.FollowerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FollowerController.class)
@ContextConfiguration(classes= ServiceUserApplication.class)
@AutoConfigureMockMvc
public class FollowerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FollowerService followerService;

    @Test
    public void testPostFollow() throws Exception {
        mockMvc.perform(post("/follower/user/follow/1/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUnfollow() throws Exception {
        mockMvc.perform(delete( "/follower/user/unfollow/1"))
                .andExpect(status().isOk());
    }
}