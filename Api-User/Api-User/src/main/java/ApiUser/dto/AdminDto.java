package ApiUser.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdminDto {

	@NotNull
	private String cpf;

}
