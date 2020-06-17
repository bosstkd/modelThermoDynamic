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


public class Prevision_puissance extends JPanel {
	
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
		 private JTextField tf_puis_auxi;
		 
public Prevision_puissance() {

		 setLayout(null);
		 
		
		 
		 JPanel panel_3 = new JPanel();
		 panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Correction lier au donn\u00E9es ins\u00E9rer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		 panel_3.setBounds(478, 11, 484, 450);
		 
		 
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
		 lblTempratureEauDe.setBounds(20, 47, 180, 14);
		 add(lblTempratureEauDe);
		 
		 JLabel lblPressionAmbiante = new JLabel("Pression Ambiante");
		 lblPressionAmbiante.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPressionAmbiante.setBounds(20, 82, 180, 14);
		 add(lblPressionAmbiante);
		 
		 JLabel lblHumidit = new JLabel("Humidit\u00E9");
		 lblHumidit.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblHumidit.setBounds(20, 117, 180, 14);
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
		 tf_tmp_e_m.setBounds(201, 47, 129, 20);
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
		 tf_prs_amb.setBounds(201, 82, 129, 20);
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
		 tf_hmd.setBounds(201, 117, 129, 20);
		 add(tf_hmd);
		 tf_hmd.setColumns(10);
		 
		 JButton btnCorrection = new JButton("Correction");
		 btnCorrection.setFont(new Font("Vani", Font.PLAIN, 14));
		 btnCorrection.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	
		 		if(tf_tmp_amb.getText().equals("")||tf_tmp_e_m.getText().equals("")||tf_prs_amb.getText().equals("")||tf_hmd.getText().equals("")||tf_freq.getText().equals("")||tf_wobb.getText().equals("")||tf_co2.getText().equals("")||tf_puis_gen.getText().equals("")||tf_fact_puis.getText().equals("")||tf_inj.getText().equals("")){
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
		 		    double puis_auxi = Double .parseDouble(tf_puis_auxi.getText());
		 		    

		 		if(tmp==0 && tmp_em == 0 && press == 0 && humid == 0 && freq == 0){
			    	  JOptionPane.showMessageDialog(null,"Aucune correction, facteurs incorrectes","Information",JOptionPane.INFORMATION_MESSAGE);
		         }else{
		         String lb_t_amb = "";
		         String lb_pres = "";
		         double lb_c46 = 0;
		         double lb_c35 = 0;
		         double lb_c15 =0;
		         double lb_c_01 = 0;
		         String lb_h = "";
		         String lb_c46_f = "";
 		         String lb_c35_f = "";
 		         String lb_c15_f = "";
 		         String lb_c_01_f = "";
 		         String lb_f = "";
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
		           double val_nom_p_alt = fonction.perte_altern_p_g(0.9, rend_altern);
		           double val_cor_p_alt = val_nom_p_alt - corr_p_alt;
		           def_tR.addRow(new Object[]{"Correction Prt altern",df.format(corr_p_alt),df.format(val_cor_p_alt)}); 
		           //-----------Correction injection d'eau-----------------  
		            double corr_inj_eau = /*fonction.inj_p_g(inj_eau)*/ 1;
		            double val_cor_inj = 1/corr_inj_eau;
		            def_tR.addRow(new Object[]{"C_injection eau",df.format(corr_inj_eau),df.format(val_cor_inj)}); 
		           //-----------Correction heure de flame-----------------  
	        	    double corr_h_d_f = fonction.hrf_p_g(Double.parseDouble(tf_h_df.getText()));
	        	    double val_cor_hdf =1+ (0.998 - corr_h_d_f);
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
			           def_tR.addRow(new Object[]{"C_injection eau",df.format(corr_perte_altern),df.format(val_cor_p_alt)}); 
			        //-----------Correction PCI-----------------------------
			           double corr_pci  = fonction.pci_p_fo(pci);
			           double val_cor_pci = 1/corr_pci;
			           def_tR.addRow(new Object[]{"Correction PCI",df.format(corr_pci),val_cor_pci}); 
			           //-----------Correction heure de flame-----------------  
			           double corr_h_d_f = fonction.hrf_p_g(Double.parseDouble(tf_h_df.getText()));
			   	    	double val_cor_hdf =1+ (corr_h_d_f - 0.998);
		        	    def_tR.addRow(new Object[]{"C_degradation",df.format(corr_h_d_f),df.format(val_cor_hdf)}); 
				        //-----------Correction Global-----------------        
		                double tphh2 = Math.abs(((rend_altern*1000)-val_cor_p_alt-val_cor_tem + puis_auxi)*(val_cor_tmp*val_cor_press*val_cor_humid*val_cor_pci*val_cor_freq*val_cor_inj)*val_cor_hdf);
				       def_tR.addRow(new Object[]{"C_Globale","_",df.format(tphh2)});
		 		         
  			      		tab_rech.setModel(def_tR);
			 	     //------------------fin hna te3 gasoil----------------------  
		         }
		         
		         
		      
		         }	
		 		}
		 		
		 		
		 		
		 	}
		 });
		 btnCorrection.setBounds(355, 365, 113, 84);
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
		 lblFrequence.setBounds(20, 152, 89, 20);
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
		 tf_freq.setBounds(201, 152, 129, 20);
		 add(tf_freq);
		 tf_freq.setColumns(10);
		 
		 JLabel lblWobbIndex = new JLabel("Wobb Index");
		 lblWobbIndex.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblWobbIndex.setBounds(20, 187, 89, 14);
		 add(lblWobbIndex);
		 
		 JLabel lblNewLabel = new JLabel("% CO2");
		 lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblNewLabel.setBounds(20, 222, 46, 14);
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
		 tf_wobb.setBounds(201, 187, 129, 20);
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
		 tf_co2.setBounds(201, 222, 129, 20);
		 add(tf_co2);
		 tf_co2.setColumns(10);
		 
		 JLabel lblPuissanceGnrateur = new JLabel("Puissance G\u00E9n\u00E9rateur");
		 lblPuissanceGnrateur.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPuissanceGnrateur.setBounds(20, 257, 180, 19);
		 add(lblPuissanceGnrateur);
		 
		 JLabel lblFacteurDePuissance = new JLabel("Facteur de puissance");
		 lblFacteurDePuissance.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblFacteurDePuissance.setBounds(21, 292, 170, 20);
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
		 tf_puis_gen.setBounds(201, 257, 129, 20);
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
		 tf_fact_puis.setBounds(201, 292, 129, 20);
		 add(tf_fact_puis);
		 tf_fact_puis.setColumns(10);
		 
		 JLabel lblInjectionDeau = new JLabel("Injection d'eau");
		 lblInjectionDeau.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblInjectionDeau.setBounds(20, 327, 170, 20);
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
		 tf_inj.setBounds(201, 327, 129, 20);
		 add(tf_inj);
		 tf_inj.setColumns(10);
		 
		 JLabel lblPci = new JLabel("PCI");
		 lblPci.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPci.setBounds(20, 364, 99, 20);
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
		 tf_pci.setBounds(201, 364, 129, 20);
		 add(tf_pci);
		 tf_pci.setColumns(10);
		 
		 JLabel lblHeureDeFlame = new JLabel("Heure de Flame");
		 lblHeureDeFlame.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblHeureDeFlame.setBounds(20, 399, 129, 20);
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
		 tf_h_df.setBounds(201, 399, 129, 20);
		 add(tf_h_df);
		 tf_h_df.setColumns(10);
		 
		 JLabel lblPuissanceAuxiliaire = new JLabel("Puissance Auxiliaire");
		 lblPuissanceAuxiliaire.setFont(new Font("Verdana", Font.PLAIN, 13));
		 lblPuissanceAuxiliaire.setBounds(20, 430, 148, 24);
		 add(lblPuissanceAuxiliaire);
		 
		 tf_puis_auxi = new JTextField();
		 tf_puis_auxi.setBounds(201, 430, 129, 20);
		 add(tf_puis_auxi);
		 tf_puis_auxi.setColumns(10);
		 setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tf_tmp_amb, tf_tmp_e_m, tf_prs_amb, tf_hmd, tf_freq, tf_wobb, tf_co2, tf_puis_gen, tf_fact_puis, tf_inj, tf_pci, tf_h_df, btnCorrection, panel_3, scrollPane_2, tab_rech, lblTempratureAmbiante, lblTempratureEauDe, lblPressionAmbiante, lblHumidit, btnVider, lblFrequence, lblWobbIndex, lblNewLabel, lblPuissanceGnrateur, lblFacteurDePuissance, lblInjectionDeau, lblPci, lblHeureDeFlame}));

	}
}
