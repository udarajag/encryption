package com.udara.encryption.aes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.udara.encryption.Encryptor;

public class AesEncryptor implements Encryptor {

	private static final String AES = "AES";

	@Override
	public boolean encyptToFile(String plainText, File cyperFile) throws IOException {
		FileOutputStream outputStream = null;
		try {
			byte[] inputBytes = Base64.getDecoder().decode(plainText);
			byte[] outputBytes = doCrypto(inputBytes, Cipher.ENCRYPT_MODE, AES);

			cyperFile.createNewFile();
			outputStream = new FileOutputStream(cyperFile);
			outputStream.write(outputBytes);

		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException | InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException | BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			outputStream.close();

		}

		return true;
	}

	private byte[] doCrypto(byte[] inputBytes,int cryptoMode, String algo) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance(algo);
		cipher.init(cryptoMode, getKey());
		
		byte[] outputBytes = cipher.doFinal(inputBytes);
		return outputBytes;
	}

	public static SecretKey getKey() throws NoSuchAlgorithmException {

		byte[] decodedKey = Base64.getDecoder().decode("IQwThSAsDy2NYIeEq4LoSA==");
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, AES);
		return originalKey;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		for(int i=0;i<10;i++){
			String encodedKey = Base64.getEncoder().encodeToString(getKey().getEncoded());
			System.out.println(encodedKey);
		}
	}

	@Override
	public String decryptToFile(File cryptFile) throws IOException {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(cryptFile);
			byte[] inputBytes = new byte[(int) cryptFile.length()];
			inputStream.read(inputBytes);
			byte[] outputBytes = doCrypto(inputBytes, Cipher.DECRYPT_MODE, AES);
			return Base64.getEncoder().encodeToString(outputBytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return null;
	}

	@Override
	public String encrypt(String key, String initVector, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String key, String initVector, String encrypted) {
		// TODO Auto-generated method stub
		return null;
	}

}
