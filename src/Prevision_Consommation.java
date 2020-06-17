import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;


public class Prevision_Consommation extends JPanel {
	
	     JTable tab_rech;
		 DefaultTableModel def_tR;
		 Object [][] d_tab_rech= new Object[15][10];
		 String[] t_tab_Rech = {"Correction","Mesurer","Valeur"};
		
	
		 
		 
		 TimeSeries pop = new TimeSeries("Correction");
	
		 TimeSeriesCollection dataset = new TimeSeriesCollection();
		 static JTextField tf_tmp_amb;
		 static JTextField tf_tmp_e_m;
		 static JTextField tf_prs_amb;
		 static JTextField tf_hmd;
		 static JTextField tf_freq;
		 static JTextField tf_wobb;
		 static JTextField tf_co2;
		 static JTextField tf_puis_gen;
		 static JTextField tf_fact_puis;
		 static JTextField tf_inj;
		 static JTextField tf_pci;
		 static JTextField tf_h_df;
		 private JTextField tf_db_g;
		 private JTextField tf_puis_auxi;
		 
public Prevision_Consommation() {

		 setLayout(null);
		 
		
		 
		 JPanel panel_3 = new JPanel();
		 panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Correction lier au donn\u00E9es ins\u00E9rer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_3.setBounds(478, 11, 484, 425);
		 
		 
		 tab_rech = new JTable(d_tab_rech,t_tab_Rech);	
		 tab_rech.setFont(new Font("Verdana", Font.PLAIN, 14));
			
			tab_rech.setRowHeight(25);
			JScrollPane scrollPane_2 = new JScrollPane(tab_rech);
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(scrollPane_2);
		 
		 
		 add(panel_3);
		 
		 JLabel lblTempratureAmbiante = new JLabel("Temp\u00E9rature Ambiante");
		 lblTempratureAmbiante.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblTempratureAmbiante.setBounds(20, 11, 180, 20);
		 add(lblTempratureAmbiante);
		 
		 JLabel lblTempratureEauDe = new JLabel("Temp\u00E9rature eau de mer");
		 lblTempratureEauDe.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblTempratureEauDe.setBounds(20, 42, 180, 14);
		 add(lblTempratureEauDe);
		 
		 JLabel lblPressionAmbiante = new JLabel("Pression Ambiante");
		 lblPressionAmbiante.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPressionAmbiante.setBounds(20, 77, 180, 14);
		 add(lblPressionAmbiante);
		 
		 JLabel lblHumidit = new JLabel("Humidit\u00E9");
		 lblHumidit.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblHumidit.setBounds(20, 113, 180, 14);
		 add(lblHumidit);
		 
		 tf_tmp_amb = new JTextField();
		 tf_tmp_amb.setFont(new Font("Verdana", Font.PLAIN, 14));
		 tf_tmp_amb.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		
		 		
		 		String dtj = "";
				if(tf_tmp_amb.getText().equals("")){
					 
				}else{
					try{
						double i = Double.parseDouble(tf_tmp_amb.getText().toString());
					}catch(Exception exp){
						dtj = tf_tmp_amb.getText();
						tf_tmp_amb.setText(dtj.substring(0,tf_tmp_amb.getText().toString().length()-1));
					}
				}
		 		
		 		
		 	}
		 });
		 tf_tmp_amb.setBounds(201, 12, 129, 20);
		 add(tf_tmp_amb);
		 tf_tmp_amb.setColumns(10);
		 
		 tf_tmp_e_m = new JTextField();
		 tf_tmp_e_m.setFont(new Font("Verdana", Font.PLAIN, 14));
		 tf_tmp_e_m.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_tmp_e_m.getText().equals("")){
					 
				}else{
					try{
						double i = Double.parseDouble(tf_tmp_e_m.getText().toString());
					}catch(Exception exp){
						dtj = tf_tmp_e_m.getText();
						tf_tmp_e_m.setText(dtj.substring(0,tf_tmp_e_m.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_tmp_e_m.setBounds(201, 42, 129, 20);
		 add(tf_tmp_e_m);
		 tf_tmp_e_m.setColumns(10);
		 
		 tf_prs_amb = new JTextField();
		 tf_prs_amb.setFont(new Font("Verdana", Font.PLAIN, 14));
		 tf_prs_amb.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_prs_amb.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_prs_amb.getText().toString());
					}catch(Exception exp){
						dtj = tf_prs_amb.getText();
						tf_prs_amb.setText(dtj.substring(0,tf_prs_amb.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_prs_amb.setBounds(201, 75, 129, 20);
		 add(tf_prs_amb);
		 tf_prs_amb.setColumns(10);
		 
		 tf_hmd = new JTextField();
		 tf_hmd.setFont(new Font("Verdana", Font.PLAIN, 14));
		 tf_hmd.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_hmd.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_hmd.getText().toString());
					}catch(Exception exp){
						dtj = tf_hmd.getText();
						tf_hmd.setText(dtj.substring(0,tf_hmd.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_hmd.setBounds(201, 110, 129, 20);
		 add(tf_hmd);
		 tf_hmd.setColumns(10);
		 
		 JButton btnCorrection = new JButton("Correction");
		 btnCorrection.setFont(new Font("Vani", Font.PLAIN, 14));
		 btnCorrection.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	
		 		if(tf_tmp_amb.getText().equals("")||tf_prs_amb.getText().equals("")||tf_hmd.getText().equals("")||tf_freq.getText().equals("")||tf_wobb.getText().equals("")||tf_co2.getText().equals("")){
			    	  JOptionPane.showMessageDialog(null,"Veillez verifier les données insérer svp !?","Attention",JOptionPane.WARNING_MESSAGE);
		 		}else{
		 		def_tR = new DefaultTableModel();
      		def_tR.setColumnIdentifiers(t_tab_Rech);
     
		 		    
		 		 DecimalFormat df = new DecimalFormat ( ) ;
                 df.setMaximumFractionDigits (8) ;
		 		
		 		    double tmp = Double.parseDouble(tf_tmp_amb.getText());
			        double tmp_em = Double.parseDouble(tf_tmp_e_m.getText());
			        double press = Double.parseDouble(tf_prs_amb.getText());
			        double humid = Double.parseDouble(tf_hmd.getText());
			        double freq = Double.parseDouble(tf_freq.getText());
			        double wobb = Double.parseDouble(tf_wobb.getText());
			        double Gco2 = Double.parseDouble(tf_co2.getText());
			        double rend_altern = Double.parseDouble(tf_puis_gen.getText());
			        double fact_puis = Double.parseDouble(tf_fact_puis.getText());
		 		    double inj_eau = Double.parseDouble(tf_inj.getText());
		 		    double pci = Double.parseDouble(tf_pci.getText());
		 		    double debit_g = Double.parseDouble(tf_db_g.getText());
		 		    double hdf = Double.parseDouble(tf_h_df.getText());
		 		    double puis_auxi = Double.parseDouble(tf_puis_auxi.getText());
		 		if(tmp==0 && press == 0 && humid == 0 && freq == 0){
			    	  JOptionPane.showMessageDialog(null,"Aucune correction, facteurs incorrectes","Information",JOptionPane.INFORMATION_MESSAGE);
		         }else{
		         String lb_t_amb = "";
		         String lb_pres = "";
		         String lb_c46 = "";
		         String lb_c35 = "";
		         String lb_c15 = "";
		         String lb_c_01 = "";
		         String lb_h = "";
		         String lb_c46_f = "";
		         String lb_c35_f = "";
		         String lb_c15_f = "";
		         String lb_c_01_f = "";
		         String lb_f = "";
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
			           double cor_hdf = fonction.hdf_c_g(Double.parseDouble(tf_h_df.getText()));
			           double val_cor_hdf = 1+(0.999-cor_hdf);
			           def_tR.addRow(new Object[]{"C_heure_F",df.format(cor_hdf),val_cor_hdf});
			          
			           
			           //-----------Correction Global-----------------        
			        
			             double c_ch =   debit_g*3600*pci/0.80075;
			             c_ch = c_ch*val_cor_t*val_cor_p*val_cor_h*val_cor_f*val_cor_wco2;
			        	
			             
			             
			             
			             double  tphh2 = (c_ch/(fonction.corr_puissance_g(tmp, press, humid, tmp_em, freq, wobb, Gco2, fact_puis, rend_altern, hdf, puis_auxi)/(1+(0.998-fonction.hrf_p_g(hdf)))))*val_cor_hdf;
			           def_tR.addRow(new Object[]{"Corr Puiss","___",df.format(fonction.corr_puissance_g(tmp, press, humid, tmp_em, freq, wobb, Gco2, fact_puis, rend_altern, hdf, puis_auxi))});
			           def_tR.addRow(new Object[]{"Cons chaleur","____",df.format(c_ch)});
			           def_tR.addRow(new Object[]{"Correction G","____",df.format(tphh2)});
		 		         
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
				        
			             double c_ch =   debit_g*3600*pci;
			             c_ch = c_ch*val_cor_t*val_cor_p*val_cor_h*val_cor_f*val_cor_pci;
			             def_tR.addRow(new Object[]{"Qte de chaleur","____",df.format(c_ch)});
			             
			             //corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi)
			             double  tphh2 = (c_ch/(fonction.corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi)/(1+(fonction.hrf_p_g(hdf)-0.998))))*val_cor_hdf;
			           
			             
				      def_tR.addRow(new Object[]{"Corr puissance","____",df.format(fonction.corr_puissance_fo( tmp,  press,  humid,  tmp_em,  freq, inj_eau, pci, fact_puis, rend_altern, hdf, puis_auxi))});
			          def_tR.addRow(new Object[]{"Correction G","____",df.format(tphh2)});
		 		         
			      	tab_rech.setModel(def_tR);
			        	
			 	     //------------------fin hna te3 gasoil----------------------  
		         }
		         
		         
		      
		         }	
		 		}

		 		
		 	}
		 });
		 btnCorrection.setBounds(355, 390, 113, 93);
		 add(btnCorrection);
		 
		 JButton btnVider = new JButton("Vider");
		 btnVider.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		def_tR = new DefaultTableModel();
        		def_tR.setColumnIdentifiers(t_tab_Rech);
		 		
		 		tf_hmd.setText("");
		 		tf_prs_amb.setText("");
		 		tf_tmp_amb.setText("");
		 		tf_tmp_e_m.setText("");
		 		tf_freq.setText("");
		 		tf_wobb.setText("");
		 		tf_co2.setText("");
		 		tf_fact_puis.setText("");
		 		tf_puis_gen.setText("");
		 		
		 		tab_rech.setModel(def_tR);
		 	}
		 });
		 btnVider.setFont(new Font("Vani", Font.PLAIN, 14));
		 btnVider.setBounds(355, 11, 113, 46);
		 add(btnVider);
		 
		 JLabel lblFrequence = new JLabel("Frequence");
		 lblFrequence.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblFrequence.setBounds(20, 145, 89, 20);
		 add(lblFrequence);
		 
		 tf_freq = new JTextField();
		 tf_freq.setFont(new Font("Verdana", Font.PLAIN, 14));
		 tf_freq.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		
		 		String dtj = "";
				if(tf_freq.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_freq.getText().toString());
					}catch(Exception exp){
						dtj = tf_freq.getText();
						tf_freq.setText(dtj.substring(0,tf_freq.getText().toString().length()-1));
					}
				}
		 		
		 	}
		 });
		 tf_freq.setBounds(201, 145, 129, 20);
		 add(tf_freq);
		 tf_freq.setColumns(10);
		 
		 JLabel lblWobbIndex = new JLabel("Wobb Index");
		 lblWobbIndex.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblWobbIndex.setBounds(20, 176, 89, 14);
		 add(lblWobbIndex);
		 
		 JLabel lblNewLabel = new JLabel("% CO2");
		 lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblNewLabel.setBounds(20, 211, 46, 14);
		 add(lblNewLabel);
		 
		 tf_wobb = new JTextField();
		 tf_wobb.setFont(new Font("Verdana", Font.PLAIN, 14));
		 tf_wobb.addKeyListener(new KeyAdapter() {
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_wobb.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_wobb.getText().toString());
					}catch(Exception exp){
						dtj = tf_wobb.getText();
						tf_wobb.setText(dtj.substring(0,tf_wobb.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_wobb.setBounds(201, 180, 129, 20);
		 add(tf_wobb);
		 tf_wobb.setColumns(10);
		 
		 tf_co2 = new JTextField();
		 tf_co2.setFont(new Font("Verdana", Font.PLAIN, 14));
		 tf_co2.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_co2.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_co2.getText().toString());
					}catch(Exception exp){
						dtj = tf_co2.getText();
						tf_co2.setText(dtj.substring(0,tf_co2.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_co2.setBounds(201, 215, 129, 20);
		 add(tf_co2);
		 tf_co2.setColumns(10);
		 
		 JLabel lblPuissanceGnrateur = new JLabel("Puissance G\u00E9n\u00E9rateur");
		 lblPuissanceGnrateur.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPuissanceGnrateur.setBounds(20, 246, 180, 19);
		 add(lblPuissanceGnrateur);
		 
		 JLabel lblFacteurDePuissance = new JLabel("Facteur de puissance");
		 lblFacteurDePuissance.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblFacteurDePuissance.setBounds(21, 281, 170, 20);
		 add(lblFacteurDePuissance);
		 
		 tf_puis_gen = new JTextField();
		 tf_puis_gen.setFont(new Font("Verdana", Font.PLAIN, 13));
		 tf_puis_gen.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_puis_gen.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_puis_gen.getText().toString());
					}catch(Exception exp){
						dtj = tf_puis_gen.getText();
						tf_puis_gen.setText(dtj.substring(0,tf_puis_gen.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_puis_gen.setBounds(201, 250, 129, 20);
		 add(tf_puis_gen);
		 tf_puis_gen.setColumns(10);
		 
		 tf_fact_puis = new JTextField();
		 tf_fact_puis.setFont(new Font("Verdana", Font.PLAIN, 13));
		 tf_fact_puis.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_fact_puis.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_fact_puis.getText().toString());
					}catch(Exception exp){
						dtj = tf_fact_puis.getText();
						tf_fact_puis.setText(dtj.substring(0,tf_fact_puis.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_fact_puis.setBounds(201, 285, 129, 20);
		 add(tf_fact_puis);
		 tf_fact_puis.setColumns(10);
		 
		 JLabel lblInjectionDeau = new JLabel("Injection d'eau");
		 lblInjectionDeau.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblInjectionDeau.setBounds(20, 316, 170, 20);
		 add(lblInjectionDeau);
		 
		 tf_inj = new JTextField();
		 tf_inj.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		
		 		String dtj = "";
				if(tf_inj.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_inj.getText().toString());
					}catch(Exception exp){
						dtj = tf_inj.getText();
						tf_inj.setText(dtj.substring(0,tf_inj.getText().toString().length()-1));
					}
				}
		 		
		 	}
		 });
		 tf_inj.setFont(new Font("Verdana", Font.PLAIN, 13));
		 tf_inj.setBounds(201, 320, 129, 20);
		 add(tf_inj);
		 tf_inj.setColumns(10);
		 
		 JLabel lblPci = new JLabel("PCI");
		 lblPci.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPci.setBounds(20, 390, 99, 20);
		 add(lblPci);
		 
		 tf_pci = new JTextField();
		 tf_pci.setFont(new Font("Verdana", Font.PLAIN, 13));
		 tf_pci.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		
		 		String dtj = "";
				if(tf_pci.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_pci.getText().toString());
					}catch(Exception exp){
						dtj = tf_pci.getText();
						tf_pci.setText(dtj.substring(0,tf_pci.getText().toString().length()-1));
					}
				}
		 		
		 	}
		 });
		 tf_pci.setBounds(201, 394, 129, 20);
		 add(tf_pci);
		 tf_pci.setColumns(10);
		 
		 JLabel lblHeureDeFlame = new JLabel("Heure de Flame");
		 lblHeureDeFlame.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblHeureDeFlame.setBounds(20, 425, 129, 20);
		 add(lblHeureDeFlame);
		 
		 tf_h_df = new JTextField();
		 tf_h_df.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyReleased(KeyEvent e) {
		 		String dtj = "";
				if(tf_h_df.getText().equals("")){
					
				}else{
					try{
						double i = Double.parseDouble(tf_h_df.getText().toString());
					}catch(Exception exp){
						dtj = tf_h_df.getText();
						tf_h_df.setText(dtj.substring(0,tf_h_df.getText().toString().length()-1));
					}
				}
		 	}
		 });
		 tf_h_df.setFont(new Font("Verdana", Font.PLAIN, 13));
		 tf_h_df.setBounds(201, 429, 129, 20);
		 add(tf_h_df);
		 tf_h_df.setColumns(10);
		 
		 JLabel lblNewLabel_1 = new JLabel("Debit du combustible");
		 lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblNewLabel_1.setBounds(20, 351, 171, 28);
		 add(lblNewLabel_1);
		 
		 tf_db_g = new JTextField();
		 tf_db_g.setBounds(201, 357, 129, 20);
		 add(tf_db_g);
		 tf_db_g.setColumns(10);
		 
		 JLabel lblPuisauxi = new JLabel("Puis_auxi");
		 lblPuisauxi.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPuisauxi.setBounds(20, 461, 152, 22);
		 add(lblPuisauxi);
		 
		 tf_puis_auxi = new JTextField();
		 tf_puis_auxi.setBounds(201, 463, 129, 20);
		 add(tf_puis_auxi);
		 tf_puis_auxi.setColumns(10);
		 setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tf_tmp_amb, tf_tmp_e_m, tf_prs_amb, tf_hmd, tf_freq, tf_wobb, tf_co2, tf_puis_gen, tf_fact_puis, tf_inj, tf_pci, tf_h_df, btnCorrection, panel_3, scrollPane_2, tab_rech, lblTempratureAmbiante, lblTempratureEauDe, lblPressionAmbiante, lblHumidit, btnVider, lblFrequence, lblWobbIndex, lblNewLabel, lblPuissanceGnrateur, lblFacteurDePuissance, lblInjectionDeau, lblPci, lblHeureDeFlame}));

	}
}
