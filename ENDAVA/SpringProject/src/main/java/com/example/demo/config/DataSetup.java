package com.example.demo.config;

import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${insert.start.data}")
public class DataSetup implements ApplicationRunner {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Value("${insert.start.data}")
    private Boolean insertData;

    @Autowired
    public DataSetup(SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(insertData) {
            saveSubject("OOP", 5, false, 60, 40);
            saveSubject("Math", 6, false, 50, 50);
            saveSubject("Chemistry", 4, true, 45, 55);
            saveSubject("Biology", 4, true, 60, 40);
            saveSubject("Data Structure", 5, false, 50, 50);
            saveSubject("Sport", 2, true, 90, 10);

            addTeacher("FirstName1", "LastName1", "12312312", 123123L);
            addTeacher("FirstName1", "LastName2", "33312312", 13123L);
            addTeacher("FirstName3", "LastName1", "44412312", 23123L);
            addTeacher("FirstName4", "LastName4", "55512312", 33123L);
            addTeacher("FirstName5", "LastName5", "66612312", 43123L);
        }

    }

    private void saveSubject(String name, Integer creditPoints,Boolean isOptional, Integer coursePercent, Integer seminaryPercent){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setSeminaryPercent(seminaryPercent);
        subject.setCoursePercent(coursePercent);
        subject.setOptional(isOptional);
        subject.setCreditPoints(creditPoints);

        subjectRepository.save(subject);
    }

    private void addTeacher(String firstName, String lastName, String cnp, Long salary){
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setCnp(cnp);
        teacher.setSalary(salary);

        teacherRepository.save(teacher);
    }
}
