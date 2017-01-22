package espejo;

import java.util.ArrayList;

import Imagen.Imagen;

public class EspejoVert {
  Imagen original;
  Imagen resultado;
  public EspejoVert (Imagen org, boolean print){
    original = org;
    ArrayList<ArrayList<int[]>> orgdata = original.getData();
    ArrayList<ArrayList<int[]>> newdata = new ArrayList<ArrayList<int[]>>();
    for (int i = 0; i < original.sizeX(); i++){
      ArrayList<int[]> linea = new ArrayList<int[]>();
      for (int j = original.sizeY()-1; j > 0 ; j--){
        linea.add(orgdata.get(i).get(j));
      }
      newdata.add(linea);
    }
      resultado = new Imagen (newdata, print);
  }
  public Imagen getResultado () {
    return resultado;
  }
}
