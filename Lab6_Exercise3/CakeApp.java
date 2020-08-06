import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//required for border
import javax.swing.BorderFactory;
import javax.swing.border.Border;
//required for file IO
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
//required for exception
import java.io.IOException;
//required for decimal number
import java.text.DecimalFormat;

//Cake Panel
class CakePanel extends JPanel implements ActionListener, ItemListener{
DecimalFormat df = new DecimalFormat("0.00");

//list all UI components for the panel
   JLabel lbl_line1;
   JLabel lbl_menu;
   JLabel lbl_line2;
   JLabel lbl_cakemenu;
   JLabel lbl_topping;
   JLabel lbl_size;  
   JLabel lbl_total;
   JLabel lbl_output; 
   JLabel lbl_quantity; 
   JRadioButton s1;
   JRadioButton s2;
   JRadioButton s3;
   JButton b_finish;
   JScrollPane jsp;
   JCheckBox t1;
   JCheckBox t2;
   JCheckBox t3;
   JTextField quantity;
   Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
   
   //global variable  
   String output="";
   String t_selection="";
   String s_selection="";
   String filePath="cake.txt"; //in the same directory
   int totalprice, multiply, q, a;

   public CakePanel(){   
        
      lbl_line1 = new JLabel ("*******************************************************************************************");
      lbl_menu = new JLabel ("Welcome to Cake Order System");
      lbl_line2 = new JLabel ("*******************************************************************************************");
      lbl_cakemenu = new JLabel ("Cake      :   Blackforest");
      lbl_topping = new JLabel ("Topping :");
      lbl_size = new JLabel ("Size        :");
      lbl_quantity = new JLabel("Quantity: ");
      lbl_total = new JLabel ("Total Price :");
      

        //adjust size and set layout
        setPreferredSize (new Dimension (735, 522));
        setLayout (null);

        //add components
        add (lbl_line1);
        add (lbl_menu);
        add (lbl_line2);
        add (lbl_cakemenu);
        add (lbl_topping);
        add (lbl_size);
        add (lbl_quantity);
        add (lbl_total);
      
        //set component bounds (only needed by Absolute Positioning)
        lbl_line1.setBounds (70, 15, 700, 25);
        lbl_menu.setBounds (220, 35, 600, 25);
        lbl_line2.setBounds (70, 55, 700, 25);
        lbl_cakemenu.setBounds (20, 80, 140, 25);
        lbl_topping.setBounds (20, 110, 100, 25);
        lbl_size.setBounds (20, 140, 100, 25);
        lbl_quantity.setBounds (20,180,100,25);
        lbl_total.setBounds (20, 225, 200, 25);
        
        //textfield
        quantity = new JTextField(15);
        quantity.setBounds(75,180,100,25); 
        add (quantity);
      
        //Radio buttons and action listener  
        t1 = new JCheckBox("Chocolate");
        t1.setBounds (75, 110, 100, 25);
        t1.addItemListener(this);
        add (t1);
      
         t2 = new JCheckBox("Cherries");
         t2.setBounds (175, 110, 90, 25);
         t2.addItemListener(this);
         add (t2);
      
         t3 = new JCheckBox("Whipped Cream");
         t3.setBounds (265, 110, 140, 25);
         t3.addItemListener(this);
         add (t3);

         s1 = new JRadioButton("Small (RM45.00)");
         s1.setBounds (75, 140, 120, 25);
         s1.addActionListener(this);
         add (s1);
      
         s2 = new JRadioButton("Medium (RM65.00)");
         s2.setBounds (200, 140, 140, 25);
         s2.addActionListener(this);
         add (s2);
      
         s3 = new JRadioButton("Big (RM80.00)");
         s3.setBounds(340, 140, 130, 25);
         s3.addActionListener(this);
         add (s3);
      
         ButtonGroup bg = new ButtonGroup();
         bg.add(s1);
         bg.add(s2);
         bg.add(s3);
                  
         b_finish = new JButton ("Finish");
         b_finish.setBounds (20, 270, 70, 25);         
         add (b_finish);

         //handle button submit action listener
         //view the input to output label
         //and write to file
         b_finish.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e){  
            //call method              
            if(printOutput())
               writeInput(); 
               lbl_quantity.setText("Quantity: ");
               lbl_total.setText("Total Price : RM"+df.format(multiply));   
    
         }  
      });
         
      lbl_output = new JLabel("Here is your Receipt");
      lbl_output.setBounds (20, 300, 400, 25);
      lbl_output.setBorder(border);
      lbl_output.setVerticalAlignment(JLabel.TOP);
      
      //add output label to scrollpane
      jsp = new JScrollPane(lbl_output);
      jsp.setBounds (20, 300, 400, 200);
      add(jsp);   
   }

      //handle radio button selection
   public void actionPerformed(ActionEvent ae) {
      s_selection = ae.getActionCommand();    	   
   }
   
   //handle item listener for checkbox
   public void itemStateChanged(ItemEvent ie) {
      JCheckBox check = (JCheckBox)ie.getSource();
      t_selection += check.getText() + " ";   
   }

   
     //method to print output to outputlabel
    public boolean printOutput(){
      output = "<html>";
      output += "Thank you for your order. Please come again. <br><br>";   
      output += "Cake: Blackforest<br>";
      output += "Topping: " +  t_selection + "<br>";
      q = Integer.parseInt(quantity.getText());
      if(t1.isSelected()){
      totalprice += 8;
      }if(t2.isSelected()){
     totalprice += 8;
      }if(t3.isSelected()){
      totalprice += 8;
      }
      
      if(s1.isSelected()){
         multiply = totalprice + (45 * q);
      }else if(s2.isSelected()){
         multiply = totalprice + (65 * q);
      }else if(s3.isSelected()){
         multiply = totalprice + (80 * q);
           }
      
      output += "Size: " + s_selection + "<br>";
      output += "Quantity: "+ q + "<br>";
      output += "Total: RM" + df.format(multiply) + "<br>";
      output += "Add RM8 for each topping" + "<br>";
      output += "</html>";          
      lbl_output.setText(output);
      jsp.getViewport().revalidate();
      return true;
    }
      
     //write to file
    public void writeInput(){
      File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		PrintWriter pr = null;
       
      String input =  s_selection + ", " + t_selection + ","  +  quantity.getText() +", RM" + df.format(multiply);
       
   //exception implementation
		try {
			// to append to file, you need to initialize FileWriter using below constructor
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			pr.println(input);
		} catch (IOException e) {			
         lbl_output.setText(e.toString());
		} finally {
			try {
				pr.close();
				br.close();
				fr.close();
			} catch (IOException e) {
				lbl_output.setText(e.toString());
			}
		}
    }
}

