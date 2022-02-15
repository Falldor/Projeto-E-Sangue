package com.esangue.model;

import java.io.Serializable;
import java.util.Objects;

import com.esangue.util.TipoSanguineo;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Doador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank (message = "Campo NOME eh requerido.")
	private String nome;

    @CPF(message = "Campo INVALIDO.")
    @NotBlank (message = "Campo CPF eh requerido.")
    private String cpf;

	@Min (value = 16, message = "Idade não pode ser menor que 16.")
	@Max (value = 69, message = "Idade não pode ser maior que 69.")
    @NotNull (message = "Campo IDADE eh requerido.")
	private Long idade;

	@NotBlank (message = "Campo ENDEREÇO eh requerido.")
	private String endereco;

	@NotBlank (message = "Campo TELEFONE eh requerido.")
	private String telefone;

    @NotNull
	private TipoSanguineo tipoSanguineo;

    @JsonFormat(pattern = "dd-MM-yyyy")
	private String ultimaDoacao;

	public Doador() {
        super();
    }

    public Doador(Long id, String nome, String cpf, Long idade, String endereco, String telefone, TipoSanguineo tipoSanguineo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipoSanguineo = tipoSanguineo;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getUltimaDoacao() {
        return ultimaDoacao;
    }

    public void setUltimaDoacao(String ultimaDoacao) {
        this.ultimaDoacao = ultimaDoacao;
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
		Doador other = (Doador) obj;
		return Objects.equals(id, other.id);
	}

    
}
