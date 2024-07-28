import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class App {
  public static void main(String[] args) {
    try {
      // Creating the Connection to the Database and Creating Tables
      Connection conn = dbAccess.createConnection();
      dbAccess.createDatabaseAndTable(conn);

      // Hard Coded Admin Account Credentials
      dbAccess.newAdmin(conn, "admin", "#test");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Starting Interface
    mainInterface();
  }

  // Main page
  public static void mainInterface() {
    // Create a new JFrame
    int width = 1000; // Width of the Frame
    int height = 500; // Height of the Frame

    JFrame frame = new JFrame();
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(new Color(44, 62, 80));
    // Creating Frame w/ Background Color

    // Create a label for the title
    JLabel titleLabel = new JLabel("Halal Restaurant Finder");
    titleLabel.setBounds(width / 2 - 100, 10, 300, 30);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);

    // Create three buttons so user can choose what account type they want to enter
    //
    JButton adminButton = new JButton("Admin");
    JButton restaurantButton = new JButton("Restaurant");
    JButton userButton = new JButton("User");

    // Set the positions and sizes of the buttons
    adminButton.setBounds(200, 350, 100, 30);
    restaurantButton.setBounds(450, 350, 100, 30);
    userButton.setBounds(700, 350, 100, 30);

    // Button Action Listeners
    adminButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // JOptionPane.showMessageDialog(null, "Opening Admin In
        frame.dispose(); // Delete Frame Before
        AdminLogin(); // Call to Admin Login
      }
    });
    restaurantButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        RestaurantSignUp(); // Call to other Frame
      }
    });

    userButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        UserSignUp();
      }
    });

    // Adding Components of JFrame (title, buttons, etc.)
    frame.add(titleLabel);
    frame.add(adminButton);
    frame.add(restaurantButton);
    frame.add(userButton);

    // Set the frame layout to null (so we can use absolute positioning)
    frame.setLayout(null);

    // Make the frame visible
    frame.setVisible(true);
  }

  public static void AdminLogin() {
    // Create a new JFrame
    int width = 1000;
    int height = 500;

    JFrame frame = new JFrame();
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(new Color(44, 62, 80));

    // Create a label for the title
    JLabel titleLabel = new JLabel("Admin Login");
    titleLabel.setBounds(width / 2 - 50, 10, 300, 30);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);

    //Creating user/pass fields
    JTextField usernameField = new JTextField(20);
    usernameField.setText("Username");
    JTextField passwordField = new JTextField(20);
    passwordField.setText("Password");
    JButton loginButton = new JButton("Login");
    JButton mainPageButton = new JButton("Main Page");

    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Retrieving user inputs
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Login Here
        try {
          Connection conn = dbAccess.createConnection();
          if (dbAccess.authenticatingAdmin(conn, username, password)) {
            frame.dispose();
            Admin.mainPage(username);
          }
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(null, "Error: Try Again");
        }
      }
    });
    
    mainPageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        mainInterface();
      }
    });

    //Creating spaces for user/pass fields
    mainPageButton.setBounds(width - 100, 10, 100, 30);
    usernameField.setBounds(width / 2 - 50, 200, 100, 30);
    passwordField.setBounds(width / 2 - 50, 230, 100, 30);
    loginButton.setBounds(width / 2 - 50, 260, 100, 30);

    frame.add(titleLabel);
    frame.add(mainPageButton);
    frame.add(usernameField);
    frame.add(passwordField);
    frame.add(loginButton);

    // Set the frame layout to null (so we can use absolute positioning)
    frame.setLayout(null);

    // Make the frame visible
    frame.setVisible(true);
  }

  public static void RestaurantSignUp() {
    // Create a new JFrame
    int width = 1000;
    int height = 500;

    //Restaurants Sign Up Frame
    JFrame frame = new JFrame();
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(new Color(44, 62, 80));

    // Create a label for the title
    JLabel titleLabel = new JLabel("Restaurant Sign Up");
    titleLabel.setBounds(width / 2 - 100, 10, 300, 30);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);

    //Creating user/pass fields
    JTextField nameField = new JTextField(20);
    nameField.setText("Name");
    JTextField zipCodeField = new JTextField(20);
    zipCodeField.setText("Zip Code");
    JTextField phoneNumberField = new JTextField(20);
    phoneNumberField.setText("Phone Number");
    JTextField passwordField = new JTextField(20);
    passwordField.setText("Password");
    JTextField healthRatingField = new JTextField(20);
    healthRatingField.setText("Health Rating");
    JTextField addressField = new JTextField(20);
    addressField.setText("Address");
    JTextField foodTypeField = new JTextField(20);
    foodTypeField.setText("Food Type");
    JButton signUpButton = new JButton("Sign Up");

    //Buttons for Main Page
    JButton mainPageButton = new JButton("Main Page");
    JButton loginPageButton = new JButton("Already Have an Account?");
    //Action Code
    signUpButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Retrieving user inputs
        String name = nameField.getText();
        String password = passwordField.getText();
        //Collecting NONLOGIN Info
        String address = addressField.getText();
        int zipcode = Integer.parseInt(zipCodeField.getText());
        String phoneNumber = phoneNumberField.getText();
        String healthRating = healthRatingField.getText();
        String foodType = foodTypeField.getText();

        // Creating Restaurant Account
        try {
          Connection conn = dbAccess.createConnection();
          dbAccess.newRestaurant(conn, name, address, zipcode, phoneNumber, foodType, healthRating, password);
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(null, "Error: Try Again");
        }
      }
    });

    //Action Code
    //Going back to Main Page
    mainPageButton.addActionListener(new ActionListener() {
      @Override  
      public void actionPerformed(ActionEvent e) {
        frame.dispose();  //Delete Frame Before
        mainInterface();  //Going to main Interface, with all features
      }
    });

    //Action Code
    //For Login
    loginPageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();  //Delete Frame Before
        RestaurantLogin();
      }
    });

    //Creating the fields with their sizes
    mainPageButton.setBounds(width - 100, 10, 100, 30);
    nameField.setBounds(width / 2 - 50, 140, 100, 30);
    passwordField.setBounds(width / 2 - 50, 170, 100, 30);
    addressField.setBounds(width / 2 - 50, 200, 100, 30);
    zipCodeField.setBounds(width / 2 - 50, 230, 100, 30);
    phoneNumberField.setBounds(width / 2 - 50, 260, 100, 30);
    foodTypeField.setBounds(width / 2 - 50, 290, 100, 30);
    healthRatingField.setBounds(width / 2 - 50, 320, 100, 30);
    signUpButton.setBounds(width / 2 - 50, 350, 100, 30);
    loginPageButton.setBounds(width / 2 - 100, 380, 200, 30);

    //Adding the LABELS & BUTTONS That were initialized
    frame.add(titleLabel);
    frame.add(mainPageButton);
    frame.add(loginPageButton);
    frame.add(nameField);
    frame.add(zipCodeField);
    frame.add(phoneNumberField);
    frame.add(passwordField);
    frame.add(healthRatingField);
    frame.add(addressField);
    frame.add(foodTypeField);
    frame.add(signUpButton);
    frame.add(signUpButton);

    // Set the frame layout to null (so we can use absolute positioning)
    frame.setLayout(null);

    // Make the frame visible
    frame.setVisible(true);
  }

  public static void RestaurantLogin() {
    // Create a new JFrame
    int width = 1000;  //Same as Others
    int height = 500;   //Same as Others

    //New Frame and Background color as the others
    JFrame frame = new JFrame();
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(new Color(44, 62, 80));

    // Create a label for the title
    JLabel titleLabel = new JLabel("Restaurant Login");
    titleLabel.setBounds(width / 2 - 100, 10, 300, 30);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));  //TITLE with bolding and FONTING
    titleLabel.setForeground(Color.WHITE);

    //FIELDS for the user, pass, and login
    //CARRYING OUT DRAFTED IMAGE
    JTextField usernameField = new JTextField(20);
    usernameField.setText("Username");
    JTextField passwordField = new JTextField(20);
    passwordField.setText("Password");
    JButton loginButton = new JButton("Login");
    JButton mainPageButton = new JButton("Main Page");
    JButton signUpButton = new JButton("Don't Have an Account?");

    //Action CODE, DOES THE LOGIN 
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Retrieving user inputs
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Login Here
        try {
          Connection conn = dbAccess.createConnection();
          if (dbAccess.authenticatingRestaurantAccount(conn, username, password)) {
            frame.dispose();
            Restaurant.mainPage(username, conn);
          }
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(null, "Error: Try Again");  
          // ERROR MESSAGE BOX
        }
      }
    });

    mainPageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();  //Delete previous frame 
        mainInterface();  //The original page
      }
    });

    signUpButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();//Delete previous frame
        RestaurantSignUp(); // if user wants to create account
      }
    });

    //Creating the fields with their sizes
    mainPageButton.setBounds(width - 100, 10, 100, 30);
    usernameField.setBounds(width / 2 - 50, 200, 100, 30);
    passwordField.setBounds(width / 2 - 50, 230, 100, 30);
    loginButton.setBounds(width / 2 - 50, 260, 100, 30);
    signUpButton.setBounds(width / 2 - 100, 290, 200, 30);

    //FRAME ADDING INITIALIZED COMPONENTS
    frame.add(titleLabel);
    frame.add(mainPageButton);
    frame.add(usernameField);
    frame.add(passwordField);
    frame.add(loginButton);
    frame.add(signUpButton);

    // Set the frame layout to null (so we can use absolute positioning)
    frame.setLayout(null);

    // Make the frame visible
    frame.setVisible(true);
  }

  //USER SIGN UP SECTION
  public static void UserSignUp() {
    // Create a new JFrame
    int width = 1000;  //Same as other frames
    int height = 500;//Same as other frames

    JFrame frame = new JFrame();//Same as other frames
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(new Color(44, 62, 80));//Same as other frames

    // Create a label for the title
    JLabel titleLabel = new JLabel("User Sign Up");
    //CENTERED w/ the font selected
    titleLabel.setBounds(width / 2 - 75, 10, 300, 30); 
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));  //TITLE with bolding and FONTING
    titleLabel.setForeground(Color.WHITE);

    //FIELDS INITIALIZED (SIMILAR TO OTHERS)
    JTextField nameField = new JTextField(20);
    nameField.setText("Name");
    JTextField usernameField = new JTextField(10);
    usernameField.setText("Username");
    JTextField passwordField = new JTextField(20);
    passwordField.setText("Password");
    JButton signUpButton = new JButton("Sign Up");
    JButton mainPageButton = new JButton("Main Page");
    JButton loginPageButton = new JButton("Already Have an Account?");

    //SIGNUP BUTTON ACTION
    signUpButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Retrieving user inputs
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Creating Restaurant Account
        try {
          Connection conn = dbAccess.createConnection();
          dbAccess.newUser(conn, name, username, password);
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(null, "Error: Try Again");  //POPUP MESSAGE FOR CATCH!
        }
      }
    });

    //MAIN PAGE BUTTON ACTION
    mainPageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();  //Delete former screen
        mainInterface();  //Go to main page
      }
    });
    
    //LOGIN PAGE ACTION
    loginPageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        UserLogin();
      }
    });

    //Creating the fields with their sizes
    mainPageButton.setBounds(width - 100, 10, 100, 30);
    nameField.setBounds(width / 2 - 50, 200, 100, 30);
    usernameField.setBounds(width / 2 - 50, 230, 100, 30);
    passwordField.setBounds(width / 2 - 50, 260, 100, 30);
    signUpButton.setBounds(width / 2 - 50, 290, 100, 30);
    loginPageButton.setBounds(width / 2 - 100, 320, 200, 30);

    //FRAME ADDING INITIALIZED COMPONENTS
    frame.add(mainPageButton);
    frame.add(loginPageButton);
    frame.add(titleLabel);
    frame.add(nameField);
    frame.add(usernameField);
    frame.add(passwordField);
    frame.add(signUpButton);

    // Set the frame layout to null (so we can use absolute positioning)
    frame.setLayout(null);

    // Make the frame visible
    frame.setVisible(true);
  }

  public static void UserLogin() {
    // Create a new JFrame
    int width = 1000;  //Same as Others
    int height = 500;  //Same as Others

    
    JFrame frame = new JFrame();
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(new Color(44, 62, 80));

    // Create a label for the title
    //CENTERED w/ the font selected
    JLabel titleLabel = new JLabel("User Login");
    titleLabel.setBounds(width / 2 - 50, 10, 300, 30);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);

    //INITIALIZING FIELDS AND BUTTONS
    JTextField usernameField = new JTextField(20);
    usernameField.setText("Username");
    JTextField passwordField = new JTextField(20);
    passwordField.setText("Password");
    JButton loginButton = new JButton("Login");
    JButton mainPageButton = new JButton("Main Page");
    JButton signUpButton = new JButton("Don't Have an Account?");
    
    //LOGIN BUTTON ACTION 
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Retrieving user inputs
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Login Here
        try {
          Connection conn = dbAccess.createConnection();
          if (dbAccess.authenticatingUserAccount(conn, username, password)) {
            frame.dispose();  //Delete previous frame
            User.mainPage(username);  //User main page w username argument
          }
        } catch (Exception exception) {
          JOptionPane.showMessageDialog(null, "Error: Try Again");
          //MESSAGE POPUP FOR ERROR
        }
      }
    });
    //MAIN PAGE BUTTON ACTION
    mainPageButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        mainInterface();
      }
    });
    //SIGNUP BUTTON ACTION
    signUpButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        UserSignUp();
      }
    });
    //SETTING THE IMAGE WITH THEIR SPACES for the button/fields
    mainPageButton.setBounds(width - 100, 10, 100, 30);
    usernameField.setBounds(width / 2 - 50, 200, 100, 30);
    passwordField.setBounds(width / 2 - 50, 230, 100, 30);
    loginButton.setBounds(width / 2 - 50, 260, 100, 30);
    signUpButton.setBounds(width / 2 - 100, 290, 200, 30);
    //Adding the intialized fields and buttons
    frame.add(titleLabel);
    frame.add(mainPageButton);
    frame.add(usernameField);
    frame.add(passwordField);
    frame.add(loginButton);
    frame.add(signUpButton);

    // Set the frame layout to null (so we can use absolute positioning)
    frame.setLayout(null);

    // Make the frame visible
    frame.setVisible(true);
  }
}