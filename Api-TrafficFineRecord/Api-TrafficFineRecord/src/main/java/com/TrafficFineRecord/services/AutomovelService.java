package com.TrafficFineRecord.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TrafficFineRecord.dto.AutomovelDTO;
import com.TrafficFineRecord.entities.Automovel;
import com.TrafficFineRecord.repositories.AutomovelRepository;
import com.TrafficFineRecord.services.exceptions.DatabaseException;
import com.TrafficFineRecord.services.exceptions.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AutomovelService {

	@Autowired
	private AutomovelRepository automovelRepository;

	public AutomovelDTO insert(AutomovelDTO dto) {

		var automovel = new Automovel();
		BeanUtils.copyProperties(dto, automovel);

		automovel = automovelRepository.save(automovel);
		return dto;
	}

	public boolean existsByPlaca(String placa) {
		return automovelRepository.existsByPlaca(placa);
	}

	@Transactional
	public Automovel updateCorDoAutomovel(String placa, AutomovelDTO dto) {

		try {
			Automovel entity = automovelRepository.getOne(placa);

			entity.setCor(dto.getCor());
			entity = automovelRepository.save(entity);

			log.info("LOG - PUT, atualização efetivada para o Id: {}", placa);
			return entity;
		} catch (EntityNotFoundException e) {

			log.warn("LOG warn - PUT, id não encontrado: {} ", placa);

			throw new ResourceNotFoundException("Não encontramos automóvel com a placa " + placa);
		}

	}

	@Transactional(readOnly = true)
	public Automovel findById(String placa) {
		Optional<Automovel> obj = automovelRepository.findById(placa);

		if (obj.isEmpty()) {
			log.warn("LOG warn - GET, automóvel não encontrado: {} ", placa);
			return obj
					.orElseThrow(() -> new ResourceNotFoundException("Não encontramos automóvel com a placa " + placa));
		} else {
			Automovel entity = obj
					.orElseThrow(() -> new ResourceNotFoundException("Não encontramos automóvel com a placa " + placa));
			return entity;
		}
	}

	public void delete(String placa) {
		try {
			automovelRepository.deleteById(placa);
		} catch (EmptyResultDataAccessException e) {
			log.warn("LOG warn - DEL return, Não encontramos automóvel com a placa {}", placa);
			throw new ResourceNotFoundException("Não encontramos automóvel com a placa " + placa);
		} catch (DataIntegrityViolationException e) {
			log.warn("LOG warn - DEL return, Erro de integridade do bco de dados!");
			throw new DatabaseException("Erro de integridade do bco de dados!");
		}
	}

	@Transactional(readOnly = true)
	public Page<Automovel> paginacaoQuerySpec(Specification<Automovel> spec, Pageable pageable) {
		return automovelRepository.findAll(spec, pageable);
	}
}
