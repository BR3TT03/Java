import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class RecognizerF {
	public static void detectAndDisplay(Mat frame, CascadeClassifier plateCascade, CascadeClassifier number) {
	      
        Mat frameGray = new Mat();
       
      Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
       
     Imgproc.equalizeHist(frameGray, frameGray);
     //Imgproc.threshold(frameGray, frameGray,0,255,Imgproc.THRESH_BINARY);
      
       // -- Detect faces
       MatOfRect plate = new MatOfRect();
       number.detectMultiScale(frame,plate);
       
     

     //  List<Rect> listOfFaces = faces.toList();
        // System.out.println(listOfFaces);
      Mat plateRegion=null;
       for (Rect rect : plate.toArray()) {
    	   Imgproc.rectangle(frame, new Point(rect.x, rect.y), 
    				new Point(rect.x + rect.width, rect.y + rect.height), 
    											new Scalar(0, 0, 255)); 
    	 
		plateRegion=frameGray.submat(rect);
		   
    	  
    		  //
		 //
    		
    		   int dilation_size = 4;
  	         
  	         Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(dilation_size , dilation_size));
  	     //  Imgproc.erode(plateRegion, plateRegion, element1);
  	     // Imgproc.Sobel(plateRegion, plateRegion, -2 ,1, 1);
  	    //Imgproc.Canny(plateRegion, plateRegion, 60, 60*3);
  	     Imgproc.threshold(plateRegion, plateRegion,80,255,Imgproc.THRESH_BINARY);
 	  //  Imgproc.erode(plateRegion, plateRegion, element1);

  //	/*
        	   Imgcodecs.imwrite("D:\\test\\OCR\\de.jpg", plateRegion);
        		  File imageFile = new File("D:\\test\\OCR\\de.jpg"); 
        		  ITesseract instance = new Tesseract();  
  			    //instance.setDatapath("");
  			   // instance.setLanguage("eng");
  			
  			    try {  

  			        String result = instance.doOCR(imageFile);  
  			        
  			        System.out.println(result);

  			    } catch (TesseractException e) {  

  			        System.err.println(e.getMessage());  

  			       

  			    }  
  			    //*/
    	   
       }
	   
     

       //-- Show what you got
      //
       HighGui.imshow("Capture - Plate Tracker", frameGray);
    //   HighGui.imshow("Capture - Plate Tracker", frame);
       
     
   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
		CascadeClassifier plateDetector = new CascadeClassifier(); 
		CascadeClassifier numberDetector = new CascadeClassifier(); 
		plateDetector.load("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_russian_plate_number.xml");
		numberDetector.load("D:\\opencv\\sources\\data\\try2.xml"); 
		 VideoCapture capture = new VideoCapture("D:\\13.mp4");
	        if (!capture.isOpened()) {
	            System.err.println("--(!)Error opening video capture");
	            System.exit(0);
	        }

	        Mat frame = new Mat();
	        while (capture.read(frame)) {
	            if (frame.empty()) {
	                System.err.println("--(!) No captured frame -- Break!");
	                break;
	            }

	            //-- 3. Apply the classifier to the frame
	            detectAndDisplay(frame, plateDetector,numberDetector);
	           
	            if (HighGui.waitKey(3) > 27) {
	            	 capture.release();
	            	break;// escape
	            }
	        }
		
	}

}
