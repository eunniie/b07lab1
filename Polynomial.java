import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Polynomial{
	double [] coefficients;
	int [] exponents;

	public Polynomial(){
		coefficients = new double[1];
		coefficients[0] = 0;
		exponents = new int[1];
		exponents[0] = 0;
	}

	public Polynomial(double [] co, int [] exp){
		coefficients = new double[co.length];
		exponents = new int[exp.length];

		for (int i = 0; i < co.length; i ++){
			coefficients[i] = co[i];
			exponents[i] = exp[i];
		}
	}
	
	public Polynomial(File f){
		try {
			ArrayList<Integer> e= new ArrayList<Integer>();
			ArrayList<Double> c= new ArrayList<Double>();
			List<String[]> p = new ArrayList<String[]>();
			
			Scanner scan = new Scanner(f);
			String s = scan.nextLine();
			
			String [] parts = s.split("(?=[\\-])|\\+");

			
			for(String part: parts) {
				p.add(part.split("x"));
		    }
			
			for (int i = 0; i< p.size(); i++) {
				c.add(Double.parseDouble(p.get(i)[0]));
				if (p.get(i).length == 1) {
					e.add(0);
				}else {
					e.add(Integer.parseInt(p.get(i)[1]));
				}
				
			}
			
			coefficients = new double [c.size()];
			exponents = new int [e.size()];
			
			for(int i = 0; i<c.size();i++) {
				coefficients[i] = c.get(i) ;
				exponents[i] = e.get(i);
	        }
			
			
			scan.close();
			
		} catch (FileNotFoundException e) {
			System.out.print("File not found");
			e.printStackTrace();
		}
		
	
		
	}
	
	public Polynomial sort(ArrayList<Integer> exp, ArrayList<Double> coef ) {
		ArrayList<Integer> e = new ArrayList<Integer>();
		for (int i = 0; i < exp.size(); i ++){
			e.add(exp.get(i));
		}

		Collections.sort(exp);

		for (int i = 0; i< exp.size(); i++){
			if (e.get(i) != exp.get(i)){
				double s = coef.get(i);
				double c = coef.get(exp.indexOf(e.get(i)));
				coef.set(i,c);
				coef.set(exp.indexOf(e.get(i)),s);
				e.set(e.indexOf(exp.get(i)), e.get(i));
				e.set(i, exp.get(i));
			}
		}
		double d[] = new double[coef.size()];
		int [] expo = new int[exp.size()];
		
		Arrays.setAll(d, coef::get);
		Arrays.setAll(expo, exp::get);
		
		Polynomial r = new Polynomial(d, expo);
		return r;
		
	}

	public Polynomial add(Polynomial a){
		ArrayList<Double> coef = new ArrayList<Double>();
		ArrayList<Integer> exp= new ArrayList<Integer>();
		
		for (int i = 0; i < this.exponents.length; i ++){
			exp.add(this.exponents[i]);
			coef.add(this.coefficients[i]);
		}

		for (int i = 0; i < a.exponents.length; i ++){
			if (exp.contains(a.exponents[i])){
				double sum = coef.get(exp.indexOf(a.exponents[i]))+a.coefficients[i];
				if (sum == 0){
					coef.remove(exp.indexOf(a.exponents[i]));
					exp.remove(exp.indexOf(a.exponents[i]));
					
				}else{
					coef.set(exp.indexOf(a.exponents[i]), sum);
				}
				
			}else{
				exp.add(a.exponents[i]);
				coef.add(a.coefficients[i]);
			}
		}

		Polynomial r = this.sort(exp, coef);
		return r;

	}

	public double evaluate (double x){
		double add = 0;
		for (int i = 0; i < this.coefficients.length; i++){
			add = add + (this.coefficients[i] * Math.pow(x,this.exponents[i]));
		}
		return add;
	}

	public boolean hasRoot(double y){
		if (this.evaluate(y) == 0){
			return true;
		}
		return false;
	}

	public Polynomial multiply(Polynomial p){
	int len = Math.max(this.coefficients.length, p.coefficients.length);
	ArrayList<Double> coef = new ArrayList<Double>();
	ArrayList<Integer> exp= new ArrayList<Integer>();
	ArrayList<Integer> e= new ArrayList<Integer>();
	ArrayList<Double> c= new ArrayList<Double>();

	if (len == this.coefficients.length){
		for (int i = 0; i < len; i++){
			for (int j = 0; j < p.coefficients.length; j++){		
				coef.add(this.coefficients[i]*p.coefficients[j]);
				exp.add(this.exponents[i]+p.exponents[j]);
			}
		}

	}else{
		for (int i = 0; i <len; i++){
			for (int j = 0; j < this.coefficients.length; j++){		
				coef.add(p.coefficients[i]*this.coefficients[j]);
				exp.add(p.exponents[i]+this.exponents[j]);
			}
		}
	}
	
	//get rid of duplicates:
	for (int i = 0;i<exp.size();i++) {
		double sum = coef.get(i);
		if (e.contains(exp.get(i))) {
			continue;
		}
		for (int j = i+1;j<exp.size();j++) {
			if(exp.get(i) == exp.get(j)) {
				sum += coef.get(j);
			}
			
		}
		c.add(sum);
		e.add(exp.get(i));
	}
	
	for (int i = 0;i<c.size();i++) {
		if (c.get(i)==0) {
			c.remove(i);
			e.remove(i);
		}
	}
	
	Polynomial r = this.sort(e, c);
	return r;
	}
	
	public void saveToFile(String s) {
		try {
			File f = new File(s);
			FileWriter w = new FileWriter(f);
			for (int i = 0; i<this.exponents.length; i++) {
				if (this.coefficients[i]>0 && i>0) {
					w.write("+");
				}
				w.write(Double.toString(this.coefficients[i]));
				if (this.exponents[i] != 0) {
					w.write("x");
					w.write(Integer.toString(this.exponents[i]));
				}
			}
			w.close();
		} catch (IOException e) {
			System.out.print("File not found");
			e.printStackTrace();
		}
	}
}