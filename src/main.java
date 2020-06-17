import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.acryl.*;
import com.jtattoo.plaf.aero.*;
import com.jtattoo.plaf.aluminium.*;
import com.jtattoo.plaf.bernstein.*;
import com.jtattoo.plaf.fast.*;
import com.jtattoo.plaf.graphite.*;
import com.jtattoo.plaf.hifi.*;
import com.jtattoo.plaf.luna.*;
import com.jtattoo.plaf.mcwin.*;
import com.jtattoo.plaf.mint.*;
import com.jtattoo.plaf.noire.*;
import com.jtattoo.plaf.smart.*;
import com.jtattoo.plaf.texture.*;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class main extends JFrame {

	private JPanel contentPane;
	private JTextField tf_user;
	private JPasswordField tf_psw;
static String theme = "";
	public static void main(String[] args) {
		String sCurrentLine = "";

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("C:/Coswin_local_img/aa.txt"));	
			try {
				while ((sCurrentLine = br.readLine()) != null) {System.out.println(sCurrentLine);
																	theme = sCurrentLine;
																}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		  try {
			UIManager.setLookAndFeel(theme); 
		
				
		} catch (Exception e) {
			 try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			 Properties props = new Properties();
	         props.put("logoString", "AO&M Company"); 
	         AcrylLookAndFeel.setCurrentTheme(props);
		}	
	
				main frame = new main();
				frame.setVisible(true);
				
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setTitle("Identification");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur");
		lblNomDutilisateur.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblNomDutilisateur.setBounds(10, 11, 118, 23);
		contentPane.add(lblNomDutilisateur);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblMotDePasse.setBounds(10, 50, 95, 23);
		contentPane.add(lblMotDePasse);
		
		tf_user = new JTextField();
		tf_user.setFont(new Font("Verdana", Font.PLAIN, 13));
		tf_user.setBounds(194, 11, 109, 26);
		contentPane.add(tf_user);
		tf_user.setColumns(10);
		
		tf_psw = new JPasswordField();
		tf_psw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==10){
					if(tf_user.getText().equals("%") || tf_psw.getText().equals("%")){
				    	  JOptionPane.showMessageDialog(null, "veillez verifier le nom d'utilisateur et le mot de passe SVP","erreur",JOptionPane.WARNING_MESSAGE);
					}else
					
					if(tf_user.getText().equals("admin")&&tf_psw.getText().equals("s2flvjj333")){
		        	    new lecture(tf_user.getText(),tf_psw.getText(),"Admin").setVisible(true);
						setVisible(false);
					}else{
						
						try {
						     // Chargement du pilote JDBC
						     Class.forName("oracle.jdbc.driver.OracleDriver");
						     // URL de connexion
						     String url = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
						     String user = "system";
						     String password = "amine";
						     // Connexion
						     Connection con = null;
						  try {
						             con = DriverManager.getConnection(url, user, password);
						      } catch (SQLException ex) {
						               Logger.getLogger(lecture.class.getName()).log(Level.SEVERE, null, ex);
						      } 
						      
					          	Statement statement = con.createStatement();
					          	
					          String req = "select to_char(sysdate, 'DD/MM/YYYY')  as dates from dual";	
					          ResultSet rst = statement.executeQuery(req);

					          String dt00 = "";
					          while(rst.next()){
					        
					        	  dt00 = rst.getString(1);
					        	 
					          }
					       
					          if(dt00.equals(new SimpleDateFormat("dd/MM/yyyy").format(new Date()))){
					        	    req = "select ID,NOM,PSW,POSTE,SERVICE from COR_AOM_USERS where id like '"+tf_user.getText()+"' and PSW like '"+tf_psw.getText()+"' ";
					        	    rst = statement.executeQuery(req);
					        	    String bl = "";
					          while(rst.next()){
					        	  bl = rst.getString(4);
					        	 // System.out.println("c ok "+rst.getString(4));
					          }
					          
					          if(bl.equals("") ){
						    	  JOptionPane.showMessageDialog(null, "veillez verifier le nom d'utilisateur et le mot de passe SVP","erreur",JOptionPane.WARNING_MESSAGE);
					          }else{
					        	    new lecture(tf_user.getText(),tf_psw.getText(),bl).setVisible(true);
									setVisible(false);
					          }
					          }else{
						    	  JOptionPane.showMessageDialog(null, "veillez verifier la date du systeme svp !!","erreur",JOptionPane.ERROR_MESSAGE);
					          }
					          
				          con.close();
					      } catch (ClassNotFoundException e1) {
					    	  JOptionPane.showMessageDialog(null, "Erreur "+e1,"erreur",JOptionPane.ERROR_MESSAGE);
					      } catch (SQLException sqle) {
					    	  JOptionPane.showMessageDialog(null, "Erreur sql "+sqle,"erreur",JOptionPane.ERROR_MESSAGE);
					      }

					}
				}
				
			}
		});
		tf_psw.setFont(new Font("Verdana", Font.PLAIN, 13));
		tf_psw.setBounds(194, 50, 109, 26);
		contentPane.add(tf_psw);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tf_user.getText().equals("%") || tf_psw.getText().equals("%")){
			    	  JOptionPane.showMessageDialog(null, "veillez verifier le nom d'utilisateur et le mot de passe SVP","erreur",JOptionPane.WARNING_MESSAGE);
				}else
				
				if(tf_user.getText().equals("admin")&&tf_psw.getText().equals("s2flvjj333")){
	        	    new lecture(tf_user.getText(),tf_psw.getText(),"Admin").setVisible(true);
					setVisible(false);
				}else{
					
					try {
					     // Chargement du pilote JDBC
					     Class.forName("oracle.jdbc.driver.OracleDriver");
					     // URL de connexion
					     String url = "jdbc:oracle:thin:@//127.0.0.1:1521/XE";
					     String user = "system";
					     String password = "amine";
					     // Connexion
					     Connection con = null;
					  try {
					             con = DriverManager.getConnection(url, user, password);
					      } catch (SQLException ex) {
					               Logger.getLogger(lecture.class.getName()).log(Level.SEVERE, null, ex);
					      } 
					      
				          	Statement statement = con.createStatement();
				          	
				          String req = "select to_char(sysdate, 'DD/MM/YYYY')  as dates from dual";	
				          ResultSet rst = statement.executeQuery(req);

				          String dt00 = "";
				          while(rst.next()){
				        
				        	  dt00 = rst.getString(1);
				        	 
				          }
				       
				          if(dt00.equals(new SimpleDateFormat("dd/MM/yyyy").format(new Date()))){
				        	    req = "select ID,NOM,PSW,POSTE,SERVICE from COR_AOM_USERS where id like '"+tf_user.getText()+"' and PSW like '"+tf_psw.getText()+"' ";
				        	    rst = statement.executeQuery(req);
				        	    String bl = "";
				          while(rst.next()){
				        	  bl = rst.getString(4);
				        	 // System.out.println("c ok "+rst.getString(4));
				          }
				          
				          if(bl.equals("") ){
					    	  JOptionPane.showMessageDialog(null, "veillez verifier le nom d'utilisateur et le mot de passe SVP","erreur",JOptionPane.WARNING_MESSAGE);
				          }else{
				        	    new lecture(tf_user.getText(),tf_psw.getText(),bl).setVisible(true);
								setVisible(false);
				          }
				          }else{
					    	  JOptionPane.showMessageDialog(null, "veillez verifier la date du systeme svp !!","erreur",JOptionPane.ERROR_MESSAGE);
				          }
				          
			          con.close();
				      } catch (ClassNotFoundException e1) {
				    	  JOptionPane.showMessageDialog(null, "Erreur "+e1,"erreur",JOptionPane.ERROR_MESSAGE);
				      } catch (SQLException sqle) {
				    	  JOptionPane.showMessageDialog(null, "Erreur sql "+sqle,"erreur",JOptionPane.ERROR_MESSAGE);
				      }

				}
				
			}
		});
		btnValider.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnValider.setBounds(80, 92, 154, 43);
		contentPane.add(btnValider);
	}
}
