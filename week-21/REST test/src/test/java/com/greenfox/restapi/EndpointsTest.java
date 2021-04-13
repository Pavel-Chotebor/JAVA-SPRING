package com.greenfox.restapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EndpointsTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void doublingShouldDoubleValueFromInput () throws Exception {
        mockMvc.perform(get("/doubling?input=5"))   //SIMULATING REQUEST PARAM
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.received").value(5))   //SAME NAME FOR CREATED OBJECT
                .andExpect(jsonPath("$.result").value(10));
    }

    @Test
    public void doublingWithNoInputShouldCreateNewErrorObject () throws Exception {
        mockMvc.perform(get("/doubling?"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.errorMessage").value("Please provide an input!"));

    }

    @Test
    public void greeterGreetYouWithInputNameAndTitle () throws Exception {
        mockMvc.perform(get("/greeter?name=Pavel&title=student"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.welcome_message").value("Oh, hi there " + "Pavel" + ", my dear " + "student" + "!"));
    }

    @Test
    public void greeterWithNoInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/greeter?"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Please provide a name and a title!"));
    }

    @Test
    public void greeterWithNoNameInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/greeter?title=student"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Please provide a name!"));
    }

    @Test
    public void greeterWithNoTitleInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/greeter?name=Pavel"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Please provide a title!"));
    }

    @Test
    public void appendShouldAppendLetterAInTheEndOfInputString () throws Exception {
        mockMvc.perform(get("/appenda/test"))   //SIMULATING PATH VARIABLE
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.appended").value("testa"));
    }

    @Test
    public void appendWithNoInputShouldHasNotFoundStatus () throws Exception {
        mockMvc.perform(get("/appenda"))
                .andExpect(status().is(404));
    }

    @Test
    public void doUntilWithSumPathVariableShouldSumEveryNumberFrom1toInputNumber () throws Exception {
        mockMvc.perform(post("/doUntil/sum")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"until\": \"5\"}"))     // CONTENT -> DATA SENDING BY POSTMAN, DATA SENDING TO POSTMAPPING
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.result").value("15"));
    }


    @Test
    public void doUntilWithSumPathVariableWithNoInputNumberShouldReturnErrorObject () throws Exception {
        mockMvc.perform(post("/doUntil/sum")
                .content(""))  // IF CONTENT IS EMPTY (NULL DOESNT WORK)
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Please provide a number!"));
    }


    @Test
    public void arrayHandlerWithSumLikeWhatFiledShouldSumAllElementsInArray () throws Exception {
        mockMvc.perform(post("/arrays")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"what\": \"sum\", \"numbers\": [1, 2, 5, 10] }"))  // WHOLE ARRAY IS NOT IN QUOTES
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.result").value("18"));
    }

    @Test
    public void arrayHandlerWithMultiplyLikeWhatFiledShouldMultiplyAllElementsInArray () throws Exception {
        mockMvc.perform(post("/arrays")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"what\": \"multiply\", \"numbers\": [1, 2, 5, 10] }")) // WHOLE ARRAY IS NOT IN QUOTES
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.result").value("100"));
    }


    @Test
    public void arrayHandlerWithDoubleLikeWhatFieldShouldDoubleAllElementsInArray () throws Exception {
        mockMvc.perform(post("/arrays")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"what\": \"double\", \"numbers\": [1, 2, 5, 10] }")) // WHOLE ARRAY IS NOT IN QUOTES
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.numbers[0]").value(2))   // DOESNT WORK WRITE [2, 4, 10, 20]
                .andExpect(jsonPath("$.numbers[1]").value(4))   // SO IT HAS TO BE LIKE ELEMENT BY ELEMENT
                .andExpect(jsonPath("$.numbers[2]").value(10))
                .andExpect(jsonPath("$.numbers[3]").value(20))
                .andExpect(jsonPath("$.numbers", hasSize(4))); // CHECKING SIZE
    }



    @Test
    public void arrayHandlerWithNoWhatFieldShouldReturnErrorObject() throws Exception {
        mockMvc.perform(post("/arrays")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numbers\": [1, 2, 5, 10] }"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Please provide what to do with the numbers!"));
    }


    //WORKING, BUT ERROR WITH RANDOM
    @Test
    public void reverserOfTheSithShouldMoveEverySecondWordToLeftAndKeepGrammarRulesAndPrintRandomWordsToEnd() throws Exception {
        mockMvc.perform(post("/sith")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\": \"This is my example sentence\"}"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.text", containsString("Is this example my sentence."))) //THIS PART CONTAINS ALL THE TIME
                .andExpect(jsonPath("$.text",
                        anyOf(containsString("Err..err.err."), // THIS WORDS ARE RANDOM
                        (containsString("Arrgh.")),
                        (containsString("Uhmm.")),
                        (containsString("Hmmm.")))));

    }

    @Test
    public void grootWithTextInputShouldSayOnlyIamGroot () throws Exception {
        mockMvc.perform(get("/groot?text=sometext"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.translated").value("I am Groot"));
    }




    @Test
    public void grootWithNoTextInputShouldSayOnlyIamGroot () throws Exception {
        mockMvc.perform(get("/groot"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("I am Groot"));
    }




    @Test
    public void arrowShouldAcceptDistanceAndTimeInputAndCountSpeed () throws Exception {
        mockMvc.perform(get("/arrow?distance=100&time=10"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.distance").value("100"))
                .andExpect(jsonPath("$.time").value("10"))
                .andExpect(jsonPath("$.speed").value("10"));
    }

    @Test
    public void arrowWithNoTimeInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/arrow?distance=100"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Missing time parameter"));

    }

    @Test
    public void arrowWithNoDistanceInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/arrow?time=10"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Missing distance parameter"));

    }

    @Test
    public void arrowWithNoInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/arrow"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Missing distance and time parameter"));
    }

    @Test
    public void arrowWithNoZeroTimeInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/arrow?distance=0&time=100"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Parameters cannot be 0"));
    }

    @Test
    public void arrowWithNoZeroDistanceInputShouldReturnErrorObject () throws Exception {
        mockMvc.perform(get("/arrow?distance=100&time=0"))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$.errorMessage").value("Parameters cannot be 0"));
    }
}