/** basic tester for Money class */

public class TestMoney{

  public static void main(String[] args){

    Money money;
    String actual,expected;

    System.out.print("        Money() test : ");
    money = new Money();
    actual = money.getMoney();
    expected = "$0.00";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

    System.out.print("Money(int, int) test : ");
    money = new Money(0,0);
    actual = money.getMoney();
    expected = "$0.00";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

    System.out.print("Money(int, int) test : ");
    money = new Money(12,7);
    actual = money.getMoney();
    expected = "$12.07";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

    System.out.print("Money(int, int) test : ");
    money = new Money(3,4023);
    actual = money.getMoney();
    expected = "$43.23";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

    System.out.print("     Money(int) test : ");
    money = new Money(0);
    actual = money.getMoney();
    expected = "$0.00";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

    System.out.print("     Money(int) test : ");
    money = new Money(63);
    actual = money.getMoney();
    expected = "$0.63";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

    System.out.print("     Money(int) test : ");
    money = new Money(21387);
    actual = money.getMoney();
    expected = "$213.87";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

 System.out.print("     Add(int) test : ");
    money = new Money();
    money.Add(150);
    actual = money.getMoney();
    expected = "$2.00";
    System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
    if( actual.equals(expected) ){
      System.out.println(" pass");
    }else{
      System.out.println(" fail");
    }

    System.out.print("     Add(int, int) test : ");
       money = new Money();
       money.Add(2, 127);
       actual = money.getMoney();
       expected = "$8.00";
       System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
       if( actual.equals(expected) ){
         System.out.println(" pass");
       }else{
         System.out.println(" fail");
       }

    System.out.print("     remove(int) test : ");
       money = new Money();
       money.remove(205);
       actual = money.getMoney();
       expected = "$3.85";
       System.out.print("expected \"" + expected + "\"   actual output \"" + actual + "\"");
       if( actual.equals(expected) ){
         System.out.println(" pass");
       }else{
         System.out.println(" fail");
      }





  }
}
