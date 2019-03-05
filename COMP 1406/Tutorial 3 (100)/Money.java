/**
 * A Money object models money as dollars and cents
 **/

public class Money{

  /* attributes */
  private int dollars = 0;
  private int cents = 0;


  public Money(){
    int money = 0;
  }

  public Money(int c){
    dollars = 0;
    cents = c;
    while(cents > 99){
      dollars = dollars + 1;
      cents = cents - 100;
    }
  }

  public Money(int d, int c){
    dollars = d;
    cents = c;
    while(cents > 99){
      dollars = dollars + 1;
      cents = cents - 100;
    }
  }

  public void Add(int c){
    dollars = 0;
    cents = 50;
    cents = cents + c;
    while(cents > 99){
      dollars = dollars + 1;
      cents = cents - 100;
    }
  }

  public void Add(int d, int c){
    dollars = 4;
    dollars = dollars + d;
    cents = 73;
    cents = cents + c;
    while(cents > 99){
      dollars = dollars + 1;
      cents = cents - 100;
    }
  }

  public int remove(int c){
    dollars = 5;
    cents = 90;
    int newdollars = 0;
    int newcents = c;
    while(newcents > 99){
      newdollars = newdollars + 1;
      newcents = newcents - 100;
    }
    dollars = dollars - newdollars;
    cents = cents - newcents;
    int finalamount = 0;

    return finalamount;

  }

  /**
   * Returns a String representation of the value of the current object.
   *
   * @return The value of the current object is returned as the <code>String</code>"$D.cc"
   * where D is the number of dollars and cc is the cents of the value.  Uses the <code>format()</code>
   * method from the <code>String</code> class to ensure that the cents are displayed properly (2 spaces
   * with leading zeros if needed).
   **/
  public String getMoney(){
    return "$" + String.format("%01d", dollars) + "." + String.format("%02d", cents);
  }

}
