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
import org.wjy.gameforu.user.controller.ReplyCommentController;
import org.wjy.gameforu.user.service.CommentService;
import org.wjy.gameforu.user.service.ReplyCommentService;
import org.wjy.gameforu.vo.GameCommentVo;
import org.wjy.gameforu.vo.ReplyCommentVo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReplyCommentController.class)
@ContextConfiguration(classes= ServiceUserApplication.class)
@AutoConfigureMockMvc
public class ReplyCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReplyCommentService replyCommentService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/reply/user/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddComment() throws Exception {
        ReplyCommentVo replyCommentVo = new ReplyCommentVo();
        replyCommentVo.setReplyComment("very good view point!");
        String s = JSON.toJSONString(replyCommentVo);
        mockMvc.perform(post( "/reply/user/reply/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete( "/reply/user/delete/1"))
                .andExpect(status().isOk());
    }
}