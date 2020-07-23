package Butterfly;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Generos extends JFrame {

	private JPanel contentPane;
	private JTextField textGen;
	private JTextField textGenNom;
	private JTextField textGenFam;
	private JTextField textGenDes;
	private JTextField textGenEstReg;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Generos frame = new Generos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static final String URL="jdbc:mysql://localhost/butterfly?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USERNAME="root";
	public static final String PASSWORD="root";
	
	public Generos() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Coleccion de Mariposas");
		lblNewLabel.setBounds(154, 11, 118, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Registro");
		lblNewLabel_1.setBounds(10, 31, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(10, 56, 85, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Familia");
		lblNewLabel_3.setBounds(10, 87, 106, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Genero:");
		lblNewLabel_4.setBounds(10, 111, 65, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Descripcion:");
		lblNewLabel_5.setBounds(10, 146, 85, 14);
		contentPane.add(lblNewLabel_5);
		
		textGen = new JTextField();
		textGen.setBounds(115, 53, 200, 20);
		contentPane.add(textGen);
		textGen.setColumns(10);
		
		textGenNom = new JTextField();
		textGenNom.setBounds(116, 84, 199, 20);
		contentPane.add(textGenNom);
		textGenNom.setColumns(10);
		
		textGenFam = new JTextField();
		textGenFam.setBounds(116, 108, 199, 20);
		contentPane.add(textGenFam);
		textGenFam.setColumns(10);
		
		textGenDes = new JTextField();
		textGenDes.setBounds(115, 139, 200, 40);
		contentPane.add(textGenDes);
		textGenDes.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Estado de Registro");
		lblNewLabel_6.setBounds(10, 193, 106, 14);
		contentPane.add(lblNewLabel_6);
		
		textGenEstReg = new JTextField();
		textGenEstReg.setBounds(115, 190, 26, 20);
		contentPane.add(textGenEstReg);
		textGenEstReg.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Polvillo Dorado","Hespéridos","Muschampia proto","A"},
				{"Macaon","Papilónicos","Papilio machaon","A"},
				{"Mariposa musgosa","Piéridos","Anthocharis cardamines","A"},
			},
			new String[] {
				"Nombre", "Familia","Descripción", "Estado"
			}
		));
		table.setBounds(10, 245, 414, 48);
		contentPane.add(table);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre");
		lblNewLabel_7.setBounds(49, 220, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Familia");
		lblNewLabel_8.setBounds(116, 221, 85, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Descripción");
		lblNewLabel_9.setBounds(226, 220, 46, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Estado");
		lblNewLabel_10.setBounds(322, 220, 46, 14);
		contentPane.add(lblNewLabel_10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 304, 89, 23);
		contentPane.add(btnAdicionar);
		
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				try {
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					ps=con.prepareStatement("INSERT INTO especie (EspNom,EspNomCie,EspGen,EspDes,EspEstReg) VALUES(?,?,?,?,?)");
					ps.setString(1, textGen.getText());
					ps.setString(2, textGenNom.getText());
					ps.setString(3, textGenFam.getText());
					ps.setString(4, textGenDes.getText());
					ps.setString(5, textGenEstReg.getText());
					int rp=ps.executeUpdate();
					if(rp>0) {
						JOptionPane.showMessageDialog(null,"Se gurado el registro");
					}else
						JOptionPane.showMessageDialog(null, "No se pudo registrar");
					con.close();
					limpiarCajas();
				}catch(Exception ex) {
					System.out.println(ex);
					limpiarCajas();
				}
			}
		});

		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(126, 304, 89, 23);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				try {
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					ps=con.prepareStatement("UPDATE especie SET EspNomCie=?, EspGen=?, EspDes=?, EspEstReg=? WHERE EspNom=?");
					ps.setString(1, textGenNom.getText());
					ps.setString(2, textGenFam.getText());
					ps.setString(3, textGenDes.getText());
					ps.setString(4, textGenEstReg.getText());
					ps.setString(5, textGen.getText());
					int rp=ps.executeUpdate();
					if(rp>0) {
						JOptionPane.showMessageDialog(null,"Se modifico el registro");
					}else
						JOptionPane.showMessageDialog(null, "No se pudo modificar el registro");
					con.close();
					limpiarCajas();
				}catch(Exception ex) {
					System.out.println(ex);
					limpiarCajas();
				}	
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(231, 304, 89, 23);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				try {
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					ps=con.prepareStatement("UPDATE especie SET EspEstReg=? WHERE EspNom=?");
					ps.setString(1, "*");
					ps.setString(2, textGen.getText());
					int rp=ps.executeUpdate();
					if(rp>0) {
						JOptionPane.showMessageDialog(null,"Se elimino logicamente el elemento");
					}else
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el elemento");
					con.close();
					limpiarCajas();
				}catch(Exception ex) {
					System.out.println(ex);
					limpiarCajas();
				}	
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(330, 304, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCajas();	
			}
		});
		
		JButton btnInactivar = new JButton("Inactivar");
		btnInactivar.setBounds(10, 339, 89, 23);
		contentPane.add(btnInactivar);
		btnInactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				try {
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					ps=con.prepareStatement("UPDATE especie SET EspEstReg=? WHERE EspNom=?");
					ps.setString(1, "I");
					ps.setString(2, textGen.getText());
					int rp=ps.executeUpdate();
					if(rp>0) {
						JOptionPane.showMessageDialog(null,"Se modifico el estado de registro");
					}else
						JOptionPane.showMessageDialog(null, "No se pudo modificar el registro");
					con.close();
					limpiarCajas();
				}catch(Exception ex) {
					System.out.println(ex);
					limpiarCajas();
				}	
			}
		});
		
		JButton btnActivar = new JButton("Activar");
		btnActivar.setBounds(126, 339, 89, 23);
		contentPane.add(btnActivar);
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con=null;
				try {
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					ps=con.prepareStatement("UPDATE especie SET EspEstReg=? WHERE EspNom=?");
					ps.setString(1, "A");
					ps.setString(2, textGen.getText());
					int rp=ps.executeUpdate();
					if(rp>0) {
						JOptionPane.showMessageDialog(null,"Se modifico el estado de registro");
					}else
						JOptionPane.showMessageDialog(null, "No se pudo modificar el registro");
					con.close();
					limpiarCajas();
				}catch(Exception ex) {
					System.out.println(ex);
					limpiarCajas();
				}	
			}
		});
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(231, 338, 89, 23);
		contentPane.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=null;
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					con.close();
				}catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(335, 338, 89, 23);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.exit(0);
				}catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		
		JButton btnBuscar = new JButton("Buscar...");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con=null;
				try {
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					ps=con.prepareStatement("SELECT * FROM especie WHERE EspNom=?");
					ps.setString(1, textGen.getText());
					res=ps.executeQuery();
					if(res.next()) {
						textGen.setText(res.getString("EspNom"));
						textGenNom.setText(res.getString("EspNomCie"));
						textGenFam.setText(res.getString("EspGen"));
						textGenDes.setText(res.getString("EspDes"));
						textGenEstReg.setText(res.getString("EspEstReg"));
					}else
						JOptionPane.showMessageDialog(null, "No se encontro");
					con.close();
				}catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		btnBuscar.setBounds(325, 52, 89, 23);
		contentPane.add(btnBuscar);
	}
	public static Connection getConnection() {
		Connection connec=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connec=(Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(Exception e){
			System.out.println(e);
		}
		return connec;
	}
	private void limpiarCajas() {
		textGen.setText(null);
		textGenNom.setText(null);
		textGenFam.setText(null);
		textGenDes.setText(null);
		textGenEstReg.setText(null);
	}
}
