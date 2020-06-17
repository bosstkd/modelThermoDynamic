import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Robot;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;


public class Historique_indice extends JPanel {
	
	
	Robot rbt ;
	private JPanel p_table2;
	JXDatePicker dt_D = new JXDatePicker();
	JXDatePicker dt_F = new JXDatePicker();
	JComboBox cb_h_d = new JComboBox(); 
	 JComboBox cb_m_d = new JComboBox();
	 JComboBox cb_s_d = new JComboBox();
	 JComboBox cb_m_f = new JComboBox();
	 JComboBox cb_s_f = new JComboBox(); 
	 JComboBox cb_h_f = new JComboBox();
	 JComboBox cb_gen = new JComboBox();
	 JComboBox cb_frag = new JComboBox();
	 JCheckBox ch_base = new JCheckBox("Base_Load");
		 
		 JTable tab_dem;
		 DefaultTableModel def_dem;
		 Object [][] d_tab_dem= new Object[15][10];
		 String[] t_tab_dem = {"Frag Temporel","T° Amb","T° Eau M","Pression","Humidité","Débit Gaz","Puissance","Pci","Unité"};
	
		 
		 
		 TimeSeries pop = new TimeSeries("");
		 TimeSeries pop1 = new TimeSeries("");
		 TimeSeries pop2 = new TimeSeries("");
		 TimeSeries pop3 = new TimeSeries("");
		 TimeSeries pop4 = new TimeSeries("");
		 TimeSeries pop5 = new TimeSeries("");
		 TimeSeries pop6 = new TimeSeries("");

		 TimeSeriesCollection dataset_t = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_te = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_p = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_h = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_d = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_puiss = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_pci = new TimeSeriesCollection();
		 
		 JFreeChart chart = ChartFactory.createTimeSeriesChart("Humidité","","",dataset_h,true,true,false);		 
		 JFreeChart chart1 = ChartFactory.createTimeSeriesChart("Pression","","",dataset_p,true,true,false);	
		 JFreeChart chart2 = ChartFactory.createTimeSeriesChart("T° Eau Mer","","",dataset_te,true,true,false);
		 JFreeChart chart3 = ChartFactory.createTimeSeriesChart("T° Amb ","","",dataset_t,true,true,false);
		 JFreeChart chart4 = ChartFactory.createTimeSeriesChart("Débit Gaz ","","",dataset_d,true,true,false);
		 JFreeChart chart5 = ChartFactory.createTimeSeriesChart("Puissance","","",dataset_puiss,true,true,false);
		 JFreeChart chart6 = ChartFactory.createTimeSeriesChart("PCI","","",dataset_pci,true,true,false);

