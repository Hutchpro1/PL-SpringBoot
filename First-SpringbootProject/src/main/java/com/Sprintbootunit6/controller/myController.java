package com.Sprintbootunit6.controller;


import com.Sprintbootunit6.model.DataReceived;
import com.Sprintbootunit6.model.DataToBeReturned;
import org.springframework.web.bind.annotation.*;

@RestController
public class myController {

    // Data may be included with http request sent to server
    // Get or Delete  the data is in a query parameter /URL?Parameter=value
    // Post or put  data is request body

    // to handle HTTP request
   @GetMapping (value = "/")   // root path  "/" or with no slash
  public String anyName() {
       System.out.println(" Method to handle GET for URL: / ");
       return "attendance code for today was 4122";
   }


   //handle the /Abraham?frank=value
    // to get data of the query parameter , use @RequestParam annaotation
   // Get or Delete  the data is in a query parameter /URL?Parameter=value
    @GetMapping (value = "/Abraham")   // root path  "/" or with no slash
    public DataToBeReturned  someName(@RequestParam int frank, @RequestParam String words) {
        System.out.println(" Method to handle GET for URL: /Abraham ");
        return new DataToBeReturned(" String value we want to return, ", frank );
    }

    // controller to handle post
   @PostMapping (value = "/kirk")
   public  String anymanetwo(@RequestBody DataReceived whatWeGOt) {
       System.out.println(" Method for post with Kirk ");
       return whatWeGOt.getDataSent();
   }

}
