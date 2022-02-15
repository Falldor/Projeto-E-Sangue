package com.esangue.repository;

import com.esangue.model.Doador;
import com.esangue.util.TipoSanguineo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoadorRepository extends JpaRepository<Doador, Long>{
    
	@Query("select obj from Doador obj WHERE lower(obj.nome) like lower(concat('%', concat(:name, '%')))")
	List<Doador> findAllByName(@Param(value = "name") String name);

	List<Doador> findByCpf(String cpf);

	@Query("SELECT tipoSanguineo FROM Doador GROUP BY tipoSanguineo ORDER BY COUNT(tipoSanguineo)")
	List<TipoSanguineo> countBloodTypes();

	@Query("SELECT COUNT(tipoSanguineo) FROM Doador GROUP BY tipoSanguineo ORDER BY COUNT(tipoSanguineo)")
	List<Long> quantBloodTypes();

}