package homework.triple.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
	private String resultCode;
	private T result;

	public static CommonResponse<Void> error(String errorCode) {
		return new CommonResponse<>(errorCode, null);
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
