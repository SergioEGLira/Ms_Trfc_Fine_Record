package ApiUser.entities.feignsupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Infracao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigoDaInfracao;
	private String infracaoDeTransito;
	private String pontosNaCarteira;
	private double valorDaMulta;

	@JsonIgnore
	@OneToMany(mappedBy = "infracao")
	private List<Autuacao> autuacoes = new ArrayList<>();

	public Infracao() {
	}

	public Infracao(Long codigoDaInfracao, String infracaoDeTransito, String pontosNaCarteira, double valorDaMulta) {
		this.codigoDaInfracao = codigoDaInfracao;
		this.infracaoDeTransito = infracaoDeTransito;
		this.pontosNaCarteira = pontosNaCarteira;
		this.valorDaMulta = valorDaMulta;
	}

}
