package net.sshplendid.examples.spring.students.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByLastName(String lastName);
}
