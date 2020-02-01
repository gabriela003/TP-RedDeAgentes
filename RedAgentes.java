package Controls;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import Frames.VentanaPrincipal;

public class RedAgentes {
	private VentanaPrincipal window;

	public static void main(String[] args)
	{
		VentanaPrincipal window = new VentanaPrincipal();
		window.setVisible(true);
		System.out.println(window.losCaminos.toString());
		window.btnCamino.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("coordenas en el grafo: "+window.losCaminos.toString());
				System.out.println("coord caminnominimo: "+window.puntos.toString());
				
			}
		});
		}
			
	/*	EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				while(true){
				try {
					//VentanaPrincipal window = new VentanaPrincipal();
				/*	Grafos test = new Grafos(4);
					test.setArista(0, 1, 30);
					test.setArista(0, 2, 40);
					test.setArista(0, 3, 60);
					test.setArista(1, 2, 50);
					test.setArista(1, 3, 50);
					test.setArista(2, 3, 50);


					System.out.println(test.imprimirVecinosYPesos());
					test.prim();
					System.out.println(test.imprimirPrim());
					
					window.setVisible(true);
					System.out.println(window.lasCoordenadas.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}
		});*/

}
