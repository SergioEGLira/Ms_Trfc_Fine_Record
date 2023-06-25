package com.TrafficFineRecord.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.TrafficFineRecord.entities.Infracao;

@Repository
public interface InfracaoRepository extends JpaRepository<Infracao, Long>, JpaSpecificationExecutor<Infracao> {

	boolean existsByCodigoDaInfracao(Long codigoDaInfracao);

}
