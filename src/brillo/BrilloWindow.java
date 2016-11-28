package brillo;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Imagen.Imagen;
import compenentes.SliderWTex;

public class BrilloWindow extends JFrame {
	private SliderWTex brillo;
	private SliderWTex contraste;
	private JButton aplicar;
	private Imagen origen;

	public BrilloWindow (Imagen origen){
		this.origen = origen;
		Dimension dim = new Dimension(500, 100);
		this.setSize(dim);
		this.setLayout(new GridLayout(3,1));
		this.setTitle("Brillo Contraste");
		brillo = new SliderWTex ("Brillo", 255, 0,origen.getBrillo());
		contraste = new SliderWTex ("Contraste", 255, 0, origen.getContraste());
		aplicar = new JButton ("Aplicar");
		aplicar.addMouseListener(new MyMouseListener());
		this.add(brillo);
		this.add(contraste);
		this.add(aplicar);

		this.setVisible(true);
	}
	protected class MyMouseListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == aplicar){
				double nuevoBrillo = brillo.getValue();
				double nuevoContraste = contraste.getValue();
				double a = nuevoContraste / (double)origen.getContraste();
				double b = nuevoBrillo - (a * (double)origen.getBrillo());
				int [] aux = new int [256];
				for (int i = 0; i < 256 ; i++){
					int valor = (int)( (a * (double)i) + b);
					if (valor > 255){
						valor = 255;
					} else if (valor <0){
						valor = 0;
					} 
					aux[i] = valor;
				}
				new Imagen (origen, aux);
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}
