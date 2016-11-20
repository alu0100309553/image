package Imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Imagen {
  private BufferedImage image;
  private ArrayList <ArrayList <int[]>> data = new ArrayList <ArrayList <int[]>>();
  private int [] hr = new int [256];
  private int [] hg = new int [256];
  private int [] hb = new int [256];
  private int [] hy = new int [256];
  private int [] hrAC = new int [256];
  private int [] hgAC = new int [256];
  private int [] hbAC = new int [256];
  private int [] hyAC = new int [256];
  private int brillo = 0;
  private int contraste = 0;
  private double entropia = 0;
  private int min;
  private int max;


  public Imagen(Imagen origen, int [] transformacion){
    hr = origen.getHistData().get(0);
    hg = origen.getHistData().get(1);
    hb = origen.getHistData().get(2);
    for (int i = 0; i < origen.sizeX(); i++){
      ArrayList <int[]> aux = new ArrayList <int[]>();
      for (int j = 0; j < origen.sizeY(); j++){
        int valor = transformacion [origen.getData(i, j)[3]];
        int r = origen.getData(i, j)[0];
        int g = origen.getData(i, j)[1];
        int b = origen.getData(i, j)[2];
        int [] rgb = {r, g, b, valor};
        hy[valor]++;
        aux.add(rgb);
      }
      data.add(aux);
    }
    acHist();
    brillo();
    contraste();
    entropia();
    min();
    max();
    new ImageWindow(this);
  }

  public Imagen(Imagen origen, int xi, int yi, int xf, int yf){
    for (int i = xi; i <= xf; i++){
      ArrayList <int[]> aux = new ArrayList <int[]>();
      for (int j = yi; j <= yf; j++){
        aux.add(origen.getData(i, j));
        hr[origen.getData(i, j)[0]]++;
        hg[origen.getData(i, j)[1]]++;
        hb[origen.getData(i, j)[2]]++;
        hy[origen.getData(i, j)[3]]++;
      }
      data.add(aux);
    }
    acHist();
    brillo();
    contraste();
    entropia();
    min();
    max();
    new ImageWindow(this);
  }

  public Imagen(String path){
    try {
      image = ImageIO.read (new File(path));
      for (int i = 0; i < image.getWidth(); i++){
        ArrayList <int[]> aux = new ArrayList <int[]>();
        //ArrayList <Integer> auxgray = new ArrayList <Integer>();
        for (int j = 0; j < image.getHeight(); j++){
          Color pixel= new Color(image.getRGB(i, j));
          int r = pixel.getRed();
          int g = pixel.getGreen();
          int b = pixel.getBlue();
          int gray = (int)(((double)r*0.2126)+((double)g*0.7152)+((double)b*0.0722));
          int [] rgb = {r, g, b, gray};
          hr[r]++;
          hg[g]++;
          hb[b]++;
          hy[gray]++;
          aux.add(rgb);
        }
        data.add(aux);
      }
      acHist();
      brillo();
      contraste();
      entropia();
      min();
      max();
      new ImageWindow(this);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public Imagen(String path, boolean print){
    try {
      image = ImageIO.read (new File(path));
      for (int i = 0; i < image.getWidth(); i++){
        ArrayList <int[]> aux = new ArrayList <int[]>();
        //ArrayList <Integer> auxgray = new ArrayList <Integer>();
        for (int j = 0; j < image.getHeight(); j++){
          Color pixel= new Color(image.getRGB(i, j));
          int r = pixel.getRed();
          int g = pixel.getGreen();
          int b = pixel.getBlue();
          int gray = (int)(((double)r*0.2126)+((double)g*0.7152)+((double)b*0.0722));
          int [] rgb = {r, g, b, gray};
          hr[r]++;
          hg[g]++;
          hb[b]++;
          hy[gray]++;
          aux.add(rgb);
        }
        data.add(aux);
      }
      acHist();
      brillo();
      contraste();
      entropia();
      min();
      max();
      if (print){
        new ImageWindow(this);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  public int[] getData (int x, int y){
    return data.get(x).get(y);
  }
  public int sizeX (){
    return data.size();
  }
  public int sizeY (){
    return data.get(0).size();
  }
  public ArrayList <int[]> getHistData(){
    ArrayList<int[]> aux = new ArrayList<int[]>();
    aux.add(hr);
    aux.add(hg);
    aux.add(hb);
    aux.add(hy);
    return aux;
  }
  public ArrayList <int[]> getHistACData(){
    ArrayList<int[]> aux = new ArrayList<int[]>();
    aux.add(hrAC);
    aux.add(hgAC);
    aux.add(hbAC);
    aux.add(hyAC);
    return aux;
  }
  private void brillo (){
    int acumulador = 0;
    for (int i = 0; i < 256; i++){
      acumulador += hy[i]*i; 
    }
    brillo = acumulador/(sizeX()*sizeY());

  }
  private void contraste (){
    double acumulador = 0;
    for (int i = 0; i < 256; i++){
      acumulador += (double) hy[i]*Math.pow(((double)i-brillo),2);
    }
    contraste = (int)Math.sqrt(acumulador/(sizeX()*sizeY()));
  }
  public int getBrillo (){
    return brillo;
  }
  public int getContraste (){
    return contraste;
  }
  private void min(){
    boolean buscando = true;
    int i = 0;
    while (buscando){
      if (hy[i]>0){
        min = i;
        buscando = false;
      }
      i++;
    }
  }
  private void max(){
    boolean buscando = true;
    int i = 255;
    while (buscando){
      if (hy[i]>0){
        max = i;
        buscando = false;
      }
      i--;
    }
  }
  public int getMax () {
    return max;
  }
  public int getMin () {
    return min;
  }
  private void acHist () {
    int acR = 0;
    int acG = 0;
    int acB = 0;
    int acY = 0;
    for (int i = 0; i < 256; i++){
      acR += hr[i];
      acG += hg[i];
      acB += hb[i];
      acY += hy[i];
      hrAC[i]= acR;
      hgAC[i]= acG;
      hbAC[i]= acB;
      hyAC[i]= acY;
    }
  }
  private void entropia (){
    double size = (double)(sizeX()*sizeY());
    double acumulador = 0;
    for (int i = 0; i < 256; i++){
      double pi = ((double)hy[i])/size;
      if (pi > 0){
        acumulador += pi*(Math.log(pi)/Math.log(2));
      }
    } 
    entropia = acumulador * -1;
  }
  public double getEntropia (){
    return entropia;
  }


}
