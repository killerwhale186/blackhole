
package org.galaxy.labs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
  
@SuppressWarnings({"unused"})
public class FinalGame4 {
     
    static final int switchChange = 0; 
    //used to stabilize component bounds between home computer (= 0), and school iMac (= 10). 
    //Considerably inefficient, but works exactly as intended unlike other methods attempted.
 
    //my components and my frame
    static JFrame thisFrame;
    static JLabel titleText;
    static JLabel fineTitle;
    static JLabel Quitindicator;
    static JLabel Playindicator;
    static JLabel Instructionsindicator;    
    static JLabel backIndicator;
    static JLabel Playtitle;                                    //POST ON GOOGLE CLASS AND DELETE LATER: Just wanted to quickly say that I've been overwhelmingly busy with several things for the past month or two which is why I have and still will be turning in my project updates somewhat late, as I only get time to work on my code during the weekend. Thanks. I do hope that this slight lateness wont affect my grade.
    static JLabel HowtoTitle;
    static JLabel exitTitle;
    static JLabel leave;
    static JLabel instructions;
    static JPanel mainPanel;
    static JPanel instructionPanel;
    static JLayeredPane GameScreen;
    static JLabel background;
    static JPanel HUD;
    static Ship ship;
    
    static final Dimension GAME_DIMENSION = new Dimension(1100, 650);
         
    //Images and imageIcons
    static ImageIcon title;
    static ImageIcon instructionsImage;
    static ImageIcon GameBackground;
     
