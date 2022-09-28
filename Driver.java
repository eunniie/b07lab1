import java.io.File;
import java.util.Arrays;


public class Driver {
	public static void main(String [] args) { 
		Polynomial p = new Polynomial(); 
		System.out.println(p.evaluate(3)); 
		double [] t1 = {-6,5,4,-3}; 
		int [] t2 = {0,2,3,10}; 
		Polynomial t = new Polynomial(t1,t2); 
		double [] a1 = {-1,2}; 
		int [] a2 = {0,1}; 
		Polynomial a = new Polynomial(a1,a2);
		Polynomial s = a.add(t); 
		System.out.println("add coef = " + Arrays.toString(s.coefficients));
		System.out.println("add exp = " + Arrays.toString(s.exponents));
		System.out.println("t(0.1) = " + t.evaluate(-0.5)); 
		if(t.hasRoot(0.5)) 
			System.out.println("1 is a root of t"); 
		else 
			System.out.println("1 is not a root of t"); 
		s = a.multiply(t); 
		System.out.println("* coef = " + Arrays.toString(s.coefficients));
		System.out.println("* exp = " + Arrays.toString(s.exponents));
		
		File f = new File("C:\\Users\\vicky\\Downloads\\file.txt");
		Polynomial test = new Polynomial(f);
		System.out.println(" coef = " + Arrays.toString(test.coefficients));
		System.out.println(" exp = " + Arrays.toString(test.exponents));
		
		t.saveToFile("C:\\Users\\vicky\\Downloads\\file.txt");
		 } 
		
		
}