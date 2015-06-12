package recuperacion;


import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;

public class InterfazCotizacion {

	private JFrame frame;
	private List<String> lista=new ArrayList<String>();//Lista Original
	private CotizacionLista listacotizacion=new CotizacionLista();
	private String campos[];
	private String texto;
	private String fecha;
	private double apertura;
	private double maximo;
	private double minimo;
	private double cerrar;
	private int volumen;
	private double ajustes_cierre;
	private LocalTime horaactual=LocalTime.now();
	private LocalDate diaactual=LocalDate.now();
	private JTable table;
	private JTableModelCotizacion modelotabla;
	JScrollPane scrollPane;
	private int contador=0;
	private JLabel registrolabel;
	private int numfilas=3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCotizacion window = new InterfazCotizacion();
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
	public InterfazCotizacion() {
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
						in.useDelimiter("\t");//Saltos de linea como delimitador
						while(in.hasNextLine()){
							texto=in.nextLine();
							texto=texto.replaceAll(",", ".");//Para poder convertirlo a double
							lista.add(texto);
						}
						//Nos daria fallo la primera linea ya que es un String y a la hora de añadirla a la lista 
						//de cotizacion daria error por lo que lo eliminamos
						lista.remove(0);
						
	
						
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
						contador++;
						registrolabel.setText("Registro " +contador+" de "+(listacotizacion.getTamañoLista()/numfilas));
						modelotabla=new JTableModelCotizacion(listacotizacion.getLista());
						table = new JTable(modelotabla);
						scrollPane.setViewportView(table);
						
						
						
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
					String cabecera="Fecha;Apertura;Maximo;Minimo;Cerrar;Volumen;Ajustes de cierre\n";
					String outFile=chooserguardar.getSelectedFile()+" "+formato;
					try (PrintWriter out=new PrintWriter(outFile);){
						out.write(cabecera);	
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
		
		Component horizontalStrut = Box.createHorizontalStrut(179);
		menuBar.add(horizontalStrut);
		
		registrolabel = new JLabel("Registro " +contador+" de "+(listacotizacion.getTamañoLista()/numfilas));
		menuBar.add(registrolabel);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton botonAnterior = new JButton("Anterior");
		botonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelotabla.anteriorFuncion();	
					if (contador<2) {
						
					}
					else{
						contador--;
					}
					registrolabel.setText("Registro " +contador+" de "+(listacotizacion.getTamañoLista()/numfilas));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		JButton botonSiguiente = new JButton("Siguiente");
		botonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelotabla.siguienteFuncion();
					if (contador==listacotizacion.getTamañoLista()/3) {
						
					}
					else{
						contador++;
					}
					registrolabel.setText("Registro " +contador+" de "+(listacotizacion.getTamañoLista()/numfilas));
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		botonSiguiente.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				
				modelotabla = (JTableModelCotizacion) table.getModel();  
				int fils = modelotabla.getRowCount(); 
				for(int i=0; i<fils; i++) { 
				
				fecha=(String) modelotabla.getValueAt(i,0); 
				apertura=(double) modelotabla.getValueAt(i,1);
				minimo=(double) modelotabla.getValueAt(i,2);
				maximo=(double) modelotabla.getValueAt(i,3);
				cerrar=(double) modelotabla.getValueAt(i,4);
				volumen=(int) modelotabla.getValueAt(i, 5);
				ajustes_cierre=(double) modelotabla.getValueAt(i,6);
				listacotizacion.setFecha(fecha, i);
				listacotizacion.setApertura(apertura, i);
				listacotizacion.setMaximo(maximo, i);
				listacotizacion.setMinimo(minimo, i);
				listacotizacion.setCerrar(cerrar, i);
				listacotizacion.setVolumen(volumen, i);
				listacotizacion.setAjustesCierre(ajustes_cierre, i);
				
				}
				JOptionPane.showMessageDialog(frame, "Modificación hecha con exito","Mensaje",JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e2) {
				// TODO: handle exception
			}	
				
			}});

		
		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
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
		
	}
}
