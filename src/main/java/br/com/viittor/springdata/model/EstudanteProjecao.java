package br.com.viittor.springdata.model;

import org.springframework.beans.factory.annotation.Value;

public interface EstudanteProjecao {

    String getNome();

    String getUltimoNome();

    @Value("#{target.nome + ' ' + target.ultimoNome}")
    String getNomeCompleto();
}