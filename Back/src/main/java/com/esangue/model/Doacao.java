package com.esangue.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Doacao implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne
    private Doador doador;

    @ManyToOne
    private Paciente paciente;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String data;

    public Doacao() {
        super();
    }

    public Doacao(Doador doador, Paciente paciente, String data) {
        super();
        this.doador = doador;
        this.paciente = paciente;
        this.data = data;
    }

    public Doacao(long id, Doador doador, Paciente paciente, String data) {
        super();
        this.id = id;
        this.doador = doador;
        this.paciente = paciente;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
		Doacao other = (Doacao) obj;
		return Objects.equals(id, other.id);
	}
    
}
