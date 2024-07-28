import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
//This class is used to connect to the database it is utilizing MySQL and the JDBC driver. It is extremely significant to the purpose of this project as it is the only way to connect to the database and save data. As information is modified and developed, it changes the database and actually saves between runs of the program. 
public class dbAccess
{
  // Database Values
  private static final String URL = "jdbc:mysql://localhost:3306/HalalRestaurantFinder"; // URL to connect to the database
  private static final String USERNAME = "root"; // username of database THIS CAN CHANGE FROM YOUR OWN ACCOUNT
  private static final String PASSWORD = "219522935"; // password of my database THIS CAN CHANGE FROM YOUR OWN ACCOUNT
  private static final String DATABASE = "HalalRestaurantFinder";
  //Table for address, name, zip, average rating, and phone number ETC. Most important database
  private static final String restaurantTABLE = "RestaurantTable";
  
  private static final String userTABLE = "UserTable"; // Tables that will be used in the database for the user LOGINS
  private static final String adminTABLE = "AdminTable";
  // Tables that will be used in the database for the user LOGINS
  //Theres only one login for admin and one for user

  //HOW THE CODE CONNECTS TO MySQL 
  public static Connection createConnection() throws Exception {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }

  // Setting Up Database Table
  public static void createDatabaseAndTable(Connection conn) throws Exception 
  {
    // Creating Data Table for Restaurant Info
    String sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS " + DATABASE;
    String sqlUseDatabase = "USE " + DATABASE;
    String sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + restaurantTABLE + " (" +
                "restaurant_name VARCHAR(255), " +
                "address VARCHAR(255), " +
                "zip_code INT, " +
                    "phone_number VARCHAR(255), " +
                "food_type VARCHAR(255), " +
                    "rating DOUBLE DEFAULT 0, " +
                    "health_rating VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "hide BOOLEAN DEFAULT FALSE," + 
                    "total_rating INT DEFAULT 0," +
                    "num_of_ratings INT DEFAULT 0)";
//All these variables are used to create the database and table for the restaurant info. The database is created and the table is created. The table is created with the restaurant name, address, zip code, phone number, food type, rating, health

    //Creation of tables 
    //EXECUTE UPDATE conducts the changes to the database and the previous few lines permits
    try (Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(sqlCreateDatabase);
      stmt.executeUpdate(sqlUseDatabase);
      stmt.executeUpdate(sqlCreateTable);
      System.out.println("Restaurant Database and Table created successfully...");  //SUCCESS 
      System.out.println("");
    }

    // Creating Data Table for User Info
    sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS " + DATABASE;
    sqlUseDatabase = "USE " + DATABASE;  
    sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + userTABLE + " (" +
                "name VARCHAR(255), " +
                    "username VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "hide BOOLEAN DEFAULT FALSE)";
    //All these variables are used to create the database and table for the USER info. The database is created and the table is created. The table is created with the username, password, and deletion


    try (Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(sqlCreateDatabase);
      stmt.executeUpdate(sqlUseDatabase);
      stmt.executeUpdate(sqlCreateTable);
      System.out.println("User Database and table created successfully...");
      System.out.println("");
    }

    // Creating data Table for Admin Info
    sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS " + DATABASE;
    sqlUseDatabase = "USE " + DATABASE;
    sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + adminTABLE + " (" +
                    "username VARCHAR(255), " +
                    "password VARCHAR(255))";


