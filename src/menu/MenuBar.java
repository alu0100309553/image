package menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
  private MenuArchivo archivo;
  private MenuInfo inf;
  private MenuLineales lineales;
  private MenuNoLineales noLineales;
  private MenuOperaciones operaciones;
  public MenuBar (){
    archivo = new MenuArchivo();
    inf = new MenuInfo();
    lineales = new MenuLineales ();
    noLineales = new MenuNoLineales ();
    operaciones = new MenuOperaciones ();
    add(archivo);
    add(lineales);
    add(noLineales);
    add(inf);
    add(operaciones);
  }

}
