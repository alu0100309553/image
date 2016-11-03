package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Imagen.Imagen;
import menu.MenuBar;

public class GUI extends JFrame {
  protected JFrame frame = new JFrame();
  private MenuBar menu = new MenuBar();
  private static Imagen activa;

  public GUI() {

    frame.setLayout(new BorderLayout());
    frame.setTitle("Image");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize);
    frame.setJMenuBar(menu);
    frame.setVisible(true);

  }
  public static void setActiva (Imagen img){
	  activa = img;
  }

  public static Imagen getActiva (){
	  return activa;
  }
}