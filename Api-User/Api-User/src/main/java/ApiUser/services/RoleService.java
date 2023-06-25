package ApiUser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ApiUser.entities.Role;
import ApiUser.enums.RoleType;
import ApiUser.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> findByRoleName(RoleType roleType) {
		return roleRepository.findByRoleName(roleType);
	}

}
