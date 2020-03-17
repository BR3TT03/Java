package random;
import java.util.*;
public class RandomGuess {

	private static Scanner scn;

	public static void main(String[] args) {
		int random;
		Random rand=new Random();
		random=rand.nextInt(100);
		//System.out.println(random);
		System.out.println("Guess a number between 1-100");
		scn = new Scanner(System.in);
		int number,i=1;
		number=scn.nextInt();
		while(random!=number) {
			i++;
			
		    if((number-random)>10)
		    	System.out.println("Very high");
		       
		    else if((number-random)>0 && (number-random)<10)
		    	
		    	System.out.println("High");
		    if((number-random)<-10)
		    	System.out.println("Very low");
		    else if((number-random)>-10 && (number-random)<0)		
             System.out.println("Low");
		    number=scn.nextInt();
		}
		System.out.println("You have guessed the number in "+i+" tries");
	}

}
