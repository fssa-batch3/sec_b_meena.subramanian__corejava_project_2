package in.fssa.kaithari.util;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.User;

public class StringUtil {
	/**
	 * 
	 * @param input
	 * @param Name
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidString(String input, String Name) throws ValidationException {

		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(Name.concat(" cannot be null or empty"));
		}
	}
}
