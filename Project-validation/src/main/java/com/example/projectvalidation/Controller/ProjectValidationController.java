package com.example.projectvalidation.Controller;


import com.example.projectvalidation.ApiResponse.Response;
import com.example.projectvalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/project/validation")
public class ProjectValidationController {

    ArrayList<Project> projects = new ArrayList<>();

    //• Display all project .
    @GetMapping("/get")
public ResponseEntity<?> getProjects(){
    return ResponseEntity.status(200).body(projects);
}

//Create a new project (ID,title , description , status, companyName)
    @PostMapping("/add")
public ResponseEntity<?> createProject(@RequestBody @Valid Project project, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new Response("Project added successfully"));
}

//• Update a project
    @PutMapping("/update/{index}")
public ResponseEntity<?> updateProject(@PathVariable int index , @RequestBody @Valid Project project , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index,project);
        return ResponseEntity.status(200).body(new Response("Project updated successfully"));
}
//• Delete a project
    @DeleteMapping("/delete/{index}")
public ResponseEntity<?> deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body(new Response("Project Deleted Successfully"));
}

//• Change the project status form Not Started to in Progress , from in Progress to Completed , and if it completed doesn't need to change
@PutMapping("/chang/status/{index}")
    public ResponseEntity<?> changeStatus(@PathVariable int index){
    Project project = projects.get(index);
            if(project.getStatus().equalsIgnoreCase("Not Started")){
                project.setStatus("In Progress");
                return ResponseEntity.status(200).body(project);
            } else if (project.getStatus().equalsIgnoreCase("In progress")) {
                project.setStatus("Completed");
                return ResponseEntity.status(200).body(project);
            } else return ResponseEntity.status(200).body(new Response("Project Status already completed"));
        }

//• Search for a project by given title
    @GetMapping("/search/by/title")
public ResponseEntity<?> searchForProject(@RequestParam String title ){
        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body( project);
            }
        } return ResponseEntity.status(404).body("Project with the given title not found ");
}

// Display All project for one company by companyName.
    @GetMapping("/get/project/company/name")
    public ResponseEntity<?> displayAllProject(@RequestParam String companyName ){
        ArrayList<Project> projectByComName = new ArrayList<>();
        for(Project p : projects){
            if(p.getCompanyName().equalsIgnoreCase(companyName)){
                projectByComName.add(p);
            }
            if (projectByComName.isEmpty()) {
                return ResponseEntity.status(404).body("No projects found for the given company name.");
            }
        }return ResponseEntity.status(200).body(projectByComName);
}


}
