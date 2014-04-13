package com.vronin.crossftpdecryptor;

import org.jets3t.service.security.EncryptionUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.*;

public class CrossFTPDecryptor {

	public static void decryptFile(String finame, String foname, EncryptionUtil encryptionUtil) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException
	{
		FileInputStream fis = new FileInputStream(finame);
		FileOutputStream fos = new FileOutputStream(foname);
		CipherInputStream cipherInputStream = encryptionUtil.decrypt(fis);
		
		IOUtils.copy(cipherInputStream, fos);
		
		fis.close();
		fos.close();
	}
	
	public static void main(String [] args)
	{
		String algorithm = "PBEWITHSHA256AND256BITAES-CBC-BC";
		String version = "2";
		
		try {
            String finame = "<FileToDecrypt>";
            String foname = "<FileToSave>";
            String encryptionKey = "<encryptionPassword>";
			EncryptionUtil encryptionUtil = new EncryptionUtil(encryptionKey, algorithm, version);
            decryptFile(finame, fonamem, encryptionUtil);
			
			
		} catch (Exception e)
		{
			System.out.println("Ughh.. something went wrong with decryption: "+e.getMessage());
		}
		
	}
}
