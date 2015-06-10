package luisejer;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;


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
		
		
		
		List<Incidencia> lista = new ArrayList<Incidencia>();
		
		Incidencia i1 = new Incidencia(1, 1, "fallo en el raton");
		Incidencia i2 = new Incidencia(2, 2, "pantalla rota");
		Incidencia i3 = new Incidencia(3, 3, "falta portatil");
		
		lista.add(i1);lista.add(i2);lista.add(i3);
		
		IncidenciaTableModel modeloTabla = new IncidenciaTableModel(lista);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
		
		

		
	}

}