    public static void main(String[] args) {
            setup(true);
    }
                                                          
     
    @SuppressWarnings("static-access")
    public static void setup(boolean newFrame) { //used to set up the menu
 
        if(newFrame) {
            thisFrame = new JFrame("Meng Chau Space Shooter");  //set up new frame and name. Frame consists of the window, name, icon, and exit tabs    
            thisFrame.setPreferredSize(GAME_DIMENSION);
        }
         
        mainPanel = new JPanel(); //set up panel which is the container of the interior of the frame
        thisFrame.add(mainPanel);
        thisFrame.setDefaultCloseOperation(thisFrame.EXIT_ON_CLOSE);
         
        mainPanel.setPreferredSize(GAME_DIMENSION);
        mainPanel.setLayout(null); //prevents the system from using preset layouts to format components
        mainPanel.setBackground(Color.BLACK);        
        mainPanel.setVisible(true); //set panel to visible
 
        thisFrame.pack(); //load in frame with all components sized to the dimensions
        thisFrame.setLocationRelativeTo(null); //load program at the center of my screen
        thisFrame.setVisible(true);
        thisFrame.setResizable(false);
          
         
         
        title = new ImageIcon("titleImage.png");
        titleText = new JLabel(title);
        mainPanel.add(titleText);                                                //I'm manually centering my labels horizontally
        titleText.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(titleText.getPreferredSize().getWidth() / 2) - 4 + switchChange, 10), titleText.getPreferredSize()));        
        
        fineTitle = new JLabel("~Created by Meng Chau~");
        mainPanel.add(fineTitle);
        fineTitle.setPreferredSize(new Dimension(163, 20));
        fineTitle.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(fineTitle.getPreferredSize().getWidth() / 2) - 6 + switchChange, 50), fineTitle.getPreferredSize()));
        fineTitle.setFont(new Font("timesRoman", Font.ITALIC, 15));
        fineTitle.setForeground(Color.WHITE);       
          
         
         
        Playtitle = new JLabel("[START GAME]");
        mainPanel.add(Playtitle);
        Playtitle.setPreferredSize(new Dimension(220, 35));
        Playtitle.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(Playtitle.getPreferredSize().getWidth() / 2) - 4 + switchChange, 233), Playtitle.getPreferredSize()));
        Playtitle.setFont(new Font("TimesRoman", Playtitle.getFont().getStyle(), 30));
        Playtitle.setForeground(Color.WHITE);
          
        HowtoTitle = new JLabel("[INSTRUCTIONS]");
        mainPanel.add(HowtoTitle);
        HowtoTitle.setPreferredSize(new Dimension(251, 35));
        HowtoTitle.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2  - (int)(HowtoTitle.getPreferredSize().getWidth() / 2) - 4 + switchChange, 308), HowtoTitle.getPreferredSize()));
        HowtoTitle.setFont(new Font("TimesRoman", HowtoTitle.getFont().getStyle(), 30));
        HowtoTitle.setForeground(Color.WHITE);
          
        exitTitle = new JLabel("[QUIT]");
        mainPanel.add(exitTitle);
        exitTitle.setPreferredSize(new Dimension(97, 35));
        exitTitle.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(exitTitle.getPreferredSize().getWidth() / 2) - 4 + switchChange, 383), exitTitle.getPreferredSize()));
        exitTitle.setFont(new Font("TimesRoman", exitTitle.getFont().getStyle(), 30));
        exitTitle.setForeground(Color.WHITE);
          
         
         
        Playindicator = new JLabel(); //default constructed labels for indication to be set by mouseInput
        mainPanel.add(Playindicator);
        Playindicator.setPreferredSize(new Dimension(282, 100));
        Playindicator.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(Playindicator.getPreferredSize().getWidth() / 2) - 4 + (int)(1.7 * switchChange), 200), Playindicator.getPreferredSize()));
        Playindicator.setFont(new Font("TimesRoman", Playindicator.getFont().getStyle(), 30));
        Playindicator.setForeground(new Color(255, 150, 0));  
          
        Instructionsindicator = new JLabel();
        mainPanel.add(Instructionsindicator);
        Instructionsindicator.setPreferredSize(new Dimension(314, 100));
        Instructionsindicator.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(Instructionsindicator.getPreferredSize().getWidth() / 2) - 4 + (int)(1.7 * switchChange), 276), Instructionsindicator.getPreferredSize()));
        Instructionsindicator.setFont(new Font("TimesRoman", Instructionsindicator.getFont().getStyle(), 30));
        Instructionsindicator.setForeground(new Color(255, 150, 0));  
          
        Quitindicator = new JLabel();
        mainPanel.add(Quitindicator);
        Quitindicator.setPreferredSize(new Dimension(162, 100));
        Quitindicator.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(Quitindicator.getPreferredSize().getWidth() / 2) - 4 + (int)(2 * switchChange), 350), Quitindicator.getPreferredSize()));
        Quitindicator.setFont(new Font("TimesRoman", Quitindicator.getFont().getStyle(), 30));
        Quitindicator.setForeground(new Color(255, 150, 0));  
          
         
         
        Playtitle.addMouseListener(new MouseAdapter() { //implements mouse functions of the mouseListener for certain components        
            @Override
            public void mouseClicked(MouseEvent e) { //mouseEvent is an object passed when any mouse activity is detected within the assigned component or container
                thisFrame.remove(mainPanel); //remove menu panel, which includes my components, the load in a new one with game state method
                play();
            }
            @Override
            public void mouseEntered(MouseEvent e) { //when hovering or not hovering over specified component, modify indication label
               Playindicator.setText(">                               <");
               Playtitle.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
               Playtitle.setForeground(new Color(130, 130, 130));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Playindicator.setText("");
                Playtitle.setForeground(Color.WHITE);
            }          
        }
        );     
        HowtoTitle.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseClicked(MouseEvent e) {
                thisFrame.remove(mainPanel);
                instruct();
            }           
            @Override
            public void mouseEntered(MouseEvent e) {
               Instructionsindicator.setText(">                                   <");
               HowtoTitle.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
               HowtoTitle.setForeground(new Color(130, 130, 130));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Instructionsindicator.setText("");
                HowtoTitle.setForeground(Color.WHITE);
            }
        }
        );
        exitTitle.addMouseListener(new MouseAdapter() {         
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(1);
            }           
            @Override
            public void mouseEntered(MouseEvent e) {
               Quitindicator.setText(">                <");
               exitTitle.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
               exitTitle.setForeground(new Color(130, 130, 130));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Quitindicator.setText("");
                exitTitle.setForeground(Color.WHITE);
            }
        }
        );
    }
      
      
    public static void instruct() {
        instructionPanel = new JPanel(); //set up instructions panel  
        instructionPanel.setPreferredSize(GAME_DIMENSION);        
        instructionPanel.setLayout(null);
        instructionPanel.setBackground(Color.BLACK);
        instructionPanel.setVisible(true);        
        thisFrame.add(instructionPanel);
        thisFrame.setVisible(true);
         
         
             
        leave = new JLabel("[BACK]"); //indicator and label are placed before so that the image does not layer on top of my components
        instructionPanel.add(leave);
        leave.setForeground(Color.WHITE);
        leave.setPreferredSize(new Dimension(108, 35));
        leave.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(leave.getPreferredSize().getWidth() / 2) - 4 + (int)(1.4 * switchChange), 510), leave.getPreferredSize()));
        leave.setFont(new Font("TimesRoman", leave.getFont().getStyle(), 30));
         
        backIndicator = new JLabel();
        instructionPanel.add(backIndicator);
        backIndicator.setPreferredSize(new Dimension(170, 100));
        backIndicator.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(backIndicator.getPreferredSize().getWidth() / 2) - 4 + (int)(2.4 * switchChange), 478), backIndicator.getPreferredSize()));
        backIndicator.setFont(new Font("TimesRoman", backIndicator.getFont().getStyle(), 30));
        backIndicator.setForeground(new Color(255, 150, 0));  
         
        instructionsImage = new ImageIcon("instructionsImage.png"); //set up custom instructions image
        instructions = new JLabel(instructionsImage);
        instructionPanel.add(instructions);
        instructions.setBounds(new Rectangle(new Point(thisFrame.getWidth() / 2 - (int)(instructions.getPreferredSize().getWidth() / 2) - 4 + switchChange, 20), instructions.getPreferredSize()));
                       
       leave.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               thisFrame.remove(instructionPanel); //same idea as when removing the menu panel, remove instructions panel.
               setup(false); //call false so that a new frame is not created and all components, labels, and panels stay in the same interface
           }           
           @Override
           public void mouseEntered(MouseEvent e) {
               backIndicator.setText(">                 <");
               leave.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
               leave.setForeground(new Color(130, 130, 130));
           }
           @Override
           public void mouseExited(MouseEvent e) {
               backIndicator.setText("");
               leave.setForeground(Color.WHITE);
           }
       });
        
    }
      
    public static void play() {
          
         GameScreen = new JLayeredPane();
         GameScreen.setPreferredSize(new Dimension(thisFrame.getWidth() - 200, (int)GAME_DIMENSION.getHeight()));
         //GameScreen.setBounds(new Rectangle(new Point(0,0)));
         //GameScreen.setLayout(null);
         GameScreen.setVisible(true);
         thisFrame.add(GameScreen, BorderLayout.WEST);
          
         GameBackground = new ImageIcon("spaceBackground.png");
         background = new JLabel(GameBackground);
         background.setPreferredSize(new Dimension(thisFrame.getWidth() - 200, (int)GAME_DIMENSION.getHeight()));
         background.setBounds(new Rectangle(new Point(0,0), background.getPreferredSize()));
         
         int shipX = (int)MouseInfo.getPointerInfo().getLocation().getX();
         int shipY = (int)MouseInfo.getPointerInfo().getLocation().getY();
         ship = new Ship(shipX, shipY, 1000);

         //add ship first (on top), then background (bottom)
         GameScreen.add(ship.getShipImage());
         GameScreen.add(background);
         
         HUD = new JPanel();
         HUD.setPreferredSize(new Dimension(200, (int)GAME_DIMENSION.getHeight()));
         //HUD.setBounds(new Rectangle(new Point(thisFrame.getWidth() - 200, 0)));
         //HUD.setLayout(null);
         HUD.setBackground(Color.GREEN);
         HUD.setVisible(true);
         thisFrame.add(HUD, BorderLayout.EAST);

         thisFrame.pack();
         thisFrame.setVisible(true);

         background.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseEntered(MouseEvent e) {
                 System.out.println("mouseEntered");
                 //ship.updateShipPos();
                 GameScreen.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
             }
             @Override
             public void mouseMoved(MouseEvent e) {
                 System.out.println("mouseMoved");
                 ship.updateShipPos();
             }
             @Override
             public void mouseExited(MouseEvent e) {
                 System.out.println("mouseExited");
                 GameScreen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             }
         });
    }    
     
     
 
    public static class Ship {
         
        private int health;
        private ImageIcon sprite;
        private JLabel shipImage;
        private int x, y;
        
        public Ship(int xLocation, int yLocation, int health) {
            this.x = xLocation; //references aspects of the current ship object
            this.y = yLocation;
            this.health = health;
            sprite = new ImageIcon("spaceShip.png");
            shipImage = new JLabel(sprite);
            shipImage.setLayout(null);
            shipImage.setPreferredSize(new Dimension(50,50));
            shipImage.setBounds(new Rectangle(new Point(x, y), shipImage.getPreferredSize()));
        }

        public JLabel getShipImage() {
            return shipImage;
        }
        
        public int getHealth() {
            return health;
        }
         
        public int getX() {
            return x;
        }
         
        public int getY() {
            return y;
        }
         
        public void updateShipPos() {
            System.out.println("updateShipPos() called");
            shipImage.setBounds(new Rectangle(new Point((int)MouseInfo.getPointerInfo().getLocation().getX(),
                    (int)MouseInfo.getPointerInfo().getLocation().getY()), shipImage.getPreferredSize()));
        }
         
        public void damage(int dmg) {
            this.health -= dmg;
        }
    }
     
     
    public static class Bullet {
        private int type;
        private int damage;
        private int speed;
    }
     
}
