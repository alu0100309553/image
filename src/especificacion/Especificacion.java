package especificacion;

import Imagen.Imagen;

public class Especificacion {
  private Imagen original;
  private Imagen referencia;
  public Especificacion (Imagen origen, Imagen ref){
    original = origen;
    referencia = ref;
    especificar();
  }
  private void especificar(){
    int [] aux = new int [256];
    double [] origenP = normalizar (original);
    double [] referenciaP = normalizar (referencia);
    int j = 1;
    for (int i = 0; i < 256; i++){
      while (origenP[i]>referenciaP[j] && j < 255){
        j++;
      }
      if (Math.abs(origenP[i]-referenciaP[j]) <= Math.abs(origenP[i]-referenciaP[j-1])){
        aux [i] = j;
      } else {
        aux [i] = j-1;
      }
    }
    new Imagen (original, aux);
  }
  private double [] normalizar (Imagen img){
    double [] aux = new double [256];
    int [] imgAC = img.getHistACData().get(3);
    double size = img.sizeX()*img.sizeY();
    for (int i = 0; i < 256; i++){
      aux [i] = (double)imgAC [i]/ size;
    }
    return aux;
  }
}
