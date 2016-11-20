package gamma;

import Imagen.Imagen;

public class Gamma {
  private Imagen original;
  private double gamma;
  public Gamma (double g, Imagen Origen){
    original = Origen;
    gamma = g;
    calGamma();
  }
  private void calGamma () {
    int[] aux = new int [256];
    for (int i = 0; i < 256; i++){
      double a = (double)i/(double)255;
      double b = Math.pow(a, gamma);
      aux [i] = Math.round(Math.round(b*(double)255));
    }
    new Imagen (original, aux);
  } 
}
