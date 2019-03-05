//SOLUTIONS to TUTORIAL 2 by Alex Bisaillion

public class PartB {
	public static double sqrtBabylonian(double N, double epsilon) {
		boolean flag = true;
		double M1 = (N/2);
		double M2;
		do{
			M2 = ((M1) + (N/M1))/2;
			if (Math.abs(M1-M2) < epsilon) {
				flag=false;
			}
			else {
				M1 = M2;
			}
		}while(flag);
		return M1;

	}
	public static void main(String[] args) {
		double[] nums = {4.0,16.0,36.0,44.0,65.0,365.0};
		double epsilon = 0.0001;
		for (double d:  nums) {
			double d1 = Math.sqrt(d);
			double d2 = sqrtBabylonian(d,epsilon);
			System.out.println("Square root of " + d + " using Math.sqrt: " + d1);
			System.out.println("Square root of " + d + " using sqrtBabylonian: " + d2);
			System.out.println("Percent difference: " + ((d2-d1)/d2)*100.0 + "%");
			System.out.println();
		}
	}
}
