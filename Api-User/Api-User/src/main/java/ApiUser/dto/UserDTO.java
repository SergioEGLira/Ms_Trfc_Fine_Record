package ApiUser.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonView;

import ApiUser.configs.validation.UserNameConstraint;
import ApiUser.configs.validation.UserSenhaConstraint;
import ApiUser.enums.UserType;
import lombok.Data;

@Data
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface UserView {
		public static interface UserPut {
		}
	}

	@NotBlank(groups = UserView.UserPut.class, message = "O Cep não deve estar em branco")
	@NotBlank(message = "O Cep não deve estar em branco")
	@Positive(groups = UserView.UserPut.class, message = "O Cep deve ser maior que 0")
	@Positive(message = "O Cep deve ser maior que 0")
	@Size(groups = UserView.UserPut.class, min = 8, max = 8, message = "O Cep deve ter 8 caracteres numéricos")
	@Size(min = 8, max = 8, message = "O Cep deve ter 8 caracteres numéricos")
	@JsonView(UserView.UserPut.class)
	private String cep;
	@PositiveOrZero(groups = UserView.UserPut.class, message = "O numero deve ser igual ou maior que 0")
	@PositiveOrZero(message = "O numero deve ser igual ou maior que 0")
	@Max(groups = UserView.UserPut.class, value = 9999, message = "O numero deve ser inferior ou igual a 9999")
	@Max(value = 9999, message = "O numero deve ser inferior ou igual a 9999")
	@JsonView(UserView.UserPut.class)
	private Long numero;
	@Size(groups = UserView.UserPut.class, min = 0, max = 20, message = "O complemento deve ter no máximo 20 caracteres")
	@Size(min = 0, max = 20, message = "O complemento deve ter no máximo 20 caracteres")
	@JsonView(UserView.UserPut.class)
	private String complemento;
	@NotBlank(message = "O nome não deve estar em branco")
	@Size(min = 2, max = 40, message = "O nome deve ter entre 2 e 40 caracteres")
	private String nome;
	@NotBlank
	@UserNameConstraint
	@Size(min = 6, max = 20)
	private String username;
	@NotBlank(message = "Email não pode ser vazio")
	@Email(message = "Favor digitar email válido")
	private String email;
	@NotBlank(message = "CPF não pode ser vazio")
	@Size(min = 14, max = 14, message = "O CPF deve ter 14 caracteres, gentileza incluir '.' e '-'")
	@CPF(message = "Favor digitar CPF válido")
	private String cpf;
	@NotBlank(message = "O telefone não deve estar em branco")
	@Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
	private String telefone;
	private String actionType;
	@NotBlank
	@UserSenhaConstraint
	@Size(min = 6, max = 20)
	private String password;
	@Enumerated(EnumType.STRING)
	private UserType userType;

}