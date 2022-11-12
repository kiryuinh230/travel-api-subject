package homework.triple.utils;

public class ClassUtils {

	private ClassUtils() {
	}

	public static <T> T getSafeCastInstance(Object o, Class<T> clazz) {
		return clazz != null && clazz.isInstance(o) ? clazz.cast(o) : null;
	}
}
