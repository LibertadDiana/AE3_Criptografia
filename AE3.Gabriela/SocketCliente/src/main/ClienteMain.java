package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class ClienteMain extends Thread {
	
	public static final int PUERTO = 2023;
	public static final String IP_SERVER = "localhost";

	public static void main (String[] args) throws NoSuchAlgorithmException, IOException {
		
		System.out.println("        APLICACIÓN CLIENTE         ");
		System.out.println("-----------------------------------");
		
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		Socket socketAlServidor = new Socket();
		System.out.println("Intentando conectar al servidor...");
		try {
			socketAlServidor.connect(direccionServidor);
			System.out.println("Conexion establecida");
			System.out.println("-----------------------------------");
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	
		        String frase = null; 
				int opcion;
				String cadenaEntrada = null;
				InputStreamReader entrada = null;
				PrintStream salida = null;
				BufferedReader bf = null;
				

			Scanner sc = new Scanner(System.in);
		
			
			
			System.out.println("Por favor ingresa tu usuario y contraseña");
			
			
			
			int oportunidades = 0;
			boolean autenticado = false;
			while(oportunidades < 3 && !autenticado) {
				
				System.out.println("Introduce tu usuario: ");
				String user = sc.nextLine();
				System.out.println("introduce tu contraseña: ");
				String pswd = sc.nextLine();
				
				
				System.out.println("BIENVENIDO AL MENU " + user + "\n");
				
				do {
					
					KeyGenerator generador = null;
					try {
						generador = KeyGenerator.getInstance("AES");
					} catch (NoSuchAlgorithmException e) {
						
						e.printStackTrace();
					}
					
					SecretKey FraseEncriptada = generador.generateKey();
				
					System.out.println("1. Encriptar Frase \n2. Desencriptar Frase \n3.Salir del Programa");
					System.out.println("\nEsconge una opción: ");
					opcion = Integer.parseInt(sc.nextLine());
					Cipher cifrador = null;
					try {
						cifrador = Cipher.getInstance("AES");
					} catch (NoSuchAlgorithmException e) {
					
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						
						e.printStackTrace();
					}
					try {
						cifrador.init(Cipher.ENCRYPT_MODE, FraseEncriptada);
					} catch (InvalidKeyException e) {
						
						e.printStackTrace();
					}
					

					switch(opcion) {
						
						case 1:
					
							System.out.println("----- ESCRIBE LA FRASE -----");
							frase = sc.nextLine();
						try {
							salida = new PrintStream(socketAlServidor.getOutputStream());
						
							salida.println(frase);
							
							entrada = new InputStreamReader(socketAlServidor.getInputStream());
						
							bf = new BufferedReader(entrada);
						
							cadenaEntrada = bf.readLine();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						System.out.println("----- ENCRIPTANDO FRASE -----");
						
						System.out.println("Mensaje Cifrado: " + cadenaEntrada);
						
				
						break;
						
						case 2:
							
							if(cadenaEntrada != null) {
							try {
								salida = new PrintStream(socketAlServidor.getOutputStream());
							
								salida.println(frase);
								
								entrada = new InputStreamReader(socketAlServidor.getInputStream());
							
								bf = new BufferedReader(entrada);
							try {
								cadenaEntrada = bf.readLine();
							} catch (SocketException e) {
								
							}
							} catch (IOException e) {
								
								e.printStackTrace();
							}
							System.out.println("----- DESENCRIPTANDO FRASE -----");
							
							System.out.println("Mensaje descifrado: " + cadenaEntrada);
							
							
					    } else {
						System.out.println("Primero debes escribir una frase: opcion: 1");
					    }
						break;
				
				     
				    	}
				} while(opcion != 3);
			      try {
						socketAlServidor.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
				      sc.close();
				      System.out.println("Programa Terminado");
			
			}
	}
}
	

	

