package es.cristian.modelo.entidad;

import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Encriptador {
	
	private SecretKey clave;
	private Cipher cifrador;
	
	/*
	 * Se crea el constructor, para asegurar la correcta inicialziación de los
	   atributos de la clase, necesarios en la lógica.
	 */
	
	public Encriptador() {
		try {
			//Generador de claves simétricas
			KeyGenerator generador = KeyGenerator.getInstance("AES");
			//se genera la clave
			 clave = generador.generateKey();
			//Objeto que permite encriptar, se configura en modo encriptar.
			 cifrador = Cipher.getInstance("AES");
		}catch(GeneralSecurityException gse){
			System.out.println("Algo ha fallado..." + gse.getMessage());
			gse.printStackTrace();
			
		}
		
	}
	
			
		public String encriptar (String mensajeOriginal) {
			String mensajeCifrado = null;
			try {
				
				
				cifrador.init(Cipher.ENCRYPT_MODE, clave);
				
				byte[] bytesMensajeOriginal = mensajeOriginal.getBytes();
				
				byte[] byteMensajeCifrado = cifrador.doFinal(bytesMensajeOriginal);
				
				mensajeCifrado = Base64.getEncoder().encodeToString(byteMensajeCifrado);
				
				//Esto lo guardo en memoria??? en DAO??
			}catch(GeneralSecurityException gse){
				System.out.println("Algo ha fallado..." + gse.getMessage());
				gse.printStackTrace();
				
			}
			return  mensajeCifrado;
		}
		
		
		public String desencriptar (String mensajeCifrado) {
			
			//Este emnsaje cifrado lo tendra que elegir el usuario desde la BBDD
			//a través del controldor.
			String mensajeDescifrado = null;
			
			try {
				cifrador = Cipher.getInstance("AES");
				cifrador.init(Cipher.DECRYPT_MODE, clave);
				
				//byte[] bytesMensajeCifrado = mensajeCifrado.getBytes();
				/*
				 * Se realiza con Base64 ya que la conversión entre String y bytes
				 * no preserva siempre bien los datos -> salta una excepción.
				 * Base64 está diseñada para representar datos binarios en cadenas de caracteres.
				 */
				byte[] bytesMensajeCifrado = Base64.getDecoder().decode(mensajeCifrado);
				byte[] bytesMensajeDescifrado = cifrador.doFinal(bytesMensajeCifrado);
				
				mensajeDescifrado = new String(bytesMensajeDescifrado);
			
			}catch(GeneralSecurityException gse){
				System.out.println("Algo ha fallado..." + gse.getMessage());
				gse.printStackTrace();
			}
			return mensajeDescifrado;
		}
		
		//Usar mejor base 64 que String??<
}
			
			