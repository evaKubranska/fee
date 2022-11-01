package com.kubranska.fees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TransactionIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldGetFee() throws Exception {
        ResultActions resultAction = mockMvc.perform(get("/transaction/A"));
        resultAction.andExpect(status().isOk())
                .andExpect(content().string("10.0"));
    }

    @Test
    void shouldGetFees() throws Exception {
        ResultActions resultAction = mockMvc.perform(post("/transaction")
                                                             .contentType(APPLICATION_JSON)
                                                             .content("{\"A\":2,\"B\":2}"));
        resultAction.andExpect(status().isOk())
                .andExpect(content().string("60.0"));
    }


}
