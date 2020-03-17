import java.io.File;

import org.opencv.core.Core; 
import org.opencv.core.Mat; 
import org.opencv.core.MatOfRect; 
import org.opencv.core.Point; 
import org.opencv.core.Rect; 
import org.opencv.core.Scalar; 
import org.opencv.imgcodecs.Imgcodecs; 
import org.opencv.imgproc.Imgproc; 
import org.opencv.objdetect.CascadeClassifier;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException; 

public class LicensePlate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
		CascadeClassifier plateDetector = new CascadeClassifier(); 
		plateDetector.load("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_russian_plate_number.xml"); 

		// Input image 
		Mat image = Imgcodecs.imread("D:\\test\\temp\\39.jpg"); 
		 Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2GRAY);	
		 MatOfRect plateDetections = new MatOfRect(); 
		plateDetector.detectMultiScale(image, plateDetections); 
		Mat plateRegion=null;
		for (Rect rect : plateDetections.toArray()) 
		{ 
			Imgproc.rectangle(image, new Point(rect.x, rect.y), 
			new Point(rect.x + rect.width, rect.y + rect.height), 
										new Scalar(0, 0, 255));
			plateRegion=image.submat(rect);
			 Imgproc.threshold(plateRegion, plateRegion,80,255,Imgproc.THRESH_BINARY);
			//  
			  Imgcodecs.imwrite("D:\\test\\OCR\\recogog.jpg", image);
			  File imageFile = new File("D:\\test\\OCR\\recogog.jpg"); 
			    ITesseract instance = new Tesseract();  
			    //instance.setDatapath("");
			    instance.setLanguage("eng");
			
			    try {  

			        String result = instance.doOCR(imageFile);  

			        System.out.println(result);

			    } catch (TesseractException e) {  

			        System.err.println(e.getMessage());  

			       

			    }  
		} 
		
		// Saving the output image 
		//String filename = "recogog.jpg"; 
		


	}

}
