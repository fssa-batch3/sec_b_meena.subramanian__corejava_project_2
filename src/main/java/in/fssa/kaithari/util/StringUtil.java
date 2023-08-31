package in.fssa.kaithari.util;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.User;

public class StringUtil {
	/**
	 * Validates a string input and rejects it if it's invalid.
	 *
	 * This static method checks whether the provided string input is null or empty
	 * after trimming. If the input is null or empty, it throws a
	 * ValidationException with an appropriate error message based on the provided
	 * Name parameter.
	 *
	 * @param input The string input to be validated.
	 * @param Name  A descriptive name or label for the input, used in the error
	 *              message.
	 * @throws ValidationException If the input is null or empty after trimming.
	 */
	public static void rejectIfInvalidString(String input, String Name) throws ValidationException {

		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(Name.concat(" cannot be null or empty"));
		}
	}
}
