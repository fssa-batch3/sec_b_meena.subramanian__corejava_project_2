package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.User;
import in.fssa.kaithari.service.UserService;

 class TestUpdateUser {
	@Test
	 void testUpdateUserName() {
		
		UserService userService = new UserService();

		User updateUser = new User();
		
		updateUser.setId(2);
		updateUser.setName("mint");
		assertDoesNotThrow(() ->{
			userService.updateName(updateUser.getId(),updateUser.getName());
		});
	}
	
}
