package pruebas;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

public class TablaPrueba {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaPrueba window = new TablaPrueba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TablaPrueba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		List <Incidencias> lista=new ArrayList<Incidencias>();
		Incidencias i1=new Incidencias(0,01, "fallo");
		Incidencias i2=new Incidencias(1,02, "fallo");
		Incidencias i3=new Incidencias(2,03, "fallo");
		Incidencias i4=new Incidencias(3,04, "fallo");
		Incidencias i5=new Incidencias(4,05, "fallo");
		lista.add(i1);lista.add(i2);lista.add(i3);lista.add(i4);lista.add(i5);
		
		IncidenciaTableModel modelo=new IncidenciaTableModel(lista);
		
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.CENTER);
		
		
		
		
	}

}
