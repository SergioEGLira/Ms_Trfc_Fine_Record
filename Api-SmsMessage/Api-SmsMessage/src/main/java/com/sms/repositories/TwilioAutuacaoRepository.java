package com.sms.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.entities.TwilioAutuacao;

@Repository
public interface TwilioAutuacaoRepository extends JpaRepository<TwilioAutuacao, UUID> {

}