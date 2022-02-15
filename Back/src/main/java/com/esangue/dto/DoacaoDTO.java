package com.esangue.dto;

import java.io.Serializable;

import com.esangue.model.Doacao;
import com.esangue.model.Doador;
import com.esangue.model.Paciente;

public class DoacaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDoacao;
    private Long idDoador;
    private Long idPaciente;
    private String data;

    public DoacaoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoacaoDTO(Doacao obj) {
		super();
		this.idDoacao = obj.getId();
		this.idDoador = obj.getDoador().getId();
		this.idPaciente = obj.getPaciente().getId();
        this.data = obj.getData();
	}

    public Long getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(Long idDoacao) {
        this.idDoacao = idDoacao;
    }

    public Long getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(Long idDoador) {
        this.idDoador = idDoador;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
