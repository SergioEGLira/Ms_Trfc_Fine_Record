package com.TrafficFineRecord.dto;

import org.springframework.beans.BeanUtils;

import com.TrafficFineRecord.entities.UserPartialReplica;

import lombok.Data;

@Data
public class UserPartialReplicaDTO {

	private String cpf;
	private String nome;
	private String email;
	private String logradouro;
	private Long numero;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String cep;
	private String telefone;
	private String actionType;

	public UserPartialReplica convertToUser() {
		var user = new UserPartialReplica();
		BeanUtils.copyProperties(this, user);
		return user;
	}
}
