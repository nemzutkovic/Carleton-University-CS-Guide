public class Convert{

  /***************************************************************************
   * Purpose: converts a given numerical grade to a letter grade             *
   * Input  : a number                                                       *
   * output : the letter grade (F, D-, D, ..., A+) corresponding to the      *
   *          input grade if the input is valid, "Invalid" otherwise         *
   ***************************************************************************/

   public static String convertToLetter(double grade){
     String letter = "";
     if(grade >= 90 && grade <= 100){
       letter = "A+";
     }else if(grade >= 85 && grade < 90){
       letter = "A";
     }else if(grade >= 80 && grade < 85){
       letter = "A-";
     }else if(grade >= 77 && grade < 80){
       letter = "B+";
     }else if(grade >= 73 && grade < 77){
       letter = "B";
     }else if(grade >= 70 && grade < 73){
       letter = "B-";
     }else if(grade >= 67 && grade < 70){
       letter = "C+";
     }else if(grade >= 63 && grade < 67){
       letter = "C";
     }else if(grade >= 60 && grade < 63){
       letter = "C-";
     }else if(grade >= 57 && grade < 60){
       letter = "D+";
     }else if(grade >= 53 && grade < 57){
       letter = "D";
     }else if(grade >= 50 && grade < 53){
       letter = "D-";
     }else if(grade >= 0 && grade < 50){
       letter = "F";
     }else{
       letter = "Invalid";
     }
     return letter;
   }

   /**************************************************************************
    * Purpose: converts a given letter grade to its equivalent grade point   *
    * Input  : A valid letter grade in the range D- to A+ (no F's)           *
    * output : The grade point corresponding to the input letter grade       *
               Use 0 for F and -1 for Invalid input                          *
    * Note   : you MUST use a switch/case for this method                    *
    **************************************************************************/

    public static int convertToGradePoint(String letterGrade){
      int gradepoint = 0;
      switch(letterGrade){
        case "A+": gradepoint = 12;
        break;
        case "A": gradepoint = 11;
        break;
        case "A-": gradepoint = 10;
        break;
        case "B+": gradepoint = 9;
        break;
        case "B": gradepoint = 8;
        break;
        case "B-": gradepoint = 7;
        break;
        case "C+": gradepoint = 6;
        break;
        case "C": gradepoint = 5;
        break;
        case "C-": gradepoint = 4;
        break;
        case "D+": gradepoint = 3;
        break;
        case "D": gradepoint = 2;
        break;
        case "D-": gradepoint = 1;
        break;
        case "F": gradepoint = 0;
        break;
        default: gradepoint = -1;
        break;
      }
      return gradepoint;
    }
}
