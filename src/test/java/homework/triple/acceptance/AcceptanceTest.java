package homework.triple.acceptance;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseClean databaseClean;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		databaseClean.cleanDatabase();
	}
}
