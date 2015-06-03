package ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class ListaEurovision {
	List <Cantantes> lista;
	public ListaEurovision() {
		lista =new ArrayList<Cantantes>();
	}
	
	public void addCantante(Cantantes c){
		lista.add(c);
	}
	public Cantantes getPosicion(int i) {
		return lista.get(i);
	}
	public int getTamañoLista(){
		return lista.size();
	}
	public void setPaisLista(String pais,int posicion){
		lista.get(posicion).setPais(pais);
	}
	public void setCancionLista(String cancion,int posicion){
		lista.get(posicion).setCancion(cancion);
	}
	public void setInterpreteLista(String interprete,int posicion){
		lista.get(posicion).setInterprete(interprete);
	}
	public void setPuntuacionLista(int puntuacion,int posicion){
		lista.get(posicion).setPuntuacion(puntuacion);;
	}
	public boolean isListaVacia(){
		System.out.println(lista.isEmpty());
		return lista.isEmpty();
	}
	public void borrarLista(){
		 lista.clear();;
	}


	@Override
	public String toString() {
		return "ListaEurovision [lista=" + lista + "]";
	}
	
	

}