    try (Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(sqlCreateDatabase);
      stmt.executeUpdate(sqlUseDatabase);
      stmt.executeUpdate(sqlCreateTable);
      System.out.println("Admin Database and table created successfully...");
      System.out.println("");
    }
  }

  // A method/function that takes 4 user inputs and then stores the new information into the database
  public static void newRestaurant(Connection conn, String name, String address, int zipCode, String phoneNumber, String foodType, String healthRating, String password) throws Exception 
  {
    String sqlCheck = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sqlCheck)) {
      // checks for duplicate names
      pstmt.setString(1, name);
      ResultSet rs = pstmt.executeQuery();
      // If rs.next() returns false then there are no rows matching the query, i.e., restaurant does not exist
      if (!rs.next()) {
        String sqlInsert = "INSERT INTO " + restaurantTABLE + " (restaurant_name, address, zip_code, phone_number, food_type, health_rating, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
          pstmtInsert.setString(1, name);
          pstmtInsert.setString(2, address);
          pstmtInsert.setInt(3, zipCode);
          pstmtInsert.setString(4, phoneNumber);
          pstmtInsert.setString(5, foodType);
          pstmtInsert.setString(6, healthRating);
          pstmtInsert.setString(7, password);
          System.out.println("Set All Elements");
          pstmtInsert.executeUpdate();
          System.out.println("Execution Error");
          JOptionPane.showMessageDialog(null, "Restaurant Account Created!");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Name Already Exists");
      }
    }
  }
  // A method/function that takes 4 user inputs and then stores the new information into the database
  public static void newUser(Connection conn, String name, String username, String password) throws Exception
  {
    String sqlCheck = "SELECT * FROM " + userTABLE + " WHERE username = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sqlCheck)) {
      pstmt.setString(1, username);
      ResultSet rs = pstmt.executeQuery();
      // If rs.next() returns false then there are no rows matching the query, i.e., user does not exist
      if (!rs.next()) {
        String sqlInsert = "INSERT INTO " + userTABLE + " (name, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
         
          pstmtInsert.setString(1, name);
          pstmtInsert.setString(2, username);
          pstmtInsert.setString(3, password);
          pstmtInsert.executeUpdate();
          JOptionPane.showMessageDialog(null, "User Account Created!");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Username Already Exists");
      }
    }

  }
  // A method/function that takes 2 user inputs and then stores the new information into the database
  public static void newAdmin(Connection conn, String username, String password) throws Exception
  {
    String sqlCheck = "SELECT * FROM " + adminTABLE + " WHERE username = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sqlCheck)) {
      pstmt.setString(1, username);
      ResultSet rs = pstmt.executeQuery();
      // If rs.next() returns false then there are no rows matching the query, i.e., admin does not exist
      if (!rs.next()) {
        String sqlInsert = "INSERT INTO " + adminTABLE + " (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
          pstmtInsert.setString(1, username);
          pstmtInsert.setString(2, password);
          pstmtInsert.executeUpdate();
          // JOptionPane.showMessageDialog(null, "Admin Account Created!");
        }
      }
    }

  }

  public static boolean authenticatingRestaurantAccount(Connection conn, String name, String password) throws Exception
  {
    String sql = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ? AND password = ?";

    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, name); 
      statement.setString(2, password);
      
      try (ResultSet rs = statement.executeQuery()) {
         if(rs.next() && !rs.getBoolean("hide")) {
           JOptionPane.showMessageDialog(null, "Login Successful");
           return true;

        } else {
          JOptionPane.showMessageDialog(null, "Login Failed: Check Credentials");
          return false;
        }
      }
    }
  }
// A method/function that takes 4 user inputs and then stores the new information into the database 
  public static boolean authenticatingUserAccount(Connection conn, String username, String password) throws Exception
  {
    String sql = "SELECT * FROM " + userTABLE + " WHERE username = ? AND password = ?";

    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, username); 
      statement.setString(2, password);

      try (ResultSet rs = statement.executeQuery()) {
         if(rs.next() && !rs.getBoolean("hide")) {
           JOptionPane.showMessageDialog(null, "Login Successful");
           return true;

        } else {
          JOptionPane.showMessageDialog(null, "Login Failed: Check Credentials");
          return false;
        }
      }
    }
  }
// A method/function that takes 4 user inputs and then stores the new information into the database
  public static String getUsersRealName(Connection conn, String username) throws Exception
  {
    String sql = "SELECT * FROM " + userTABLE + " WHERE username = ?";

    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, username); 

      try (ResultSet rs = statement.executeQuery()) {
         if(rs.next()) {
           return rs.getString("name");

        } else {
          return "User";
        }
      }
    }
  }
