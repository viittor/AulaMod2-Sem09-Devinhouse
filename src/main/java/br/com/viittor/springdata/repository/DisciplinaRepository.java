package br.com.viittor.springdata.repository;

import br.com.viittor.springdata.model.Disciplina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
}
