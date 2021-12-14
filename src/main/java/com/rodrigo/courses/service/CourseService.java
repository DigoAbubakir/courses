package com.rodrigo.courses.service;

import com.rodrigo.courses.entity.Course;
import com.rodrigo.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }

    public Course listOne(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NullPointerException("Usuario Nao encontrado"));
    }

    //TODO criar função para editar um usuario
    public Course edit(Long id) {
        Course course = listOne(id);
        return null;
    }
}
