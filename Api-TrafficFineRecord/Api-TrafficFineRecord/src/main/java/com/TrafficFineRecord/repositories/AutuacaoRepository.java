package com.TrafficFineRecord.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TrafficFineRecord.entities.Autuacao;
import com.TrafficFineRecord.enums.MultaStatus;

@Repository
public interface AutuacaoRepository extends JpaRepository<Autuacao, UUID>, JpaSpecificationExecutor<Autuacao> {

	@Query("SELECT obj FROM Autuacao obj WHERE obj.createdAt BETWEEN :min AND :max ORDER BY obj.placaDoCarro ASC")
	Page<Autuacao> findAutuacoes(LocalDateTime min, LocalDateTime max, Pageable pageable);

	List<Autuacao> findByStatus(MultaStatus status);

}
