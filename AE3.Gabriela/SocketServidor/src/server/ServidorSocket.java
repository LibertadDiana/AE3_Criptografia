package server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import modelo.Criptografia;

public class ServidorSocket {

	public void comenzar() {
		InetSocketAddress direccion = new InetSocketAddress(2023);
		Socket socketAlCliente = null;
		System.out.println("ARRANCANDO  SERVIDOR");
				
		try (ServerSocket serverSocket = new ServerSocket()){
			serverSocket.bind(direccion);
			
				Criptografia cripto = new Criptografia();
				System.out.println("Esperando peticion...");
				socketAlCliente = serverSocket.accept();	
				System.out.println("Peticion aceptada ");
				Criptografia.incriptar(socketAlCliente);
			    Criptografia.desencriptar(socketAlCliente);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
}



