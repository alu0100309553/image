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

public class MenuOperaciones extends JMenu {
	private JMenuItem diferencia = new JMenuItem("Diferencia");
	private JMenuItem digitalizar = new JMenuItem("Digitalizar");

	MenuOperaciones (){
		diferencia.addActionListener(new MenuListener());
		digitalizar.addActionListener(new MenuListener());
		setText("Operaciones");
		add(diferencia);
		add(digitalizar);
	}

	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == diferencia){            
				new Diferencia();
			} else if (e.getSource() == digitalizar){
				new Digitalizar(GUI.getActiva());
			}

		}

	}
}
