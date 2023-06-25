package com.TrafficFineRecord.dto;

import lombok.Data;

@Data
public class NotificationCommandDTO {

	private String cpf;
	private String telefone;
	private String placaDoCarro;
	private String infracao;

}
