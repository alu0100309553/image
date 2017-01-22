package rotacion;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import Imagen.Imagen;

public class RotEntera extends JFrame{
  private ArrayList<ArrayList<int[]>> newdata;
  private ArrayList<ArrayList<int[]>> orgdata;
  private Imagen original;
  private JRadioButton deg90 = new JRadioButton ("90º");
  private JRadioButton deg180 = new JRadioButton ("180º");
  private JRadioButton deg270 = new JRadioButton ("270º");
  private ButtonGroup buttonGroup = new ButtonGroup();
  private JButton rotar = new JButton ("Rotar");


  public RotEntera (Imagen original){
    this.original = original;    
    setLayout(new FlowLayout());
    setTitle("Rotación");
    deg90.setSelected(true);
    buttonGroup.add(deg90);
    buttonGroup.add(deg180);
    buttonGroup.add(deg270);
    add(deg90);
    add(deg180);
    add(deg270);
    rotar.addActionListener(new myActionListener());
    add(rotar);
    setVisible(true);
    pack();
  }
  private void rotacion90 (){
    orgdata = original.getData();
    newdata = new ArrayList<ArrayList<int[]>>();
    for (int i = original.sizeY()-1; i >= 0; i--){
      ArrayList<int[]> linea = new ArrayList<int[]>();
      for (int j = 0; j < original.sizeX(); j++){
        linea.add(orgdata.get(j).get(i));
      }
      newdata.add(linea);
    }
    new Imagen (newdata, true);
  }
  private void rotacion180 (){
    orgdata = original.getData();
    newdata = new ArrayList<ArrayList<int[]>>();
    for (int i = original.sizeX()-1; i >= 0; i--){
      ArrayList<int[]> linea = new ArrayList<int[]>();
      for (int j = original.sizeY()-1; j >= 0; j--){
        linea.add(orgdata.get(i).get(j));
      }
      newdata.add(linea);
    }
    new Imagen (newdata, true);
  }
  private void rotacion270 (){
    orgdata = original.getData();
    newdata = new ArrayList<ArrayList<int[]>>();
    for (int i = 0; i < original.sizeY(); i++){
      ArrayList<int[]> linea = new ArrayList<int[]>();
      for (int j = original.sizeX()-1; j >= 0; j--){
        linea.add(orgdata.get(j).get(i));
      }
      newdata.add(linea);
    }
    new Imagen (newdata, true);
  }
  private class myActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == rotar){
        if (deg90.isSelected()){
          rotacion90();
        }else if (deg180.isSelected()){
          rotacion180();
        }else if (deg270.isSelected()){
          rotacion270();
        }
      }
    }
  }
}
