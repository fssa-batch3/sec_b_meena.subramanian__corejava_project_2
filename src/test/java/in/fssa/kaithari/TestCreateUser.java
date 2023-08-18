package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.KaithariValidatorErrors;
import in.fssa.kaithari.model.User;
import in.fssa.kaithari.service.UserService;

public class TestCreateUser {
	
	@Test
	 void testCreateUserWithValidInput() {
		
		UserService userService = new UserService();

		User newUser = new User();
		newUser.setEmail("Vasu@gmail.com");
		newUser.setName("Vasu");
		newUser.setPassword("Subi@123");
		assertDoesNotThrow(() ->{
			userService.create(newUser);
		});
	}
	
	@Test    
	 void testCreateUserWithInvalidInput(){
		try {
			UserService userService = new UserService();
			userService.create(null);
		}
		catch(ValidationException ex ) {
			Assertions.assertEquals(KaithariValidatorErrors.USER_NAME, ex.getMessage());
		} 
		catch(Exception ex) {
			Assertions.assertEquals(KaithariValidatorErrors.USER_NAME,ex.getMessage());
		}
	}
		@Test
		 void testCreateUserWithEmailNull() {
			
			
			UserService userService = new UserService();
		
			User newUser = new User();
			newUser.setEmail(null);
			newUser.setName("Meena");
			newUser.setPassword("1234567456");
			newUser.toString();
				
			Exception exception = assertThrows(ValidationException.class, () ->{
			userService.create(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
		@Test
		 void testCreateUserWithEmailEmpty() {
			
			UserService userService = new UserService();
			
			User newUser = new User();
			newUser.setEmail(" ");
			newUser.setName("Meena");
			newUser.setPassword("1234567456");
			newUser.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
			userService.create(newUser);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
		@Test
		void testCreateUserWithInvalidEmail() {
			
			UserService userService = new UserService();
			
			User newUser = new User();
			newUser.setEmail("sarangmail.com");
			newUser.setName("Meena");
			newUser.setPassword("1234567456");
			newUser.toString();
				
			Exception exception = assertThrows(ValidationException.class, () ->{
			userService.create(newUser);
		});
		String expectedMessage = "Email does not match the pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
		
		@Test
		 void testCreateUserWithNameNull() {
			
			UserService userService = new UserService();
			
			User newUser = new User();
			newUser.setEmail("sara@gmail.com");
			newUser.setName(null);
			newUser.setPassword("1234567456");
			newUser.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
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
			newUser.setEmail("sara@gmail.com");
			newUser.setName("");
			newUser.setPassword("1234567456");
			newUser.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
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
			newUser.setName("@@@@");
			newUser.setEmail("suji@gmail.com");
			newUser.setPassword("1234567456");
			newUser.toString();
				
			Exception exception = assertThrows(ValidationException.class, () ->{
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
			newUser.setEmail("Saran@gmail.com");
			newUser.setName("Saranya");
			newUser.setPassword(null);
			newUser.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
		
		@Test
		 void testCreateUserWithPasswordEmpty() {
			
			UserService userService = new UserService();
			
			User newUser = new User();
			newUser.setEmail("Saran@gmail.com");
			newUser.setName("Saranya");
			newUser.setPassword("");
			newUser.toString();
				
				Exception exception = assertThrows(ValidationException.class, () ->{
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
		@Test
		 void testCreateUserWithInvalidPassword() {
			
			UserService userService = new UserService();
			
			User newUser = new User();
			newUser.setEmail("myna@gmail.com");
			newUser.setName("Saranya");
			newUser.setPassword("sadfgnuabwD");
			newUser.toString();
				
			Exception exception = assertThrows(ValidationException.class, () ->{
			userService.create(newUser);
		});			
		String expectedMessage = "Password does not match the pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	} 
		
}
