package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

public class HistWindow extends JFrame {
	
	HistWindow (int [] r, int [] g, int [] b, int [] y, int size){
		Dimension dim = new Dimension(256, 100);
		setSize(dim);
		Histograma panel = new Histograma(r,g,b,y, size);
		add(panel);
		this.setTitle("Histograma");
		this.setVisible(true);
	}
}
