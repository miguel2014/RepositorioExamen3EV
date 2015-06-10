package luisejer;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class IncidenciaTableModel  extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Incidencia> lista;
	private String [] cabecera={"Codigo Incidencia","Codigo Material","Descripcion"};
	
	
	/**
	 * @param lista
	 */
	public IncidenciaTableModel(List<Incidencia> lista) {
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
		return 3;
	}
	
	public String getColumnName(int numeroColumn){
		return cabecera[numeroColumn];
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Incidencia objeto = lista.get(rowIndex);
		String celda=(String) aValue;
		switch (columnIndex) {
		case 0:
			try {
				objeto.setCodigoIncidencia(Integer.parseInt(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;
		case 1 :
			try {
				objeto.setCodigoMaterial(Integer.parseInt(celda));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;
		case 2:
			objeto.setDescripcion(celda);
			break;
		default:
			
		}
	};
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	};

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Incidencia objeto = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return objeto.getCodigoIncidencia();
			
		case 1 :
			return objeto.getCodigoMaterial();
			
		case 2 :
			return objeto.getDescripcion();
	
		default:
			return "";
		}
	}

}
