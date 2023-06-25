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

import com.TrafficFineRecord.dto.AutomovelDTO;
import com.TrafficFineRecord.entities.Automovel;
import com.TrafficFineRecord.services.AutomovelService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/automoveis")
public class AutomovelResource {

	@Autowired
	private AutomovelService automovelService;

	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody @Valid AutomovelDTO dto) {
		log.info("LOG - POST received {} ", dto.toString());
		if (automovelService.existsByPlaca(dto.getPlaca())) {
			log.warn("LOG warn - POST, já existe automóvel cadastrado com a placa: {} ", dto.getPlaca());
			throw new DataAccessResourceFailureException(
					"Erro: já existe automóvel cadastrado com a placa: " + dto.getPlaca());
		} else {
			dto = automovelService.insert(dto);
			log.info("LOG - POST return {} ", ResponseEntity.ok().body(dto));
			return ResponseEntity.ok().body(dto);
		}
	}

	@PutMapping(value = "/{placa}/updateCorDoAutomovel")
	public ResponseEntity<Automovel> updateCorDoAutomovel(@PathVariable String placa,
			@RequestBody @Validated(AutomovelDTO.UserView.AutomovelUpdate.class) AutomovelDTO dto) {

		log.info("LOG - PUT received updateCorDoAutomovel id {} ", placa);

		Automovel newDto = automovelService.updateCorDoAutomovel(placa, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@GetMapping(value = "/{placa}")
	public ResponseEntity<Automovel> findById(@PathVariable String placa) {
		log.info("LOG - GET received findById id {} ", placa);
		Automovel dto = automovelService.findById(placa);
		log.info("LOG - GET return {} ", HttpStatus.OK);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{placa}")
	public ResponseEntity<Void> delete(@PathVariable String placa) {
		log.info("LOG - DEL received id {} ", placa);
		automovelService.delete(placa);
		log.info("LOG - DEL return {} ", HttpStatus.NO_CONTENT);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/paginacaoQuerySpec")
	public ResponseEntity<Page<Automovel>> paginacaoQuerySpec(Automovel automovel, Pageable pageable) {
		log.info("LOG - GET received paginacaoQuerySpec {} ", pageable);
		Page<Automovel> list = automovelService.paginacaoQuerySpec(automovel.toSpec(), pageable);
		log.info("LOG - GET Page return {} ", ResponseEntity.ok().body(list));
		return ResponseEntity.ok().body(list);
	}
}
