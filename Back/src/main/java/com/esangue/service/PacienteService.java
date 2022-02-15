package com.esangue.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esangue.dto.PacienteDTO;
import com.esangue.model.Paciente;
import com.esangue.repository.PacienteRepository;
import com.esangue.service.exceptions.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;
	
	public List<Paciente> findByName(String name) {
		return repository.findAllByName(name);
	}

	public Paciente findById(Long id) {
		Optional<Paciente> paciente = repository.findById(id);
		return paciente.orElseThrow(() -> new ObjectNotFoundException("Paciente não encontrado! Id: " + id 
				+ ", Tipo: " + Paciente.class.getName()));
	}

	public List<PacienteDTO> findAll() {
		List<Paciente> pacientes = repository.findAll();
		List<PacienteDTO> pacientesDTO = pacientes.stream().map(obj -> new PacienteDTO(obj)).collect(Collectors.toList());
		return pacientesDTO;
	}

	public long findQuantity(String bloodType) {
		List<Paciente> pacientes = repository.findAll();

		long resultado = 0;
		for (Paciente paciente : pacientes) {
			if (paciente.getTipoSanguineo().getDescricao().equalsIgnoreCase(bloodType)) {
				resultado ++;
			}
		}

		if (resultado == 0) {
			throw new IllegalArgumentException("Nao ha pacientes cadastrados com o tipo sanguineo informado");
		}
		
		return resultado;
    }

	public Paciente update(Long id, Paciente patient) {
		Paciente newObj = findById(id);
		updateData(newObj, patient);
		return repository.save(newObj);
	}
	
	private void updateData(Paciente newObj, Paciente paciente) {
		newObj.setNome(paciente.getNome());
		newObj.setIdade(paciente.getIdade());
		newObj.setTipoSanguineo(paciente.getTipoSanguineo());
		newObj.setHospital(paciente.getHospital());
	}
	
	public Paciente create(Paciente patient) {
		patient.setId(null);
		return repository.save(patient);
	}

	public void delete(Long id) {
		Paciente paciente = findById(id);
		repository.delete(paciente);
	}

}
