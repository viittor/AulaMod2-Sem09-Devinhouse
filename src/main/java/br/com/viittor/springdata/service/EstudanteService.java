package br.com.viittor.springdata.service;

import br.com.viittor.springdata.model.Endereco;
import br.com.viittor.springdata.model.Estudante;
import br.com.viittor.springdata.model.EstudanteProjecao;
import br.com.viittor.springdata.model.Genero;
import br.com.viittor.springdata.repository.EstudanteRepository;
import br.com.viittor.springdata.repository.SpecificationsEstudante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EstudanteService {

    private boolean system = true;
    private EstudanteRepository estudanteRepository;

    public EstudanteService(EstudanteRepository estudanteRepository) {
        this.estudanteRepository = estudanteRepository;
    }

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual ação do estudante deseja executar");
            System.out.println("0 - Voltar");
            System.out.println("1 - Salvar");
            System.out.println("2 - Visualizar");
            System.out.println("3 - Buscar por nome");
            System.out.println("4 - Buscar por idade");
            System.out.println("5 - Buscar por idade projeção");
            System.out.println("6 - Buscar estudantes com endereço");
            System.out.println("7 - Buscar estudantes por cidade");
            System.out.println("8 - Busca dinamica");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    visualizar(scanner);
                    break;
                case 3:
                    buscarPorNome(scanner);
                    break;
                case 4:
                    buscarPorIdade(scanner);
                    break;
                case 5:
                    buscarPorIdadeProjecao(scanner);
                    break;
                case 6:
                    buscarEstudantesComEndereco();
                    break;
                case 7:
                    buscarEstudantesPorCidade(scanner);
                    break;
                case 8:
                    buscaDinamica(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaDinamica(Scanner scanner) {
        System.out.println("Informe o nome");
        String nome = scanner.next();
        if(nome.equals("NULL")) {
            nome = null;
        }

        System.out.println("Infome a idade");
        Integer idade = scanner.nextInt();
        if(idade == 0) {
            idade = null;
        }

        List<Estudante> estudantes = estudanteRepository.findAll(Specification.where(
                SpecificationsEstudante.nome(nome)
                        .or(SpecificationsEstudante.idadeMaiorQue(idade))
        ));

        estudantes.forEach(System.out::println);
    }

    private void buscarEstudantesPorCidade(Scanner scanner) {
        System.out.println("Informe a cidade:");
        String cidade = scanner.next();
        List<Estudante> estudantes = estudanteRepository.findByEnderecoCidade(cidade);
        estudantes.forEach(System.out::println);
    }

    private void buscarEstudantesComEndereco() {
        List<Estudante> estudantes = estudanteRepository.findByEnderecoNotNull();
        estudantes.forEach(System.out::println);
    }

    private void buscarPorIdadeProjecao(Scanner scanner) {
        System.out.println("Informe a idade do aluno:");
        int idade = scanner.nextInt();
        List<EstudanteProjecao> estudantes = estudanteRepository.buscarPorIdadeProjecao(idade);
        estudantes.forEach(estudante -> {
            System.out.println("Nome: " + estudante.getNome() +
                    ", Sobrenome: " + estudante.getUltimoNome() +
                    ", Nome completo: " + estudante.getNomeCompleto());
        });
    }

    private void buscarPorIdade(Scanner scanner) {
        System.out.println("Informe a idade do aluno:");
        int idade = scanner.nextInt();
        List<Estudante> estudantes = estudanteRepository.findPorIdade(idade);
        estudantes.forEach(System.out::println);
    }

    private void buscarPorNome(Scanner scanner) {
        System.out.println("Informe o nome que deseja buscar");
        String nomeBuscar = scanner.next();
        Optional<Estudante> retornoBusca = estudanteRepository.findByNome(nomeBuscar);

        List<Genero> generos = new ArrayList<>();
        generos.add(Genero.NAO_BINARIO);
        generos.add(Genero.TRANS);

        estudanteRepository.findByGeneroIn(generos);

        System.out.println(retornoBusca.get());
    }

    private void salvar(Scanner scanner) {
        Estudante estudante = new Estudante();
        System.out.println("Informe o primeiro nome");
        estudante.setNome(scanner.next());

        System.out.println("Informe o último nome");
        estudante.setUltimoNome(scanner.next());

        System.out.println("Informe a sua idade");
        estudante.setIdade(scanner.nextInt());
        estudante.setDataNascimento(LocalDate.now());
        estudante.setGenero(Genero.MASCULINO);
        estudante.setAtivo(false);

        Endereco endereco = new Endereco();
        endereco.setCidade("Florianopolis");
        endereco.setNumero("51");
        endereco.setRua("Rua da Tecnologia");
        estudante.setEndereco(endereco);

        estudanteRepository.save(estudante);
    }

    private void visualizar(Scanner scanner) {
        System.out.println("Qual página deseja visualizar");

        int pagina = scanner.nextInt();

        PageRequest pageRequest = PageRequest.of(pagina, 2, Sort.by("nome"));
        Page<Estudante> resultado = estudanteRepository.findAll(pageRequest);
        System.out.println("Total de elementos: " + resultado.getTotalElements());
        System.out.println("Total de páginas: " + resultado.getTotalPages());
        resultado.get().forEach(System.out::println);

    }
}