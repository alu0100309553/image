package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Imagen.Imagen;
import brillo.BrilloWindow;
import ecualizacion.Ecualizacion;
import gui.GUI;
import tramos.Tramos;

public class MenuNoLineales extends JMenu{
	private JMenuItem ecualizacion = new JMenuItem("Ecualización");
	MenuNoLineales(){
		setText("Trans. No Lineales");
		ecualizacion.addActionListener(new MenuListener());
		add(ecualizacion);
	}
	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ecualizacion){
				new Ecualizacion(GUI.getActiva());
			} 
		}

	}
}