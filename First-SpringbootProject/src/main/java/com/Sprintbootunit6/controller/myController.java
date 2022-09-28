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
   @GetMapping (value = "/")   // root path  "/" or with no slash
  public String anyName() {
       System.out.println(" Method to handle GET for URL: / ");
       return "The Launch code for today was 007 ";
   }

    //handle the /Abraham?frank=value
    // to get data of the query parameter , use @RequestParam annotation
    // Get or Delete  the data is in a query parameter /URL?Parameter=value
    @GetMapping (value = "/pedro")   // root path  "/" or with no slash
    public DataToBeReturned  someName(@RequestParam int Stark, @RequestParam String words) {
        System.out.println(" Method to handle GET for URL: /tonyStark ");
        // this where we would read the data from the DataBase
        return new DataToBeReturned(" String value we want to return, ", Stark);
    }

    // controller to handle post
   @PostMapping (value = "/pedro")
   public  String anymanetwo(@RequestBody DataReceived whatWeGOt) {
       System.out.println(" Method for post with kirk ");
       // this where we would Create entry in the  DataBase
       return whatWeGOt.getDataSent();
   }

    @PutMapping (value = "/pedro")
    public  String anythree(@RequestBody DataReceived whatWeGOt) {
        System.out.println(" Method for PUT  with kirk ");
        // this where we would Update the  DataBase
        return whatWeGOt.getDataSent();
    }
    // Get or Delete  the data is in a query parameter /URL?Parameter=value
    @DeleteMapping (value = "/pedro")
    public  String anyFour(@RequestParam int Stark, @RequestParam String words) {
        System.out.println(" Method for Delete with kirk ");
        // this where we would delete from DataBase
        return "Delete method call";
    }

    // Data objects are normally NOT sent with a GET HTTP request
    // POST request used since we want to send data to server
    // Note use of @RequestBody in parameter to method
    //      this tells the server to convert the JSON in the request
    //      to the Object specified - the  default ctro and setters for class
    //      are used to instantiate and populate the new object.
    //
    //      The JSON data attribute names must match the name of the data members in the class
    //
    @PostMapping(value="/askQuestion")   // handle the path - /askQuestion for POST
    public Answer questionMethod(HttpServletRequest aRequest, @RequestBody Question questionAsked) throws JsonProcessingException {

        logRequest(aRequest);                                             // Write HTTP request to log
        ObjectWriter ow = new ObjectMapper().writer();                    // Convert object received to JSON
        logMessage("with body: " + ow.writeValueAsString(questionAsked)); // add data from body of request to log

        Answer anAnswer = new Answer();

        switch(questionAsked.getQuestionText())   {
            case "Who is teaching unit 6?":
                anAnswer.setTheAnswer("Frank");
                break;
            case "Who is teaching unit 1?":
                anAnswer.setTheAnswer("Brian");
                break;
            case "Who is teaching unit 2?":
                anAnswer.setTheAnswer("Daniel");
                break;
            case "Who is teaching unit 4?":
                anAnswer.setTheAnswer("Mauli");
                break;
            case "Who is teaching unit 5?":
                anAnswer.setTheAnswer("Tom");
                break;
            default:
                anAnswer.setTheAnswer("You have stumped me!");
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
