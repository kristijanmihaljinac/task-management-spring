package com.kristijanmihaljinac.taskmanagementspring.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristijanmihaljinac.taskmanagementspring.repository.JpaTaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    void dashboard_opened() throws Exception {
        this.mockMvc
                .perform(
                        get("/task/dashboard")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("admin").password("admin").roles("USER", "ADMIN"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("pages/task_dashboard"));
    }


}