class MenuActionListener implements ActionListener {
   CakePanel fp;
   //receive CakePanel class to this constructor
   public MenuActionListener(CakePanel p){
      fp = p;
   }
   
   public void actionPerformed(ActionEvent e) {
      BufferedReader reader;
	   try {
			reader = new BufferedReader(new FileReader(fp.filePath));
			String line = reader.readLine();
         String output="<html>";
			while (line != null) {
				output += line + "<br>";
				// read next line
				line = reader.readLine();
			}
         output += "<br>";
         fp.lbl_output.setText(output);
			reader.close();
		} catch (IOException io) {
			fp.lbl_output.setText(io.toString());
		}

  }
}
//run the application using this main
public class CakeApp {  
   public static void main(String[] 	args) {  
      JFrame f = new JFrame("Cake App");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      //load panels
      HeaderPanel h = new 	HeaderPanel();
      HeaderPanel h2 = new 	HeaderPanel();
      CakePanel fp = new CakePanel();
      
      JMenuBar mb = new JMenuBar(); 
      // create a menu 
      JMenu x = new JMenu("Cake Menu"); 
      
      // create menuitems 
      JMenuItem m1 = new JMenuItem("View Data"); 
      // attach listener and send CakePanel class
      m1.addActionListener(new MenuActionListener(fp));
      
     JMenuItem m2 = new JMenuItem("Exit");  
      m2.addActionListener((event) -> System.exit(0));
      // add menu items to menu 
      x.add(m1); 
      x.add(m2);
     
      // add menu to menu bar 
      mb.add(x); 
      // add menubar to frame 
      f.setJMenuBar(mb);  
               
      //add panels to frame      
      f.add(fp);
      f.pack();
      f.setVisible(true);
   }  
}
