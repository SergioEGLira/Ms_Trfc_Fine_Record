package com.TrafficFineRecord.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class AutomovelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface UserView {
		public static interface AutomovelUpdate {
		}
	}

	@NotBlank(message = "A placa não deve estar em branco")
	@Size(min = 8, max = 8, message = "A placa deve ter 8 caracteres alfanuméricos, incluido o hifen")
	private String placa;
	@Positive(message = "O renavam deve ser positivo")
	private Long renavam;
	@NotBlank(message = "A marca deve ser preenchida")
	@Size(min = 4, max = 20, message = "A marca deve ter entre 4 e 20 caracteres")
	private String marca;
	@NotBlank(message = "O modelo deve ser preenchido")
	@Size(min = 4, max = 50, message = "O modelo deve ter entre 4 e 50 caracteres")
	private String modelo;
	@Positive(message = "O ano deve ser positivo")
	@Min(1900)
	@Max(2050)
	private long ano;
	@NotBlank(groups = UserView.AutomovelUpdate.class, message = "A cor deve ser digitada")
	@NotBlank(message = "A cor deve ser digitada")
	@Size(groups = UserView.AutomovelUpdate.class, min = 3, max = 15, message = "A cor deve ter entre 3 e 15 caracteres")
	@Size(min = 3, max = 15, message = "A cor deve ter entre 3 e 15 caracteres")
	@JsonView(UserView.AutomovelUpdate.class)
	private String cor;

}
