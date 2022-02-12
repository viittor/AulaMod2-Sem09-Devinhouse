package br.com.viittor.springdata.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "estudantes")
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudante_ger")
    @SequenceGenerator(name = "estudante_ger", sequenceName = "estudante_seq")
    private Long id;

    private String nome;

    @Column(name = "last_name")
    private String ultimoNome;

    private Integer idade;

//    @Temporal(TemporalType.DATE)
//    private Date dataNascimento;

    // date (somente data) - LocalDate
    // timestamp (data e hora) - LocalDateTime
    // time - (somente hora) - LocalTime
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Convert(converter = YesNoBooleanConverter.class)
    private Boolean ativo;

    @Transient // Informo pro JPA que este atributo não é persistido.
    private String observacao;

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

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.ultimoNome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", idade=" + idade +
                ", dataNascimento=" + dataNascimento +
                ", endereco=" + endereco +
                ", genero=" + genero +
                ", ativo=" + ativo +
                '}';
    }
}