package recuperacion;

public class Cotizacion {
	private String fecha;
	private double apertura;
	private double maximo;
	private double minimo;
	private double cerrar;
	private  int volumen;
	private double ajustes_cierre;
	public Cotizacion(String fecha, double apertura, double maximo,
			double minimo, double cerrar, int volumen, double ajustes_cierre) {
		super();
		this.fecha = fecha;
		this.apertura = apertura;
		this.maximo = maximo;
		this.minimo = minimo;
		this.cerrar = cerrar;
		this.volumen = volumen;
		this.ajustes_cierre = ajustes_cierre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getApertura() {
		return apertura;
	}
	public void setApertura(double apertura) {
		this.apertura = apertura;
	}
	public double getMaximo() {
		return maximo;
	}
	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}
	public double getMinimo() {
		return minimo;
	}
	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}
	public double getCerrar() {
		return cerrar;
	}
	public void setCerrar(double cerrar) {
		this.cerrar = cerrar;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public double getAjustes_cierre() {
		return ajustes_cierre;
	}
	public void setAjustes_cierre(double ajustes_cierre) {
		this.ajustes_cierre = ajustes_cierre;
	}
	@Override
	public String toString() {
		return "Cotizacion [fecha=" + fecha + ", apertura=" + apertura
				+ ", maximo=" + maximo + ", minimo=" + minimo + ", cerrar="
				+ cerrar + ", volumen=" + volumen + ", ajustes_cierre="
				+ ajustes_cierre + "]";
	}
	
}