package ApiUser.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ApiUser.entities.Role;
import ApiUser.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Optional<Role> findByRoleName(RoleType name);

}
