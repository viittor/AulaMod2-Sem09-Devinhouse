package br.com.viittor.springdata.repository;

import br.com.viittor.springdata.model.Disciplina;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaExemplo {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public JpaExemplo() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void salvar(Disciplina disciplina) {
        entityManager.getTransaction().begin();
        entityManager.persist(disciplina);
        entityManager.getTransaction().commit();
        entityManager.flush();
        entityManager.close();
    }
}