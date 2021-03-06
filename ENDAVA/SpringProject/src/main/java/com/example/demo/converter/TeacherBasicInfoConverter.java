package com.example.demo.converter;

import com.example.demo.dto.TeacherBasicInfoDto;
import com.example.demo.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherBasicInfoConverter {

    private final SubjectConverter subjectConverter;

    @Autowired
    public TeacherBasicInfoConverter(SubjectConverter subjectConverter) {
        this.subjectConverter = subjectConverter;
    }

    public List<TeacherBasicInfoDto> mapToDtos(List<Teacher> teachers)   {
        return teachers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TeacherBasicInfoDto mapToDto(Teacher teacher)   {
        TeacherBasicInfoDto dto = new TeacherBasicInfoDto();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setSubjects(subjectConverter.maptoDtos(teacher.getSubjects()));

        return dto;
    }
}
