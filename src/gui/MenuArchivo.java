package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuArchivo extends JMenu {
  private JMenuItem abrir = new JMenuItem("Abrir");
  private JMenuItem guardar = new JMenuItem("Guardar");
  private JMenuItem acerca = new JMenuItem("Acerca de");
  MenuArchivo (){
    abrir.addActionListener(new MenuListener());
    guardar.addActionListener(new MenuListener());
    setText("Archivo");
    add(abrir);
    add(guardar);
    add(acerca);
  }
  
  protected class MenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter bmp = new FileNameExtensionFilter(
          "Imágenes *.bmp ", "bmp");
      FileNameExtensionFilter tiff = new FileNameExtensionFilter(
          "Imágenes *.tiff ", "tiff");
      FileNameExtensionFilter png = new FileNameExtensionFilter(
          "Imágenes *.png ", "png");
      chooser.setFileFilter(bmp);
      chooser.setFileFilter(tiff);
      chooser.setFileFilter(png);
      
      if (e.getSource() == abrir){            
        int returnVal = chooser.showOpenDialog(getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          //TODO generar matriz e imagen a partir del archivo
           System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName() + chooser.getSelectedFile().getAbsolutePath() );
        } else if (returnVal == JFileChooser.ERROR_OPTION ){
          //TODO gestionara error al abrir archivo
          
        } 
        
      } else if (e.getSource() == guardar ){
        int returnVal = chooser.showSaveDialog(getParent());
            System.out.println(chooser.getSelectedFile().getAbsolutePath()
                + chooser.getFileFilter().getExtensions()[0]);
        
      }
      
    }

}
}
