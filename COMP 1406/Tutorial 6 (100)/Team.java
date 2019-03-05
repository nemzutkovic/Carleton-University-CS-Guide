public class Team implements Comparable<Team>{
  protected int wins;      // wins
  protected int losses;    // losses
  protected int draws;     // draws (tie game)
  protected String name;   // name of the team
  
  public Team(String name, int wins, int losses, int draws){
    this.name = name;
    this.wins = wins;
    this.losses = losses;
    this.draws = draws;
  }
    
  /* getters */
  public int getWins(){ return wins; }
  public int getLosses(){ return losses; }
  public int getdraws(){ return draws; }
  
  /* setters */
  public Team win(){ wins += 1; return this; }
  public Team lose(){ losses += 1; return this; }
  public Team draw(){ draws += 1; return this; }
  
  
 
}
