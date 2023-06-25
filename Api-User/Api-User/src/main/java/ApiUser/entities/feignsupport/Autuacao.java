package ApiUser.entities.feignsupport;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Autuacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID idApiAutuacao;
	private String placaDoCarro;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	@Enumerated(EnumType.STRING)
	private MultaStatus status;
	@ManyToOne
	@JoinColumn(name = "id_infracao")
	private Infracao infracao;
	
	@ManyToOne
	@JoinColumn(name = "id_automovel")
	private Automovel automovel;

	@ManyToOne
	@JoinColumn(name = "cpf")
	private UserPartialReplica userPartialReplica;

	public Autuacao() {
	}

	public Autuacao(UUID idApiAutuacao, String placaDoCarro, LocalDateTime createdAt, LocalDateTime updatedAt,
			MultaStatus status, Infracao infracao, Automovel automovel, UserPartialReplica userPartialReplica) {
		this.idApiAutuacao = idApiAutuacao;
		this.placaDoCarro = placaDoCarro;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
		this.infracao = infracao;
		this.automovel = automovel;
		this.userPartialReplica = userPartialReplica;
	}

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}
}