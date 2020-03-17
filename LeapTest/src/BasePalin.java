import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class BasePalin {
	boolean dresult;
	boolean bresult;
	
	public boolean Binary(int val)
	{
		StringBuilder binary = new StringBuilder();
		StringBuilder reverse = new StringBuilder();
		ArrayList<Integer> bin = new ArrayList<Integer>(1);
		ArrayList<Integer> rev = new ArrayList<Integer>(1);
		
		for(int i=0;i<10;i++) {
			if(val==1)
			{
				bin.add(val%2);
				binary.append(bin.get(0));
				break;
			}
			bin.add(val%2);
			binary.append(bin.get(i));
			val=(int)(val/2);
			if(val==1)
			{
				
				bin.add(val%2);
				binary.append(bin.get(i+1));
				break;
			}
		}
		Collections.reverse(bin);
		int j=0;
		for(int i=bin.size()-1;i>=0;i--)
		{
			rev.add(bin.get(i));
			reverse.append(rev.get(j));
			j++;
		}
		 bresult = bin.equals(rev);
		 
		 if(dresult == true && bresult == true)
		 {
			 
			 System.out.print(binary+" = "+reverse);
		 }	 
		return bresult;
}
		
	public boolean Decimal(int val)
	{
		int len = String.valueOf(val).length();
		ArrayList<Integer> dec  = new ArrayList<Integer>(1);
		ArrayList<Integer> rev = new ArrayList<Integer>(1);
		for(int i=0;i<len;i++)
		{
			dec.add(val%10);
			rev.add(val%10);
			val = (int)(val/10);
		}
		Collections.reverse(dec);
		dresult = dec.equals(rev);
		return dresult;	
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasePalin call = new BasePalin();
		for(int i=1;i<=1000;i++)
		{
		if(call.Decimal(i) == true)
		{
			if(call.Binary(i) == true) {
				System.out.println(" = "+i);
				System.out.println("");
			}
		
		}
		
	}

}
}
