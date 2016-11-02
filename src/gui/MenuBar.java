package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
  private MenuArchivo archivo;
  private MenuInfo inf;
  private JMenu lineales;
  MenuBar (){
    archivo = new MenuArchivo();
    inf = new MenuInfo();
    lineales = new JMenu ("Op Lineales");
    add(archivo);
    add(lineales);
    add(inf);
  }

}
