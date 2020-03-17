import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
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

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;


public class Demo2 {
	
	private static Mat dis[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 String input = "D:\\test\\temp\\99.jpg";
		 Mat src = Imgcodecs.imread(input);
		try {
			

		      //Reading the image
			   
		    //  Mat src = Imgcodecs.imread(input);
		     // HighGui.imshow("src", src);
		      //Creating the empty destination matrix
		      Mat dst = new Mat();

		      //Converting the image to gray sacle and saving it in the dst matrix
		      Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
		    
		    // /*
		      CascadeClassifier plateDetector = new CascadeClassifier(); 
		      plateDetector.load("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_russian_plate_number.xml");
		      MatOfRect plate = new MatOfRect();
		      plateDetector.detectMultiScale(dst,plate);
		      Mat plateRegion=null;
		      for (Rect rect : plate.toArray()) {
		    	   Imgproc.rectangle(src, new Point(rect.x, rect.y), 
		    				new Point(rect.x + rect.width, rect.y + rect.height), 
		    											new Scalar(0, 0, 255)); 
		    	   

				plateRegion=dst.submat(rect);
				// HighGui.imshow("src", plateRegion);
		      }
		   
		     Imgproc.threshold(plateRegion, plateRegion,140,255,Imgproc.THRESH_OTSU); 
		    
		  //   Mat vertical=plateRegion.clone();
		   //  Mat horizontal = plateRegion.clone();
		   //  Imgcodecs.imwrite("D:\\test\\mask.jpg",plateRegion);
		    
		  //  Imgproc.Canny(plateRegion, plateRegion, 60, 60*3);
		   
		     Mat contourMat = plateRegion.clone();
		     List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		    // int thickness = (fillFlag.equals(onFillString))?-1:2;

		  //   Imgproc.findContours(contourMat, contours, new Mat(),
		     //Imgproc.CHAIN_APPROX_NONE,Imgproc.CHAIN_APPROX_SIMPLE);
		   
		  //   for(int i=0;i<contours.size();i++){
		    //	 MatOfPoint currentContour = contours.get(i);
		    //	 double currentArea = Imgproc.contourArea(currentContour);
		    	// System.out.println();
		    	//Rect con=contours.get(i);
		 //     dst=plateRegion.clone();
		    	// Imgproc.drawContours(plateRegion, contours, -1, new Scalar(0,255,0),
		    //	 1);
		    	// Imgproc.fillPoly(plateRegion, contours,  new Scalar(0,255,255));
		    	///*
		     
		   	 Imgproc.findContours(contourMat, contours, new Mat(),
		    			     Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
		   	//Imgproc.fillPoly(plateRegion, contours,  new Scalar(255,255,255));
		   	 int generic =contourMat.cols()/200;
		    	  Imgproc.drawContours(contourMat, contours, -1, new Scalar(255,255,255),
		 		    	 generic);
		    	
		    	  double maxVal = 0;
		    	
		    	  for (MatOfPoint contour : contours) {
		    		  Rect rec = Imgproc.boundingRect(contour);
		    		  if(rec.x>0&&rec.y>0) {
		    			  
		    			  double contourArea = Imgproc.contourArea(contour);
		    			 
						if (maxVal < contourArea)
		    			    {
		    			        maxVal = contourArea;
		    			    
		    			        
					    	dst=contourMat.submat(rec);
					    	
		    			    }
		    				  
		    			
		    			  
		    			 
		    		  }
		    		  
		    		  
		    	  }
		    	  Mat com=dst;
		   // 	  HighGui.imshow("plates", com);
			//	    HighGui.waitKey(0);
			//	    HighGui.destroyWindow("plates");
				    Mat dis=null;
				    List<MatOfPoint> contourss = new ArrayList<MatOfPoint>();
				 Imgproc.findContours(com, contourss, new Mat(),Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		    	//Mat Letters[]=new Mat[contourss.size()];
				 int contNo=0;
				
				 ContourSort obj[] =  new ContourSort[contourss.size()];
				 int top,bottom,left,right;
				  for (MatOfPoint contour : contourss) {
				 // System.out.println(Imgproc.boundingRect(contour));
					Rect rec = Imgproc.boundingRect(contour); 
					
					if(rec.width/rec.height>=0.8&&rec.width/rec.height<=1.0) {
						
					}else if(rec.x>=0 & rec.y>0 & rec.height>=com.height()/2){
						
						dis=com.submat(rec);
						top= (int) (0.09*dis.rows()); bottom = top;
				         left = (int) (0.01*dis.cols()); right = left;
				         Mat bord=new Mat( );
				         Core.copyMakeBorder( dis, bord, top, bottom, left, right, Core.BORDER_REPLICATE, new Scalar(255,255,255));
						 obj[contNo]= new ContourSort(contNo,rec.x,bord);
					
						contNo++;
						
						
					//	HighGui.imshow("char", dis);
					   // HighGui.waitKey(0);
						// HighGui.destroyWindow("char");
						 
						 
					}
					
				  }
				  ContourSort temp =  new ContourSort(0,0,dis);
				 for(int i=0;i<contNo;i++) {
					 for(int j=i+1;j<contNo;j++) {
						 if(obj[i].x>obj[j].x) {
							 temp=obj[j];
							 obj[j]=obj[i];
							 obj[i]=temp;
						 }
					 }
					// HighGui.imshow("char", obj[i].m);
					  //HighGui.waitKey(0);
						// HighGui.destroyWindow("char");
					
					    //instance.setDatapath("");
					   // instance.setLanguage("eng");
					
					   
					  
				 }
				 String result = "" ;
				 for(int i=0;i<contNo;i++) {
					 try {  

					       // @SuppressWarnings("deprecation")
					    	Tesseract1 instance = new Tesseract1();  
					    	//Core.bitwise_not(com, com);
					/*  	HighGui.imshow("char", obj[i].m);
							  HighGui.waitKey(0);
							 HighGui.destroyWindow("char");
							 */
					    	MatOfByte bytemat = new MatOfByte();

					    	 Imgcodecs.imencode(".jpg", obj[i].m, bytemat);

					    	byte[] bytes = bytemat.toArray();

					    	InputStream in = new ByteArrayInputStream(bytes);

					    	BufferedImage img = ImageIO.read(in);
					     //  File ff =new File("D:\\test\\aaa.jpg");
					     //   ImageIO.write(img,"jpg",ff);
					    	result = result.concat(instance.doOCR(img));  
					    	
					      //  result="1";
					        
					      
					       img=null;
					   

					    } catch (TesseractException e) {  

					        System.err.println(e.getMessage());  

					       

					    }  

				 }
				result= result.replaceAll("[\\n\\t ]", "");
				 System.out.println(result);
				 //System.out.print(result);
		    	 System.out.println("comp");
				  
				 
		    	//  Core.bitwise_not(dst, dst);
		    
		    	//Imgproc.drawContours(contd, contours, -1, new Scalar(255,255,255), 1);
		    	//  Mat a=null;
			/*	for(MatOfPoint contour:contours) {
					Rect rec = Imgproc.boundingRect(contour); 
					 if(rec.x>0&&rec.y>0) {
					 
						 dst=com.submat(rec);
						 HighGui.imshow("plates", dst);
						    HighGui.waitKey(0);
						  HighGui.destroyWindow("plates");
					 }
					 
				
				}*/
				
					 
					//  MatOfInt hull = new MatOfInt();
			          //  Imgproc.convexHull(contour, hull);
			       //    Imgproc.drawContours(contd, (List<MatOfPoint>) hull, i,  new Scalar(255,255,255) );
					 
				//  Imgcodecs.imwrite("D:\\test\\outj.jpg",dst);
					
					// System.out.println(rec);
			//	HighGui.imshow("platesss",dst.submat(rec) );
					//   HighGui.waitKey(0);
					//  HighGui.destroyWindow("platesss");
				//  }
		    	//  HighGui.imshow("contd", dst);
				   //  HighGui.waitKey(0);
				 //  HighGui.destroyWindow("contd");
				  
				//  Imgproc.findContours(dst, contours, new Mat(),Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_NONE);
				  // Imgproc.drawContours(dst, contours, -1, new Scalar(0,0,255), 1);
				  // HighGui.imshow("plates", dst);
				  //   HighGui.waitKey(0);
				 //  HighGui.destroyWindow("plates"); 
		    	//  //*/
		  	//	for(int i=0;i<contours.size();i++) {
		  			// MatOfPoint currentContour = contours.get(0);
		  			
		 		   
		 		    
		  	//	}
				   
				 // Core.bitwise_not(dst, dst);
		    	 
				 //  HighGui.destroyWindow("plate");*/
				 
				   
		    
		     /*  final double HTHRESH = dst.rows() * 0.5; // bounding-box height threshold
			  // List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
			   List<Point> digits = new ArrayList<Point>();    // contours of the possible digits
			   Imgproc.findContours(dst, contours, new Mat(), Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
			   Imgproc.drawContours(dst, contours, -1, new Scalar(255,255,255), 1);
			   for (int i = 0; i < contours.size(); i++)
			   {
			       if (Imgproc.boundingRect(contours.get(i)).height > HTHRESH)
			       {
			           // this contour passed the bounding-box height threshold. add it to digits
			           digits.addAll(contours.get(i).toList());
			       }   
			   }
	    	//  */
	    	// Core.bitwise_not(dst, dst);
	    	//  HighGui.imshow("plates", dst);
			/////     HighGui.waitKey(0);
			 //    HighGui.destroyWindow("plates");
			    // Imgproc.fillPoly(plateRegion, dst.toArray(),  new Scalar(0,00,0));
		//	     Core.bitwise_not(plateRegion, plateRegion);
	 //   	 Core.absdiff(dst,plateRegion, dst);
	    //	  MatOfPoint currentContour = contours.get(i);
	    	// Rect rectangle = Imgproc.boundingRect(currentContour);
	 // 	Imgproc.rectangle(plateRegion, rectangle.tl(), rectangle.br(), new
	  //  Scalar(0,0,255),10);
	 //  	
	    	// MatOfInt hull = new MatOfInt();
	    	// Imgproc.convexHull(currentContour, hull);

	    //	 List<MatOfPoint> hullContours = new ArrayList<MatOfPoint>();
	    //	MatOfPoint hullMat = new MatOfPoint();
	    // hullMat.create((int)hull.size().height,i,CvType.CV_32SC2);
	    	 
	    // }
	   //  HighGui.imshow("plates", dst);
	   //  HighGui.waitKey(0);
	  
	        //! [init]

	        //! [horiz]
	        // Specify size on horizontal axis
	     /*
	        int horizontal_size = horizontal.cols() / 2;

	        // Create structure element for extracting horizontal lines through morphology operations
	        Mat horizontalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(horizontal_size,1));

	        // Apply morphology operations
	        Imgproc.erode(horizontal, horizontal, horizontalStructure);
	        Imgproc.dilate(horizontal, horizontal, horizontalStructure);
	        Imgcodecs.imwrite("D:\\test\\horizontal.jpg",horizontal);
 	//   /*
 	     
 	    int vertical_size = vertical.rows() / 2;
 	  Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size( 1,vertical_size));

   Imgproc.dilate(vertical, vertical, verticalStructure);
   Imgproc.erode(vertical, vertical, verticalStructure);
  
  

   // Find contours
  // Imgproc.findContours(vertical, contours, mat, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

   // Draw contours in dest Mat
  // Imgproc.drawContours(mat, contours, -1, white);
  
 //  Core.multiply_5( vertical, 0.5, horizontal, 0.5, 0.0, dst);


  Core.absdiff(vertical,horizontal , dst);
  Core.absdiff(dst,plateRegion , dst);
  Core.bitwise_not(dst, dst);
  
 //
  HighGui.imshow("Linear Blend", dst);
 
 //
 
 //  
  HighGui.waitKey(0);
   */
		      
	/*  
		      File imageFile = new File("D:\\test\\outj.jpg"); 
    		  ITesseract instance = new Tesseract();  
			    //instance.setDatapath("");
			   // instance.setLanguage("eng");
			
			    try {  

			        String result = instance.doOCR(imageFile);  
			     
			        
			        
			       System.out.println(result);

			    } catch (TesseractException e) {  

			        System.err.println(e.getMessage());  

			       

			    }  
		  //   */
		      
		      // saving the image
		    
		    //  Imgcodecs.imwrite("D:\\test\\temp\\newj.jpg", dst);*/
		      		      
		}catch(Exception e) {
			
		}

	}
	


}
