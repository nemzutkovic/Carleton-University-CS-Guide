import java.util.Random;

public class Flip {

  public static void main(String[] args){
    String argument = args[0];
    double bias = Double.parseDouble(argument);

    String secondargument = args[1];
    int flips = Integer.parseInt(secondargument);

    Random die = new Random();

    int headscounter = 0;
    int tailscounter = 0;

    for(int i = 0; i < flips; i += 1){
      double dieroll = die.nextDouble();
      if (dieroll >= 0 && dieroll < bias){
        headscounter += 1;
      }
      if (dieroll > bias && dieroll <= 1){
        tailscounter += 1;
      }
    }
    System.out.println(headscounter + " heads, " + tailscounter + " tails, " + bias + " bias, " + flips + " flips");
  }
}
