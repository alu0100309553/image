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
import gui.GUI;
import rotacion.RotEntera;

public class MenuOpGeometricas extends JMenu {
	private JMenuItem rotEnt = new JMenuItem("Rotación Entera");
	private JMenuItem rotacion = new JMenuItem("Rotación");

	MenuOpGeometricas (){
		rotEnt.addActionListener(new MenuListener());
		rotacion.addActionListener(new MenuListener());
		setText("Operaciones Geométricas");
		add(rotEnt);
		add(rotacion);
	}

	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == rotEnt){            
				new RotEntera(GUI.getActiva());
			} else if (e.getSource() == rotacion){
				//new Digitalizar(GUI.getActiva());
			}

		}

	}
}
