package br.com.codenation.models;

import java.time.LocalDate;
import java.util.Objects;

public class Time {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long idCapitao;

    private Time() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return this.corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return this.corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public Long getIdCapitao() {
        return this.idCapitao;
    }

    public void setIdCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }

    public static class TimeBuilder {
        private Long id;
        private String nome;
        private LocalDate dataCriacao;
        private String corUniformePrincipal;
        private String corUniformeSecundario;
        private Long idCapitao;

        public TimeBuilder(Long id) {
            this.id = id;
        }

        public TimeBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public TimeBuilder withDataCriacao(LocalDate dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }

        public TimeBuilder withCorUniformePrincipal(String corUniformePrincipal) {
            this.corUniformePrincipal = corUniformePrincipal;
            return this;
        }

        public TimeBuilder withCorUniformeSecundario(String corUniformeSecundario) {
            this.corUniformeSecundario = corUniformeSecundario;
            return this;
        }

        public TimeBuilder withIdCapitao(Long idCapitao) {
            this.idCapitao = idCapitao;
            return this;
        }

        public Time build() {
            Time time = new Time();
            time.setId(this.id);
            time.setNome(this.nome);
            time.setDataCriacao(this.dataCriacao);
            time.setCorUniformePrincipal(this.corUniformePrincipal);
            time.setCorUniformeSecundario(this.corUniformeSecundario);
            time.setIdCapitao(idCapitao);
            return time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Time time = (Time) o;
            return Objects.equals(id, time.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        @Override
        public String toString() {
            return "Time { id=" + this.id 
                    + ", nome=" + this.nome 
                    + ", dataCriacao=" + this.dataCriacao
                    + ", corUniformePrincipal=" + this.corUniformePrincipal 
                    + ", corUniformeSecundario=" + this.corUniformeSecundario 
                    + ", idCapitao=" + this.idCapitao;
        }

    }

}