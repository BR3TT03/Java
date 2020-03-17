
public class SumPrime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum=0,flag=0,i,j,count=0;
		
		for(i=2;i<100;i++)
		{
			for(j=1;j<=i;j++)
			{
				if(i%j==0)
				{
					flag++;
				}
			}
			if(flag==2) {
				sum=sum+i;
				System.out.println(i);
				count++;
			}
			flag=0;
		}
		System.out.println("The sum of prime numbers from 2-100 is "+sum+ " & the total number of prime numbers is "+count);

	}
	
}
