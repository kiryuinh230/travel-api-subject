package homework.triple.service;

import homework.triple.domain.entity.MemberEntity;
import homework.triple.domain.exception.MemberNotFoundException;
import homework.triple.domain.model.Member;
import homework.triple.global.error.ErrorCode;
import homework.triple.global.exception.TravelApplicationException;
import homework.triple.repository.MemberEntityRepository;
import homework.triple.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberEntityRepository memberEntityRepository;
	private final BCryptPasswordEncoder encoder;

	@Value("${jwt.secret-key}")
	private String secretKey;

	@Value("${jwt.token.expired-time-ms}")
	private Long expiredTimeMs;

	public Member loadUserByUsername(String username) {
		return memberEntityRepository.findByUserName(username)
			.map(Member::fromEntity)
			.orElseThrow(() -> new MemberNotFoundException(ErrorCode.USER_NOT_FOUND, "user not found"));
	}

	@Transactional
	public Long join(final String username, final String password) {
		memberEntityRepository.findByUserName(username).ifPresent(it -> {
			throw new TravelApplicationException(ErrorCode.DUPLICATED_USERNAME, String.format("duplicate username is %s", username));
		});

		MemberEntity saveMember = memberEntityRepository.save(MemberEntity.of(username, password));

		return Member.fromEntity(saveMember).getId();
	}

	public String login(final String username, final String password) {
		MemberEntity memberEntity = memberEntityRepository.findByUserName(username)
			.orElseThrow(() -> new TravelApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not found", username)));

		if (!encoder.matches(memberEntity.getPassword(), encoder.encode(password))) {
			throw new TravelApplicationException(ErrorCode.WRONG_PASSWORD, "");
		}

		return JwtTokenUtils.generateToken(username, secretKey, expiredTimeMs);
	}
}
