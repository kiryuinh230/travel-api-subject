package homework.triple.domain.entity;

import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "members")
@Getter
@Setter
@SQLDelete(sql = "UPDATED \"members\" SET removed_at = NOW() where id = ?")
@Where(clause = "removed_at is NULL")
public class MemberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name", unique = true)
	private String userName;

	private String password;

	@Column(name = "register_at")
	private Timestamp registeredAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "removed_at")
	private Timestamp removedAt;

	@PrePersist
	void registeredAt() {
		this.registeredAt = Timestamp.from(Instant.now());
	}

	@PreUpdate
	void updateAt() {
		this.updatedAt = Timestamp.from(Instant.now());
	}

	public static MemberEntity of(String userName, String password) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setUserName(userName);
		memberEntity.setPassword(password);

		return memberEntity;
	}
}
