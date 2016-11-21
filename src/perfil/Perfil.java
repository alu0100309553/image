package perfil;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Imagen.Imagen;

public class Perfil extends JPanel {
	private int [] [] valores;
	public Perfil (Imagen data, int xi, int yi, int xf, int yf) {
		int dx = xf-xi;
		int dy = yf-yi;
		if (Math.abs(dx) >= Math.abs(dy)){
			double a = (double)dy/(double)dx;
			double b = (double)yi - a*(double)xi;
			valores = new int [dx] [];
			for (int i = 0; i < dx; i++ ){
				valores [i] = data.getData(i+xi, (int)((a * (double)(i+xi)) + b));
			}
			this.setSize(dx, 256);
		} else {
			double a = (double)dx/(double)dy;
			double b = (double)xi - a*(double)yi;
			valores = new int [dy][];
			for (int i = 0; i < dy; i++ ){
				valores [i] = data.getData((int)((a * (double)(i+yi)) + b), i+yi);
			}
			this.setSize(dy, 256);
		}
	}
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i < valores.length; i++){
			g.setColor(Color.DARK_GRAY);
			g.drawLine(i, 256, i, 256-valores[i][3]);
			g.setColor(Color.RED);
			g.fillRect(i, 256-valores[i][0] , 1,1);
			g.setColor(Color.GREEN);
			g.fillRect(i, 256-valores[i][1] , 1,1);
			g.setColor(Color.BLUE);
			g.fillRect(i, 256-valores[i][2] , 1,1);
		}

	}


}
