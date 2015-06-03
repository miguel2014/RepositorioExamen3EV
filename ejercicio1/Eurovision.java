
package ejercicio1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Eurovision {

	private JFrame frame;
	private JTextField textoPais;
	private JTextField textoInterprete;
	private JTextField textoCancion;
	private JTextField textoPuntuacion;
	private JLabel registro;
	private int contador=0;
	private int minimo=1;
	private JButton botonAnterior;
	private JButton botonSiguiente;
	private JMenuItem mntmGuardarBaseDe;
	private JMenu mnGanador;
	private List<String> lista=new ArrayList<String>();
	private ListaEurovision listaeu;
	private String campos[];
	private String pais;
	private String interprete;
	private String cancion;
	private int puntuacion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eurovision window = new Eurovision();
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
	public Eurovision() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Eurovision 2015");
		frame.setResizable(false);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Datos EuroVision");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCargarBaseDe = new JMenuItem("Cargar Base de Datos");
		mntmCargarBaseDe.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				int returnval=chooser.showOpenDialog(frame);
				
				if (returnval==JFileChooser.APPROVE_OPTION) {
					File fichero=chooser.getSelectedFile();
					
					if (!lista.isEmpty()) {
						lista.clear();
						listaeu.borrarLista();
					}
					try (Scanner in=new Scanner(fichero);){
						while(in.hasNextLine()){
							String texto=in.nextLine();
							lista.add(texto);
						}
						listaeu=new ListaEurovision();
						
						for (int i = 0; i < lista.size(); i++) {
							campos=lista.get(i).split(";");
							pais=campos[0];
							interprete=campos[1];
							cancion=campos[2];
							puntuacion=Integer.parseInt(campos[3]);
							listaeu.addCantante(new Cantantes(pais, interprete, cancion, puntuacion));
						}

						
						textoPais.setText(listaeu.getPosicion(contador).getPais());
						textoInterprete.setText(listaeu.getPosicion(contador).getInterprete());
						textoCancion.setText(listaeu.getPosicion(contador).getCancion());
						textoPuntuacion.setText(listaeu.getPosicion(contador).getPuntuacion()+"");
						
						registro.setText("Participantes 1 de "+lista.size());

						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		mnNewMenu.add(mntmCargarBaseDe);
		
		mntmGuardarBaseDe = new JMenuItem("Guardar Base de Datos");
	
		mntmGuardarBaseDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserguardar=new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.csv", "csv");
				chooserguardar.setFileFilter(filtro);
				int returnval=chooserguardar.showSaveDialog(frame);
				
				if (returnval==JFileChooser.APPROVE_OPTION) {
					String outFile=chooserguardar.getSelectedFile()+".csv";
					File ficheroparaguardar=new File(outFile);
					
					try (PrintWriter out=new PrintWriter(ficheroparaguardar);){
							for (int i = 0; i < lista.size(); i++) {
							out.println((listaeu.getPosicion(i)));
							}
							
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntmGuardarBaseDe);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmSalir);
		
		JMenu mnPais = new JMenu("Pais");
		menuBar.add(mnPais);
		
		JMenuItem mntmAnterior = new JMenuItem("Anterior");
		mntmAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Has de introducir datos");
				} else {
					botonAnteriorfuncion();
					
				}
				
			}
		});
		mnPais.add(mntmAnterior);
		
		JMenuItem mntmSiguiente = new JMenuItem("Siguiente");
		mntmSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Has de introducir datos");
				} else {
					botonSiguienteFuncion();
					
				}
			}
		});
		mnPais.add(mntmSiguiente);
		
		mnGanador = new JMenu("Ganador");
		menuBar.add(mnGanador);
		JMenuItem mntmMostrar = new JMenuItem("Mostrar");
		mntmMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Has de introducir datos");
				} else {

					String textoPais=listaeu.getPosicion(0).getPais();
					String textoInterprete=listaeu.getPosicion(0).getInterprete();
					String textoCancion=listaeu.getPosicion(0).getCancion();
					int textoPuntuacion=listaeu.getPosicion(0).getPuntuacion();
				
				JOptionPane.showMessageDialog(frame, "Gano "+textoPais+" con un total de "+ textoPuntuacion+""
						+ ",la canto el interprete " +textoInterprete+" con la cancion "+textoCancion );	
				}
			}
		});
		mnGanador.add(mntmMostrar);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Realizado por Miguel Angel Gutierrez Delgado");
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		botonAnterior = new JButton("<--Anterior");
		
		botonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Has de introducir datos");
				} else {
					botonAnteriorfuncion();
					
				}
			}
		});
		
		panel.add(botonAnterior);
		
		botonSiguiente = new JButton("Siguiente -->");
		botonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lista.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Has de introducir datos");
				} else {
					botonSiguienteFuncion();
					
				}
				
			}
		});
		panel.add(botonSiguiente);
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		registro = new JLabel("Participantes 0 de "+lista.size());
		
		panel_1.add(registro);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Pais");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textoPais = new JTextField();
		GridBagConstraints gbc_textoPais = new GridBagConstraints();
		gbc_textoPais.insets = new Insets(0, 0, 5, 5);
		gbc_textoPais.gridx = 2;
		gbc_textoPais.gridy = 1;
		panel_2.add(textoPais, gbc_textoPais);
		textoPais.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Interprete");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textoInterprete = new JTextField();
		textoInterprete.setColumns(10);
		GridBagConstraints gbc_textiInterprete = new GridBagConstraints();
		gbc_textiInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_textiInterprete.gridx = 2;
		gbc_textiInterprete.gridy = 2;
		panel_2.add(textoInterprete, gbc_textiInterprete);
		
		JLabel lblCancion = new JLabel("Cancion");
		lblCancion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCancion = new GridBagConstraints();
		gbc_lblCancion.anchor = GridBagConstraints.WEST;
		gbc_lblCancion.insets = new Insets(0, 0, 5, 5);
		gbc_lblCancion.gridx = 1;
		gbc_lblCancion.gridy = 3;
		panel_2.add(lblCancion, gbc_lblCancion);
		
		textoCancion = new JTextField();
		textoCancion.setColumns(10);
		GridBagConstraints gbc_textoCancion = new GridBagConstraints();
		gbc_textoCancion.insets = new Insets(0, 0, 5, 5);
		gbc_textoCancion.gridx = 2;
		gbc_textoCancion.gridy = 3;
		panel_2.add(textoCancion, gbc_textoCancion);
		
		JLabel lblPuntuacion = new JLabel("Puntuacion");
		GridBagConstraints gbc_lblPuntuacion = new GridBagConstraints();
		gbc_lblPuntuacion.anchor = GridBagConstraints.WEST;
		gbc_lblPuntuacion.insets = new Insets(0, 0, 0, 5);
		gbc_lblPuntuacion.gridx = 1;
		gbc_lblPuntuacion.gridy = 4;
		panel_2.add(lblPuntuacion, gbc_lblPuntuacion);
		
		textoPuntuacion = new JTextField();
		textoPuntuacion.setColumns(10);
		GridBagConstraints gbc_TextoPuntuacion = new GridBagConstraints();
		gbc_TextoPuntuacion.insets = new Insets(0, 0, 0, 5);
		gbc_TextoPuntuacion.gridx = 2;
		gbc_TextoPuntuacion.gridy = 4;
		panel_2.add(textoPuntuacion, gbc_TextoPuntuacion);

		
	}
	public void botonAnteriorfuncion(){
		listaeu.setPaisLista(textoPais.getText(),contador);
		listaeu.setCancionLista(textoCancion.getText(), contador);
		listaeu.setInterpreteLista(textoInterprete.getText(), contador);;
		listaeu.setPuntuacionLista(Integer.parseInt(textoPuntuacion.getText()),contador);
		
		contador--;
		if (contador<0) {
			contador=listaeu.getTamañoLista()-1;}
		minimo--;
		if (minimo<1) {
			minimo=listaeu.getTamañoLista();
		}
		textoPais.setText(listaeu.getPosicion(contador).getPais());
		textoInterprete.setText(listaeu.getPosicion(contador).getInterprete());
		textoCancion.setText(listaeu.getPosicion(contador).getCancion());
		textoPuntuacion.setText(listaeu.getPosicion(contador).getPuntuacion()+"");
		
		
		registro.setText("Participantes "+minimo+" de "+lista.size());
		
		
	}
	public void botonSiguienteFuncion(){
		listaeu.setPaisLista(textoPais.getText(),contador);
		listaeu.setCancionLista(textoCancion.getText(), contador);
		listaeu.setInterpreteLista(textoInterprete.getText(), contador);;
		listaeu.setPuntuacionLista(Integer.parseInt(textoPuntuacion.getText()),contador);
		
		contador++;
		if (contador==listaeu.getTamañoLista()) {
			contador=0;}
		minimo++;
		if (minimo>listaeu.getTamañoLista()) {
			minimo=1;
		}
		textoPais.setText(listaeu.getPosicion(contador).getPais());
		textoInterprete.setText(listaeu.getPosicion(contador).getInterprete());
		textoCancion.setText(listaeu.getPosicion(contador).getCancion());
		textoPuntuacion.setText(listaeu.getPosicion(contador).getPuntuacion()+"");
		
		registro.setText("Participantes "+minimo+" de "+lista.size());
		
	}
	
}
