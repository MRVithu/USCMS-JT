package com.vithu.uscms.others;


import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author S.Sugashan
 * @CreatedOn 28th January 2018
 * @Purpose Service for data encryption
 */

public class DataEncryption {
	 
	public String encrptyMe(String data) {
		return BCrypt.hashpw(data, BCrypt.gensalt(12));
	}

	public boolean checkDataMatch(String data, String generatedHashedString) {
		
		return BCrypt.checkpw(data, generatedHashedString);
	}
}
