package rotacion;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Imagen.Correccion;
import Imagen.Imagen;

public class RotInversa extends JFrame {
	private Imagen original;
	private JButton aplicar = new JButton ("Aplicar");
	private JRadioButton interpolar = new JRadioButton ("Bi-Lineal");
	private JRadioButton masProximo = new JRadioButton ("Vecino más proximo");
	private JPanel metodo = new JPanel ();
	private JPanel angPanel = new JPanel ();
	private ButtonGroup metodos = new ButtonGroup();
	private JTextField angulo = new JTextField ();
	private JLabel textoAngulo = new JLabel ("Ángulo de rotación");
	private ArrayList<ArrayList<int[]>> newdata;
	private ArrayList<ArrayList<int[]>> orgdata;
	public RotInversa (Imagen original){
		this.original = original;    
		setLayout(new GridLayout (3,1));
		setTitle("Rotación Directa");
		aplicar.addActionListener(new myActionListener());
		angPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Ángulo"));
		angPanel.setLayout(new GridLayout(1,2));
		angulo.setToolTipText("Ángulo en que se rotará la imágen");
		angPanel.add(textoAngulo);
		angPanel.add(angulo);
		add(angPanel);
		interpolar.setSelected(true);
		metodos.add(interpolar);
		metodos.add(masProximo);
		metodo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Método de interpolación"));
		metodo.add(interpolar);
		metodo.add(masProximo);
		add(metodo);
		add(aplicar);
		setVisible(true);
		pack();
	}

	private Point rotacion (Point org, double ang ){
		return new Point((int)((org.x * Math.cos(ang))+(org.y * -Math.sin(ang))),(int)((org.x * Math.sin(ang))+(org.y * Math.cos(ang))));
	}
	private void rotar () {
		//orgdata = original.getData();
		newdata = new ArrayList<ArrayList<int[]>> ();
		//Calculando el cuadrado que contiene a la imágen rotada.
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (int i = 0; i < original.sizeX(); i+= original.sizeX()-1){
			for (int j = 0; j < original.sizeY(); j += original.sizeY()-1){
				Point punto = rotacion (new Point (i,j), Math.toRadians(Double.parseDouble(angulo.getText())));
				if (punto.x > maxX){
					maxX = punto.x;
				}
				if (punto.x < minX){
					minX = punto.x;
				}
				if (punto.y > maxY){
					maxY = punto.y;
				}
				if (punto.y < minY){
					minY = punto.y;
				}
			}
		}
		int sizeX = Math.abs(maxX-minX);
		int sizeY = Math.abs(maxY-minY);
		if (interpolar.isSelected()){
			biLineal(sizeX, sizeY);
		} else {
			masProximo(sizeX, sizeY);
		}
		
		
	}
	
	private void biLineal(int sizeX, int sizeY){
		for (int i = 0; i < sizeX; i++){
			ArrayList<int[]> line = new ArrayList<int[]>();
			for (int j = 0; j < sizeY; j++){
				int[] aux = {0,0,0,0};
				line.add(aux);
			}
			newdata.add(line);
		}
	}

	private void masProximo(int sizeX, int sizeY){
		for (int i = 0; i < sizeX; i++){
			ArrayList<int[]> line = new ArrayList<int[]>();
			for (int j = 0; j < sizeY; j++){
				int[] aux = {0,0,0,0};
				line.add(aux);
			}
			newdata.add(line);
		}
	}
	private class myActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == aplicar){
				rotar();
			}

		}
	}

}
