package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import in.fssa.kaithari.exception.ValidationException;
//import in.fssa.kaithari.model.KaithariValidatorErrors;
import in.fssa.kaithari.model.User;
import in.fssa.kaithari.service.ProductService;
import in.fssa.kaithari.service.UserService;

public class TestCreateUser {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String generateRandomString(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}

		return stringBuilder.toString();
	}

	@Test
	public void testCreateUserWithValidInput() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setEmail(randomString.concat("@gmail.com"));
		newUser.setName("Vasu meen");
		newUser.setPassword("Subia@12345");

		assertDoesNotThrow(() -> {
			userService.create(newUser);
		});
	}

	@Test
	public void testCreateUserWithInvalidInput() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {

			userService.create(null);
		});
		String expectedMessage = "invalid user input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateUserWithEmailNull() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail(null);
		newUser.setName("Meena sub");
		newUser.setPassword("Meena@1234");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});

		String expectedMessage = "email cannot be null or empty";

		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithEmailEmpty() {

		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("");
		newUser.setName("Meena");
		newUser.setPassword("Meena@1234");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "email cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithInvalidEmail() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setEmail(randomString.concat("gmail.com"));
		newUser.setName("Meena");
		newUser.setPassword("Meena@1234");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Email does not match the pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithNameNull() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setEmail(randomString.concat("@gmail.com"));
		newUser.setName(null);
		newUser.setPassword("1234567456");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithNameEmpty() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setEmail(randomString.concat("@gmail.com"));
		newUser.setName("");
		newUser.setPassword("1234567456");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithInValidName() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setName("@@@@");
		newUser.setEmail(randomString.concat("@gmail.com"));
		newUser.setPassword("1234567456");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name does not match the pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithPasswordNull() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setEmail(randomString.concat("@gmail.com"));
		newUser.setName("Saranya");
		newUser.setPassword(null);
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithPasswordEmpty() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setEmail(randomString.concat("@gmail.com"));
		newUser.setName("Saranya");
		newUser.setPassword("");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateUserWithInvalidPassword() {

		UserService userService = new UserService();

		User newUser = new User();
		String randomString = generateRandomString(5);
		newUser.setEmail(randomString.concat("myna@gmail.com"));
		newUser.setName("Saranya");
		newUser.setPassword("sadfgnuabwD");
		newUser.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		
		
		String expectedMessage = "Password does not match the pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void findByEmail() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
		    User user = userService.findByEmail("ajun@gmaill.com");
		});
	}
	
	@Test
	void findByUpdateAddress() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
	 userService.updateAddress(14, "Meena", "65/98,madam Street.", "Tirunelveli",8695703584L, 627426, "Veeravanallur");
		});
	}
	
	@Test
	void findById() {

		UserService userService = new UserService();

		assertDoesNotThrow(() -> {
		    User user = userService.findById(14);
		});
	}
}
