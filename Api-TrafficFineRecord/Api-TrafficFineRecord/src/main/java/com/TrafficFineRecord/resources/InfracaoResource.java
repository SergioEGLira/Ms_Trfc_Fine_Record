package com.TrafficFineRecord.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrafficFineRecord.dto.InfracaoDTO;
import com.TrafficFineRecord.entities.Infracao;
import com.TrafficFineRecord.services.InfracaoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/infracoes")
public class InfracaoResource {

	@Autowired
	private InfracaoService infracaoService;

	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody @Valid InfracaoDTO dto) {
		log.info("LOG - POST received {} ", dto.toString());
		if (infracaoService.existsByCodigoDaInfracao(dto.getCodigoDaInfracao())) {
			log.warn("LOG warn - POST, código da infração já cadastrado id: {} ", dto.getCodigoDaInfracao());
			throw new DataAccessResourceFailureException("Erro: código da infração já cadastrado...");
		} else {
			dto = infracaoService.insert(dto);
			log.info("LOG - POST return {} ", HttpStatus.OK);
			return ResponseEntity.ok().body(dto);
		}
	}

	@GetMapping(value = "/{codigoDaInfracao}")
	public ResponseEntity<Infracao> findById(@PathVariable Long codigoDaInfracao) {
		log.info("LOG - GET received findById id {} ", codigoDaInfracao);
		Infracao entity = infracaoService.findById(codigoDaInfracao);
		log.info("LOG - GET return {} ", HttpStatus.OK);
		return ResponseEntity.ok().body(entity);
	}

	@DeleteMapping(value = "/{codigoDaInfracao}")
	public ResponseEntity<Void> delete(@PathVariable Long codigoDaInfracao) {
		log.info("LOG - DEL received codigoDaInfracao id {} ", codigoDaInfracao);
		infracaoService.delete(codigoDaInfracao);
		log.info("LOG - DEL return {} ", HttpStatus.NO_CONTENT);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{codigoDaInfracao}/updatePontosNaCarteiraValorDaMulta")
	public ResponseEntity<Infracao> updatePontosNaCarteiraValorDaMulta(@PathVariable Long codigoDaInfracao,
			@RequestBody @Validated(InfracaoDTO.UserView.InfracaoUpdate.class) InfracaoDTO dto) {
		log.info("LOG - PUT received updatePontosNaCarteiraValorDaMulta id {} ", codigoDaInfracao);
		Infracao newDto = infracaoService.updatePontosNaCarteiraValorDaMulta(codigoDaInfracao, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@GetMapping("/paginacaoQuerySpec")
	public ResponseEntity<Page<Infracao>> paginacaoQuerySpec(Infracao infracao, Pageable pageable) {
		log.info("LOG - GET received paginacaoQuerySpec {} ", pageable);
		Page<Infracao> list = infracaoService.paginacaoQuerySpec(infracao.toSpec(), pageable);
		log.info("LOG - GET Page return {} ", ResponseEntity.ok().body(list));
		return ResponseEntity.ok().body(list);
	}
}
