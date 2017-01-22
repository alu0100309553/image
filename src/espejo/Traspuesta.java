package espejo;

import Imagen.Imagen;

public class Traspuesta {
  public Traspuesta (Imagen org, boolean print){
    EspejoHor hor = new EspejoHor (org, false);
    new EspejoVert (hor.getResultado(),print);
  }
}
