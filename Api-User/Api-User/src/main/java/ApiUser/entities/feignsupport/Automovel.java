package ApiUser.entities.feignsupport;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Automovel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String placa;
	private Long renavam;
	private String marca;
	private String modelo;
	private long ano;
	private String cor;

	public Automovel() {
		super();
	}

	public Automovel(Long renavam, String marca, String modelo, long ano, String placa, String cor) {
		super();
		this.renavam = renavam;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.placa = placa;
		this.cor = cor;
	}

}
