package com.TrafficFineRecord.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TrafficFineRecord.dto.AutuacaoDTO;
import com.TrafficFineRecord.dto.MultaStatusDTO;
import com.TrafficFineRecord.dto.NotificationCommandDTO;
import com.TrafficFineRecord.entities.Automovel;
import com.TrafficFineRecord.entities.Autuacao;
import com.TrafficFineRecord.entities.Infracao;
import com.TrafficFineRecord.entities.UserPartialReplica;
import com.TrafficFineRecord.enums.MultaStatus;
import com.TrafficFineRecord.publishers.NotificationCommandPublisher;
import com.TrafficFineRecord.repositories.AutomovelRepository;
import com.TrafficFineRecord.repositories.AutuacaoRepository;
import com.TrafficFineRecord.repositories.InfracaoRepository;
import com.TrafficFineRecord.repositories.UserRepository;
import com.TrafficFineRecord.services.exceptions.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AutuacaoService {

	@Autowired
	AutuacaoRepository autuacaoRepository;

	@Autowired
	InfracaoRepository infracaoRepository;

	@Autowired
	AutomovelRepository automovelRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	NotificationCommandPublisher notificationCommandPublisher;

	@Transactional(readOnly = true)
	public Autuacao findById(UUID idApiAutuacao) {
		Optional<Autuacao> obj = autuacaoRepository.findById(idApiAutuacao);
		if (obj.isEmpty()) {
			log.warn("LOG warn - GET, Id não encontrado: {} ", idApiAutuacao);
			return obj.orElseThrow(() -> new ResourceNotFoundException("Não encontramos o id " + idApiAutuacao));
		} else {
			Autuacao entity = obj.orElseThrow(
					() -> new ResourceNotFoundException("Não encontramos Autuacao com o id " + idApiAutuacao));
			return entity;
		}
	}

	public Page<Autuacao> findAutuacoes(String minDate, String maxDate, Pageable pageable) {
		LocalDateTime today = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDateTime min = minDate.equals("") ? today.minusDays(365) : LocalDateTime.parse(minDate);
		LocalDateTime max = maxDate.equals("") ? today : LocalDateTime.parse(maxDate);
		log.info("LOG - GET Page return {} ", HttpStatus.OK);
		return autuacaoRepository.findAutuacoes(min, max, pageable);
	}

	@Transactional
	public AutuacaoDTO insert(AutuacaoDTO dto) {

		try {

			Autuacao entity = new Autuacao();

			entity.setPlacaDoCarro(dto.getPlacaDoCarro());
			entity.setStatus(MultaStatus.NAO_PAGA);

			Infracao infracao = infracaoRepository.getOne(dto.getCodigoDaInfracao());
			entity.setInfracao(infracao);

			Automovel automovel = automovelRepository.getOne(dto.getPlacaDoCarro());
			entity.setAutomovel(automovel);

			UserPartialReplica userPartialReplica = userRepository.getOne(dto.getCpf());
			entity.setUserPartialReplica(userPartialReplica);

			entity = autuacaoRepository.save(entity);

			var notificationCommandDTO = new NotificationCommandDTO();

			notificationCommandDTO.setCpf(dto.getCpf());
			notificationCommandDTO.setTelefone(userPartialReplica.getTelefone());
			notificationCommandDTO.setPlacaDoCarro(dto.getPlacaDoCarro());
			notificationCommandDTO.setInfracao(infracao.getInfracaoDeTransito());

			notificationCommandPublisher.publishNotificationCommand(notificationCommandDTO);

			return new AutuacaoDTO(entity);

		} catch (EntityNotFoundException e) {
			log.warn("LOG warn - POST, Id não encontrado.");
			throw new EntityNotFoundException(
					"Gentileza revisar as informações inseridas, pois não encontramos o código da infração "
							+ dto.getCodigoDaInfracao() + " e/ou a placa do carro " + dto.getPlacaDoCarro()
							+ " e/ou o cpf  " + dto.getCpf());
		}

	}

	@Transactional
	public MultaStatusDTO updateStatusDaMulta(UUID idApiAutuacao, MultaStatusDTO dto) {

		try {

			Autuacao entity = autuacaoRepository.getOne(idApiAutuacao);
			entity.setStatus(dto.getStatus());
			entity = autuacaoRepository.save(entity);
			log.info("LOG - PUT return {}", HttpStatus.OK);
			return dto;
		} catch (EntityNotFoundException e) {
			log.warn("LOG warn - PUT, id não encontrado: {} ", idApiAutuacao);
			throw new EntityNotFoundException("Não encontramos o id " + idApiAutuacao);
		}

	}

	@Transactional(readOnly = true)
	public Page<Autuacao> paginacaoQuerySpec(Specification<Autuacao> spec, Pageable pageable) {
		return autuacaoRepository.findAll(spec, pageable);
	}

	@Transactional(readOnly = true)
	public List<Autuacao> findByStatus(MultaStatus status) {
		List<Autuacao> list = autuacaoRepository.findByStatus(status);
		if (list.size() == 0) {
			log.warn("LOG warn - PUT, Status não encontrado: {} ", status);
			throw new EntityNotFoundException("Não encontrado...");
		} else {
			return list.stream().collect(Collectors.toList());
		}
	}

	@Transactional(readOnly = true)
	public Page<Autuacao> getAllAutuacoesByUserPaged(Specification<Autuacao> findUserId, Pageable pageable) {
		return autuacaoRepository.findAll(findUserId, pageable);
	}

	public Object getAllAutuacoesByUser(Specification<Autuacao> findUserId) {
		List<Autuacao> list = autuacaoRepository.findAll(findUserId);
		if (list.size() == 0) {
			throw new EntityNotFoundException("Não encontrado...");
		} else {
			return autuacaoRepository.findAll(findUserId);
		}
	}

}
