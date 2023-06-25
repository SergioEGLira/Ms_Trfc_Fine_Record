package com.TrafficFineRecord.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

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
@Table(name = "tb_automovel")
public class Automovel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	private String placa;
	@Column(nullable = false, unique = true, length = 8)
	@NonNull
	private Long renavam;
	@Column(nullable = false, length = 20)
	@NonNull
	private String marca;
	@Column(nullable = false, length = 50)
	@NonNull
	private String modelo;
	@Column(nullable = false, length = 4)
	@NonNull
	private Long ano;
	@Column(nullable = false, length = 15)
	@NonNull
	private String cor;

	public Specification<Automovel> toSpec() {
		return (root, query, builder) -> {
			List<Predicate> predicate = new ArrayList<>();
			if (StringUtils.hasText(marca)) {
				Path<String> string = root.<String>get("marca");
				Predicate pred = builder.like(string, "%" + marca + "%");
				predicate.add(pred);
			}
			return builder.and(predicate.toArray(new Predicate[0]));
		};
	}
}