// A method/function that takes 4 user inputs and then stores the new information into the database
  public static boolean authenticatingAdmin(Connection conn, String username, String password) throws Exception
  {
    String sql = "SELECT * FROM " + adminTABLE + " WHERE username = ? AND password = ?";

    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, username); 
      statement.setString(2, password);

      try (ResultSet rs = statement.executeQuery()) {
         if(rs.next()) {
           JOptionPane.showMessageDialog(null, "Login Successful");
           return true;

        } else {
          JOptionPane.showMessageDialog(null, "Login Failed: Check Credentials");
          return false;
        }
      }
    }
  }
// A method/function that takes 4 user inputs and then stores the new information into the database
  //RETURNS ArrayLIST of STRINGS
  public static ArrayList<String> getRestaurantsByZipCode(Connection conn, int zipCode) throws Exception
  {
    ArrayList<String> list = new ArrayList<String>();

    String sql = "SELECT * FROM " + restaurantTABLE;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();
      //ENSURE the "hidden" variable is false and not false
      while(rs.next()) {
        if (!rs.getBoolean("hide") && rs.getInt("zip_code") == zipCode) {
                  list.add(rs.getString("restaurant_name"));
              }
      }
    }
    return list;  //RETURN 
  }
  // A method/function that takes 2 user inputs and then stores the new information into the database
  //RETURNS ArrayLIST of STRINGS
  //Organize by Food Type
  public static ArrayList<String> getRestaurantsByCuisine(Connection conn, String cuisine) throws Exception
  {
    ArrayList<String> list = new ArrayList<String>();

    String sql = "SELECT * FROM " + restaurantTABLE;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        if (!rs.getBoolean("hide") && rs.getString("food_type").equals(cuisine)) {
                  list.add(rs.getString("restaurant_name"));
              }
      }
    }
    //ITERATION
    for(String name: list) {
      System.out.println(name);
    }

    return list;
  }

  // A method/function that takes 1 user input and then stores the new information into the database
  //RETURNS ArrayLIST of STRINGS for ALL RESTAURANT NAMES

  public static ArrayList<String> getRestaurantNameList(Connection conn) throws Exception
  {
    ArrayList<String> list = new ArrayList<String>();

    String sql = "SELECT * FROM " + restaurantTABLE;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        if (!rs.getBoolean("hide")) {
                  list.add(rs.getString("restaurant_name"));
              }
      }
    }

    return list;  //RETURNING
  }

  // A method/function that takes 2 user inputs and then stores the new information into the database
  //RETURNS ArrayLIST of STRINGS
  //SELECT FROM TABLE ACCORDING TO ZIPCODE IF IDENTICAL 
  //DOESN'T DO NEARBY
  public static ArrayList<String> getRestaurantsByZipCodeDB(Connection conn, int zipCode) throws Exception
  {
    ArrayList<String> list = new ArrayList<String>();

    String sql = "SELECT * FROM " + restaurantTABLE;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        if (rs.getInt("zip_code") == zipCode) {
                  list.add(rs.getString("restaurant_name"));
              }
      }
    }

    return list;
  }

  // A method/function that takes 2 user inputs and then stores the new information into the database
  //ARRAYLIST RETURN
  //SELECT FROM TABLE ACCORDING TO FOODTYPE IF IDENTICAL
  public static ArrayList<String> getRestaurantsByCuisineDB(Connection conn, String cuisine) throws Exception
  {
    ArrayList<String> list = new ArrayList<String>();

    String sql = "SELECT * FROM " + restaurantTABLE;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        if (rs.getString("food_type").equals(cuisine)) {
                  list.add(rs.getString("restaurant_name"));
              }
      }
    }
    //ITERATION
    for(String name: list) {
      System.out.println(name);
    }

    return list;
  }
