package com.esangue.repository;

import com.esangue.model.Doacao;

import org.springframework.data.jpa.repository.JpaRepository;
public interface DoacaoRepository extends JpaRepository<Doacao, Long>{

    Doacao findById(long id);

}