package menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
  private MenuArchivo archivo;
  private MenuInfo inf;
  private MenuLineales lineales;
  private MenuNoLineales noLineales;
  public MenuBar (){
    archivo = new MenuArchivo();
    inf = new MenuInfo();
    lineales = new MenuLineales ();
    noLineales = new MenuNoLineales ();
    add(archivo);
    add(lineales);
    add(noLineales);
    add(inf);
  }

}
