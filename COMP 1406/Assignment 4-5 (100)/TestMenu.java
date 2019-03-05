//Poe 100809178

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TestMenu extends JFrame implements WindowListener,ActionListener{

  private JButton bOrc;
  public void actionPerformed(ActionEvent e) {
    if ("Orc".equals(e.getActionCommand())) {
      System.out.println(e.getActionCommand());
    } else if("Human".equals(e.getActionCommand())){
      System.out.println(e.getActionCommand());
    }else if("Goblin".equals(e.getActionCommand())){
      System.out.println(e.getActionCommand());
    }else if("Elf".equals(e.getActionCommand())){
      System.out.println(e.getActionCommand());
    }
  }
  private class Clicklistener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      System.out.println("A character has been selected");

      if (e.getActionCommand() == "Elf"){World.setCharacter(e.getActionCommand());}
      else if (e.getActionCommand() == "Orc"){World.setCharacter(e.getActionCommand());}
      else if (e.getActionCommand() == "Goblin"){World.setCharacter(e.getActionCommand());}
      else if (e.getActionCommand() == "Human"){World.setCharacter(e.getActionCommand());}
      System.out.println(World.getCharacter());
      setVisible(false);
      //World.showTheWindow();

    }
    
    //TestMenu.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
  public void windowOpened(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowClosing(WindowEvent e) {}
  
  public TestMenu ()
  {
    this.setTitle("Character Select");
    this.setLayout(new GridLayout(8, 1));
    this.setDefaultLookAndFeelDecorated(true);
    this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
    JLabel msg1 = new JLabel("<html>Instructions: <br>To move the player and navigate through the rooms <br>Click on anywhere in the surface and the player<br>will advance int that direction<br><br>First you must pick a player by clicking on one of the four buttons</html>");
    JButton bElf = new JButton("Elf");
    bElf.setMnemonic(KeyEvent.VK_D);
    bElf.setActionCommand("Elf");
    bElf.setVerticalTextPosition(AbstractButton.CENTER);
    bElf.setHorizontalTextPosition(AbstractButton.LEADING);
    
    JButton bOrc = new JButton("Orc");
    bOrc.setMnemonic(KeyEvent.VK_D);
    bOrc.setActionCommand("Orc");
    bOrc.setVerticalTextPosition(AbstractButton.CENTER);
    bOrc.setHorizontalTextPosition(AbstractButton.LEADING);
    
    JButton bHuman = new JButton("Human");
    bHuman.setMnemonic(KeyEvent.VK_M);
    bHuman.setActionCommand("Human");
    bHuman.setVerticalTextPosition(AbstractButton.CENTER);
    bHuman.setHorizontalTextPosition(AbstractButton.LEADING);
    
    JButton bGoblin = new JButton("Goblin");
    bGoblin.setMnemonic(KeyEvent.VK_E);
    bGoblin.setActionCommand("Goblin");
    bGoblin.setVerticalTextPosition(AbstractButton.CENTER);
    bGoblin.setHorizontalTextPosition(AbstractButton.LEADING);
    this.setSize(600,600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Character Select");
    
    Clicklistener click= new Clicklistener();
    
    bElf.addActionListener(click);
    bHuman.addActionListener(click);
    bOrc.addActionListener(click);
    bGoblin.addActionListener(click);
    JPanel panel= new JPanel();
    JPanel panel2= new JPanel();
    JPanel panel3= new JPanel();
    JPanel panel4= new JPanel();

    panel.add(bElf);
    panel3.add(bHuman);
    panel4.add(bOrc);
    panel2.add(bGoblin);

    this.add(msg1);
    this.add(panel);
    this.add(panel2);
    this.add(panel3);
    this.add(panel4);
    this.setVisible(true);
    this.pack();
  }
  
}