package ApiUser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ApiUser.dto.UserSameCpfDTO;
import ApiUser.entities.User;
import ApiUser.enums.ActionType;
import ApiUser.publishers.UserEventPublisher;
import ApiUser.repositories.UserRepository;
import ApiUser.services.exceptions.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserEventPublisher userEventPublisher;

	public User save(User user) {
		userRepository.save(user);
		return user;
	}

	@Transactional
	public User saveUser(User user) {
		user = save(user);
		userEventPublisher.publishUserEvent(user.convertToUserEventDto(), ActionType.CREATE);

		return user;
	}

	@Transactional(readOnly = true)
	public User getOne(String cpf) {
		Optional<User> obj = userRepository.findById(cpf);

		if (obj.isEmpty()) {
			log.warn("LOG warn - GET, id não encontrado: {} ", cpf);
			return obj.orElseThrow(() -> new ResourceNotFoundException("Não encontramos o usuário com id " + cpf));
		} else {
			User entity = obj
					.orElseThrow(() -> new ResourceNotFoundException("Não encontramos o usuário com id " + cpf));
			return entity;
		}
	}

	public Optional<User> findById(String cpf) {
		return userRepository.findByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public UserSameCpfDTO findBySameCpf(String cpf) {
		Optional<User> obj = userRepository.findByCpf(cpf);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Não encontramos a entidade"));
		return new UserSameCpfDTO(entity);
	}

	@Transactional
	public User updateend(String cpf, User userEdit) {

		User putUser = userRepository.getOne(cpf);
		putUser.setLogradouro(userEdit.getLogradouro());
		putUser.setBairro(userEdit.getBairro());
		putUser.setLocalidade(userEdit.getLocalidade());
		putUser.setUf(userEdit.getUf());
		putUser.setCep(userEdit.getCep());
		putUser.setNumero(userEdit.getNumero());
		putUser.setComplemento(userEdit.getComplemento());

		userRepository.save(putUser);

		userEventPublisher.publishUserEvent(putUser.convertToUserEventDto(), ActionType.UPDATE);

		return putUser;
	}

	@Transactional(readOnly = true)
	public Page<User> paginacaoQuerySpec(Specification<User> spec, Pageable pageable) {
		return userRepository.findAll(spec, pageable);
	}

	public boolean existsByCpf(String cpf) {
		return userRepository.existsByCpf(cpf);
	}

	@Transactional
	public User updateRoleType(User user) {
		user = save(user);

		return user;
	}
}
