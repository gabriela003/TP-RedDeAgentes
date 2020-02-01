package Controls;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import Frames.Agente;

public class GrafoAgentes {
	public ArrayList<Agente> agentes;

	public GrafoAgentes() {
		this.agentes = new ArrayList<Agente>();
	}
	public int tamanio() {
		return agentes.size();
	}
	public ArrayList<Coordinate> caminoMinimo(Agente salida, Agente llegada){
		double dist = 2000000;
		ArrayList<Coordinate> camino = new ArrayList<Coordinate>();
		ArrayList<Agente> contactos = new ArrayList<Agente>();
		contactos=salida.getContactos();
		for(Agente contacto: contactos) {
			double distanciaActual =distanciaCoord(salida.getUbicacion(),contacto.getUbicacion());
			Agente agenteSalida=null;
			if(distanciaActual<dist) {
				dist=distanciaActual;
				agenteSalida=contacto;
				camino.add(contacto.getUbicacion());
			}
			if(agenteSalida.equals(llegada)) {
				return camino;
			}
		}
		return camino;
		
	}
	public static double distanciaCoord(Coordinate coord1, Coordinate coord2) {   
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(coord2.getLat() - coord1.getLat());  
        double dLng = Math.toRadians(coord2.getLon() - coord1.getLon());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(coord1.getLat())) * Math.cos(Math.toRadians(coord2.getLat()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }
	public int size() {
		return agentes.size();
	}
	public void agregarAgente(Agente ag) {
		agentes.add(ag);
		
	}
	@Override
	public String toString() {
		return agentes.toString();
	}
}
