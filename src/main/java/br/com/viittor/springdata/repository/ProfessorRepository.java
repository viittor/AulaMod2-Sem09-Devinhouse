package br.com.viittor.springdata.repository;


import br.com.viittor.springdata.model.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

    @Query("SELECT p FROM Professor p left join fetch p.disciplinas")
        //@Query(value = "select * from professor p left join disciplina d on p.id = d.id_professor", nativeQuery = true)
    List<Professor> buscarProfessorComDisciplina();
}