package br.com.viittor.springdata;

import br.com.viittor.springdata.service.DisciplinaService;
import br.com.viittor.springdata.service.EstudanteService;
import br.com.viittor.springdata.service.ProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final EstudanteService estudanteService;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;
    private boolean system = true;

    public SpringDataApplication(EstudanteService estudanteService,
                                 ProfessorService professorService,
                                 DisciplinaService disciplinaService) {
        this.estudanteService = estudanteService;
        this.professorService = professorService;
        this.disciplinaService = disciplinaService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual a função que você deseja acessar");
            System.out.println("0 - Sair");
            System.out.println("1 - Estudante");
            System.out.println("2 - Professor");
            System.out.println("3 - Disciplina");

            Integer function = scanner.nextInt();

            switch (function) {
                case 1:
                    estudanteService.inicial(scanner);
                    break;
                case 2:
                    professorService.inicial(scanner);
                    break;
                case 3:
                    disciplinaService.inicial(scanner);
                default:
                    System.out.println("Finalizando");
                    system = false;
                    break;
            }
        }
    }
}

// flyway e liquibase