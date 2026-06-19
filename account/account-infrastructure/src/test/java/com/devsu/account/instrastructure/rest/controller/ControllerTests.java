package com.devsu.account.instrastructure.rest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.devsu.account.adapter.viewmodel.AccountRequestModel;
import com.devsu.account.adapter.viewmodel.MovementRequestModel;
import com.devsu.account.instrastructure.config.ConfigurationTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
public class ControllerTests extends ConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/movements - Should return 200 ok and movement list")
    public void shouldReturnMovementsListWhenClientFilterIsApplied() throws Exception {
        mockMvc.perform(get("/api/movement/{id}", "6f56ad15-1714-44a4-9697-03d4e3fb6d35"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("GET /api/account - Should return 200 ok and movement list")
    public void shouldReturnAccountsList() throws Exception {
        mockMvc.perform(get("/api/account"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("POST /api/account - Should return 200 ok and movement list")
    public void shouldCreateAccount() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        AccountRequestModel payload = new AccountRequestModel();
        payload.setAccountType("SAVINGS");
        payload.setCustomerId("7428c3b9-2926-49a0-9bf2-44d46a169d9e");
        payload.setState(true);

        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("POST /api/movement - Should return 200 ok and movement list")
    public void shouldCreateMovement() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        MovementRequestModel payload = new MovementRequestModel();
        payload.setAccountNumber("7753557066");
        payload.setAmount(1000);

        mockMvc.perform(post("/api/movement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

    }

}