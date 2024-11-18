package com.example.eventvalidation.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {

    // event Class : ID , description , capacity, startDate , endDate
    // • ID
    //Cannot be null
    //Length more than 2
    @NotEmpty(message = "ID Can't be null !")
    @Size(min = 3, message = "ID Size must be more than 2")
    private String id;

    //• Description
    //Cannot be null
    //Length more than 15
    @NotEmpty(message = "Description can't be Empty")
    @Size(min = 16, message = "Description length has to be more than 15")
    private String description;

    //• Capacity
    //Cannot be null
    //It has to be number
    //It must be more than 25
    @NotNull(message = "Event Capacity can't be Null!")
    @Min(value = 26 , message = "The event capacity must be greater than 25")
    private Integer capacity;

    //• startDate • endDate

 @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;

 @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;

}
