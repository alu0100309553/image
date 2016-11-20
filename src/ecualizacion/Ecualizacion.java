package ecualizacion;

import Imagen.Imagen;

public class Ecualizacion {
	private Imagen original;
	public Ecualizacion (Imagen origen){
		original = origen;
		ecualizar();
	}
	private void ecualizar () {
		int [] aux = new int [256];
		int [] origenAC = original.getHistACData().get(3);
		double size = original.sizeX()*original.sizeY();
		double m = (double)256/size;
		for (int i = 0; i < 256; i++){
			double c0 = (double) origenAC [i];
			aux [i] = Math.max(0,((int)((c0*m)))-1);
		}
		new Imagen (original, aux);
	}

}
