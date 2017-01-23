package rotacion;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Imagen.Imagen;

public class RotDirecta extends JFrame {
  private Imagen original;
  private JButton aplicar = new JButton ("Aplicar");
  private JRadioButton interpolar = new JRadioButton ("Bi-Lineal");
  private JRadioButton masProximo = new JRadioButton ("Vecino más proximo");
  private JPanel metodo = new JPanel ();
  private JPanel angPanel = new JPanel ();
  private ButtonGroup metodos = new ButtonGroup();
  private JTextField angulo = new JTextField ();
  private JLabel textoAngulo = new JLabel ("Ángulo de rotación");
  private ArrayList<ArrayList<int[]>> newdata;
  private ArrayList<ArrayList<int[]>> orgdata;
  public RotDirecta (Imagen original){
    this.original = original;    
    setLayout(new GridLayout (3,1));
    setTitle("Rotación Directa");
    aplicar.addActionListener(new myActionListener());
    angPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Ángulo"));
    angPanel.setLayout(new GridLayout(1,2));
    angulo.setToolTipText("Ángulo en que se rotará la imágen");
    angPanel.add(textoAngulo);
    angPanel.add(angulo);
    add(angPanel);
    interpolar.setSelected(true);
    metodos.add(interpolar);
    metodos.add(masProximo);
    metodo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Método de interpolación"));
    metodo.add(interpolar);
    metodo.add(masProximo);
    add(metodo);
    add(aplicar);
    setVisible(true);
    pack();
  }
  
  private Point rotacion (Point org, double ang ){
	  return new Point((int)((org.x * Math.cos(ang))+(org.y * -Math.sin(ang))), (int)((org.x * Math.sin(ang))+(org.y * Math.cos(ang))));
  }
  private void rotar () {
	  //Calculando el cuadrado que contiene a la imágen rotada.
	  int maxX = Integer.MIN_VALUE;
	  int maxY = Integer.MIN_VALUE;
	  int minX = Integer.MAX_VALUE;
	  int minY = Integer.MAX_VALUE;
	  for (int i = 0; i < original.sizeX(); i+= original.sizeX()-1){
		  for (int j = 0; j < original.sizeY(); j += original.sizeY()-1){
			  Point punto = rotacion (new Point (i,j), Double.parseDouble(angulo.getText()));
			  if (punto.x > maxX){
				  maxX = punto.x;
			  }
			  if (punto.x < minX){
				  minX = punto.x;
			  }
			  if (punto.y > maxY){
				  maxX = punto.y;
			  }
			  if (punto.y < minY){
				  minX = punto.y;
			  }
		  }
	  }
	  int sizeX = maxX-minX;
	  int sizeY = maxY-minY;
	  new Point2D.Double(2.265635325, 3.26546523);
	  if (interpolar.isSelected()){
          bilineal();
        } else {
          masproximo();
        }
  }
  
  public void bilineal () {
	  /*
    orgdata = original.getData();
    newdata = new ArrayList<ArrayList<int[]>>();
    float escX = Float.parseFloat(escalaH.getText())/100;
    float escY= Float.parseFloat(escalaV.getText())/100;
    int x =(int) ((original.sizeX()-1) * escX);
    int y =(int) ((original.sizeY()-1) * escY);
    for (int i = 0; i < x; i++){
      int iesc = (int)(i/escX);
      ArrayList<int[]> line = new ArrayList <int[]>();
      for (int j = 0; j < y; j++){
        int [] values = new int [4];
        int jesc = (int)(j/escY);
        int [] a = orgdata.get(iesc).get(jesc+1);
        int [] b = orgdata.get(iesc+1).get(jesc+1);
        int [] c = orgdata.get(iesc).get(jesc);
        int [] d = orgdata.get(iesc+1).get(jesc);
        float q = ((float)j/escY) -(float)jesc;
        float p = ((float)i/escX) -(float)iesc;
        for (int k = 0; k < 4; k++){
          values [k] = (int)(c[k]+((d[k]-c[k])*p) + ((a[k]-c[k])*q) + ((a[k]+c[k]-a[k]-d[k])*p*q));
        }
        line.add(values);
      }
      newdata.add(line);
    }
    */
    new Imagen (newdata, true);
  }
  
  public void masproximo () {
  /*  orgdata = original.getData();
    newdata = new ArrayList<ArrayList<int[]>>();
    float escX = Float.parseFloat(escalaH.getText())/100;
    float escY= Float.parseFloat(escalaV.getText())/100;
    int x =(int) (original.sizeX() * escX);
    int y =(int) (original.sizeY() * escY);
    for (int i = 0; i < x; i++){
      int iesc = (int)(i/escX);
      ArrayList<int[]> line = new ArrayList <int[]>();
      for (int j = 0; j < y; j++){
        int jesc = (int)(j/escY);
        line.add(orgdata.get(iesc).get((int)(jesc)));
      }
      newdata.add(line);
    }
    */
    new Imagen (newdata, true);
  }
  
  
  private class myActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == aplicar){
        if (interpolar.isSelected()){
          bilineal();
        } else {
          masproximo();
        }
      }
    
    }
  }

}
