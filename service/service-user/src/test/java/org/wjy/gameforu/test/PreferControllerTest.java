package org.wjy.gameforu.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.wjy.gameforu.ServiceUserApplication;
import org.wjy.gameforu.user.controller.PreferController;
import org.wjy.gameforu.user.service.PreferService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PreferController.class)
@ContextConfiguration(classes= ServiceUserApplication.class)
@AutoConfigureMockMvc
public class PreferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreferService preferService;

    @Test
    public void testGetPrefer() throws Exception {
        mockMvc.perform(get("/prefer/user/prefer/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSetPrefer() throws Exception {
        Integer[] preferIds = {1,2,3};
        mockMvc.perform(post( "/prefer/user/setprefer")
                        .requestAttr("uid",1)
                        .requestAttr("preferIds",preferIds)
                )
                .andExpect(status().isOk());
    }
}