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
public class UserPartialReplica implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@JsonIgnore
	@OneToMany(mappedBy = "userPartialReplica")
	private List<Autuacao> autuacoes = new ArrayList<>();

	public UserPartialReplica() {
	}

	public UserPartialReplica(String cpf, String nome, String email, String logradouro, Long numero, String complemento,
			String bairro, String localidade, String uf, String cep, String telefone) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.cep = cep;
		this.telefone = telefone;
	}

}
