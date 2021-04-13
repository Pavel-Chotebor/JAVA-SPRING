package com.greenfox.groot;


import com.greenfox.groot.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class GrootEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    private void grootMethodShould



}
