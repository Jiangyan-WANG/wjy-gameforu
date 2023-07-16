package org.wjy.gameforu.test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.wjy.gameforu.ServiceUserApplication;
import org.wjy.gameforu.user.controller.CommentController;
import org.wjy.gameforu.user.service.CommentService;
import org.wjy.gameforu.vo.GameCommentVo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@ContextConfiguration(classes= ServiceUserApplication.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/comment/user/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddComment() throws Exception {
        GameCommentVo gameCommentVo = new GameCommentVo();
        gameCommentVo.setGameComment("very good game!");
        String s = JSON.toJSONString(gameCommentVo);
        mockMvc.perform(post( "/comment/user/comment/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete( "/comment/user/delete/1"))
                .andExpect(status().isOk());
    }
}