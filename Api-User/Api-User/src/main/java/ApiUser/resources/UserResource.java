package ApiUser.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import ApiUser.configs.security.AuthenticationCurrentUserService;
import ApiUser.configs.security.JwtProvider;
import ApiUser.dto.AdminDto;
import ApiUser.dto.JwtDto;
import ApiUser.dto.LoginDto;
import ApiUser.dto.UserDTO;
import ApiUser.dto.UserEventDTO;
import ApiUser.dto.UserSameCpfDTO;
import ApiUser.entities.Role;
import ApiUser.entities.User;
import ApiUser.entities.feignsupport.Autuacao;
import ApiUser.enums.RoleType;
import ApiUser.enums.UserType;
import ApiUser.feignclients.TrafficFineRecordFeignClient;
import ApiUser.services.RoleService;
import ApiUser.services.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/user")
public class UserResource {

	@Autowired
	private ViaCepOpenFeignResource viaCepOpenFeignResource;

	@Autowired
	private UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AuthenticationCurrentUserService authenticationCurrentUserService;

	@Autowired
	TrafficFineRecordFeignClient trafficFineRecordFeignClient;

	@PostMapping("/signup")
	public ResponseEntity<User> postViaCepFeign(@RequestBody @Valid UserDTO userDTO) throws JsonProcessingException {
		log.info("LOG - POST received postViaCepFeign {} ", userDTO.toString());

		if (userService.existsByCpf(userDTO.getCpf())) {
			throw new DataAccessResourceFailureException(
					"Erro: já existe cpf cadastrado com o número: " + userDTO.getCpf());
		}

		Role roleAdmin = roleService.findByRoleName(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException());

		Role roleUser = roleService.findByRoleName(RoleType.ROLE_USER).orElseThrow(() -> new RuntimeException());

		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		User user = viaCepOpenFeignResource.obterCep(userDTO.getCep());
		user.setComplemento(userDTO.getComplemento());
		user.setNumero(userDTO.getNumero());
		user.setNome(userDTO.getNome());
		user.setEmail(userDTO.getEmail());
		user.setCpf(userDTO.getCpf());
		user.setTelefone(userDTO.getTelefone());
		user.setUsername(userDTO.getUsername());
		user.setUserType(userDTO.getUserType());
		user.setPassword(userDTO.getPassword());

		if (userDTO.getUserType() == UserType.ADMIN) {
			user.getRoles().add(roleAdmin);
		}

		if (userDTO.getUserType() == UserType.USER) {
			user.getRoles().add(roleUser);
		}

		userService.saveUser(user);
		log.info("LOG - POST return {} ", ResponseEntity.ok(user));

		UserEventDTO userEventDTO = new UserEventDTO();

		userEventDTO.setCpf(userDTO.getCpf());
		userEventDTO.setNome(userDTO.getNome());
		userEventDTO.setEmail(userDTO.getEmail());
		userEventDTO.setLogradouro(user.getLogradouro());
		userEventDTO.setNumero(userDTO.getNumero());
		userEventDTO.setComplemento(userDTO.getComplemento());
		userEventDTO.setBairro(user.getBairro());
		userEventDTO.setLocalidade(user.getLocalidade());
		userEventDTO.setUf(user.getUf());
		userEventDTO.setCep(userDTO.getCep());
		userEventDTO.setTelefone(userDTO.getTelefone());

		return ResponseEntity.ok(user);
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<User> getOne(@PathVariable String cpf) {
		log.info("LOG - GET received getOne id {} ", cpf);
		User dto = userService.getOne(cpf);
		log.info("LOG - GET return {} ", ResponseEntity.ok().body(dto));
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping("/{cpf}/enderecoput")
	public ResponseEntity<Object> updateViaCep(@PathVariable(value = "cpf") String cpf,
			@RequestBody @Validated(UserDTO.UserView.UserPut.class) UserDTO userPutDTO) {
		log.info("LOG - PUT received updateViaCep id {} ", cpf);
		Optional<User> userOptional = userService.findById(cpf);
		if (!userOptional.isPresent()) {
			log.warn("LOG warn - PUT {} ", ResponseEntity.status(HttpStatus.NOT_FOUND).body("User não encontrado..."));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User não encontrado...");
		} else {

			User user = viaCepOpenFeignResource.obterCep(userPutDTO.getCep());
			user.setNumero(userPutDTO.getNumero());
			user.setComplemento(userPutDTO.getComplemento());
			userService.updateend(cpf, user);
			log.info("LOG - PUT return {} ", ResponseEntity.noContent().build());
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/paginacaoQuerySpec")
	public ResponseEntity<Page<User>> paginacaoQuerySpec(User user, Pageable pageable) {
		log.info("LOG - GET received paginacaoQuerySpec {} ", pageable);
		Page<User> list = userService.paginacaoQuerySpec(user.toSpec(), pageable);
		log.info("LOG - GET Page return {} ", ResponseEntity.ok().body(list));
		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwt(authentication);
		return ResponseEntity.ok(new JwtDto(jwt));
	}

	@PostMapping("/newadmin")
	public ResponseEntity<Object> postUserRoleType(@RequestBody @Valid AdminDto adminDto) {

		Optional<User> userOptional = userService.findById(adminDto.getCpf());
		if (!userOptional.isPresent()) {
			throw new DataAccessResourceFailureException("Erro: não encontramos o Cpf: " + adminDto.getCpf());
		} else {

			Role role = roleService.findByRoleName(RoleType.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role não existe."));

			var user = userOptional.get();
			user.setUserType(UserType.ADMIN);

			user.getRoles().add(role);

			userService.updateRoleType(user);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
	}
  
	@GetMapping("/{cpf}/onlymytokencpf")
	public ResponseEntity<Object> getOneUserOnlyMyTokenCpf(@PathVariable(value = "cpf") String cpf) {

		String currentUserId = authenticationCurrentUserService.getCurrentUser().getCpf();

		if (currentUserId.equals(cpf)) {
			UserSameCpfDTO userModelOptional = userService.findBySameCpf(cpf);
			return ResponseEntity.status(HttpStatus.OK).body(userModelOptional);
		}
		throw new AccessDeniedException("O Cpf do usuário procurado é diferente do Cpf do token recebido...");
	}

	@GetMapping(value = "/autuacao/{cpf}/getallautuacoesbyuseronlymytokencpf")
	public ResponseEntity<List<Autuacao>> getAllAutuacoesByUser(@PathVariable(value = "cpf") String cpf) {
		String currentUserId = authenticationCurrentUserService.getCurrentUser().getCpf();
		if (currentUserId.equals(cpf)) {
			List<Autuacao> list = trafficFineRecordFeignClient.getAllAutuacoesByUser(cpf);
			return ResponseEntity.ok().body(list);
		}
		throw new AccessDeniedException("O Cpf do usuário procurado é diferente do Cpf do token recebido...");
	}
}
