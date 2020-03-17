import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TesseractExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 File imageFile = new File("D:\\test\\OCR\\eurotext_unlv.png");  

		    ITesseract instance = new Tesseract();  
		    //instance.setDatapath("");
		    instance.setLanguage("eng");
		// Tesseract instance=Tesseract.getInstance();
		    try {  

		        String result = instance.doOCR(imageFile);  

		        System.out.println(result);

		    } catch (TesseractException e) {  

		        System.err.println(e.getMessage());  

		       

		    }  
	}

}
