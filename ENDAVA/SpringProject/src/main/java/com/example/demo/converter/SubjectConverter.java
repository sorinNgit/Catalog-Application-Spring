package com.example.demo.converter;

import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.SubjectScoringDto;
import com.example.demo.model.Subject;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubjectConverter {

    public SubjectDto maptoDto(Subject subject){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setOptional(subject.getOptional());
        subjectDto.setCreditPoints(subject.getCreditPoints());
        subjectDto.setSsdto(new SubjectScoringDto(subject.getCoursePercent(), subject.getSeminaryPercent()));

        return subjectDto;
    }

    public List<SubjectDto> maptoDtos(List<Subject> subjects){
        return subjects.stream()
                .map(this::maptoDto)
                .collect(Collectors.toList());
    }

    public Subject maptoEntity(SubjectDto subjectDto){
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setName(subjectDto.getName());
        subject.setOptional(subjectDto.getOptional());
        subject.setCreditPoints(subjectDto.getCreditPoints());
        subject.setCoursePercent(subjectDto.getSsdto().getCoursePercent());
        subject.setSeminaryPercent(subjectDto.getSsdto().getSeminaryPercent());

        return subject;
    }

    public List<Subject> mapToEntities(List<SubjectDto> subjectDtos){
        return subjectDtos.stream().map(this::maptoEntity).collect(Collectors.toList());
    }
}
