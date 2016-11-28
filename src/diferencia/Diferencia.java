package diferencia;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Imagen.Imagen;
import ecualizacion.Ecualizacion;
import especificacion.Especificacion;

public class Diferencia extends JFrame {
	private JButton imgA = new JButton ("Imagen A");
	private JButton imgB = new JButton ("Imagen B");
	private JButton especificar = new JButton ("Especificar H. de B con H. de A");
	private JButton normalizar = new JButton ("Ecualizar ambos histogramas");
	private JButton calcular = new JButton ("Calcular");
	private JTextField imgAJT = new JTextField ();
	private JTextField imgBJT = new JTextField ();
	private JPanel filesel = new JPanel(new GridLayout(2,2));
	private JPanel filesoptions = new JPanel(new GridLayout(4,1));
	private JSlider umbralSL = new JSlider ();
	private Imagen imagenA;
	private Imagen imagenB;
	private ArrayList<ArrayList<int[]>> resultado = new ArrayList<ArrayList<int[]>>();
	private JFrame frame = new JFrame();
	private DifPanel panel;
	public Diferencia () {
		frame.setLayout(new BorderLayout());
		imgA.addMouseListener(new MyMouseListener());
		imgB.addMouseListener(new MyMouseListener());
		filesel.add(imgAJT);
		filesel.add(imgBJT);
		filesel.add(imgA);
		filesel.add(imgB);
		filesoptions.add(filesel);
		normalizar.addMouseListener(new MyMouseListener());
		especificar.addMouseListener(new MyMouseListener());
		calcular.addMouseListener(new MyMouseListener());
		filesoptions.add(especificar);
		filesoptions.add(normalizar);
		filesoptions.add(calcular);
		frame.add(filesoptions, BorderLayout.NORTH);
		umbralSL.setMinimum(0);
		umbralSL.setMaximum(255);
		umbralSL.setValue(125);
		umbralSL.addChangeListener(new MyChangeListener());
		umbralSL.setEnabled(false);
		Dimension d = new Dimension(256, 30);
		umbralSL.setSize(d);
		umbralSL.setPreferredSize(d);
		frame.add(umbralSL, BorderLayout.SOUTH);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
	
	protected class MyChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == umbralSL){
				panel.setUmbral(umbralSL.getValue());
				panel.repaint();
			}
			
		}
		
	}
	
	protected class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser chooser = new JFileChooser();
		      FileNameExtensionFilter bmp = new FileNameExtensionFilter(
		          "Imágenes *.bmp ", "bmp");
		      FileNameExtensionFilter tif = new FileNameExtensionFilter(
		          "Imágenes *.tif ", "tif");
		      FileNameExtensionFilter png = new FileNameExtensionFilter(
		          "Imágenes *.png ", "png");
		      chooser.setFileFilter(png);
		      chooser.setFileFilter(tif);
		      chooser.setFileFilter(bmp);
			if (e.getSource() == imgA){
				int returnVal = chooser.showOpenDialog(getParent());
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	imgAJT.setText(chooser.getSelectedFile().getName());
		        	imagenA = new Imagen(chooser.getSelectedFile().getAbsolutePath(),false);
		        	umbralSL.setEnabled(false);
		        	especificar.setEnabled(true);
		        	normalizar.setEnabled(true);
		        } else if (returnVal == JFileChooser.ERROR_OPTION ){
		          //TODO gestionar error al abrir archivo
		          
		        } 
			} else if (e.getSource() == imgB){
				int returnVal = chooser.showOpenDialog(getParent());
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	imgBJT.setText(chooser.getSelectedFile().getName());
		        	imagenB = new Imagen(chooser.getSelectedFile().getAbsolutePath(),false);
		        	umbralSL.setEnabled(false);
		        	especificar.setEnabled(true);
		        	normalizar.setEnabled(true);
		        } else if (returnVal == JFileChooser.ERROR_OPTION ){
		          //TODO gestionar error al abrir archivo
		          
		        } 
			} else if (e.getSource() == especificar){
				normalizar.setEnabled(false);
				Especificacion esp = new Especificacion(imagenB, imagenA, false);
				imagenB = esp.getResultado();
			} else if (e.getSource() == normalizar){
				especificar.setEnabled(false);
				Ecualizacion ecuA = new Ecualizacion (imagenA, false);
				imagenA = ecuA.getResultado ();
				Ecualizacion ecuB = new Ecualizacion (imagenB, false);
				imagenB = ecuB.getResultado ();
			} else if (e.getSource() == calcular){
				ArrayList<ArrayList<int[]>> imA = imagenA.getData();
				ArrayList<ArrayList<int[]>> imB = imagenB.getData();
				
				for (int i = 0; i < imagenA.sizeX(); i++){
					ArrayList <int[]> aux = new ArrayList <int[]>();
					for (int j = 0; j < imagenA.sizeY(); j++){
						int r = Math.abs(imA.get(i).get(j)[0]-imB.get(i).get(j)[0]);
						int g = Math.abs(imA.get(i).get(j)[0]-imB.get(i).get(j)[0]);
						int b = Math.abs(imA.get(i).get(j)[0]-imB.get(i).get(j)[0]);
						int y = Math.abs(imA.get(i).get(j)[0]-imB.get(i).get(j)[0]);
						int [] rgb = {r, g, b, y};
						aux.add(rgb);
					}
					resultado.add(aux);
				}
				panel = new DifPanel(imagenA, resultado);
				frame.add(panel, BorderLayout.CENTER);
				frame.repaint();
				umbralSL.setEnabled(true);
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
