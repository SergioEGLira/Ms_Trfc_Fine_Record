package ApiUser.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ApiUser.entities.User;
import ApiUser.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Não achamos usuário com username: " + username));
		return UserDetailsImpl.build(user);
	}

	public UserDetails loadUserByCpf(String cpf) throws AuthenticationCredentialsNotFoundException {
		User user = userRepository.findByCpf(cpf).orElseThrow(
				() -> new AuthenticationCredentialsNotFoundException("Não achamos usuário com cpf: " + cpf));
		return UserDetailsImpl.build(user);
	}
}
