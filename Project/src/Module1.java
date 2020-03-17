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

public class Module1 {
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

	     Imgproc.threshold(plateRegion, plateRegion,140,255,Imgproc.THRESH_OTSU); 
	    
	     Mat vertical=plateRegion.clone();
 	//    /*
 	     
 	    int vertical_size = vertical.rows() / 40;
 	    Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size( 1,vertical_size));
 	    Imgproc.erode(vertical, vertical, verticalStructure);
 	    Imgproc.dilate(vertical, plateRegion, verticalStructure);
 	    Mat src=plateRegion;
 	   Mat gray = new Mat();
 	   if (src.channels() == 3)
       {
           Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
       }
       else
       {
           gray = src;
       }

       // Show gray image
       //showWaitDestroy("gray" , gray);
       //! [gray]

       //! [bin]
       // Apply adaptiveThreshold at the bitwise_not of gray
       Mat bw = new Mat();
       Core.bitwise_not(gray, gray);
       Imgproc.adaptiveThreshold(gray, bw, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, -2);

       // Show binary image
      // showWaitDestroy("binary" , bw);
       //! [bin]

       //! [init]
       // Create the images that will use to extract the horizontal and vertical lines
       Mat horizontal = bw.clone();
        vertical = bw.clone();
       //! [init]

       //! [horiz]
       // Specify size on horizontal axis
       int horizontal_size = horizontal.cols() / 30;

       // Create structure element for extracting horizontal lines through morphology operations
       Mat horizontalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(horizontal_size,1));

       // Apply morphology operations
       Imgproc.erode(horizontal, horizontal, horizontalStructure);
       Imgproc.dilate(horizontal, horizontal, horizontalStructure);

       // Show extracted horizontal lines
      // showWaitDestroy("horizontal" , horizontal);
       //! [horiz]

       //! [vert]
       // Specify size on vertical axis
       vertical_size = vertical.rows() / 30;

       // Create structure element for extracting vertical lines through morphology operations
       verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size( 1,vertical_size));

       // Apply morphology operations
       Imgproc.erode(vertical, vertical, verticalStructure);
       Imgproc.dilate(vertical, vertical, verticalStructure);

       // Show extracted vertical lines
      // showWaitDestroy("vertical", vertical);
       //! [vert]

       //! [smooth]
       // Inverse vertical image
       Core.bitwise_not(vertical, vertical);
      // showWaitDestroy("vertical_bit" , vertical);

       // Extract edges and smooth image according to the logic
       // 1. extract edges
       // 2. dilate(edges)
       // 3. src.copyTo(smooth)
       // 4. blur smooth img
       // 5. smooth.copyTo(src, edges)

       // Step 1
       Mat edges = new Mat();
       Imgproc.adaptiveThreshold(vertical, edges, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 3, -2);
      // showWaitDestroy("edges", edges);

       // Step 2
       Mat kernel = Mat.ones(2, 2, CvType.CV_8UC1);
       Imgproc.dilate(edges, edges, kernel);
     //  showWaitDestroy("dilate", edges);
//
       // Step 3
    Mat smooth = new Mat();
      vertical.copyTo(smooth);

       // Step 4
    Imgproc.blur(smooth, smooth, new Size(2, 2));

       // Step 5
      smooth.copyTo(vertical, edges);
      vertical.copyTo(plateRegion);
       // Show final result
      // showWaitDestroy("smooth - final", vertical);
      // Imgcodecs.imwrite("D:\\test\\Morph.jpg",vertical);
     // Core.bitwise_not(vertical, plateRegion);
  
     
    
 
  
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
