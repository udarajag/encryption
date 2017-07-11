package com.udara.encryption.aes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.udara.encryption.Encryptor;

public class AesEncryptor2Test {

	private static final String VALUE = "I AM A HAPPY PERSON";
	private static final String INIT_VECTOR = "RandomInitVector";
	private static final String KEY_TEST = "Bar12345Bar12345";
	private Encryptor encryptor;

	@Before
	public void setup() {
		encryptor = new AesEncryptor2();
	}

	@Test
	public void testEncrypt() {
		assertEquals("", encryptor.decrypt(KEY_TEST, INIT_VECTOR,
				encryptor.encrypt(KEY_TEST, INIT_VECTOR, VALUE)));
	}

	@Test
	public void testDecrypt() {
		assertEquals("", encryptor.decrypt(KEY_TEST, INIT_VECTOR,
				encryptor.encrypt(KEY_TEST, INIT_VECTOR, VALUE)));
	}
	
	
	
	

}
