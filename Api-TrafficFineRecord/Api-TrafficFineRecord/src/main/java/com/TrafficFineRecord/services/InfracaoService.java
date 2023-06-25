package com.TrafficFineRecord.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TrafficFineRecord.dto.InfracaoDTO;
import com.TrafficFineRecord.entities.Infracao;
import com.TrafficFineRecord.repositories.InfracaoRepository;
import com.TrafficFineRecord.services.exceptions.DatabaseException;
import com.TrafficFineRecord.services.exceptions.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class InfracaoService {

	@Autowired
	private InfracaoRepository infracaoRepository;

	public InfracaoDTO insert(InfracaoDTO dto) {
		Infracao entity = new Infracao();
		entity.setCodigoDaInfracao(dto.getCodigoDaInfracao());
		entity.setInfracaoDeTransito(dto.getInfracaoDeTransito());
		entity.setPontosNaCarteira(dto.getPontosNaCarteira());
		entity.setValorDaMulta(dto.getValorDaMulta());
		entity = infracaoRepository.save(entity);
		return new InfracaoDTO(entity);
	}

	public boolean existsByCodigoDaInfracao(Long codigoDaInfracao) {
		return infracaoRepository.existsByCodigoDaInfracao(codigoDaInfracao);
	}

	@Transactional(readOnly = true)
	public Infracao findById(Long codigoDaInfracao) {
		Optional<Infracao> obj = infracaoRepository.findById(codigoDaInfracao);
		if (obj.isEmpty()) {
			log.warn("LOG warn - GET, Id não encontrado: {} ", codigoDaInfracao);
			return obj.orElseThrow(
					() -> new ResourceNotFoundException("Não encontramos a entidade com id " + codigoDaInfracao));
		} else {
			Infracao entity = obj.orElseThrow(
					() -> new ResourceNotFoundException("Não encontramos a entidade com id " + codigoDaInfracao));
			return entity;
		}
	}

	public void delete(Long codigoDaInfracao) {
		try {
			infracaoRepository.deleteById(codigoDaInfracao);
		} catch (EmptyResultDataAccessException e) {
			log.warn("LOG warn - DEL return, Não encontramos o Id {}", codigoDaInfracao);
			throw new ResourceNotFoundException("Não encontramos o id " + codigoDaInfracao);
		} catch (DataIntegrityViolationException e) {
			log.warn("LOG warn - DEL return, Erro de integridade do bco de dados!");
			throw new DatabaseException("Erro de integridade do bco de dados!");
		}
	}

	@Transactional
	public Infracao updatePontosNaCarteiraValorDaMulta(Long codigoDaInfracao, InfracaoDTO dto) {

		try {
			Infracao entity = infracaoRepository.getOne(codigoDaInfracao);
			entity.setPontosNaCarteira(dto.getPontosNaCarteira());
			entity.setValorDaMulta(dto.getValorDaMulta());

			entity = infracaoRepository.save(entity);
			log.info("LOG - PUT return {}", HttpStatus.OK);
			return entity;
		} catch (EntityNotFoundException e) {
			log.warn("LOG warn - PUT, id não encontrado: {} ", codigoDaInfracao);
			throw new ResourceNotFoundException("Não encontramos o id " + codigoDaInfracao);
		}
	}

	@Transactional(readOnly = true)
	public Page<Infracao> paginacaoQuerySpec(Specification<Infracao> spec, Pageable pageable) {
		return infracaoRepository.findAll(spec, pageable);
	}
}
