import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.jmx.snmp.Timestamp;


public class excel {

	static int min = 0;
	static int heure = 0;
	
static void c(String chemin, String date_d, int hr, int mn ){
		
		Vector vct = new Vector();
		Vector vct2 = new Vector();
		Vector vct3 = new Vector();
		 int gg = 0;
		String str = "";
		
		 XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook(chemin);
			   XSSFSheet sheet = (XSSFSheet)wb.getSheetAt(0);
			   XSSFRow row = sheet.getRow(0);
			   XSSFCell cell = row.getCell((short) 0);
	       int i = 0;          
           boolean ts = true;
           
           
           
           
           //----------------------------------------
           
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
			      
			      Date newDate = new Date(date_d);
			      
                  //lecture.maxval = sheet.getLastRowNum();
			for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {   
	            for (Iterator cellIt = row.cellIterator(); cellIt.hasNext();) {
	            
	              cell = (XSSFCell) cellIt.next();	     
	              
	              
	              
	              if(ts)i++;
	              if(cell.getCellType()==1){
	            	  String x = cell.getStringCellValue().toUpperCase().replaceAll("'", ""); 
		              if(cell.getRowIndex()==0 && ts){
		            	  vct.addElement(x);
		              }
		              else if(cell.getRowIndex()==1){
		            	  vct2.addElement(x);
		              }
	              }
	              if(cell.getRowIndex()>1){
	            	  if(cell.getCellType()==0){
	            		  float x = (float) cell.getNumericCellValue();
	            		  vct3.addElement(x);
	            	  }else
	            		  if(cell.getCellType()==3){
   	    	            	  System.out.println("valeurs de cellule : "+cell.getStringCellValue());
	            			  vct3.addElement(cell.getStringCellValue());
	                     }
	            		  else
	                		 if (cell.getCellType()==1){
	    	            	  String x = cell.getStringCellValue().toUpperCase().replaceAll("'", ""); 
	            			  vct3.addElement(x);
	            		      } 
	              } 	              
	          }     	            
	            if(ts) ts = false;
	            
	            row = (XSSFRow) rowIt.next();
	            
	            if(cell.getRowIndex()==2){
	            	
	            	boolean bl = true;
	            	
	            	
	            	for(int j = 0; j < vct.size(); j++){
	            		//System.out.println(vct.elementAt(j)+"\t");
	            		try{
	            			vct.elementAt(j+1);
	            		}catch(Exception e){
	            			bl = false;
	            		}
	            	
	            	}
	            	

	            }
	           
          	 // System.out.println("ligne "+ gg++);

	            if(cell.getRowIndex()>=2){
	            	
	            	boolean bl = true;
	            	
	            	//boolean num = false;
	            	float test_f = 0;

	            	for(int j = 0; j < vct.size(); j++){
	            		
	            		try{
	            			vct.elementAt(j+1);
	            		}catch(Exception e){
	            			bl = false;
	            		}

	            	}	 
	            	
          		String req = "insert into COR_AOM_VOLN1 (T_AMB, HMD, P_AMB, T_EAU_MER, DBT_GAZ, P_GAZ, FREQ, PUIS, WOB_INDEX, INJ_EAU, TMP_GAZ, PCI, COMP_GAZ_CO2, FACT_PUIS, CONS_CHAL, HEURE_DE_F, PUIS_AUXI, GENERATEUR, HEURE, DEBIT_FUEL, BASE_L )values( ";

	          for (int i1 = 0 ; i1 < vct3.size(); i1++) { 	
	        	  
	        	  if(i1<17){
	        		  try{
	        			  double nbr = Double.parseDouble(""+vct3.elementAt(i1));
	        			  req = req + nbr + ", ";
	        			  
	        		  }catch (Exception e) {
	        			  
	        			  req = req + "0.24, ";
	        			  
					}
	        	  }else
	        	  
	        	  if(i1==17) req = req +" '"+vct3.elementAt(i1)+"', ";
	        	 
	        	  else  if(i1==18){

	        		  if(min < 55 ) min = min + 5;
	        		  else{
	        			  min = 0;
	        			  
	        			  if(heure < 23) heure++;
		        		  else{
		        			  heure = 0;
		        			  newDate.setDate(newDate.getDate()+1);
					          //System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(newDate));
		        		  }
	        		  }
	        		  String m = ""+mn;
	        		  String h = ""+hr;
	        		  
	        		  if(min < 10) m = "0"+min;
	        		  else m = ""+min;
	        		  
	        		  if(heure < 10) h = "0"+heure;
	        		  else h = ""+heure;
	        		  
	        		  Timestamp tm = new Timestamp((long) Double.parseDouble(""+vct3.elementAt(i1)));
		 	          String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(newDate)+" "+h+":"+m+":00";				 	            
		 	          req = req + " TIMESTAMP '"+timestamp + "', ";
	        		 // System.out.println("cell "+i1+" "+new SimpleDateFormat("dd/MM/yyyy").format(tm.getDate()));
	        	  }else if(i1==19){
	        		  try{
	        			  double nbr = Double.parseDouble(""+vct3.elementAt(i1));
	        			 // System.out.println(i1+" "+nbr);
	        			  req = req + nbr + ", ";
	        		  }catch (Exception e) {
	        			  req = req + "0.79288,  ";
					}
	        	
	        	  }else if(i1==20){
	        		  try{
	        			  double nbr = Double.parseDouble(""+vct3.elementAt(i1));
	        			 // System.out.println(i1+" "+nbr);
	        			  req = req + vct3.elementAt(i1) +") ";
	        		  }catch (Exception e) {
	        			  req = req + "0.0 )  ";
					}

	        	  }
	
	          }
	          
	          
	         
				      
			          	
			          	System.out.println(req);
			          	statement.executeUpdate(req);
			          	req = "";

	      		vct3.removeAllElements();	
      		  }
	          	lecture.etatprog++;
	          	
	          	System.out.println("Compteur % = "+lecture.etatprog);
	         }
	            
	            con.close();
		       lecture.etatprog = 10000;  
		          System.out.println("connexion ok");
		          
			
			      } catch (ClassNotFoundException e1) {
				        JOptionPane.showMessageDialog(null,e1,"Attention",JOptionPane.WARNING_MESSAGE);
			      } catch (SQLException sqle) {
				        JOptionPane.showMessageDialog(null,sqle,"Attention",JOptionPane.WARNING_MESSAGE);
			      }
			  
           
	        JOptionPane.showMessageDialog(null,"insertion effectuer","Information",JOptionPane.INFORMATION_MESSAGE);

			
	//*******************************		
			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}


static void essaye2(String date_d, int hr, int mn) {
	final JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
	    "Fichier d'extension .xlsx", "xlsx");
	chooser.setFileFilter(filter);	
	
	
	int returnVal = chooser.showOpenDialog(chooser);
	if(returnVal == JFileChooser.APPROVE_OPTION) {
		
		
		
	try {
		FileChannel fileSource;
		fileSource = new FileInputStream(chooser.getSelectedFile().getAbsolutePath()).getChannel();


		String table = chooser.getSelectedFile().getName().substring(0,chooser.getSelectedFile().getName().length()-5);
		
		c(chooser.getSelectedFile().getAbsolutePath(),date_d,hr,mn);
		
		   
		   if (fileSource != null) {
		       try {
				fileSource.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   
		   
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
									   }
   
	} 
}







}
