import java.util.Arrays;

public final class TestBox{
  private static String[] strings = {"kitten", "cat", "dog", "oh", "cow", "oxen"}; 
  
  public static void main(String[] args){
    Box[] boxes = new Box[ strings.length ];
    for(int i=0; i<strings.length; i+=1){
      boxes[i] = new Box(strings[i]);
    }
    
    System.out.println("before sorting ------------------------");
    System.out.println( Arrays.toString(boxes) );
    Arrays.sort(boxes);
    System.out.println("after sorting -------------------------");
    System.out.println( Arrays.toString(boxes) );
    
  }
}