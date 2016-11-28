package diferencia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Imagen.Imagen;

public class DifPanel extends JPanel{
	private Imagen lienzo;
	private ArrayList<ArrayList<int[]>> resultado;
	int umbral = 125;
	DifPanel (Imagen lienzo, ArrayList<ArrayList<int[]>> resultado) {
		this.lienzo = lienzo;
		this.resultado = resultado;
		Dimension d = new Dimension (lienzo.sizeX(), lienzo.sizeY());
		this.setSize(d);
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < lienzo.sizeX(); i++){
			for (int j = 0; j < lienzo.sizeY(); j ++){
				if (resultado.get(i).get(j)[3] < umbral){
					g.setColor(new Color (lienzo.getData(i, j)[3], lienzo.getData(i, j)[3], lienzo.getData(i, j)[3]));
				} else {
					g.setColor(Color.red);
				}
				g.drawLine(i,j,i,j);
			}
		}
	}
	public void setUmbral(int umbral){
		this.umbral = umbral;
	}

}
