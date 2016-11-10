package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Imagen.Imagen;
import brillo.BrilloWindow;
import gui.GUI;
import tramos.Tramos;

public class MenuLineales extends JMenu{
	private JMenuItem brillo = new JMenuItem("Brillo Contraste");
	private JMenuItem tramos = new JMenuItem("Ajuste Por Tramos");
	MenuLineales(){
		setText("Trans. Lineales");
		brillo.addActionListener(new MenuListener());
		tramos.addActionListener(new MenuListener());
		add(brillo);
		add(tramos);
		
	}
	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == brillo){
				new BrilloWindow(GUI.getActiva());
			} else if (e.getSource() == tramos){
				new Tramos (GUI.getActiva());
			}


		}

	}
}