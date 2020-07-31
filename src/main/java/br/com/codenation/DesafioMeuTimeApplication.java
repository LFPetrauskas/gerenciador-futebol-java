package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Jogador.JogadorBuilder;
import br.com.codenation.models.Time;
import br.com.codenation.models.Time.TimeBuilder;
import br.com.codenation.util.JogadorComparator;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
  private List<Time> times = new ArrayList<>();
  private List<Jogador> jogadores = new ArrayList<>();

  public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
      String corUniformeSecundario) {

    if (buscarTimes().contains(id)) {
      throw new IdentificadorUtilizadoException();
    }

    Time time = new TimeBuilder(id).withNome(nome).withDataCriacao(dataCriacao)
        .withCorUniformePrincipal(corUniformePrincipal).withCorUniformeSecundario(corUniformeSecundario).build();

    times.add(time);
  }

  public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
      BigDecimal salario) {

    if (!this.buscarTimes().contains(idTime)) {
      throw new TimeNaoEncontradoException();
    }

    if (this.buscarJogadoresDoTime(idTime).contains(id)) {
      throw new IdentificadorUtilizadoException();
    }

    Jogador jogador = new JogadorBuilder(id).withIdTime(idTime).withNome(nome).withDataNascimento(dataNascimento)
        .withNivelHabilidade(nivelHabilidade).withSalario(salario).build();

    this.jogadores.add(jogador);
  }

  public void definirCapitao(Long idJogador) {

    List<Jogador> jogador = this.jogadores.stream().filter(j -> j.getId().equals(idJogador))
        .collect(Collectors.toList());

    if (jogador.isEmpty()) {
      throw new JogadorNaoEncontradoException();
    }

    Long idTime = jogador.get(0).getIdTime();

    for (Time t : this.times) {
      if (t.getId().equals(idTime)) {
        t.setIdCapitao(idJogador);
      }
    }
  }

  public Long buscarCapitaoDoTime(Long idTime) {

    List<Time> time = this.times.stream().filter(t -> t.getId().equals(idTime)).collect(Collectors.toList());

    if (time.isEmpty()) {
      throw new TimeNaoEncontradoException();
    }

    if (time.get(0).getIdCapitao() == null) {
      throw new CapitaoNaoInformadoException();
    }

    return time.get(0).getIdCapitao();
  }

  public String buscarNomeJogador(Long idJogador) {

    List<Jogador> jogador = this.jogadores.stream().filter(j -> j.getId().equals(idJogador))
        .collect(Collectors.toList());

    if (jogador.isEmpty()) {
      throw new JogadorNaoEncontradoException();
    }

    return jogador.get(0).getNome();
  }

  public String buscarNomeTime(Long idTime) {

    List<Time> time = this.times.stream().filter(t -> t.getId().equals(idTime)).collect(Collectors.toList());

    if (time.isEmpty()) {
      throw new TimeNaoEncontradoException();
    }

    return time.get(0).getNome();
  }

  public List<Long> buscarJogadoresDoTime(Long idTime) {

    if (!this.buscarTimes().contains(idTime)) {
      throw new TimeNaoEncontradoException();
    }

    List<Jogador> listaJogadores = this.jogadores.stream().filter(j -> j.getIdTime().equals(idTime))
        .collect(Collectors.toList());

    List<Long> idsJogadores = new ArrayList<>();

    for (Jogador j : listaJogadores) {
      idsJogadores.add(j.getId());
    }

    return idsJogadores;
  }

  public Long buscarMelhorJogadorDoTime(Long idTime) {

    Integer maiorHabilidade = 0;

    Long idJogador = 0l;

    List<Jogador> jogadoresTime = this.jogadores.stream().filter(j -> j.getIdTime().equals(idTime))
        .collect(Collectors.toList());

    if (jogadoresTime.isEmpty()) {
      throw new TimeNaoEncontradoException();
    }

    for (Jogador j : jogadoresTime) {
      if (j.getNivelHabilidade() > maiorHabilidade) {
        maiorHabilidade = j.getNivelHabilidade();
        idJogador = j.getId();
      }
    }

    return idJogador;
  }

  public Long buscarJogadorMaisVelho(Long idTime) {

    List<Jogador> jogadoresTime = this.jogadores.stream().filter(j -> j.getIdTime().equals(idTime))
        .collect(Collectors.toList());

    if (jogadoresTime.isEmpty()) {
      throw new TimeNaoEncontradoException();
    }

    Long idJogadorMaisVelho = jogadoresTime.get(0).getId();

    LocalDate menorData = jogadoresTime.get(0).getDataNascimento();

    for (Jogador j : jogadoresTime) {
      if (j.getDataNascimento().isBefore(menorData)) {
        menorData = j.getDataNascimento();
        idJogadorMaisVelho = j.getId();
      }
    }

    return idJogadorMaisVelho;
  }

  public List<Long> buscarTimes() {

    List<Long> ids = new ArrayList<>();

    for (Time time : this.times) {
      ids.add(time.getId());
    }

    return ids;
  }

  public Long buscarJogadorMaiorSalario(Long idTime) {

    BigDecimal maior = new BigDecimal(0);

    Long idMaior = 0l;

    List<Long> idsTime = buscarJogadoresDoTime(idTime);

    for (Long i : idsTime) {
      BigDecimal salario = buscarSalarioDoJogador(i);
      if (salario.compareTo(maior) > 0) {
        maior = salario;
        idMaior = i;
      }
    }

    return idMaior;
  }

  public BigDecimal buscarSalarioDoJogador(Long idJogador) {

    List<Jogador> jogador = this.jogadores.stream().filter(j -> j.getId().equals(idJogador))
        .collect(Collectors.toList());

    if (jogador.isEmpty()) {
      throw new JogadorNaoEncontradoException();
    }

    return jogador.get(0).getSalario();
  }

  public List<Long> buscarTopJogadores(Integer top) {

    if (this.jogadores.isEmpty()) {
      return new ArrayList<>();
    }

    List<Jogador> clone = new ArrayList<>(this.jogadores);

    Collections.sort(clone, new JogadorComparator());
    for(Jogador j: clone) {
      System.out.println(j);
    }

    List<Long> ids = new ArrayList<>();

    for (Integer i = 0; i < top; i++) {
      if (i == clone.size()) {
        break;
      }
      ids.add(clone.get(i).getId());
    }

    return ids;
  }

}