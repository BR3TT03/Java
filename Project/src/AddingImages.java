
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import java.util.Locale;
import java.util.Scanner;
class AddingImages{
   

    public static void main(String[] args) {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        double alpha = 0.5; double beta; double input;
        Mat src1, src2, dst = new Mat();
        System.out.println(" Simple Linear Blender ");
        System.out.println("-----------------------");
        System.out.println("* Enter alpha [0.0-1.0]: ");
       // Scanner scan = new Scanner( System.in ).useLocale(Locale.US);
        input =0.5;
        if( input >= 0.0 && input <= 1.0 )
            alpha = input;
        src1 = Imgcodecs.imread("D:\\test\\abc.jpg");
        src2 = Imgcodecs.imread("D:\\test\\template.jpg");
        HighGui.imshow("Linear Blend", src1);
        HighGui.waitKey(10);
        if( src1.empty() == true ){ System.out.println("Error loading src1"); return;}
        if( src2.empty() == true ){ System.out.println("Error loading src2"); return;}
        beta = ( 1.0 - alpha );
        Core.addWeighted( src1, alpha, src2, beta, 0.1, dst);
      
       // Imgcodecs.imwrite("D:\\test\\addition.jpg",dst);
       // System.exit(0);
   
      
    }
}
