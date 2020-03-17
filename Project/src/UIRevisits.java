
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import net.proteanit.sql.DbUtils;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


//import for imageResize
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class UIRevisits extends JFrame implements ActionListener  {
   String val,number;
   int flag,insertFlag;
	JFrame jf;
   JButton jb,Insert,Search; 
   JLabel jl,jpjl,PlateNum;
   JTextField enter,Number;
   JTable jt;
   JMenuBar jmb;
   JMenu menu;
   JMenuItem dw,pw;
   JPanel jp;
   JScrollPane jsp;
   Connection con = null;
   String input ;
   
   
   //variable for imageBrowse
	private javax.swing.JLabel CascadeOutput;
	private javax.swing.JLabel PlateOutput;
	private javax.swing.JTextField VehicleNoOutput;
	private javax.swing.JLabel browseImage;
	private javax.swing.JLabel coutput;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel mainPanel;
	private String imagePathStr;

	//------------------------------
	/*public void UIDemo2(){
		
		//Components to insert entries of vehicles into the table
		PlateNum=new JLabel("Plate Number :");
		Number=new JTextField(10);
		Insert=new JButton("Insert");
		PlateNum.setBounds(10, 140, 90, 20);
		add(PlateNum);
		Number.setBounds(100, 140, 100, 20);
		add(Number);
		Insert.setBounds(100, 180, 100, 20);
		add(Insert);
		Insert.addActionListener(this);
		
		
	
	//Components to view data of the table	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
	   jb = new JButton();
	   Search = new JButton("Search");
	   Search.setBounds(650,200,100,20);
	   Search.addActionListener(this);
	   add(Search);
		jmb = new JMenuBar();
	   menu = new JMenu("View");
	   dw = new JMenuItem("Datewise");
	   pw = new JMenuItem("Platewise");
	   jmb.add(menu);
	   menu.add(dw);
	   menu.add(pw);
	   
	   //select components design------------
	   jl = new JLabel();
	   jl.setBounds(400,30,120,20);
	   add(jl);
	   enter = new JTextField(20);
	   enter.setBounds(530,30,100,20);
	   add(enter);
	   enter.setVisible(false);
	   setJMenuBar(jmb);
	   dw.addActionListener(this);
	   pw.addActionListener(this);
	   
	   jb.setBounds(650,30,50,20);
	   add(jb);
	   jb.setVisible(false);
	   jb.addActionListener(this);
	   //------------------------------------
	   
	   
	   jt = new JTable();
       jsp = new JScrollPane(jt);
       jsp.setBounds(500,140,150,150);
       add(jsp);
       
	  
       fetch();
	   //show Data form database
       
	   setSize(800,500);
	   setVisible(true);
   }*/
		
	
	
		
		
	/*public void actionPerformed(ActionEvent e) {
		
		
		//System.out.println("Entered");
		//Inserting vehicle entries into the table
		if(e.getSource()==Search) {
			flag=1;
			int row=jt.getSelectedRow();
			int column=jt.getSelectedColumn();
		
			newFrame((String)jt.getValueAt(row,column)); 
		}
		if(e.getSource() == Insert)
		{
			//System.out.println("insert");
			number=Number.getText();
			input="E:\\test\\"+number;
			String NumberPlate =Extract();
			number=NumberPlate;
			java.util.Date date=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			Time time = new Time(date.getTime());
			try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost/platedata","root","");
		
		
				String info = "insert into platedata values(?,?,?)";
				PreparedStatement re = con.prepareStatement(info);
				//data for plate data table
				re.setString(1,number);
				re.setTime(2,time);
				re.setDate(3,sqlDate);
				re.executeUpdate();
				re.close();
				
				
			
			System.out.println("Data Inserted");
			
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
			fetch();

			
		}
		
		//viewing the table entries from database	
		
		jb.setVisible(true);
    	jb.setText("Go");
		int r = jt.getSelectedRow();
		int c = jt.getSelectedColumn();
        if(e.getSource() == dw)
        {
        	jl.setText("Enter a date");
        	enter.setVisible(true);
        	flag=0;
    	}
       	
        		
        if(e.getSource() == pw)
        {
        	jl.setText("Enter a plate number");
        	enter.setVisible(true);
        	flag=1;
        }
        if(e.getSource() == jb) {
        	
        	val = enter.getText();
        	newFrame(val);
        }
	

	}*/

 public void newFrame(String num) {
	 JFrame jf =new JFrame("INFO");
	 jf.setSize(500,500);
	 JTable info =new JTable();
	 JScrollPane sc = new JScrollPane(info);
	 sc = new JScrollPane(info);

     jf.add(sc);
	 jf.setVisible(true);
	 String query;
	 try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost/platedata","root","");
			if(flag==0) {
			 query="Select * from platedata where Date= ?";
			}
			else
			{
				
				query="Select * from platedata where PlateNumber= ? ORDER BY Date DESC ,  Time DESC";
			}
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, num);
			
			
			ResultSet rs = pre.executeQuery();
			info.setModel(DbUtils.resultSetToTableModel(rs));
	 }
	 catch(Exception e1)
		{
			e1.printStackTrace();
		}
 }
	public int Extract() {
		
		return 0;
		
	
	}
	
	
	//image upload panel
	private void initComponents() {
		// Extracting Plate Table from database------------
		
		jt = new JTable();
		
		jsp = new JScrollPane(jt);
	       jsp.setBounds(800,140,400,150);
	       add(jsp);
	       JButton Search;
	       Search = new JButton("Search");
		   Search.setBounds(960,250,100,20);
		   Search.addActionListener(this);
		   add(Search);
	       fetch();
	    //---------------------------------------------------   
	    
        mainPanel = new javax.swing.JPanel();
        browseImage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        CascadeOutput = new javax.swing.JLabel();
        PlateOutput = new javax.swing.JLabel();
        coutput = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        VehicleNoOutput = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

       // browseImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("gg.png"))); // NOI18N
        browseImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        browseImage.setMaximumSize(new java.awt.Dimension(1002, 802));
        browseImage.setMinimumSize(new java.awt.Dimension(1002, 802));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("BrowseImage");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        CascadeOutput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PlateOutput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        coutput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        coutput.setText("                    Cascade Output");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("       PlateNoDetection");

        VehicleNoOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehicleNoOutputActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Recognize Plate No");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(browseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VehicleNoOutput))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CascadeOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(coutput, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PlateOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(719, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(browseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CascadeOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PlateOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(coutput, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VehicleNoOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setVisible(true);
    }
	
	
	public void fetch()
	{
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost/platedata","root","");
			
			String query="Select  PlateNumber, Date,Time from platedata ORDER BY Date DESC,  Time DESC  ";
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet rs = pre.executeQuery();
			jt.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
   
	}
	
	//unknown events
	private void VehicleNoOutputActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	//main recognizer
    	 
        
    	
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
 		// String input = "D:\\test\\test\\test_003.jpg";
 		 Mat src = Imgcodecs.imread(input);
 		 String result = "" ;
 		try {
 			

 		      
 		      Mat dst = new Mat();

 		      //Converting the image to gray sacle and saving it in the dst matrix
 		      Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
 		    
 		    // /*
 		      CascadeClassifier plateDetector = new CascadeClassifier(); 
 		      plateDetector.load(".\\haarcascade_russian_plate_number.xml");
 		      MatOfRect plate = new MatOfRect();
 		      plateDetector.detectMultiScale(dst,plate);
 		      Mat plateRegion=null;
 		      
 		      for (Rect rect : plate.toArray()) {
 		    	   Imgproc.rectangle(src, new Point(rect.x, rect.y), 
 		    				new Point(rect.x + rect.width, rect.y + rect.height), 
 		    											new Scalar(0, 0, 255)); 
 		    	//  HighGui.imshow("plates", src);
 					//	   HighGui.waitKey(0);
 						//  HighGui.destroyWindow("plates");

 				plateRegion=dst.submat(rect);
 				
 		    //  }
 		      
 				MatOfByte byteemat = new MatOfByte();

			    	Imgcodecs.imencode(".jpg", src, byteemat);

			    	byte[] bytees = byteemat.toArray();

			    	InputStream ins = new ByteArrayInputStream(bytees);

			    	BufferedImage imgs = ImageIO.read(ins);
			    	 //ImageIcon image = new ImageIcon(imgs);
			    	
				   
			    	//imgs=imgs.getScaledInstance(200,200, Image.SCALE_SMOOTH);
			    	//imgs=imgs.resize(200,200);
			    
			    	//System.out.println(imgs.getWidth());
			    	ImageIcon icon = new ImageIcon(imgs); 
			    	Image imgsss = icon.getImage();
				    Image newImages=imgsss.getScaledInstance(300,350, Image.SCALE_SMOOTH);
				    icon = new ImageIcon(newImages); 
 				CascadeOutput.setIcon(icon);
 			
 				Imgcodecs.imencode(".jpg", plateRegion, byteemat);
 				bytees = byteemat.toArray();
 				ins = new ByteArrayInputStream(bytees);
 				 imgs = ImageIO.read(ins);
 				
 				icon = new ImageIcon(imgs);
 				 PlateOutput.setIcon(icon);
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
 		      //HighGui.imshow("plates", com);
 			  //  HighGui.waitKey(0);
 			//	    HighGui.destroyWindow("plates");
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
 				// System.out.println(result);
 				     VehicleNoOutput.setText(result);
 		      }
 		    	 //System.out.println("comp");
 				  
 		      
 		
 		      		      
 		}catch(Exception e) {
 			
 		}
         
         
    }
    private void SearchActionPerformed(ActionEvent e)
    {
    	System.out.println("Search");
    	//Search 
    			if(e.getSource()==Search) {
    				flag=1;
    				int row=jt.getSelectedRow();
    				int column=jt.getSelectedColumn();
    			
    				newFrame((String)jt.getValueAt(row,column)); 
    			}
    }
	
	//browsImage action
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
		
       JFileChooser fileChooser = new JFileChooser();
       FileNameExtensionFilter filter= new FileNameExtensionFilter("*.IMAGE","jpg","png","gif");
       fileChooser.addChoosableFileFilter(filter);
       int result= fileChooser.showSaveDialog(this);
       if(result == JFileChooser.APPROVE_OPTION)
       {
           File selectedImage = fileChooser.getSelectedFile();
          input= selectedImage.getAbsolutePath();
         //  System.out.println(imagePath);
           try{
               browseImage.setIcon(ResizeImage(input));
               CascadeOutput.setIcon(null);
               PlateOutput.setIcon(null);

           }catch(Exception exception){
           JOptionPane.showMessageDialog(this, "Image Error:" +exception.getMessage());
           }
       }
       
    } 
	
	//resizeImage
	private ImageIcon ResizeImage(String imgPath)
	{   int imageX=browseImage.getWidth();
	    int imageY=browseImage.getWidth();
	    ImageIcon myImage = new ImageIcon(imgPath);
	    Image img = myImage.getImage();
	    Image newImage=img.getScaledInstance(imageX,imageY, Image.SCALE_SMOOTH);
	    ImageIcon image = new ImageIcon(newImage);
	    
	    return image;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//      new UIDemo();
		UIRevisits u = new UIRevisits();
		u.initComponents();
//		u.UIDemo2();
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
