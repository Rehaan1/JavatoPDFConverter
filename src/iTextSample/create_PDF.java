package iTextSample;

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.AreaBreak; 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class create_PDF
{
	
	public String ReadFile(String address)
	{
		String javaData="";
		try
		{
			File myObj = new File(address);
			Scanner myReader = new Scanner(myObj);
			while(myReader.hasNextLine())
			{
				javaData += "\n"+myReader.nextLine();
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			return javaData;
		}
		
	}
	
	public List<File> LocateFiles(String address)
	{
		List<File> fileList = new ArrayList<File>();
		try
		{
			File folder = new File(address);
			
			// list all the files
		    File[] files = folder.listFiles();
		    fileList = new ArrayList<File>(Arrays.asList(files));
		    
		    int index=0;
		    
		    for(File file: fileList)
		    {
		    	String filename = file.getName();
		    	int len = filename.length();
		    	
		    	//remove all non .java files
		    	if(!filename.substring(len-4, len).equalsIgnoreCase("java"))
		    	{
		    		//System.out.println(filename.substring(len-4, len));
		    		fileList.remove(index);
		    	}
		    	index++;
		    }
		    
//		    for(File file : fileList) 
//		    {
//		    	if(file.isFile()) 
//		        {
//		          System.out.println(file);
//		        }
//		    }  
		} 
		catch (Exception e) 
		{
		      e.getStackTrace();
		}
		finally
		{
			return fileList;
		}	
	}
	
	public static void main(String args[]) throws Exception
	{              
	      // Creating a PdfWriter       
	      String dest = args[0];       
	      PdfWriter writer = new PdfWriter(dest); 
	      
	      String directory = args[1];
	   
	      // Creating a PdfDocument       
	      PdfDocument pdfDoc = new PdfDocument(writer);                             
	   
	      // Creating a Document        
	      Document document = new Document(pdfDoc);
	      
	      // Create class object
	      create_PDF ob = new create_PDF();
	      
	      List<File> fileList = ob.LocateFiles(directory);
	      
	      for(File file : fileList) 
		  {
	    	  // Adding a new page 
		      pdfDoc.addNewPage(); 
		      
	    	  String javaData = ob.ReadFile(file.toString());
	    	 
		      // Creating Paragraphs 
	    	  Paragraph filename = new Paragraph(file.getName());
	    	  Paragraph line = new Paragraph("--------------------------------");
		      Paragraph paragraph1 = new Paragraph(javaData);
		      
		      // Adding paragraphs to document 
		      document.add(filename);
		      document.add(line);
		      document.add(paragraph1); 
		      
		      // Creating an Area Break          
		      AreaBreak aB = new AreaBreak();           
		   
		      // Adding area break to the PDF       
		      document.add(aB); 
	    	  
		  }  

	      // Closing the document    
	      document.close();              
	      System.out.println("PDF Created");    
	   } 
}
