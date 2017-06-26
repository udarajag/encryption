package com.udara.encryption;

import java.io.File;
import java.io.IOException;

public interface Encryptor {

	String decryptToFile(File cryptFile) throws IOException;

	boolean encyptToFile(String plainText, File cyperFile) throws IOException;

	String encrypt(String key, String initVector, String value);

	String decrypt(String key, String initVector, String encrypted);
}
