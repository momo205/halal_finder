import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Admin 
{
    private static String username;
    private static ArrayList<String> restaurantList = new ArrayList<>();

    // Method to display the main page
    public static void mainPage(String argUsername) 
    {
        username = argUsername;
           
        // Create a new JFrame with specified dimensions
        int width = 1000;
        int height = 500;
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(44, 62, 80)); // Set background color
           
        // Create a label for the title
        JLabel titleLabel = new JLabel("Manage Restaurants");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size and make bold
        titleLabel.setBounds(width / 2 - 100, 10, 275, 30); // Position the label
        titleLabel.setForeground(Color.WHITE);
           
        // Create a logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(width - 150, 10, 100, 30); // Position the button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                App.mainInterface(); // Call the main interface of the application
            }
        });
    
        // Create buttons for different search options
        JButton searchByNameButton = new JButton("Search by Name");
        searchByNameButton.setBounds(100, 100, 200, 30); // Position the button
        searchByNameButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose(); // Close the currect frame
               searchByName(); // Call the searchByName method
           }
        });
            
        JButton searchByZipCodeButton = new JButton("Search by Zip Code");
        searchByZipCodeButton.setBounds(100, 150, 200, 30); // Position the button
        searchByZipCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                searchByZipCode(); // Call the searchByZipCode method
            }
        });
        JButton searchByCuisineButton = new JButton("Search by Cuisine");
        searchByCuisineButton.setBounds(100, 200, 200, 30); // Position the button
        searchByCuisineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                searchByCuisine(); // Call the searchByCuisine method
            }
        });
    
        // Create a button to go back to the main admin page
        JButton adminMainPage = new JButton("Admin Main Page");
        adminMainPage.setBounds(100, 10, 150, 30); // Position the button
        adminMainPage.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                mainPage(username); // Call the mainPage method with the current username
            }
        });
    
        // Add components to the frame
        frame.add(titleLabel);
        frame.add(logoutButton);
        frame.add(adminMainPage);
    
        frame.add(searchByNameButton);
        frame.add(searchByZipCodeButton);
        frame.add(searchByCuisineButton);
        frame.setLayout(null); // Use null layout for absolute positioning
        // Set frame visibility
        frame.setVisible(true);
    }

    // Method to deisplay the search by name page
    public static void searchByName() 
    {
        // Create a new JFrame with specified dimensions
        int width = 1000;
        int height = 500;
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(44, 62, 80)); // Set background color
        
        // Create a label for the title
        JLabel titleLabel = new JLabel("Search by Name");
        titleLabel.setBounds(width / 2 - 50, 10, 200, 30); // Position the label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        JLabel userLabel = new JLabel("Hello " + username);
        userLabel.setBounds(50, 40, 200, 30);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Create a logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(width - 100, 10, 100, 30); // Position the button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                App.mainInterface(); // Call the main interface of the application
            }
        });
    
        // Create a menu button
        JButton menuButton = new JButton("Menu");
        menuButton.setBounds(width - 200, 10, 100, 30);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainPage(username);
           }
       });
        
       // Create a search field
       JTextField searchField = new JTextField();
       searchField.setBounds(50, 80, 200, 30);
       // Create a search button
       JButton searchButton = new JButton("Search");
       searchButton.setBounds(260, 80, 100, 30);
    
       // Add components to the frame
       frame.add(titleLabel);
       frame.add(userLabel);
       frame.add(logoutButton);
       frame.add(menuButton);
       frame.add(searchField);
       frame.add(searchButton);
       // Display a list of halal restaurants
       populateRestaurantList();
    
       // Create the scroll pane and panel for restaurant list
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       JScrollPane scrollPane = new JScrollPane(panel);
       scrollPane.setBounds(50, 120, 900, 300);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
       searchButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String searchText = searchField.getText().toLowerCase();
               ArrayList<String> results = new ArrayList<>();
               for (String restaurant : restaurantList) {
                   if (restaurant.toLowerCase().contains(searchText)) {
                       results.add(restaurant);
                   }
               }
               displaySearchResults(scrollPane, results);
           }
       });
       frame.add(scrollPane);
       displaySearchResults(scrollPane, restaurantList);
       // Set the frame layout to null (so we can use absolute positioning)
       frame.setLayout(null);
       // Make the frame visible
       frame.setVisible(true);
    }
    public static void searchByZipCode() {
       // Create a new JFrame
       int width = 1000;
       int height = 500;
       JFrame frame = new JFrame();
       frame.setSize(width, height);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().setBackground(new Color(44, 62, 80));
       // Create a label for the title
       JLabel titleLabel = new JLabel("Search by Zip Code");
       titleLabel.setBounds(width / 2 - 100, 10, 300, 30);
       titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
       titleLabel.setForeground(Color.WHITE);
       JLabel userLabel = new JLabel("Hello " + username);
       userLabel.setBounds(50, 40, 200, 30);
       userLabel.setFont(new Font("Arial", Font.BOLD, 24));
       userLabel.setForeground(Color.WHITE);
       // Create a logout button
       JButton logoutButton = new JButton("Logout");
       logoutButton.setBounds(width - 100, 10, 100, 30);
       logoutButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               App.mainInterface();
           }
       });
    
       JButton menuButton = new JButton("Menu");
       menuButton.setBounds(width - 200, 10, 100, 30);
       menuButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               mainPage(username);
           }
       });
       // Create a search field
       JTextField searchField = new JTextField();
       searchField.setBounds(50, 80, 200, 30);
       // Create a search button
       JButton searchButton = new JButton("Search");
       searchButton.setBounds(260, 80, 100, 30);
    
       // Add components to the frame
       frame.add(titleLabel);
       frame.add(userLabel);
       frame.add(logoutButton);
       frame.add(menuButton);
       frame.add(searchField);
       frame.add(searchButton);
       // Display a list of halal restaurants
       populateRestaurantList();
    
       // Create the scroll pane and panel for restaurant list
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       JScrollPane scrollPane = new JScrollPane(panel);
       scrollPane.setBounds(50, 120, 900, 300);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
       searchButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int searchText = Integer.parseInt(searchField.getText());
               ArrayList<String> results = new ArrayList<>();               
               try {
                Connection conn = dbAccess.createConnection();
                results = dbAccess.getRestaurantsByZipCodeDB(conn, searchText);
               } catch (Exception exception) {
    
               }
    
               displaySearchResults(scrollPane, results);
           }
       });
       frame.add(scrollPane);
       displaySearchResults(scrollPane, restaurantList);
       // Set the frame layout to null (so we can use absolute positioning)
       frame.setLayout(null);
       // Make the frame visible
       frame.setVisible(true);
    }
    public static void searchByCuisine() {
       // Create a new JFrame
       int width = 1000;
       int height = 500;
       JFrame frame = new JFrame();
       frame.setSize(width, height);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().setBackground(new Color(44, 62, 80));
       // Create a label for the title
       JLabel titleLabel = new JLabel("Search by Cuisine");
       titleLabel.setBounds(width / 2 - 100, 10, 300, 30);
       titleLabel.setForeground(Color.WHITE);
       titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
       JLabel userLabel = new JLabel("Hello " + username);
       userLabel.setBounds(50, 40, 200, 30);
       userLabel.setForeground(Color.WHITE);
       userLabel.setFont(new Font("Arial", Font.BOLD, 24));
       // Create a logout button
       JButton logoutButton = new JButton("Logout");
       logoutButton.setBounds(width - 100, 10, 100, 30);
       logoutButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               App.mainInterface();
           }
       });
    
       JButton menuButton = new JButton("Menu");
       menuButton.setBounds(width - 200, 10, 100, 30);
       menuButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               mainPage(username);
           }
       });
       // Create a search field
       JTextField searchField = new JTextField();
       searchField.setBounds(50, 80, 200, 30);
       // Create a search button
       JButton searchButton = new JButton("Search");
       searchButton.setBounds(260, 80, 100, 30);
    
       // Add components to the frame
       frame.add(titleLabel);
       frame.add(userLabel);
       frame.add(logoutButton);
       frame.add(menuButton);
       frame.add(searchField);
       frame.add(searchButton);
       // Display a list of halal restaurants
       populateRestaurantList();
    
       // Create the scroll pane and panel for restaurant list
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       JScrollPane scrollPane = new JScrollPane(panel);
       scrollPane.setBounds(50, 120, 900, 300);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
       searchButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String searchText = searchField.getText();
               ArrayList<String> results = new ArrayList<>();               
               try {
                Connection conn = dbAccess.createConnection();
                results = dbAccess.getRestaurantsByCuisineDB(conn, searchText);
               } catch (Exception exception) {
    
               }
    
               displaySearchResults(scrollPane, results);
           }
       });
       frame.add(scrollPane);
       displaySearchResults(scrollPane, restaurantList);
       // Set the frame layout to null (so we can use absolute positioning)
       frame.setLayout(null);
       // Make the frame visible
       frame.setVisible(true);
    }
    private static void populateRestaurantList()
    {	
      try{
        // Creating the Connection to the Database and Writing New Restaurant Into Database
        Connection conn = dbAccess.createConnection();
        restaurantList = dbAccess.getRestaurantNameListDB(conn);
      } catch (Exception e) {
           e.printStackTrace();
      }
    }
    private static void displaySearchResults(JScrollPane scrollPane, ArrayList<String> results) {
       // Create a panel to hold the search results
       JPanel panel = (JPanel) scrollPane.getViewport().getView();
       panel.removeAll();
       // Display new search results
       for (String restaurant : results) {
           JButton resultButton = new JButton("Restaurant: " + restaurant);
           resultButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   showRestaurantDetails(restaurant);
               }
           });
           panel.add(resultButton);
       }
       // Refresh the panel and scroll pane
       panel.revalidate();
       panel.repaint();
    }
    private static void showRestaurantDetails(String restaurantName) {
       // Create a new JFrame for the restaurant details
       JFrame detailsFrame = new JFrame(restaurantName + " Details");
       detailsFrame.setSize(400, 300);
       detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       detailsFrame.getContentPane().setBackground(new Color(44, 62, 80));
       // Fetch restaurant details (replace this with actual data retrieval logic)
       String details = getRestaurantDetails(restaurantName);
       // Display restaurant details
       JTextArea detailsTextArea = new JTextArea(details);
       detailsTextArea.setEditable(false);
       detailsFrame.add(detailsTextArea);
    
       detailsTextArea.setBounds(20, 20, 360, 150); // Adjusted bounds for text area
       detailsFrame.add(detailsTextArea);
    
       detailsFrame.setLayout(null);
    
       // Create a "Delete Account" button
       JButton deleteButton = new JButton("Delete Account");
       deleteButton.setBounds(20, 200, 150, 30); // Position the button
       deleteButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
            try {
                // Create a connection to the database
              Connection conn = dbAccess.createConnection();
                // Mark the restaurant for deletion in the database
              dbAccess.markForDeletion(conn, restaurantName);
            } catch (Exception exception) {
    
            }
           }
       });

       // Create a "Restore Account" button
       JButton restoreButton = new JButton("Restore Account");
       restoreButton.setBounds(170, 200, 150, 30);
       restoreButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
            try {
              Connection conn = dbAccess.createConnection();
              dbAccess.unMarkForDeletion(conn, restaurantName);
            } catch (Exception exception) {
    
            }
           }
       });
    
       detailsFrame.add(deleteButton);
       detailsFrame.add(restoreButton);
    
       // Make the frame visible
       detailsFrame.setVisible(true);
    }
    private static String getRestaurantDetails(String restaurantName) {
       // Replace this with actual database access code to get restaurant details
      try {
        Connection conn = dbAccess.createConnection();
    
        String address = "\nAddress:" + dbAccess.getAddress(conn, restaurantName);
        String phoneNumber = "\nPhone Number: " + dbAccess.getPhoneNumber(conn, restaurantName);
        String foodType = "\nCuisine: " + dbAccess.getFoodType(conn, restaurantName);
        String rating = "Food Rating: " + dbAccess.getRating(conn, restaurantName);
        String healthRating = "\nHealth Rating: " + dbAccess.getHealthRating(conn, restaurantName);
    
        return restaurantName + address + phoneNumber + foodType + rating + healthRating;
      } catch(Exception e) {
        return "Error Retrieving Info";
      }
    }
}