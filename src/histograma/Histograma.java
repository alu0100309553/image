package histograma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Histograma extends JPanel {
	private int [] r;
	private int [] g;
	private int [] b;
	private int [] y;
	private int size;
	Histograma (int [] r, int [] g, int [] b, int [] y, int size){
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.y = y;
		this.size = size;
	}
	private int getMax (){
		int aux = 0;
		for (int i = 0; i < 256; i++){
			if (r[i] > aux){
				aux = r[i];
			}
			if (g[i] > aux){
				aux = g[i];
			}
			if (b[i] > aux){
				aux = b[i];
			}
			if (y[i] > aux){
				aux = y[i];
			}
		}
		return aux;
	}

	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		int max = getMax();
		gr.setColor(Color.DARK_GRAY);
		for (int i = 0; i < y.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100, i,100 - (int)((double)(100*y[i])/(double)max));
		}
		gr.setColor(Color.RED);
		for (int i = 0; i < r.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100 - (int)((double)(100*r[i])/(double)max), i,100 - (int)((double)(100*r[i])/(double)max));
		}
		gr.setColor(Color.GREEN);
		for (int i = 0; i < g.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100 - (int)((double)(100*g[i])/(double)max), i,100 - (int)((double)(100*g[i])/(double)max));
		}
		gr.setColor(Color.BLUE);
		for (int i = 0; i < b.length; i++){
			//g.drawLine(i, (int)((float)(data[i])/(float)size), i, (int)((float)(data[i])/(float)size));
			gr.drawLine(i, 100 - (int)((double)(100*b[i])/(double)max), i,100 - (int)((double)(100*b[i])/(double)max));
		}
	}
}
