import java.util.Scanner;
public class Edges {
	int Break(int val)
	{
		int length = String.valueOf(val).length();
		System.out.println(length);
		int arr[] = new int[length-1];
		int sum=0;
		
		//System.out.println(arr.length);
		for(int i=length-1;i>=0;i--)
		{
			arr[i]=val%10;
			val = (int)(val/10);
		}
		int first = 0, last =length-1 ;
		while(first<last || first==last)
		{
			
			//System.out.println(arr[first]+" VAL "+arr[last]);
			if(first<last) {
			sum= sum+arr[first]*arr[last];
			//System.out.println(sum);
			}
			else if(first==last)
			{
				sum=sum+arr[last];
			}
			first++;
			last--;
			//System.out.println(first+" EDGE "+last);
		}
		return sum;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ans=0;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter any number:");
		int num;
		num = in.nextInt();
		in.close();
		Edges call =new Edges();
		while(true) {
			
			ans = call.Break(num);
			
			System.out.println("returned "+ans);
			num=ans;
			if((int)(ans/10)==0)
			{
				break;
			}
			System.out.println("Sent "+num);
			
			
		}
		System.out.println(ans);
	}

}
