package gamma;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Imagen.Imagen;

public class GammaWin extends JFrame {
  private Imagen original;
  private JTextField valor;
  private JLabel label;
  private JButton button;
  public GammaWin (Imagen origen){
    original = origen;
    this.setLayout(new GridLayout (1,3));
    this.setTitle("Correccion Gamma");
    label = new JLabel ("Valor Gamma:");
    valor = new JTextField ();
    button = new JButton ("Aplicar");
    button.addActionListener(new Listener());
    this.add(label);
    this.add(valor);
    this.add(button);
    this.setVisible(true);
    Dimension dim = new Dimension (300, 70);
    this.setSize(dim);
  }

  private class Listener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == button){
        new Gamma (Double.parseDouble(valor.getText()), original);
      }
      // TODO Auto-generated method stub

    } 
  }

}
