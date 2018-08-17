package com.mgmsec.HackMyTeeth.HackMyTeeth.service;

public interface CryptoService {
	String encodeHmacSHA(String key, String data);
	byte[] deriveKey(String password, byte[] salt, int keyLen);
	String encryptAES(byte[] key, String plainText);
	String decryptAES(byte[] key,String cipherText);
}
