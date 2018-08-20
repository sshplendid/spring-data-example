package net.sshplendid.examples.spring.students.controller;

import net.sshplendid.examples.spring.students.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.sshplendid.examples.spring.students.entity.Student.Gender.FEMALE;
import static net.sshplendid.examples.spring.students.entity.Student.Gender.MALE;

@RestController
public class StudentsController {
    private final List<Student> students = initStudents();

    private List<Student> initStudents() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("John", "Doe", 15, MALE));
        students.add(new Student("Jane", "Doe", 16, FEMALE));
        students.add(new Student("Jameson", "Doe", 20, MALE));

        return students;
    }

    @GetMapping("/students")
    public List<Student> getStudents(String namelike, Long age) {
        return students.stream()
                       .filter(s -> ( (!Optional.ofNullable(age).isPresent()) || s.getAge() > Optional.ofNullable(age).get())
                        && ( (!Optional.ofNullable(namelike).isPresent()) || s.getLastName().contains(Optional.ofNullable(namelike).get()))  )
                       .collect(Collectors.toList());
    }

    @PostMapping("/students")
    public Student postStudent(Student student) {
        students.add(student);

        return student;
    }

    @PatchMapping("/student/{id}")
    public Student patchStudent(String id, Student student) {
        students.stream()
                .filter( s -> s.getId() == Integer.parseInt(id) )
                .forEach( s -> {
                    s.setAge(student.getAge());
                    s.setGender(student.getGender());
                    s.setLastName(student.getLastName());
                } );

        return student;
    }
}
