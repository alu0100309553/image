package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
  private MenuArchivo Archivo;
  private JMenu Lineales;
  MenuBar (){
    Archivo = new MenuArchivo();
    Lineales = new JMenu ("Op Lineales");
    add(Archivo);
    add(Lineales);
  }

}
