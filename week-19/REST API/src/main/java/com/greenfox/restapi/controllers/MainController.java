package com.greenfox.restapi.controllers;

import com.greenfox.restapi.models.*;
import com.greenfox.restapi.models.Error;
import com.greenfox.restapi.models.InputNumber;
import com.greenfox.restapi.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class MainController {

    private WordService wordService;
    private DoUntilService doUntilService;
    private ArrayHandler arrayHandler;
    private ArrayHandlerService arrayHandlerService;
    private LogService logService;
    private SithTextService sithTextService;

    public MainController(WordService wordService, DoUntilService doUntilService, ArrayHandler arrayHandler, ArrayHandlerService arrayHandlerService, LogService logService
    ,SithTextService sithTextService) {
        this.wordService = wordService;
        this.doUntilService = doUntilService;
        this.arrayHandler = arrayHandler;
        this.arrayHandlerService = arrayHandlerService;
        this.logService = logService;
        this.sithTextService = sithTextService;
    }


    @GetMapping("/doubling")
    public ResponseEntity<?> doubling(@RequestParam(required = false) Integer input) {
        if (input == null) {
            logService.saveLog(new Log("/doubling","input is missing"));
            return new ResponseEntity<>(new Error("Please provide an input!"), HttpStatus.OK);


        } else {
            Integer result = input * 2;
            DoublingNumber doublingNumber = new DoublingNumber(input, result);

            logService.saveLog(new Log("/doubling","?input=" + input));
            return new ResponseEntity<>(doublingNumber, HttpStatus.OK);
        }
    }


    @GetMapping("/greeter")
    public ResponseEntity<?> greeting(@RequestParam(required = false) String name, @RequestParam(required = false) String title) {

        if (name != null && title != null) {
            logService.saveLog(new Log("/greeter","?name=" + name + "&title=" + title));
            return new ResponseEntity<>(new Person(name, title), HttpStatus.OK);


        } else if (name == null && title == null) {
            logService.saveLog(new Log("/greeter","input is missing"));
            return new ResponseEntity<>(new Error("Please provide a name and a title!"), HttpStatus.BAD_REQUEST);



        } else if (name == null) {
            logService.saveLog(new Log("/greeter","input is missing"));
            return new ResponseEntity<>(new Error("Please provide a name!"), HttpStatus.BAD_REQUEST);

        } else {
            logService.saveLog(new Log("/greeter","input is missing"));
            return new ResponseEntity<>(new Error("Please provide a title!"), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/appenda/{appendable}")
    public ResponseEntity<?> append(@PathVariable(required = false) String appendable) {

        if (appendable != null) {
            logService.saveLog(new Log("/appenda/" + appendable, appendable));
            return new ResponseEntity<>(new Word(wordService.appendA(appendable)), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/doUntil/{action}")
    public ResponseEntity<?> doUntil(@PathVariable String action, @RequestBody(required = false) InputNumber inputNumber) {

        if (inputNumber != null) {
            logService.saveLog(new Log("/doUntil/" + action, "until: " + inputNumber));
            return new ResponseEntity<>(new NumberResult(doUntilService.recognizeMathOperationAndCount(action, inputNumber)), HttpStatus.OK);
        } else {
            logService.saveLog(new Log("/doUntil", "input is missing"));
            return new ResponseEntity<>(new Error("Please provide a number!"), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/arrays")  //
    public ResponseEntity<?> arrayHandler(@RequestBody(required = false) ArrayHandler inputArray) {

        // if in "what" field from ArrayHandler sent by postman isn't any value
        if (inputArray.getWhat() == null) {
            logService.saveLog(new Log("/arrays", "input is missing"));
            return new ResponseEntity<>(new Error("Please provide what to do with the numbers!"),
                    HttpStatus.BAD_REQUEST);

            // if in "what" field is value "double" -> do operation returning int[]
        } else if (inputArray.getWhat().equals("double")) {
            logService.saveLog(new Log("/arrays", "what: " + inputArray.getWhat() + ",numbers: " + Arrays.toString(inputArray.getNumbers())));
            return new ResponseEntity<>(new ArrayHandler(arrayHandlerService.doublingElementsInArray(inputArray.getNumbers())),
                    HttpStatus.OK);

        }// else if in "what" field is other values -> recognize this operation by its name and return int result
        return new ResponseEntity<>(new ArrayResult(arrayHandlerService.recognizeOperationReturningSimpleIntAndCount(inputArray)),
                HttpStatus.OK);
    }


    @GetMapping ("/log")   // DONE BUT NOT GOOD.. FINISH IN THE END!
    public ResponseEntity<?> printAllLogs () {
       return new ResponseEntity<>(new LogsResult(logService.getAllLogs().size(), logService.getAllLogsLikeString()),HttpStatus.OK);
    }


    @PostMapping ("/sith")
    public ResponseEntity<?> reverserOfTheSith (@RequestBody Text text) {
        return new ResponseEntity<>(new SithText(sithTextService.convector(text.getText())),HttpStatus.OK);

    }
}