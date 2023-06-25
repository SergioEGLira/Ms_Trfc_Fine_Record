package com.TrafficFineRecord.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "tb_infracao")
public class Infracao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	private Long codigoDaInfracao;
	@Column(nullable = false, unique = true, length = 120)
	@NonNull
	private String infracaoDeTransito;
	@Column(nullable = false, length = 40)
	@NonNull
	private String pontosNaCarteira;
	@Column(nullable = false, length = 8)
	@NonNull
	private Double valorDaMulta;

	@JsonIgnore
	@OneToMany(mappedBy = "infracao")
	@Setter(AccessLevel.NONE)
	private List<Autuacao> autuacoes = new ArrayList<>();

	public Specification<Infracao> toSpec() {
		return (root, query, builder) -> {
			List<Predicate> predicate = new ArrayList<>();
			if (StringUtils.hasText(pontosNaCarteira)) {
				Path<String> stringPontosNaCarteira = root.<String>get("pontosNaCarteira");
				Predicate predicatePontosNaCarteira = builder.like(stringPontosNaCarteira,
						"%" + pontosNaCarteira + "%");
				predicate.add(predicatePontosNaCarteira);

			}
			return builder.and(predicate.toArray(new Predicate[0]));

		};
	}
}
