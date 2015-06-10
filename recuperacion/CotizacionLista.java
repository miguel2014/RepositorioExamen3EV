package recuperacion;

import java.util.ArrayList;
import java.util.List;

public class CotizacionLista {
	List <Cotizacion> lista;
	public CotizacionLista() {
		lista =new ArrayList<Cotizacion>();
	}
	public void addCotizacion(Cotizacion c){
		lista.add(c);
	}
	
	public List<Cotizacion> getLista() {
		return lista;
	}
	public Cotizacion getPosicion(int i) {
		return lista.get(i);
	}
	public int getTamañoLista(){
		return lista.size();
	}
	public void setFecha(String fecha,int posicion){
		lista.get(posicion).setFecha(fecha);
	};
	public void setApertura(int apertura,int posicion){
		lista.get(posicion).setApertura(apertura);;
	};
	public void setMaximo(int maximo,int posicion){
		lista.get(posicion).setMaximo(maximo);
	};
	public void setMinimo(int minimo,int posicion){
		lista.get(posicion).setMinimo(minimo);
	};
	public void setCerrar(int cerrar,int posicion){
		lista.get(posicion).setCerrar(cerrar);
	};
	public void setVolumen(int volumen,int posicion){
		lista.get(posicion).setVolumen(volumen);
	};
	public void setAjustesCierre(int ajustes_cierre,int posicion){
		lista.get(posicion).setAjustes_cierre(ajustes_cierre);
	}
	@Override
	public String toString() {
		return "CotizacionLista [lista=" + lista + "]";
	};
	
}
