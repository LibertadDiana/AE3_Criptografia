package es.cristian.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import es.cristian.modelo.entidad.MensajeEncriptado;

/*
 * Clase DaoMensajeEncriptado
 * 
 * Modelo DAO de la clase MensajeEncriptado, para realizar el CRUD pertienente
 * desde la clase Controlador y así conectar con la vista del usuario.
 */
public class DaoMensajeEncriptado {

	private List<MensajeEncriptado> listaMensajes;
	private int contador;
	
	public DaoMensajeEncriptado() {
		listaMensajes = new ArrayList<MensajeEncriptado>();
		
	}
	
	public List<MensajeEncriptado> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(List<MensajeEncriptado> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}
	
	//Métodos
	
	public MensajeEncriptado get (int posicion) {
		try {
			return listaMensajes.get(posicion);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Mensaje fuera de rango");
			return null;
		}
	}
	
	public boolean add(MensajeEncriptado m) {
		for(MensajeEncriptado mensaje : listaMensajes) {
			if(mensaje.getMensaje() == m.getMensaje()) {
				return false;
			}
		}
		
		
		m.setId(++contador);
		listaMensajes.add(m);
		return true;
	}
	
	public List<MensajeEncriptado> list(){
		return listaMensajes;
	}
}
