package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Imagen.Imagen;
import gui.GUI;
import histograma.HistWindow;
import histograma.HistWindowAC;

public class MenuInfo extends JMenu{
	private JMenuItem hist = new JMenuItem("Histograma");
	private JMenuItem histAc = new JMenuItem("Histograma Acumulativo");
	private JMenuItem entropia = new JMenuItem("Entropia");
	MenuInfo(){
		setText("Información");
		hist.addActionListener(new MenuListener());
		histAc.addActionListener(new MenuListener());
		entropia.addActionListener(new MenuListener());
		add(hist);
		add(histAc);
		add(entropia);
		
	}
	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == hist){
				Imagen origen = GUI.getActiva();
				new HistWindow(origen.getHistData().get(0), origen.getHistData().get(1), origen.getHistData().get(2), origen.getHistData().get(3), origen.sizeX()*origen.sizeY());
			}else if (e.getSource() == histAc){
				Imagen origen = GUI.getActiva();
				new HistWindowAC(origen.getHistACData().get(0), origen.getHistACData().get(1), origen.getHistACData().get(2), origen.getHistACData().get(3), origen.sizeX()*origen.sizeY());
			}


		}

	}
}