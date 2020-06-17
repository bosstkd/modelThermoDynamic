import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

import javax.swing.UIManager;


public class Correction_Puissance extends JPanel {
	
	 JFreeChart chart = null;
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
	
	 private JPanel p_table;
	 private JPanel p_table2;
	     JTable tab_rech;
		 DefaultTableModel def_tR;
		 Object [][] d_tab_rech= new Object[15][10];
		 String[] t_tab_Rech = {"Correction","Mesurer","Valeur"};
		 
		 JTable tab_dem;
		 DefaultTableModel def_dem;
		 Object [][] d_tab_dem= new Object[15][12];
		 String[] t_tab_dem = {"Frag Temporel","T° Amb","T° Eau M","Pression","Humidité","Fréquence","Wobb","Perte Altern","Inject Eau","T.P.H","Puis_Mu"};
		 String[] t_tab_dem2 = {"Frag Temporel","T° Amb","T° Eau M","Pression","Humidité","Fréquence","Inject Eau","PCI","T.P.H"};

		 
		 
		 JTable tab_ind;
		 DefaultTableModel def_ind;
		 Object [][] d_tab_ind= new Object[15][20];
		 String[] t_tab_ind = {"Frag Temporel","GENERATEUR","T_AMB","Humid","P_AMB","T_EAU_MER","DBT_GAZ","FREQ","PUIS","WOB_INDEX","INJ_EAU","PCI","COMP_GAZ_CO2","FACT_PUIS","Dégradation","PUIS_AUXI","DEBIT_FUEL"};
		 //String[] t_tab_ind2 = {"Frag Temporel","T° Amb","T° Eau M","Pression","Humidité","Fréquence","Inject Eau","PCI","T.P.H"};

		 
		 
		 TimeSeries pop = new TimeSeries("Correction");
	
