
public class ThreadDemo implements Runnable  {

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=-21;i>=-51;i--) {
		//prints odd number
			if(i%2!=0)
			{
			System.out.println("Odd: "+i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {}
			}
		}
	
		for(int j=-51;j>=-21;j++) {
			if(j%2==0) {
			System.out.println("Even: "+j);
			try {
				Thread.sleep(500);
			} catch (Exception e) {} 
		}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadDemo obj = new ThreadDemo();
		Thread odd=new Thread(obj);
		Thread even=new Thread(obj);
		odd.start();
		even.start();
	}

}
