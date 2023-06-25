package com.TrafficFineRecord.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.TrafficFineRecord.entities.Autuacao;
import com.TrafficFineRecord.enums.MultaStatus;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class AutuacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface UserView {
		public static interface AutuacaoInsert {
		}
	}

	private UUID idApiAutuacao;
	@NotBlank(groups = UserView.AutuacaoInsert.class, message = "A placa não deve estar em branco")
	@NotBlank(message = "A placa não deve estar em branco")
	@Size(groups = UserView.AutuacaoInsert.class, min = 8, max = 8, message = "A placa deve ter 8 caracteres alfanuméricos, incluido o hifen")
	@Size(min = 8, max = 8, message = "A placa deve ter 8 caracteres alfanuméricos, incluido o hifen")
	@JsonView(UserView.AutuacaoInsert.class)
	private String placaDoCarro;
	private LocalDateTime createdAt;
	private MultaStatus status;
	@Positive(groups = UserView.AutuacaoInsert.class, message = "O codigo da infracao deve ser positivo")
	@Positive(message = "O codigo da infracao deve ser positivo")
	@Max(groups = UserView.AutuacaoInsert.class, value = 99999)
	@Max(value = 99999)
	@JsonView(UserView.AutuacaoInsert.class)
	private Long codigoDaInfracao;
	private String infracaoDeTransito;
	private String infracaoPontosNaCarteira;
	private double infracaoValorDaMulta;
	private Long automovelRenavam;
	private String automovelMarca;
	private String automovelModelo;
	private long automovelAno;
	private String automovelCor;
	@JsonView(UserView.AutuacaoInsert.class)
	private String authUserNome;
	private String authUserEmail;
	@JsonView(UserView.AutuacaoInsert.class)
	private String cpf;
	private String authUserLogradouro;
	private Long authUserNumero;
	private String authUserComplemento;
	private String authUserBairro;
	private String authUserLocalidade;
	private String authUserUf;
	private String authUserCep;
	private String authUserTelefone;

	public AutuacaoDTO() {
	}

	public AutuacaoDTO(Autuacao entity) {

		idApiAutuacao = entity.getIdApiAutuacao();
		placaDoCarro = entity.getPlacaDoCarro();
		createdAt = entity.getCreatedAt();

		status = entity.getStatus();

		authUserNome = entity.getUserPartialReplica().getNome();
		authUserEmail = entity.getUserPartialReplica().getEmail();
		cpf = entity.getUserPartialReplica().getCpf();
		authUserLogradouro = entity.getUserPartialReplica().getLogradouro();
		authUserNumero = entity.getUserPartialReplica().getNumero();
		authUserComplemento = entity.getUserPartialReplica().getComplemento();
		authUserBairro = entity.getUserPartialReplica().getBairro();
		authUserLocalidade = entity.getUserPartialReplica().getLocalidade();
		authUserLocalidade = entity.getUserPartialReplica().getLocalidade();
		authUserUf = entity.getUserPartialReplica().getUf();
		authUserCep = entity.getUserPartialReplica().getCep();
		authUserTelefone = entity.getUserPartialReplica().getTelefone();
		codigoDaInfracao = entity.getInfracao().getCodigoDaInfracao();
		infracaoDeTransito = entity.getInfracao().getInfracaoDeTransito();
		infracaoPontosNaCarteira = entity.getInfracao().getPontosNaCarteira();
		infracaoValorDaMulta = entity.getInfracao().getValorDaMulta();
		automovelRenavam = entity.getAutomovel().getRenavam();
		automovelMarca = entity.getAutomovel().getMarca();
		automovelModelo = entity.getAutomovel().getModelo();
		automovelAno = entity.getAutomovel().getAno();
		automovelCor = entity.getAutomovel().getCor();
	}

}
