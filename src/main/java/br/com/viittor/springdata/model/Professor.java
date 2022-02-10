package br.com.viittor.springdata.model;

import javax.persistence.*;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_ger")
    @SequenceGenerator(name = "professor_ger", sequenceName = "prof_seq")
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name = "last_name")
    private String sobreNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
}