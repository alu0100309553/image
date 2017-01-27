package filtros;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Imagen.Imagen;

public class Filtros extends JFrame {
  private int tamV;
  private int tamH;
  private JTextField celdasVJT = new JTextField ();
  private JTextField celdasHJT = new JTextField ();
  private JLabel celdasV = new JLabel ("Tamaño del filtro en vertical");
  private JLabel celdasH = new JLabel ("Tamaño del filtro en horizontal");
  private JPanel tamKernelPanel = new JPanel(new GridLayout (3,2));
  private JPanel defkernelPanel = new JPanel();
  private ArrayList<ArrayList <Valor>> kernel;
  private Imagen original;
  private JButton aplicar = new JButton ("Aplicar");
  private ArrayList<ArrayList<int[]>> newdata;
  private ArrayList<ArrayList<int[]>> orgdata;
  private int normFactor = 0;

  public class Valor {
    public int valor;
    private JTextField valorJT;
    Valor () {
      valorJT = new JTextField("0");
      valorJT.addActionListener(new Listener());
      valorJT.addFocusListener(new FocusList());
      valor = 0;
    }
    private class Listener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e) {
        valor = Integer.parseInt(valorJT.getText());

      }
    }

    private class FocusList implements FocusListener{

      @Override
      public void focusGained(FocusEvent e) {
      }

      @Override
      public void focusLost(FocusEvent e) {
        valor = Integer.parseInt(valorJT.getText());
      }
    }

  }
  public Filtros (Imagen origen) {

    original = origen;
    setLayout(new BorderLayout());
    setTitle("Filtros");
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screenSize);
    setVisible(true);
    celdasVJT.addActionListener(new JTChangeListener());
    celdasHJT.addActionListener(new JTChangeListener());
    tamKernelPanel.add(celdasV);
    tamKernelPanel.add(celdasVJT);
    tamKernelPanel.add(celdasH);
    tamKernelPanel.add(celdasHJT);
    add(tamKernelPanel, BorderLayout.NORTH);
    aplicar.addActionListener(new JTChangeListener());
    add(aplicar, BorderLayout.SOUTH);
    add(defkernelPanel, BorderLayout.CENTER);
  }

  private void rejilla () {
    kernel = new ArrayList<ArrayList <Valor>>();
    defkernelPanel.removeAll();
    defkernelPanel.setLayout(new GridLayout(tamV, tamH));
    for (int i = 0; i < tamH; i++){
      ArrayList <Valor> aux = new ArrayList <Valor>();
      for (int j = 0; j < tamV; j++){
        Valor celda = new Valor();
        aux.add(celda);
        defkernelPanel.add(celda.valorJT);

      }
      kernel.add(aux);
    }
    defkernelPanel.revalidate();
    defkernelPanel.repaint();
  }

  private void aplicar(){
    orgdata = original.getData();
    newdata = new ArrayList<ArrayList<int[]>>();
    // obtener factor de normalización del filtro
    normFactor = 0;
    for (int i = 0; i < tamH; i++){
      for (int j = 0; j < tamV; j++){
        normFactor += kernel.get(i).get(j).valor;
      }
    }
    
    if (normFactor == 0){
      JFrame info = new JFrame("Factor de Normalización");
      info.setLayout(new GridLayout(2,1));
      JTextArea text = new JTextArea ("El factor de normalización calculado es 0, indique uno distinto de 0." );
      JTextField val = new JTextField();
      JButton button = new JButton ("Aceptar");
      button.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent arg0) {
          normFactor = Integer.parseInt(val.getText());
          convolucionar();
        }
        
      });
      info.add(text);
      info.add(val);
      info.add(button);
      info.pack();
      //info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      info.setVisible(true);
    } else {
      convolucionar();
    }

    

  }
  
  private void convolucionar () {
    for (int i = ((tamH-1)/2) ; i < original.sizeX()-1-((tamH-1)/2); i++){
      ArrayList<int[]> line = new ArrayList <int[]>();
      for (int j = ((tamV-1)/2); j < original.sizeY()-1-((tamV-1)/2); j++){
        int auxR = 0, auxG = 0, auxB = 0, auxY = 0;
        for (int k = 0; k < tamH; k++){
          for (int l = 0; l < tamV; l++){
            int [] aux = original.getData(i+k-((tamH-1)/2), j+l-((tamV-1)/2));
            auxR += aux [0] * kernel.get(k).get(l).valor;
            auxG += aux [1] * kernel.get(k).get(l).valor;
            auxB += aux [2] * kernel.get(k).get(l).valor;
            auxY += aux [3] * kernel.get(k).get(l).valor;
          }
        }
        
        auxR /= normFactor;
        auxG /= normFactor;
        auxB /= normFactor;
        auxY /= normFactor;
        
        if (auxR < 0){
          auxR = 0;
        } else if (auxR > 255){
          auxR = 255;
        }
        if (auxG < 0){
          auxG = 0;
        } else if (auxG > 255){
          auxG = 255;
        }
        if (auxB < 0){
          auxB = 0;
        } else if (auxB > 255){
          auxB = 255;
        }
        if (auxY < 0){
          auxY = 0;
        } else if (auxY > 255){
          auxY = 255;
        }
        
        int [] pixel = {auxR, auxG, auxB, auxY};
        line.add(pixel);
      }
      newdata.add(line);
    }
    new Imagen (newdata, true);
  }
  
  private class JTChangeListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == celdasVJT){
        if (Integer.parseInt(celdasVJT.getText()) % 2 == 1 && Integer.parseInt(celdasHJT.getText()) % 2  == 1 ){
          tamV = Integer.parseInt(celdasVJT.getText());
          rejilla();
        } else {
          System.out.println("Tamaño Incorrecto");
        }
      } else if (e.getSource() == celdasHJT){
        if (Integer.parseInt(celdasVJT.getText()) % 2 == 1 && Integer.parseInt(celdasHJT.getText()) % 2  == 1 ){
          tamH = Integer.parseInt(celdasHJT.getText());
          rejilla();
        } else {
          System.out.println("Tamaño Incorrecto");
        }
      } else if (e.getSource()== aplicar){
        aplicar();
      }
    }
  }
}
