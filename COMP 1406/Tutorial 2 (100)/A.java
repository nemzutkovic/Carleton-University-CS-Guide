import java.util.Scanner;

public class A{

  public static void main(String[] args){
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter a positive integer: ");
    int userinput = keyboard.nextInt();

    for(int i = 0; i < userinput; i += 1){
      for(int j = 0; j < userinput; j += 1){
        System.out.print("Q");
      }
      System.out.println();
    }
    System.out.println();

    for(int i = 0; i < userinput; i += 1){
      for(int j = 0; j < userinput; j +=1){
        if(j <= i){
          System.out.print("Q");
        }
      }
      System.out.println();
    }
    System.out.println();

    for(int i = 0; i < userinput; i += 1){
      for(int j = 0; j < userinput; j +=1){
        if(j >= i){
          System.out.print("Q");
        }
      }
      System.out.println();
    }
    System.out.println();

    for(int i = 0; i < userinput; i += 1){
      for(int j = 0; j < userinput; j +=1){
        if(j >= i){
          System.out.print("Q");
        }else{
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    System.out.println();

    for(int i = 0; i < userinput; i += 1){
      for(int j = 0; j < userinput; j += 1){
        if(j < (userinput-i-1)){
          System.out.print(" ");
        }else{
          System.out.print("Q");
        }
      }
      System.out.println();
    }
  }
}
