package luisejer;


public class Incidencia {

	private int codigoIncidencia;
	private int codigoMaterial;
	private String descripcion;
	/**
	 * @param codigoIncidencia
	 * @param codigoMaterial
	 * @param descripcion
	 */
	public Incidencia(int codigoIncidencia,int codigoMaterial,
			String descripcion) {
		this.codigoIncidencia = codigoIncidencia;
		this.codigoMaterial = codigoMaterial;
		this.descripcion = descripcion;
	}
	/**
	 * @return the codigoIncidencia
	 */
	public int getCodigoIncidencia() {
		return codigoIncidencia;
	}
	/**
	 * @param codigoIncidencia the codigoIncidencia to set
	 */
	public void setCodigoIncidencia(int codigoIncidencia) {
		this.codigoIncidencia = codigoIncidencia;
	}
	/**
	 * @return the codigoMaterial
	 */
	public int getCodigoMaterial() {
		return codigoMaterial;
	}
	/**
	 * @param codigoMaterial the codigoMaterial to set
	 */
	public void setCodigoMaterial(int codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Incidencias [codigoIncidencia=" + codigoIncidencia
				+ ", codigoMaterial=" + codigoMaterial + ", descripcion="
				+ descripcion + "]";
	}
	
	
}
