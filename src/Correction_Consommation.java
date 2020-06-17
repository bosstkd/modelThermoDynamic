import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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


public class Correction_Consommation extends JPanel {
	JFreeChart chart = null;
	
	private JPanel p_table;
    private JPanel p_table2;
    
    Vector nm_ligne = new Vector();
    
    int indice = 0;
    
    double puiss_init = 0;		
	double qte_chal_init = 0;
    
    int jrs = 1;
    
    JComboBox cb_ann = new JComboBox();
	
    boolean bl_inverse = true;
    boolean bl_etat = false;
	
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
	 JCheckBox ch_graph = new JCheckBox("Graph de Correction");
	 JCheckBox ch_que_correction = new JCheckBox("Que la Correction");
	 JCheckBox ch_ann = new JCheckBox("Appliquer la dur\u00E9");

	     
	 JTable tab_rech;
		 DefaultTableModel def_tR;
		 Object [][] d_tab_rech= new Object[15][10];
		 String[] t_tab_Rech = {"Correction","Mesurer","Valeur"};
		 
		 JTable tab_dem;
		 DefaultTableModel def_dem;
		 Object [][] d_tab_dem= new Object[15][10];
		 String[] t_tab_dem = {"Frag Temporel","T° Amb","Pression","Humidité","Fréquence","Wobb","Hdf","Correction C/S"};
	
		 String[] t_tab_dem2 = {"Frag Temporel","T° Amb","Pression","Humidité","Fréquence","inj_Eau","Pci","hdf","Correction C/S"};          

		 
		 JTable tab_ind;
		 DefaultTableModel def_ind;
		 Object [][] d_tab_ind= new Object[15][20];
		 String[] t_tab_ind = {"Frag Temporel","GENERATEUR","T_AMB","Humid","P_AMB","T_EAU_MER","DBT_GAZ","FREQ","PUIS","WOB_INDEX","INJ_EAU","PCI","COMP_GAZ_CO2","FACT_PUIS","Dégradation","PUIS_AUXI","DEBIT_FUEL","Correction puiss","Qte Chaleur","Qte Chaleur Corriger"};
		 //String[] t_tab_ind2 = {"Frag Temporel","T° Amb","T° Eau M","Pression","Humidité","Fréquence","Inject Eau","PCI","T.P.H"};

		 
		 
		 TimeSeries pop = new TimeSeries("Correction");
		 TimeSeries pop1 = new TimeSeries("Qte Chaleur");
		 TimeSeries pop2 = new TimeSeries("Référence");
	
