package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GUI extends JFrame {
  protected JFrame frame = new JFrame();
  private MenuBar menu = new MenuBar();

  GUI() {

    frame.setLayout(new BorderLayout());
    frame.setTitle("Image");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize);
    frame.setJMenuBar(menu);
    frame.add(new InfoPanel(), BorderLayout.SOUTH);
    frame.setVisible(true);

  }

}