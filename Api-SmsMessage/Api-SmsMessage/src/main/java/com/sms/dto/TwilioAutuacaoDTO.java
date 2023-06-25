package com.sms.dto;

import org.springframework.beans.BeanUtils;

import com.sms.entities.TwilioAutuacao;
import com.sms.enums.NotificationStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TwilioAutuacaoDTO {

	private String cpf;
	private String telefone;
	private String placaDoCarro;
	private String infracao;

	private NotificationStatus notificationStatus;

	public TwilioAutuacaoDTO() {
	}

	public TwilioAutuacaoDTO(String cpf, String telefone, String placaDoCarro, String infracao,
			NotificationStatus notificationStatus) {
		this.cpf = cpf;
		this.telefone = telefone;
		this.placaDoCarro = placaDoCarro;
		this.infracao = infracao;
		this.notificationStatus = notificationStatus;
	}

//--------------------------------------------------------------------------	
//	public TwilioAutuacaoDTO(TwilioAutuacao entity) {
//		cpf = entity.getCpf();
//		telefone = entity.getTelefone();
//		placaDoCarro = entity.getPlacaDoCarro();
//		infracao = entity.getInfracao();
//	}

	public TwilioAutuacao convertToTwilioAutuacao() {
		var twilioAutuacao = new TwilioAutuacao();
		BeanUtils.copyProperties(this, twilioAutuacao);
		return twilioAutuacao;
	}
//--------------------------------------------------------------------------

}
