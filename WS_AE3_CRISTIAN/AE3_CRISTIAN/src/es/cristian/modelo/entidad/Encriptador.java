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
	 * COntrusctor
	 * 
	 * COntine las instancias y la lógica principales para la creación
	 * de las claves simétrcias y su desciframiento.
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
	
		//Método específico para la encriptación.
			
		public String encriptar (String mensajeOriginal) {
			String mensajeCifrado = null;
			try {
				
				
				cifrador.init(Cipher.ENCRYPT_MODE, clave);
				
				byte[] bytesMensajeOriginal = mensajeOriginal.getBytes();
				
				byte[] byteMensajeCifrado = cifrador.doFinal(bytesMensajeOriginal);
				
				mensajeCifrado = Base64.getEncoder().encodeToString(byteMensajeCifrado);
				
				
			}catch(GeneralSecurityException gse){
				System.out.println("Algo ha fallado..." + gse.getMessage());
				gse.printStackTrace();
				
			}
			return  mensajeCifrado;
		}
		
		//Método específico para la deseencriptación.
		public String desencriptar (String mensajeCifrado) {
			
			//Este emnsaje cifrado lo tendra que elegir el usuario desde la BBDD
			//a través del controldor.
			String mensajeDescifrado = null;
			
			try {
				cifrador = Cipher.getInstance("AES");
				cifrador.init(Cipher.DECRYPT_MODE, clave);
				
				
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
		
		
}
			
			