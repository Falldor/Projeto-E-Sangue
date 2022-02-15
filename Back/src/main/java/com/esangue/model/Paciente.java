package com.esangue.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.esangue.util.TipoSanguineo;

@Entity
public class Paciente implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "Campo NOME eh requerido.")
    private String nome;

    @NotNull (message = "Campo IDADE eh requerido.")
    @Min (value = 0, message = "Idade não pode ser menor que 0.")
	@Max (value = 180, message = "Idade não pode ser maior que 100.")
    private Long idade;

    @NotNull
    private TipoSanguineo tipoSanguineo;
    
    @NotBlank (message = "Campo HOSPITAL eh requerido.")
    private String hospital;

    public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}

    public Paciente(Long id, String nome, Long idade, TipoSanguineo tipoSanguineo, String hospital) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tipoSanguineo = tipoSanguineo;
        this.hospital = hospital;
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

    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(id, other.id);
	}

}