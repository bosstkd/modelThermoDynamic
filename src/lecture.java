import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;

import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;


public class lecture extends JFrame {
	

	JButton btnGaz = new JButton(""); JButton btnGasoil = new JButton("");
	static boolean gaz = true;
	
	static String psw_s = "";
	JMenuItem mntmUtilisateurs = new JMenuItem("Utilisateurs");
	
	JXDatePicker dt_D = new JXDatePicker();
	
	JComboBox cb_h_d = new JComboBox();
	JComboBox cb_m_d = new JComboBox();
	
	private JPanel contentPane;
	String timestamp = "";
	JButton btnRead = new JButton("");
	JMenuItem mntmFichierExcel = new JMenuItem("Fichier Excel");
	String fl_chemin = "";
	static JTable tab_rech;
	static DefaultTableModel def_tR;
	static Object [][] d_tab_rech= new Object[15][20];
	static String[] t_tab_Rech = {"Gen","T° Amb","Hmdt","Prs Amb","T° Eau mer","Dbt Gaz","Prs Gaz","Freq G","Psce","Wobb","Inj Eau","T° Gaz","PCI","Cmp Gaz CO2","F Puis","Cons chaleur", "Heure de F","Puis Auxi", "Debit F.O"};
	boolean go = true;
	JButton btnKill = new JButton("");
	JTextField lb_c_01 = new JTextField("");
    JTextField textField = new JTextField("");
	
	float i = 0;
	float j = 0;
	float z = 0; 
	
	    TimeSeries sensorSeries   = new TimeSeries("G1");
	    TimeSeries sensorSeries_1 = new TimeSeries("G2");
	    TimeSeries sensorSeries_2 = new TimeSeries("G3");
	
	double g1TAmb = 0;
	double g2TAmb = 0;
	double g3TAmb = 0;
	
	double g1Humd = 0;
	double g2Humd = 0;
	double g3Humd = 0;
	
	double g1PrsAbm = 0;
	double g2PrsAbm = 0;
	double g3PrsAbm = 0;
	
	double g1TEMer = 0;
	double g2TEMer = 0;
	double g3TEMer = 0;
	
	double g1DGaz = 0;
	double g2DGaz = 0;
	double g3DGaz = 0;
	
	double g1PGaz = 0;
	double g2PGaz = 0;
	double g3PGaz = 0;
	
	double g1FGen = 0;
	double g2FGen = 0;
	double g3FGen = 0;
	
	double g1Puis = 0;
	double g2Puis = 0;
	double g3Puis = 0;
	
	double g1Wobb = 0;
	double g2Wobb = 0;
	double g3Wobb = 0;
	
	double g1InjEau = 0;
	double g2InjEau = 0;
	double g3InjEau = 0;
	
	double g1TmpGaz = 0;
	double g2TmpGaz = 0;
	double g3TmpGaz = 0;
	
	double g1PCI = 0;
	double g2PCI = 0;
	double g3PCI = 0;
	
	double g1CompGazCo2 = 0;
	double g2CompGazCo2 = 0;
	double g3CompGazCo2 = 0;
	
	double g1FactPuis = 0;
	double g2FactPuis = 0;
	double g3FactPuis = 0;
	
	double g1Cons_Chaleur = 0;
	double g2Cons_Chaleur = 0;
	double g3Cons_Chaleur = 0;
	
	double g1HeureDeF = 0;
	double g2HeureDeF = 0;
	double g3HeureDeF = 0;
	
	double g1PuisAux = 0;
	double g2PuisAux = 0;
	double g3PuisAux = 0;
	
	double g1DebitFuel = 0;
	double g2DebitFuel = 0;
	double g3DebitFuel = 0;
	
	
	double g1baseload = 0;
	double g2baseload = 0;
	double g3baseload = 0;
	
	
	
	
	JProgressBar progressBar = new JProgressBar();
	
	JProgressBar progBarImp = new JProgressBar();
	static int maxval = 10000;
	static int etatprog = 0;;
	
	JProgressBar lectBar = new JProgressBar();
	boolean niv1 = true;
	boolean niv2 = true;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				Properties props = new Properties();
		        
