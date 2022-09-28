package com.Sprintbootunit6.controller;


import com.Sprintbootunit6.model.Answer;
import com.Sprintbootunit6.model.DataReceived;
import com.Sprintbootunit6.model.DataToBeReturned;
import com.Sprintbootunit6.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class myController {

    // Data may be included with http request sent to server
    // Get or Delete  the data is in a query parameter /URL?Parameter=value
    // Post or put  data is sent by request body

    // to handle HTTP request
    //Read
   @GetMapping (value = "/")   // root path  "/" or with no slash
  public String anyName() {
       System.out.println(" Method to handle GET for URL: / ");
       return "Welcome to My SpringBoot Rocket : Launch code for today is 007 ";
   }

    //handle the /Abraham?frank=value     localhost:8080/pedro?star=26&comet=test1
    // to get data of the query parameter , use @RequestParam annotation
    // Get or Delete  the data is in a query parameter /URL?Parameter=value
    //Read
    @GetMapping (value = "/pedro")   // root path  "/" or with no slash
    public DataToBeReturned  someName(@RequestParam int star, @RequestParam String comet) { //int and string
        System.out.println(" Method to handle GET for URL: /comet ");
        // this where we would read the data from the DataBase
        System.out.println(comet);
        return new DataToBeReturned(" String value we want to return, ", star, comet );

    }

    // controller to handle post   localhost:8080/pedro
    // {
    //    "dataSent":" testing password"    use in body of postman request
    //}
    // create
   @PostMapping (value = "/pedro")
   public  String anymanetwo(@RequestBody DataReceived whatWeGOt) {
       System.out.println(" Method for post with pedro");
       // this where we would Create entry in the  DataBase
       return whatWeGOt.getDataSent();
   }
    //localhost:8080/pedro
    // {
    //    "dataSent":" testing password"    use in body of postman request
    //}
    //update
    @PutMapping (value = "/pedro") //datasent from DataREcived class
    public  String anythree(@RequestBody DataReceived whatWeGOt) {
        System.out.println(" Method for PUT  with pedro ");
        // this where we would Update the  DataBase
        return whatWeGOt.getDataSent();
    }

    // Get or Delete  the data is in a query parameter /URL?Parameter=value
    @DeleteMapping (value = "/pedro")
    public  String anyFour(@RequestParam int Stark, @RequestParam String words) {
        System.out.println(" Method for Delete with pedro ");
        // this where we would delete from DataBase
        return "Delete method call";
    }

    // Data objects are normally NOT sent with a GET HTTP request
    // POST request used since we want to send data to server
    // Note use of @RequestBody in parameter to method
    //      this tells the server to convert the JSON in the request
    //      to the Object specified - the  default constructor and setters for class
    //      are used to instantiate and populate the new object.
    //
    //      The JSON data attribute names must match the name of the data members in the class
    //

    //localhost:8080/askQuestion
//    {
//        "questionText":"How many miles is Neptune from the Sun?"  in JSON body in postman
//    }
    @PostMapping(value="/askQuestion")   // handle the path - /askQuestion for POST
    public Answer questionMethod(HttpServletRequest aRequest, @RequestBody Question questionAsked) throws JsonProcessingException {

        logRequest(aRequest);                                             // Write HTTP request to log
        ObjectWriter ow = new ObjectMapper().writer();                    // Convert object received to JSON
        logMessage("with body: " + ow.writeValueAsString(questionAsked)); // add data from body of request to log

        Answer anAnswer = new Answer();

        switch(questionAsked.getQuestionText())   {
            case "How many miles is Earth from the Sun?":
                anAnswer.setTheAnswer(" 92.96 million miles");
                break;
            case "How many miles is Mars from the Sun?":
                anAnswer.setTheAnswer(" 141.6 million miles");
                break;
            case "How many miles is Jupiter from the Sun?":
                anAnswer.setTheAnswer(" 483.8 million miles");
                break;
            case "How many miles is Saturn from the Sun?":
                anAnswer.setTheAnswer(" 890.8 million miles");
                break;
            case "How many miles is Neptune from the Sun?":
                anAnswer.setTheAnswer(" 2.793 billion miles");
                break;
            default:
                anAnswer.setTheAnswer("Congratulations !! You have crashed on a meteor headed for the Sun !");
        }

        return anAnswer;
    }

    // Log request with timestamp
    private void logRequest(HttpServletRequest theRequest) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);

        System.out.printf("%s --> %4s %4s request for URL: %s%s\n",
                timeNow
                , theRequest.getProtocol()
                , theRequest.getMethod()
                , theRequest.getRequestURI()
                , (theRequest.getQueryString() != null ? ("?" + theRequest.getQueryString()) : ""));

    }

    // log a message passed in as a parameter
    private void logMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);

        System.out.printf("%s --> %s\n", timeNow, message);
    }



}
