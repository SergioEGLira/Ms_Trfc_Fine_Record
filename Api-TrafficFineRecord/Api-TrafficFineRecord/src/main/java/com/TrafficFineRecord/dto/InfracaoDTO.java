package com.TrafficFineRecord.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.TrafficFineRecord.entities.Infracao;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InfracaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface UserView {
		public static interface InfracaoUpdate {
		}
	}

	@Positive(message = "O codigo da infracao deve ser positivo")
	@Max(value = 99999)
	private Long codigoDaInfracao;
	@NotBlank(message = "A descrição da infração de transito não deve estar em branco")
	@Size(min = 8, max = 120)
	private String infracaoDeTransito;
	@NotBlank(groups = UserView.InfracaoUpdate.class, message = "A descrição dos pontos na carteira não deve estar em branco")
	@NotBlank(message = "A descrição dos pontos na carteira não deve estar em branco")
	@Size(groups = UserView.InfracaoUpdate.class, min = 10, max = 40)
	@Size(min = 10, max = 40)
	@JsonView(UserView.InfracaoUpdate.class)
	private String pontosNaCarteira;
	@Positive(groups = UserView.InfracaoUpdate.class, message = "O valor da multa deve ser positivo")
	@Positive(message = "O valor da multa deve ser positivo")
	@Max(groups = UserView.InfracaoUpdate.class, value = 18000)
	@Max(value = 18000)
	@JsonView(UserView.InfracaoUpdate.class)
	private double valorDaMulta;

	public InfracaoDTO() {
		super();
	}

	public InfracaoDTO(Long codigoDaInfracao, String infracaoDeTransito, String pontosNaCarteira, double valorDaMulta) {
		super();
		this.codigoDaInfracao = codigoDaInfracao;
		this.infracaoDeTransito = infracaoDeTransito;
		this.pontosNaCarteira = pontosNaCarteira;
		this.valorDaMulta = valorDaMulta;
	}

	public InfracaoDTO(Infracao entity) {

		codigoDaInfracao = entity.getCodigoDaInfracao();
		infracaoDeTransito = entity.getInfracaoDeTransito();
		pontosNaCarteira = entity.getPontosNaCarteira();
		valorDaMulta = entity.getValorDaMulta();

	}
}
