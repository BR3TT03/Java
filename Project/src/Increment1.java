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


import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;


public class Increment1 {
	
	//private static Mat dis[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 String input = "D:\\test\\test\\test_003.jpg";
		 Mat src = Imgcodecs.imread(input);
		try {
			

		      
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
		    	  HighGui.imshow("plates", src);
						   HighGui.waitKey(0);
						   HighGui.destroyWindow("plates");

				plateRegion=dst.submat(rect);
				
		    //  }
		      
		   
		     Imgproc.threshold(plateRegion, plateRegion,140,255,Imgproc.THRESH_OTSU); 
		    
		
		   
		     Mat contourMat = plateRegion.clone();
		     List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		  
		     
		   	 Imgproc.findContours(contourMat, contours, new Mat(),
		    			     Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
		   	//Imgproc.fillPoly(plateRegion, contours,  new Scalar(255,255,255));
		   	 	int generic =contourMat.cols()/250;
		    	Imgproc.drawContours(contourMat, contours, -1, new Scalar(255,255,255), generic);
		    	
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
		      HighGui.imshow("plates", com);
			    HighGui.waitKey(0);
				    HighGui.destroyWindow("plates");
				    Mat dis=null;
				  List<MatOfPoint> contourss = new ArrayList<MatOfPoint>();
				 Imgproc.findContours(com, contourss, new Mat(),Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		
				 int contNo=0;
				
				 ContourSort obj[] =  new ContourSort[contourss.size()];
				 int top,bottom,left,right;
				  for (MatOfPoint contour : contourss) {
				
					Rect rec = Imgproc.boundingRect(contour); 
					
					if(rec.width/rec.height>=0.8&&rec.width/rec.height<=1.0) {
						
					}else if(rec.x>=0 & rec.y>0 & rec.height>=com.height()/(2)+3){
						
						dis=com.submat(rec);
						//Imgproc.fillPoly(plateRegion, contourss,  new Scalar(255,255,255));
						top= (int) (0.09*dis.rows()); bottom = top;
				         left = (int) (0.009*dis.cols()); right = left;
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
					
					
					    //instance.setDatapath("");
					   // instance.setLanguage("eng");
					
					   
					  
				 }
				 String result = "" ;
				 for(int i=0;i<contNo;i++) {
					 try {  

					       
					    	Tesseract1 instance = new Tesseract1();  
					    
					/* 	Core.bitwise_not(com, com); 	
					  HighGui.imshow("char", obj[i].m);
							  HighGui.waitKey(0);
							 HighGui.destroyWindow("char");
							// */
					    	MatOfByte bytemat = new MatOfByte();

					    	Imgcodecs.imencode(".jpg", obj[i].m, bytemat);

					    	byte[] bytes = bytemat.toArray();

					    	InputStream in = new ByteArrayInputStream(bytes);

					    	BufferedImage img = ImageIO.read(in);
					   
					    	result = result.concat(instance.doOCR(img));  	
					    ////	System.out.println(result);
					        img=null;
					   

					    } catch (TesseractException e) {  

					        System.err.println(e.getMessage());  

					       

					    }  

				 }
				result= result.replaceAll("[\\n\\t ]", "");
				/* Imgproc.putText (
				         matrix,                          // Matrix obj of the image
				         "Ravivarma's Painting",          // Text to be added
				         new Point(10, 50),               // point
				         Core.FONT_HERSHEY_SIMPLEX ,      // front face
				         1,                               // front scale
				         new Scalar(0, 0, 0),             // Scalar object for color
				         4                                // Thickness
				      );*/
				      if(result.contains("\'l")) {
				    	 int i= result.indexOf("\'l");
				    	
				    	 result=result.substring(0, i)+"0"+result.substring(i+2, result.length());
				    	
				      }
				      if(result.contains("]")) {
				    	  int i= result.indexOf("]");
				    	  result=result.substring(0, i)+""+result.substring(i+1, result.length());
				      }
				 System.out.println(result);
		      }
		    	 System.out.println("comp");
				  
				 
		
		      		      
		}catch(Exception e) {
			
		}

	}
	


}