// A method/function that takes 1 user inputs and then stores the new information into the database
  //NAME LIST FROM DATABASE
  public static ArrayList<String> getRestaurantNameListDB(Connection conn) throws Exception
  {
    ArrayList<String> list = new ArrayList<String>();

    String sql = "SELECT * FROM " + restaurantTABLE;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();
      while(rs.next()) {
        list.add(rs.getString("restaurant_name"));//GETTING NAME
      }
    }

    return list;//RETURNING LIST
  }

  //Accessor Function for ADDRESS
  public static String getAddress(Connection conn, String restaurantName) throws Exception
  {
    String sql = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, restaurantName);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        return rs.getString("address");
      }
    }

    return "Error";
  }
  
  //Accessor Function for ZIP CODE FROM DATABASE
  public static String getZipCode(Connection conn, String restaurantName) throws Exception
  {
    String sql = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, restaurantName);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        return rs.getString("zip_code");
      }
    }

    return "Error";
  }

  //Accessor Function for PHONE NUMBER FROM DATABASE
  public static String getPhoneNumber(Connection conn, String restaurantName) throws Exception
  {
    String sql = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, restaurantName);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        return rs.getString("phone_number");
      }
    }

    return "Error";
  }
  //Accessor Function for FOOD TYPE FROM DATABASE
  public static String getFoodType(Connection conn, String restaurantName) throws Exception
  {
    String sql = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, restaurantName);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        return rs.getString("food_type");
      }
    }

    return "Error";  //Case
  }

   //Accessor Function for RATING FROM DATABASE
  //This is not mutatable for restaurants
  public static String getRating(Connection conn, String restaurantName) throws Exception
  {
    String sql = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, restaurantName);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        return rs.getString("rating");
      }
    }

    return "Error";  //reach/catch case 
  }

  //Accessor Function for Health Rating FROM DATABASE
  //Mutatable by Restaurant
  public static String getHealthRating(Connection conn, String restaurantName) throws Exception
  {
    String sql = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, restaurantName);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        return rs.getString("health_rating");
      }
    }

    return "Error"; //REACH/END Case 
  }

  public static void addRating(Connection conn, String name, int userRating) throws Exception {
      String sqlUpdate = "UPDATE " + restaurantTABLE + " SET total_rating = total_rating + ?, num_of_ratings = num_of_ratings + 1, rating = ROUND((total_rating + ?) / (num_of_ratings + 1), 1) WHERE restaurant_name = ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
          pstmt.setInt(1, userRating);
          pstmt.setInt(2, userRating);
          pstmt.setString(3, name);
          pstmt.executeUpdate(); // Carries out changes on DB
      }
  }
