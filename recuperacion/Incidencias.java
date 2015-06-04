package recuperacion;

public class Incidencias {
	private int codigoIncidencia;
	private int codigoMaterial;
	private String descripcion;
	public Incidencias(int codigoIncidencia, int codigoMaterial,
			String descripcion) {
		super();
		this.codigoIncidencia = codigoIncidencia;
		this.codigoMaterial = codigoMaterial;
		this.descripcion = descripcion;
	}
	public int getCodigoIncidencia() {
		return codigoIncidencia;
	}
	public void setCodigoIncidencia(int codigoIncidencia) {
		this.codigoIncidencia = codigoIncidencia;
	}
	public int getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(int codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Incidencias [codigoIncidencia=" + codigoIncidencia
				+ ", codigoMaterial=" + codigoMaterial + ", descripcion="
				+ descripcion + "]";
	}
	
}
