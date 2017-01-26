package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Imagen.Imagen;
import gui.GUI;

public class MenuArchivo extends JMenu {
	private JMenuItem abrir = new JMenuItem("Abrir");
	private JMenuItem guardarbn = new JMenuItem("Guardar escala de grises");
	private JMenuItem guardarcolor = new JMenuItem("Guardar color");
	private JMenuItem acerca = new JMenuItem("Acerca de");
	MenuArchivo (){
		abrir.addActionListener(new MenuListener());
		guardarbn.addActionListener(new MenuListener());
		guardarcolor.addActionListener(new MenuListener());
		acerca.addActionListener(new MenuListener());
		setText("Archivo");
		add(abrir);
		add(guardarbn);
		add(guardarcolor);
		add(acerca);
	}

	protected class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter bmp = new FileNameExtensionFilter(
					"Imágenes *.bmp ", "bmp");
			FileNameExtensionFilter tif = new FileNameExtensionFilter(
					"Imágenes *.jpg ", "jpg");
			FileNameExtensionFilter png = new FileNameExtensionFilter(
					"Imágenes *.png ", "png");
			chooser.setFileFilter(png);
			chooser.setFileFilter(tif);
			chooser.setFileFilter(bmp);

			if (e.getSource() == abrir){            
				int returnVal = chooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					new Imagen(chooser.getSelectedFile().getAbsolutePath());
				} else if (returnVal == JFileChooser.ERROR_OPTION ){

				} 

			} else if (e.getSource() == guardarbn ){
				Imagen imagen = GUI.getActiva();
				int returnVal = chooser.showSaveDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {

					String path = chooser.getSelectedFile().getAbsolutePath();
					String [] descripcion = chooser.getFileFilter().getDescription().split("\\.");
					String extension = descripcion [descripcion.length-1];
					extension = extension.replaceAll(" ", "");

					BufferedImage buffimagen = new BufferedImage(imagen.sizeX(), imagen.sizeY(), BufferedImage.TYPE_INT_RGB);
					Graphics g = buffimagen.getGraphics();
					for (int i = 0; i < imagen.sizeX(); i++ ){
						for (int j = 0; j < imagen.sizeY(); j++){
							g.setColor(new Color(imagen.getData(i, j)[3],imagen.getData(i, j)[3],imagen.getData(i, j)[3]));
							g.drawLine(i,j,i,j);
						}
					}

					try {
						boolean estado = ImageIO.write(buffimagen, extension.toUpperCase(), new File(path +"."+ extension));
						if (!estado){
							System.out.println("Error en la especificación del fichero");
						}
					} catch (IOException error) {
						System.out.println("Error de escritura");
					}


				} else if (returnVal == JFileChooser.ERROR_OPTION ){

				} 

			} else if (e.getSource() == guardarcolor ){
				Imagen imagen = GUI.getActiva();
				int returnVal = chooser.showSaveDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {

					String path = chooser.getSelectedFile().getAbsolutePath();
					String [] descripcion = chooser.getFileFilter().getDescription().split("\\.");
					String extension = descripcion [descripcion.length-1];
					extension = extension.replaceAll(" ", "");

					BufferedImage buffimagen = new BufferedImage(imagen.sizeX(), imagen.sizeY(), BufferedImage.TYPE_INT_RGB);
					Graphics g = buffimagen.getGraphics();
					for (int i = 0; i < imagen.sizeX(); i++ ){
						for (int j = 0; j < imagen.sizeY(); j++){
							g.setColor(new Color(imagen.getData(i, j)[0],imagen.getData(i, j)[1],imagen.getData(i, j)[2]));
							g.drawLine(i,j,i,j);
						}
					}

					try {
						boolean estado = ImageIO.write(buffimagen, extension.toUpperCase(), new File(path +"."+ extension));
						if (!estado){
							System.out.println("Error en la especificación del fichero");
						}
					} catch (IOException error) {
						System.out.println("Error de escritura");
					}


				} else if (returnVal == JFileChooser.ERROR_OPTION ){

				} 

			}else if (e.getSource() == acerca){
				JFrame info = new JFrame("Acerca de:");
				info.setLayout(new GridLayout(1,1));
				JTextArea text = new JTextArea ("Image es un programa para el tratamiento de imágenes. "
						+ "\nDesarrollado en la asignatura Visión por Computador "
						+ "\nde 4º de Grado en Ingeniería Informática (Computación) "
						+ "\nEscuela Técnica Superior de Ingeniería Informática. "
						+ "\nUniversidad de La Laguna - 2017"
						+ "\nDesarrollado por: Rubén Labrador Páez" );
				info.add(text);
				info.pack();
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				info.setVisible(true);
			}
		}
	}
}
