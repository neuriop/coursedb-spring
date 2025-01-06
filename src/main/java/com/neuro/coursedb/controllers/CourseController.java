package com.neuro.coursedb.controllers;

import com.neuro.coursedb.models.Course;
import com.neuro.coursedb.services.GenericReadWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {
    @Autowired private GenericReadWriteService genericReadWriteService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Course>> getCourses(){
        return ResponseEntity.of(Optional.ofNullable(genericReadWriteService.getData(Course.class)));
    }

    @PutMapping(value = "/save")
    public ResponseEntity<String> saveCourses(@RequestBody List<Course> courses){
        genericReadWriteService.writeData(courses, Course.class);
        return ResponseEntity.ok("Wrote list to file");
    }

}
