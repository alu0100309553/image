package alfaBlending;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Imagen.Imagen;
import ecualizacion.Ecualizacion;
import especificacion.Especificacion;
import histograma.HistWindow;

public class AlfaBlending extends JFrame {
  private JButton imgA = new JButton ("IBG");
  private JButton imgB = new JButton ("IFG");
  private JButton calcular = new JButton ("Calcular");
  private JTextField imgAJT = new JTextField ();
  private JTextField imgBJT = new JTextField ();
  private JTextField alphaJT = new JTextField ();
  private JLabel alphaLabel = new JLabel ("Alfa");
  private JPanel filesel = new JPanel(new GridLayout(2,2));
  private JPanel filesoptions = new JPanel(new GridLayout(4,1));
  private Imagen imagenA;
  private Imagen imagenB;
  private ArrayList<ArrayList<int[]>> resultado = new ArrayList<ArrayList<int[]>>();
  private JFrame frame = new JFrame();

  public AlfaBlending () {
    frame.setLayout(new BorderLayout());
    imgA.addMouseListener(new MyMouseListener());
    imgB.addMouseListener(new MyMouseListener());
    filesel.add(imgAJT);
    filesel.add(imgBJT);
    filesel.add(imgA);
    filesel.add(imgB);
    filesoptions.add(filesel);
    filesoptions.add(alphaLabel);
    filesoptions.add(alphaJT);
    calcular.addMouseListener(new MyMouseListener());
    filesoptions.add(calcular);
    frame.add(filesoptions, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }

  protected class MyMouseListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter bmp = new FileNameExtensionFilter(
          "Imágenes *.bmp ", "bmp");
      FileNameExtensionFilter tif = new FileNameExtensionFilter(
          "Imágenes *.tif ", "tif");
      FileNameExtensionFilter png = new FileNameExtensionFilter(
          "Imágenes *.png ", "png");
      chooser.setFileFilter(png);
      chooser.setFileFilter(tif);
      chooser.setFileFilter(bmp);
      if (e.getSource() == imgA){
        int returnVal = chooser.showOpenDialog(getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          imgAJT.setText(chooser.getSelectedFile().getName());
          imagenA = new Imagen(chooser.getSelectedFile().getAbsolutePath(),false);
        } else if (returnVal == JFileChooser.ERROR_OPTION ){
          //TODO gestionar error al abrir archivo

        } 
      } else if (e.getSource() == imgB){
        int returnVal = chooser.showOpenDialog(getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
          imgBJT.setText(chooser.getSelectedFile().getName());
          imagenB = new Imagen(chooser.getSelectedFile().getAbsolutePath(),false);
        } else if (returnVal == JFileChooser.ERROR_OPTION ){
          //TODO gestionar error al abrir archivo

        } 
      } else if (e.getSource() == calcular){
        ArrayList<ArrayList<int[]>> imA = imagenA.getData();
        ArrayList<ArrayList<int[]>> imB = imagenB.getData();
        resultado = new ArrayList<ArrayList<int[]>>();
        Double alfa = Double.parseDouble(alphaJT.getText());

        for (int i = 0; i < imagenA.sizeX(); i++){
          ArrayList <int[]> aux = new ArrayList <int[]>();
          for (int j = 0; j < imagenA.sizeY(); j++){
            int r = (int) ((imA.get(i).get(j)[0] * alfa)+(imB.get(i).get(j)[0] *(1-alfa)));
            int g = (int) ((imA.get(i).get(j)[1] * alfa)+(imB.get(i).get(j)[1] *(1-alfa)));
            int b = (int) ((imA.get(i).get(j)[2] * alfa)+(imB.get(i).get(j)[2] *(1-alfa)));
            int y = (int) ((imA.get(i).get(j)[3] * alfa)+(imB.get(i).get(j)[3] *(1-alfa)));
            int [] rgb = {r, g, b, y};
            aux.add(rgb);

          }
          resultado.add(aux);
        }
        new Imagen (resultado, true);
      }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }
  }


}
