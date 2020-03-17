
public class Caller {

	public static void main(String a[]) {
		Interface obj;
		obj=new Child();
		obj.calculate();
		obj=new Child1();
		obj.calculate();
	}
}
