package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Seller;
import in.fssa.kaithari.service.SellerService;

public class TestCreateSeller {
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
	public void testCreateSellerWithValidInput() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setEmail(randomString.concat("@example.com"));
	    newSeller.setName("Meenu");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("Password@123");

	    assertDoesNotThrow(() -> {
	        sellerService.create(newSeller);
	    });
	}
	
	@Test
	public void testCreateSellerWithInvalidInput() {
	    SellerService sellerService = new SellerService();
	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(null);
	    });
	    String expectedMessage = "Invalid seller input";
	    String actualMessage = exception.getMessage();
	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateSellerWithEmailNull() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    newSeller.setEmail(null);
	    newSeller.setName("Meenu");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("Password@123");
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });

	    String expectedMessage = "Email cannot be null or empty";

	    String actualMessage = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateSellerWithEmailEmpty() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    newSeller.setEmail("");
	    newSeller.setName("Meenu");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("Password@123");
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });

	    String expectedMessage = "Email cannot be null or empty";

	    String actualMessage = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(expectedMessage.equals(actualMessage));
	}

	
	@Test
	void testCreateSellerWithInvalidEmail() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setEmail(randomString.concat("gmail.com"));
	    newSeller.setName("JMeenu");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("Password@123");
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });
	    
	    String expectedMessage = "Email does not match the pattern";
	    String actualMessage = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	void testCreateSellerWithNameNull() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setEmail(randomString.concat("@example.com"));
	    newSeller.setName(null);
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("Password@123");
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });

	    String expectedMessage = "Name cannot be null or empty";
	    String actualMessage = exception.getMessage();
	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateSellerWithNameEmpty() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setEmail(randomString.concat("@example.com"));
	    newSeller.setName("");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("Password@123");
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });

	    String expectedMessage = "Name cannot be null or empty";
	    String actualMessage = exception.getMessage();
	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateSellerWithInvalidName() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setName("@@@@");
	    newSeller.setEmail(randomString.concat("@example.com"));
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("Password@123");
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });
	    
	    String expectedMessage = "Name does not match the pattern";
	    String actualMessage = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateSellerWithPasswordNull() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setEmail(randomString.concat("@example.com"));
	    newSeller.setName("Meenu");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword(null);
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });

	    String expectedMessage = "Password cannot be null or empty";
	    String actualMessage = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateSellerWithPasswordEmpty() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setEmail(randomString.concat("@example.com"));
	    newSeller.setName("Meenu");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("");
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });

	    String expectedMessage = "Password cannot be null or empty";
	    String actualMessage = exception.getMessage();
	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateSellerWithInvalidPassword() {
	    SellerService sellerService = new SellerService();

	    Seller newSeller = new Seller();
	    String randomString = generateRandomString(5);
	    newSeller.setEmail(randomString.concat("@example.com"));
	    newSeller.setName("Meenu");
	    newSeller.setProofImage("proof.jpg");
	    newSeller.setIdImage("id.jpg");
	    newSeller.setPassword("sadfgnuabwD"); // Invalid password format
	    newSeller.toString();

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        sellerService.create(newSeller);
	    });

	    String expectedMessage = "Password does not match the pattern";
	    String actualMessage = exception.getMessage();
	    System.out.println(actualMessage);
	    assertTrue(expectedMessage.equals(actualMessage));
	}


}
