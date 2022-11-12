package homework.triple.domain.model;

import homework.triple.domain.entity.MemberEntity;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
@ToString
public class Member implements UserDetails {

	private Long id;
	private String userName;
	private String password;
	private Timestamp registeredAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;

	public static Member fromEntity(MemberEntity entity) {
		return new Member(
			entity.getId(),
			entity.getUserName(),
			entity.getPassword(),
			entity.getRegisteredAt(),
			entity.getUpdatedAt(),
			entity.getRemovedAt()
		);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.deletedAt == null;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.deletedAt == null;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.deletedAt == null;
	}

	@Override
	public boolean isEnabled() {
		return this.deletedAt == null;
	}
}
