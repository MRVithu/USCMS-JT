package com.vithu.uscms.others;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author M.Vithusanth
 * @CreatedOn 20th April 2018
 * @Purpose To Encrypt & Decrypt data
 */

public class Encrypter {
	
	public static final String key = "hehehehhhhhhhhhh"; // 128 bit key

	public static String encrypt( String unencryptedString ) throws EncryptionException{
		 String encryptedText="";
		try	{		
			 Key aesKey = new SecretKeySpec(key.getBytes("UTF8"), "AES");
			 Cipher cipher = Cipher.getInstance("AES");
	            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(unencryptedString.getBytes());
 
            encryptedText = Base64.getEncoder().encodeToString(encrypted);
            return encryptedText;
            
		}
		catch (Exception e)	{
			throw new EncryptionException( e );
		}
	}

	public static String decrypt( String encryptedString ) throws EncryptionException	{
		if ( encryptedString == null || encryptedString.trim().length() <= 0 )
				throw new IllegalArgumentException( "encrypted string was null or empty" );

		try	{
			 Key aesKey = new SecretKeySpec(key.getBytes("utf-8"), "AES");
			 Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			//System.out.println("After trimming:"+ encryptedString.replaceAll(" ", "+"));
            return new String(cipher.doFinal(Base64.getMimeDecoder().decode(encryptedString.replaceAll(" ", "+"))));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new EncryptionException( e );
		}
	}

	
	private static String bytes2String( byte[] bytes )	{
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
		{
			stringBuffer.append( (char) bytes[i] );
		}
		return stringBuffer.toString();
	}

	public static class EncryptionException extends Exception {
		public EncryptionException( Throwable t ) {
			super( t );
		}
	}
}

