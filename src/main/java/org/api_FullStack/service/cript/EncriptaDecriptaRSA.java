package org.api_FullStack.service.cript;

import org.springframework.stereotype.Component;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

@Component
public class EncriptaDecriptaRSA {

    public static final String ALGORITHM = "RSA";

    public static final String PATH_CHAVE_PRIVADA = "src/main/java/org/api_FullStack/service/cript/files/private.key";
    public static final String PATH_CHAVE_PUBLICA = "src/main/java/org/api_FullStack/service/cript/files/public.key";

    public static void geraChave() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();

            File chavePrivadaFile = new File(PATH_CHAVE_PRIVADA);
            File chavePublicaFile = new File(PATH_CHAVE_PUBLICA);

            if (chavePrivadaFile.getParentFile() != null) {
                chavePrivadaFile.getParentFile().mkdirs();
            }
            chavePrivadaFile.createNewFile();

            if (chavePublicaFile.getParentFile() != null) {
                chavePublicaFile.getParentFile().mkdirs();
            }
            chavePublicaFile.createNewFile();

            try (ObjectOutputStream chavePublicaOS = new ObjectOutputStream(new FileOutputStream(chavePublicaFile));
                 ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(new FileOutputStream(chavePrivadaFile))) {

                chavePublicaOS.writeObject(key.getPublic());
                chavePrivadaOS.writeObject(key.getPrivate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean verificaSeExisteChavesNoSO() {
        File chavePrivada = new File(PATH_CHAVE_PRIVADA);
        File chavePublica = new File(PATH_CHAVE_PUBLICA);

        return chavePrivada.exists() && chavePublica.exists();
    }

    public static byte[] criptografa(String texto, PublicKey chave) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    //Metodo para descriptografar uma String criptograda
    public static String decriptografa(byte[] texto, PrivateKey chave) {
        byte[] decryptedText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, chave);
            decryptedText = cipher.doFinal(texto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new String(decryptedText);
    }

    public String cript(String senha) throws Exception {
        String senhaCriptografada;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PUBLICA))) {
            final PublicKey chavePublica = (PublicKey) objectInputStream.readObject();
            final byte[] textoCriptografado = criptografa(senha, chavePublica);
            senhaCriptografada = Base64.getEncoder().encodeToString(textoCriptografado);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return senhaCriptografada;
    }

    public String descript(String criptPassword) throws Exception {
        String senhaDescriptografada;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA))) {
            final PrivateKey chavePrivada = (PrivateKey) objectInputStream.readObject();
            final byte[] textoCriptografado = Base64.getDecoder().decode(criptPassword);
            senhaDescriptografada = decriptografa(textoCriptografado, chavePrivada);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return senhaDescriptografada;
    }
    /*
    * Testar a criptografia.
    */
    /*
    public static void main(String[] args) {
        try {
            if (!verificaSeExisteChavesNoSO()) {
                geraChave();
            }

            final String msgOriginal = "string";
            String textoCriptografado;
            String textoDecriptografado;

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PUBLICA))) {
                final PublicKey chavePublica = (PublicKey) inputStream.readObject();
                final byte[] criptografado = criptografa(msgOriginal, chavePublica);
                textoCriptografado = Base64.getEncoder().encodeToString(criptografado);
            }

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA))) {
                final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
                final byte[] criptografado = Base64.getDecoder().decode(textoCriptografado);
                textoDecriptografado = decriptografa(criptografado, chavePrivada);
            }

            System.out.println("Mensagem Original: " + msgOriginal);
            System.out.println("Mensagem Criptografada: " + textoCriptografado);
            System.out.println("Mensagem Decriptografada: " + textoDecriptografado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
