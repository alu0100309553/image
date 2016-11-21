package perfil;

import java.awt.Dimension;

import javax.swing.JFrame;

import Imagen.Imagen;
import histograma.Histograma;

public class PerfilWindow extends JFrame {
	public PerfilWindow (Imagen data, int xi, int yi, int xf, int yf){

		Dimension dim = new Dimension(600, 270);
		setSize(dim);
		Perfil panel = new Perfil(data, xi, yi, xf, yf);
		add(panel);
		this.setTitle("Perfil");
		this.setVisible(true);


	}

}
