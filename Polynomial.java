public class Polynomial{
	double [] coefficients;

	public Polynomial(){
		coefficients = new double[1];
		coefficients[0] = 0;
	}

	public Polynomial(double [] co){
		coefficients = new double[co.length];
		for (int i = 0; i < co.length; i ++){
			coefficients[i] = co[i];
		}
	}

	public Polynomial add(Polynomial a){
		Polynomial r = new Polynomial(a.coefficients);
		int len = Math.max(r.coefficients.length, this.coefficients.length);
		if (len == r.coefficients.length){
			for (int i = 0; i < this.coefficients.length; i ++){
			r.coefficients[i] += this.coefficients[i];
			}
			return r;
		}else {
			for (int i = 0; i < len; i ++){
			this.coefficients[i] += r.coefficients[i];
			}
			return this;
		}
	}

	public double evaluate (double x){
		
		double add = 0;
		for (int i = 0; i < coefficients.length; i ++){
			add = add + (coefficients[i] * Math.pow(x,i));
		}
		return add;
	}

	public boolean hasRoot(double y){
		if (this.evaluate(y) == 0){
			return true;
		}
		return false;
	}
}