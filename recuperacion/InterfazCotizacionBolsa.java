package recuperacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class InterfazCotizacionBolsa {

	private JFrame frame;
	private List<String> lista;//Lista Original
	private CotizacionLista listacotizacion;
	private String campos[];
	private String texto;
	private JTable table;
	private double apertura;
	private double maximo;
	private double minimo;
	private double cerrar;
	private String volumen;;
	private double ajustes_cierre;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCotizacionBolsa window = new InterfazCotizacionBolsa();
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
	public InterfazCotizacionBolsa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuFichero = new JMenu("Fichero");
		menuBar.add(menuFichero);
		
		JMenuItem opcionFicheroAbrir = new JMenuItem("Abrir");
		opcionFicheroAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				int returnval=chooser.showOpenDialog(frame);
				if (returnval==JFileChooser.APPROVE_OPTION) {
					File fichero=chooser.getSelectedFile();
					lista=new ArrayList<String>();
					try (Scanner in=new Scanner(fichero);){
						in.useDelimiter("\t");
						while(in.hasNextLine()){
							texto=in.nextLine();
							texto=texto.replaceAll(",", ".");
							lista.add(texto);
						}
						lista.remove(0);
						listacotizacion=new CotizacionLista();
						System.out.println(lista);
						for (int i = 0; i < lista.size(); i++) {
							campos=lista.get(i).split("\t");
							String fecha=campos[0];
							apertura=Double.parseDouble(campos[1]);
							maximo=Double.parseDouble(campos[2]);
							minimo=Double.parseDouble(campos[3]);
							cerrar=Double.parseDouble(campos[4]);
							//volumen=Double.parseDouble(campos[5]);
							//ajustes_cierre=Double.parseDouble(campos[6]);
							//listacotizacion.addCotizacion(new Cotizacion(fecha, apertura, maximo, minimo, cerrar, volumen, ajustes_cierre));
						}
						System.out.println(listacotizacion);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		menuFichero.add(opcionFicheroAbrir);
		
		JMenuItem opcionFicheroGuardar = new JMenuItem("Guardar");
		opcionFicheroGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserguardar=new JFileChooser();
				int returnval=chooserguardar.showSaveDialog(frame);
				
			}
		});
		menuFichero.add(opcionFicheroGuardar);
		
		JMenuItem opcionFicheroSalir = new JMenuItem("Salir");
		opcionFicheroSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFichero.add(opcionFicheroSalir);
		
		JMenu menuAyuda = new JMenu("Ayuda");
		menuBar.add(menuAyuda);
		
		JMenuItem opcionAyudaAcercaDe = new JMenuItem("Acerca de");
		opcionAyudaAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Realizado por Miguel Angel Gutierrez Delgado");
			}
		});
		menuAyuda.add(opcionAyudaAcercaDe);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton botonAnterior = new JButton("Anterior");
		
		JButton botonSiguiente = new JButton("Siguiente");
		botonSiguiente.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton botonModificar = new JButton("Modificar");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(17)
					.addComponent(botonAnterior)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(botonSiguiente)
					.addGap(120)
					.addComponent(botonModificar)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(botonAnterior)
					.addComponent(botonSiguiente)
					.addComponent(botonModificar))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
	}

}
