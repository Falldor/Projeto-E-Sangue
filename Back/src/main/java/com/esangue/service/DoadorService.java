package com.esangue.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esangue.dto.DoadorDTO;
import com.esangue.model.Doador;
import com.esangue.repository.DoadorRepository;
import com.esangue.service.exceptions.DataIntegrityViolationException;
import com.esangue.service.exceptions.ObjectNotFoundException;
import com.esangue.util.TipoSanguineo;

@Service
public class DoadorService {
	
	@Autowired
	private DoadorRepository repository;

	public List<Doador> findByName(String name) {
		return repository.findAllByName(name);
	}

	public Doador findById(Long id) {
		Optional<Doador> doador = repository.findById(id);
		return doador.orElseThrow(() -> new ObjectNotFoundException("Doador nao encontrado! Id: " + id 
				+ ", Tipo: " + Doador.class.getName()));
	}

	public List<DoadorDTO> findAll() {
		List<Doador> doadores = repository.findAll();
		List<DoadorDTO> doadoresDTO = doadores.stream().map(obj -> new DoadorDTO(obj)).collect(Collectors.toList());
		return doadoresDTO;
	}
	
    public boolean containsBloodType(String tipoSanguineo) {
        for (TipoSanguineo ts: TipoSanguineo.values()) {

            if (ts.getDescricao().equalsIgnoreCase(tipoSanguineo)) {
                return true;
            }

        }
        return false;
    }

	public long findQuantity(String bloodType) {
		if (!containsBloodType(bloodType)) {
			throw new ObjectNotFoundException("Tipo Sanguineo nao encontrado ou cadastrado!");
		}

		List<Doador> doadores = repository.findAll();

		long resultado = 0;
		for (Doador doador : doadores) {
			if (doador.getTipoSanguineo().getDescricao().equalsIgnoreCase(bloodType)) {
				resultado ++;
			}
		}

		if (resultado == 0) {
			throw new ObjectNotFoundException("Nao ha doadores cadastrados com o tipo sanguineo informado");
		}

		return resultado;
    }

	public Object findBloodTypeWithLessRegisteredDonators() {
		if (repository.countBloodTypes().isEmpty()) {
			throw new ObjectNotFoundException("Nao ha doadores cadastrados! ");
		}

		return repository.countBloodTypes().get(0).getDescricao();
	}

	public Map<TipoSanguineo, Long> getAllBloodTypesWithDonatorsAndQuantity() {

		Map<TipoSanguineo, Long> mapa = new HashMap<>();
		
		Iterator<TipoSanguineo> i1 = repository.countBloodTypes().iterator();
		Iterator<Long> i2 = repository.quantBloodTypes().iterator();	
		while (i1.hasNext() || i2.hasNext()) {
			mapa.put(i1.next(), i2.next());
		}

		return mapa;
	}

	public Doador create(Doador doador) {
		doador.setId(null);

		List<Doador> doadores = repository.findByCpf(doador.getCpf());
		if (!doadores.isEmpty()) {
			throw new DataIntegrityViolationException("O doador com CPF: " + doador.getCpf() + " ja esta cadastrado no sistema!");
		}

		return repository.save(doador);
	}

	public Doador update(Long id, Doador donator) {
		Doador newObj = findById(id);
		updateData(newObj, donator);
		return repository.save(newObj);
	}
	
	private void updateData(Doador newObj, Doador donator) {
		newObj.setNome(donator.getNome());
		newObj.setIdade(donator.getIdade());
		newObj.setTipoSanguineo(donator.getTipoSanguineo());
		newObj.setEndereco(donator.getEndereco());
		newObj.setTelefone(donator.getTelefone());
	}

	public void delete(Long id) {
		Doador donator = findById(id);
		repository.delete(donator);
	}

    public String teste() {
        String tesA = "tes";
		String tesB = "te";
		return tesA + tesB;
    }

}