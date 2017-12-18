public class Test {
	
	public static void main(String[] args) {
		
		ComplexNumber a = new ComplexNumber(1, 2);
		ComplexNumber b = new ComplexNumber(3, 4);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		
		ComplexNumber sum = a.add(b);
		System.out.println("a + b = " + sum);
		
		ComplexNumber diff = a.minus(b);
		System.out.println("a - b = " + diff);
		
		//test multiply
		ComplexNumber prod = a.multiply(b);
		System.out.println("a * b = " + prod);

		//test norm
		double norm = a.getNorm();
		System.out.println("|a| = " + norm);
	}
}

