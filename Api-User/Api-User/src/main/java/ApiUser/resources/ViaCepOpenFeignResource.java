package ApiUser.resources;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ApiUser.entities.User;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepOpenFeignResource {

	@GetMapping("/{cep}/json")
	User obterCep(@PathVariable("cep") String cep);

}