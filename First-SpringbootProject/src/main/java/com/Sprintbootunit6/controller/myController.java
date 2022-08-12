package com.Sprintbootunit6.controller;


import com.Sprintbootunit6.model.DataReceived;
import com.Sprintbootunit6.model.DataToBeReturned;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping (value = "/kirk")   // root path  "/" or with no slash
    public DataToBeReturned  someName(@RequestParam int Stark, @RequestParam String words) {
        System.out.println(" Method to handle GET for URL: /tonyStark ");
        // this where we would read the data from the DataBase
        return new DataToBeReturned(" String value we want to return, ", Stark);
    }

    // controller to handle post
   @PostMapping (value = "/kirk")
   public  String anymanetwo(@RequestBody DataReceived whatWeGOt) {
       System.out.println(" Method for post with kirk ");
       // this where we would Create entry in the  DataBase
       return whatWeGOt.getDataSent();
   }

    @PutMapping (value = "/kirk")
    public  String anythree(@RequestBody DataReceived whatWeGOt) {
        System.out.println(" Method for PUT  with kirk ");
        // this where we would Update the  DataBase
        return whatWeGOt.getDataSent();
    }
    // Get or Delete  the data is in a query parameter /URL?Parameter=value
    @DeleteMapping (value = "/kirk")
    public  String anyFour(@RequestParam int Stark, @RequestParam String words) {
        System.out.println(" Method for Delete with kirk ");
        // this where we would delete from DataBase
        return "Delete method call";
    }
}
