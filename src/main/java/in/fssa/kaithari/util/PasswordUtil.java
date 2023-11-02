package in.fssa.kaithari.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
public static String encryptPassword(String password) {
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
		
	}
}
