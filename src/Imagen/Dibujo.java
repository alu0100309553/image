package Imagen;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Dibujo extends JPanel {
	private ImagePanel imagen;
	Dibujo (ImagePanel imagen){
		this.imagen = imagen;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(imagen.roisel){
			g.setColor(Color.RED);
			if (imagen.roi){
				g.drawRect(imagen.xi, imagen.yi, imagen.xt-imagen.xi, imagen.yt-imagen.yi);
			} else {
				g.drawLine(imagen.xi, imagen.yi, imagen.xt, imagen.yt);
			}
		}
	}
}
