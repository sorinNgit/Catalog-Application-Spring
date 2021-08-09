package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private Logger logger = LoggerFactory.getLogger(SubjectService.class);

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(Long id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);

        if(optionalSubject.isPresent()){
            return optionalSubject.get();
        }
        else{
            //logger.info("Subject not found!");
            throw new RuntimeException("Subject not found!");
        }


        //return optionalSubject.orElseThrow(() -> throw new NotFoundException("Subject not found!", "subject.not.found"));
    }

    public Subject saveSubject(Subject subject){
        if(subject.getCoursePercent() + subject.getSeminaryPercent() != 100){
            throw new BadRequestException("Subject scoring percent bust me 100%","subject.scoring.is.invalid");
        }

        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id){
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isPresent()){
            subjectRepository.delete(subject.get());
        }else {
            throw new RuntimeException("Subject not found!");
        }
    }

    public Subject updateSubject(Long id, Subject subjectUpdated){
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if(subjectOptional.isPresent()){
            subjectUpdated.setId(id);
            return subjectRepository.save(subjectUpdated);
        }else{
            throw new RuntimeException("Subject not found!");
        }
    }

    public List<Subject> findAllByCreditPoints(Integer points) {
        return subjectRepository.findByCreditPoints(points);
    }

    public Long getNumberOfSubjectsWithCreditPointsBetween(Integer creditStart, Integer creditEnd) {
        return subjectRepository.countByCreditPointsBetween(creditStart, creditEnd);
    }
}
