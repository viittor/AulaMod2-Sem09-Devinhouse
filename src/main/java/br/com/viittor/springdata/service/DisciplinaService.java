package br.com.viittor.springdata.service;

import br.com.viittor.springdata.model.Disciplina;
import br.com.viittor.springdata.model.Estudante;
import br.com.viittor.springdata.model.Professor;
import br.com.viittor.springdata.repository.DisciplinaRepository;
import br.com.viittor.springdata.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class DisciplinaService {

    private boolean system = true;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual ação em disciplina deseja executar");
            System.out.println("0 - Voltar");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Visualizar");
            System.out.println("5 - Adicionar estudante a disciplina");

            int action = scanner.nextInt();

            switch (action) {
                case 1 :
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3 :
                    deletar(scanner);
                    break;
                case 4:
                    visualizar();
                    break;
                case 5:
                    adicionarEstudante(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void adicionarEstudante(Scanner scanner) {
        System.out.println("Informe o id da disciplina");

        Long idDisciplina = scanner.nextLong();

        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(idDisciplina);

        // early return
        if(disciplinaOptional.isEmpty()) {
            System.out.println("O id informado é inválido");
            return;
        }

        System.out.println("Informe o id do aluno");

        Long idAluno = scanner.nextLong();

        Optional<Estudante> estudanteOptional = estudanteRepository.findById(idAluno);

        // early return
        if(estudanteOptional.isEmpty()) {
            System.out.println("O id informado é inválido");
            return;
        }

        Disciplina disciplina = disciplinaOptional.get();
        disciplina.adicionarEstudante(estudanteOptional.get());

        disciplinaRepository.save(disciplina);
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Informe o id da disciplina que quer atualizar");

        Long id = scanner.nextLong();
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);

        // early return
        if(disciplinaOptional.isEmpty()) {
            System.out.println("O id informado é inválido");
            return;
        }

        System.out.println("Informe a descrição para atualizar");
        scanner.nextLine();
        String descricao = scanner.nextLine();
        Disciplina disciplina = disciplinaOptional.get();
        disciplina.setDescricao(descricao);

        disciplinaRepository.save(disciplina);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Informe o id da disciplina para deletar");
        Long id = scanner.nextLong();

        disciplinaRepository.deleteById(id);
    }

    private void visualizar() {
        Iterable<Disciplina> disciplinas = disciplinaRepository.findAll();
        disciplinas.forEach(System.out::println);
    }

    private void salvar(Scanner scanner) {
        System.out.println("Informe a descrição da disciplina");
        String descricao = scanner.next();

        System.out.println("Infome o Nome do professor");
        String nome = scanner.next();

        System.out.println("Informe o sobrenome do professor");
        String sobreNome = scanner.next();

        Disciplina disciplina = new Disciplina();
        disciplina.setDescricao(descricao);

        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setSobreNome(sobreNome);

        disciplina.setProfessor(professor);

        disciplinaRepository.save(disciplina);
    }

}