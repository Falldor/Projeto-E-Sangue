package com.esangue.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.esangue.dto.DoacaoDTO;
import com.esangue.model.Doacao;
import com.esangue.model.Doador;
import com.esangue.model.Paciente;
import com.esangue.repository.DoacaoRepository;
import com.esangue.repository.DoadorRepository;
import com.esangue.repository.PacienteRepository;
import com.esangue.service.exceptions.DataIntegrityViolationException;
import com.esangue.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoacaoService {

	@Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private DoadorRepository doadorRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Doacao findById(Long id) {
		Optional<Doacao> doacao = doacaoRepository.findById(id);
		return doacao.orElseThrow(() -> new ObjectNotFoundException("Doacao não encontrada! Id: " + id 
				+ ", Tipo: " + Doacao.class.getName()));
	}

	public List<DoacaoDTO> findAll() {
		List<Doacao> doacoes = doacaoRepository.findAll();
		List<DoacaoDTO> doacoesDTO = doacoes.stream().map(obj -> new DoacaoDTO(obj)).collect(Collectors.toList());
		return doacoesDTO;
	}

	public boolean verificaCompatibilidadeSaguinea(String a, String b) {
        boolean t = false;
        String doador = a.toUpperCase();
        String paciente = b.toUpperCase();

        if(doador.equals("O+") && paciente.equals("O+")){
            t = true;
        }
        else if(doador.equals("O-")){
            t = true;
        }
        else if(doador.equals("A+") && (paciente.equals("A+") || paciente.equals("AB+"))){
            t = true;
        }
        else if(doador.equals("A-") && (paciente.equals("A+") || paciente.equals("AB+") || (paciente.equals("A-") || (paciente.equals("AB-"))))){
            t = true;
        }
        else if(doador.equals("B+") && (paciente.equals("AB+") || paciente.equals("B+"))){
            t = true;
        }
        else if(doador.equals("B-") && (paciente.equals("AB+") || paciente.equals("AB-") || paciente.equals("B+") || (paciente.equals("B-")))){
            t = true;
        }
        else if(doador.equals("AB+") && (paciente.equals("AB+"))){
            t = true;
        }
        else if(doador.equals("AB-") && (paciente.equals("AB+") || paciente.equals("AB-"))){
            t = true;
        }
        return t;
    }

    public boolean verificaTempoEntreDoacoes(String dataum, String datadois) throws ParseException {
        boolean t = false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date ultimaDoacao = sdf.parse(dataum);
        Date dataAtual = sdf.parse(datadois);

        long diff = dataAtual.getTime() - ultimaDoacao.getTime();
        
        TimeUnit time = TimeUnit.DAYS;
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
        
        if (difference > 90) {
            t = true;
        }

        return t;

    }

    public Doacao makeADonate(Long idd, Long idp, Doacao doacao) throws ParseException {

        // Verifica se o doador existe no sistema

        Optional<Doador> optionalDoador = doadorRepository.findById(idd);
        if(!optionalDoador.isPresent()) {
			throw new ObjectNotFoundException("Doador com id " + idd + " nao encontrado");
		}

        // Verifica se o paciente existe no sistema

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(idp);
        if(!optionalPaciente.isPresent()) {
            throw new ObjectNotFoundException("Paciente com id " + idp + " nao encontrado");
		}

        Doador doador = optionalDoador.get();
        Paciente paciente = optionalPaciente.get();

        // Verifica se o doador nunca fez doacao e se o intervalo entre a ultima doacao e a doacao atual eh maior que 90 dias

        if (doador.getUltimaDoacao() == null) {
            doador.setUltimaDoacao(doacao.getData());
        }
        else if (!verificaTempoEntreDoacoes(doador.getUltimaDoacao(), doacao.getData())) {
            throw new DataIntegrityViolationException("O intervalo entre a ultima doacao eh menor que 90 dias e nao permite novas doacoes");
        }

        // Verifica se o tipo sanguineo do doador eh compativel com o paciente

        if (! verificaCompatibilidadeSaguinea(doador.getTipoSanguineo().getDescricao(), paciente.getTipoSanguineo().getDescricao())) {
			throw new DataIntegrityViolationException("O doador com o tipo sanguineo " + doador.getTipoSanguineo().getDescricao() + " nao eh compativel com o paciente de tipo sanguineo " + paciente.getTipoSanguineo().getDescricao());
		}

        doacao.setDoador(doador);
        doacao.setPaciente(paciente);
        doacao.setData(doacao.getData());
        doador.setUltimaDoacao(doacao.getData());
        
        return this.doacaoRepository.save(doacao);
    }

    public Map<String, Long> getAllBloodTypesWithDonationsAndQuantity() {

		List<Doacao> listaTeste = doacaoRepository.findAll();
        List<String> listaFinal = new ArrayList<>();

        for (Doacao doacao : listaTeste) {
            listaFinal.add(doacao.getDoador().getTipoSanguineo().getDescricao());
        }

        Map<String, Long> frequencyMap = new HashMap<>();
        for (String s : listaFinal) {
            frequencyMap.merge(s, 1L, Long::sum);
        }
        return frequencyMap;

	}


}