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
import org.wjy.gameforu.user.controller.TagController;
import org.wjy.gameforu.user.controller.ThumbsCommentController;
import org.wjy.gameforu.user.service.TagService;
import org.wjy.gameforu.user.service.ThumbsCommentService;
import org.wjy.gameforu.vo.GameTagVo;
import org.wjy.gameforu.vo.ThumbsVo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ThumbsCommentController.class)
@ContextConfiguration(classes= ServiceUserApplication.class)
@AutoConfigureMockMvc
public class ThumbsCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ThumbsCommentService thumbsCommentService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/thumbscomment/user/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddComment() throws Exception {
        ThumbsVo thumbsVo = new ThumbsVo();
        thumbsVo.setThumbs(1);
        String s = JSON.toJSONString(thumbsVo);
        mockMvc.perform(post( "/thumbscomment/user/thumbs/1/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete( "/thumbscomment/user/delete/1"))
                .andExpect(status().isOk());
    }
}