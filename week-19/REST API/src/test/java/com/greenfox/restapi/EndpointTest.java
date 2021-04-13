package com.greenfox.restapi;

import com.greenfox.restapi.controllers.MainController;
import com.greenfox.restapi.models.DoublingNumber;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.ContentResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(MainController.class)

public class EndpointTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void doublingShouldDoubleValueFromInput () throws Exception {
        mockMvc.perform(get("/doubling?input=5"))
                .andExpect(status().is(200))
                .andExpect((ResultMatcher) content().json("{received:5,result:10,error:null}"));
    }

}
