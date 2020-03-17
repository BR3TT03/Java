import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
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

public class PutText {

	public static void detectAndDisplay(Mat frame, CascadeClassifier plateCascade, CascadeClassifier number) {
	      
        Mat frameGray = new Mat();
       
      Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
    //  Imgproc.blur(frameGray, frameGray, new Size(7, 7));
   //  Imgproc.equalizeHist(frameGray, frameGray);
     //Imgproc.threshold(frameGray, frameGray,0,255,Imgproc.THRESH_BINARY);
      
       // -- Detect faces
       MatOfRect plate = new MatOfRect();
       plateCascade.detectMultiScale(frame,plate);
       
     

     //  List<Rect> listOfFaces = faces.toList();
        // System.out.println(listOfFaces);
      Mat plateRegion=null;
       for (Rect rect : plate.toArray()) {
    	   Imgproc.rectangle(frame, new Point(rect.x, rect.y), 
    				new Point(rect.x + rect.width, rect.y + rect.height), 
    											new Scalar(0, 0, 255)); 
    	 
		plateRegion=frameGray.submat(rect);
		   
    	  
    		  //Imgproc.adaptiveThreshold(plateRegion, plateRegion, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, -2);
	Imgproc.threshold(plateRegion, plateRegion,80,255,Imgproc.THRESH_BINARY_INV); 
  	      
  	       Mat vertical=plateRegion.clone();
  	      /*
  	         Mat horizontalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(horizontal.cols()/2 , 1));
  	      Imgproc.erode(horizontal, horizontal, horizontalStructure);
  	    Imgproc.dilate(horizontal, horizontal, horizontalStructure);
  	  Core.bitwise_not(horizontal, horizontal);
  	// Step 3
  	  

  	
  //	 */
  	   //   /*
  	    int vertical_size = vertical.rows() / 21;
  	  Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size( 1,vertical_size));
     Imgproc.erode(vertical, vertical, verticalStructure);
      Imgproc.dilate(vertical, plateRegion, verticalStructure);
     // Core.bitwise_not(vertical, plateRegion);
   /*
      Mat edges = new Mat();
      Imgproc.adaptiveThreshold(vertical,  edges, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 3, -2);
      Mat kernel = Mat.ones(20, 20, CvType.CV_8UC1);
      Imgproc.erode(edges, edges, kernel);
   
      Mat smooth = new Mat();
      vertical.copyTo(smooth);

      // Step 4
      Imgproc.blur(smooth, smooth, new Size(2, 2));

      // Step 5
      smooth.copyTo(vertical);

      // Step 5
      vertical.copyTo( plateRegion);
     
     //  */
  	    //    Imgproc.dilate(horizontal, horizontal, horizontalStructure);
  	     //  Imgproc.erode(plateRegion, plateRegion, element1);
  	     // Imgproc.Sobel(plateRegion, plateRegion, -2 ,1, 1);
  	      
  	  //  Imgproc.Canny(plateRegion, plateRegion, 60, 60*3);
  	         
  	      // Imgproc.blur(plateRegion, plateRegion, new Size(3, 3));
  	 
  	    // Imgproc.threshold(plateRegion, plateRegion, 0, 255, Imgproc.THRESH_OTSU);
  	     //Imgproc.threshold(plateRegion, plateRegion,80,255,Imgproc.THRESH_BINARY_INV);
  	    // Imgproc.Canny(plateRegion, plateRegion, 50, 100);
  	  //Imgproc.equalizeHist(plateRegion, plateRegion);
  	       //
  	// 
  	     /*	
  	List<MatOfPoint> contours = new ArrayList<>();
  	   // Imgproc.dilate(plateRegion, plateRegion, element1);
  	  Imgproc.findContours(plateRegion, contours, plateRegion, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
  	  
 
  	for (int i = 0; i < contours.size(); i++) {
  	    Imgproc.drawContours(plateRegion, contours, i, new Scalar(255, 255, 255), -1);
  	}
  	  //*/
  	   //  Imgproc.threshold(plateRegion, plateRegion,80,255,Imgproc.THRESH_BINARY);
 	 

  //	/*
        	   Imgcodecs.imwrite("D:\\test\\OCR\\de.jpg", plateRegion);
        		  File imageFile = new File("D:\\test\\OCR\\de.jpg"); 
        		  ITesseract instance = new Tesseract();  
  			    //instance.setDatapath("");
  			   // instance.setLanguage("eng");
  			
  			    try {  

  			        String result = instance.doOCR(imageFile);  
  			      Imgproc.putText (
  			           frameGray,                          // Matrix obj of the image
  			            result,          // Text to be added
  			            new Point(rect.x,rect.y),               // point
  			            1,      // front face
  			            1,                               // front scale
  			            new Scalar(0, 0, 255),             // Scalar object for color
  			            4                                // Thickness
  			         );
  			      //  System.out.println(result);

  			    } catch (TesseractException e) {  

  			        System.err.println(e.getMessage());  

  			       

  			    }  
  			  //  */
    	   
       }
	   
     

       //-- Show what you got
      // 
     //
       HighGui.imshow("Capture - Plate Tracker", frameGray);
    //        HighGui.imshow("Capture - Plate Tracker", frame);
       
     
   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
		CascadeClassifier plateDetector = new CascadeClassifier(); 
		CascadeClassifier numberDetector = new CascadeClassifier(); 
		plateDetector.load("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_russian_plate_number.xml");
		numberDetector.load("D:\\opencv\\sources\\data\\try2.xml"); 
		 VideoCapture capture = new VideoCapture("D:\\14.mp4");
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
