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
import org.wjy.gameforu.user.controller.ReplyCommentController;
import org.wjy.gameforu.user.controller.ScoreController;
import org.wjy.gameforu.user.service.ReplyCommentService;
import org.wjy.gameforu.user.service.ScoreService;
import org.wjy.gameforu.vo.GameScoreVo;
import org.wjy.gameforu.vo.ReplyCommentVo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScoreController.class)
@ContextConfiguration(classes= ServiceUserApplication.class)
@AutoConfigureMockMvc
public class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScoreService scoreService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/score/user/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddComment() throws Exception {
        GameScoreVo gameScoreVo = new GameScoreVo();
        gameScoreVo.setScore(10);
        String s = JSON.toJSONString(gameScoreVo);
        mockMvc.perform(post( "/score/user/score/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
    }

}