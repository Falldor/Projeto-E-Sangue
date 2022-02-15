package com.esangue.dto;

import java.io.Serializable;

import com.esangue.model.Paciente;
import com.esangue.util.TipoSanguineo;

public class PacienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	private Long idade;
	private TipoSanguineo tipoSanguineo;
	private String hospital;
	
	
	public PacienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PacienteDTO(Paciente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.idade = obj.getIdade();
		this.tipoSanguineo = obj.getTipoSanguineo();
		this.hospital = obj.getHospital();
	}


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

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}


	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	
}
