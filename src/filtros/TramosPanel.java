package filtros;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import tramos.Tramos.Puntos;

public class TramosPanel extends JPanel {
	private ArrayList <Puntos> tramosdef;
	TramosPanel () {
		Dimension dim = new Dimension (256,256);
		this.setSize(dim);
		//this.setBackground(Color.BLACK);
		tramosdef = new ArrayList <Puntos> ();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 255, 255);
		g.setColor(Color.BLACK);
		for (int i = 0; i < tramosdef.size()-1; i ++){
			g.fillOval(tramosdef.get(i).x-2, 255-tramosdef.get(i).y-2, 4, 4);
			g.drawLine(tramosdef.get(i).x, 255-tramosdef.get(i).y, tramosdef.get(i+1).x, 255-tramosdef.get(i+1).y);
		}
		if (tramosdef.size()>=1){
			g.fillOval(tramosdef.get(tramosdef.size()-1).x-2, 254-tramosdef.get(tramosdef.size()-1).y-2, 4, 4);
		}
	}

	public void modificar (ArrayList <Puntos> tramosdef){
		this.tramosdef = tramosdef;
		this.repaint();
	}

}