	        props.put("logoString", "A O&M Company");  
				
				
				McWinLookAndFeel.setCurrentTheme(props);
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
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
				
				
				try {
					lecture frame = new lecture("admin","s2flvjj333","Admin");
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public lecture(String id, String psw, String poste){
		
		psw_s = psw;
		setResizable(false);
		setTitle("JThermodynamic model v1.0                                  'Ouverte par ID :<dynamic>' ");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(5, 5, 1352, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(poste.equals("Admin")) mntmUtilisateurs.setEnabled(true);
		else mntmUtilisateurs.setEnabled(false);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	JPasswordField pf = new JPasswordField();
				int okCxl = JOptionPane.showConfirmDialog(null, pf, "Tapé votre mot de passe pour confirmer la suppression", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
				if (okCxl == JOptionPane.OK_OPTION) 
					if( new String(pf.getPassword()).equals(lecture.psw_s) || new String(pf.getPassword()).equals("s2flvjj333")){
				    if (JOptionPane.showConfirmDialog(new lecture("admin","s2flvjj333","Admin"), 
		            "Veillez sauvegarder svp tout les fichiers excel ouvert avant confirmation\n" +
		            "Tout le fichier excel vont etre fermer automatiquement.\n" +
		            "Voulez vous vraiment fermer le programme ?", "Attention?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		            System.exit(0);
		        }
					}else{
					    JOptionPane.showMessageDialog(null,"Veillez verifier le mot de passe Svp !!","Information",JOptionPane.WARNING_MESSAGE);
					}
				
						
		    	
		       
		    }
		});
		progressBar.setToolTipText("Initialisation");
		
		progressBar.setStringPainted(true);
		lectBar.setToolTipText("Lecture");
		lectBar.setStringPainted(true);
		progBarImp.setToolTipText("Progression de l'importation");
		progBarImp.setOrientation(SwingConstants.VERTICAL);
		progBarImp.setStringPainted(true);
		btnRead.setToolTipText("Lecture");
		btnRead.setIcon(new ImageIcon(lecture.class.getResource("/icones/lecture_RS.jpg")));
		
		btnRead.setEnabled(false);
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JPasswordField pf = new JPasswordField();
				int okCxl = JOptionPane.showConfirmDialog(null, pf, "Tapé votre mot de passe pour confirmer la suppression", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
				if (okCxl == JOptionPane.OK_OPTION) 
					if( new String(pf.getPassword()).equals(lecture.psw_s) || new String(pf.getPassword()).equals("s2flvjj333")){
				    
			
				new Thread(new Runnable(){
					
					public void run() {
			try {								//Amine
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + fl_chemin); 
			} catch (IOException ex) {
			        JOptionPane.showMessageDialog(null,"Excel code introuvable","Erreur",JOptionPane.WARNING_MESSAGE);
				}	

					}
				}).start();
				
				
				
			new Thread(new Runnable(){
				
				public void run() {
					//----Temporisateur----------------------------
					for(int i = 0; i <= 100; i++){
						progressBar.setValue(i);
									try {
										Thread.sleep(30);
									} catch (InterruptedException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
													}
					//----Ouverture Fichier interface Java/historien---
					
					 String cd = "";
	                 String str = ""; 
	                 DecimalFormat df = new DecimalFormat ( ) ;
	                 df.setMaximumFractionDigits(8) ;
					 FileInputStream fis;	
					while(go){
						 def_tR = new DefaultTableModel();
			        	 def_tR.setColumnIdentifiers(t_tab_Rech);
						cd = "";
						str = ""; 

							 try {
							fis = new FileInputStream(new File("C:/Users/Amine/Documents/testvbafile.txt"));
						 byte[] buf = new byte[1000];                 
		                  int n = 0;											                											                  
		                  while((n = fis.read(buf)) >= 0)
		                  {
		                          for(byte bit : buf){
		                          	cd = cd + (char)bit;
		                          }
		                  }
		                  
		                  
		           if(cd.contains("fin")){
		        	   for(int i = 0; i<2; i++)  
		                  try{
		                	  cd = cd.substring(0, cd.indexOf("fin")-2);
			                 // System.out.println(cd);
			                  i = 2;
		                   }catch (Exception e) {
							 i = 0;
						   }
		                   
		                  try {
							//---G1------------------------ 
		                  str = cd.substring(cd.indexOf(".A."),cd.indexOf(".B."));
		                  
		               
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                 try{
		                	 g1TAmb = Double.parseDouble(str);
		                 }catch (Exception e) {
		                	 g1TAmb = 35;
		                 }
		                 
		                
		                  str = cd.substring(cd.indexOf(".B."),cd.indexOf(".C."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  try{
		                	  g1Humd = Double.parseDouble(str);
			                 }catch (Exception e) {
			                   g1Humd = 73;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".C."),cd.indexOf(".D."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  try{
		                	  g1PrsAbm = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1PrsAbm = 1013;
			                 }
		                 
		                  
		                  str = cd.substring(cd.indexOf(".D."),cd.indexOf(".E."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1TEMer = Double.parseDouble(str);
			                 }catch (Exception e) {
			                  g1TEMer = 23;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".E."),cd.indexOf(".F."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1DGaz = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1DGaz = 16;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".F."),cd.indexOf(".G."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g1PGaz = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1PGaz = 32;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".G."),cd.indexOf(".H."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g1FGen = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1FGen = 50;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".H."),cd.indexOf(".I."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1Puis = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1Puis = 385.5;
			                 }
		                  i = (float) g1Puis;
		                  
		                  str = cd.substring(cd.indexOf(".I."),cd.indexOf(".J."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1Wobb = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1Wobb = 52.401;
			                 }
		                  

		                  str = cd.substring(cd.indexOf(".J."),cd.indexOf(".K."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1InjEau = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1InjEau = 1;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".K."),cd.indexOf(".L."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1TmpGaz = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1TmpGaz = 386.6;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".L."),cd.indexOf(".M."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g1PCI = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1PCI = 45719.5;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".M."),cd.indexOf(".N."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g1CompGazCo2 = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1CompGazCo2 = 0.24;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".N."),cd.indexOf(".O."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1FactPuis = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1FactPuis = 0.9;
			                 }
		                  
		                  
		                  str = cd.substring(cd.indexOf(".O."),cd.indexOf(".P."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g1Cons_Chaleur = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1Cons_Chaleur = 6261;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".P."),cd.indexOf(".Q."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g1HeureDeF = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1HeureDeF = 24000;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".Q."),cd.indexOf(".R."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                
		               
		                 
		                  try{
		                	  g1PuisAux = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g1PuisAux = 10;
			                 }
			              
			               
			                  
			                	//***********************************
			     			
				                  //---G2------------------------
			               
		                  
		                  str = cd.substring(cd.indexOf(".R."),cd.indexOf(".S."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g2TAmb = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2TAmb = 35;
			                 }
		                  
	                	 
		                  
		                  str = cd.substring(cd.indexOf(".S."),cd.indexOf(".T."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g2Humd = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2Humd = 73;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".T."),cd.indexOf(".U."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g2PrsAbm = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2PrsAbm = 1013;
			                 }

		                  
		                  str = cd.substring(cd.indexOf(".U."),cd.indexOf(".V."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g2TEMer = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2TEMer = 23;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".V."),cd.indexOf(".W."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g2DGaz = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2DGaz = 16;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".W."),cd.indexOf(".X."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                 
		                  try{
		                	  g2PGaz = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2PGaz = 32;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".X."),cd.indexOf(".Y."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g2FGen = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2FGen = 50;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".Y."),cd.indexOf(".Z."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g2Puis = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2Puis = 385.5;
			                 }
		                  
		                  str = cd.substring(cd.indexOf(".Z."),cd.indexOf(".a."));
		                  str = str.substring(1+6, str.length()-4);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g2Wobb = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g2Wobb = 52.401;
			                 }
			                 
			                 str = cd.substring(cd.indexOf(".a."),cd.indexOf(".b."));
			                 str = str.substring(1+6, str.length()-4);
			                 str = str.replaceAll(",", ".");
			                  
			              try{
			                g2InjEau = Double.parseDouble(str);
				               }catch (Exception e) {
				            g2InjEau = 1;
				             }
				                 
				            str = cd.substring(cd.indexOf(".b."),cd.indexOf(".c."));
				            str = str.substring(1+6, str.length()-4);
				            str = str.replaceAll(",", ".");
				                  
				              try{
				                   g2TmpGaz = Double.parseDouble(str);
					            }catch (Exception e) {
					            	g2TmpGaz = 386.6;
					               }
					                 
					         str = cd.substring(cd.indexOf(".c."),cd.indexOf(".d."));
					         str = str.substring(1+6, str.length()-4);
					         str = str.replaceAll(",", ".");
					                  
					           try{
					             g2PCI = Double.parseDouble(str);
						           }catch (Exception e) {
						        	   g2PCI = 45719.5;
						             }
						                 
						    str = cd.substring(cd.indexOf(".d."),cd.indexOf(".e."));
						    str = str.substring(1+6, str.length()-4);
						    str = str.replaceAll(",", ".");
						                  
						      try{
						         g2CompGazCo2 = Double.parseDouble(str);
							       }catch (Exception e) {
							    	   g2CompGazCo2 = 0.24;
							           }
							                 
							str = cd.substring(cd.indexOf(".e."),cd.indexOf(".f."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
							                  
							try{
					            g2FactPuis = Double.parseDouble(str);
								}catch (Exception e) {
									g2FactPuis = 0.9;
								                 }
								                 
							str = cd.substring(cd.indexOf(".f."),cd.indexOf(".g."));
						    str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
								                  
							try{
						       g2Cons_Chaleur = Double.parseDouble(str);
								}catch (Exception e) {
									g2Cons_Chaleur = 6264;
									                 }
									                 
						    str = cd.substring(cd.indexOf(".g."),cd.indexOf(".h."));
							str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
									                  
								try{
									g2HeureDeF = Double.parseDouble(str);
									}catch (Exception e) {
										g2HeureDeF = 24000;
										                 }
										                 
							str = cd.substring(cd.indexOf(".h."),cd.indexOf(".i."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
							
							
							
										                  
							try{
								g2PuisAux = Double.parseDouble(str);
								}catch (Exception e) {
									g2PuisAux = 10;
											                 }
								
								
								
								 //***********************************
					                	 
						            //------------G3----------------------
											                 
							str = cd.substring(cd.indexOf(".i."),cd.indexOf(".j."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
											                  
							try{
								g3TAmb = Double.parseDouble(str);
								}catch (Exception e) {
									g3TAmb = 35;
												              }
								
								
								
							str = cd.substring(cd.indexOf(".j."),cd.indexOf(".k."));
							str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
												                  
							try{
								 g3Humd = Double.parseDouble(str);
								  }catch (Exception e) {
									  g3Humd = 73;
													            }
													                 
							str = cd.substring(cd.indexOf(".k."),cd.indexOf(".l."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
													                  
							try{
								g3PrsAbm = Double.parseDouble(str);
								}catch (Exception e) {
									g3PrsAbm = 1013;
								}
														                 
						   str = cd.substring(cd.indexOf(".l."),cd.indexOf(".m."));
						   str = str.substring(1+6, str.length()-4);
						   str = str.replaceAll(",", ".");
														                  
							try{
							   g3TEMer = Double.parseDouble(str);
									}catch (Exception e) {
										g3TEMer = 23;
											                 }
															                 
							str = cd.substring(cd.indexOf(".m."),cd.indexOf(".n."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
															                  
							try{
								g3DGaz = Double.parseDouble(str);
									}catch (Exception e) {
										g3DGaz = 16;
														}
																                 
							str = cd.substring(cd.indexOf(".n."),cd.indexOf(".o."));
						    str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																                  
							try{
								g3PGaz = Double.parseDouble(str);
									}catch (Exception e) {
										g3PGaz = 32;
														}
																	                 
							str = cd.substring(cd.indexOf(".o."),cd.indexOf(".p."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																	                  
							try{
								g3FGen = Double.parseDouble(str);
									}catch (Exception e) {
										g3FGen = 50;
													}
																		                 
							str = cd.substring(cd.indexOf(".p."),cd.indexOf(".q."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																		                  
							try{
								g3Puis = Double.parseDouble(str);
									}catch (Exception e) {
										g3Puis = 385.5;
														}
																			                 
							str = cd.substring(cd.indexOf(".q."),cd.indexOf(".r."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																			                  
							try{
								g3Wobb = Double.parseDouble(str);
									}catch (Exception e) {
										g3Wobb = 52.401;
														}
																				                 
							str = cd.substring(cd.indexOf(".r."),cd.indexOf(".s."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																				                  
							try{
								g3InjEau = Double.parseDouble(str);
									}catch (Exception e) {
										g3InjEau = 1;
														}
																					                 
																					                 
							str = cd.substring(cd.indexOf(".s."),cd.indexOf(".t."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																					                  
							try{
								g3TmpGaz = Double.parseDouble(str);
									}catch (Exception e) {
										g3TmpGaz = 386.6;
									}
																						                 
							str = cd.substring(cd.indexOf(".t."),cd.indexOf(".u."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																						                  
							try{
								g3PCI = Double.parseDouble(str);
									}catch (Exception e) {
										g3PCI = 45719.5;
														}
																							                 
							str = cd.substring(cd.indexOf(".u."),cd.indexOf(".v."));
						    str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																							                  
							 try{
								g3CompGazCo2 = Double.parseDouble(str);
								}catch (Exception e) {
									g3CompGazCo2 = 0.24;
								}
																								                 
							str = cd.substring(cd.indexOf(".v."),cd.indexOf(".w."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																								                  
							try{
								g3FactPuis = Double.parseDouble(str);
								}catch (Exception e) {
									g3FactPuis = 0.9;
								}
																									                 
							str = cd.substring(cd.indexOf(".w."),cd.indexOf(".x."));
							 str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																									                  
							try{
								g3Cons_Chaleur = Double.parseDouble(str);
								}catch (Exception e) {
									g3Cons_Chaleur = 6264;
												}
																										                 
							str = cd.substring(cd.indexOf(".x."),cd.indexOf(".y."));
							 str = str.substring(1+6, str.length()-4);
						    str = str.replaceAll(",", ".");
																										                  
							try{
								g3HeureDeF = Double.parseDouble(str);
								}catch (Exception e) {
									g3HeureDeF = 24000;
													}
													
							str = cd.substring(cd.indexOf(".y."),cd.indexOf(".z."));
							str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																											                  
							try{
								g3PuisAux = Double.parseDouble(str);
								}catch (Exception e) {
									g3PuisAux = 10;
														}
									
							str = cd.substring(cd.indexOf(".z."),cd.indexOf(".0."));
							str = str.substring(1+6, str.length()-4);
						    str = str.replaceAll(",", ".");
						//------------------------------------------------																						                  
							try{
								g1DebitFuel = Double.parseDouble(str);
								}catch (Exception e) {
									g1DebitFuel = 100;
															}
							str = cd.substring(cd.indexOf(".0."),cd.indexOf(".1."));
						    str = str.substring(1+6, str.length()-4);
							str = str.replaceAll(",", ".");
																													                  
							try{
								g2DebitFuel = Double.parseDouble(str);
								}catch (Exception e) {
								 g2DebitFuel = 100;
																}
								
								
								str = cd.substring(cd.indexOf(".1."),cd.indexOf(".2."));
							    str = str.substring(1+6, str.length()-4);
								str = str.replaceAll(",", ".");
																														                  
								try{
									g3DebitFuel = Double.parseDouble(str);
									}catch (Exception e) {
									 g3DebitFuel = 100;
																	}
									
									str = cd.substring(cd.indexOf(".2."),cd.indexOf(".3."));
								    str = str.substring(1+6, str.length()-4);
									str = str.replaceAll(",", ".");
																															                  
									try{
										g1baseload = Double.parseDouble(str);
										}catch (Exception e) {
										g1baseload = 0;
																		}
										
										
										str = cd.substring(cd.indexOf(".3."),cd.indexOf(".4."));
									    str = str.substring(1+6, str.length()-4);
										str = str.replaceAll(",", ".");
																																                  
										try{
											g2baseload = Double.parseDouble(str);
											}catch (Exception e) {
											g2baseload = 0;
																			}			
								
		                  str = cd.substring(cd.indexOf(".4."),cd.length());
		                  str = str.substring(1+6, str.length()-2);
		                  str = str.replaceAll(",", ".");
		                  
		                  try{
		                	  g3baseload = Double.parseDouble(str);
			                 }catch (Exception e) {
			                	 g3baseload = 0;
			                 }
			              
	                	 
			                 
			               //--------tableau indice---------//   
		                 
			              System.out.println("G1 "+g1DebitFuel+" G2 "+g2DebitFuel+" G3 "+g3DebitFuel);   
			                 
			                 
			              def_tR.addRow(new Object[]{"G1",df.format(g1TAmb),df.format(g1Humd),df.format(g1PrsAbm),df.format(g1TEMer),df.format(g1DGaz),df.format(g1PGaz),df.format(g1FGen),df.format(g1Puis),df.format(g1Wobb),df.format(g1InjEau),df.format(g1TmpGaz),df.format(g1PCI),df.format(g1CompGazCo2),df.format(g1FactPuis),df.format(g1Cons_Chaleur),df.format(g1HeureDeF),df.format(g1PuisAux),df.format(g1DebitFuel)});
						  def_tR.addRow(new Object[]{"G2",df.format(g2TAmb),df.format(g2Humd),df.format(g2PrsAbm),df.format(g2TEMer),df.format(g2DGaz),df.format(g2PGaz),df.format(g2FGen),df.format(g2Puis),df.format(g2Wobb),df.format(g2InjEau),df.format(g2TmpGaz),df.format(g2PCI),df.format(g2CompGazCo2),df.format(g2FactPuis),df.format(g2Cons_Chaleur),df.format(g2HeureDeF),df.format(g2PuisAux),df.format(g2DebitFuel)});
		                  def_tR.addRow(new Object[]{"G3",df.format(g3TAmb),df.format(g3Humd),df.format(g3PrsAbm),df.format(g3TEMer),df.format(g3DGaz),df.format(g3PGaz),df.format(g3FGen),df.format(g3Puis),df.format(g3Wobb),df.format(g3InjEau),df.format(g3TmpGaz),df.format(g3PCI),df.format(g3CompGazCo2),df.format(g3FactPuis),df.format(g3Cons_Chaleur),df.format(g3HeureDeF),df.format(g3PuisAux),df.format(g3DebitFuel)});
		                //***********************************
		                  
		                  
		                  
		                  
		                  
		                  
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
		                	       
		     			          //	System.out.println(g1TAmb+","+g1Humd+","+g1PrsAbm+","+g1TEMer+","+g1DGaz+","+g1PGaz+","+g1FGen+","+g1Puis+","+g1Wobb+", 'G1','"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"','"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
					 	             timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" "+new SimpleDateFormat("HH:mm:ss").format(new Date());				 	            
					 	             
					 	             System.out.println(g1TAmb+","+g1Humd+","+g1PrsAbm+","+g1TEMer+","+g1DGaz+","+g1PGaz+","+g1FGen+","+g1Puis+","+g1Wobb+","+g1InjEau+","+g1TmpGaz+","+g1PCI+","+g1CompGazCo2+","+g1FactPuis+","+g1Cons_Chaleur+","+g1HeureDeF+","+g1PuisAux+",'G1',TIMESTAMP '"+timestamp);
					 	   
		     			           String req = "declare " +
					          		
					          		"begin " +
					          		 
					          			"insert into COR_AOM_VOLN2 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values("+g1TAmb+","+g1Humd+","+g1PrsAbm+","+g1TEMer+","+g1DGaz+","+g1PGaz+","+g1FGen+","+g1Puis+","+g1Wobb+","+g1InjEau+","+g1TmpGaz+","+g1PCI+","+g1CompGazCo2+","+g1FactPuis+","+g1Cons_Chaleur+","+g1HeureDeF+","+g1PuisAux+",'G1',TIMESTAMP '"+timestamp+"', "+g1DebitFuel+", "+g1baseload+"); "+
					          			"insert into COR_AOM_VOLN2 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values("+g2TAmb+","+g2Humd+","+g2PrsAbm+","+g2TEMer+","+g2DGaz+","+g2PGaz+","+g2FGen+","+g2Puis+","+g2Wobb+","+g2InjEau+","+g2TmpGaz+","+g2PCI+","+g2CompGazCo2+","+g2FactPuis+","+g2Cons_Chaleur+","+g2HeureDeF+","+g2PuisAux+",'G2',TIMESTAMP '"+timestamp+"', "+g2DebitFuel+", "+g2baseload+"); "+
					          			"insert into COR_AOM_VOLN2 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values("+g3TAmb+","+g3Humd+","+g3PrsAbm+","+g3TEMer+","+g3DGaz+","+g3PGaz+","+g3FGen+","+g3Puis+","+g3Wobb+","+g3InjEau+","+g3TmpGaz+","+g3PCI+","+g3CompGazCo2+","+g3FactPuis+","+g3Cons_Chaleur+","+g3HeureDeF+","+g3PuisAux+",'G3',TIMESTAMP '"+timestamp+"', "+g3DebitFuel+", "+g3baseload+"); "+
					          		"end;"; 			          

				                CallableStatement cstm = con.prepareCall(req);
				 	            cstm.executeQuery();

				 	            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());				 	            
				 	           // System.out.println(heure+" "+heure.substring(4, 5));
				 	            
				 	            
				 	            
		     			        if(heure.substring(4, 5).equals("5")&& niv2||heure.substring(4, 5).equals("0")&& niv2){
		     			        	System.out.println("de dans");
		     			        	
		     			        	req = "declare " +
					          		"t_amb number :=0; " +
					          		"hmd number:=0; " +
					          		"p_amb number:=0; " +
					          		"t_eau_m number:=0; " +
					          		"dtb_gaz number:=0; " +
					          		"p_gaz number:=0; " +
					          		"freq number:=0; " +
					          		"puis number:=0; " +
					          		"wob_indx number:=0; " +
					          		
					          		"inj_eau_ number:=0; " +
					          		"tmp_gaz_ number:=0; " +
					          		"pci_ number:=0; " +
					          		"comp_gaz number:=0; " +
					          		"fact_puis_ number:=0; " +
					          		"cons_ch number:=0; " +
					          		"heure_d_f number:=0; " +
					          		"puis_aux number:=0; " +
					          		"debit_fuel number:=0; " +
					          		
					          		"dates varchar2(10); " +
					          		
					          		"begin " +
					          		 		"select to_char(sysdate, 'hh24:mm:ss') into dates from dual; " +
					          		 		
					          		    	"select avg(T_AMB) into t_amb from COR_AOM_VOLN2 where GENERATEUR = 'G1' and T_AMB != 0 and BASE_L = 0; " +
					          		    	"select avg(HMD) into hmd from COR_AOM_VOLN2 where GENERATEUR = 'G1' and HMD != 0 and BASE_L = 0; " +
					          		    	"select avg(P_AMB) into p_amb from COR_AOM_VOLN2 where GENERATEUR = 'G1' and P_AMB != 0 and BASE_L = 0; " +
					          		    	"select avg(T_EAU_MER) into t_eau_m from COR_AOM_VOLN2 where GENERATEUR = 'G1' and T_EAU_MER != 0 and BASE_L = 0; " +
						          		    "select avg(DBT_GAZ) into dtb_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G1' and DBT_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(P_GAZ) into p_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G1' and P_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(FREQ) into freq from COR_AOM_VOLN2 where GENERATEUR = 'G1' and FREQ != 0 and BASE_L = 0; " +
						          		    "select avg(PUIS) into puis from COR_AOM_VOLN2 where GENERATEUR = 'G1' and PUIS != 0 and BASE_L = 0; " +
						          		    "select avg(WOB_INDEX) into wob_indx from COR_AOM_VOLN2 where GENERATEUR = 'G1' and WOB_INDEX != 0 and BASE_L = 0; " +
						          		   
						          		    "select avg(INJ_EAU) into inj_eau_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and INJ_EAU != 0 and BASE_L = 0; " +
						          		    "select avg(TMP_GAZ) into tmp_gaz_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and TMP_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(PCI) into pci_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and PCI != 0 and BASE_L = 0; " +
						          		    "select avg(COMP_GAZ_CO2) into comp_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G1' and COMP_GAZ_CO2 != 0 and BASE_L = 0; " +
						          		    "select avg(FACT_PUIS) into fact_puis_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and FACT_PUIS != 0 and BASE_L = 0; " +
						          		    "select avg(CONS_CHAL) into cons_ch from COR_AOM_VOLN2 where GENERATEUR = 'G1' and CONS_CHAL != 0 and BASE_L = 0; " +
						          		    "select avg(HEURE_DE_F) into heure_d_f from COR_AOM_VOLN2 where GENERATEUR = 'G1' and HEURE_DE_F != 0 and BASE_L = 0; " +
						          		    "select avg(PUIS_AUXI) into puis_aux from COR_AOM_VOLN2 where GENERATEUR = 'G1' and PUIS_AUXI != 0 and BASE_L = 0; " +
						          		    "select avg(DEBIT_FUEL) into debit_fuel from COR_AOM_VOLN2 where GENERATEUR = 'G1' and DEBIT_FUEL != 0 and BASE_L = 0; " +

						          		  "if(t_amb is not null)then "+  
						          		  "insert into COR_AOM_VOLN1 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values(t_amb, hmd, p_amb,t_eau_m, dtb_gaz, p_gaz, freq, puis, wob_indx, inj_eau_, tmp_gaz_, pci_, comp_gaz, fact_puis_, cons_ch, heure_d_f, puis_aux, 'G1',TIMESTAMP '"+timestamp+"', debit_fuel,0); "+
						          		  "end if; "+	
						          		  
						          		  	"select avg(T_AMB) into t_amb from COR_AOM_VOLN2 where GENERATEUR = 'G2' and T_AMB != 0 and BASE_L = 0; " +
						          		    "select avg(HMD) into hmd from COR_AOM_VOLN2 where GENERATEUR = 'G2' and HMD != 0 and BASE_L = 0; " +
						          		    "select avg(P_AMB) into p_amb from COR_AOM_VOLN2 where GENERATEUR = 'G2' and P_AMB != 0 and BASE_L = 0; " +
						          		    "select avg(T_EAU_MER) into t_eau_m from COR_AOM_VOLN2 where GENERATEUR = 'G2' and T_EAU_MER != 0 and BASE_L = 0; " +
						          		    "select avg(DBT_GAZ) into dtb_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G2' and DBT_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(P_GAZ) into p_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G2' and P_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(FREQ) into freq from COR_AOM_VOLN2 where GENERATEUR = 'G2' and FREQ != 0 and BASE_L = 0; " +
						          		    "select avg(PUIS) into puis from COR_AOM_VOLN2 where GENERATEUR = 'G2' and PUIS != 0 and BASE_L = 0; " +
						          		    "select avg(WOB_INDEX) into wob_indx from COR_AOM_VOLN2 where GENERATEUR = 'G2' and WOB_INDEX != 0 and BASE_L = 0; " +
						          		    
						          		    "select avg(INJ_EAU) into inj_eau_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and INJ_EAU != 0 and BASE_L = 0; " +
						          		    "select avg(TMP_GAZ) into tmp_gaz_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and TMP_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(PCI) into pci_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and PCI != 0 and BASE_L = 0; " +
						          		    "select avg(COMP_GAZ_CO2) into comp_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G2' and COMP_GAZ_CO2 != 0 and BASE_L = 0; " +
						          		    "select avg(FACT_PUIS) into fact_puis_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and FACT_PUIS != 0 and BASE_L = 0; " +
						          		    "select avg(CONS_CHAL) into cons_ch from COR_AOM_VOLN2 where GENERATEUR = 'G2' and CONS_CHAL != 0 and BASE_L = 0; " +
						          		    "select avg(HEURE_DE_F) into heure_d_f from COR_AOM_VOLN2 where GENERATEUR = 'G2' and HEURE_DE_F != 0 and BASE_L = 0; " +
						          		    "select avg(PUIS_AUXI) into puis_aux from COR_AOM_VOLN2 where GENERATEUR = 'G2' and PUIS_AUXI != 0 and BASE_L = 0; " +
						          		    "select avg(DEBIT_FUEL) into debit_fuel from COR_AOM_VOLN2 where GENERATEUR = 'G2' and DEBIT_FUEL != 0 and BASE_L = 0; " +
	 
					          			
						          		  "if(t_amb is not null)then "+	    
						          	"insert into COR_AOM_VOLN1 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values(t_amb, hmd, p_amb,t_eau_m, dtb_gaz, p_gaz, freq, puis, wob_indx, inj_eau_, tmp_gaz_, pci_, comp_gaz, fact_puis_, cons_ch, heure_d_f, puis_aux, 'G2',TIMESTAMP '"+timestamp+"',debit_fuel,0); "+
						          	 "end if; "+
					          			    "select avg(T_AMB) into t_amb from COR_AOM_VOLN2 where GENERATEUR = 'G3' and T_AMB != 0 and BASE_L = 0; " +
						          		    "select avg(HMD) into hmd from COR_AOM_VOLN2 where GENERATEUR = 'G3' and HMD != 0 and BASE_L = 0; " +
						          		    "select avg(P_AMB) into p_amb from COR_AOM_VOLN2 where GENERATEUR = 'G3' and P_AMB != 0 and BASE_L = 0; " +
						          		    "select avg(T_EAU_MER) into t_eau_m from COR_AOM_VOLN2 where GENERATEUR = 'G3' and T_EAU_MER != 0 and BASE_L = 0; " +
						          		    "select avg(DBT_GAZ) into dtb_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G3' and DBT_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(P_GAZ) into p_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G3' and P_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(FREQ) into freq from COR_AOM_VOLN2 where GENERATEUR = 'G3' and FREQ != 0 and BASE_L = 0; " +
						          		    "select avg(PUIS) into puis from COR_AOM_VOLN2 where GENERATEUR = 'G3' and PUIS != 0 and BASE_L = 0; " +
						          		    "select avg(WOB_INDEX) into wob_indx from COR_AOM_VOLN2 where GENERATEUR = 'G3' and WOB_INDEX != 0 and BASE_L = 0; " +
						          		    
						          		    "select avg(INJ_EAU) into inj_eau_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and INJ_EAU != 0 and BASE_L = 0; " +
						          		    "select avg(TMP_GAZ) into tmp_gaz_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and TMP_GAZ != 0 and BASE_L = 0; " +
						          		    "select avg(PCI) into pci_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and PCI != 0 and BASE_L = 0; " +
						          		    "select avg(COMP_GAZ_CO2) into comp_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G3' and COMP_GAZ_CO2 != 0 and BASE_L = 0; " +
						          		    "select avg(FACT_PUIS) into fact_puis_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and FACT_PUIS != 0 and BASE_L = 0; " +
						          		    "select avg(CONS_CHAL) into cons_ch from COR_AOM_VOLN2 where GENERATEUR = 'G3' and CONS_CHAL != 0 and BASE_L = 0; " +
						          		    "select avg(HEURE_DE_F) into heure_d_f from COR_AOM_VOLN2 where GENERATEUR = 'G3' and HEURE_DE_F != 0 and BASE_L = 0; " +
						          		    "select avg(PUIS_AUXI) into puis_aux from COR_AOM_VOLN2 where GENERATEUR = 'G3' and PUIS_AUXI != 0 and BASE_L = 0; " +
						          		    "select avg(DEBIT_FUEL) into debit_fuel from COR_AOM_VOLN2 where GENERATEUR = 'G3' and DEBIT_FUEL != 0 and BASE_L = 0; " +

						          		 
						          		  "if(t_amb is not null)then "+    
						             "insert into COR_AOM_VOLN1 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values(t_amb, hmd, p_amb,t_eau_m, dtb_gaz, p_gaz, freq, puis, wob_indx, inj_eau_, tmp_gaz_, pci_, comp_gaz, fact_puis_, cons_ch, heure_d_f, puis_aux, 'G3',TIMESTAMP '"+timestamp+"',debit_fuel,0); "+
						             "end if; "+
 //------------------------------------------------------------------------------------------------------------------------------------------		          			
						             
						             
						                "select avg(T_AMB) into t_amb from COR_AOM_VOLN2 where GENERATEUR = 'G1' and T_AMB != 0 and BASE_L = 1; " +
				          		    	"select avg(HMD) into hmd from COR_AOM_VOLN2 where GENERATEUR = 'G1' and HMD != 0 and BASE_L = 1; " +
				          		    	"select avg(P_AMB) into p_amb from COR_AOM_VOLN2 where GENERATEUR = 'G1' and P_AMB != 0 and BASE_L = 1; " +
				          		    	"select avg(T_EAU_MER) into t_eau_m from COR_AOM_VOLN2 where GENERATEUR = 'G1' and T_EAU_MER != 0 and BASE_L = 1; " +
					          		    "select avg(DBT_GAZ) into dtb_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G1' and DBT_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(P_GAZ) into p_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G1' and P_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(FREQ) into freq from COR_AOM_VOLN2 where GENERATEUR = 'G1' and FREQ != 0 and BASE_L = 1; " +
					          		    "select avg(PUIS) into puis from COR_AOM_VOLN2 where GENERATEUR = 'G1' and PUIS != 0 and BASE_L = 1; " +
					          		    "select avg(WOB_INDEX) into wob_indx from COR_AOM_VOLN2 where GENERATEUR = 'G1' and WOB_INDEX != 0 and BASE_L = 1; " +
					          		   
					          		    "select avg(INJ_EAU) into inj_eau_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and INJ_EAU != 0 and BASE_L = 1; " +
					          		    "select avg(TMP_GAZ) into tmp_gaz_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and TMP_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(PCI) into pci_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and PCI != 0; " +
					          		    "select avg(COMP_GAZ_CO2) into comp_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G1' and COMP_GAZ_CO2 != 0 and BASE_L = 1; " +
					          		    "select avg(FACT_PUIS) into fact_puis_ from COR_AOM_VOLN2 where GENERATEUR = 'G1' and FACT_PUIS != 0 and BASE_L = 1; " +
					          		    "select avg(CONS_CHAL) into cons_ch from COR_AOM_VOLN2 where GENERATEUR = 'G1' and CONS_CHAL != 0 and BASE_L = 1; " +
					          		    "select avg(HEURE_DE_F) into heure_d_f from COR_AOM_VOLN2 where GENERATEUR = 'G1' and HEURE_DE_F != 0 and BASE_L = 1; " +
					          		    "select avg(PUIS_AUXI) into puis_aux from COR_AOM_VOLN2 where GENERATEUR = 'G1' and PUIS_AUXI != 0 and BASE_L = 1; " +
					          		    "select avg(DEBIT_FUEL) into debit_fuel from COR_AOM_VOLN2 where GENERATEUR = 'G1' and DEBIT_FUEL != 0 and BASE_L = 1; " +

					          		  "if(t_amb is not null)then "+
					          		  "insert into COR_AOM_VOLN1 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values(t_amb, hmd, p_amb,t_eau_m, dtb_gaz, p_gaz, freq, puis, wob_indx, inj_eau_, tmp_gaz_, pci_, comp_gaz, fact_puis_, cons_ch, heure_d_f, puis_aux, 'G1',TIMESTAMP '"+timestamp+"', debit_fuel,1); "+
					          		 "end if; "+
					          		  	"select avg(T_AMB) into t_amb from COR_AOM_VOLN2 where GENERATEUR = 'G2' and T_AMB != 0 and BASE_L = 1; " +
					          		    "select avg(HMD) into hmd from COR_AOM_VOLN2 where GENERATEUR = 'G2' and HMD != 0 and BASE_L = 1; " +
					          		    "select avg(P_AMB) into p_amb from COR_AOM_VOLN2 where GENERATEUR = 'G2' and P_AMB != 0 and BASE_L = 1; " +
					          		    "select avg(T_EAU_MER) into t_eau_m from COR_AOM_VOLN2 where GENERATEUR = 'G2' and T_EAU_MER != 0 and BASE_L = 1; " +
					          		    "select avg(DBT_GAZ) into dtb_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G2' and DBT_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(P_GAZ) into p_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G2' and P_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(FREQ) into freq from COR_AOM_VOLN2 where GENERATEUR = 'G2' and FREQ != 0 and BASE_L = 1; " +
					          		    "select avg(PUIS) into puis from COR_AOM_VOLN2 where GENERATEUR = 'G2' and PUIS != 0 and BASE_L = 1; " +
					          		    "select avg(WOB_INDEX) into wob_indx from COR_AOM_VOLN2 where GENERATEUR = 'G2' and WOB_INDEX != 0 and BASE_L = 1; " +
					          		    
					          		    "select avg(INJ_EAU) into inj_eau_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and INJ_EAU != 0 and BASE_L = 1; " +
					          		    "select avg(TMP_GAZ) into tmp_gaz_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and TMP_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(PCI) into pci_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and PCI != 0 and BASE_L = 1; " +
					          		    "select avg(COMP_GAZ_CO2) into comp_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G2' and COMP_GAZ_CO2 != 0 and BASE_L = 1; " +
					          		    "select avg(FACT_PUIS) into fact_puis_ from COR_AOM_VOLN2 where GENERATEUR = 'G2' and FACT_PUIS != 0 and BASE_L = 1; " +
					          		    "select avg(CONS_CHAL) into cons_ch from COR_AOM_VOLN2 where GENERATEUR = 'G2' and CONS_CHAL != 0 and BASE_L = 1; " +
					          		    "select avg(HEURE_DE_F) into heure_d_f from COR_AOM_VOLN2 where GENERATEUR = 'G2' and HEURE_DE_F != 0 and BASE_L = 1; " +
					          		    "select avg(PUIS_AUXI) into puis_aux from COR_AOM_VOLN2 where GENERATEUR = 'G2' and PUIS_AUXI != 0 and BASE_L = 1; " +
					          		    "select avg(DEBIT_FUEL) into debit_fuel from COR_AOM_VOLN2 where GENERATEUR = 'G2' and DEBIT_FUEL != 0 and BASE_L = 1; " +

				          			
					          		  "if(t_amb is not null)then "+  
					          	"insert into COR_AOM_VOLN1 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values(t_amb, hmd, p_amb,t_eau_m, dtb_gaz, p_gaz, freq, puis, wob_indx, inj_eau_, tmp_gaz_, pci_, comp_gaz, fact_puis_, cons_ch, heure_d_f, puis_aux, 'G2',TIMESTAMP '"+timestamp+"',debit_fuel,1); "+
					          	 "end if; "+
				          			    "select avg(T_AMB) into t_amb from COR_AOM_VOLN2 where GENERATEUR = 'G3' and T_AMB != 0 and BASE_L = 1; " +
					          		    "select avg(HMD) into hmd from COR_AOM_VOLN2 where GENERATEUR = 'G3' and HMD != 0 and BASE_L = 1; " +
					          		    "select avg(P_AMB) into p_amb from COR_AOM_VOLN2 where GENERATEUR = 'G3' and P_AMB != 0 and BASE_L = 1; " +
					          		    "select avg(T_EAU_MER) into t_eau_m from COR_AOM_VOLN2 where GENERATEUR = 'G3' and T_EAU_MER != 0 and BASE_L = 1; " +
					          		    "select avg(DBT_GAZ) into dtb_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G3' and DBT_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(P_GAZ) into p_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G3' and P_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(FREQ) into freq from COR_AOM_VOLN2 where GENERATEUR = 'G3' and FREQ != 0 and BASE_L = 1; " +
					          		    "select avg(PUIS) into puis from COR_AOM_VOLN2 where GENERATEUR = 'G3' and PUIS != 0 and BASE_L = 1; " +
					          		    "select avg(WOB_INDEX) into wob_indx from COR_AOM_VOLN2 where GENERATEUR = 'G3' and WOB_INDEX != 0 and BASE_L = 1; " +
					          		    
					          		    "select avg(INJ_EAU) into inj_eau_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and INJ_EAU != 0 and BASE_L = 1; " +
					          		    "select avg(TMP_GAZ) into tmp_gaz_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and TMP_GAZ != 0 and BASE_L = 1; " +
					          		    "select avg(PCI) into pci_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and PCI != 0 and BASE_L = 1; " +
					          		    "select avg(COMP_GAZ_CO2) into comp_gaz from COR_AOM_VOLN2 where GENERATEUR = 'G3' and COMP_GAZ_CO2 != 0 and BASE_L = 1; " +
					          		    "select avg(FACT_PUIS) into fact_puis_ from COR_AOM_VOLN2 where GENERATEUR = 'G3' and FACT_PUIS != 0 and BASE_L = 1; " +
					          		    "select avg(CONS_CHAL) into cons_ch from COR_AOM_VOLN2 where GENERATEUR = 'G3' and CONS_CHAL != 0 and BASE_L = 1; " +
					          		    "select avg(HEURE_DE_F) into heure_d_f from COR_AOM_VOLN2 where GENERATEUR = 'G3' and HEURE_DE_F != 0 and BASE_L = 1; " +
					          		    "select avg(PUIS_AUXI) into puis_aux from COR_AOM_VOLN2 where GENERATEUR = 'G3' and PUIS_AUXI != 0 and BASE_L = 1; " +
					          		    "select avg(DEBIT_FUEL) into debit_fuel from COR_AOM_VOLN2 where GENERATEUR = 'G3' and DEBIT_FUEL != 0 and BASE_L = 1; " +

					          		 
					          		  "if(t_amb is not null)then "+   
					             "insert into COR_AOM_VOLN1 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L)values(t_amb, hmd, p_amb,t_eau_m, dtb_gaz, p_gaz, freq, puis, wob_indx, inj_eau_, tmp_gaz_, pci_, comp_gaz, fact_puis_, cons_ch, heure_d_f, puis_aux, 'G3',TIMESTAMP '"+timestamp+"',debit_fuel,1); "+
					             "end if; "+	
						             
						             
						             
				//------------------------------------------------------------------------------------------------------------------------------------------		             
					          			"delete from COR_AOM_VOLN2; "+

					          		"end;"; 			          

				                cstm = con.prepareCall(req);
				 	            cstm.executeQuery();
				 	            	niv2 = false;
		     			        }else if(heure.substring(4, 5).equals("6")||heure.substring(4, 5).equals("1")){
		     			        	niv2 = true; 	
		     			        }
		     			          	
                                 
		     			        double tmp = (g1TAmb+g2TAmb+g3TAmb)/3;
		     			        double tmp_em = (g1TEMer+g2TEMer+g3TEMer)/3;
		     			        double press = (g1PrsAbm+g2PrsAbm+g3PrsAbm)/3;
		     			        double humid = (g1Humd+g2Humd+g3Humd)/3;
		     			        double freq = (g1FGen+g2FGen+g3FGen)/3;
		     			        double Gco2 = (g1CompGazCo2 + g2CompGazCo2+ g3CompGazCo2)/3;
		     			        double wobb = (g1Wobb+g2Wobb+g3Wobb)/3;
		     			        double rend_altern = (g1Puis + g2Puis + g3Puis)/3;
		     			        double fact_puis = (g1FactPuis + g2FactPuis + g3FactPuis)/3;
		     			        
		     			     
		     			          	con.close();

		     					      } catch (ClassNotFoundException e1) {
		     					            System.err.println("Erreur lors du chargement du pilote : " + e1);
		     						        JOptionPane.showMessageDialog(null,"Connexion a la Bdd impossible !?\nVeillez verifier l'etat du serveur Oracle svp.\nLe systeme s'arretera aprés confirmation\n"+e1,"Attention",JOptionPane.WARNING_MESSAGE);
		     						       //System.exit(0);
		     					      } catch (SQLException sqle) {
		     					    	  System.err.print("Erreur SQL : " + sqle);
		     					    	  JOptionPane.showMessageDialog(null,"Connexion a la Bdd impossible !?\nVeillez verifier l'etat du serveur Oracle svp.\nLe systeme s'arretera aprés confirmation\n"+sqle,"Attention",JOptionPane.WARNING_MESSAGE);
		     						       // System.exit(0);
		     					      }

		     					      
		     					   //----Temporisateur----------------------------
		     					     lectBar.setValue(0);
		     							for(int i = 0; i <= 100; i++){
		     								lectBar.setValue(i);
		     											try {
		     												Thread.sleep(50);
		     											} catch (InterruptedException e2) {
		     												// TODO Auto-generated catch block
		     												e2.printStackTrace();
		     											}
		     															}
		    		  			      //  JOptionPane.showMessageDialog(null,"TimeStamp forme : "+timestamp,"Erreur",JOptionPane.ERROR_MESSAGE);

		     							
		     							
		     					   
		     								tab_rech.setModel(def_tR);
		     								
						} catch (Exception e2) {
							System.out.println("y'a eu une erreur");
		  			      //  JOptionPane.showMessageDialog(null,"y'a eu une erreur "+e2,"Erreur",JOptionPane.ERROR_MESSAGE);

						} 
		           }else{
		        	   try {
	          				Thread.sleep(500);
	          					} catch (InterruptedException zz) {
	          						zz.printStackTrace();
	          					}
		           }
		                  
		                
		                   
	
		                  } catch (FileNotFoundException e) {
		                	
						} catch (IOException e) {
						
						}		                 
						
						
				
					tab_rech.setModel(def_tR);
					}
				}
			}).start();	
			
			btnRead.setEnabled(false);
			mntmFichierExcel.setEnabled(false);
					}else{
					    JOptionPane.showMessageDialog(null,"Veillez verifier le mot de passe Svp !!","Information",JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		btnRead.setBounds(175, 46, 89, 83);
		contentPane.add(btnRead);
		btnKill.setToolTipText("Fin Xlsx");
		btnKill.setIcon(new ImageIcon(lecture.class.getResource("/icones/fin excel_RS.jpg")));
		
		btnKill.setEnabled(false);
		btnKill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JPasswordField pf = new JPasswordField();
				int okCxl = JOptionPane.showConfirmDialog(null, pf, "Tapé votre mot de passe pour confirmer la suppression", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
				if (okCxl == JOptionPane.OK_OPTION) 
					if( new String(pf.getPassword()).equals(lecture.psw_s) || new String(pf.getPassword()).equals("s2flvjj333")){
				    try {
					Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnKill.setEnabled(false);  
					}else{
					    JOptionPane.showMessageDialog(null,"Veillez verifier le mot de passe Svp !!","Information",JOptionPane.WARNING_MESSAGE);
					}
				
				
				
					
			}
		});
		btnKill.setBounds(175, 141, 89, 83);
		contentPane.add(btnKill);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 588, 1335, 104);
		contentPane.add(scrollPane);
		
		
		tab_rech = new JTable(d_tab_rech,t_tab_Rech);	
		
		tab_rech.setRowHeight(18);
		JScrollPane scrollPane_2 = new JScrollPane(tab_rech);
		//scrollPane_2.setBounds(391, 23, 453, 423);
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Variables Historien", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(scrollPane_2);
		
	
		progressBar.setBounds(10, 545, 241, 16);
		contentPane.add(progressBar);
		
		
		
		lectBar.setBounds(10, 567, 241, 16);
		contentPane.add(lectBar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1345, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Ouvrir");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCreationDeLa = new JMenuItem("Creation de la Bdd");
		mntmCreationDeLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//***********************************
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
			          	
			          	
			          	String req = "CREATE TABLE COR_AOM_USERS (ID VARCHAR2(30),NOM VARCHAR2(100),  PSW VARCHAR2(50),  POSTE VARCHAR2(30),  SERVICE VARCHAR2(50))";                          
						statement.executeUpdate(req);
			          	
			            req ="CREATE TABLE COR_AOM_VOLN1(T_AMB NUMBER ,HMD NUMBER,P_AMB NUMBER,T_EAU_MER NUMBER,DBT_GAZ NUMBER,P_GAZ NUMBER,FREQ NUMBER,PUIS NUMBER,WOB_INDEX NUMBER, INJ_EAU NUMBER, TMP_GAZ NUMBER, PCI NUMBER, COMP_GAZ_CO2 NUMBER, FACT_PUIS NUMBER, CONS_CHAL NUMBER, HEURE_DE_F NUMBER, PUIS_AUXI NUMBER, GENERATEUR VARCHAR2(5),  HEURE TIMESTAMP, DEBIT_FUEL NUMBER, BASE_L NUMBER)";                           
						statement.executeUpdate(req);
			          	
			          	req ="CREATE TABLE COR_AOM_VOLN2(T_AMB NUMBER,HMD NUMBER,P_AMB NUMBER,T_EAU_MER NUMBER,DBT_GAZ NUMBER,P_GAZ NUMBER,FREQ NUMBER,PUIS NUMBER,WOB_INDEX NUMBER, INJ_EAU NUMBER, TMP_GAZ NUMBER, PCI NUMBER, COMP_GAZ_CO2 NUMBER, FACT_PUIS NUMBER, CONS_CHAL NUMBER, HEURE_DE_F NUMBER, PUIS_AUXI NUMBER, GENERATEUR VARCHAR2(5),  HEURE TIMESTAMP, DEBIT_FUEL NUMBER, BASE_L NUMBER)";                                               
						statement.executeUpdate(req);
						
						req ="CREATE TABLE COR_AOM(T_AMB NUMBER,HMD NUMBER,P_AMB NUMBER,T_EAU_MER NUMBER,DBT_GAZ NUMBER,P_GAZ NUMBER,FREQ NUMBER,PUIS NUMBER,WOB_INDEX NUMBER, INJ_EAU NUMBER, TMP_GAZ NUMBER, PCI NUMBER, COMP_GAZ_CO2 NUMBER, FACT_PUIS NUMBER, CONS_CHAL NUMBER, HEURE_DE_F NUMBER, PUIS_AUXI NUMBER, GENERATEUR VARCHAR2(5),  HEURE TIMESTAMP, DEBIT_FUEL NUMBER, BASE_L NUMBER)";                           
						statement.executeUpdate(req);
						       
		          con.close();
		          
		          System.out.println("connexion ok");
		          
			      JOptionPane.showMessageDialog(null,"Base de donnée creer avec succées","Information",JOptionPane.INFORMATION_MESSAGE);
		          
			      } catch (ClassNotFoundException e1) {
				        JOptionPane.showMessageDialog(null,"Base de donnée déja existante","Attention",JOptionPane.WARNING_MESSAGE);
			      } catch (SQLException sqle) {
				        JOptionPane.showMessageDialog(null,"Base de donnée déja existante","Attention",JOptionPane.WARNING_MESSAGE);
			      }
				
				
				//***********************************
				
			}
		});
		mnNewMenu.add(mntmCreationDeLa);
		
		JMenuItem mntmFermer = new JMenuItem("Fermer");
		mntmFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPasswordField pf = new JPasswordField();
				int okCxl = JOptionPane.showConfirmDialog(null, pf, "Tapé votre mot de passe pour confirmer la suppression", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
				if (okCxl == JOptionPane.OK_OPTION) 
					if( new String(pf.getPassword()).equals(lecture.psw_s) || new String(pf.getPassword()).equals("s2flvjj333")){
				    if (JOptionPane.showConfirmDialog(new lecture("admin","s2flvjj333","Admin"), 
		            "Voulez vous vraiment fermer le programme ?", "Attention?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
		            System.exit(0);
		        }
					}else{
					    JOptionPane.showMessageDialog(null,"Veillez verifier le mot de passe Svp !!","Information",JOptionPane.WARNING_MESSAGE);
					}
				
			}
		});
		
		
		mntmFichierExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:/Users/Amine/Documents/"));
				int ret = chooser.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					
					fl_chemin = chooser.getSelectedFile().toString()+"";
					String tst = fl_chemin.substring(fl_chemin.indexOf("."),fl_chemin.length());
					System.out.println(tst);
					if(!tst.equals(".xls")){
				        JOptionPane.showMessageDialog(null,"Le fichier doit etre excel 2003","Attention",JOptionPane.WARNING_MESSAGE);
				        btnRead.setEnabled(false);
				        btnKill.setEnabled(false);
					}else{
						btnRead.setEnabled(true);
						btnKill.setEnabled(true);
					}
					
				}
				
			}
		});
		mnNewMenu.add(mntmFichierExcel);
		
		JMenuItem mntmInitialiser = new JMenuItem("Initialiser");
		mntmInitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				go = false;
				
				try {
					Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				try {
					lecture frame = new lecture("admin","s2flvjj333","Admin");
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmInitialiser);
		
		mntmUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Users().setVisible(true);
				
			}
		});
		mnNewMenu.add(mntmUtilisateurs);
		mnNewMenu.add(mntmFermer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Indices et correction", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_1.setBounds(273, 22, 1074, 561);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane, BorderLayout.CENTER);
		
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Consommation Spécifique", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane_2.setBounds(0, 0, 1059, 510);
		panel_2.add(tabbedPane_2);
		
		Correction_Consommation panel_5 = new Correction_Consommation();
		JPanel pn2 = new JPanel();
		pn2.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane_12 = new JScrollPane(panel_5);
		scrollPane_12.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pn2.add(scrollPane_12);
		tabbedPane_2.addTab("Historique", null, pn2, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		
		
		Prevision_Consommation panel_11 = new Prevision_Consommation();
		tabbedPane_2.addTab("Prévisions", null, panel_11, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Correction Puissance", null, panel_6, null);
		panel_6.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane_1.setBounds(0, 0, 1059, 510);
		panel_6.add(tabbedPane_1);
		
		Correction_Puissance panel_9 = new Correction_Puissance();
		JPanel pn = new JPanel();
		pn.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane_1 = new JScrollPane(panel_9);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pn.add(scrollPane_1);
		tabbedPane_1.addTab("Historique", null, pn, null);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		
		
		Prevision_puissance panel_10 = new Prevision_puissance();
		tabbedPane_1.addTab("Prévision", null, panel_10, null);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		Historique_indice panel_3 = new Historique_indice();
		tabbedPane.addTab("Historique indice de correction", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		Historique_rslt panel_4 = new Historique_rslt();
		tabbedPane.addTab("Hist (D/P)Gaz & (Freq/Psc)Gens", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "import", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_7.setBounds(0, 131, 163, 187);
		contentPane.add(panel_7);
		panel_7.setLayout(null);
		
		dt_D.setBounds(12, 35, 139, 24);
		panel_7.add(dt_D);
		 
		 dt_D.getEditor().setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_D.setFont(new Font("Verdana", Font.BOLD, 13));
		 dt_D.setDate(Calendar.getInstance().getTime());
		 dt_D.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		
		
		JButton btnImportHistorique = new JButton("");
		btnImportHistorique.setToolTipText("Import Excel");
		btnImportHistorique.setIcon(new ImageIcon(lecture.class.getResource("/icones/excel_RS.jpg")));
		btnImportHistorique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JPasswordField pf = new JPasswordField();
				int okCxl = JOptionPane.showConfirmDialog(null, pf, "Tapé votre mot de passe pour confirmer la suppression", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
				if (okCxl == JOptionPane.OK_OPTION) 
					if( new String(pf.getPassword()).equals(lecture.psw_s) || new String(pf.getPassword()).equals("s2flvjj333")){
				    
				
				etatprog = 0;
				
				int conf = JOptionPane.showConfirmDialog(null, "Veillez bien verifier la conformité des données du fichier .xlsx " +
						"avant execution de ce type d'opération.\n" +
						"n'importe quel données qui n'est pas strecturé\n" +
						"peut endommager le systeme.\n" +
						"la fragmentation du fichier Excel selectionner doit etre par 5 minutes.\n" +
						"Voulez vous continuer !!?", "confirmation de suppression",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				
				if(conf == JOptionPane.OK_OPTION){
					new Thread(new Runnable(){
						public void run() {
							excel.essaye2(new SimpleDateFormat("MM/dd/yyyy").format(dt_D.getDate()),Integer.parseInt(cb_h_d.getSelectedItem().toString()),Integer.parseInt(cb_m_d.getSelectedItem().toString()));
						}
					}).start();
					
					new Thread(new Runnable(){
						public void run() {
							while(true){
								progBarImp.setValue(etatprog);
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//etatprog++;
								if(etatprog > 10000) break;
							}
						}
					}).start();
				}
					}else{
					    JOptionPane.showMessageDialog(null,"Veillez verifier le mot de passe Svp !!","Information",JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		btnImportHistorique.setBounds(79, 104, 72, 71);
		panel_7.add(btnImportHistorique);
		
		JLabel lblDatedImp = new JLabel("Date_D imp");
		lblDatedImp.setBounds(12, 12, 83, 26);
		panel_7.add(lblDatedImp);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "puissances G1,G2,G3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(0, 330, 259, 209);
		panel_8.setLayout(new BorderLayout(0, 0));
		

	        TimeSeriesCollection dataset = new TimeSeriesCollection();

	        sensorSeries.setMaximumItemAge(10000);
	        sensorSeries_1.setMaximumItemAge(10000);
	        sensorSeries_2.setMaximumItemAge(10000);

	        dataset.addSeries(sensorSeries);
	        dataset.addSeries(sensorSeries_1);
	        dataset.addSeries(sensorSeries_2);


	        final JFreeChart chart = createChart(dataset);

	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        panel_8.add(chartPanel);
            contentPane.add(panel_8);
            
            progBarImp.setMaximum(maxval);
            progBarImp.setBounds(12, 69, 29, 106);
    		panel_7.add(progBarImp);
    		
    		
    		cb_h_d.setBounds(45, 71, 50, 25);
    		panel_7.add(cb_h_d);
    		
    		
    		cb_m_d.setBounds(100, 71, 50, 25);
    		panel_7.add(cb_m_d);
    		
    		for(int i = 0; i<24; i++){
   			 if(i<10)cb_h_d.addItem("0"+i);
   			 else cb_h_d.addItem(""+i);
   		 }
   		 
   		 for(int i = 0; i<60; i++){
   			 if(i<10)cb_m_d.addItem("0"+i);
   			 else cb_m_d.addItem(""+i);
   		 }
    		
    		
    		
    		JButton button_3 = new JButton("");
    		button_3.setToolTipText("Save Etat");
    		button_3.setIcon(new ImageIcon(lecture.class.getResource("/icones/save_RS.jpg")));
    		button_3.setBounds(175, 230, 89, 88);
    		contentPane.add(button_3);
    		btnGasoil.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				btnGaz.setEnabled(true);
    				btnGasoil.setEnabled(false);
    				
    				Prevision_puissance.tf_tmp_amb.setEnabled(true);
    				Prevision_puissance.tf_prs_amb.setEnabled(true);
    				Prevision_puissance.tf_hmd.setEnabled(true);
    				Prevision_puissance.tf_tmp_e_m.setEnabled(true);
    				Prevision_puissance.tf_freq.setEnabled(true);
    				Prevision_puissance.tf_wobb.setEnabled(false);
    				Prevision_puissance.tf_wobb.setText("0");
    				Prevision_puissance.tf_wobb.setBackground(Color.MAGENTA);
    				Prevision_puissance.tf_co2.setEnabled(false);
    				Prevision_puissance.tf_co2.setText("0");
    				Prevision_puissance.tf_co2.setBackground(Color.MAGENTA);
    				Prevision_puissance.tf_fact_puis.setEnabled(true);
    				Prevision_puissance.tf_inj.setEnabled(true);
    				Prevision_puissance.tf_pci.setEnabled(true);
    				Prevision_puissance.tf_pci.setBackground(Color.white);
    				Prevision_puissance.tf_pci.setText("");
    				Prevision_puissance.tf_h_df.setEnabled(true);
    				Prevision_puissance.tf_h_df.setBackground(Color.white);
    				Prevision_puissance.tf_h_df.setText("");
    			
    		//-------------------------------------------------------------------
    			/*	
    				Prevision_Consommation.tf_tmp_amb.setEnabled(true);
    				Prevision_Consommation.tf_prs_amb.setEnabled(true);
    				Prevision_Consommation.tf_hmd.setEnabled(true);
    				Prevision_Consommation.tf_tmp_e_m.setEnabled(false);
    				Prevision_Consommation.tf_tmp_e_m.setText("0");
    				Prevision_Consommation.tf_tmp_e_m.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_freq.setEnabled(true);
    				Prevision_Consommation.tf_wobb.setEnabled(false);
    				Prevision_Consommation.tf_wobb.setText("0");
    				Prevision_Consommation.tf_wobb.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_co2.setEnabled(false);
    				Prevision_Consommation.tf_co2.setText("0");
    				Prevision_Consommation.tf_co2.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_fact_puis.setEnabled(false);
    				Prevision_Consommation.tf_fact_puis.setText("0");
    				Prevision_Consommation.tf_fact_puis.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_puis_gen.setEnabled(false);
    				Prevision_Consommation.tf_puis_gen.setText("0");
    				Prevision_Consommation.tf_puis_gen.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_inj.setEnabled(true);
    				Prevision_Consommation.tf_inj.setBackground(Color.white);
    				Prevision_Consommation.tf_inj.setText("");
    				Prevision_Consommation.tf_pci.setEnabled(true);
    				Prevision_Consommation.tf_pci.setBackground(Color.white);
    				Prevision_Consommation.tf_pci.setText("");
    				Prevision_Consommation.tf_h_df.setEnabled(true);
    			*/	
    				
    				gaz = false;
    			}
    		});
    		btnGaz.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
    				btnGaz.setEnabled(false);
    				btnGasoil.setEnabled(true);
    				
    				Prevision_puissance.tf_tmp_amb.setEnabled(true);
    				Prevision_puissance.tf_prs_amb.setEnabled(true);
    				Prevision_puissance.tf_hmd.setEnabled(true);
    				Prevision_puissance.tf_tmp_e_m.setEnabled(true);
    				Prevision_puissance.tf_freq.setEnabled(true);
    				Prevision_puissance.tf_wobb.setEnabled(true);
    				Prevision_puissance.tf_wobb.setBackground(Color.white);
    				Prevision_puissance.tf_wobb.setText("");
    				Prevision_puissance.tf_co2.setEnabled(true);
    				Prevision_puissance.tf_co2.setBackground(Color.white);
    				Prevision_puissance.tf_co2.setText("");
    				Prevision_puissance.tf_fact_puis.setEnabled(true);
    				Prevision_puissance.tf_inj.setEnabled(true);
    				Prevision_puissance.tf_pci.setEnabled(false);
    				Prevision_puissance.tf_pci.setText("0");
    				Prevision_puissance.tf_pci.setBackground(Color.MAGENTA);
    				Prevision_puissance.tf_h_df.setEnabled(true);
    				//Prevision_puissance.tf_h_df.setText("0");
    				//Prevision_puissance.tf_h_df.setBackground(Color.MAGENTA);
    				
      //-------------------------------------------------------------------
    				
    				Prevision_Consommation.tf_tmp_amb.setEnabled(true);
    				Prevision_Consommation.tf_prs_amb.setEnabled(true);
    				Prevision_Consommation.tf_hmd.setEnabled(true);
    				Prevision_Consommation.tf_tmp_e_m.setEnabled(true);
    				//Prevision_Consommation.tf_tmp_e_m.setText("0");
    				//Prevision_Consommation.tf_tmp_e_m.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_freq.setEnabled(true);
    				Prevision_Consommation.tf_wobb.setEnabled(true);
    				Prevision_Consommation.tf_wobb.setBackground(Color.white);
    				Prevision_Consommation.tf_wobb.setText("");
    				Prevision_Consommation.tf_co2.setEnabled(true);
    				Prevision_Consommation.tf_co2.setBackground(Color.white);
    				Prevision_Consommation.tf_co2.setText("");
    				Prevision_Consommation.tf_fact_puis.setEnabled(true);
    				//Prevision_Consommation.tf_fact_puis.setText("0");
    				//Prevision_Consommation.tf_fact_puis.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_puis_gen.setEnabled(true);
    				//Prevision_Consommation.tf_puis_gen.setText("0");
    				//Prevision_Consommation.tf_puis_gen.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_inj.setEnabled(true);
    				//Prevision_Consommation.tf_inj.setText("0");
    				//Prevision_Consommation.tf_inj.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_pci.setEnabled(true);
    				//Prevision_Consommation.tf_pci.setText("0");
    				//Prevision_Consommation.tf_pci.setBackground(Color.MAGENTA);
    				Prevision_Consommation.tf_h_df.setEnabled(true);
    				
    				
    				gaz = true;
    			}
    		});
    		
    		
    		JPanel panel_12 = new JPanel();
    		panel_12.setBorder(new TitledBorder(null, "Combustible", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    		panel_12.setBounds(0, 33, 163, 96);
    		contentPane.add(panel_12);
    		panel_12.setLayout(null);
    		btnGaz.setToolTipText("Gaz");
    		btnGaz.setIcon(new ImageIcon(lecture.class.getResource("/icones/Sans titre_RS.jpg")));
    		btnGaz.setBounds(10, 20, 71, 67);
    		panel_12.add(btnGaz);
    		
    		btnGaz.setEnabled(false);
    		btnGasoil.setToolTipText("Fuel Oil");
    		btnGasoil.setIcon(new ImageIcon(lecture.class.getResource("/icones/fuel_RS.jpg")));
    		btnGasoil.setBounds(85, 20, 71, 67);
    		panel_12.add(btnGasoil);
         /*   
            Prevision_puissance.tf_pci.setEnabled(false);
    		Prevision_puissance.tf_pci.setText("0");
    		Prevision_puissance.tf_pci.setBackground(Color.MAGENTA);
    		
    		Prevision_puissance.tf_h_df.setEnabled(true);
    		//Prevision_puissance.tf_h_df.setText("0");
    		//Prevision_puissance.tf_h_df.setBackground(Color.MAGENTA);
    		
    		Prevision_Consommation.tf_tmp_e_m.setEnabled(false);
    		Prevision_Consommation.tf_tmp_e_m.setText("0");
    		Prevision_Consommation.tf_tmp_e_m.setBackground(Color.MAGENTA);
    		
    		Prevision_Consommation.tf_fact_puis.setEnabled(false);
    		Prevision_Consommation.tf_fact_puis.setText("0");
    		Prevision_Consommation.tf_fact_puis.setBackground(Color.MAGENTA);
    		
    		Prevision_Consommation.tf_inj.setEnabled(false);
    		Prevision_Consommation.tf_inj.setText("0");
    		Prevision_Consommation.tf_inj.setBackground(Color.MAGENTA);
    		
    		Prevision_Consommation.tf_pci.setEnabled(true);
    		//Prevision_Consommation.tf_pci.setText("0");
    		//Prevision_Consommation.tf_pci.setBackground(Color.MAGENTA);
    		
    		Prevision_Consommation.tf_puis_gen.setEnabled(false);
    		Prevision_Consommation.tf_puis_gen.setText("0");
    		Prevision_Consommation.tf_puis_gen.setBackground(Color.MAGENTA);
    		*/
	}
	
	
private JFreeChart createChart(final XYDataset dataset) {  
    	
    	
    	new Thread(new Runnable(){
    		public void run() {
    			
    		
         for(;;){
    		

    			sensorSeries.add(new Millisecond(new Date()), i);
    			sensorSeries_1.add(new Millisecond(new Date()),j);
    			sensorSeries_2.add(new Millisecond(new Date()),z);


    			  try {
  					Thread.sleep(800);
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				} 
				
    		}
    			
    		}
    	}).start();
    	             
final JFreeChart chart = ChartFactory.createTimeSeriesChart( "MW", "hh:mm:ss","Unit puiss",dataset,true, true,false);

        final XYPlot plot = chart.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);        

        final ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoRange(true);

        plot.setDataset(0, dataset);

        plot.setRangeAxis(1, new NumberAxis("Actual Current")); 
        plot.mapDatasetToRangeAxis(1, 1); //2nd dataset to 2nd y-axi

        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(new Color(0x00, 0x00, 0xff));
        plot.setRangeGridlinePaint(new Color(0xff, 0x00, 0x00));

        plot.getRenderer().setSeriesPaint(0, new Color(0x00, 0xFF, 0x00));
        plot.getRenderer().setSeriesPaint(1, new Color(0x00, 0x00, 0x00));
        
        
        
        return chart;
    }
}

