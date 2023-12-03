package es.cristian.modelo.entidad;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/*
 * Class HashConversor
 * 
 * Mediante esta clase y su método estático, se realiza el resumen hash.
 * Se llama desde la clase Controlador, para convertir directamente la password
 * en su resumen. ASí como realizar la Verificación de accedo del usuario.
 */
public class HashConversor {
	
	public static String conversor (String password) throws NoSuchAlgorithmException {
		
		byte[] hashPassword = password.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(hashPassword);
		
		byte[] resumen = md.digest();
		
		//String mensaje = new String(resumen);
		String mensajeHashB64 = Base64.getEncoder().encodeToString(resumen);
		
		return mensajeHashB64;
		
	}
}
