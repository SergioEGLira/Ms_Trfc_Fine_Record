package ApiUser.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserEventDTO implements Serializable {
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
	private String actionType;

}
