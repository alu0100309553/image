package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private boolean mode = true; //true = rgb, false = gris
	private Imagen data;
	private InfoPanel info;
	ImagePanel (Imagen data, InfoPanel info){
		super();
		this.data = data;
		this.info = info;
		this.addMouseMotionListener(new MyMouseListener());
		this.addMouseListener(new MyClickListener());
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
	}
	private void info(int x, int y){
		info.setData(x,y);	
	}
	
	private class MyClickListener implements MouseListener{
		private int xi = 0;
		private int yi = 0;
		private int xf = 0;
		private int yf = 0;

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			xi = e.getX();
			yi = e.getY();
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			xf = e.getX();
			yf = e.getY();
			new Imagen(data, xi, yi, xf, yf);
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			info (e.getX(), e.getY());

			// TODO Auto-generated method stub

		}

	}
	public void setMode (boolean mode){
		this.mode = mode;
	}
}
