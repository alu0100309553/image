package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import Imagen.Imagen;
import diferencia.Diferencia;
import digitalizar.Digitalizar;
import escala.Escala;
import espejo.EspejoHor;
import espejo.EspejoVert;
import espejo.Traspuesta;
import gui.GUI;
import rotacion.RotEntera;

public class MenuOpGeometricas extends JMenu {
	private JMenuItem rotEnt = new JMenuItem("Rotación Entera");
	private JMenuItem rotacion = new JMenuItem("Rotación");
	private JMenuItem espejoVert = new JMenuItem("Espejo Vertical");
	private JMenuItem espejoHor = new JMenuItem("Espejo Horizontal");
	private JMenuItem traspuesta = new JMenuItem("Traspuesta");
  private JMenuItem escala = new JMenuItem("Escala");

	MenuOpGeometricas (){
		rotEnt.addActionListener(new MenuListener());
		rotacion.addActionListener(new MenuListener());
		espejoVert.addActionListener(new MenuListener());
		espejoHor.addActionListener(new MenuListener());
		traspuesta.addActionListener(new MenuListener());
    escala.addActionListener(new MenuListener());
		setText("Operaciones Geométricas");
		add(rotEnt);
		add(rotacion);
		add(espejoVert);
		add(espejoHor);
		add(traspuesta);
		add(escala);
	}

	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == rotEnt){            
				new RotEntera(GUI.getActiva());
			} else if (e.getSource() == rotacion){
			  System.out.println("Rotación normal");
			} else if (e.getSource() == espejoVert){
			  new EspejoVert(GUI.getActiva(), true);			  
			} else if (e.getSource() == espejoHor){
        new EspejoHor(GUI.getActiva(), true);        
      } else if (e.getSource() == traspuesta){
        new Traspuesta (GUI.getActiva(), true);
      } else if (e.getSource() == escala){
        new Escala(GUI.getActiva());
      }

		}

	}
}
