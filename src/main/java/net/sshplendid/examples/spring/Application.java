package net.sshplendid.examples.spring;

import net.sshplendid.examples.spring.students.entity.Student;
import net.sshplendid.examples.spring.students.entity.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static net.sshplendid.examples.spring.students.entity.Student.Gender.FEMALE;
import static net.sshplendid.examples.spring.students.entity.Student.Gender.MALE;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


//    @Bean
    public CommandLineRunner test(StudentRepository repo) {
        return (args) -> {
            repo.save(new Student("John", "Doe", 12, MALE));
            repo.save(new Student("Kate", "Perry", 22, FEMALE));
            repo.save(new Student("Lady", "Gaga", 15, FEMALE));
            repo.save(new Student("Wenth", "Miller", 24, MALE));
            repo.save(new Student("Kane", "Doe", 21, MALE));

            log.info("-- findByLastName():");
            repo.findByLastName("Doe").forEach(s -> log.info(s.toString()));
            log.info("");

            log.info("-- findById(3L):");
            repo.findById(3L).ifPresent(s -> log.info(s.toString()));
        };
    }
}
