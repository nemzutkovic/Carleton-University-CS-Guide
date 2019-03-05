import java.util.Scanner;
import java.util.Arrays;

public class Pythagorus{

  /**********************************************************
   * Purpose : determine if a,b,c form a Pythagorean Triple *
   *           where c is the hypotenuse                    *
   * Input : a,b,c are each non-negative integers           *
   * Output : output true if c^2 = a^2 + b^2                *
   *          output false if not                           *
   **********************************************************/

  public static boolean isPythagorusInt(int a, int b, int c){
    boolean flag;
    if((c*c) == ((a*a) + (b*b))){
      flag = true;
    }else{
      flag = false;
    }
    return flag;
  }

  /*************************************************************************
  * Purpose : determine if a,b,c satisfy Pythagorus' Theorem up to epsilon *
  *           where c is the hypotenuse                                    *
  * Input : a,b,c are each non-negative doubles                            *
  *         epsilon is a non-negative doubles                              *
  * Output : outputs true if a,b,c satisfy Pyhtagorus' Theorem             *
  *          up to the given epsilon                                       *
  *          - outputs false if not                                        *
  **************************************************************************/

  public static boolean isPythagorusDouble(double a, double b, double c, double epsilon){
    boolean flag;
    if(((c*c) - ((a*a) + (b*b))) < epsilon){
      flag = true;
    }else{
      flag = false;
    }
    return flag;
  }

  public static void main(String[] args){
    Scanner keyboard = new Scanner(System.in);
    String input = "";

    /**********************************************************
     * The while loop is used to check for proper user input. *
     **********************************************************/

    while(!input.equals("int") || !input.equals("float") || !input.equals("quit")){
      System.out.print("Enter int, float, or quit: ");
      input = keyboard.nextLine();

      /************************************************************************
       * If the user enters "int" the program lets the user input 3 integers, *
       * seperated by a comma and space. Pythagorus's Theorem is evaluated.   *
       ************************************************************************/

      if(input.equals("int")){
        Scanner intuser = new Scanner(System.in);
        System.out.print("Enter triple: ");

        String integerinput;
        String[] initialarray;
        integerinput = intuser.nextLine();
        initialarray = integerinput.split("");
        
        String nospaces = "";
        for(int i = 0; i < initialarray.length; i += 1){
          if(!initialarray[i].equals(" ")){
            nospaces += initialarray[i];
          }
        }

        String[] threeintegers;
        threeintegers = nospaces.split(",");

        int int1 = Integer.parseInt(threeintegers[0]);
        int int2 = Integer.parseInt(threeintegers[1]);
        int int3 = Integer.parseInt(threeintegers[2]);

        System.out.print(isPythagorusInt(int1, int2, int3));

        break;
        }

        /*************************************************************************
         * If the user enters "float" the program lets the user input 3 doubles, *
         * seperated by a comma and space. Pythagorus's Theorem, up to epsilon   *
         * is evaluated.                                                         *
         *************************************************************************/

      if(input.equals("float")){
        Scanner dubuser = new Scanner(System.in);
        System.out.print("Enter triple: ");

        String doubleinput;
        String[] initialarray;
        doubleinput = dubuser.nextLine();
        initialarray = doubleinput.split("");

        String nospaces = "";
        for(int i = 0; i < initialarray.length; i += 1){
          if(!initialarray[i].equals(" ")){
            nospaces += initialarray[i];
          }
        }

        String[] threedoubles;
        threedoubles = nospaces.split(",");

        double dub1 = Double.parseDouble(threedoubles[0]);
        double dub2 = Double.parseDouble(threedoubles[1]);
        double dub3 = Double.parseDouble(threedoubles[2]);
        double epsilon = Double.parseDouble(threedoubles[3]);

        System.out.print(isPythagorusDouble(dub1, dub2, dub3, epsilon));
        break;
        }

        /************************************************************************
         * If the user enters "quit", the program ends.                         *
         ************************************************************************/

      if(input.equals("quit")){
        System.out.print("Goodbye");
        break;
      }
      }
    }
  }
