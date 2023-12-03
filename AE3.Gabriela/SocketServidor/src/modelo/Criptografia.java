package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import modelo.entidad.HashConversor;

public class Criptografia {

	public static <Usuarios> void contraseña(Socket socketAlCliente) throws IOException, NoSuchAlgorithmException {
		
		InputStreamReader entrada = null;
		PrintStream salida = null;
		
		try {
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		BufferedReader bf = new BufferedReader(entrada);
		String cadenaEntrada = null;
		try {
			cadenaEntrada = bf.readLine();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		

			byte[] hashPassword = cadenaEntrada.getBytes();
			
			    ArrayList<modelo.entidad.User> userList = new ArrayList<modelo.entidad.User>();
			
				userList.add(new modelo.entidad.User("usuario1", HashConversor.conversor("contraseña1")));
				userList.add(new modelo.entidad.User("usuario2", HashConversor.conversor("contraseña2")));
				userList.add(new modelo.entidad.User("usuario3", HashConversor.conversor("contraseña3")));
			
			
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(hashPassword);
			
			byte[] resumen = md.digest();
			
			//String mensaje = new String(resumen);
			String mensajeHashB64 = Base64.getEncoder().encodeToString(resumen);
			
			salida = new PrintStream(socketAlCliente.getOutputStream());
	
			salida.println(mensajeHashB64);
			
	}
			
	
	
	
	public static  void incriptar(Socket socketAlCliente) throws NoSuchAlgorithmException, GeneralSecurityException{
		InputStreamReader entrada = null;
		PrintStream salida = null;
		
		try {
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		BufferedReader bf = new BufferedReader(entrada);
		String cadenaEntrada = null;
		try {
			cadenaEntrada = bf.readLine();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		KeyGenerator generador = null;
		try {
			generador = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		SecretKey FraseEncriptada = generador.generateKey();
		Cipher cifrador = null;
		
		try {
			cifrador = Cipher.getInstance("AES");

			cifrador.init(Cipher.ENCRYPT_MODE, FraseEncriptada);
		
	
			salida = new PrintStream(socketAlCliente.getOutputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		byte[] bytesFrase = cadenaEntrada.getBytes();
		byte[] bytesMensajeCifrado = cifrador.doFinal(bytesFrase);
		String mensajeCifrado = new String(bytesMensajeCifrado);
		salida.println(mensajeCifrado);
	
		
  }
	
	public static void desencriptar(Socket socketAlCliente) throws NoSuchAlgorithmException, GeneralSecurityException, IOException {
		InputStreamReader entrada = null;
		PrintStream salida = null;
		
		try {
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		BufferedReader bf = new BufferedReader(entrada);
		String cadenaEntrada = null;
		try {
			cadenaEntrada = bf.readLine();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		KeyGenerator generador = null;
		try {
			generador = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		SecretKey FraseEncriptada = generador.generateKey();
		Cipher cifrador = null;

	
				cifrador = Cipher.getInstance("AES");
			
				cifrador.init(Cipher.ENCRYPT_MODE, FraseEncriptada);

			    salida = new PrintStream(socketAlCliente.getOutputStream());
	
			
				System.out.println("----- DESENCRPTANDO FRASE -----");
				byte[] bytesFrase1 = cadenaEntrada.getBytes();
				byte[] bytesMensajeCifrado1 = cifrador.doFinal(bytesFrase1);
				Cipher descifrador = Cipher.getInstance("AES");
	
					descifrador.init(Cipher.DECRYPT_MODE, FraseEncriptada);
				
				byte[] bytesMensajeDescifrado;
			
					bytesMensajeDescifrado = descifrador.doFinal(bytesMensajeCifrado1);
			
				System.out.println("Mensaje Descifrado: " + new String(bytesMensajeDescifrado));
				
				
			
		String bytesMensajeDescifrado1 = null;
		String mensajeCifrado = new String(bytesMensajeDescifrado);
		salida.println(mensajeCifrado);
	}
}