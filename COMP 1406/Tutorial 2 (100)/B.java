

public class B{

  public static double sqrtBabylonian(double N, double epsilon){
    double m1 = N/2;
    double m2 = ((m1 + (N/m1))/2);
    if(m1-m2 < epsilon){
      break;
      return m2;
    }else{
      m1 = m2;
      continue;
    }
  }



  public static void main(String[] args){
    System.out.println(B.sqrtBabylonian(50.5, 0.00001));
  }
}
