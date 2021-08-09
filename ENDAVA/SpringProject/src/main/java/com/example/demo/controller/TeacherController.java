package com.example.demo.controller;

import com.example.demo.converter.TeacherBasicInfoConverter;
import com.example.demo.converter.TeacherConverter;
import com.example.demo.dto.TeacherBasicInfoDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/*
    @RestController annotation means that this class will be a Bean (managed by spring) and will contain endpoints
    (an endpoint is a method that handles HTTP requests end returns a HTTP response)
    @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.
 */

//@Controller
//@ResponseBody
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    /*
        the controllers usually shouldn't have much logic in them
        they should call services to retrieve needed data, perform validations
        most of the logic should be in the services
     */

//    @Value("${test.value}")
//    private String value;

    private final TeacherService teacherService;
    private final TeacherConverter teacherConverter;
    private final TeacherBasicInfoConverter basicInfoConverter;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherConverter teacherConverter, TeacherBasicInfoConverter basicInfoConverter) {
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
        this.basicInfoConverter = basicInfoConverter;
    }

    @GetMapping("/")
    public List<TeacherDto> getAllTeachers()   {
        List<Teacher> teachers = teacherService.getAllTeachers();

        return teacherConverter.maptoDtos(teachers);
    }

    @GetMapping("/basic")
    public List<TeacherBasicInfoDto> getAllBasicTeachers()  {
        List<Teacher> teachers = teacherService.getAllTeachers();

        return basicInfoConverter.mapToDtos(teachers);
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable Long id)  {
        Teacher teacher = teacherService.getTeacher(id);

        return teacherConverter.maptoDto(teacher);
    }

    @GetMapping("/page")
    public List<TeacherBasicInfoDto> getAllBasicTeachersPages(@RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumber,
                                                              @RequestParam(name = "size", required = false, defaultValue = "5") Integer size){
        List<Teacher> teachers = teacherService.getAllPagesTeachers(pageNumber, size);

        return basicInfoConverter.mapToDtos(teachers);
    }

    @GetMapping("/basic/{id}")
    public TeacherBasicInfoDto getBasicTeacher(@PathVariable Long id)    {
        Teacher teacher = teacherService.getTeacher(id);

        return basicInfoConverter.mapToDto(teacher);
    }

    @GetMapping("/filter")
    //public List<TeacherDto>  getFilteredTeachers(@RequestParam(name = "cnp", required = false, defaultValue = "33312312") String cnp) {
    public List<TeacherDto>  getFilteredTeachers(@RequestParam(name = "cnp") String cnp,
                                                 @RequestParam(name = "firstName") String firstName,
                                                 @RequestParam(name = "lastName") String lastName) {


        List<Teacher> teachers;

        if(cnp != null){
            teachers = Arrays.asList(teacherService.getTeacherByCnp(cnp));
        } else if(firstName != null && lastName != null){
            teachers = teacherService.getAllTeacherByFirstAndLastName(firstName,lastName);
        } else if (firstName != null){
            teachers = teacherService.getAllTeachersByFirstName(firstName);
        } else if (lastName != null){
            teachers = teacherService.getAllTeachersByLastName(lastName);
        } else {
            teachers = teacherService.getAllTeachers();
        }
        return teacherConverter.maptoDtos(teachers);
    }


    @PostMapping("/")
    public TeacherDto saveTeacher(@Valid @RequestBody TeacherDto teacherDto){
        Teacher teacher = teacherConverter.maptoEntity(teacherDto);
        List<Subject> subjectList = teacher.getSubjects();
        subjectList.forEach(s -> s.setId(null));
        teacher = teacherService.saveTeacher(teacher);

        return teacherConverter.maptoDto(teacher);
    }


    @DeleteMapping(value = "{id}")
    public void deleteTeacher(@PathVariable(name = "id") Long id){
        teacherService.deleteTeacher(id);
    }

    @PutMapping(value = "{id}")
    public TeacherDto updateTeacher(@Valid @RequestBody TeacherDto teacherDto, @PathVariable Long id){
        Teacher teacher = teacherConverter.maptoEntity(teacherDto);
        teacher = teacherService.updateTeacher(id,teacher);

        return teacherConverter.maptoDto(teacher);
    }


    @PutMapping("/{teacherId}/subject/{subjectId}")
    public TeacherDto assignSubjectToTeacher(@PathVariable(name = "teacherId") Long teacherId,
                                             @PathVariable(name = "subjectId") Long subjectId){
        Teacher teacher = teacherService.assignSubjectToTeacher(teacherId,subjectId);
        return teacherConverter.maptoDto(teacher);

    }



//    @GetMapping("/")
//    public TeacherDto getDemoTeacher() {
//        return teacherService.getDemoTeacher();
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/hello")
//    public String hello() {
//        return "Greetings from my first Spring Boot application! :)";
//    }
//
//    @GetMapping("/bye")
//    public String bye() {
//        return value;
//    }
}