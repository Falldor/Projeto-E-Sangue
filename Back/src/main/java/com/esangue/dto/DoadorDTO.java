package com.esangue.dto;

import java.io.Serializable;

import com.esangue.model.Doador;
import com.esangue.util.TipoSanguineo;

public class DoadorDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cpf;
	private Long idade;
	private TipoSanguineo tipoSanguineo;

	public DoadorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoadorDTO(Doador obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.idade = obj.getIdade();
		this.tipoSanguineo = obj.getTipoSanguineo();
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
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
}
