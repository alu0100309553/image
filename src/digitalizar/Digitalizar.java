package digitalizar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Imagen.Imagen;


public class Digitalizar extends JFrame {
	private JButton digitalizar = new JButton ("Digitalizar");
	private JLabel muestreo = new JLabel ("Tamaño de Muestreo");
	private JTextField muestreoIn = new JTextField (2);
	private JLabel bits = new JLabel ("Bits de Muestreo");
	private JTextField bitsIn = new JTextField (2);
	private Imagen original;
	private ArrayList<ArrayList<int[]>> newdata = new ArrayList<ArrayList<int[]>>() ;
	private ArrayList<ArrayList<int[]>> orgdata;
	public Digitalizar (Imagen origen){
		original = origen;
		Dimension dim = new Dimension(256, 100);
		setSize(dim);
		setLayout(new FlowLayout());
		setTitle("Digitalizacion");
		add(muestreo);
		add(muestreoIn);
		add(bits);
		add(bitsIn);
		digitalizar.addActionListener(new myActionListener());
		add(digitalizar);
		setVisible(true);
	}
	private void digit (){
		orgdata = original.getData();
		int tam = Integer.parseInt(muestreoIn.getText());
		int b = Integer.parseInt(bitsIn.getText());
		int n = (int)Math.pow(2, b)-1;
		int salto = 255/n;
		for (int i = 0; i+tam < original.sizeX(); i+= tam ){
			ArrayList<int[]> line = new ArrayList<int[]>();
			for (int j = 0; j+tam < original.sizeY(); j+= tam){
				int auxR = 0;
				int auxG = 0;
				int auxB = 0;
				int auxY = 0;
				for(int k = 0; k < tam; k++){
					for(int l = 0; l < tam; l++){
						auxR += orgdata.get(i+k).get(j+l) [0];
						auxG += orgdata.get(i+k).get(j+l) [1];
						auxB += orgdata.get(i+k).get(j+l) [2];
						auxY += orgdata.get(i+k).get(j+l) [3];
					}
				}
				auxR /= (tam*tam);
				auxG /= (tam*tam);
				auxB /= (tam*tam);
				auxY /= (tam*tam);
				int [] aux  = {auxR, auxG, auxB, auxY};
				for (int m = 0; m < aux.length; m++){
					if (aux[m]%salto>salto/2){
						aux[m] = (((aux[m]/salto)+1)*salto)-1;
					} else {
						aux[m] = (aux[m]/salto)*salto;
					}
				}
				line.add(aux);
			}
			newdata.add(line);
		}
		new Imagen (newdata, true);
	} 
	
	private class myActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == digitalizar){
				digit ();
			}
			// TODO Auto-generated method stub
			
		}
		
	}

}
