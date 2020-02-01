package Frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Controls.GrafoAgentes;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame{
	private JPanel panelMapa;
	private JMapViewer mapa;
	private JTextField textField;
	private JLabel lblAgente;
	public JButton btnContactar;
	private JPanel perfilAgente;
	public ArrayList<Coordinate> lasCoordenadas;
	public JButton btnCamino;
	private ArrayList<MapPolygonImpl> losCaminos2;
	public GrafoAgentes losCaminos;
	public MapPolygonImpl camino;
	private JButton btnEliminarCamino;
	private ArrayList<Agente> agentes;
	public ArrayList<Coordinate> puntos;
	
public VentanaPrincipal() {
	lasCoordenadas = new ArrayList<Coordinate>();
	agentes = new ArrayList<Agente>();
	losCaminos=new Controls.GrafoAgentes();
	camino = new MapPolygonImpl();
	puntos = new ArrayList<Coordinate>();
	 
	//inicializamos la ventana
	setBounds(100, 100, 725, 506);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	
	//inicializamos el panel 
	panelMapa = new JPanel();
	panelMapa.setBounds(10, 11, 437, 446);
	getContentPane().add(panelMapa);
	
	//inicializamos el mapa
	mapa = new JMapViewer();
	mapa.setZoomContolsVisible(false);
	mapa.setDisplayPositionByLatLon(-34.550, -58.7000, 15);
	mapa.addMouseListener(new MouseAdapter() 
	{
		@Override
		public void mouseClicked(MouseEvent e) 
		{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
		    Coordinate coor = mapa.getPosition(e.getPoint());
		    lasCoordenadas.add(coor);
			String nombre = JOptionPane.showInputDialog("Nombre: ");
			Agente ag = new Agente(nombre, coor);
			agentes.add(ag);
			if(agentes.size()>0) {
				agentes.get(agentes.size()-1).agregarContacto(ag);
			}
			losCaminos.agentes=agentes;
		    mapa.addMapMarker(new MapMarkerDot(nombre, coor));
		}}
	});
	
	//agregamos el mapa al panel
	panelMapa.add(mapa);
	
	lblAgente = new JLabel("Agente:");
	lblAgente.setBounds(457, 40, 46, 14);
	getContentPane().add(lblAgente);
	
	textField = new JTextField();
	textField.setBounds(523, 60, 151, 20);
	getContentPane().add(textField);
	textField.setColumns(10);
	
	perfilAgente = new JPanel();
	perfilAgente.setBounds(457, 174, 242, 220);
	getContentPane().add(perfilAgente);
	perfilAgente.setLayout(null);
	
	//corregir el contactar: no sale en pantalla el nombre del agente
	btnContactar = new JButton("Contactar");
	btnContactar.setBounds(467,140, 107, 23);
	getContentPane().add(btnContactar);
	btnContactar.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String agente = textField.getText();
			for(Agente a : agentes) {
				if(a.getNombre().equals(agente)) {
					perfilAgente.setBackground(Color.blue);
					JLabel perfil= new JLabel(a.getNombre());
					perfilAgente.add(perfil);
					perfilAgente.validate();
				}
			}
		}
			
		});
	JTextField tfSalida = new JTextField();
	tfSalida.setBounds(523, 37, 151, 20);
	getContentPane().add(tfSalida);
	tfSalida.setColumns(10);
	/*JTextField tfllegada = new JTextField();
	tfSalida.setBounds(523, 37, 151, 20);
	getContentPane().add(tfSalida);
	tfSalida.setColumns(10);*/
	btnCamino = new JButton("Camino");
	btnCamino.setBounds(599, 141, 100, 25);
	getContentPane().add(btnCamino);
	
	JLabel lblContactar = new JLabel("contactar:");
	lblContactar.setBounds(457, 65, 66, 14);
	getContentPane().add(lblContactar);
	btnCamino.addActionListener(new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			/*losCaminos = new ArrayList<MapPolygonImpl>();
			for (int i=0; i<lasCoordenadas.size()-1; ++i)
			{
				camino = new MapPolygonImpl(lasCoordenadas.get(i), lasCoordenadas.get(i+1), lasCoordenadas.get(i));
				losCaminos.add(camino);
				mapa.addMapPolygon(camino);
			}*/
			Agente agenteSalida=Agente.buscarAgente(tfSalida.getText(),agentes);
			Agente agenteLlegada =Agente.buscarAgente(textField.getText(),agentes);
			puntos =losCaminos.caminoMinimo(agenteSalida,agenteLlegada);
			losCaminos2 = new ArrayList<MapPolygonImpl>();
			for (int i=0; i<puntos.size()-1; ++i) {
			camino = new MapPolygonImpl(puntos.get(i), puntos.get(i+1),puntos.get(i));
			//losCaminos.add(camino);
			mapa.addMapPolygon(camino);
			}
		}

		
	});
/*	btnEliminarCamino = new JButton("EliminarCamino");
	btnEliminarCamino.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			for (MapPolygonImpl c: losCaminos)
			{
				mapa.removeMapPolygon(c);
			}
			
		}
	});
	btnEliminarCamino.setBounds(471, 138, 100, 25);
	getContentPane().add(btnEliminarCamino);*/

	
	
}
}
