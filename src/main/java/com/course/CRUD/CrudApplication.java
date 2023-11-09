package com.course.CRUD;

import com.course.CRUD.models.Cohort;
import com.course.CRUD.models.Modules;
import com.course.CRUD.repositories.*;
import com.course.CRUD.models.subModels.Admin;
import com.course.CRUD.models.subModels.Mentor;
import com.course.CRUD.models.subModels.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CrudApplication {

private final List<String> webTech = List.of("HTML","CSS3","FLEXBOX","AGILE");
private final List<String> jsTech = List.of("JAVASCRIPT","VUE.JS","QUERY SELECTOR","AJAX","JSON","APIs","BOOSTRAP");
private final List<String> javaTech = List.of("JAVA","SPRING","SPRING SECURITY","SPRING DATA JPA","TEST","POSTGRESQL");
private final List<String> boostTech = List.of("Know yourself","Practices Interviews","Elevetor Pitch CV ","Linkedin","Job search");
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(StudentRepository studentRepository,
									  MentorRepository mentorRepository,
									  AdminRepository adminRepository,
									  ModulesRepository modulesRepository,
									  CohortRepository cohortRepository,
									  PasswordEncoder passwordEncoder) {
		return (args) -> {
			Student student = new Student("STU-0001-BR", "Bruno Marcos", "Ferreira", "bruno@gmail.com", passwordEncoder.encode("password"), 20,true);
			studentRepository.save(student);

			Mentor webMentor = new Mentor("MNT-0002-CM", "Camila ", "Domato", "camila@gmail.com", passwordEncoder.encode("password"), 20,true);

			Mentor jsMentor = new Mentor("MNT-0003-NC", "Nicolas ", "Cirulli", "nico@gmail.com", passwordEncoder.encode("password"), 20,true);

			Mentor javaMentor = new Mentor("MNT-0001-MT", "Martin ", "Araolaza", "martin@gmail.com", passwordEncoder.encode("password"), 20,true);

			Mentor boostMentor = new Mentor("MNT-0004-AG", "Antonella ", "Galeano", "antonella@gmail.com", passwordEncoder.encode("password"), 20,true);

			Admin admin = new Admin("ADM-0001-EM", "Elias", "Medina", "elias@gmail.com", passwordEncoder.encode("password"), 20,true);

			mentorRepository.save(webMentor);
			mentorRepository.save(jsMentor);
			mentorRepository.save(javaMentor);
			mentorRepository.save(boostMentor);
			adminRepository.save(admin);

			Modules webTechModule = new Modules("Web Tech", webTech, 80,true);
			Modules jsTechModule = new Modules("JS Tech", jsTech, 120,true);
			Modules javaTechModule = new Modules("Java Tech", javaTech, 460,	true);
			Modules boostTechModule = new Modules("Boost Tech", boostTech, 40,true);

			webMentor.addModules(webTechModule);
			jsMentor.addModules(jsTechModule);
			javaMentor.addModules(javaTechModule);
			boostMentor.addModules(boostTechModule);

			mentorRepository.save(webMentor);
			mentorRepository.save(jsMentor);
			mentorRepository.save(javaMentor);
			mentorRepository.save(boostMentor);

			modulesRepository.save(webTechModule);
			modulesRepository.save(jsTechModule);
			modulesRepository.save(javaTechModule);
			modulesRepository.save(boostTechModule);
			Cohort cohort = new Cohort(true, 20, LocalDate.now(), 1);
			cohort.setStudent(student);
			cohortRepository.save(cohort);
			student.addCohort(cohort);
			studentRepository.save(student);
	};

}
}
