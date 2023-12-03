package es.cristian.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import es.cristian.modelo.entidad.MensajeEncriptado;

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
