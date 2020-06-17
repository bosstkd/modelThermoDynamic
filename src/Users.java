
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Users extends JFrame {

	private JPanel contentPane;
	private JTextField tf_id;
	private JPasswordField tf_psw_0;
	private JPasswordField tf_psw_1;
	private JTextField tf_nom;
	JButton btnValider = new JButton("Valider");
	JComboBox cb_service = new JComboBox();

	
	JComboBox cb_poste = new JComboBox();

	public Users() {
		setTitle("Users");
		setResizable(false);
		setBounds(100, 100, 253, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblId.setBounds(24, 33, 46, 23);
		contentPane.add(lblId);
		
		JLabel lblMotDePasse = new JLabel("Taper le Psw");
		lblMotDePasse.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblMotDePasse.setBounds(24, 103, 106, 23);
		contentPane.add(lblMotDePasse);
		
		JLabel lblRetaperLePsw = new JLabel("Retaper le Psw");
		lblRetaperLePsw.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblRetaperLePsw.setBounds(24, 137, 106, 23);
		contentPane.add(lblRetaperLePsw);
		
		JLabel lblService = new JLabel("Service");
		lblService.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblService.setBounds(24, 171, 46, 23);
		contentPane.add(lblService);
		
		JLabel lblPoste = new JLabel("Poste");
		lblPoste.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPoste.setBounds(24, 205, 46, 14);
		contentPane.add(lblPoste);
		
		tf_id = new JTextField();
		tf_id.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
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
			          String req = "select ID,NOM,PSW,POSTE,SERVICE from COR_AOM_USERS where id like '"+tf_id.getText()+"' ";
			          ResultSet rst = statement.executeQuery(req);
			          String bl = "";
			          while(rst.next()){
			        	  bl = "ok";
			        	  tf_id.setText(rst.getString(1));
			        	  tf_nom.setText(rst.getString(2));
			        	  tf_psw_0.setText(rst.getString(3));
			        	  tf_psw_1.setText(rst.getString(3));
			        	  cb_poste.setSelectedItem(rst.getString(4));
			        	  cb_service.setSelectedItem(rst.getString(5));
			          }
			          
			          if(bl.equals("ok") ){
			        	  btnValider.setText("Modifier");
			          }else{
			        	  btnValider.setText("Valider");
			          }

		          con.close();

			      } catch (ClassNotFoundException e1) {
			    	  JOptionPane.showMessageDialog(null, "Erreur "+e1,"erreur",JOptionPane.ERROR_MESSAGE);
			      } catch (SQLException sqle) {
			    	  JOptionPane.showMessageDialog(null, "Erreur sql "+sqle,"erreur",JOptionPane.ERROR_MESSAGE);
			      }
				
			}
		});
		tf_id.setFont(new Font("Verdana", Font.PLAIN, 12));
		tf_id.setBounds(169, 33, 67, 22);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		tf_psw_0 = new JPasswordField();
		tf_psw_0.setFont(new Font("Verdana", Font.PLAIN, 12));
		tf_psw_0.setBounds(130, 103, 106, 22);
		contentPane.add(tf_psw_0);
		
		tf_psw_1 = new JPasswordField();
		tf_psw_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		tf_psw_1.setBounds(130, 137, 106, 22);
		contentPane.add(tf_psw_1);
		cb_service.setFont(new Font("Verdana", Font.PLAIN, 12));
		cb_service.setModel(new DefaultComboBoxModel(new String[] {"Technique", "Administratif", "Informatique", "HSE"}));
		
		
		cb_service.setBounds(130, 170, 106, 23);
		contentPane.add(cb_service);
		cb_poste.setFont(new Font("Verdana", Font.PLAIN, 12));
		cb_poste.setModel(new DefaultComboBoxModel(new String[] {"Admin", "D-Tech", "D-Af", "D-Hse", "D-Rh", "Acheteur", "Magasinier", "Operateur", "Caissier"}));
		
		
		cb_poste.setBounds(130, 200, 106, 23);
		contentPane.add(cb_poste);
		
		JLabel lblFaitEntreLes = new JLabel("Fait entr\u00E9e les informations");
		lblFaitEntreLes.setFont(new Font("Verdana", Font.BOLD, 12));
		lblFaitEntreLes.setBounds(24, 0, 200, 22);
		contentPane.add(lblFaitEntreLes);
		
		JLabel lblNomEtPrnm = new JLabel("Nom et Pr\u00E9nm");
		lblNomEtPrnm.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblNomEtPrnm.setBounds(24, 67, 106, 25);
		contentPane.add(lblNomEtPrnm);
		
		tf_nom = new JTextField();
		tf_nom.setFont(new Font("Verdana", Font.PLAIN, 12));
		tf_nom.setBounds(130, 72, 106, 20);
		contentPane.add(tf_nom);
		tf_nom.setColumns(10);
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tf_id.getText().equals("")||tf_nom.getText().equals("")||tf_psw_0.getText().equals("")||tf_psw_1.getText().equals("")){
			    	  JOptionPane.showMessageDialog(null, "veillez remplir tout les champs SVP !!","erreur",JOptionPane.WARNING_MESSAGE);
				}else if(! tf_psw_0.getText().equals(tf_psw_1.getText())){
			    	  JOptionPane.showMessageDialog(null, "veillez vérifier le mot de passe SVP !!","erreur",JOptionPane.WARNING_MESSAGE);
				}else{
					
					String msg = "";
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
			          String req = "";
			        	if(btnValider.getText().equals("Valider")){
							req = "insert into COR_AOM_USERS(ID ,NOM ,  PSW ,  POSTE ,  SERVICE ) VALUES('"+tf_id.getText()+"','"+tf_nom.getText()+"','"+tf_psw_0.getText()+"','"+cb_poste.getSelectedItem()+"','"+cb_service.getSelectedItem()+"')";
						msg = "insertion effectuer avec succée";
			        	}else{
							req = "update COR_AOM_USERS set PSW = '"+tf_psw_0.getText()+"', NOM = '"+tf_nom.getText()+"',  POSTE = '"+cb_poste.getSelectedItem()+"',  SERVICE = '"+cb_service.getSelectedItem()+"' where ID = '"+tf_id.getText()+"' ";
						msg = "modification effectuer avec succée";

			        	}
					     statement.executeUpdate(req);
						
				   JOptionPane.showMessageDialog(null, msg,"information",JOptionPane.INFORMATION_MESSAGE);
				   tf_id.setText("");
				   tf_nom.setText("");
				   tf_psw_0.setText("");
				   tf_psw_1.setText("");
				   cb_poste.setSelectedIndex(0);
				   cb_service.setSelectedIndex(0);
				

		          con.close();

			      } catch (ClassNotFoundException e1) {
			    	  JOptionPane.showMessageDialog(null, "Erreur "+e1,"erreur",JOptionPane.ERROR_MESSAGE);
			      } catch (SQLException sqle) {
			    	  JOptionPane.showMessageDialog(null, "Erreur sql "+sqle,"erreur",JOptionPane.ERROR_MESSAGE);
			      }
				
				}
				

			}
		});
		btnValider.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnValider.setBounds(24, 247, 106, 23);
		contentPane.add(btnValider);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
			       
			        	
							String req = "Delete from COR_AOM_USERS where ID like '"+tf_id.getText()+"'";
	
					     statement.executeUpdate(req);
						
				   JOptionPane.showMessageDialog(null, "Suppression effectuer avec succées ","information",JOptionPane.INFORMATION_MESSAGE);

				   tf_id.setText("");
				   tf_nom.setText("");
				   tf_psw_0.setText("");
				   tf_psw_1.setText("");
				   cb_poste.setSelectedIndex(0);
				   cb_service.setSelectedIndex(0);
				   
		          con.close();

			      } catch (ClassNotFoundException e1) {
			    	  JOptionPane.showMessageDialog(null, "Erreur "+e1,"erreur",JOptionPane.ERROR_MESSAGE);
			      } catch (SQLException sqle) {
			    	  JOptionPane.showMessageDialog(null, "Erreur sql "+sqle,"erreur",JOptionPane.ERROR_MESSAGE);
			      }
				
			}
		});
		btnSupprimer.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSupprimer.setBounds(130, 247, 106, 23);
		contentPane.add(btnSupprimer);
	}
}
