package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class HistogramaAC extends JPanel {
	private int [] r;
	private int [] g;
	private int [] b;
	private int [] y;
	private int size;
	HistogramaAC (int [] r, int [] g, int [] b, int [] y, int size){
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.y = y;
		this.size = size;
	}
	public void paintComponent(Graphics gr) {
		
		super.paintComponent(gr);
		gr.setColor(Color.DARK_GRAY);
		for (int i = 0; i < y.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100, i,100 - (int)((double)(10000*y[i])/(double)size));
		}
		gr.setColor(Color.RED);
		for (int i = 0; i < r.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100 - (int)((double)(10000*r[i])/(double)size), i,100 - (int)((double)(10000*r[i])/(double)size));
		}
		gr.setColor(Color.GREEN);
		for (int i = 0; i < g.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100 - (int)((double)(10000*g[i])/(double)size), i,100 - (int)((double)(10000*g[i])/(double)size));
		}
		gr.setColor(Color.BLUE);
		for (int i = 0; i < b.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100 - (int)((double)(10000*b[i])/(double)size), i,100 - (int)((double)(10000*b[i])/(double)size));
		}
	}
}
