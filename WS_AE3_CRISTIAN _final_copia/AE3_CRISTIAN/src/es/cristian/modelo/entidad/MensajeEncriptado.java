package es.cristian.modelo.entidad;

public class MensajeEncriptado {
	
	private int id;
	private String mensaje;
	
	public MensajeEncriptado(int id, String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
	}
	
	
	
	public MensajeEncriptado() {
		
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	@Override
	public String toString() {
		return "MensajeEncriptado [id=" + id + ", mensaje=" + mensaje + "]";
	}



	
	

}
