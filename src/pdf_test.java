import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
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


public class pdf_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/iTextTest.pdf"));
			document.open();
			
			
			Image image2 = null;
			try {
				image2 = Image.getInstance("icones/rech_RS.png");
				  image2.scaleAbsolute(300f, 120f);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				
		  Anchor anchorTarget = new Anchor("Algerian Operating and Maintenance");
		      anchorTarget.setName("BackToTop");
		      Paragraph paragraph1 = new Paragraph();

		      paragraph1.setSpacingBefore(50);

		      paragraph1.add(anchorTarget);
		      
		      document.add(paragraph1);

	
			Paragraph title1 = new Paragraph("Chapter 1", 
					FontFactory.getFont(FontFactory.HELVETICA, 
					   18, Font.BOLDITALIC, new CMYKColor(0, 255, 255,17)));
					   
					Chapter chapter1 = new Chapter(title1, 1);
					      
					chapter1.setNumberDepth(0);
			
			Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1", 

				       FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, 
				   
				       new CMYKColor(0, 255, 255,17)));
				   
				Section section1 = chapter1.addSection(title11);
                int x = 1;
				Paragraph someSectionText = new Paragraph("Rapport de correction puissance pour G"+x);

				section1.add(image2); 
				
				section1.add(someSectionText);

				someSectionText = new Paragraph("Following is a 3 X 2 table.");

				section1.add(someSectionText);
				
				
				
				PdfPTable t = new PdfPTable(10);

				
				  t.setHorizontalAlignment(0);
				  t.setWidthPercentage(100);
			      t.setSpacingBefore(25);
			      t.setSpacingAfter(25);
			      
			      for(int i = 0; i < 10; i++){
			      PdfPCell c1 = new PdfPCell(new Phrase("Header "+i));  
			      t.addCell(c1);
			      }
			
			      for(int i = 0; i < 2000; i++){
				       t.addCell("F:"+i);
				      }
			     
			      
			      
			      
			      section1.add(t);
				
				
				document.add(section1);

		document.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				//document.open();
		
		
		
	}

}
