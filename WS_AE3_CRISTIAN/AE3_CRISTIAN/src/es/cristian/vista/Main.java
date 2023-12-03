package es.cristian.vista;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import es.cristian.controlador.Controlador;
import es.cristian.modelo.entidad.Encriptador;
import es.cristian.modelo.entidad.MensajeEncriptado;
import es.cristian.modelo.persistencia.DaoMensajeEncriptado;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		//Objeto Controlador, conecto con todas las demás clases.
		Controlador controlador = new Controlador();
		
		Scanner sc = new Scanner(System.in);
		
		/*
		 * Bucle While principal para aportar 3 oportunidades al usuario.
		 * Si falla 3 oportunidades, se finaliza el programa. Pasa lo mismo
		 * si no está autenticado.
		 * Primero verifica si todavía no han sucedido estas casuísticas para 
		 * poder entras en el programa y verificar la entrada del usuario.
		 */
		
		int oportunidades = 0;
		boolean autenticado = false;
		while(oportunidades < 3 && !autenticado) {
			System.out.println("Introduce tu usuario: ");
			String user = sc.nextLine();
			System.out.println("introduce tu contraseña: ");
			String pswd = sc.nextLine();
				
		/*
		 * Sentencia if, para comenzar el programa si se ha
		 * introducido correctamente la contraseña, mediante el método confirmUser
		 * de la clase COntrolador.
		 */
		boolean correcto = controlador.confirmUser(user, pswd);
				
		if(correcto == true) {
			autenticado = true;
			System.out.println("Bienvenid@ " + user);
			boolean continuar = true;
			
			
						
				/*
				 * bucle do while, para gestionar la salida del menú, cuando 
				 * la elección es 1 y continuar pasa a ser false.
				 * También gestiona el resto de opciones del menú.
				 */
				String choice = "";
				
				do {
					System.out.println("Menú de opciones\nElige un número:");
					System.out.println("1. Salir del programa.");
					System.out.println("2. Encriptar frase.");
					System.out.println("3. Desencriptar frase.");
					
					choice = sc.nextLine();
					
					try { //Se asegura de recibir un número.
						int election = Integer.parseInt(choice); 
						
						if(election == 1) {
							System.out.println("Has elegido salir del programa.");
							continuar = false;
							autenticado = false;
							
							
							
						}else if(election == 2) {
							System.out.println("Qué mensaje quieres encriptar?");
							String mensaje = sc.nextLine();
							controlador.encriptar(mensaje);
							
							MensajeEncriptado mEncriptado = new MensajeEncriptado();
							/*
							 * Mediante el objeto, le paso el mensaje(único artibuto) al nuevo libro creado
							   en el método del controlador addMensaje, que a su vez, lo crea
							   en daoMensajeEncriptado.
							 */
							mEncriptado.setMensaje(mensaje);
							controlador.addMensaje(mEncriptado);
							
							System.out.println("Listado de mensajes encriptados: ");
							System.out.println(controlador.listarMensaje() + "\n");
							
						}else if(election == 3) {
							System.out.println("Elige uno de los siguientes mensajes mediante su ID para desencriptarlo");
							System.out.println(controlador.listarMensaje());
							int eleccion = Integer.parseInt(sc.nextLine());
							
							/*
							 * Election(ID elegido) lo paso por el método getMensaje. Su variable
							 * La paso por método desencriptar, para desencriptar el mensaje elegido.
							 */
							String mensajeElegido = controlador.getMensaje(eleccion);	
							String mensajeDesencriptado = controlador.desencriptar(mensajeElegido);
							System.out.println("Tu mensaje original era: " + mensajeDesencriptado + "\n");
							System.out.println("¿Qué deseas hacer ahora?");
						}
					}catch(NumberFormatException e) {
						System.out.println("La elección no es un número válido, vuelve a intentarlo.");
					}
				
				}while(continuar);
				
				
			}else {
				System.out.println("Usuario o contraseña incorrectos. Inténtalo de nuevo: ");
				oportunidades++;
			}
					
		}
		
		if(!autenticado) {
			System.out.println("¡Acceso denegado!");
		}
		
		
		
		
		sc.close();
 }
}
