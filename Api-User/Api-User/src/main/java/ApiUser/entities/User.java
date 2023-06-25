package ApiUser.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import ApiUser.dto.UserEventDTO;
import ApiUser.enums.UserType;
import lombok.AccessLevel;

//import com.user.dto.UserEventDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ToString
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	private String cpf;
	@NonNull
	@Column(nullable = false, length = 40)
	private String nome;
	@NonNull
	private String username;
	@NonNull
	@Column(nullable = false, unique = true, length = 60)
	private String email;
	@NonNull
	private String logradouro;
	@Column(nullable = false, length = 4)
	@NonNull
	private Long numero;
	@Column(length = 20)
	@NonNull
	private String complemento;
	@NonNull
	private String bairro;
	@NonNull
	private String localidade;
	@NonNull
	private String uf;
	@Column(nullable = false, length = 9)
	@NonNull
	private String cep;
	@Column(nullable = false, length = 15)
	@NonNull
	private String telefone;
	@NonNull
	private String password;
	@Column(nullable = false)
	@NonNull
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TB_USERS_ROLES", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Setter(AccessLevel.NONE)
	private Set<Role> roles = new HashSet<>();

	public Specification<User> toSpec() {
		return (root, query, builder) -> {
			List<Predicate> predicate = new ArrayList<>();
			if (StringUtils.hasText(nome)) {
				Path<String> stringNome = root.<String>get("nome");
				Predicate predicateNome = builder.like(stringNome, "%" + nome + "%");
				predicate.add(predicateNome);

			} else if (StringUtils.hasText(localidade)) {
				Path<String> stringLocalidade = root.<String>get("localidade");
				Predicate predicateLocalidade = builder.like(stringLocalidade, "%" + localidade + "%");
				predicate.add(predicateLocalidade);
			}
			return builder.and(predicate.toArray(new Predicate[0]));
		};
	}

	public UserEventDTO convertToUserEventDto() {
		var userEventDto = new UserEventDTO();
		BeanUtils.copyProperties(this, userEventDto);

		return userEventDto;
	}

}
