public class Problem3{

  /*****************************************************************************
   * Purpose : computes the arraylength and locations of all the maximal       *
   *           sequences of consecutive true occurrences in values             *
   * Inputs  : values is a non-null array of booleans with arraylength >= 1    *
   * Outputs : outputs an integer array with one or more elements              *
   *              - first element is the arraylength of a maximal sequence of  *
   *                consecutive trues in the input array values                *
   *              - the next elements are the indexes of the starting points   *
   *                (in the input array values) of each of the maximal         *
   *                sequences of consecutive trues                             *
   *           if there are no true values in the input array, output [0]      *
   ****************************************************************************/

  public static int[] longestStreak(boolean[] values){

    /****************************************************************************
     * The purpose of this for loop is to store the maximal sequence of         *
     * consecutive trues in the variable called truecounter.                     *
     ****************************************************************************/

    int truecounter = 0;
    int counter = 0;
    for(int i = 0; i < values.length; i += 1){
      if(values[i] == true){
        counter += 1;
        if(counter > truecounter){
          truecounter = counter;
        }
        }else{
          counter = 0;
      }
    }

    /**************************************************************************
     * The purpose of this for loop is to determine the length of the integer *
     * array we will be returning at the very end of this problem. This       *
     * information will be stored in the variable called frequency.           *
     **************************************************************************/

    int maxtrues = 0;
    int frequency = 0;
    for(int i = 0; i < values.length; i += 1){
      if(values[i] == true){
        maxtrues += 1;
        if(maxtrues == truecounter){
          frequency += 1;
          maxtrues = 0;
        }
      }else if(values[i] == false){
        maxtrues = 0;
      }
    }

    /**************************************************************************
     * This creates the list we will be returning at the end of the problem   *
     * and stores the maximal sequence of trues in the first element [0].     *
     **************************************************************************/

    int[] outputlist = new int[1 + frequency];
    outputlist[0] = truecounter;

    /**************************************************************************
     * The purpose of this final for loop is to store all the indices of when *
     * the maximal sequence of trues occured. If none, the program returns 0. *
     **************************************************************************/

    int indexchecker = 0;
    int arrayindex = 0;
    for(int i = 0; i < values.length; i += 1){
      if(values[i] == true){
        indexchecker += 1;
      }
      if(indexchecker == truecounter){
        if(truecounter == 0){
          break;
        }
        outputlist[arrayindex + 1] = i - indexchecker + 1;
        indexchecker = 0;
        arrayindex += 1;
      }else if(values[i] == false){
          indexchecker = 0;
      }
    }

    /**************************************************************************
     * NOTE: java.util.Arrays.toString() must be used to return outputlist.   *
     **************************************************************************/

    return outputlist;
  }
}
