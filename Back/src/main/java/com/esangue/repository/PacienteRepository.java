package com.esangue.repository;

import com.esangue.model.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	@Query("SELECT obj FROM Paciente obj WHERE lower(obj.nome) like lower(concat('%', concat(:name, '%')))")
	List<Paciente> findAllByName(@Param(value = "name") String name);

}