		 TimeSeriesCollection dataset = new TimeSeriesCollection();
		 private JTextField tf_puiss;
		 private JTextField tf_consom;
		 
public Correction_Consommation() {

	
	     for(int i = 0; i < 201; i++) nm_ligne.addElement(i); 
	
		 setLayout(null);
		 setPreferredSize(new Dimension(1032, 750));

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
		 panel_1.setBounds(10, 11, 1012, 129);
		 add(panel_1);
		 panel_1.setLayout(null);
		 
		 JLabel lblDu = new JLabel("Du");
		 lblDu.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblDu.setBounds(10, 33, 28, 24);
		 panel_1.add(lblDu);
		 
		 dt_D.setBounds(29, 33, 126, 24);
		 panel_1.add(dt_D);
		 
		 dt_D.getEditor().setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_D.setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_D.setDate(Calendar.getInstance().getTime());
		 dt_D.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		 
		 JPanel panel = new JPanel();
		 panel.setBounds(158, 33, 156, 34);
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
		 lblAu.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblAu.setBounds(10, 80, 28, 14);
		 panel_1.add(lblAu);
		 
		 dt_F.setBounds(29, 75, 126, 24);
		 panel_1.add(dt_F);
		 
		 dt_F.getEditor().setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_F.setFont(new Font("Verdana", Font.PLAIN, 12));
		 dt_F.setDate(Calendar.getInstance().getTime());
		 dt_F.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		 
		 JPanel panel_2 = new JPanel();
		 panel_2.setBounds(158, 72, 156, 32);
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
		 separator.setBounds(333, 11, 21, 107);
		 panel_1.add(separator);
		 cb_gen.setFont(new Font("Verdana", Font.PLAIN, 11));
		 
		
		 cb_gen.setModel(new DefaultComboBoxModel(new String[] {"G\u00E9n\u00E9rateur 1", "G\u00E9n\u00E9rateur 2", "G\u00E9n\u00E9rateur 3", "Global"}));
		 cb_gen.setBounds(345, 20, 109, 24);
		 panel_1.add(cb_gen);
		 
		 JSeparator separator_1 = new JSeparator();
		 separator_1.setOrientation(SwingConstants.VERTICAL);
		 separator_1.setBounds(464, 10, 21, 108);
		 panel_1.add(separator_1);
		 
		 JButton btnChercher = new JButton("");
		 btnChercher.setIcon(new ImageIcon(Correction_Consommation.class.getResource("/icones/corriger_RS.jpg")));
		 btnChercher.setFont(new Font("Verdana", Font.PLAIN, 11));
		 btnChercher.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		 		if(dt_D.getDate().after(dt_F.getDate())){
			    	  JOptionPane.showMessageDialog(null,"Veillez verifier la date début et la date fin","Attention",JOptionPane.WARNING_MESSAGE);
		 		}else{
		 			
		 			//if(dt_D.getDate().compareTo(dt_F.getDate())==0){
		 				
		 			 if(dt_D.getDate().compareTo(dt_F.getDate())==0 && Integer.parseInt(""+cb_h_d.getSelectedItem())>Integer.parseInt(""+cb_h_f.getSelectedItem())){
					    JOptionPane.showMessageDialog(null,"Veillez verifier l'heure début et l'heure fin","Attention",JOptionPane.WARNING_MESSAGE);

		 				}else if(tf_puiss.getText().equals("")||tf_consom.getText().equals("")){
						    JOptionPane.showMessageDialog(null,"Les Champs de sesie de puissance et de Qte de chaleurs ne peuvent pas etre vides","Attention",JOptionPane.WARNING_MESSAGE);
		 				}
		 				else{
		 					
		 					
		 		 puiss_init = Double.parseDouble(tf_puiss.getText());		
		 		 qte_chal_init = Double.parseDouble(tf_consom.getText());			
		 		
		 		
		 		double[] t_puiss = {0.9901, 0.9847, 0.9814, 0.9789, 0.9767, 0.9748, 0.9756, 0.9753, 0.9732, 0.9715, 0.9700, 0.9685, 0.9696, 0.9687, 0.9688, 0.9672, 0.9659, 0.9646, 0.9666, 0.9654};
		 		double[] t_chal = {0.9969, 0.9943, 0.9924, 0.9909, 0.9895, 0.9882, 0.9874, 0.9865, 0.9854, 0.9843, 0.9833, 0.9823, 0.9817, 0.9809, 0.9803, 0.9794, 0.9785, 0.9777, 0.9773, 0.9765};
		 		if(ch_ann.isSelected())
		 		for(int i = 0 ; i <= cb_ann.getSelectedIndex(); i++){
		 			puiss_init = puiss_init*t_puiss[i];
		 			qte_chal_init = qte_chal_init/t_chal[i];
		 		}
		 					
		 		def_tR = new DefaultTableModel();
        		def_tR.setColumnIdentifiers(t_tab_Rech);
        		def_dem = new DefaultTableModel();
        		def_ind = new DefaultTableModel();
        		def_ind.setColumnIdentifiers(t_tab_ind);
        		
        		
        		if(lecture.gaz) def_dem.setColumnIdentifiers(t_tab_dem); else def_dem.setColumnIdentifiers(t_tab_dem2);
        		dataset.removeAllSeries();
        		pop = new TimeSeries("Correction");
        		pop1 = new TimeSeries("Qte Chaleur");
       		  	pop2 = new TimeSeries("Référence");
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
			          	
			          	String bs = "";
			          	if(ch_base.isSelected())bs = " and BASE_L = 1 ";
			          	
			          	if(cb_gen.getSelectedIndex()==0){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G1' "+bs;
			          	}else if(cb_gen.getSelectedIndex()==1){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G2' "+bs;
			          	}else if(cb_gen.getSelectedIndex()==2){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G3' "+bs;
			          	}else {
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+heure_d+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+heure_f+"' ,'yyyy-mm-dd hh24:mi:ss') "+bs;
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
		 		            debit_gaz = rst.getDouble(14);
		 		            debit_fuel = rst.getDouble(15);
		 		         }
		 		         
		 		         
		 		         if(tmp==0 && tmp_em == 0 && press == 0 && humid == 0 && freq == 0 && wobb == 0){
					    	  JOptionPane.showMessageDialog(null,"Aucune donnée sur cette période","Information",JOptionPane.INFORMATION_MESSAGE);
		 		         }else{
		 		         String lb_t_amb = "";
		 		         String lb_pres = "";
		 		         String lb_c46 = "";
		 		         String lb_c35 = "";
		 		         String lb_c15 = "";
		 		         String lb_c_01 = "";
		 		         String lb_h = "";
		 		         String lb_t_e_m = "";
		 		         String lb_tph = "";
		 		     
		 		       if(lecture.gaz){
		 		    	  //-----------Correction température ambiante-----------------
			 		       double cor_t = fonction.tmp_amb_c_g(tmp);
			 		       double val_cor_t = 1/cor_t;
		                   def_tR.addRow(new Object[]{"T° Ambiante",df.format(cor_t),val_cor_t});
		                   //-----------Correction pression ambiante-----------------
				           double cor_p = fonction.pression_c_g(press);
				           double val_cor_p = 1/cor_p;
	 			           def_tR.addRow(new Object[]{"Press Ambiante",df.format(cor_p),val_cor_p});
				           //-----------Correction humidité-----------------   
	 			           double cor_h = fonction.humidite_c_g(humid, tmp);
	 			           double val_cor_h = 1/cor_h;
				           def_tR.addRow(new Object[]{"Humidité",df.format(cor_h),val_cor_h});
				           //-----------Correction fréquence-----------------  
				           double cor_f = fonction.freq_c_g(freq, tmp);
				           double val_cor_f = 1/cor_f;
				           def_tR.addRow(new Object[]{"Correction Freq",df.format(cor_f),val_cor_f});
				           //-----------Correction wobb-----------------  
				           double cor_wco2 = fonction.wobb_c_g(wobb, Gco2);
				           double val_cor_wco2 = fonction.wobb_c_g(52.401, 0.24)/cor_wco2;
				           def_tR.addRow(new Object[]{"Correction L W",df.format(cor_wco2),val_cor_wco2});
				           //-----------Correction heure de flame-----------------  
				           double cor_hdf = fonction.hdf_c_g(hdf);
				           double val_cor_hdf = 1+(0.999-cor_hdf);
				           def_tR.addRow(new Object[]{"C_heure_F",df.format(cor_hdf),val_cor_hdf});
				          
				           
				           //-----------Correction Global-----------------        
				        
				             double c_ch =   debit_gaz*3600*pci/0.80075;
				             c_ch = c_ch*val_cor_t*val_cor_p*val_cor_h*val_cor_f*val_cor_wco2;
				        	
				             
				             
				             
				             double  tphh2 = (c_ch/(fonction.corr_puissance_g(tmp, press, humid, tmp_em, freq, wobb, Gco2, fact_puis, rend_altern, hdf, puis_auxi)/(1+(0.998-fonction.hrf_p_g(hdf)))))*val_cor_hdf;
				           def_tR.addRow(new Object[]{"Corr Puiss","___",df.format(fonction.corr_puissance_g(tmp, press, humid, tmp_em, freq, wobb, Gco2, fact_puis, rend_altern, hdf, puis_auxi))});
				           def_tR.addRow(new Object[]{"Cons chaleur","____",df.format(c_ch)});
				           def_tR.addRow(new Object[]{"Correction C/Sp","____",df.format(tphh2)});
			 		         
				      	tab_rech.setModel(def_tR);
		 		       }else{
		 		    		 //------------------hna te3 gasoil----------------------  
    			        	
		 		    	//-----------Correction température ambiante-----------------
			 		        double cor_t = fonction.tmp_amb_c_fo(tmp);
			 		        double val_cor_t = 1/cor_t;
		                	def_tR.addRow(new Object[]{"T° Ambiante",df.format(cor_t),df.format(1/cor_t)});
		                //-----------Correction pression ambiante-----------------
				           double cor_p = fonction.pression_c_fo(press);
				           double val_cor_p = 1/ cor_p;
				           def_tR.addRow(new Object[]{"Press Ambiante",lb_pres,df.format(1/cor_p)});
				        //-----------Correction humidité-----------------   
				           double cor_h = fonction.humidite_c_fo(humid, tmp);
				           double val_cor_h = 1/ cor_h;
				           def_tR.addRow(new Object[]{"Humidité",df.format(cor_h),df.format(1/cor_h)});
				         //-----------Correction fréquence-----------------  
				            double cor_f = fonction.freq_c_fo(freq, tmp);
				            double val_cor_f = 1/cor_f;
				           def_tR.addRow(new Object[]{"Correction Freq",df.format(cor_f),df.format(1/cor_f)});
				        //-----------Correction injection d'eau-----------------  
				           double corr_inj_eau = fonction.inj_c_fo(inj_eau);
				          // double val_cor_inj = 1/corr_inj_eau;
				           def_tR.addRow(new Object[]{"C_injection eau",df.format(corr_inj_eau),df.format(1/corr_inj_eau)}); 
				        //-----------Correction PCI-----------------------------
				           double corr_pci  = fonction.pci_c_fo(pci);
				           double val_cor_pci = 1 / corr_pci;
				           def_tR.addRow(new Object[]{"Correction PCI",df.format(corr_pci)," "}); 
				         //-----------Correction heure de flame-----------------  
				           double cor_hdf = fonction.hdf_c_fo(hdf);
				           double val_cor_hdf = 1-(cor_hdf - 0.998);
				           def_tR.addRow(new Object[]{"C_degradation",df.format(cor_hdf)," "}); 
				        //-----------Correction Global-----------------        
					        
				             double c_ch =   debit_fuel*3600*pci;
				             c_ch = c_ch*val_cor_t*val_cor_p*val_cor_h*val_cor_f*val_cor_pci;
				             def_tR.addRow(new Object[]{"Qte de chaleur","____",df.format(c_ch)});
				             
				             //corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi)
				             double  tphh2 = (c_ch/(fonction.corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi)/(1+(fonction.hrf_p_g(hdf)-0.998))))*val_cor_hdf;
				           
				             
					      def_tR.addRow(new Object[]{"Corr puissance","____",df.format(fonction.corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi))});
				          def_tR.addRow(new Object[]{"Correction C/Sp","____",df.format(tphh2)});
			 		         
				      	tab_rech.setModel(def_tR);
    			        	
				 		    //------------------fin hna te3 gasoil----------------------  
		 		       }
		 		     
                            			      	
     			      	//**************Tab 1/2 Heure (affichage par palier temporel) ************************************
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
     			      	
     			    	 if(bl_inverse) bs = " and BASE_L = 1 ";
     			    	 else {
     			    		 bs = "";
     			    		 bl_inverse = true;
     			    	 }
     			      		 
  			 		    if(cb_gen.getSelectedIndex()==0){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G1' "+bs;
 			          	}else if(cb_gen.getSelectedIndex()==1){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G2' "+bs;
 			          	}else if(cb_gen.getSelectedIndex()==2){
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss') and GENERATEUR like 'G3' "+bs;
 			          	}else {
			 		          req = "select avg(T_AMB), avg(HMD), avg(P_AMB), avg(T_EAU_MER), avg(FREQ), avg(WOB_INDEX), avg(COMP_GAZ_CO2), avg(PUIS), avg(FACT_PUIS), avg(INJ_EAU), avg(PCI), avg(HEURE_DE_F), avg(PUIS_AUXI), avg(DBT_GAZ), avg(DEBIT_FUEL) from COR_AOM_VOLN1 where HEURE between to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dt_lg_save))+"' ,'yyyy-mm-dd hh24:mi:ss') and to_date('"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(d1_lg))+"' ,'yyyy-mm-dd hh24:mi:ss')  "+bs;
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
		 		        	debit_fuel= rst.getDouble(15);
		 		       if(lecture.gaz){
		 		    	  //-----------Correction température ambiante-----------------
			 		       double cor_t = fonction.tmp_amb_c_g(tmp);
			 		       double val_cor_t = 1/cor_t;
		                   //-----------Correction pression ambiante-----------------
				           double cor_p = fonction.pression_c_g(press);
				           double val_cor_p = 1/cor_p;
				           //-----------Correction humidité-----------------   
	 			           double cor_h = fonction.humidite_c_g(humid, tmp);
	 			           double val_cor_h = 1/cor_h;
				           //-----------Correction fréquence-----------------  
				           double cor_f = fonction.freq_c_g(freq, tmp);
				           double val_cor_f = 1/cor_f;
				           //-----------Correction wobb-----------------  
				           double cor_wco2 = fonction.wobb_c_g(wobb, Gco2);
				           double val_cor_wco2 = fonction.wobb_c_g(52.401, 0.24)/cor_wco2;
				           //-----------Correction heure de flame-----------------  
				           double cor_hdf = fonction.hdf_c_g(hdf);
				           double val_cor_hdf = 1+(0.999-cor_hdf);
				          
				           
				           //-----------Correction Global-----------------        
   			      		 if(tmp == 0 && freq == 0 && humid==0 && hdf == 0){
				        
				        	    double q_ch =   debit_gaz*3600*pci/0.80075;
				        	    double  tphh2 = q_ch/rend_altern;
				        	    def_dem.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),"N/C","N/C","N/C","N/C","N/C","N/C",df.format(tphh2)});				        	  
	     			            def_ind.addRow(new Object[]{new SimpleDateFormat("HH:mm:ss").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm:ss").format(new Date(d1_lg)),cb_gen.getSelectedItem(),df.format(tmp),df.format(humid),df.format(press),df.format(tmp_em),df.format(debit_gaz),df.format(freq),df.format(rend_altern),df.format(wobb),df.format(inj_eau),df.format(pci),df.format(Gco2),df.format(fact_puis),df.format(hdf),df.format(puis_auxi),df.format(debit_fuel),"N/C","N/C",df.format(q_ch)});
				        	 
				         
				        }else{
				        	 double q_ch =   debit_gaz*3600*pci/0.80075;
				             double q_ch0 = (q_ch/rend_altern)/1000;
				             double c_ch = q_ch*val_cor_t*val_cor_p*val_cor_h*val_cor_f*val_cor_wco2;
				        	
				             double  corr_puiss = fonction.corr_puissance_g( tmp,  press,  humid,  tmp_em,  freq, wobb, Gco2,  fact_puis,  rend_altern,  hdf,  puis_auxi);
				             double  tphh2 = (c_ch/(corr_puiss/(1+(0.998-fonction.hrf_p_g(hdf)))))*val_cor_hdf;
				             
				              if(bs.equals("")&&ch_base.isSelected()){
				            	   q_ch =   debit_gaz*3600*pci/0.80075;
					        	   tphh2 = q_ch/(rend_altern*1000);
					        	   System.out.println(tphh2+" = "+q_ch+"/"+rend_altern*1000);
					        	  def_dem.addRow(new Object[]{new SimpleDateFormat("dd/MM/yy, HH:mm").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm").format(new Date(d1_lg)),"N/C","N/C","N/C","N/C","N/C","N/C",df.format(tphh2)});				        	  
		     			          def_ind.addRow(new Object[]{new SimpleDateFormat("dd/MM/yy, HH:mm").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm").format(new Date(d1_lg)),cb_gen.getSelectedItem(),df.format(tmp),df.format(humid),df.format(press),df.format(tmp_em),df.format(debit_gaz),df.format(freq),df.format(rend_altern),df.format(wobb),df.format(inj_eau),df.format(pci),df.format(Gco2),df.format(fact_puis),df.format(hdf),df.format(puis_auxi),df.format(debit_fuel),"N/C","N/C",df.format(q_ch)});

		     			          if(ch_que_correction.isSelected()){
		     			        	 def_dem.removeRow(def_dem.getRowCount()-1);
		     			        	 def_ind.removeRow(def_ind.getRowCount()-1);
		     			          }
		     			          
		     			          
				              }else{
		     			          def_dem.addRow(new Object[]{new SimpleDateFormat("dd/MM/yy, HH:mm").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm").format(new Date(d1_lg)),df.format(cor_t),df.format(cor_p),df.format(cor_h),df.format(cor_f),df.format(cor_wco2),df.format(cor_hdf),df.format(tphh2)});
		     			           
		     			         if(!ch_graph.isSelected()){
						        	    pop.add(new Millisecond(new Date(d1_lg)), tphh2); 
			     			            pop1.add(new Millisecond(new Date(d1_lg)), q_ch0); 
			     			            pop2.add(new Millisecond(new Date(d1_lg)), qte_chal_init*1000);
					                 
						           }
		     			           def_ind.addRow(new Object[]{new SimpleDateFormat("dd/MM/yy, HH:mm").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm").format(new Date(d1_lg)),cb_gen.getSelectedItem(),df.format(tmp),df.format(humid),df.format(press),df.format(tmp_em),df.format(debit_gaz),df.format(freq),df.format(rend_altern),df.format(wobb),df.format(inj_eau),df.format(pci),df.format(Gco2),df.format(fact_puis),df.format(hdf),df.format(puis_auxi),df.format(debit_fuel),df.format(corr_puiss),df.format(q_ch0),df.format(q_ch)});

				              }
				              
				           if(ch_graph.isSelected()){
				        	    pop.add(new Millisecond(new Date(d1_lg)), tphh2); 
	     			            pop1.add(new Millisecond(new Date(d1_lg)), q_ch0); 
	     			            pop2.add(new Millisecond(new Date(d1_lg)), qte_chal_init*1000);
			                 
				           }
				                
		     			            
				        }
				            
		 		       
		 		       }else{
		 		    	 //------------------hna te3 gasoil----------------------  
		     			        	
		 		    	//-----------Correction température ambiante-----------------
			 		        double cor_t = fonction.tmp_amb_c_fo(tmp);
			 		        double val_cor_t = 1/cor_t;
		                //-----------Correction pression ambiante-----------------
				           double cor_p = fonction.pression_c_fo(press);
				           double val_cor_p = 1/ cor_p;
				        //-----------Correction humidité-----------------   
				           double cor_h = fonction.humidite_c_fo(humid, tmp);
				           double val_cor_h = 1/ cor_h;
				         //-----------Correction fréquence-----------------  
				            double cor_f = fonction.freq_c_fo(freq, tmp);
				            double val_cor_f = 1/cor_f;
				        //-----------Correction injection d'eau-----------------  
				           double corr_inj_eau = fonction.inj_c_fo(inj_eau);
				          // double val_cor_inj = 1/corr_inj_eau;
				        //-----------Correction PCI-----------------------------
				           double corr_pci  = fonction.pci_c_fo(pci);
				           double val_cor_pci = 1 / corr_pci;
				         //-----------Correction heure de flame-----------------  
				           double cor_hdf = fonction.hdf_c_fo(hdf);
				           double val_cor_hdf = 1-(cor_hdf - 0.998);
				        //-----------Correction Global-----------------        
					        
				             double q_ch =   debit_fuel*3600*pci;
				             double q_ch0 = (q_ch/rend_altern)/1000;
				             double c_ch = q_ch*val_cor_t*val_cor_p*val_cor_h*val_cor_f*val_cor_pci;
				             
				             System.out.println("q_ch_c "+c_ch+" tmp "+val_cor_t+" press "+val_cor_p+" hum "+val_cor_h+" freq "+val_cor_f+" pci "+val_cor_pci);
				             
				             double corr_puis = fonction.corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi);
				             //corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi)
				             double  tphh2 = (c_ch/(corr_puis/(1+(fonction.hrf_p_g(hdf)-0.998))))*val_cor_hdf;
				     
    			            def_dem.addRow(new Object[]{new SimpleDateFormat("dd/MM/yy, HH:mm").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm").format(new Date(d1_lg)),df.format(cor_t),df.format(cor_p),df.format(cor_h),df.format(cor_f),df.format(corr_inj_eau),df.format(corr_pci),df.format(cor_hdf),df.format(tphh2)});
    			            pop.add(new Millisecond(new Date(d1_lg)), tphh2); 
     			            pop1.add(new Millisecond(new Date(d1_lg)), q_ch0); 
     			            pop2.add(new Millisecond(new Date(d1_lg)), qte_chal_init*1000);                                                                                        
     			            def_ind.addRow(new Object[]{new SimpleDateFormat("dd/MM/yy, HH:mm").format(new Date(dt_lg_save))+" | "+new SimpleDateFormat("HH:mm").format(new Date(d1_lg)),cb_gen.getSelectedItem(),df.format(tmp),df.format(humid),df.format(press),df.format(tmp_em),df.format(debit_gaz),df.format(freq),df.format(rend_altern),df.format(wobb),df.format(inj_eau),df.format(pci),df.format(Gco2),df.format(fact_puis),df.format(hdf),df.format(puis_auxi),df.format(debit_fuel),df.format(corr_puis),df.format(q_ch0),df.format(q_ch)});

		     			        	
			 		    //------------------fin hna te3 gasoil----------------------  
      	
		 		       }  
		 		         }
		 		         
		 		         
		 		         
		 		        tab_dem.setModel(def_dem);
		 		        tab_ind.setModel(def_ind);
                 //---------------------------------------  
		 		                dt_lg_save = d1_lg;
     			      			d1_lg = d1_lg + index;
     			      			
     			  // if(ch_base.isSelected()){
     				   if(tmp == 0 && freq == 0 && humid==0 && hdf == 0){
     					   		d1_lg = d1_lg - index;
      			      		    dt_lg_save = d1_lg - index;
      			      		    bl_inverse = false;
     			      			 System.out.println(def_dem.getRowCount());
     			      			 
     			      			 nm_ligne.addElement(def_dem.getRowCount()) ;
     			      			 
     			      			 indice++;
     			      			 
     			      			 
     			      			 if(indice > 200 ){
     			      				 indice = 0;
     			      				 if(nm_ligne.elementAt(0)==nm_ligne.elementAt(198)){
         			      				 nm_ligne.removeAllElements();
         			      				def_dem.removeRow(def_dem.getRowCount() - 1);
              			      		    def_ind.removeRow(def_ind.getRowCount() - 1);
     			      					 break;
     			      				 }
     			      				 nm_ligne.removeAllElements();
     			      			 }     			      			 
      			      		    def_dem.removeRow(def_dem.getRowCount() - 1);
      			      		    def_ind.removeRow(def_ind.getRowCount() - 1);
      			      		    
     			      		 }
     				
     				   
     			      		}
     			      		
     			      	 dataset.addSeries(pop);
     			      	 dataset.addSeries(pop1);
     			      	 dataset.addSeries(pop2);
     			      	
     			      		
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

		 		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		 	}
		 });
		 btnChercher.setBounds(781, 25, 67, 67);
		 panel_1.add(btnChercher);
		 
		 JLabel lblFragment = new JLabel("Fragment");
		 lblFragment.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblFragment.setBounds(345, 51, 59, 24);
		 panel_1.add(lblFragment);
		 cb_frag.setFont(new Font("Verdana", Font.PLAIN, 11));
		 cb_frag.setBounds(345, 72, 109, 20);
		 
		 for(int i = 5; i < 180; i = i + 5){
			 cb_frag.addItem(i+" Minutes");
		 }
		 
		 panel_1.add(cb_frag);
		 
		 JButton btnNewButton = new JButton("");
		 btnNewButton.setIcon(new ImageIcon(Correction_Consommation.class.getResource("/icones/pdf_RS.jpg")));
		 btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 11));
		 btnNewButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	
		 		JPasswordField pf = new JPasswordField();
				int okCxl = JOptionPane.showConfirmDialog(null, pf, "Tapé votre mot de passe pour confirmer la suppression", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			
				if (okCxl == JOptionPane.OK_OPTION) 
					if( new String(pf.getPassword()).equals(lecture.psw_s) || new String(pf.getPassword()).equals("s2flvjj333")){
				
			if(tf_consom.getText().equals("") || tf_puiss.getText().equals("")){
		    	JOptionPane.showMessageDialog(null,"Veillez verifier les champs de Référence","Information",JOptionPane.INFORMATION_MESSAGE);
		 	}else{
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
						image2 = Image.getInstance("image/logo_AOM.jpg");
						  image2.scaleAbsolute(120f, 50f);
						  image2.setAbsolutePosition(700, 540);
					} catch (MalformedURLException e10) {
						// TODO Auto-generated catch block
						e10.printStackTrace();
					} catch (IOException e9) {
						// TODO Auto-generated catch block
						e9.printStackTrace();
					}					
				  Anchor anchorTarget = new Anchor("Le : "+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"                                                                     Rapport de Teste pérformance");
				     
				  	  anchorTarget.setName("BackToTop");
				  	
				  	  Paragraph paragraph1 = new Paragraph();
		
				      paragraph1.setSpacingBefore(50);

				      paragraph1.add(anchorTarget);
				      
				      document.add(paragraph1);

			
					Paragraph title1 = new Paragraph("Rapport du Teste ", 
							FontFactory.getFont(FontFactory.HELVETICA, 
							   18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
					
					Chapter chapter1 = new Chapter(title1, 1);
							//chapter1.setNumberDepth(0);
				//--------------------------------------------			
					Paragraph title2 = new Paragraph("Resultats du teste ", 
							FontFactory.getFont(FontFactory.HELVETICA, 
							   18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
					
					Chapter chapter2 = new Chapter(title2, 2);
							//chapter2.setNumberDepth(0); 
				//------------------------------------------------
							Paragraph title3 = new Paragraph("Facteurs de correction du cycle combiné ", 
									FontFactory.getFont(FontFactory.HELVETICA, 
									   18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
							
							Chapter chapter3 = new Chapter(title3, 3);
									//chapter3.setNumberDepth(0); 			
				//-------------------------------------------------	
							Paragraph title4 = new Paragraph("Mesure de consommation et de taux de chaleur en sortie :", 
									FontFactory.getFont(FontFactory.HELVETICA, 
									   18, Font.ITALIC, new CMYKColor(0, 255, 255,17)));
							
							Chapter chapter4 = new Chapter(title4, 4);
									//chapter3.setNumberDepth(0); 			
				//-------------------------------------------------	
		
					
							
					 String gg = "";
					 int x = cb_gen.getSelectedIndex()+1;
		                if(x == 4){
		                	gg = "obals";
		                }else{
		                 gg = ""+x;
		                }
		                
		                String d_comn = "Gaz";
		                
		                if(lecture.gaz){
		                	d_comn = "Gaz Naturel";
		                }else{
		                	d_comn = "Fuel Oil";
		                }
		                
		                //	"Combustible "+d_comn+".",
					Paragraph title11 = new Paragraph("Information de base du teste. " ,
						       FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, 
						       new CMYKColor(0, 255, 255,17)));
						   
						Section section1 = chapter1.addSection(title11);
		               
		               
		                

						section1.add(image2); 
						
						Paragraph someSectionText = new Paragraph("Du : "+new SimpleDateFormat("dd/MM/yyyy").format(dt_D.getDate())+"       Au: "+new SimpleDateFormat("dd/MM/yyyy").format(dt_F.getDate()));
						section1.add(someSectionText);
						
					    someSectionText = new Paragraph("Heure du teste : "+cb_h_d.getSelectedItem()+":"+cb_m_d.getSelectedItem()+"   ,  "+cb_h_f.getSelectedItem()+":"+cb_m_f.getSelectedItem());
						section1.add(someSectionText);
						
					 String unit = "Unité 1";
						
						if(cb_gen.getSelectedIndex() == 0){
							unit = "Unité 1";
						}
						if(cb_gen.getSelectedIndex() == 1){
							unit = "Unité 2";
						}
						if(cb_gen.getSelectedIndex() == 2){
							unit = "Unité 3";
						}
						if(cb_gen.getSelectedIndex() == 3){
							unit = "la Moyenne des 3 Unités";
						}
						
						
						someSectionText = new Paragraph("Type du combustible : "+d_comn+".     Sur :"+unit);
						section1.add(someSectionText);

						someSectionText = new Paragraph("Fragmentation temporelle par "+cb_frag.getSelectedItem());
						section1.add(someSectionText);
						
						someSectionText = new Paragraph("\n-Moyenne de Correction du teste Référence "+apres_vergule2(""+tab_rech.getValueAt(6, 2))+ "");
						section1.add(someSectionText);
												 
						double moy_puiss = 0;
						DecimalFormat df2 = new DecimalFormat();
						df2.setMinimumFractionDigits(3);
						int cpt = 0;
						for(int r = 0; r < tab_ind.getRowCount(); r++){
							try{
								Double.parseDouble(rt_nbr(""+tab_ind.getValueAt(r, 8)));
								moy_puiss = moy_puiss + Double.parseDouble(rt_nbr(""+tab_ind.getValueAt(r, 8)));
							}catch (Exception zee) {
								cpt ++;
								
							}
							
						}
						moy_puiss = moy_puiss/(tab_ind.getRowCount()-cpt);
						System.out.println("Moyenne mesurée :"+moy_puiss+" = "+moy_puiss+" / ("+tab_ind.getRowCount()+" - "+cpt+")" );
						someSectionText = new Paragraph("-Moyenne de puissance mesuré "+df2.format(moy_puiss*1000)+"\n" );
						section1.add(someSectionText);
						
						someSectionText = new Paragraph("-Moyenne de Correction de consommation "+apres_vergule2(""+tab_rech.getValueAt(8, 2))+ "");
						section1.add(someSectionText);
						cpt = 0;
						moy_puiss = 0;
						for(int r = 0; r < tab_ind.getRowCount(); r++){
							
							try{
								Double.parseDouble(rt_nbr(""+tab_ind.getValueAt(r, 18)));
								moy_puiss = moy_puiss + Double.parseDouble(rt_nbr(""+tab_ind.getValueAt(r, 18)));
							}catch (Exception zee) {
								cpt ++;
							}
						}
						moy_puiss = moy_puiss/(tab_ind.getRowCount()-cpt);
						cpt = 0;
						someSectionText = new Paragraph("-Moyenne de Qte de chaleur mesuré "+df2.format(moy_puiss));
						section1.add(someSectionText);
						
						
//--------------------------------------------------------
						DecimalFormat df = new DecimalFormat ( ) ;
		  	            df.setMaximumFractionDigits (2) ;
						
						Section section2 = chapter2.addSection(title2);
						
						someSectionText = new Paragraph("1- En Sortie: ");
						section2.add(someSectionText);
						
						someSectionText = new Paragraph("Référence de garantie : "+ df.format(puiss_init));
						section2.add(someSectionText);
						
						  int dx = 4;
					
						PdfPTable t = new PdfPTable(dx);

						  t.setHorizontalAlignment(0);
						  t.setWidthPercentage(100);
					      t.setSpacingBefore(25);
					      t.setSpacingAfter(25);
					      
					     
					      String[] titre = {"F_Temps","Correction tst reference","Marge %","Delta kw"};
					      for(int i = 0; i < dx ; i++){
					      PdfPCell c1 = new PdfPCell(new Phrase(titre[i]));  
					      t.addCell(c1);
					      }
					      
					        double prc = 0;
			  	            String rstll = "";
					
					      for(int i = 0; i < def_dem.getRowCount();i++){
								try{
								    
										 t.addCell((String)def_dem.getValueAt(i, 0));	
										 t.addCell(apres_vergule2((String)def_ind.getValueAt(i, 17)));
										 try{
											 rstll = rt_nbr(def_ind.getValueAt(i, 17).toString());
											 System.out.println(rstll);
											 prc = (Double.parseDouble(""+rstll)/1000);
											 prc = (prc/puiss_init)*100  - 100;
											 t.addCell(df.format(prc) +" %");
										 }catch (Exception zz) {
											 t.addCell("N/ C");
										}
										
										 try{
										 prc = Double.parseDouble(rstll) - puiss_init*1000;
										 rstll = (String) df.format(prc);
										 t.addCell(rstll);
										 }catch (Exception zz) {
											 t.addCell("N/ C");
										}

								}catch (Exception e41){}
					      }
					      section2.add(t);
					      
					      someSectionText = new Paragraph("2- Taux de chaleur : ");
						  section2.add(someSectionText);
						  
						  someSectionText = new Paragraph("Teste de tolérance estimé a 1%.     Référence de Garantie "+df.format(qte_chal_init)+" kj/kw-hr");
						  section2.add(someSectionText);
					      
					      
						  
						  PdfPTable t2 = new PdfPTable(4);

						  t2.setHorizontalAlignment(0);
						  t2.setWidthPercentage(100);
					      t2.setSpacingBefore(25);
					      t2.setSpacingAfter(25);
					      
					     
					      String[] titre2 = {"F_Temps","Correction C_Spécifique","Marge %","Delta kw"};
					      for(int i = 0; i < 4 ; i++){
					      PdfPCell c1 = new PdfPCell(new Phrase(titre2[i]));  
					      t2.addCell(c1);
					      }
					      rstll = "";
					
					      for(int i = 0; i < def_dem.getRowCount();i++){
								try{
								    
										 t2.addCell((String)def_dem.getValueAt(i, 0));	
										 
										 try {
											t2.addCell(apres_vergule2((String)def_dem.getValueAt(i, 7)));
										} catch (Exception e2) {
											t2.addCell("N/ C");
										}
										 
									try {
										 rstll = rt_nbr(def_dem.getValueAt(i, 7).toString());
										 System.out.println(rstll);
										  
										 prc = (Double.parseDouble(""+rstll)/1000);
										 prc = (prc/qte_chal_init)*100  - 100;
										 
										 t2.addCell(df.format(prc)+" %");
										} catch (Exception e2) {
										 t2.addCell("N/ C");
										}
										
										try {
										 prc = Double.parseDouble(rstll) - qte_chal_init*1000;
										 rstll = (String) df.format(prc);
										 t2.addCell(rstll);
										} catch (Exception e2) {
										 t2.addCell("N/ C");
										}
										
										
									
								}catch (Exception e41){}
					      }
					    
					      section2.add(t2);
//----------------------------------------------------------------					  
					      Section section3 = chapter3.addSection(title3);			      
					      someSectionText = new Paragraph("1- Corrections en Sortie: ");
					      section3.add(someSectionText);
					      
					      PdfPTable t3 = new PdfPTable(8);

					      t3.setHorizontalAlignment(0);
					      t3.setWidthPercentage(100);
					      t3.setSpacingBefore(25);
					      t3.setSpacingAfter(25);
					      
					     
					      String[] titre3 = {"F_Temps","Température Amb","Pression Amb","Humidité Relative","Fréquence","Fuel composition (Wobbe/Co2)","Dégradation","Total Multiplcative"};
					      for(int i = 0; i < 8 ; i++){
					      PdfPCell c1 = new PdfPCell(new Phrase(titre3[i]));  
					      t3.addCell(c1);
					      }
					      rstll = "";
					      double mul = 0;
					      df.setMaximumFractionDigits(8);
					      for(int i = 0; i < def_dem.getRowCount();i++){
								try{
								    
									t3.addCell((String)def_dem.getValueAt(i, 0));
									t3.addCell((String)def_dem.getValueAt(i, 1));
									try {
										rstll = rt_nbr(def_dem.getValueAt(i, 1).toString());
										mul = Double.parseDouble(rstll);
										t3.addCell((String)def_dem.getValueAt(i, 2));
									} catch (Exception e2) {
										t3.addCell("N/ C");
									}
									
									
									try {
										rstll = rt_nbr(def_dem.getValueAt(i, 2).toString());
										mul = mul*Double.parseDouble(rstll);
										t3.addCell((String)def_dem.getValueAt(i, 3));
									} catch (Exception e2) {
										t3.addCell("N/ C");
									}
									
									try {
										rstll = rt_nbr(def_dem.getValueAt(i, 3).toString());
										mul = mul*Double.parseDouble(rstll);
										t3.addCell((String)def_dem.getValueAt(i, 4));
									} catch (Exception e2) {
										t3.addCell("N/ C");
									}
									
									try {
										rstll = rt_nbr(def_dem.getValueAt(i, 4).toString());
										mul = mul*Double.parseDouble(rstll);
										t3.addCell((String)def_dem.getValueAt(i, 5));
									} catch (Exception e2) {
										t3.addCell("N/ C");
									}
									
									try {
										rstll = rt_nbr(def_dem.getValueAt(i, 5).toString());
										mul = mul*Double.parseDouble(rstll);
										t3.addCell((String)def_dem.getValueAt(i, 6));
									} catch (Exception e2) {
										t3.addCell("N/ C");
									}
									
									try {
										rstll = rt_nbr(def_dem.getValueAt(i, 6).toString());
										mul = mul*Double.parseDouble(rstll);
										t3.addCell(""+df.format(mul));
									} catch (Exception e2) {
										t3.addCell("N/ C");
									}
									
									
									
								}catch (Exception e41){}
					      }
					      section3.add(t3);
	
					      try {
							  image2 = Image.getInstance(fl_chemin+".jpg");
							  image2.scaleAbsolute(700f, 280f);
						} catch (MalformedURLException e10) {
							// TODO Auto-generated catch block
							e10.printStackTrace();
						} catch (IOException e9) {
							// TODO Auto-generated catch block
							e9.printStackTrace();
						}
					     section3.add(image2);
					     try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//----------------------------------------------------------------								  
          Section section4 = chapter4.addSection(title4);	  
	
          PdfPTable t4 = new PdfPTable(7);

          t4.setHorizontalAlignment(0);
          t4.setWidthPercentage(100);
          t4.setSpacingBefore(25);
          t4.setSpacingAfter(25);
	      
	     
	      String[] titre4 = {"F_Temps","Puissance Mesurer","Qte Chaleur","Qte Chaleur Corriger","T° Amb Mésurer","Débit Gaz M&surer","PCI Mésurer"};
	      for(int i = 0; i < 7 ; i++){
	      PdfPCell c1 = new PdfPCell(new Phrase(titre4[i]));  
	      t4.addCell(c1);
	      }
	      rstll = "";
	
	      for(int i = 0; i < def_dem.getRowCount();i++){
				try{
					t4.addCell((String)def_dem.getValueAt(i, 0));	
					t4.addCell(apres_vergule2((String)def_ind.getValueAt(i, 8)));
					t4.addCell(apres_vergule2((String)def_ind.getValueAt(i, 18)));
					t4.addCell(apres_vergule2((String)def_ind.getValueAt(i, 19)));
					t4.addCell(apres_vergule2((String)def_ind.getValueAt(i, 2)));
					t4.addCell(apres_vergule2((String)def_ind.getValueAt(i, 6)));
					t4.addCell(""+def_ind.getValueAt(i, 11));
				}catch (Exception e41){
					
					t4.addCell((String)def_dem.getValueAt(i, 0));	
					t4.addCell("N/ C");
					t4.addCell("N/ C");
					t4.addCell("N/ C");
					
				}
	      }
	    
	      section4.add(t4);
          
	      someSectionText = new Paragraph("2- Conditions de base et Spécification des performances: \n" +
	      		"Les résultats des essais sur les trois tranches de production seront combinés " +
	      		"mathématiquement pour l'obtention des résultats relatifs à la centrale.\n \n" +
	      		"  Il est entendu par conditions de base; les conditions de référence auxquelles les " +
	      		"performances (puissance nette et consommation spécifique) sont calculées et " +
	      		"corrigées (ou rapportées) et qui sont:\n" +
	      		"- Charge à l' état stable: en base.\n" +
	      		"- Température ambiante : 35°C. \n" +
	      		"- Pression ambiante : 1.0 13Bar. \n" +
	      		"- Humidité relative : 73%.\n" +
	      		"- Température eau de mer: 23°C.\n" +
	      		"- Température de Recirculation Entrée Chaudière = 51 °C.\n" +
	      		"- Fréquence Alternateur : 50Hz. \n" +
	      		"- Facteur de puissance : 0.9 (retard).\n" +
	      		"- Carburant: Gaz Naturel.\n" +
	      		"- Pouvoir calorifique inferieur (PCI) :45720KJ/Kg. \n" +
	      		"- Injection d'eau dans la TG : 0 kg/h.\n" +
	      		"- Composition du combustible (mole %): N2=4.59, CO2=0.24, CH4=85.3, C2H6=7.29, 3H8=1.73, C4H10=0.66, C5H12=0.19.");
	      section4.add(someSectionText);
          
          
          
//----------------------------------------------------------------
					    document.add(section1);
					    document.add(section2);
					    document.add(section3);
					    document.add(section4);
				
					    
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
					}else{
					    JOptionPane.showMessageDialog(null,"Veillez verifier le mot de passe Svp !!","Information",JOptionPane.WARNING_MESSAGE);
					}
		 		
		 	
		 		
		 		
		 		
		 		
		 	}
		 });
		 btnNewButton.setBounds(858, 25, 67, 67);
		 panel_1.add(btnNewButton);
		 
		 JButton button_1 = new JButton("");
		 button_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		dt_D.setDate(new Date());
		 		dt_F.setDate(new Date());
		 		
		 		ch_ann.setSelected(false);
		 		ch_base.setSelected(false);
		 		ch_graph.setSelected(false);
		 		ch_que_correction.setSelected(false);
		 		
		 		cb_ann.setSelectedIndex(0);
		 		cb_frag.setSelectedIndex(0);
		 		cb_gen.setSelectedIndex(0);
		 		cb_h_d.setSelectedIndex(0);
		 		cb_h_f.setSelectedIndex(0);
		 		cb_m_d.setSelectedIndex(0);
		 		cb_m_f.setSelectedIndex(0);
		 		cb_s_d.setSelectedIndex(0);
		 		cb_s_f.setSelectedIndex(0);
		 		
		 		def_tR = new DefaultTableModel();
        		def_tR.setColumnIdentifiers(t_tab_Rech);
        		def_dem = new DefaultTableModel();
        		def_ind = new DefaultTableModel();
        		def_ind.setColumnIdentifiers(t_tab_ind);
        		
        		
        		if(lecture.gaz) def_dem.setColumnIdentifiers(t_tab_dem); else def_dem.setColumnIdentifiers(t_tab_dem2);
        		dataset.removeAllSeries();
        		pop = new TimeSeries("Correction");
        		pop1 = new TimeSeries("Qte Chaleur");
       		  	pop2 = new TimeSeries("Référence");
       		  	
       		    tab_dem.setModel(def_dem);
		        tab_ind.setModel(def_ind);
		        tab_rech.setModel(def_tR);
		        
		         dataset.addSeries(pop);
		      	 dataset.addSeries(pop1);
		      	 dataset.addSeries(pop2);
		      	 
		 		
		 	}
		 });
		 button_1.setIcon(new ImageIcon(Correction_Consommation.class.getResource("/icones/vider_RS.jpg")));
		 button_1.setBounds(935, 25, 67, 67);
		 panel_1.add(button_1);
		 
		 JLabel lblPuissanceDeRfrence = new JLabel("Puissance de r\u00E9f\u00E9rence");
		 lblPuissanceDeRfrence.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblPuissanceDeRfrence.setBounds(468, 11, 175, 14);
		 panel_1.add(lblPuissanceDeRfrence);
		 
		 JLabel lblConsommationDeRfrence = new JLabel("Consommation de R\u00E9f\u00E9rence");
		 lblConsommationDeRfrence.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblConsommationDeRfrence.setBounds(468, 51, 184, 14);
		 panel_1.add(lblConsommationDeRfrence);
		 
		 tf_puiss = new JTextField();
		 tf_puiss.setText("379.85");
		 tf_puiss.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		
		 		String dtj = "";
				if(tf_puiss.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_puiss.getText().toString());
					}catch(Exception exp){
						dtj = tf_puiss.getText();
						tf_puiss.setText(dtj.substring(0,tf_puiss.getText().toString().length()-1));
					}
				}
		 		
		 	}
		 });
		 tf_puiss.setFont(new Font("Verdana", Font.PLAIN, 11));
		 tf_puiss.setBounds(527, 26, 105, 20);
		 panel_1.add(tf_puiss);
		 tf_puiss.setColumns(10);
		 
		 tf_consom = new JTextField();
		 tf_consom.setText("6.286");
		 tf_consom.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		
		 		String dtj = "";
				if(tf_consom.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_consom.getText().toString());
					}catch(Exception exp){
						dtj = tf_consom.getText();
						tf_consom.setText(dtj.substring(0,tf_consom.getText().toString().length()-1));
					}
				}
		 		
		 	}
		 });
		 tf_consom.setFont(new Font("Verdana", Font.PLAIN, 11));
		 tf_consom.setBounds(527, 67, 105, 20);
		 panel_1.add(tf_consom);
		 tf_consom.setColumns(10);
		 
	
		 ch_base.setFont(new Font("Verdana", Font.PLAIN, 11));
		 ch_base.setBounds(638, 22, 95, 23);
		 panel_1.add(ch_base);
		 
		
		 ch_graph.setFont(new Font("Verdana", Font.PLAIN, 9));
		 ch_graph.setBounds(638, 65, 133, 23);
		 panel_1.add(ch_graph);
		 
		 JSeparator separator_2 = new JSeparator();
		 separator_2.setOrientation(SwingConstants.VERTICAL);
		 separator_2.setBounds(774, 11, 21, 107);
		 panel_1.add(separator_2);
		 
		
		 ch_que_correction.setFont(new Font("Verdana", Font.PLAIN, 9));
		 ch_que_correction.setBounds(638, 42, 134, 24);
		 panel_1.add(ch_que_correction);
		 
		 JSeparator separator_3 = new JSeparator();
		 separator_3.setBounds(474, 93, 290, 8);
		 panel_1.add(separator_3);
		 
		 JLabel lblAnne = new JLabel("Ann\u00E9e");
		 lblAnne.setFont(new Font("Verdana", Font.PLAIN, 11));
		 lblAnne.setBounds(468, 98, 46, 20);
		 panel_1.add(lblAnne);
		 
		
		 cb_ann.setFont(new Font("Verdana", Font.PLAIN, 11));
		 
		 new Thread(new Runnable(){
			public void run() {
                for(int i = 1; i < 21; i++){
                	cb_ann.addItem(i);
                }
			}
		 }).start();
		 
		 cb_ann.setBounds(581, 98, 51, 20);
		 panel_1.add(cb_ann);
		 
		 ch_ann.setFont(new Font("Verdana", Font.PLAIN, 10));
		 ch_ann.setBounds(638, 95, 133, 23);
		 panel_1.add(ch_ann);
		 
		 JPanel panel_3 = new JPanel();
		 panel_3.setBorder(new TitledBorder(null, "Correction lier a l'espace temporel demander", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_3.setBounds(10, 151, 334, 207);
		 
		 
		 tab_rech = new JTable(d_tab_rech,t_tab_Rech);	
			
			tab_rech.setRowHeight(25);
			JScrollPane scrollPane_2 = new JScrollPane(tab_rech);
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(scrollPane_2);
		 
		 
		 add(panel_3);
		 
		 JPanel panel_4 = new JPanel();
		 panel_4.setBorder(new TitledBorder(null, "Historique de correction par fragments temporel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_4.setBounds(10, 369, 1012, 120);
		 
		 tab_dem = new JTable(d_tab_dem ,t_tab_dem );	
			
			tab_dem .setRowHeight(25);
			JScrollPane scrollPane_3 = new JScrollPane(tab_dem );
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(scrollPane_3);
		 
		 add(panel_4);
		 
		 
		 JPanel panel_42 = new JPanel();
		 panel_42.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historique des valeurs mesurer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_42.setBounds(10, 530, 1012, 201);
		 
		 
		 tab_ind = new JTable(d_tab_ind ,t_tab_ind );	
		 tab_ind .setRowHeight(25);
			//JScrollPane scrollPane_3 = new JScrollPane(tab_dem );
			
			p_table2 = new JPanel(new BorderLayout());
			p_table2.setPreferredSize(new Dimension(1800, 100));
			panel_42.setLayout(new BorderLayout(0, 0));
			JScrollPane  scrollPane_12 = new JScrollPane(p_table2);
			scrollPane_12.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			panel_42.add(scrollPane_12, BorderLayout.CENTER);


			
			
			p_table2.add(new JScrollPane(tab_ind),BorderLayout.CENTER);

		add(panel_42);	
		 
		
		 
		 for(int i = 0; i<60; i++){
			 if(i<10)cb_s_d.addItem("0"+i);
			 else cb_s_d.addItem(""+i);
		 }
		 

		  chart = ChartFactory.createTimeSeriesChart("Correction consommation spécifique","hh:mm:ss","kj/kW-hr",dataset,true,true,false);		 
		 ChartPanel panel_5 = new ChartPanel(chart);
		 panel_5.setBorder(new TitledBorder(null, "Graph de valeur de correction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_5.setBounds(354, 151, 668, 207);
		 add(panel_5);
		 panel_5.setLayout(new BorderLayout(0, 0));
	}

public void writeChart(String filename, int x, int y, JFreeChart chart )
throws Exception
{	
	
String fileExt = filename.substring(filename.indexOf("."), filename.length());
if (fileExt.equalsIgnoreCase("jpg") || fileExt.equalsIgnoreCase("jpeg"))
	ChartUtilities.saveChartAsJPEG(new File(filename), chart, x, y);		
else
	ChartUtilities.saveChartAsPNG(new File(filename), chart, x, y);

}



String rt_nbr(String str){
    String rt = str.replaceAll(",", ".");
    String rt1 = "";
	for(int i = 0; i< rt.length(); i++){
		try{
			if(rt.substring(i, i+1).equals("0")||rt.substring(i, i+1).equals("1")||rt.substring(i, i+1).equals("2")||rt.substring(i, i+1).equals("3")||rt.substring(i, i+1).equals("4")||rt.substring(i, i+1).equals("5")||rt.substring(i, i+1).equals("6")||rt.substring(i, i+1).equals("7")||rt.substring(i, i+1).equals("8")||rt.substring(i, i+1).equals("9")||rt.substring(i, i+1).equals(".")||rt.substring(i, i+1).equals("-")){
				rt1 = rt1 + (String) rt.substring(i, i+1);
			}
		}catch (Exception e) {
			
		}
	}
	return rt1;
}

String apres_vergule2(String str){
	int i = str.indexOf(",");
	String ret = str.substring(0,i+3);
	return ret;
}
}
