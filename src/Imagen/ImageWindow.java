package Imagen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import gui.GUI;

public class ImageWindow extends JFrame {
	private InfoPanel info;
	private ImagePanel imgPanel;
	private Imagen img;
	
	ImageWindow (Imagen data){
		setLayout(new BorderLayout());
		Dimension dim = new Dimension(data.sizeX(), data.sizeY());
		setSize(dim);
		setTitle("Imagen " + GUI.getContador());
		GUI.addContador();
		info = new InfoPanel(data);
		imgPanel = new ImagePanel(data, info);
		info.setImage(imgPanel);
		add(imgPanel, BorderLayout.CENTER);
		addWindowListener(new MyWindowListener());
		add(info, BorderLayout.SOUTH);
		img= data;
		setVisible(true);
	}
	protected class MyWindowListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Crear dialogo para guardar o que hacer
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			GUI.setActiva(img);
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
