import java.awt.Color;
import java.awt.Font;
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


public class Historique_rslt extends JPanel {
	
	
	
	
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
		 
		 
		 JTable tab_dem;
		 DefaultTableModel def_dem;
		 Object [][] d_tab_dem= new Object[15][10];
		 String[] t_tab_dem = {"Frag Temporel","Débit Gaz","Pression Gaz","Freq Génerateur","Puissance Génerateur"};
	
		 
		 
		 TimeSeries pop = new TimeSeries("");
		 TimeSeries pop1 = new TimeSeries("");
		 TimeSeries pop2 = new TimeSeries("");
		 TimeSeries pop3 = new TimeSeries("");
		 TimeSeriesCollection dataset_t = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_te = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_p = new TimeSeriesCollection();
		 TimeSeriesCollection dataset_h = new TimeSeriesCollection();
		 
		 JFreeChart chart = ChartFactory.createTimeSeriesChart("Débit Gaz","","", dataset_t,true,true,false);		 
		 JFreeChart chart1 = ChartFactory.createTimeSeriesChart("Pression Gaz","","", dataset_te,true,true,false);	
		 JFreeChart chart2 = ChartFactory.createTimeSeriesChart("Freq Génerateur","","",dataset_p,true,true,false);
		 JFreeChart chart3 = ChartFactory.createTimeSeriesChart("Puissance Génerateur","","",dataset_h,true,true,false);
		 
public Historique_rslt() {

		 setLayout(null);
		 
		 
		 
		 
		 

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
		 panel.setBounds(158, 21, 156, 34);
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
		 lblAu.setBounds(364, 26, 28, 14);
		 panel_1.add(lblAu);
		 
		 dt_F.setBounds(386, 20, 126, 24);
		 panel_1.add(dt_F);
		 
		 dt_F.getEditor().setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_F.setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_F.setDate(Calendar.getInstance().getTime());
		 dt_F.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		 
		 JPanel panel_2 = new JPanel();
		 panel_2.setBounds(522, 21, 156, 36);
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
		 cb_gen.setBounds(707, 16, 109, 24);
		 panel_1.add(cb_gen);
		 
		 JSeparator separator_1 = new JSeparator();
		 separator_1.setOrientation(SwingConstants.VERTICAL);
		 separator_1.setBounds(694, 11, 21, 77);
		 panel_1.add(separator_1);
		 
		 JButton btnChercher = new JButton("Chercher");
		 btnChercher.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 		if(dt_D.getDate().after(dt_F.getDate())){
			    	  JOptionPane.showMessageDialog(null,"Veillez verifier la date début et la date fin","Attention",JOptionPane.WARNING_MESSAGE);
		 		}else{
		 			
		 			//if(dt_D.getDate().compareTo(dt_F.getDate())==0){
		 				
		 			 if(dt_D.getDate().compareTo(dt_F.getDate())==0 && Integer.parseInt(""+cb_h_d.getSelectedItem())>Integer.parseInt(""+cb_h_f.getSelectedItem())){
					    JOptionPane.showMessageDialog(null,"Veillez verifier l'heure début et l'heure fin","Attention",JOptionPane.WARNING_MESSAGE);

		 				}else{
		 	
        		def_dem = new DefaultTableModel();
        		def_dem.setColumnIdentifiers(t_tab_dem);
        		
        		dataset_h .removeAllSeries();
        		dataset_p .removeAllSeries();
        		dataset_t .removeAllSeries();
        		dataset_te.removeAllSeries();
        		
        		  pop = new TimeSeries("");
        		  pop1 = new TimeSeries("");
        		  pop2 = new TimeSeries("");
        		  pop3 = new TimeSeries("");
        		
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
     			      		
     			      		while(d1_lg <= d2.getTime()){
     			      		 
     			      		 
  			 		    if(cb_gen.getSelectedIndex()==0){
			 		          req = "select avg(DBT_GAZ), avg(P_GAZ), avg(FREQ), avg(PUIS) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G1' ";
 			          	}else if(cb_gen.getSelectedIndex()==1){
			 		          req = "select avg(DBT_GAZ), avg(P_GAZ), avg(FREQ), avg(PUIS) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G2' ";
 			          	}else if(cb_gen.getSelectedIndex()==2){
			 		          req = "select avg(DBT_GAZ), avg(P_GAZ), avg(FREQ), avg(PUIS) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G3' ";
 			          	}else {
			 		          req = "select avg(DBT_GAZ), avg(P_GAZ), avg(FREQ), avg(PUIS) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')  ";
 			          	}
     			 //---------------------------------------
  			 		    
  			 		    System.out.println("to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')");
  			 		    
  			 		ResultSet rst = statement.executeQuery(req);
		 		        
		 		   double DBT_GAZ = 0;
		 		   double P_GAZ = 0;
		 		   double FREQ = 0;
		 		   double PUIS = 0;
		 		         
		 		         while(rst.next()){
		 		        	DBT_GAZ = rst.getDouble(1);
		 		        	P_GAZ = rst.getDouble(2);
		 		        	FREQ = rst.getDouble(3);
		 		        	PUIS = rst.getDouble(4);

		     			            def_dem.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),DBT_GAZ,P_GAZ,FREQ,PUIS});
		     			            pop.add(new Millisecond(new Date(d1_lg)), DBT_GAZ);
		     			            pop1.add(new Millisecond(new Date(d1_lg)), P_GAZ);
		     			            pop2.add(new Millisecond(new Date(d1_lg)), FREQ);
		     			            pop3.add(new Millisecond(new Date(d1_lg)), PUIS);
		     			          
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
     			      		
     			      	}
     			      	
			          	 con.close();
				      
				          
					      } catch (ClassNotFoundException e1) {
					    	  JOptionPane.showMessageDialog(null,"Erreur de connexion a la bdd","Attention",JOptionPane.ERROR_MESSAGE);
					      } catch (SQLException sqle) {
					    	  JOptionPane.showMessageDialog(null,"Erreur sur la requette","Attention",JOptionPane.ERROR_MESSAGE);
					      }
		 				}
		 				
		 		
		 			
		 		
		 		}
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnChercher.setBounds(827, 11, 119, 77);
		 panel_1.add(btnChercher);
		 
		 JLabel lblFragment = new JLabel("Fragment");
		 lblFragment.setBounds(707, 47, 59, 24);
		 panel_1.add(lblFragment);
		 cb_frag.setBounds(707, 68, 109, 20);
		 
		 for(int i = 5; i < 180; i = i + 5){
			 cb_frag.addItem(i+" Minutes");
		 }
		 
		 panel_1.add(cb_frag);
		 
		 JPanel panel_4 = new JPanel();
		 panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historique indices de correction par fragments temporel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_4.setBounds(10, 369, 952, 120);
		 
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
		 

		
		 ChartPanel panel_5 = new ChartPanel(chart);
		 panel_5.setBorder(new TitledBorder(null, "Débit Gaz / Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_5.setBounds(733, 135, 235, 223);
		 add(panel_5);
		 panel_5.setLayout(new BorderLayout(0, 0));
		 
		 	
		 ChartPanel panel_6 = new ChartPanel(chart1);
		 panel_6.setBorder(new TitledBorder(null, "Pression Gaz/ Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_6.setBounds(492, 135, 235, 223);
		 add(panel_6);
		 panel_6.setLayout(new BorderLayout(0, 0));
		 
			
		 ChartPanel panel_7 = new ChartPanel(chart2);
		 panel_7.setBorder(new TitledBorder(null, "Fréquence G / Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_7.setBounds(250, 135, 235, 223);
		 add(panel_7);
		 panel_7.setLayout(new BorderLayout(0, 0));
		 
		 
		 
		 
		 ChartPanel panel_8 = new ChartPanel(chart3);
		 panel_8.setBorder(new TitledBorder(null, "Puissance G / Fragment Temporel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_8.setBounds(10, 135, 235, 223);
		 add(panel_8);
		 panel_8.setLayout(new BorderLayout(0, 0));
	}
}
