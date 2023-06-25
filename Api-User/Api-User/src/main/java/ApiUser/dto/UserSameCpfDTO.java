package ApiUser.dto;

import java.io.Serializable;

import ApiUser.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSameCpfDTO implements Serializable {
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

	public UserSameCpfDTO() {
	}

	public UserSameCpfDTO(String cpf, String nome, String email, String logradouro, Long numero, String complemento,
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

	public UserSameCpfDTO(User entity) {

		cpf = entity.getCpf();
		nome = entity.getNome();
		email = entity.getEmail();
		logradouro = entity.getLogradouro();
		numero = entity.getNumero();
		complemento = entity.getComplemento();
		bairro = entity.getBairro();
		localidade = entity.getLocalidade();
		uf = entity.getUf();
		cep = entity.getCep();
		telefone = entity.getTelefone();

	}

}
