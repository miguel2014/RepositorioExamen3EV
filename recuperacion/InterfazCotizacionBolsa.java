package recuperacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingConstants;

public class InterfazCotizacionBolsa {

	private JFrame frame;
	private List<String> lista=new ArrayList<String>();//Lista Original
	private CotizacionLista listacotizacion=new CotizacionLista();
	private String campos[];
	private String texto;
	private JTable tabla;
	private String fecha;
	private double apertura;
	private double maximo;
	private double minimo;
	private double cerrar;
	private int volumen;
	private double ajustes_cierre;
	private LocalTime horaactual=LocalTime.now();
	private LocalDate diaactual=LocalDate.now();
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
				
					try (Scanner in=new Scanner(fichero);){
						in.useDelimiter("\t");
						while(in.hasNextLine()){
							texto=in.nextLine();
							texto=texto.replaceAll(",", ".");
							lista.add(texto);
						}
						lista.remove(0);
						String[] nombreColumnas = {"Fecha","Apertura","Maximo","Minimo","Cerrar","Volumen","Ajustes de cierre"};
						
	
						
						for (int i = 0; i < lista.size(); i++) {
							campos=lista.get(i).split("\t");
							fecha=campos[0];
							apertura=Double.parseDouble(campos[1]);
							maximo=Double.parseDouble(campos[2]);
							minimo=Double.parseDouble(campos[3]);
							cerrar=Double.parseDouble(campos[4]);
							volumen=Integer.parseInt(campos[5].replace(".",""));
							ajustes_cierre=Double.parseDouble(campos[6]);
							listacotizacion.addCotizacion(new Cotizacion(fecha, apertura, maximo, minimo, cerrar, volumen, ajustes_cierre));
						}

						//JTableModelCotizacion modelo=new JTableModelCotizacion(listacotizacion,nombreColumnas);
						
						tabla = new JTable();
						
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
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.csv", "csv");
				chooserguardar.setFileFilter(filtro);
				int returnval=chooserguardar.showSaveDialog(frame);
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Has de introducir datos");
				}
				else{
				if (returnval==JFileChooser.APPROVE_OPTION) {
					String formato=diaactual.getDayOfMonth()+"-"+diaactual.getMonthValue()+"-"+diaactual.getYear()+"_"+horaactual.getHour()+":"+horaactual.getMinute()+":"+
							horaactual.getSecond()+".csv";
					String outFile=chooserguardar.getSelectedFile()+" "+formato;
					try (PrintWriter out=new PrintWriter(outFile);){
							for (int i = 0; i < listacotizacion.getTamañoLista(); i++) {
								fecha=listacotizacion.getPosicion(i).getFecha();
								apertura=listacotizacion.getPosicion(i).getApertura();
								maximo=listacotizacion.getPosicion(i).getMaximo();
								minimo=listacotizacion.getPosicion(i).getMinimo();
								cerrar=listacotizacion.getPosicion(i).getCerrar();
								volumen=listacotizacion.getPosicion(i).getVolumen();
								ajustes_cierre=listacotizacion.getPosicion(i).getAjustes_cierre();
								texto=fecha+";"+apertura+";"+maximo+";"+minimo+";"+cerrar+";"+volumen+";"+ajustes_cierre+"\n";
								out.write(texto);
							}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
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
		JScrollPane scrollPane = new JScrollPane(tabla);
		
		frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
		
	}

}
