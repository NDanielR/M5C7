package com.dasther.nramirez.m5c7.service;

import java.util.List;

import com.dasther.nramirez.m5c7.model.Course;
//JAVADOC
/**
 * Clase de logica de negocio para gestionar cursos
 */
public interface CourseService {

    /**
     * Lista los cursos existentes en el sistema
     * 
     * @return Todos los cursos exixtentes. En caso de no haber cursos devuelve una lista vacia.
    */
    
    List<Course> getAll();

    /**
     * cunsulta el curso que tenga asignado el id dado
     * @param id el identificador del curso a buscar
     * @return la informacion del curso que tiene el id asignado
     * @throws IllegalArgumentException si no se encuentra el id en los cursos
     */
    
    Course getOneById(Long id);

    /**
     * 
     * @param partialName
     * @return
     * @throws
     */
    List<Course> getAllThatContainsName(String partialName);

    Course create(Course course);

    Course update(Long id, Course course);

    void delete(Long id);

}
