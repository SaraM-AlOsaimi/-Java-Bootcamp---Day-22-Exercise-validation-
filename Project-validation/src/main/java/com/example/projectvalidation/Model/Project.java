package com.example.projectvalidation.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "ID can't be empty!")
    @Size(min = 3, message = "ID size too short, should be more than 2") // Length more than 2
    private String id;

    @NotEmpty(message = "Title is Empty")
    @Size(min = 9, message = "title size should be more than 8")
   private String title;

    @NotEmpty(message = "Description is Empty")
    @Size(min = 16, message = "description length should be more than 15!")
   private String description;

    @NotEmpty(message = "Status is Empty")
    // must be Not Started or in Progress or Completed only
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be : (Not Started , or In Progress, or Completed) only")
   private String status;

    @NotEmpty(message = "Company Name is Empty")
    @Size(min = 7, message = "Company Name length must be more than 6")
   private String companyName;


}
