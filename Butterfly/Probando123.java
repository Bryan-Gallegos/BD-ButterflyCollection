package Butterfly;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Probando123 extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Probando123 frame = new Probando123();
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
	
	public Probando123() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConection = new JButton("Conectar");
		btnConection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con=null;
					con=getConnection();
					PreparedStatement ps=null;
					ResultSet res;
					ps=con.prepareStatement("SELECT * FROM persona");
					res=ps.executeQuery();
					if(res.next()) {
						JOptionPane.showMessageDialog(null,"DNI:  "+res.getInt("PerDNI")+"\nNombre: "+res.getString("PerNom")+"\nApellido:  "+res.getString("PerApe")+"\nActividad:  "+res.getString("PerAct")+"\nDescripcion: "+res.getString("PerOd"));
					}else
						JOptionPane.showMessageDialog(null, "Aun no hay datos");
					con.close();
				}catch(Exception ex) {
					System.out.println(ex);
				}
			
				
				
			}
		});
		btnConection.setBounds(165, 169, 89, 23);
		contentPane.add(btnConection);
	}
	public static Connection getConnection() {
		Connection connec=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connec=(Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
			JOptionPane.showMessageDialog(null, "Conexion Exitosa");
		}catch(Exception e){
			System.out.println(e);
		}
		return connec;
	}
}