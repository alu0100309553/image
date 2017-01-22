package espejo;

import java.util.ArrayList;

import Imagen.Imagen;

public class EspejoHor {
  Imagen original;
  Imagen resultado;
  public EspejoHor (Imagen org, boolean print){
    original = org;
    ArrayList<ArrayList<int[]>> orgdata = original.getData();
    ArrayList<ArrayList<int[]>> newdata = new ArrayList<ArrayList<int[]>>();
    for (int i =  original.sizeX()-1; i >= 0; i--){
      newdata.add(orgdata.get(i));
    }
    resultado = new Imagen (newdata, print);
  }
  public Imagen getResultado () {
    return resultado;
  }
}
