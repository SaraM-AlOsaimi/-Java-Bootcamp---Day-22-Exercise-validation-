package com.example.eventvalidation.Controller;


import com.example.eventvalidation.ApiResponse.Response;
import com.example.eventvalidation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event/validation")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    //• Display all event .
    @GetMapping("/get")
    public ResponseEntity<?> getEvents(){
        return ResponseEntity.status(200).body(events);
    }

    // Create a new event (ID , description , capacity, startDate , endDate)
    @PostMapping("/add")
public ResponseEntity<?> createEvent(@RequestBody @Valid Event event, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new Response("Event added successfully"));
}

    //• Update an event
    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateEvent(@PathVariable int index , @RequestBody @Valid Event event, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new Response("Event updated Successfully"));
    }
    //• Delete an event
    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteEvent(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(200).body(new Response("Event deleted Successfully"));
    }
    //• Change capacity
    @PutMapping("/change/capacity/{index}")
    public ResponseEntity<?> changeCapacity(@PathVariable int index , @RequestParam int capacity) {
        if (capacity <= 25) {
            return ResponseEntity.status(400).body(new Response("Capacity must be greater than 25"));
        }
        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(200).body(new Response("Event Capacity changed"));
    }

    //• Search for an event by given id
    @GetMapping("/get/event/by/id")
    public ResponseEntity<?> getEventById(@RequestParam String id){
        if(id.length() <= 2){
            return ResponseEntity.status(400).body(new Response("ID length must be greater than 2"));
        }
        for (Event event : events) {
            if (event.getId().equalsIgnoreCase(id)) {
                return ResponseEntity.status(200).body(new Response("event is found") );
            }
        }
        return ResponseEntity.status(404).body(new Response("Event not found"));
    }


}
