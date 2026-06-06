package com.dasther.nramirez.m5c7.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasther.nramirez.m5c7.model.Course;
import com.dasther.nramirez.m5c7.service.CourseService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/course")//simplifico el codigo para no repetir lo mismo en los endpoint
public class CourseController {

    private CourseService courseService;
    
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourse() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getOneById(id);
    }
    
    @GetMapping("/find")
    public List<Course> getCourseContainsName(@RequestParam("nombre") String name) {
        return courseService.getAllThatContainsName(name);
    }
    
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
     
        
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        courseService.delete(id);
    }
    
    
    
}
