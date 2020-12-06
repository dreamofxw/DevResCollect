package com.example.libjava2;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    public static String AESKey = "ahahdasfasdfasdfasdfasdfasdf";

    public static void main(String[] args) {
        byte[] bytes = decryptData("abc".getBytes());
        System.out.println("bytes ="+bytes);
    }

    public static byte[] decryptData(byte[] decryptdata){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(AESKey.getBytes(),"AES");
            Cipher aes = Cipher.getInstance("AES");
            aes.init(Cipher.ENCRYPT_MODE,secretKeySpec,new IvParameterSpec(new byte[aes.getBlockSize()]));
            byte[] bytes = aes.doFinal(decryptdata);
            return bytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
