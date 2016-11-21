package Imagen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

import perfil.Perfil;
import perfil.PerfilWindow;

public class ImagePanel extends JPanel {
	private boolean mode = false; //true = rgb, false = gris
	public Imagen data;
	private InfoPanel info;
	public boolean roisel = false;
	public int xi = 0;
	public int yi = 0;
	public int xf = 0;
	public int yf = 0;
	public int xt = 0;
	public int yt = 0;
	private Dibujo dibujo;
	public boolean roi = true;
	ImagePanel (Imagen data, InfoPanel info){
		super();
		this.data = data;
		this.info = info;
		this.addMouseMotionListener(new MyMouseListener());
		this.addMouseListener(new MyClickListener());
		dibujo = new Dibujo (this);
		Dimension d = new Dimension (data.sizeX(), data.sizeY());
		dibujo.setSize(d);
		dibujo.setPreferredSize(d);
		dibujo.addMouseMotionListener(new MyMouseListener());
		dibujo.addMouseListener(new MyClickListener());
		dibujo.setOpaque(false);
		//dibujo.setBackground(Color.RED);
		this.add(dibujo);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < data.sizeX(); i++){
			for (int j = 0; j < data.sizeY(); j ++){
				if (mode){
					g.setColor(new Color (data.getData(i, j)[0], data.getData(i, j)[1], data.getData(i, j)[2]));
				}
				else{
					g.setColor(new Color (data.getData(i, j)[3], data.getData(i, j)[3], data.getData(i, j)[3]));
				}
				g.drawLine(i,j,i,j);
			}
		}
		if (roisel){
			g.setColor(Color.BLACK);
			g.drawRect(xi, yi, xi-xt, yi-yt);
		}
	}
	private void info(int x, int y){
		if (x >= data.sizeX()){
			x = data.sizeX()-1;
		}
		if (y >= data.sizeY()){
			y = data.sizeY()-1;
		}
		if (x < 0){
			x = 0;
		}
		if (y < 0){
			y = 0;
		}
		info.setData(x,y);	
	}

	private class MyClickListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			xi = e.getX();
			yi = e.getY();
			roisel = true;
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			xf = e.getX();
			yf = e.getY();
			if (roi){
				new Imagen(data, xi, yi, xf, yf);
			} else {
				new PerfilWindow(data, xi, yi, xf, yf);
			}
			roisel = false;
			xi = 0;
			yi = 0;
			xf = 0;
			yf = 0;
			xt = 0;
			yt = 0;
			dibujo.repaint();

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	private class MyMouseListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			if (roisel){
				xt = e.getX();
				yt = e.getY();
				dibujo.repaint();
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			info (e.getX(), e.getY());
		}

	}
	public void setMode (boolean mode){
		this.mode = mode;
	}
}
