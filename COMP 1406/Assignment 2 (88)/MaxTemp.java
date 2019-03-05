import java.util.Arrays;

public class MaxTemp{

  /** t1 and t2 are considered the same if Math.abs(t1-t2) < EPSILON */
  public static final double EPSILON = 0.01;
  
  // Encapsualtes templist
  private Temperature[] templist;

  /* ----------------------------------------------------
   * constructor
   * ----------------------------------------------------
   */

  public MaxTemp(Temperature[] t){
    this.templist = t;
  }

  /* ----------------------------------------------------
   * getter
   * ----------------------------------------------------
   */

    // Returns null if empty array or null was passed into constructor
  public double[] getMax(){
    if(templist == null || templist.length == 0){
      return null;
    }

    // Sets all temperature values in the array to Kelvin temperatures
    for(int i = 0; i < templist.length; i++){
      templist[i].setScale("K");
    }

    // Finds the max Kelvin temperatures in the array
    double maxtemp = 0;
    for(int i = 0; i < templist.length; i++){
      if(templist[i].getTemp() > maxtemp){
        maxtemp = templist[i].getTemp();
      }
    }

    // Finds the number of occurences of the max Kelvin Temperature in the array up to Epsilon
    int maxtempcounter = 0;
    for(int i = 0; i < templist.length; i++){
      if((maxtemp - templist[i].getTemp()) < EPSILON){
        maxtempcounter += 1;
      }
    }

    // - otherwise, returns an array of length 2 [max, count]
    //   where max is the maximum temperature (expressed in the Kelvin scale)
    //   of all Temperature objects passed to the constructor, and count
    //   is the number of times that temperature was present (in the input
    //   array of the constructor)

    return new double[]{maxtemp, maxtempcounter};
  }

  /* ----------------------------------------------------
   * Testing Code
   * ----------------------------------------------------
   */

  /*public static void main(String[] args){
    MaxTemp testlist =  new MaxTemp(new Temperature[]{
      new Temperature(1001.12, "K"),
      new Temperature(-200.0, "F"),
      new Temperature(1001.11, "K")
    });
    System.out.println(Arrays.toString(testlist.getMax()));                   
  }*/
}
