package com.TrafficFineRecord.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.TrafficFineRecord.enums.MultaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ToString
@Table(name = "tb_autuacao")
public class Autuacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idApiAutuacao;
	@Column(nullable = false, length = 8)
	@NonNull
	private String placaDoCarro;
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@Setter(AccessLevel.NONE)
	@NonNull
	private LocalDateTime createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	@Setter(AccessLevel.NONE)
	@NonNull
	private LocalDateTime updatedAt;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NonNull
	private MultaStatus status;

	@ManyToOne
	@JoinColumn(name = "id_infracao")
	@NonNull
	private Infracao infracao;

	@ManyToOne
	@JoinColumn(name = "id_automovel")
	@NonNull
	private Automovel automovel;

	@ManyToOne
	@JoinColumn(name = "cpf")
	@NonNull
	private UserPartialReplica userPartialReplica;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Specification<Autuacao> toSpec() {
		return (root, query, builder) -> {
			List<Predicate> predicate = new ArrayList<>();
			if (StringUtils.hasText(placaDoCarro)) {
				Path<String> string = root.<String>get("placaDoCarro");
				Predicate pred = builder.like(string, "%" + placaDoCarro + "%");
				predicate.add(pred);
			}

			return builder.and(predicate.toArray(new Predicate[0]));
		};
	}

	public static Specification<Autuacao> findUserId(final String cpf) {
		return (root, query, cb) -> {
			query.distinct(true);
			Root<Autuacao> autuacao = root;
			Root<UserPartialReplica> userPartialReplica = query.from(UserPartialReplica.class);
			Expression<Collection<Autuacao>> usersAutuacoes = userPartialReplica.get("autuacoes");
			return cb.and(cb.equal(userPartialReplica.get("cpf"), cpf), cb.isMember(autuacao, usersAutuacoes));
		};
	}

}
