package com.kristijanmihaljinac.taskmanagementspring.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kristijanmihaljinac.taskmanagementspring.domain.Priority;
import com.kristijanmihaljinac.taskmanagementspring.domain.Status;
import com.kristijanmihaljinac.taskmanagementspring.domain.Task;
import com.kristijanmihaljinac.taskmanagementspring.domain.User;
import com.kristijanmihaljinac.taskmanagementspring.domain.enums.PriorityEnum;
import com.kristijanmihaljinac.taskmanagementspring.domain.enums.StatusEnum;
import com.kristijanmihaljinac.taskmanagementspring.repository.JpaTaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class TaskRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JpaTaskRepository jpaTaskRepository;

    @Test
    void findAll() throws Exception {
        this.mockMvc.perform(
                        get("/api/task")
                                .with(user("admin").password("admin").roles("ADMIN", "USER"))
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Transactional
    @Test
    void delete_adminRights() throws Exception {
        final long TEST_DELETE_TASK_ID = 1L;

        assertTrue(jpaTaskRepository.findById(TEST_DELETE_TASK_ID).isPresent());

        this.mockMvc.perform(
                        delete("/api/task/" + TEST_DELETE_TASK_ID)
                                .with(user("admin").password("admin").roles("ADMIN", "USER"))
                                .with(csrf())
                )
                .andExpect(status().isNoContent());

        assertTrue(jpaTaskRepository.findById(TEST_DELETE_TASK_ID).isEmpty());
    }

    @Test
    void delete_noAdminRights() throws Exception {
        final long TEST_DELETE_TASK_ID = 1L;

        this.mockMvc.perform(
                        delete("/api/task/" + TEST_DELETE_TASK_ID)
                                .with(user("mmarkic").password("admin").roles("USER"))
                                .with(csrf())
                )
                .andExpect(status().isForbidden());
    }

    @Transactional
    @Test
    void updateSpecialRemark() throws Exception {
        final long TEST_UPDATE_TASK_ID = 1L;
        final String TEST_UPDATED_SUBJECT = "Test subject";

        this.mockMvc.perform(
                        put("/api/task/" + TEST_UPDATE_TASK_ID + "/update-subject?subject=" + TEST_UPDATED_SUBJECT)
                                .with(user("admin").password("admin").roles("ADMIN", "USER"))
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value(TEST_UPDATED_SUBJECT));

    }


    @Transactional
    @Test
    void save() throws Exception {
        String TEST_TASK_SUBJECT = "Test Subject";
        String TEST_DESCRIPTION = "Test description";
        User ASSIGNED_BY = new User();
        ASSIGNED_BY.setUsername("admin");

        User ASSIGNED_TO = new User();
        ASSIGNED_BY.setUsername("mmarkic");

        Status STATUS = new Status();
        STATUS.setId(StatusEnum.TODO.getValue());

        LocalDateTime DEDLINE_APPOINTMENT = LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.MINUTES);


        Priority TEST_PRIORITY = new Priority();
        TEST_PRIORITY.setId(PriorityEnum.HIGH.getValue());

        String TEST_TEST_DESCRIPTION = "Test description";

        Task task = new Task();
        task.setSubject(TEST_TASK_SUBJECT);
        task.setDescription(TEST_DESCRIPTION);
        task.setAssignedBy(ASSIGNED_BY);
        task.setAssignedTo(ASSIGNED_TO);
        task.setStatus(STATUS);
        task.setPriority(TEST_PRIORITY);
        task.setDeadline(DEDLINE_APPOINTMENT);
        task.setAssignedToUsername("mmarkic");
        task.setPriorityId(TEST_PRIORITY.getId());

        this.mockMvc.perform(
                        post("/api/task")
                                .with(user("test").password("testpass").roles("ADMIN", "USER"))
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(task))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subject").value(TEST_TASK_SUBJECT))
                .andExpect(jsonPath("$.description").value(TEST_DESCRIPTION));

    }
}
