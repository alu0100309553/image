package especificacion;

import Imagen.Imagen;

public class Especificacion {
  private Imagen original;
  private Imagen referencia;
  private boolean pintar = true;
  private Imagen resultado;
  public Especificacion (Imagen origen, Imagen ref){
    original = origen;
    referencia = ref;
    especificar();
  }
  
  public Especificacion (Imagen origen, Imagen ref, boolean pintar){
	    original = origen;
	    referencia = ref;
	    this.pintar = pintar;
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
    resultado = new Imagen (original, aux, pintar);
  }
  
  public Imagen getResultado () {
	  return resultado;
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
