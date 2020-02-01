package Frames;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Agente {
	private String nombreClave;
	private Coordinate ubicacion;
	private ImageIcon foto;
	private ArrayList<Agente> contactos;
	
	public Agente(String nombre, Coordinate coor) {
		this.nombreClave = nombre;
		this.ubicacion = coor;
		this.foto = new ImageIcon("agentePerfil.png");
		this.contactos = new ArrayList<Agente>();
	}
	public Agente() {
	}
	
	public String getNombre() {
		return nombreClave;
	}
	public Coordinate getUbicacion() {
		return this.ubicacion;
	}
	public void agregarContacto(Agente nuevo) {
		contactos.add(nuevo);
		
	}

	public ArrayList<Agente> getContactos() {
		return contactos;
	}
	public static Agente buscarAgente(String text, ArrayList<Agente> contactos) {
		Agente agente= new Agente();
		for(int i=0; i<contactos.size();i++){
			if(text.equals(contactos.get(i).getNombre())){
				agente=contactos.get(i);
			} 
			}
		return agente;
	}
	@Override
	public String toString() {
		return "(agente: "+nombreClave+" ubicacion: "+ubicacion.toString()+")";
	}
}