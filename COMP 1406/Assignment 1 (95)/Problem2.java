public class Problem2{

  /***************************************************************************
   * longestStreak : array of booleans -> integer                            *
   * Purpose: computes the length of a longest streak of consecutive         *
   *          true occurrences in the input argument values                  *
   * Input  : values is a non-null array of booleans with length at least 1  *
   * output : outputs the maximal number of consecutive trues found in       *
   *          the input array                                                *
   ***************************************************************************/

   public static int longestStreak(boolean[] values){
     int truecounter = 0;
     int count = 0;
     for(boolean val : values){
       if(val == true){
         count += 1;
         if(count > truecounter){
           truecounter = count;
         }
       }
       if(val == false){
         count = 0;
       }
     }
     return truecounter;
   }
}
