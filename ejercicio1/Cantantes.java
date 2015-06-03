package ejercicio1;

public class Cantantes implements Comparable<Cantantes>{
	private String pais;
	private String interprete;
	private String cancion;
	private int puntuacion;
	public Cantantes(String pais, String interprete, String cancion,
			int puntuacion) {
		super();
		this.pais = pais;
		this.interprete = interprete;
		this.cancion = cancion;
		this.puntuacion = puntuacion;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getInterprete() {
		return interprete;
	}
	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}
	public String getCancion() {
		return cancion;
	}
	public void setCancion(String cancion) {
		this.cancion = cancion;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	@Override
	public String toString() {
		return pais + ";" + interprete
				+ ";" + cancion + ";" + puntuacion;
	}
	@Override
	public int compareTo(Cantantes o) {
		// TODO Auto-generated method stub
		return this.puntuacion-o.puntuacion;
	}
	
}
