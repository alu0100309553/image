package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Imagen.Imagen;
import brillo.BrilloWindow;
import gui.GUI;

public class MenuLineales extends JMenu{
	private JMenuItem brillo = new JMenuItem("Brillo Contraste");
	MenuLineales(){
		setText("Trans. Lineales");
		brillo.addActionListener(new MenuListener());
		add(brillo);
		
	}
	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == brillo){
				Imagen origen = GUI.getActiva();
				new BrilloWindow(origen);
			}


		}

	}
}