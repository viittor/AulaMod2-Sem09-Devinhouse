package br.com.viittor.springdata.service;

import br.com.viittor.springdata.model.Professor;
import br.com.viittor.springdata.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ProfessorService {

    private boolean system = true;

    @Autowired
    private ProfessorRepository professorRepository;

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual ação em professor deseja executar");
            System.out.println("0 - Voltar");
            System.out.println("1 - Salvar");
            System.out.println("2 - Visualizar");

            int action = scanner.nextInt();

            switch (action) {
                case 1 :
                    salvar(scanner);
                    break;
                case 2:
                    visualizar();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void visualizar() {
        Iterable<Professor> professores = professorRepository.buscarProfessorComDisciplina();
        professores.forEach(System.out::println);
    }

    private void salvar(Scanner scanner) {
        System.out.println("Informe o nome");
        String nome = scanner.next();

        System.out.println("Informe o sobrenome");
        String sobrenome = scanner.next();

        Professor prof = new Professor();
        prof.setNome(nome);
        prof.setSobreNome(sobrenome);

        professorRepository.save(prof);
    }

}