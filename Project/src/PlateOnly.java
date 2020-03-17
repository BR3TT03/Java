


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class PlateOnly {
	private double angle(Point pt1, Point pt2, Point pt0) {
	    double dx1 = pt1.x - pt0.x;
	    double dy1 = pt1.y - pt0.y;
	    double dx2 = pt2.x - pt0.x;
	    double dy2 = pt2.y - pt0.y;
	    return (dx1*dx2 + dy1*dy2)/Math.sqrt((dx1*dx1 + dy1*dy1)*(dx2*dx2 + dy2*dy2) + 1e-10);
	}

	
    public void run(String arg) throws Exception {

        //! [load_image]
        // Check number of arguments
        if (arg.length() == 0){
            System.out.println("Not enough parameters!");
            System.out.println("Program Arguments: [image_path]");
            System.exit(-1);
        }

        // Load the image
        Mat src = Imgcodecs.imread(arg);

        // Check if image is loaded fine
        if( src.empty() ) {
            System.out.println("Error opening image: " + arg);
            System.exit(-1);
        }

        // Show source image
        HighGui.imshow("src", src);
        //! [load_image]

        //! [gray]
        // Transform source image to gray if it is not already
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
        HighGui.waitKey(0);
        HighGui.destroyWindow("src");
        showWaitDestroy("gray" , gray);
        //! [gray]

        //! [bin]
        // Apply adaptiveThreshold at the bitwise_not of gray
        Mat bw = new Mat();
        Core.bitwise_not(gray, gray);
        Imgproc.adaptiveThreshold(gray, bw, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, -2);

        // Show binary image
        showWaitDestroy("binary" , bw);
        //! [bin]

        //! [init]
        // Create the images that will use to extract the horizontal and vertical lines
        Mat horizontal = bw.clone();
        Mat vertical = bw.clone();
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
        showWaitDestroy("horizontal" , horizontal);
        //! [horiz]

        //! [vert]
        // Specify size on vertical axis
        int vertical_size = vertical.rows() / 30;

        // Create structure element for extracting vertical lines through morphology operations
        Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size( 1,vertical_size));

        // Apply morphology operations
        Imgproc.erode(vertical, vertical, verticalStructure);
        Imgproc.dilate(vertical, vertical, verticalStructure);

        // Show extracted vertical lines
        showWaitDestroy("vertical", vertical);
        //! [vert]

        //! [smooth]
        // Inverse vertical image
        Core.bitwise_not(vertical, vertical);
        showWaitDestroy("vertical_bit" , vertical);

        // Extract edges and smooth image according to the logic
        // 1. extract edges
        // 2. dilate(edges)
        // 3. src.copyTo(smooth)
        // 4. blur smooth img
        // 5. smooth.copyTo(src, edges)

        // Step 1
        Mat edges = new Mat();
        Imgproc.adaptiveThreshold(vertical, edges, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 3, -2);
        showWaitDestroy("edges", edges);

        // Step 2
        Mat kernel = Mat.ones(2, 2, CvType.CV_8UC1);
        Imgproc.dilate(edges, edges, kernel);
        showWaitDestroy("dilate", edges);

        // Step 3
     // Mat smooth = new Mat();
    //   vertical.copyTo(smooth);

        // Step 4
   //    Imgproc.blur(smooth, smooth, new Size(2, 2));

        // Step 5
     //   smooth.copyTo(vertical, edges);

        // Show final result
        showWaitDestroy("smooth - final", vertical);
        Imgcodecs.imwrite("D:\\test\\Morp.jpg",vertical);
        //! [smooth]
       
    }

    private void showWaitDestroy(String winname, Mat img) {
        HighGui.imshow(winname, img);
        HighGui.moveWindow(winname, 500, 0);
        HighGui.waitKey(0);
        HighGui.destroyWindow(winname);
    }



    public static void main(String[] args) throws Exception {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
       String arg="D:\\test\\outj.jpg";
        new PlateOnly().run(arg);
    }
}