		 private JTextField tf_puiss_1;
		 private JTextField tf_puiss_2;
		 
public Historique_indice() {

		 setLayout(null);
		 
		 try {
			rbt = new Robot();
		} catch (AWTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 

		 for(int i = 0; i<24; i++){
			 if(i<10)cb_h_d.addItem("0"+i);
			 else cb_h_d.addItem(""+i);
		 }
		 
		 for(int i = 0; i<60; i++){
			 if(i<10)cb_m_d.addItem("0"+i);
			 else cb_m_d.addItem(""+i);
		 }
		 
		 JPanel panel_1 = new JPanel();
		 panel_1.setBorder(new TitledBorder(null, "Precision des Facteurs de recherche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_1.setBounds(10, 11, 952, 99);
		 add(panel_1);
		 panel_1.setLayout(null);
		 
		 JLabel lblDu = new JLabel("Du");
		 lblDu.setBounds(10, 21, 28, 24);
		 panel_1.add(lblDu);
		 
		 dt_D.setBounds(29, 21, 126, 24);
		 panel_1.add(dt_D);
		 
		 dt_D.getEditor().setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_D.setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_D.setDate(Calendar.getInstance().getTime());
		 dt_D.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		 
		 JPanel panel = new JPanel();
		 panel.setBounds(158, 21, 156, 30);
		 panel_1.add(panel);
		 panel.setLayout(null);
		 
	
		 cb_h_d.setBounds(0, 0, 42, 26);
		 panel.add(cb_h_d);
		 
		 
		
		 cb_m_d.setBounds(52, 0, 42, 26);
		 panel.add(cb_m_d);
		 
		 
		 cb_s_d.setBounds(104, 0, 46, 26);
		 panel.add(cb_s_d);
		 
		 JLabel label = new JLabel(":");
		 label.setBounds(44, 6, 19, 14);
		 panel.add(label);
		 
		 JLabel label_1 = new JLabel(":");
		 label_1.setBounds(96, 6, 14, 14);
		 panel.add(label_1);
		 
		 JLabel lblAu = new JLabel("Au");
		 lblAu.setBounds(10, 62, 28, 14);
		 panel_1.add(lblAu);
		 
		 dt_F.setBounds(29, 56, 126, 24);
		 panel_1.add(dt_F);
		 
		 dt_F.getEditor().setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_F.setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_F.setDate(Calendar.getInstance().getTime());
		 dt_F.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		 
		 JPanel panel_2 = new JPanel();
		 panel_2.setBounds(158, 55, 156, 30);
		 panel_1.add(panel_2);
		 panel_2.setLayout(null);
		 
		 cb_m_f.setBounds(54, 0, 42, 26);
		 panel_2.add(cb_m_f);
		 
		 
		 cb_s_f.setBounds(110, 0, 42, 26);
		 panel_2.add(cb_s_f);
		 
		 JLabel label_2 = new JLabel(":");
		 label_2.setBounds(47, 6, 15, 14);
		 panel_2.add(label_2);
		 
		 JLabel label_3 = new JLabel(":");
		 label_3.setBounds(100, 6, 15, 14);
		 panel_2.add(label_3);
		 
		
		 cb_h_f.setBounds(2, 0, 42, 26);
		 panel_2.add(cb_h_f);
		 
		 for(int i = 0; i<24; i++){
			 if(i<10)cb_h_f.addItem("0"+i);
			 else cb_h_f.addItem(""+i);
		 }
		 
		 for(int i = 0; i<60; i++){
			 if(i<10)cb_m_f.addItem("0"+i);
			 else cb_m_f.addItem(""+i);
		 }
		 
		 for(int i = 0; i<60; i++){
			 if(i<10)cb_s_f.addItem("0"+i);
			 else cb_s_f.addItem(""+i);
		 }
		 
		 JSeparator separator = new JSeparator();
		 separator.setOrientation(SwingConstants.VERTICAL);
		 separator.setBounds(333, 11, 21, 77);
		 panel_1.add(separator);
		 
		
		 cb_gen.setModel(new DefaultComboBoxModel(new String[] {"G\u00E9n\u00E9rateur 1", "G\u00E9n\u00E9rateur 2", "G\u00E9n\u00E9rateur 3", "Global"}));
		 cb_gen.setBounds(343, 16, 109, 24);
		 panel_1.add(cb_gen);
		 
		 JSeparator separator_1 = new JSeparator();
		 separator_1.setOrientation(SwingConstants.VERTICAL);
		 separator_1.setBounds(462, 8, 21, 77);
		 panel_1.add(separator_1);
		 
		 JButton btnChercher = new JButton("");
		 btnChercher.setIcon(new ImageIcon(Historique_indice.class.getResource("/icones/rech_RS.png")));
		 btnChercher.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 		if(dt_D.getDate().after(dt_F.getDate())){
			    	  JOptionPane.showMessageDialog(null,"Veillez verifier la date début et la date fin","Attention",JOptionPane.WARNING_MESSAGE);
		 		}else{
		 			
		 			//if(dt_D.getDate().compareTo(dt_F.getDate())==0){
		 				
		 			 if(dt_D.getDate().compareTo(dt_F.getDate())==0 && Integer.parseInt(""+cb_h_d.getSelectedItem())>Integer.parseInt(""+cb_h_f.getSelectedItem())){
		 				 	JOptionPane.showMessageDialog(null,"Veillez verifier l'heure début et l'heure fin","Attention",JOptionPane.WARNING_MESSAGE);
		 				}else
		 				if(tf_puiss_1.getText().equals("") || tf_puiss_2.getText().equals("")){
		 					 JOptionPane.showMessageDialog(null,"Veillez Remplir l'intervale de puissance désirer svp !","Attention",JOptionPane.WARNING_MESSAGE);
		 				}else{
		 					
		 	setCursor(new Cursor(Cursor.WAIT_CURSOR));
        		def_dem = new DefaultTableModel();
        		def_dem.setColumnIdentifiers(t_tab_dem);
        		
        		dataset_h .removeAllSeries();
        		dataset_p .removeAllSeries();
        		dataset_t .removeAllSeries();
        		dataset_te.removeAllSeries();
        		dataset_d.removeAllSeries();
        		dataset_puiss.removeAllSeries();
        		dataset_pci.removeAllSeries();
        		
        		  pop = new TimeSeries("");
        		  pop1 = new TimeSeries("");
        		  pop2 = new TimeSeries("");
        		  pop3 = new TimeSeries("");
        		  pop4 = new TimeSeries("");
        		  pop5 = new TimeSeries("");
        		  pop6 = new TimeSeries("");
        		
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
			          	
			          	
			          	String heure_d = new SimpleDateFormat("yyyy-MM-dd").format(dt_D.getDate())+" "+cb_h_d.getSelectedItem()+":"+cb_m_d.getSelectedItem()+":"+cb_s_d.getSelectedItem();
			          	
			          	String heure_f = new SimpleDateFormat("yyyy-MM-dd").format(dt_F.getDate())+" "+cb_h_f.getSelectedItem()+":"+cb_m_f.getSelectedItem()+":"+cb_s_f.getSelectedItem();
			          	String req = "";
			          	
     			      	
     			      	//**************Tab 1/2 Heure************************************
     			       Date d1 = null;
     				   Date d2 = null;
     				   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     			      	try {
     						d1 = format.parse(heure_d);
     						d2 = format.parse(heure_f);
     					} catch (java.text.ParseException ez) {
     						// TODO Auto-generated catch block
     						ez.printStackTrace();
     					}
     					long diff = d2.getTime() - d1.getTime();
     			      	System.out.println(diff);
     			      	int index = (cb_frag.getSelectedIndex()+1) * 300000;
     			      	if(diff >=index){
     			      		long boucle = diff/index;
     			      		long d1_lg = d1.getTime();
     			      		long dt_lg_save = d1_lg;
     			      		d1_lg = d1_lg + index;
     			      		System.out.println(boucle+" "+d1_lg+" "+dt_lg_save);
     			      		
     			      		String bload = " and BASE_L = 1 ";
     			      		
     			      		while(d1_lg <= d2.getTime()){
     			      		 
     			      		 if(ch_base.isSelected()) bload = " and BASE_L = 1 ";
     			      		 else {
     			      			bload = " ";
     			      		 }
  			 		    if(cb_gen.getSelectedIndex()==0){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER),avg(DBT_GAZ),avg(PUIS),avg(PCI) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G1' and PUIS <= "+tf_puiss_2.getText()+" and PUIS >= "+tf_puiss_1.getText()+bload ;
 			          	}else if(cb_gen.getSelectedIndex()==1){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER),avg(DBT_GAZ),avg(PUIS),avg(PCI) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G2' and PUIS <= "+tf_puiss_2.getText()+" and PUIS >= "+tf_puiss_1.getText()+bload;
 			          	}else if(cb_gen.getSelectedIndex()==2){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER),avg(DBT_GAZ),avg(PUIS),avg(PCI) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G3' and PUIS <= "+tf_puiss_2.getText()+" and PUIS >= "+tf_puiss_1.getText()+bload;
 			          	}else {
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER),avg(DBT_GAZ),avg(PUIS),avg(PCI) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')  ";
 			          	}
     			 //---------------------------------------
  			 		    
  			 		    System.out.println("to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')");
  			 		    
  			 		ResultSet rst = statement.executeQuery(req);
		 		        
		 		   double tmp = 0;
		 		   double tmp_em = 0;
		 		   double press = 0;
		 		   double humid = 0;
		 		   double debit_g = 0;
		 		   double puiss = 0;
		 		   double pci = 0;
		 		         while(rst.next()){
		 		        	tmp = rst.getDouble(1);
		 		        	humid = rst.getDouble(2);
		 		        	press = rst.getDouble(3);
		 		        	tmp_em = rst.getDouble(4);
		 		        	debit_g = rst.getDouble(5);
		 		        	puiss = rst.getDouble(6);
		 		        	pci = rst.getDouble(7);
		 		        	
		 		        	if(tmp != 0 && humid != 0 && press != 0 && tmp_em != 0){
		 		        		def_dem.addRow(new Object[]{new SimpleDateFormat("dd/MM/yy , HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),tmp,tmp_em,press,humid,debit_g,puiss,pci, cb_gen.getSelectedItem()});
		     			            pop.add(new Millisecond(new Date(d1_lg)), tmp);
		     			            pop1.add(new Millisecond(new Date(d1_lg)), tmp_em);
		     			            pop2.add(new Millisecond(new Date(d1_lg)), press);
		     			            pop3.add(new Millisecond(new Date(d1_lg)), humid);
		     			            pop4.add(new Millisecond(new Date(d1_lg)), debit_g);
		     			            pop5.add(new Millisecond(new Date(d1_lg)), puiss);
		     			            pop6.add(new Millisecond(new Date(d1_lg)), pci);
		 		        	}
		 		        			
		     			          
		 		         }
		 		        tab_dem.setModel(def_dem);
		 		       
                 //---------------------------------------  
		 		                dt_lg_save = d1_lg;
     			      			d1_lg = d1_lg + index;
     			      			
     			      		}
     			      		
     			      	 dataset_t.addSeries(pop);
     			      	 dataset_te.addSeries(pop1);
     			      	 dataset_p.addSeries(pop2);
     			      	 dataset_h.addSeries(pop3);
     			      	 dataset_d.addSeries(pop4);
     			      	 dataset_puiss.addSeries(pop5);
     			      	 dataset_pci.addSeries(pop6);
     			      		
     			      	}
     			      	
			          	 con.close();
			 		 	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				          
					      } catch (ClassNotFoundException e1) {
					    	  JOptionPane.showMessageDialog(null,"Erreur de connexion a la bdd","Attention",JOptionPane.ERROR_MESSAGE);
					      } catch (SQLException sqle) {
					    	  JOptionPane.showMessageDialog(null,"Erreur sur la requette","Attention",JOptionPane.ERROR_MESSAGE);
					      }
		 				}
		 		}
		 		
		 	}
		 });
		 btnChercher.setBounds(845, 11, 85, 77);
		 panel_1.add(btnChercher);
		 
		 JLabel lblFragment = new JLabel("Fragment");
		 lblFragment.setBounds(343, 47, 59, 24);
		 panel_1.add(lblFragment);
		 cb_frag.setBounds(343, 68, 109, 20);
		 
		 for(int i = 5; i < 180; i = i + 5){
			 cb_frag.addItem(i+" Minutes");
		 }
		 
		 panel_1.add(cb_frag);
		 
		 JLabel lblPuissance = new JLabel("1- Puissance :  de");
		 lblPuissance.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblPuissance.setBounds(474, 14, 119, 24);
		 panel_1.add(lblPuissance);
		 
		 JLabel lblPuissance_1 = new JLabel("2- Puissance : jusqu'a");
		 lblPuissance_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblPuissance_1.setBounds(474, 59, 144, 26);
		 panel_1.add(lblPuissance_1);
		 
		 tf_puiss_1 = new JTextField();
		 tf_puiss_1.setText("0");
		 tf_puiss_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		 tf_puiss_1.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_puiss_1.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_puiss_1.getText().toString());
					}catch(Exception exp){
						dtj = tf_puiss_1.getText();
						tf_puiss_1.setText(dtj.substring(0,tf_puiss_1.getText().toString().length()-1));
					}
				}
		 		
		 	}
		 });
		 tf_puiss_1.setBounds(615, 17, 98, 20);
		 panel_1.add(tf_puiss_1);
		 tf_puiss_1.setColumns(10);
		 
		 tf_puiss_2 = new JTextField();
		 tf_puiss_2.setText("500");
		 tf_puiss_2.setFont(new Font("Verdana", Font.PLAIN, 11));
		 tf_puiss_2.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {

		 		String dtj = "";
				if(tf_puiss_2.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_puiss_2.getText().toString());
					}catch(Exception exp){
						dtj = tf_puiss_2.getText();
						tf_puiss_2.setText(dtj.substring(0,tf_puiss_2.getText().toString().length()-1));
					}
				}
		 		
		 	}
		 });
		 tf_puiss_2.setBounds(615, 65, 98, 20);
		 panel_1.add(tf_puiss_2);
		 tf_puiss_2.setColumns(10);
		 
		 JLabel lblEntre = new JLabel("Entre");
		 lblEntre.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblEntre.setBounds(641, 43, 46, 14);
		 panel_1.add(lblEntre);
		 ch_base.setFont(new Font("Verdana", Font.PLAIN, 10));
		 
		 ch_base.setBounds(731, 38, 97, 23);
		 panel_1.add(ch_base);
		 
		 JPanel panel_4 = new JPanel();
		 panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historique indices de correction par fragments temporel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_4.setBounds(10, 389, 952, 120);
		 
		 tab_dem = new JTable(d_tab_dem ,t_tab_dem );	
			
			tab_dem .setRowHeight(25);
			JScrollPane scrollPane_3 = new JScrollPane(tab_dem );
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(scrollPane_3);
		 
		 add(panel_4);

		 for(int i = 0; i<60; i++){
			 if(i<10)cb_s_d.addItem("0"+i);
			 else cb_s_d.addItem(""+i);
		 }
		 
		 
		 
		 JPanel panel_42 = new JPanel();
		 panel_42.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historique des valeurs mesurer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_42.setBounds(10, 117, 952, 261);
		
			//JScrollPane scrollPane_3 = new JScrollPane(tab_dem );
			
			p_table2 = new JPanel();
			p_table2.setPreferredSize(new Dimension(1800, 250));
			panel_42.setLayout(new BorderLayout(0, 0));
			panel_42.add(new JScrollPane(p_table2), BorderLayout.CENTER);
			p_table2.setLayout(null);
			
			
			
			
			ChartPanel panel_8 = new ChartPanel(chart3);
			panel_8.setBounds(10, 11, 235, 223);
			p_table2.add(panel_8);
			panel_8.setBorder(new TitledBorder(null, "Graph T° Amb / Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_8.setLayout(new BorderLayout(0, 0));
			
			
			ChartPanel panel_7 = new ChartPanel(chart2);
			panel_7.setBounds(255, 11, 235, 223);
			p_table2.add(panel_7);
			panel_7.setBorder(new TitledBorder(null, "Graph T° Eau / Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_7.setLayout(new BorderLayout(0, 0));
			
				
			ChartPanel panel_6 = new ChartPanel(chart1);
			panel_6.setBounds(500, 11, 235, 223);
			p_table2.add(panel_6);
			panel_6.setBorder(new TitledBorder(null, "Graph Pression / Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_6.setLayout(new BorderLayout(0, 0));
			

		
			ChartPanel panel_5 = new ChartPanel(chart);
			panel_5.setBounds(745, 11, 235, 223);
			p_table2.add(panel_5);
			panel_5.setBorder(new TitledBorder(null, "Graph Humidité / Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_5.setLayout(new BorderLayout(0, 0));
			
			ChartPanel panel_10 = new ChartPanel(chart4);
			panel_10.setBounds(990, 11, 235, 223);
			p_table2.add(panel_10);
			panel_10.setBorder(new TitledBorder(null, "Débit Gaz kg/s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_10.setLayout(new BorderLayout(0, 0));
			
			ChartPanel panel_11 = new ChartPanel(chart5);
			panel_11.setBounds(1235, 11, 235, 223);
			p_table2.add(panel_11);
			panel_11.setBorder(new TitledBorder(null, "Puissance MW", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_11.setLayout(new BorderLayout(0, 0));
			
			ChartPanel panel_12 = new ChartPanel(chart6);
			panel_12.setBounds(1480, 11, 235, 223);
			p_table2.add(panel_12);
			panel_12.setBorder(new TitledBorder(null, "PCI", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_12.setLayout(new BorderLayout(0, 0));


			//p_table2.add();

		add(panel_42);	
		 
		 
	}
}
