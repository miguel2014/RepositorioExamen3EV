package recuperacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class IncidenciaTableModel  extends AbstractTableModel{
	private List<Incidencias> lista;
	private String[] cabecera={"codigoIncidencia","codigoMaterial","descripcion"};
	public IncidenciaTableModel(List<Incidencias> lista) {
		lista = new ArrayList<Incidencias>();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Incidencias objeto=lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return objeto.getCodigoIncidencia();
		case 1:
			return objeto.getCodigoMaterial();
		case 2:
			return objeto.getDescripcion();
		}
		return objeto;
	}
	public String getColumnsName(int numerocolumna){
		return cabecera[numerocolumna];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Incidencias objeto=lista.get(rowIndex);
		String celda=(String) aValue;
		switch (columnIndex) {
		case 0:
			objeto.setCodigoIncidencia(Integer.parseInt(celda));
		case 1:
			objeto.setCodigoMaterial(Integer.parseInt(celda));
		case 2:
			objeto.setDescripcion(celda);
		};
			
	}
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return true;	
	}
	
	

}
