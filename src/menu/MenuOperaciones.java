package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import Imagen.Imagen;
import diferencia.Diferencia;

public class MenuOperaciones extends JMenu {
  private JMenuItem diferencia = new JMenuItem("Diferencia");

  MenuOperaciones (){
    diferencia.addActionListener(new MenuListener());
    setText("Operaciones");
    add(diferencia);
  }
  
  protected class MenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      
      if (e.getSource() == diferencia){            
        new Diferencia();
      } 
      
    }

}
}
