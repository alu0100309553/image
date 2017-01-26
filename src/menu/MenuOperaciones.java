package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import Imagen.Imagen;
import alfaBlending.AlfaBlending;
import diferencia.Diferencia;
import digitalizar.Digitalizar;
import filtros.Filtros;
import gui.GUI;

public class MenuOperaciones extends JMenu {
	private JMenuItem diferencia = new JMenuItem("Diferencia");
	private JMenuItem digitalizar = new JMenuItem("Digitalizar");
	private JMenuItem filtro = new JMenuItem("Filtro");
	private JMenuItem blending = new JMenuItem("Alpha Blending");

	MenuOperaciones (){
		diferencia.addActionListener(new MenuListener());
		digitalizar.addActionListener(new MenuListener());
		filtro.addActionListener(new MenuListener());
    blending.addActionListener(new MenuListener());
		setText("Operaciones");
		add(diferencia);
		add(digitalizar);
		add(filtro);
		add(blending);
	}

	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == diferencia){            
				new Diferencia();
			} else if (e.getSource() == digitalizar){
				new Digitalizar(GUI.getActiva());
			} else if (e.getSource() == filtro){
				new Filtros(GUI.getActiva());
			} else if (e.getSource() == blending){
        new AlfaBlending();
      }

		}

	}
}
