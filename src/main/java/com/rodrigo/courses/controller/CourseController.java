package com.rodrigo.courses.controller;

import com.rodrigo.courses.entity.Course;
import com.rodrigo.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public Course save(@RequestBody Course c) {
        return courseService.save(c);
    }

    @GetMapping
    public List<Course> findAllCourses(){
        return courseService.listAll();
    }

    @DeleteMapping("/delete/{id}")
    public void courseDelete(@PathVariable Long id){
        courseService.deleteCourse(id);
    }

    @GetMapping("/listar/{id}")
    public Course listOne(@PathVariable Long id){
        return courseService.listOne(id);
    }

}
