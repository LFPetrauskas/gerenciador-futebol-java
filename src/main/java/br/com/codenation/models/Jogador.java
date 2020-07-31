package br.com.codenation.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Jogador {
    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;

    private Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
            BigDecimal salario) {
        this.id = id;
        this.setIdTime(idTime);
        this.setNome(nome);
        this.setDataNascimento(dataNascimento);
        this.setNivelHabilidade(nivelHabilidade);
        this.setSalario(salario);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public static class JogadorBuilder {
        private Long id;
        private Long idTime;
        private String nome;
        private LocalDate dataNascimento;
        private Integer nivelHabilidade;
        private BigDecimal salario;

        public JogadorBuilder(Long id) {
            this.id = id;
        }

        public JogadorBuilder withIdTime(Long idTime) {
            this.idTime = idTime;
            return this;
        }

        public JogadorBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public JogadorBuilder withDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public JogadorBuilder withNivelHabilidade(Integer nivelHabilidade) {
            this.nivelHabilidade = nivelHabilidade;
            return this;
        }

        public JogadorBuilder withSalario(BigDecimal salario) {
            this.salario = salario;
            return this;
        }

        public Jogador build() {
            return new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Jogador jogador = (Jogador) o;
            return Objects.equals(id, jogador.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }
}