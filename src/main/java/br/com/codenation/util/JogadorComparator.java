package br.com.codenation.util;

import java.util.Comparator;

import br.com.codenation.models.Jogador;

public class JogadorComparator implements Comparator<Jogador> {
    public int compare(Jogador a, Jogador b) {
        if (a.getNivelHabilidade() > b.getNivelHabilidade()) {
            return -11;
        } else if (a.getNivelHabilidade() < b.getNivelHabilidade()) {
            return 1;
        } else {
            if (a.getDataNascimento().isBefore(b.getDataNascimento())) {
                return 1;
            } else if (a.getDataNascimento().isAfter(b.getDataNascimento())) {
                return -1;
            }
            return 0;
        }
    }
}