package com.udara.encryption.aes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.udara.encryption.Encryptor;

public class AesEncryptor2 implements Encryptor {

	public String encrypt(String key, String initVector, String value) {
		try {
			byte[] encrypted = doCrypting(key, initVector, value, Cipher.ENCRYPT_MODE);

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private byte[] doCrypting(String key, String initVector, String value, int cryptMode)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(charFormat()));
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(charFormat()), getAlgo());

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(cryptMode, skeySpec, iv);

		byte[] encrypted = cipher.doFinal(value.getBytes());
		return encrypted;
	}

	private String getAlgo() {
		return "AES";
	}

	private String charFormat() {
		return "UTF-8";
	}

	public String decrypt(String key, String initVector, String encrypted) {
		try {
			byte[] decrypted = doCrypting(key, initVector, encrypted, Cipher.DECRYPT_MODE);
			return new String(decrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private String getPassKey() {
		return "Bar12345Bar12345";
	}

	private String getInitVector() {
		return "RandomInitVector";
	}

	@Override
	public String decryptToFile(File cryptFile) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean encyptToFile(String plainText, File cyperFile) throws IOException {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(cyperFile));
			out.write(encrypt(getPassKey(), getInitVector(), plainText));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
		return false;
	}

	// public static void main(String[] args) {
	// String key = "Bar12345Bar12345"; // 128 bit key
	// String initVector = "RandomInitVector"; // 16 bytes IV
	//
	// System.out.println(decrypt(key, initVector, encrypt(key, initVector,
	// "Hello World")));
	// }

}
