package ecualizacion;

import Imagen.Imagen;

public class Ecualizacion {
	private Imagen original;
	private boolean pintar = true;
	private Imagen resultado;
	public Ecualizacion (Imagen origen){
		original = origen;
		ecualizar();
	}
	public Ecualizacion (Imagen origen, boolean pintar){
		original = origen;
		this.pintar = pintar;
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
		resultado = new Imagen (original, aux, pintar);
	}
	public Imagen getResultado () {
		return resultado;
	}

}
