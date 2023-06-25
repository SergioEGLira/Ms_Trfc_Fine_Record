package com.TrafficFineRecord.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.TrafficFineRecord.entities.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, String>, JpaSpecificationExecutor<Automovel> {

	boolean existsByPlaca(String placa);

}
