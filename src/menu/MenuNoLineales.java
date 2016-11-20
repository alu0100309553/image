package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import Imagen.Imagen;
import brillo.BrilloWindow;
import ecualizacion.Ecualizacion;
import especificacion.Especificacion;
import gamma.GammaWin;
import gui.GUI;
import tramos.Tramos;

public class MenuNoLineales extends JMenu{
  private JMenuItem ecualizacion = new JMenuItem("Ecualización");
  private JMenuItem especificacion = new JMenuItem("Especificacion");
  private JMenuItem gamma = new JMenuItem("Gamma");
  MenuNoLineales(){
    setText("Trans. No Lineales");
    ecualizacion.addActionListener(new MenuListener());
    add(ecualizacion);
    especificacion.addActionListener(new MenuListener());
    add(especificacion);
    gamma.addActionListener(new MenuListener());
    add(gamma);
  }
  protected class MenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

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
      if (e.getSource() == ecualizacion){
        new Ecualizacion(GUI.getActiva());
      } else if (e.getSource() == especificacion){
        int returnVal = chooser.showOpenDialog(getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          new Especificacion(GUI.getActiva(),new Imagen(chooser.getSelectedFile().getAbsolutePath(), false));
        } else if (returnVal == JFileChooser.ERROR_OPTION ){
          //TODO gestionar error al abrir archivo

        } 
      } else if (e.getSource() == gamma){
        new GammaWin(GUI.getActiva());
      } 
    }


  }
}