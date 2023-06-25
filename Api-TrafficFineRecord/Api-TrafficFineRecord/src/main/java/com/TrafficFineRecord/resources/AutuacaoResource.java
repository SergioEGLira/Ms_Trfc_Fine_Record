package com.TrafficFineRecord.resources;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TrafficFineRecord.dto.AutuacaoDTO;
import com.TrafficFineRecord.dto.MultaStatusDTO;
import com.TrafficFineRecord.entities.Autuacao;
import com.TrafficFineRecord.enums.MultaStatus;
import com.TrafficFineRecord.services.AutuacaoService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Log4j2
@RequestMapping("/autuacao")
public class AutuacaoResource {

	@Autowired
	AutuacaoService autuacaoService;

	@GetMapping("/{idApiAutuacao}")
	public ResponseEntity<Autuacao> findById(@PathVariable UUID idApiAutuacao) {
		log.info("LOG - GET received findById id {} ", idApiAutuacao);
		Autuacao entity = autuacaoService.findById(idApiAutuacao);
		log.info("LOG - GET return {} ", HttpStatus.OK);
		return ResponseEntity.ok().body(entity);
	}

	@GetMapping(value = "/getAutuacaoPorIntervaloDeDatas")
	public Page<Autuacao> findAutuacoes(@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate, Pageable pageable) {
		log.info("LOG - GET received getAutuacaoPorIntervaloDeDatas {} ", pageable);
		return autuacaoService.findAutuacoes(minDate, maxDate, pageable);
	}

	@PostMapping
	public ResponseEntity<AutuacaoDTO> insert(
			@RequestBody @Validated(AutuacaoDTO.UserView.AutuacaoInsert.class) AutuacaoDTO dto) {
		log.info("LOG - POST insert Autuação, received userId: {} ", dto.getCpf());
		AutuacaoDTO newDTO = autuacaoService.insert(dto);
		log.info("LOG - POST return {} ", HttpStatus.OK);
		return ResponseEntity.ok().body(newDTO);
	}

	@PutMapping(value = "/{idApiAutuacao}/updateStatusDaMulta")
	public ResponseEntity<MultaStatusDTO> updateStatusDaMulta(@PathVariable UUID idApiAutuacao,
			@RequestBody @Valid MultaStatusDTO dto) {
		log.info("LOG - PUT received updateStatusDaMulta id {} ", idApiAutuacao);
		dto = autuacaoService.updateStatusDaMulta(idApiAutuacao, dto);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping("/paginacaoQuerySpec")
	public ResponseEntity<Page<Autuacao>> paginacaoQuerySpec(Autuacao autuacao, Pageable pageable) {
		log.info("LOG - GET received paginacaoQuerySpec {} ", pageable);
		Page<Autuacao> list = autuacaoService.paginacaoQuerySpec(autuacao.toSpec(), pageable);
		log.info("LOG - GET Page return {} ", ResponseEntity.ok().body(list));
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{status}/status")
	public ResponseEntity<List<Autuacao>> findByStatus(@PathVariable("status") MultaStatus status) {
		log.info("LOG - GET received findByStatus: {} ", status);
		List<Autuacao> list = autuacaoService.findByStatus(status);
		log.info("LOG - GET return {} ", HttpStatus.OK);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/getAllAutuacoesByUserPaged")
	public ResponseEntity<Page<Autuacao>> getAllAutuacoesByUserPaged(
			@PageableDefault(page = 0, size = 20, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String cpf) {
		log.info("LOG - GET received paginacaoQuerySpec {} ", pageable);
		log.info("LOG - GET return {} ", HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK)
				.body(autuacaoService.getAllAutuacoesByUserPaged(Autuacao.findUserId(cpf), pageable));
	}

	@GetMapping(value = "/{cpf}/getAllAutuacoesByUser")
	public ResponseEntity<Object> getAllAutuacoesByUser(@PathVariable(value = "cpf") String cpf) {
		log.info("LOG - GET received getAllAutuacoesByUser: {} ", cpf);
		return ResponseEntity.status(HttpStatus.OK)
				.body(autuacaoService.getAllAutuacoesByUser(Autuacao.findUserId(cpf)));
	}
}
