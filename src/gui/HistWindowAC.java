package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

public class HistWindowAC extends JFrame {
	
	HistWindowAC (int [] r, int [] g, int [] b, int [] y, int size){
		Dimension dim = new Dimension(256, 100);
		setSize(dim);
		Histograma panel = new Histograma(r,g,b,y, size);
		add(panel);
		this.setTitle("HistogramaAC");
		this.setVisible(true);
	}
}
