package com.vithu.uscms.session;

import java.security.SecureRandom;

import com.vithu.uscms.others.Encrypter;
import com.vithu.uscms.others.Encrypter.EncryptionException;
import com.vithu.uscms.others.JsonFormer;


/**
* @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose Token generator & validate token for logged in users activity
 */
public class TokenManager {

	public static String getToken() {

		SecureRandom random = new SecureRandom();
		String token = "";
		long longToken = Math.abs(random.nextLong());
		token = Long.toString(longToken, 16);
		return token;
	}

	public static CurrentUser validateToken(String token) {
		//System.out.println("From JSP="+token);
		CurrentUser currentUser = new CurrentUser();

		try {
			String userString = Encrypter.decrypt(token);
			currentUser = (CurrentUser) JsonFormer.deForm(userString);
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
		return currentUser;
	}
}
