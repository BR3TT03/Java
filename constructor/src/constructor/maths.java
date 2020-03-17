package constructor;

import java.lang.Math;
import java.util.Scanner;

public class maths {
	double Num, SquareRoot, Sum;
	private static Scanner input;

	maths() {
		Num = 64;
	}

	void sqrt() {
		SquareRoot = Math.sqrt(Num);
	}

	void add(double usrdata) {
		Sum = 64 + usrdata;
	}

	void show() {
		System.out.println("Sqrt of 64 is: " + SquareRoot + "\n" + "Sum is: " + Sum);
	}

	public static void main(String[] args) {
		input = new Scanner(System.in);
		maths obj = new maths();
		double usrdata;
		System.out.println("Enter a number to add to 64");
		usrdata = input.nextDouble();
		obj.sqrt();
		obj.add(usrdata);
		obj.show();
		int sq;
		sq=input.nextInt();
		System.out.println("Square root of "+sq+" is :"+Math.sqrt(sq));

	}

}