//mark for DELETION, DOESNT ACTUALLY DELETE
  public static void markForDeletion(Connection conn, String restaurantName) throws Exception {
      String sqlUpdate = "UPDATE " + restaurantTABLE + " SET hide = ? WHERE restaurant_name = ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
          pstmt.setBoolean(1, true); // Set hide to true
          pstmt.setString(2, restaurantName);  // Set restaurant_name to the restaurantName
          int rowsUpdated = pstmt.executeUpdate();
          System.out.println(rowsUpdated + " row(s) marked for deletion for restaurant: " + restaurantName); //USED FOR DEBUGGING
      }
  }
  
  //UNMARK for DELETION, UNDOES DELETion
  public static void unMarkForDeletion(Connection conn, String restaurantName) throws Exception {
      String sqlUpdate = "UPDATE " + restaurantTABLE + " SET hide = ? WHERE restaurant_name = ?";  
      try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
          pstmt.setBoolean(1, false); // Set marked_for_Deletion to false
          pstmt.setString(2, restaurantName);
          int rowsUpdated = pstmt.executeUpdate();
          System.out.println(rowsUpdated + " row(s) unmarked for deletion for restaurant: " + restaurantName);
      }
  }

  //getPassword
  public static String getPassword(Connection conn, String name) throws Exception {
    String sql = "SELECT password FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql))     {
      pstmt.setString(1, name);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getString("password");
      } else {
        throw new Exception("Restaurant not found: " + name);
      }
    }
  }
  //Accessor for Zip 
  public static int getZipcode(Connection conn, String name) throws Exception {
    String sql = "SELECT zip_code FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, name);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getInt("zip_code");
      } else {
        throw new Exception("Restaurant not found: " + name);
      }
    }
}
  
  //MUTATORs
  //UPDATE RESTAURANT NAME   
  public static void updateRestaurantName(Connection conn, String restaurantName, String newName) throws Exception 
  {
      String sqlCheck = "SELECT * FROM " + restaurantTABLE + " WHERE restaurant_name = ?";
      String sqlUpdate = "UPDATE " + restaurantTABLE + " SET restaurant_name = ? WHERE restaurant_name = ?";
      
      try (PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck);
           PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) 
      {
          // Check if the new name already exists
          pstmtCheck.setString(1, newName);
          ResultSet rs1 = pstmtCheck.executeQuery();
          
          if (rs1.next()) {
              // If newName already exists, handle accordingly
        	  JOptionPane.showMessageDialog(null, "Error: Restaurant Name Already Exists");
              return;
          }
          
          // If newName doesn't exist, update the restaurant name
          pstmtUpdate.setString(1, newName);
          pstmtUpdate.setString(2, restaurantName);
          pstmtUpdate.executeUpdate();
      }
  }


  //MUTATOR FOR ZIP CODE 
  public static void updateRestaurantZipCode(Connection conn, String restaurantName, int newZipCode) throws Exception 
  {
	  String sql = "UPDATE " + restaurantTABLE + " SET zip_code = ? WHERE restaurant_name = ?";
	  try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
	  {
		  pstmt.setInt(1, newZipCode);
		  pstmt.setString(2, restaurantName);
		  pstmt.executeUpdate();
	  }
  }

  //MUTATOR FOR PHONE NUMBER
  public static void updateRestaurantPhoneNumber(Connection conn, String restaurantName, String newPhoneNumber) throws Exception 
  {
	  String sql = "UPDATE " + restaurantTABLE + " SET phone_number = ? WHERE restaurant_name = ?";
	  try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
	  {
		  pstmt.setString(1, newPhoneNumber);
		  pstmt.setString(2, restaurantName);
		  pstmt.executeUpdate();
	  }
  }
    
  // Method to update restaurant's password
  public static void updateRestaurantPassword(Connection conn, String restaurantName, String newPassword) throws Exception 
  {
	  String sql = "UPDATE " + restaurantTABLE + " SET password = ? WHERE restaurant_name = ?";
	  try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
	  {
		  pstmt.setString(1, newPassword);
		  pstmt.setString(2, restaurantName);
		  pstmt.executeUpdate();
	  }
  }

  // Method to update restaurant's health rating
  public static void updateRestaurantHealthRating(Connection conn, String restaurantName, String newHealthRating) throws Exception 
  {
	  String sql = "UPDATE " + restaurantTABLE + " SET health_rating = ? WHERE restaurant_name = ?";
	  try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
	  {
		  pstmt.setString(1, newHealthRating);
		  pstmt.setString(2, restaurantName);
		  pstmt.executeUpdate();
	  }
  }
  
  // Method to update restaurant's address
  public static void updateRestaurantAddress(Connection conn, String restaurantName, String newAddress) throws Exception 
  {
	  String sql = "UPDATE " + restaurantTABLE + " SET address = ? WHERE restaurant_name = ?";
	  try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
	  {
		  pstmt.setString(1, newAddress);
		  pstmt.setString(2, restaurantName);
		  pstmt.executeUpdate();
	  }
  }
  
  // Method to update restaurant's food type
  public static void updateRestaurantFoodType(Connection conn, String restaurantName, String newFoodType) throws Exception 
  {
	  String sql = "UPDATE " + restaurantTABLE + " SET food_type = ? WHERE restaurant_name = ?";
	  try (PreparedStatement pstmt = conn.prepareStatement(sql)) 
	  {
		  pstmt.setString(1, newFoodType);
		  pstmt.setString(2, restaurantName);
		  pstmt.executeUpdate();
	  }
  }
}