		 TimeSeriesCollection dataset = new TimeSeriesCollection();
		 
public Correction_Puissance() {

		 setLayout(null);
		 
		 
		 setPreferredSize(new Dimension(974, 800));
		 
		 

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
		 
		 JButton btnChercher = new JButton("Corriger");
		 btnChercher.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 		if(dt_D.getDate().after(dt_F.getDate())){
			    	  JOptionPane.showMessageDialog(null,"Veillez verifier la date début et la date fin","Attention",JOptionPane.WARNING_MESSAGE);
		 		}else{
		 			
		 			//if(dt_D.getDate().compareTo(dt_F.getDate())==0){
		 				
		 			 if(dt_D.getDate().compareTo(dt_F.getDate())==0 && Integer.parseInt(""+cb_h_d.getSelectedItem())>Integer.parseInt(""+cb_h_f.getSelectedItem())){
					    JOptionPane.showMessageDialog(null,"Veillez verifier l'heure début et l'heure fin","Attention",JOptionPane.WARNING_MESSAGE);

		 				}else{
		 		def_tR = new DefaultTableModel();
        		def_tR.setColumnIdentifiers(t_tab_Rech);
        		def_dem = new DefaultTableModel();
        		def_ind = new DefaultTableModel();
        		def_ind.setColumnIdentifiers(t_tab_ind);
        		
        		
        		if(lecture.gaz) def_dem.setColumnIdentifiers(t_tab_dem); else def_dem.setColumnIdentifiers(t_tab_dem2);
        		dataset.removeAllSeries();
        		pop = new TimeSeries("Correction");
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
			          	if(cb_gen.getSelectedIndex()==0){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G1' ";
			          	}else if(cb_gen.getSelectedIndex()==1){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G2' ";
			          	}else if(cb_gen.getSelectedIndex()==2){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G3' ";
			          	}else {
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') ";
			          	}
			          	
		 		         ResultSet rst = statement.executeQuery(req);
		 		        
		 		        double tmp = 0;
     			        double tmp_em = 0;
     			        double press = 0;
     			        double humid = 0;
     			        double freq = 0;
     			        double wobb = 0;
     			        double Gco2 = 0;
     			        double rend_altern = 0;
     			        double fact_puis = 0;
     			        double inj_eau = 0;
     			        double pci = 0;
     			        double hdf = 0;
     			        double puis_auxi = 0;
     			        double debit_gaz = 0;
     			        double debit_fuel = 0;
     			       DecimalFormat df = new DecimalFormat ( ) ;
  	                   df.setMaximumFractionDigits (8) ;
		 		         
		 		         while(rst.next()){
		 		        	tmp = rst.getDouble(1);
		 		        	humid = rst.getDouble(2);
		 		        	press = rst.getDouble(3);
		 		        	tmp_em = rst.getDouble(4);
		 		        	freq = rst.getDouble(5);
		 		            wobb = rst.getDouble(6);
		 		            Gco2 = rst.getDouble(7);
		 		            rend_altern = rst.getDouble(8);
		 		            fact_puis = rst.getDouble(9);
		 		            inj_eau = rst.getDouble(10); 
		 		            pci = rst.getDouble(11);
		 		            hdf = rst.getDouble(12);
 		 		            puis_auxi = rst.getDouble(13);
 		 		           
		 		         }
		 		         
		 		         
		 		         if(tmp==0 && tmp_em == 0 && press == 0 && humid == 0 && freq == 0 && wobb == 0){
					    	  JOptionPane.showMessageDialog(null,"Aucune donnée sur cette période","Information",JOptionPane.INFORMATION_MESSAGE);
		 		         }else{
		 		         String lb_t_amb = "";
		 		         String lb_pres = "";
		 		         double lb_c46 = 0;
		 		         double lb_c35 = 0;
		 		         double lb_c15 = 0;
		 		         double lb_c_01 = 0;
		 		         String lb_h = "";
		 		         String lb_t_e_m = "";
		 		         String lb_tph = "";
		 		     
		 		       if(lecture.gaz){
		 		    	     //-----------Correction température-----------------  
				        	double cor_t = fonction.tmp_amb_p_g(tmp);
				        	double val_cor_tmp = 1/cor_t;
				        	def_tR.addRow(new Object[]{"T° Ambiante",df.format(cor_t),df.format(val_cor_tmp)});
					           //-----------Correction pression-----------------  
				            double cor_p = fonction.pression_p_g(press);
				            double val_cor_press = 1/cor_p;
					        def_tR.addRow(new Object[]{"Press Ambiante",df.format(cor_p),df.format(val_cor_press)});
					           //-----------Correction humidité-----------------  
					        double cor_h = fonction.humidite_p_g(humid, tmp);
					        double val_cor_humid = 1/cor_h;
				            def_tR.addRow(new Object[]{"Humidité",df.format(cor_h),df.format(val_cor_humid)});
					           //-----------Correction température eau de mer----------------  
				            double cor_t_em = fonction.tmp_em_p_g(tmp_em, tmp);
				            double val_cor_tem = 0 - cor_t_em;
				            def_tR.addRow(new Object[]{"Correction T°_E_M",df.format(cor_t_em),df.format(val_cor_tem)});
					           //-----------Correction frequence-----------------  
				            double cor_f = fonction.freq_p_g(freq, tmp);
				            double val_cor_freq = 1/cor_f;
				            def_tR.addRow(new Object[]{"Correction Freq",df.format(cor_f),df.format(val_cor_freq)});
					           //-----------Correction wobb-----------------  
				            double cor_wco2 = fonction.wobb_p_g(wobb, Gco2);
				            double val_cor_wco2 = fonction.wobb_p_g(52.401, 0.24)/cor_wco2;
				            def_tR.addRow(new Object[]{"Correction L W",df.format(cor_wco2),df.format(val_cor_wco2)});
				           //-----------Correction perte alternateur-----------------  
				           double corr_p_alt = fonction.perte_altern_p_g(fact_puis, rend_altern);
				           System.out.println("Correction perte Alternateur : "+corr_p_alt+" / Facteur de puissance "+ fact_puis);

				           double val_nom_p_alt = fonction.perte_altern_p_g(0.9, rend_altern);
				           System.out.println("Nominal perte Alternateur : "+val_nom_p_alt);
				           double val_cor_p_alt = val_nom_p_alt - corr_p_alt;
				           
				           def_tR.addRow(new Object[]{"Correction Prt altern",df.format(corr_p_alt),df.format(val_cor_p_alt)}); 
				           //-----------Correction injection d'eau-----------------  
				            double corr_inj_eau = /*fonction.inj_p_g(inj_eau)*/ 1;
				            double val_cor_inj = 1/corr_inj_eau;
				            def_tR.addRow(new Object[]{"C_injection eau",df.format(corr_inj_eau),df.format(val_cor_inj)}); 
				           //-----------Correction heure de flame-----------------  
			        	    double corr_h_d_f = fonction.hrf_p_g(hdf);
			        	    double val_cor_hdf =1+ (corr_h_d_f - 0.998 );
			        	    def_tR.addRow(new Object[]{"C_degradation",df.format(corr_h_d_f),df.format(val_cor_hdf)}); 
				          
				           
				           
			        double tphh2 = Math.abs(((rend_altern*1000)-val_cor_p_alt-val_cor_tem + puis_auxi)*(val_cor_tmp*val_cor_press*val_cor_humid*val_cor_wco2*val_cor_freq*val_cor_inj)*val_cor_hdf);
			          // ABS(((B39*1000)-E45-E27+E53)*(E14*E16*E22*E39*E33*E49)*E61)
				           
				           def_tR.addRow(new Object[]{"C_Globale","_",df.format(tphh2)});
				         
				      	tab_rech.setModel(def_tR);
		 		       }else{
		 		    	  //------------------hna te3 gasoil----------------------  
				        	
				        	//-----------Correction température ambiante-----------------
				 		        double cor_t = fonction.tmp_amb_p_fo(tmp);
				 		        double val_cor_tmp = 1/cor_t;
			                	def_tR.addRow(new Object[]{"T° Ambiante",df.format(cor_t),df.format(val_cor_tmp)});
			                //-----------Correction pression ambiante-----------------
		 			           double cor_p = fonction.pression_p_fo(press);
		 			           double val_cor_press = 1/cor_p;
		  			           def_tR.addRow(new Object[]{"Press Ambiante",df.format(cor_p),df.format(val_cor_press)});
		  			        //-----------Correction humidité-----------------   
		  			           double cor_h = fonction.humidite_p_fo(humid, tmp);
		  			           double val_cor_humid = 1/cor_h;
		  			           def_tR.addRow(new Object[]{"Humidité",df.format(cor_h),df.format(val_cor_humid)});
		  			        //-----------Correction température eau de mer -----------------         
		  			           double cor_t_em = fonction.tmp_em_p_fo(tmp_em, tmp);
		  			           double val_cor_tem = 0 - cor_t_em;
					           def_tR.addRow(new Object[]{"Correction T°_E_M",df.format(cor_t_em),df.format(val_cor_tem)});
					        //-----------Correction fréquence-----------------  
					            double cor_f = fonction.freq_p_fo(freq, tmp);
					            double val_cor_freq = 1/cor_f;
					            def_tR.addRow(new Object[]{"Correction Freq",df.format(cor_f),df.format(val_cor_freq)});
					        //-----------Correction injection d'eau-----------------  
					           double corr_inj_eau = fonction.inj_p_fo(inj_eau);
					           double val_cor_inj = 1/corr_inj_eau;
					           def_tR.addRow(new Object[]{"C_injection eau",df.format(corr_inj_eau),df.format(val_cor_inj)}); 
					         //-----------Correction perte alternateur-----------------  
					           double corr_perte_altern = fonction.perte_altern_p_g(fact_puis, rend_altern);
					           double nominal_corr_p_altrn= fonction.perte_altern_p_g(0.9, rend_altern);
					           double val_cor_p_alt = nominal_corr_p_altrn - corr_perte_altern;
					           def_tR.addRow(new Object[]{"C_perte alternateur",df.format(corr_perte_altern),df.format(val_cor_p_alt)}); 
					        //-----------Correction PCI-----------------------------
					           double corr_pci  = fonction.pci_p_fo(pci);
					           double val_cor_pci = 1/corr_pci;
					           System.out.println("pci : "+pci+"   Correction : "+corr_pci);
					           def_tR.addRow(new Object[]{"Correction PCI",df.format(corr_pci),df.format(val_cor_pci)}); 
					           //-----------Correction heure de flame-----------------  
				        	    double corr_h_d_f = fonction.hrf_p_g(hdf);
				        	    double val_cor_hdf =1+ (corr_h_d_f - 0.998);
				        	    def_tR.addRow(new Object[]{"C_degradation",df.format(corr_h_d_f),df.format(val_cor_hdf)}); 
						        //-----------Correction Global-----------------        
					           double tphh2 = Math.abs(((rend_altern*1000)-val_cor_p_alt-val_cor_tem + puis_auxi)*(val_cor_tmp*val_cor_press*val_cor_humid*val_cor_pci*val_cor_freq*val_cor_inj)*val_cor_hdf);
						       def_tR.addRow(new Object[]{"C_Globale","_",df.format(tphh2)});
				 		         
		  			      		tab_rech.setModel(def_tR);
					 	     //------------------fin hna te3 gasoil----------------------   
		 		       }
		 		     
     			      	
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
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G1' ";
 			          	}else if(cb_gen.getSelectedIndex()==1){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G2' ";
 			          	}else if(cb_gen.getSelectedIndex()==2){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G3' ";
 			          	}else {
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI),avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')  ";
 			          	}
     			 //---------------------------------------
  			 		    
  			 		  //  System.out.println("to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')");
  			 		    
  			 		 rst = statement.executeQuery(req);
		 		        
		 		     tmp = 0;
  			         tmp_em = 0;
  			         press = 0;
  			         humid = 0;
		 		         
		 		         while(rst.next()){
		 		        	tmp = rst.getDouble(1);
		 		        	humid = rst.getDouble(2);
		 		        	press = rst.getDouble(3);
		 		        	tmp_em = rst.getDouble(4);
		 		        	freq = rst.getDouble(5);
		 		        	wobb = rst.getDouble(6);
		 		        	Gco2 = rst.getDouble(7);
		 		        	rend_altern = rst.getDouble(8);
		 		        	fact_puis = rst.getDouble(9);
		 		        	inj_eau = rst.getDouble(10);
		 		        	pci = rst.getDouble(11);
		 		        	hdf = rst.getDouble(12);
		 		        	puis_auxi = rst.getDouble(13);
		 		            debit_gaz = rst.getDouble(14);
	 	     			    debit_fuel = rst.getDouble(15);
		 		        //---------------Température ambiante-----------------
		 		      
		 		        	
		 		       if(lecture.gaz){
		 		    	   
		 		    	   
		 		    	  //-----------Correction température-----------------  
				        	double cor_t = fonction.tmp_amb_p_g(tmp);
				        	double val_cor_tmp = 1/cor_t;
					           //-----------Correction pression-----------------  
				            double cor_p = fonction.pression_p_g(press);
				            double val_cor_press = 1/cor_p;
					           //-----------Correction humidité-----------------  
					        double cor_h = fonction.humidite_p_g(humid, tmp);
					        double val_cor_humid = 1/cor_h;
					           //-----------Correction température eau de mer----------------  
				            double cor_t_em = fonction.tmp_em_p_g(tmp_em, tmp);
				            double val_cor_tem = 0 - cor_t_em;
					           //-----------Correction frequence-----------------  
				            double cor_f = fonction.freq_p_g(freq, tmp);
				            double val_cor_freq = 1/cor_f;
					           //-----------Correction wobb-----------------  
				            double cor_wco2 = fonction.wobb_p_g(wobb, Gco2);
				            double val_cor_wco2 = 1/cor_wco2;
				           //-----------Correction perte alternateur-----------------  
				           double corr_p_alt = fonction.perte_altern_p_g(fact_puis, rend_altern);
				           double val_nom_p_alt = fonction.perte_altern_p_g(0.9, rend_altern);
				           double val_cor_p_alt = val_nom_p_alt - corr_p_alt;
				           //-----------Correction injection d'eau-----------------  
				            double corr_inj_eau = /*fonction.inj_p_g(inj_eau)*/ 1;
				            double val_cor_inj = 1/corr_inj_eau;
				           //-----------Correction heure de flame-----------------  
			        	    double corr_h_d_f = fonction.hrf_p_g(hdf);
			        	    double val_cor_hdf =1+ (0.998 - corr_h_d_f);
				          
				           
				           
			        double tphh2 = Math.abs(((rend_altern*1000)-val_cor_p_alt-val_cor_tem + puis_auxi)*(val_cor_tmp*val_cor_press*val_cor_humid*val_cor_wco2*val_cor_freq*val_cor_inj)*val_cor_hdf);
			        
		 		 		         
		     			            def_dem.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),df.format(cor_t),df.format(cor_t_em),df.format(cor_p),df.format(cor_h),df.format(cor_f),df.format(cor_wco2),df.format(corr_p_alt),df.format(corr_inj_eau),df.format(tphh2),df.format(rend_altern)});
		     			            pop.add(new Millisecond(new Date(d1_lg)), tphh2);
		     			            def_ind.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),cb_gen.getSelectedItem(),df.format(tmp),df.format(humid),df.format(press),df.format(tmp_em),df.format(debit_gaz),df.format(freq),df.format(rend_altern),df.format(wobb),df.format(inj_eau),df.format(pci),df.format(Gco2),df.format(fact_puis),df.format(hdf),df.format(puis_auxi),df.format(debit_fuel)});
	            
		 		       
		 		       }else{
		 		    	  //------------------hna te3 gasoil----------------------  
				        	
				        	//-----------Correction température ambiante-----------------
				 		        double cor_t = fonction.tmp_amb_p_fo(tmp);
				 		        double val_cor_tmp = 1/cor_t;
			                //-----------Correction pression ambiante-----------------
		 			           double cor_p = fonction.pression_p_fo(press);
		 			           double val_cor_press = 1/cor_p;
		  			        //-----------Correction humidité-----------------   
		  			           double cor_h = fonction.humidite_p_fo(humid, tmp);
		  			           double val_cor_humid = 1/cor_h;
		  			        //-----------Correction température eau de mer -----------------         
		  			           double cor_t_em = fonction.tmp_em_p_fo(tmp_em, tmp);
		  			           double val_cor_tem = 0 - cor_t_em;
					        //-----------Correction fréquence-----------------  
					            double cor_f = fonction.freq_p_fo(freq, tmp);
					            double val_cor_freq = 1/cor_f;
					        //-----------Correction injection d'eau-----------------  
					           double corr_inj_eau = fonction.inj_p_fo(inj_eau);
					           double val_cor_inj = 1/corr_inj_eau;
					         //-----------Correction perte alternateur-----------------  
					           double corr_perte_altern = fonction.perte_altern_p_g(fact_puis, rend_altern);
					           double nominal_corr_p_altrn= fonction.perte_altern_p_g(0.9, rend_altern);
					           double val_cor_p_alt = nominal_corr_p_altrn - corr_perte_altern;
					        //-----------Correction PCI-----------------------------
					           double corr_pci  = fonction.pci_p_fo(pci);
					           double val_cor_pci = 1/corr_pci;
					           //-----------Correction heure de flame-----------------  
				        	    double corr_h_d_f = fonction.hrf_p_g(hdf);
				        	    double val_cor_hdf =1+ (0.998 - corr_h_d_f);
						        //-----------Correction Global-----------------        
					           double tphh2 = Math.abs(((rend_altern*1000)-val_cor_p_alt-val_cor_tem + puis_auxi)*(val_cor_tmp*val_cor_press*val_cor_humid*val_cor_pci*val_cor_freq*val_cor_inj)*val_cor_hdf);
						
					 	     //------------------fin hna te3 gasoil----------------------   
		     			            def_dem.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),df.format(cor_t),df.format(cor_t_em),df.format(cor_p),df.format(cor_h),df.format(cor_f),df.format(corr_inj_eau),df.format(corr_pci),df.format(tphh2)});
		     			            pop.add(new Millisecond(new Date(d1_lg)), tphh2);
		     			            def_ind.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),cb_gen.getSelectedItem(),df.format(tmp),df.format(humid),df.format(press),df.format(tmp_em),df.format(debit_gaz),df.format(freq),df.format(rend_altern),df.format(wobb),df.format(inj_eau),df.format(pci),df.format(Gco2),df.format(fact_puis),df.format(hdf),df.format(puis_auxi),df.format(debit_fuel)});
			 		    //------------------fin hna te3 gasoil----------------------  
		 		       }  
		 		         }
		 		        tab_dem.setModel(def_dem);
		 		        tab_ind.setModel(def_ind);
                 //---------------------------------------  
		 		                dt_lg_save = d1_lg;
     			      			d1_lg = d1_lg + index;
     			      		}
     			      	 dataset.addSeries(pop);
     			      	 
     			      	 
     			      	}
     			      	
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
		 btnChercher.setBounds(827, 11, 119, 41);
		 panel_1.add(btnChercher);
		 
		 JLabel lblFragment = new JLabel("Fragment");
		 lblFragment.setBounds(707, 47, 59, 24);
		 panel_1.add(lblFragment);
		 cb_frag.setBounds(707, 68, 109, 20);
		 
		 for(int i = 5; i < 180; i = i + 5){
			 cb_frag.addItem(i+" Minutes");
		 }
		 
		 panel_1.add(cb_frag);
		 
		 JButton btnImprimer = new JButton("Rapport");
		 btnImprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		
		 		JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:/Users/Amine/Documents/"));
				int ret = chooser.showSaveDialog(null);
				if(ret == JFileChooser.APPROVE_OPTION){
					String fl_chemin = chooser.getSelectedFile().toString()+"";
					
					
					System.out.println(fl_chemin);
				
					try {
						writeChart(fl_chemin+".jpg", 700, 250,  chart );
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		 		
		 		Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
				
				try {
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fl_chemin+".pdf"));
					document.open();
					
					
					Image image2 = null;
					try {
						image2 = Image.getInstance("image/logo_AOM.png");
						  image2.scaleAbsolute(280f, 80f);
						  image2.setAbsolutePosition(550, 500);
					} catch (MalformedURLException e10) {
						// TODO Auto-generated catch block
						e10.printStackTrace();
					} catch (IOException e9) {
						// TODO Auto-generated catch block
						e9.printStackTrace();
					}
						
					
					
					
					
					
				  Anchor anchorTarget = new Anchor("Le : "+new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				     
				  	  anchorTarget.setName("BackToTop");
				  	
				  	  Paragraph paragraph1 = new Paragraph();
		
				      paragraph1.setSpacingBefore(50);

				      paragraph1.add(anchorTarget);
				      
				      document.add(paragraph1);

			
					Paragraph title1 = new Paragraph("Rapport du model thermodinamique", 
							FontFactory.getFont(FontFactory.HELVETICA, 
							   18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
							   
							Chapter chapter1 = new Chapter(title1, 1);
							chapter1.setNumberDepth(0);
					 String gg = "";
					 int x = cb_gen.getSelectedIndex()+1;
		                if(x == 4){
		                	gg = "obals";
		                }else{
		                 gg = ""+x;
		                }
		                
		                String d_comn = "Gaz";
		                
		                if(lecture.gaz){
		                	d_comn = "Gaz";
		                }else{
		                	d_comn = "Gasoil";
		                }
					Paragraph title11 = new Paragraph("Rapport de correction de puissance G"+gg+".  Combustible "+d_comn+".", 

						       FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, 
						   
						       new CMYKColor(0, 255, 255,17)));
						   
						Section section1 = chapter1.addSection(title11);
		               
		               
		                
						Paragraph someSectionText = new Paragraph("Du : "+new SimpleDateFormat("dd/MM/yyyy").format(dt_D.getDate())+"       Au: "+new SimpleDateFormat("dd/MM/yyyy").format(dt_F.getDate()));

						section1.add(image2); 
						
						section1.add(someSectionText);

						someSectionText = new Paragraph("Fragmentation temporelle par "+cb_frag.getSelectedItem());

						section1.add(someSectionText);
						 
						
						
						
						int dx = 0;
					      String[] titre = t_tab_dem;
					      dx = titre.length;
					      //{"Frag Temporel","T° Amb","T° Eau M","Pression","Humidité","Fréquence","Wobb","Perte Altern","Inject Eau","T.P.H"};
					      if(!lecture.gaz){
					      titre = t_tab_dem2;
					      dx = titre.length;
					      }
						
						
						PdfPTable t = new PdfPTable(dx);

						
						  t.setHorizontalAlignment(0);
						  t.setWidthPercentage(100);
					      t.setSpacingBefore(25);
					      t.setSpacingAfter(25);
					      
					     
					      
					      for(int i = 0; i < dx ; i++){
					      PdfPCell c1 = new PdfPCell(new Phrase(titre[i]));  
					      t.addCell(c1);
					      }
					
					      for(int i = 0; i < def_dem.getRowCount();i++){
								try{
									for(int j = 0; j<def_dem.getColumnCount();j++){
										 t.addCell((String)def_dem.getValueAt(i, j));									
									}
								}catch (Exception e41){}
					      }
					      
					      
					      try {
							  image2 = Image.getInstance(fl_chemin+".jpg");
							  image2.scaleAbsolute(700f, 250f);
						} catch (MalformedURLException e10) {
							// TODO Auto-generated catch block
							e10.printStackTrace();
						} catch (IOException e9) {
							// TODO Auto-generated catch block
							e9.printStackTrace();
						}
					      
					      
					      
					      

					      section1.add(t);
					      section1.add(image2);
					      document.add(section1);

				document.close();
				new File(fl_chemin+".jpg").delete();
		    	JOptionPane.showMessageDialog(null,"Rapport PDF Creer avec succées.","Information",JOptionPane.INFORMATION_MESSAGE);

				} catch (FileNotFoundException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				} catch (DocumentException e7) {
					// TODO Auto-generated catch block
					e7.printStackTrace();
				}
				}
		 		
		 	}
		 });
		 btnImprimer.setBounds(827, 54, 119, 34);
		 panel_1.add(btnImprimer);
		 
		 JPanel panel_3 = new JPanel();
		 panel_3.setBorder(new TitledBorder(null, "Correction lier a l'espace temporel demander", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_3.setBounds(10, 127, 334, 231);
		 
		 
		 tab_rech = new JTable(d_tab_rech,t_tab_Rech);	
			
			tab_rech.setRowHeight(25);
			JScrollPane scrollPane_2 = new JScrollPane(tab_rech);
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(scrollPane_2);
		 
		 
		 add(panel_3);
		 
		 JPanel panel_4 = new JPanel();
		 panel_4.setBorder(new TitledBorder(null, "Historique de correction par fragments temporel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_4.setBounds(10, 369, 952, 127);
		 
		 	tab_dem = new JTable(d_tab_dem ,t_tab_dem );	
			tab_dem .setRowHeight(25);
			//JScrollPane scrollPane_3 = new JScrollPane(tab_dem );
			
			p_table = new JPanel(new BorderLayout());
			p_table.setPreferredSize(new Dimension(940, 250));
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(new JScrollPane(p_table), BorderLayout.CENTER);

			p_table.add(new JScrollPane(tab_dem),BorderLayout.CENTER);
		 
		 add(panel_4);
		 
		 JPanel panel_42 = new JPanel();
		 panel_42.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historique des valeurs mesurer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_42.setBounds(10, 530, 952, 201);
		 
		 
		 tab_ind = new JTable(d_tab_ind ,t_tab_ind );	
		 tab_ind .setRowHeight(25);
			//JScrollPane scrollPane_3 = new JScrollPane(tab_dem );
			
			p_table2 = new JPanel(new BorderLayout());
			p_table2.setPreferredSize(new Dimension(1800, 250));
			panel_42.setLayout(new BorderLayout(0, 0));
			panel_42.add(new JScrollPane(p_table2), BorderLayout.CENTER);

			p_table2.add(new JScrollPane(tab_ind),BorderLayout.CENTER);

		add(panel_42);	
			
			
			
		 for(int i = 0; i<60; i++){
			 if(i<10)cb_s_d.addItem("0"+i);
			 else cb_s_d.addItem(""+i);
		 }
		 

		 chart = ChartFactory.createTimeSeriesChart("Correction T,P,H,T2 par fragment Temporel","temps","Correction",dataset,true,true,false);		 
		 ChartPanel panel_5 = new ChartPanel(chart);
		 panel_5.setBorder(new TitledBorder(null, "Graph de valeur de correction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_5.setBounds(354, 135, 608, 223);
		 add(panel_5);
		 panel_5.setLayout(new BorderLayout(0, 0));
	}


public void writeChart(String filename, int x, int y, JFreeChart chart )
throws Exception
{	
	
//if (param.backgroundPaint != null) chart.getPlot().setBackgroundPaint(param.backgroundPaint);
//TODO: handle legends with series titles 
//chart.removeLegend();
//plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
//plot.setNoDataMessage("No data available");

//if (param.subtitle != null)chart.addSubtitle(new TextTitle(param.subtitle));

String fileExt = filename.substring(filename.indexOf("."), filename.length());
if (fileExt.equalsIgnoreCase("jpg") || fileExt.equalsIgnoreCase("jpeg"))
	ChartUtilities.saveChartAsJPEG(new File(filename), chart, x, y);		
else
	ChartUtilities.saveChartAsPNG(new File(filename), chart, x, y);

}


			
			
		
		


void indice(){
	def_dem = new DefaultTableModel();
	def_dem.setColumnIdentifiers(t_tab_dem);
	

	
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
 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G1' ";
	          	}else if(cb_gen.getSelectedIndex()==1){
 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G2' ";
	          	}else if(cb_gen.getSelectedIndex()==2){
 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G3' ";
	          	}else {
 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')  ";
	          	}
		 //---------------------------------------
	 		    
	 		    System.out.println("to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')");
	 		    
	 		ResultSet rst = statement.executeQuery(req);
		        
		   double tmp = 0;
		   double tmp_em = 0;
		   double press = 0;
		   double humid = 0;
		         
		         while(rst.next()){
		        	tmp = rst.getDouble(1);
		        	humid = rst.getDouble(2);
		        	press = rst.getDouble(3);
		        	tmp_em = rst.getDouble(4);

 			            def_dem.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),tmp,tmp_em,press,humid});
 			            pop.add(new Millisecond(new Date(d1_lg)), tmp);
 			         
 			          
		         }
		        tab_dem.setModel(def_dem);
		       
     //---------------------------------------  
		                dt_lg_save = d1_lg;
		      			d1_lg = d1_lg + index;
		      			
		      		}
		      		
		 
		      		
		      	}
		      	
          	 con.close();
	      	} catch (ClassNotFoundException e1) {
		    	  JOptionPane.showMessageDialog(null,"Erreur de connexion a la bdd","Attention",JOptionPane.ERROR_MESSAGE);
		      } catch (SQLException sqle) {
		    	  JOptionPane.showMessageDialog(null,"Erreur sur la requette","Attention",JOptionPane.ERROR_MESSAGE);
		      }
}

String rt_nbr(String str){
    String rt = str.replaceAll(",", ".");
    String rt1 = "";
	for(int i = 0; i< rt.length(); i++){
		try{
			if(rt.substring(i, i+1).equals("0")||rt.substring(i, i+1).equals("1")||rt.substring(i, i+1).equals("2")||rt.substring(i, i+1).equals("3")||rt.substring(i, i+1).equals("4")||rt.substring(i, i+1).equals("5")||rt.substring(i, i+1).equals("6")||rt.substring(i, i+1).equals("7")||rt.substring(i, i+1).equals("8")||rt.substring(i, i+1).equals("9")||rt.substring(i, i+1).equals(".")){
				rt1 = rt1 + (String) rt.substring(i, i+1);
			}
		}catch (Exception e) {
			
		}
	}
	return rt1;
}



}
