package br.com.viittor.springdata.model;

import javax.persistence.*;

// id
// url
// id_disciplina

@Entity
@Table(name = "material")
public class DisciplinaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_ger")
    @SequenceGenerator(name = "material_ger", sequenceName = "material_seq")
    private Long id;

    private String url;

    // FOREIGN KEY id_discplina references disciplina(id)
    @OneToOne
    @JoinColumn(name = "id_disciplina", referencedColumnName = "id")
    private Disciplina disciplina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}