package recuperacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class JTableModelCotizacion extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cotizacion> lista;
	String [] nombreColumnas;
	
	public JTableModelCotizacion(List<Cotizacion> lista,String[] nombreColumnas) {
		lista=new ArrayList<Cotizacion>();
		this.nombreColumnas=nombreColumnas;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
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
		}
		return objeto;
		
	}
	
	@Override
	public boolean isCellEditable(int fila, int columna) {
		// TODO Auto-generated method stub
		return true;
	}

}
