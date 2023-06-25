package com.sms.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.sms.enums.NotificationStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ToString
@Table(name = "tb_twilio")
public class TwilioAutuacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idSmsTwilio;
	private String cpf;
	private String telefone;
	private String placaDoCarro;
	private String infracao;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	@Enumerated(EnumType.STRING)
	private NotificationStatus notificationStatus;

	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}

	public TwilioAutuacao() {
	}

	public TwilioAutuacao(UUID idSmsTwilio, String cpf, String telefone, String placaDoCarro, String infracao,
			Instant createdAt, NotificationStatus notificationStatus) {
		this.idSmsTwilio = idSmsTwilio;
		this.cpf = cpf;
		this.telefone = telefone;
		this.placaDoCarro = placaDoCarro;
		this.infracao = infracao;
		this.createdAt = createdAt;
		this.notificationStatus = notificationStatus;
	}

}
