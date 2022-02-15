package com.esangue.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.esangue.dto.DoacaoDTO;
import com.esangue.dto.DoadorDTO;
import com.esangue.dto.PacienteDTO;
import com.esangue.model.Doacao;
import com.esangue.model.Doador;
import com.esangue.model.Paciente;
import com.esangue.service.DoacaoService;
import com.esangue.service.DoadorService;
import com.esangue.service.PacienteService;
import com.esangue.util.TipoSanguineo;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/esangue")
public class ESangueController { 

	@Autowired
	private PacienteService pacienteService;
    
	@Autowired
	private DoadorService doadorService;

	@Autowired
	private DoacaoService doacaoService;

	// Metodos de Paciente

	@GetMapping(value = "/pacientes/nomes/{name}")
	public ResponseEntity<List<Paciente>> findPatientByName(@PathVariable String name) {
		return ResponseEntity.ok().body(pacienteService.findByName(name));
	}
	
	@GetMapping(value = "/pacientes/{id}")
	public ResponseEntity<Paciente> findPatientById(@PathVariable Long id) {
		return ResponseEntity.ok().body(pacienteService.findById(id));
	}
	
	@GetMapping(value = "/pacientes")
	public ResponseEntity<List<PacienteDTO>> findAllPatients(){
		return ResponseEntity.ok().body(pacienteService.findAll());
	}

	@GetMapping(value = "/pacientes/qtd/{bloodType}")
	public ResponseEntity<Long> findQuantityPatientByBloodType(@PathVariable String bloodType) {
		return ResponseEntity.ok().body(pacienteService.findQuantity(bloodType));
	}

	@PostMapping(value = "/pacientes")
	public ResponseEntity<Paciente> createPatient(@Valid @RequestBody Paciente patient) {

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("esangue/pacientes/{id}")
				.buildAndExpand(pacienteService.create(patient).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/pacientes/{id}")
	public ResponseEntity<Paciente> updatePatient(@PathVariable Long id, @Valid @RequestBody Paciente patient){
		return ResponseEntity.ok().body(pacienteService.update(id, patient));
	}
	
	@PatchMapping(value = "/pacientes/{id}")
	public ResponseEntity<Paciente> updatePatientPatch(@PathVariable Long id, @Valid @RequestBody Paciente patient){
		return ResponseEntity.ok().body(pacienteService.update(id, patient));
	}
	
	@DeleteMapping(value = "/pacientes/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id){
		pacienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Metodos de Doador
	
	@GetMapping(value = "/doadores/nomes/{name}")
	public ResponseEntity<List<Doador>> findDonatorByName(@PathVariable String name) {
		return ResponseEntity.ok().body(doadorService.findByName(name));
	}
	
	@GetMapping(value = "/doadores/{id}")
	public ResponseEntity<Doador> findDonatorById(@PathVariable Long id) {
		return ResponseEntity.ok().body(doadorService.findById(id));
	}
	
	@GetMapping(value = "/doadores")
	public ResponseEntity<List<DoadorDTO>> findAllDonators(){
		return ResponseEntity.ok().body(doadorService.findAll());
	}

	@GetMapping(value = "/doadores/qtd/{bloodType}")
	public ResponseEntity<Long> findQuantityDonatorByBloodType(@PathVariable String bloodType) {
		return ResponseEntity.ok().body(doadorService.findQuantity(bloodType));
	}
	
	@GetMapping(value = "/doadores/qtd")
	public ResponseEntity<Object> findBloodTypeWithLessRegisteredDonators() {
		return ResponseEntity.ok().body(doadorService.findBloodTypeWithLessRegisteredDonators());
	}

	@GetMapping(value = "/doadores/qtds")
	public ResponseEntity<Map<TipoSanguineo, Long>> getAllBloodTypesWithDonatorsAndQuantity() {
		return ResponseEntity.ok().body(doadorService.getAllBloodTypesWithDonatorsAndQuantity());
	}

	@PostMapping(value = "/doadores")
	public ResponseEntity<Doador> createDonator(@Valid @RequestBody Doador donator) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("esangue/doadores/{id}")
				.buildAndExpand(doadorService.create(donator).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/doadores/{id}")
	public ResponseEntity<Doador> updateDonator(@Valid @PathVariable Long id, @RequestBody Doador donator){
		return ResponseEntity.ok().body(doadorService.update(id, donator));
	}

	@PatchMapping(value = "/doadores/{id}")
	public ResponseEntity<Doador> updateDonatorPatch(@PathVariable Long id, @Valid @RequestBody Doador donator){
		return ResponseEntity.ok().body(doadorService.update(id, donator));
	}
	
	@DeleteMapping(value = "/doadores/{id}")
	public ResponseEntity<Void> deleteDonator(@PathVariable Long id){
		doadorService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Metodos de Doacao

	@GetMapping(value = "/doacoes/{id}")
	public ResponseEntity<Doacao> findDonationById(@PathVariable Long id) {
		return ResponseEntity.ok().body(doacaoService.findById(id));
	}
	
	@GetMapping(value = "/doacoes")
	public ResponseEntity<List<DoacaoDTO>> findAllDonations(){
		return ResponseEntity.ok().body(doacaoService.findAll());
	}
	
	@GetMapping(value = "/doacoes/qtds")
	public ResponseEntity<Map<String, Long>> getAllBloodTypesWithDonationsAndQuantity() {
		return ResponseEntity.ok().body(doacaoService.getAllBloodTypesWithDonationsAndQuantity());
	}

	@PostMapping(value = "/doacoes/{idd}/{idp}")
	public ResponseEntity<Doacao> donate(@PathVariable Long idd, @PathVariable Long idp, @Valid @RequestBody Doacao doacao) throws ParseException {
		return new ResponseEntity<>(this.doacaoService.makeADonate(idd, idp, doacao), HttpStatus.OK);
	}

	// Metodo de Teste

	@GetMapping(value = "/teste")
	public ResponseEntity<String> metodoTesteA() {
		return ResponseEntity.ok().body("teste");
	}

	@GetMapping(value = "/doadores/teste")
	public ResponseEntity<String> metodoTesteB() {
		return ResponseEntity.ok().body(doadorService.teste());
	}

}