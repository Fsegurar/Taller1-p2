package co.edu.unbosque.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class panelCustomSearch extends JPanel {

	private JLabel etiqueta_numero;
	private JTextField txtNumero;
	
	private JLabel etiqueta_posicion;	
	private JTextField txtPosicion;
	
	private JLabel etiqueta_especie;
	private JTextField txtEspecie;
	
	private JLabel etiqueta_sex;
	private JTextField txtSex;
	
	private JLabel etiqueta_dangerous;
	private JTextField txtDangerous;
	
	private JLabel etiqueta_neighborhood;
	private JTextField txtNeighborhood;
	
	private JLabel etiqueta_size;
	private JTextField txtSize;
	
	private JButton boton_search;
	
	public panelCustomSearch(){

		setLayout(null);

		inicializarComponentes();
		setVisible(true); 
		
	}

	private void inicializarComponentes() {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder("panel de búsqueda personalizada"));		
		
		etiqueta_numero = new JLabel("Número de mascotas que desea ver: ");
		etiqueta_numero.setBounds(30, 50, 220, 20);
		add(etiqueta_numero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(250, 50, 110, 20);
		add(txtNumero);
			
		etiqueta_posicion = new JLabel("posición (TOP/LAST): ");
		etiqueta_posicion.setBounds(30, 100, 160, 20);
		add(etiqueta_posicion);
		
		txtPosicion = new JTextField();
		txtPosicion.setBounds(160, 100, 200, 20);
		add(txtPosicion);
		
		etiqueta_especie = new JLabel("Especie (Canino/Felino): ");
		etiqueta_especie.setBounds(30, 150, 170, 20);
		add(etiqueta_especie);
		
		txtEspecie = new JTextField();
		txtEspecie.setBounds(170, 150, 190, 20);
		add(txtEspecie);
		
	
		etiqueta_sex = new JLabel("sexo (macho/hembra): ");
		etiqueta_sex.setBounds(30, 200, 160, 20);
		add(etiqueta_sex);
		
		txtSex = new JTextField();
		txtSex.setBounds(170, 200, 190, 20);
		add(txtSex);
		
		etiqueta_size = new JLabel("Tamaño (Miniatura/Pequeño/Mediano/Grande): ");
		etiqueta_size.setBounds(30, 250, 280, 20);
		add(etiqueta_size);
		
		txtSize = new JTextField();
		txtSize.setBounds(30, 270, 330, 20);
		add(txtSize);
		
		etiqueta_dangerous = new JLabel("¿Es peligroso? (si/no): ");
		etiqueta_dangerous.setBounds(30, 320, 160, 20);
		add(etiqueta_dangerous);
	
		txtDangerous = new JTextField();
		txtDangerous.setBounds(170, 320, 190, 20);
		add(txtDangerous);
		
		etiqueta_neighborhood = new JLabel("Barrio: ");
		etiqueta_neighborhood.setBounds(30, 370, 120, 20);
		add(etiqueta_neighborhood);
		
		txtNeighborhood = new JTextField();
		txtNeighborhood.setBounds(80, 370, 280, 20);
		add(txtNeighborhood);
		
		boton_search= new JButton("Buscar");
		boton_search.setBounds(140, 430, 100, 20);
		boton_search.setBackground(Color.LIGHT_GRAY);
		boton_search.setActionCommand("BUSCAR");
		add(boton_search);
		
	}


	public JLabel getEtiqueta_numero() {
		return etiqueta_numero;
	}


	public JButton getBoton_search() {
		return boton_search;
	}

	public void setBoton_search(JButton boton_search) {
		this.boton_search = boton_search;
	}

	public void setEtiqueta_numero(JLabel etiqueta_numero) {
		this.etiqueta_numero = etiqueta_numero;
	}


	public JTextField getTxtNumero() {
		return txtNumero;
	}


	public void setTxtNumero(JTextField txtNumero) {
		this.txtNumero = txtNumero;
	}


	public JLabel getEtiqueta_posicion() {
		return etiqueta_posicion;
	}


	public void setEtiqueta_posicion(JLabel etiqueta_posicion) {
		this.etiqueta_posicion = etiqueta_posicion;
	}


	public JTextField getTxtPosicion() {
		return txtPosicion;
	}


	public void setTxtPosicion(JTextField txtPosicion) {
		this.txtPosicion = txtPosicion;
	}


	public JLabel getEtiqueta_especie() {
		return etiqueta_especie;
	}


	public void setEtiqueta_especie(JLabel etiqueta_especie) {
		this.etiqueta_especie = etiqueta_especie;
	}


	public JTextField getTxtEspecie() {
		return txtEspecie;
	}


	public void setTxtEspecie(JTextField txtEspecie) {
		this.txtEspecie = txtEspecie;
	}


	public JLabel getEtiqueta_sex() {
		return etiqueta_sex;
	}


	public void setEtiqueta_sex(JLabel etiqueta_sex) {
		this.etiqueta_sex = etiqueta_sex;
	}


	public JTextField getTxtSex() {
		return txtSex;
	}


	public void setTxtSex(JTextField txtSex) {
		this.txtSex = txtSex;
	}


	public JLabel getEtiqueta_dangerous() {
		return etiqueta_dangerous;
	}


	public void setEtiqueta_dangerous(JLabel etiqueta_dangerous) {
		this.etiqueta_dangerous = etiqueta_dangerous;
	}


	public JTextField getTxtDangerous() {
		return txtDangerous;
	}


	public void setTxtDangerous(JTextField txtDangerous) {
		this.txtDangerous = txtDangerous;
	}


	public JLabel getEtiqueta_neighborhood() {
		return etiqueta_neighborhood;
	}


	public void setEtiqueta_neighborhood(JLabel etiqueta_neighborhood) {
		this.etiqueta_neighborhood = etiqueta_neighborhood;
	}


	public JTextField getTxtNeighborhood() {
		return txtNeighborhood;
	}


	public JLabel getEtiqueta_size() {
		return etiqueta_size;
	}

	public void setEtiqueta_size(JLabel etiqueta_size) {
		this.etiqueta_size = etiqueta_size;
	}

	public JTextField getTxtSize() {
		return txtSize;
	}

	public void setTxtSize(JTextField txtSize) {
		this.txtSize = txtSize;
	}

	public void setTxtNeighborhood(JTextField txtNeighborhood) {
		this.txtNeighborhood = txtNeighborhood;
	}
}


























