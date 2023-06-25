package ApiUser.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ApiUser.entities.feignsupport.Autuacao;

@FeignClient(name = "http://localhost:8080/api-trafficFineRecord/")
@Component
public interface TrafficFineRecordFeignClient {

	@GetMapping(value = "/autuacao/{cpf}/getAllAutuacoesByUser")
	List<Autuacao> getAllAutuacoesByUser(@PathVariable(value = "cpf") String cpf);

}
