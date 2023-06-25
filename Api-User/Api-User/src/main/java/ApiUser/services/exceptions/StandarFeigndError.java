package ApiUser.services.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandarFeigndError implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	private String path;

	public StandarFeigndError() {
	}
}
