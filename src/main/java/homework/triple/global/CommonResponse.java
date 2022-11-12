package homework.triple.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
	private String resultCode;
	private T result;

	public static CommonResponse<Void> error(final String errorCode) {
		return new CommonResponse<>(errorCode, null);
	}

	public static CommonResponse<String> error(final String errorCode, final String errorMessage) {
		return new CommonResponse<>(errorCode, errorMessage);
	}

	public static <T> CommonResponse<T> success() {
		return new CommonResponse<T>("success", null);
	}

	public static <T> CommonResponse success(T result) {
		return new CommonResponse<>("success", result);
	}

	public String toStream() {
		if (result == null) {
			return "{" +
				"\" resultCode\":" + "\"" + resultCode + "\"," +
				"\" result\":" + null + "}";
		}
		return "{" +
			"\" resultCode\":" + "\"" + resultCode + "\"," +
			"\" result\":" + result + "\"" + "}";
	}
}
