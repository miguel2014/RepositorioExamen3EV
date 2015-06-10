package recuperacion;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class JTableModelCotizacion extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cotizacion> lista;
	private String[] nombreColumnas = {"Fecha","Apertura","Maximo","Minimo","Cerrar","Volumen","Ajustes de cierre"};
	private int numfilas=3;
	private int indice=0;

	public JTableModelCotizacion(List<Cotizacion> lista) {
		this.lista = lista;
	}
	

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	public void siguienteFuncion(){
		if (indice+numfilas<lista.size()) {
			indice+=numfilas;
			fireTableChanged(null);
		}
		
	}
	
	public void anteriorFuncion(){
		if (indice-numfilas>=0){
			indice-=numfilas;
			fireTableChanged(null);
		}
		
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return nombreColumnas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Cotizacion objeto=lista.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return objeto.getFecha();
		case 1:
			return objeto.getApertura();
		case 2:
			return objeto.getMaximo();
		case 3:
			return objeto.getMinimo();
		case 4:
			return objeto.getCerrar();
		case 5:
			return objeto.getVolumen();
		case 6:
			return objeto.getAjustes_cierre();
		default:
			return "";
		}
		
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cotizacion objeto = lista.get(rowIndex);
		String celda=(String) aValue;
		switch (columnIndex) {
		case 0:
			objeto.setFecha(celda);			
			break;
		case 1 :
			try {
				objeto.setApertura(Double.parseDouble(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;
		case 2:
			try {
				objeto.setMaximo(Double.parseDouble(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case 3:
			try {
				objeto.setMinimo(Double.parseDouble(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
		case 4:
			try {
				objeto.setCerrar(Double.parseDouble(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
		case 5:
			try {
				objeto.setVolumen(Integer.parseInt(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
		case 6:
			try {
				objeto.setAjustes_cierre(Double.parseDouble(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
		default:
			
		}
	};
	
		
	@Override
	public boolean isCellEditable(int fila, int columna) {
		// TODO Auto-generated method stub
		return true;
	}

}
