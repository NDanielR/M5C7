package com.dasther.nramirez.m5c7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.dasther.nramirez.m5c7.Exception.CourseNotFoundException;
import com.dasther.nramirez.m5c7.model.Course;

@Service

public class CourseServiceInMemory implements CourseService {

    private AtomicLong consecutive;
    private List<Course> courses;

    public CourseServiceInMemory() {
        consecutive = new AtomicLong(1);
        courses = new ArrayList<>();
    }

    @Override
    public List<Course> getAll() {
        return new ArrayList<Course>(courses);
    }

    @Override
    public Course getOneById(Long id) {
        validateId(id);
        return courses.stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new CourseNotFoundException(
                String.format("El curso con ID '%d' no existe", id)));
    }

    @Override
    public List<Course> getAllThatContainsName(String partialName) {

        if(isBlank(partialName)){
            return getAll();
        }

        return courses.stream()
                .filter(c -> c.getName().contains(partialName)
                        || c.getDescription().contains(partialName))
                .toList();
    }

    @Override
    public Course create(Course course) {
        validateCourse(course);

        course.setId(consecutive.getAndIncrement());

        courses.add(course);

        return course;
    }

    @Override
    public Course update(Long id, Course course) {
        validateId(id);
        validateCourse(course);

        var existingCourse = getOneById(id);
        existingCourse.setCode(course.getCode());
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setInitialDate(course.getInitialDate());
        existingCourse.setFinalDate(course.getInitialDate());
        existingCourse.setCredits(course.getCredits());
        return course;
    }

    @Override
    public void delete(Long id) {
        validateId(id);
        var existingCourse = getOneById(id);
        courses.remove(existingCourse);
    }

    private void validateId(Long id) {
        if (id == null){
            throw new IllegalArgumentException("No se agrego ID a la busqueda");
        }

        if(id < 0){
            throw new IllegalArgumentException("El ID no puede se igual o menor de 0");
        }
    }

    private boolean isBlank (String str){
        return str == null || str.isBlank();
    }

    private void validateCourse(Course course) {
        if(course == null){
            throw new IllegalArgumentException("No fue enviado un curso a guardar");
        }

        if(course.getName() == null || course.getName().isBlank()){
            throw new IllegalArgumentException("El nombre del curso es obligatorio");
        } 

        if(course.getCredits() <= 0){
            throw new IllegalArgumentException("los creditos del curso deben se mayores a 0");
        }

        if (course.getInitialDate().isAfter(course.getFinalDate())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser mayor que la final");            
        }
    }
}
