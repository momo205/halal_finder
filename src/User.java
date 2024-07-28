import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.*;

public class User {
    private static String username;
    private static ArrayList<String> restaurantList = new ArrayList<>();

    public static void mainPage(String argUsername) {
        username = argUsername;

        try {
          Connection conn = dbAccess.createConnection();
          username = dbAccess.getUsersRealName(conn, argUsername);
        } catch(Exception e) {}

        // Create a new JFrame
        int width = 1000;
        int height = 500;

        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(44, 62, 80));

        // Create a label for the title
        JLabel titleLabel = new JLabel("User Page");
        titleLabel.setBounds(width / 2 - 50, 10, 200, 30);
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

        // Create buttons for different search options
        JButton searchByNameButton = new JButton("Search by Name");
        searchByNameButton.setBounds(100, 100, 200, 30);
        searchByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              frame.dispose();
                searchByName();
            }
        });

        JButton searchByZipCodeButton = new JButton("Search by Zip Code");
        searchByZipCodeButton.setBounds(100, 150, 200, 30);
        searchByZipCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              frame.dispose();
                searchByZipCode();
            }
        });

        JButton searchByCuisineButton = new JButton("Search by Cuisine");
        searchByCuisineButton.setBounds(100, 200, 200, 30);
        searchByCuisineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              frame.dispose();
                searchByCuisine();
            }
        });

        // Add components to the frame
        frame.add(titleLabel);
        frame.add(userLabel);
        frame.add(logoutButton);
        frame.add(searchByNameButton);
        frame.add(searchByZipCodeButton);
        frame.add(searchByCuisineButton);

        // Set the frame layout to null (so we can use absolute positioning)
        frame.setLayout(null);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void searchByName() {
        // Create a new JFrame
        int width = 1000;
        int height = 500;

        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(44, 62, 80));

        // Create a label for the title
        JLabel titleLabel = new JLabel("Search by Name");
        titleLabel.setBounds(width / 2 - 50, 10, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
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
                  results = dbAccess.getRestaurantsByZipCode(conn, searchText);
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
                  results = dbAccess.getRestaurantsByCuisine(conn, searchText);
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
        restaurantList = dbAccess.getRestaurantNameList(conn);
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

        JButton rate1Button = new JButton("1");
        JButton rate2Button = new JButton("2");
        JButton rate3Button = new JButton("3");
        JButton rate4Button = new JButton("4");
        JButton rate5Button = new JButton("5");

        rate1Button.setBounds(20, 200, 50, 30);
        rate2Button.setBounds(70, 200, 50, 30);
        rate3Button.setBounds(120, 200, 50, 30);
        rate4Button.setBounds(170, 200, 50, 30);
        rate5Button.setBounds(220, 200, 50, 30);

        rate1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try {
                Connection conn = dbAccess.createConnection();
                dbAccess.addRating(conn, restaurantName, 1);
              } catch (Exception exception) {

              }
            }
        });

        rate2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try {
                Connection conn = dbAccess.createConnection();
                dbAccess.addRating(conn, restaurantName, 2);
              } catch (Exception exception) {

              }
            }
        });

        rate3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try {
                Connection conn = dbAccess.createConnection();
                dbAccess.addRating(conn, restaurantName, 3);
              } catch (Exception exception) {

              }
            }
        });

        rate4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try {
                Connection conn = dbAccess.createConnection();
                dbAccess.addRating(conn, restaurantName, 4);
              } catch (Exception exception) {

              }
            }
        });

        rate5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try {
                Connection conn = dbAccess.createConnection();
                dbAccess.addRating(conn, restaurantName, 5);
              } catch (Exception exception) {

              }
            }
        });

        detailsFrame.add(rate1Button);
        detailsFrame.add(rate2Button);
        detailsFrame.add(rate3Button);
        detailsFrame.add(rate4Button);
        detailsFrame.add(rate5Button);

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

        return restaurantName + address + phoneNumber + foodType + rating + healthRating + "\nLeave A Rating Below";
      } catch(Exception e) {
        return "Error Retrieving Info";
      }
    }
}