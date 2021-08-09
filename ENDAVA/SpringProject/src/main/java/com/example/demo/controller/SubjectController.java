package com.example.demo.controller;

import com.example.demo.converter.SubjectConverter;
import com.example.demo.dto.SubjectDto;
import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectConverter subjectConverter;

    private Logger logger = LoggerFactory.getLogger(SubjectController.class);


    @Autowired
    public SubjectController(SubjectConverter subjectConverter,SubjectService subjectService) {
        this.subjectConverter = subjectConverter;
        this.subjectService = subjectService;
    }


    @GetMapping(value = "")
    public List<SubjectDto> getAllSubjects(){
        List<Subject> subjects = subjectService.getAllSubjects();
        return subjectConverter.maptoDtos(subjects);
    }

    @PostMapping("")
    public SubjectDto saveSubject(@Valid @RequestBody SubjectDto subjectDto){
        Subject subject = subjectConverter.maptoEntity(subjectDto);
        subject = subjectService.saveSubject(subject);

        logger.info("Saved new subject! {}",subject);
        return subjectConverter.maptoDto(subject);
    }


    @GetMapping(value = "/{id}")
    public Subject getSubject(@PathVariable("id") Long id) {
        return subjectService.getSubject(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        logger.info("Deleted subject with id {}",id);
        subjectService.deleteSubject(id);

    }

    @PutMapping("/{id}")
    public SubjectDto updateSubject(@PathVariable Long id,@RequestBody SubjectDto request){
        Subject subject = subjectConverter.maptoEntity(request);
        subject = subjectService.updateSubject(id, subject);
        logger.info("Updated subject! {}",subject);

        return subjectConverter.maptoDto(subject);
    }

    @GetMapping("/points/{credit}")
    public List<SubjectDto> getAllSubjectsByCreditPoints(@PathVariable Integer credit){
        List<Subject> subjects = subjectService.findAllByCreditPoints(credit);
        return subjectConverter.maptoDtos(subjects);
    }

    @GetMapping("/between/{creditStart}/{creditEnd}")
    public Long getNumberOfSubjectsWithCreditPointsBetween(@PathVariable Integer creditStart, @PathVariable Integer creditEnd){
        return subjectService.getNumberOfSubjectsWithCreditPointsBetween(creditStart,creditEnd);
    }

/*
    @GetMapping(value = "/demo")
    public Subject getParamSubject(@RequestParam("id") Long id) {
        return subjectService.getSubject(id);
    }
*/


}
