package br.com.viittor.springdata.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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

    // lazy - preguiçoso
    // eager - ansioso
    // OneToMany e ManyToMany é lazy
    @OneToMany(mappedBy = "professor")
    //@JoinColumn(name = "id_professor", referencedColumnName = "id") //select * from professor p join disciplina d on p.id = d.id_professor;
    private List<Disciplina> disciplinas;

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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                //", disciplinas=" + disciplinas +
                '}';
    }
}