//  Notepad

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Notepad extends JFrame{
  JTextArea note;
  JMenuBar bar;
  JMenu file , edit , help , view;
  JMenuItem nw , open , save , print , exit , copy , cut , select , pest , about , font , color;
  String fileName = "";
  Notepad () {
    
    setTitle("Notepad");
    setBounds(400 , 150 , 800 , 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
    bar = new JMenuBar();
    
    file = new JMenu("File");
    bar.add(file);
    
    nw = new JMenuItem("New");
    file.add(nw);
    nw.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent e) {
        note.setText(null);
        fileName = "";
      }
    });
    nw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    // 
    open = new JMenuItem("Open");
    file.add(open);
    open.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        JFileChooser filechooser = new JFileChooser();
       int a = filechooser.showOpenDialog(null);
       if (a == JFileChooser.APPROVE_OPTION) {
         try {
           File myfile = new File(filechooser.getSelectedFile().getAbsolutePath().toString());
           Scanner sc = new Scanner(myfile);
           fileName = filechooser.getSelectedFile().getAbsolutePath().toString();
           while (sc.hasNextLine())
            note.setText(note.getText() + sc.nextLine() + "\n");
         } catch(Exception ex) {
         }
       }
      }
    });
     open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
    // 
    save = new JMenuItem("Save");
    file.add(save);
    save.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        if (fileName != "") {
          try {
          FileWriter openedFile = new FileWriter(fileName);
          openedFile.write(note.getText());  
          openedFile.close();
          } catch (Exception exp) {
            
          }
        } else {
        JFileChooser filechooser = new JFileChooser();
        int a = filechooser.showSaveDialog(null);
        if (a == JFileChooser.APPROVE_OPTION) {
          fileName = filechooser.getSelectedFile().getAbsolutePath().toString();
          try {
          FileWriter newFile = new FileWriter(fileName);
          newFile.write(note.getText());  
          newFile.close();
          } catch (Exception ex) {
            
          }
        }
        }
      }
    });
     save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    // 
    print = new JMenuItem("Print");
    file.add(print);
    print.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
      try {
            note.print();
      } catch(Exception ex) {
      }
      }
    });
     print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    // 
    exit = new JMenuItem("Exit");
    file.add(exit);
    exit.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        System.exit(0);
      }
    });
     exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
    
    // 
    
    edit = new JMenu("Edit");
    bar.add(edit);
    
    copy = new JMenuItem("Copy");
    edit.add(copy);
    copy.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        note.copy();
      }
    });
     copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    // 
    cut = new JMenuItem("Cut");
    edit.add(cut);
    cut.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        note.cut();
      }
    });
     cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    // 
    pest = new JMenuItem("Paste");
    edit.add(pest);
    pest.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        note.paste();
      }
    });
     pest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
    // 
    select = new JMenuItem("Select");
    edit.add(select);
    select.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        note.selectAll();
      }
    });
     select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    // 
    view = new JMenu("View");
    bar.add(view);
    
    font = new JMenuItem("Font");
    view.add(font);
    font.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        String ch = JOptionPane.showInputDialog("Enter size to set Font");
        int fno = Integer.parseInt(ch);
        note.setFont(new Font("Arial" , Font.PLAIN , fno));
      }
    });
    font.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
    
    // 
    color = new JMenuItem("Color");
    view.add(color);
    color.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        Color clr = JColorChooser.showDialog(null , "select color to text" , Color.BLACK);
        note.setForeground(clr);
      }
    });
    color.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
    
    // 
    help = new JMenu("Help");
    bar.add(help);
    
    about = new JMenuItem("About");
    help.add(about);
    about.addActionListener(new ActionListener () {
      public void actionPerformed (ActionEvent e) {
         new About();
      } 
    });
    
    setJMenuBar(bar);
    
    // 
    
    note = new JTextArea();
    note.setLineWrap(true);
    JScrollPane spane = new JScrollPane(note ,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    add(spane);
    note.setFont(new Font("Arial" , Font.PLAIN , 20));
    
    validate();
  }
  
  public static void main (String[] args) throws Exception {
    
    Notepad np = new Notepad();
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    
  }
  
}
class About extends JFrame {
  About () {
    setSize(500 , 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("About Notepad");
    setVisible(true);
    
    String str  = "<html>"+
    "<h3 color = red> This Notepad Was Created By Rohit Tippe. </h3>" +
    "<hr/>" +
    "<p> you can use this notepad to write , read or store files </p>"
    +"</html>";
    
    JLabel label = new JLabel(str);
    add(label);
    
    JLabel ver = new JLabel("Version 1.0");
    add(BorderLayout.SOUTH , ver);
    
  }
}
