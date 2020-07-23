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
public class Personas extends JFrame{
	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNomPer;
	private JTextField textApePer;
	private JTextField textDesPer;
	private JTextField textPerEstReg;
	private JTable table;
	
	public static void main(String[]args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Personas frame = new Personas();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static final String URL="jdbc:mysql://localhost/butterfly?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String USERNAME="root";
	public static final String PASSWORD="root";
	
	public Personas() {
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
		
		JLabel lblNewLabel_2 = new JLabel("DNI:");
		lblNewLabel_2.setBounds(10, 56, 85, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre de la Persona:");
		lblNewLabel_3.setBounds(10, 87, 106, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Apellido de la Persona:");
		lblNewLabel_4.setBounds(10, 111, 65, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Descripcion:");
		lblNewLabel_5.setBounds(10, 146, 85, 14);
		contentPane.add(lblNewLabel_5);
		
		textDNI = new JTextField();
		textDNI.setBounds(115, 53, 200, 20);
		contentPane.add(textDNI);
		textDNI.setColumns(10);
		
		textNomPer = new JTextField();
		textNomPer.setBounds(116, 84, 199, 20);
		contentPane.add(textNomPer);
		textNomPer.setColumns(10);
		
		textApePer = new JTextField();
		textApePer.setBounds(116, 108, 199, 20);
		contentPane.add(textApePer);
		textApePer.setColumns(10);
		
		textDesPer = new JTextField();
		textDesPer.setBounds(115, 139, 200, 40);
		contentPane.add(textDesPer);
		textDesPer.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Estado de Registro");
		lblNewLabel_6.setBounds(10, 193, 106, 14);
		contentPane.add(lblNewLabel_6);
		
		textPerEstReg = new JTextField();
		textPerEstReg.setBounds(115, 190, 26, 20);
		contentPane.add(textPerEstReg);
		textPerEstReg.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"72697516", "Bryan", "Gallegos", "A"},
				{"78945612", "Juan", "Guillén", "A"},
				{"75963543", "Luis", "Flórez", "I"},
			},
			new String[] {
				"DNI", "Nombre Persona", "Apellido Persona", "Estado"
			}
		));
		table.setBounds(10, 245, 414, 48);
		contentPane.add(table);

		JLabel lblNewLabel_7 = new JLabel("DNI");
		lblNewLabel_7.setBounds(49, 220, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Nombre Person.");
		lblNewLabel_8.setBounds(116, 221, 85, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Apellido Per.");
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
					ps=con.prepareStatement("INSERT INTO especie ("
							+ "PerDNI,PerNom,PerApe,PerAct,PerOd,PerEstReg) VALUES(?,?,?,?,?)");
					int rp=ps.executeUpdate();
					if(rp>0) {
						JOptionPane.showMessageDialog(null,"Se guardo el registro");
					}else
						JOptionPane.showMessageDialog(null, "No se pudo registrar");
					con.close();
					limpiarCajas();
				}catch(Exception ex){
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
					ps.setString(1, textNomPer.getText());
					ps.setString(2, textApePer.getText());
					ps.setString(3, textDesPer.getText());
					ps.setString(4, textPerEstReg.getText());
					ps.setString(5, textDNI.getText());
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
					ps.setString(2, textDNI.getText());
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
					ps.setString(2, textDNI.getText());
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
					ps.setString(2, textDNI.getText());
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
					ps.setString(1, textDNI.getText());
					res=ps.executeQuery();
					if(res.next()) {
						textDNI.setText(res.getString("EspNom"));
						textNomPer.setText(res.getString("EspNomCie"));
						textApePer.setText(res.getString("EspGen"));
						textDesPer.setText(res.getString("EspDes"));
						textPerEstReg.setText(res.getString("EspEstReg"));
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
		textDNI.setText(null);
		textNomPer.setText(null);
		textApePer.setText(null);
		textDesPer.setText(null);
		textPerEstReg.setText(null);
	}
}
