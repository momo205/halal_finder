import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Restaurant {
   private static String name;
   public static void mainPage(String argName, Connection conn) {
       name = argName;
       // Create a new JFrame
       int width = 1000;
       int height = 500;
       JFrame frame = new JFrame();
       frame.setSize(width, height);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().setBackground(new Color(44, 62, 80));
       // Create labels for the title and fields
       JLabel titleLabel = new JLabel("Restaurant Page");
       titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size and make bold
       titleLabel.setBounds(width / 2 - 50, 10, 200, 30);
       titleLabel.setForeground(Color.WHITE);
       // Load the image dynamically from the resources folder
       ImageIcon imageIcon = new ImageIcon("resources/IMG_3972.jpg");
       Image image = imageIcon.getImage(); // transform it
       Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
       imageIcon = new ImageIcon(newimg);  // transform it back
       JLabel imageLabel = new JLabel(imageIcon);
       imageLabel.setBounds(width / 2 - 100, 60, 300, 300); // Center the image horizontally       
      
       JLabel nameLabel = new JLabel("Name:");
       nameLabel.setBounds(40, 60, 100, 30);
       nameLabel.setForeground(Color.WHITE);
       JTextField nameField = new JTextField(name);
       nameField.setBounds(140, 60, 200, 30);
       nameField.setBackground(new Color(173, 216, 230));
      
       JLabel passwordLabel = new JLabel("Password:");
       passwordLabel.setBounds(40, 100, 100, 30);
       passwordLabel.setForeground(Color.WHITE);
       final JTextField[] passwordField = {null}; // Declare as an array to make it effectively final
       try {
           final JTextField tempField = new JTextField(String.valueOf(dbAccess.getPassword(conn, name)));
           tempField.setBounds(140, 100, 200, 30);
           tempField.setBackground(new Color(173, 216, 230));
           passwordField[0] = tempField;
       } catch (Exception e) {
           e.printStackTrace(); // Handle this exception appropriately
       }
      
       JLabel addressLabel = new JLabel("Address:");
       addressLabel.setBounds(40, 140, 100, 30);
       addressLabel.setForeground(Color.WHITE);
       final JTextField[] addressField = {null}; // Declare as an array to make it effectively final
       try {
           final JTextField tempField = new JTextField(String.valueOf(dbAccess.getAddress(conn, name)));
           tempField.setBounds(140, 140, 200, 30);
           tempField.setBackground(new Color(173, 216, 230));
           addressField[0] = tempField;
       } catch (Exception e) {
           e.printStackTrace(); // Handle this exception appropriately
       }
      
       JLabel zipcodeLabel = new JLabel("Zipcode:");
       zipcodeLabel.setBounds(40, 180, 100, 30);
       zipcodeLabel.setForeground(Color.WHITE);
       final JTextField[] zipcodeField = {null}; // Declare as an array to make it effectively final
       try {
           final JTextField tempField = new JTextField(String.valueOf(dbAccess.getZipcode(conn, name)));
           tempField.setBounds(140, 180, 200, 30);
           tempField.setBackground(new Color(173, 216, 230));
           zipcodeField[0] = tempField;
       } catch (Exception e) {
           e.printStackTrace(); // Handle this exception appropriately
       }
      
       JLabel phoneNumberLabel = new JLabel("Phone Number:");
       phoneNumberLabel.setBounds(40, 220, 100, 30);
       phoneNumberLabel.setForeground(Color.WHITE);
       final JTextField[] phoneNumberField = {null}; // Declare as an array to make it effectively final
       try {
           final JTextField tempField = new JTextField(String.valueOf(dbAccess.getPhoneNumber(conn, name)));
           tempField.setBounds(140, 220, 200, 30);
           tempField.setBackground(new Color(173, 216, 230));
           phoneNumberField[0] = tempField;
       } catch (Exception e) {
           e.printStackTrace(); // Handle this exception appropriately
       }
      
       JLabel foodTypeLabel = new JLabel("Food Type:");
       foodTypeLabel.setBounds(40, 260, 100, 30);
       foodTypeLabel.setForeground(Color.WHITE);
       final JTextField[] foodTypeField = {null}; // Declare as an array to make it effectively final
       try {
           final JTextField tempField = new JTextField(String.valueOf(dbAccess.getFoodType(conn, name)));
           tempField.setBounds(140, 260, 200, 30);
           tempField.setBackground(new Color(173, 216, 230));
           foodTypeField[0] = tempField;
       } catch (Exception e) {
           e.printStackTrace(); // Handle this exception appropriately
       }
      
       JLabel healthRatingLabel = new JLabel("Health Rating:");
       healthRatingLabel.setBounds(40, 300, 100, 30);
       healthRatingLabel.setForeground(Color.WHITE);
       final JTextField[] healthRatingField = {null}; // Declare as an array to make it effectively final
       try {
           final JTextField tempField = new JTextField(String.valueOf(dbAccess.getHealthRating(conn, name)));
           tempField.setBounds(140, 300, 200, 30);
           tempField.setBackground(new Color(173, 216, 230));
           healthRatingField[0] = tempField;
       } catch (Exception e) {
           e.printStackTrace(); // Handle this exception appropriately
       }
      
       // Rating Label (not editable)
       JLabel ratingLabel = new JLabel("Average Rating:");
       ratingLabel.setBounds(40, 340, 100, 30);
       ratingLabel.setForeground(Color.WHITE);
      
    // Display the health rating as a label
       JLabel ratingValueLabel;
       try {
           String rating = String.valueOf(dbAccess.getRating(conn, name));
           ratingValueLabel = new JLabel(rating);
       } catch (Exception e) {
           ratingValueLabel = new JLabel("N/A"); // Handle the exception by displaying "N/A"
           e.printStackTrace(); // Handle this exception appropriately
       }
       ratingValueLabel.setBounds(140, 340, 200, 30);
       ratingValueLabel.setForeground(Color.WHITE);
       // Similarly add input fields for phoneNumber, password, healthRating, address, and foodType
       JButton saveButton = new JButton("Save");
       saveButton.setBounds(70, 380, 100, 30);
       JButton logoutButton = new JButton("Logout");
       logoutButton.setBounds(width - 170, 40, 150, 30);
      
    // Red button for "deleting" account
       JButton deleteAccountButton = new JButton("Delete Account");
       deleteAccountButton.setBounds(width - 170, 85, 150, 30);
       deleteAccountButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
            	   dbAccess.markForDeletion(conn, name);
               		frame.dispose();
                   JOptionPane.showMessageDialog(null, "Account marked for deletion.");
                   App.mainInterface();
                  
               } catch (Exception exception) {
                   JOptionPane.showMessageDialog(null, "Error marking account for deletion: " + exception.getMessage());
               }
           }
       });
       // ActionListener for logout button
       logoutButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               frame.dispose();
               App.mainInterface();
           }
       });
       // ActionListener for save button
       saveButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String newName = nameField.getText();
               int newZipcode = 0;
               String newPassword = "";
               String newAddress = "";
               String newPhoneNumber = "";
               String newFoodType = "";
               String newHealthRating = "";
              
               try {
                   newZipcode = Integer.parseInt(zipcodeField[0].getText());
                   newPassword = passwordField[0].getText();
                   newAddress = addressField[0].getText();
                   newPhoneNumber = phoneNumberField[0].getText();
                   newFoodType = foodTypeField[0].getText();
                   newHealthRating = healthRatingField[0].getText();
               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(null, "Invalid Input");
                   return;
               }
               try {
                   dbAccess.updateRestaurantName(conn, name, newName);
                   dbAccess.updateRestaurantZipCode(conn, name, newZipcode);
                   dbAccess.updateRestaurantPassword(conn, name, newPassword);
                   dbAccess.updateRestaurantAddress(conn, name, newAddress);
                   dbAccess.updateRestaurantPhoneNumber(conn, name, newPhoneNumber);
                   dbAccess.updateRestaurantFoodType(conn, name, newFoodType);
                   dbAccess.updateRestaurantHealthRating(conn, name, newHealthRating);
               } catch (Exception exception) {
                   JOptionPane.showMessageDialog(null, "Error updating data: " + exception.getMessage());
               }
               JOptionPane.showMessageDialog(null, "Restaurant details updated successfully!");
           }
       });
       // Add components to your frame or panel
       // Adding Components of JFrame (title, buttons, fields, etc.)
       frame.add(titleLabel);
       frame.add(nameLabel);
       frame.add(nameField);
       frame.add(zipcodeLabel);
       frame.add(zipcodeField[0]);
       frame.add(passwordLabel);
       frame.add(passwordField[0]);
       frame.add(addressLabel);
       frame.add(addressField[0]);
       frame.add(phoneNumberLabel);
       frame.add(phoneNumberField[0]);
       frame.add(foodTypeLabel);
       frame.add(foodTypeField[0]);
       frame.add(healthRatingLabel);
       frame.add(healthRatingField[0]);
       frame.add(ratingLabel);
       frame.add(ratingValueLabel); // Add health rating as label instead of field
       frame.add(saveButton);
       frame.add(logoutButton);
       frame.add(deleteAccountButton);
       frame.add(imageLabel);
       // Set the frame layout to null (so we can use absolute positioning)
       frame.setLayout(null);
       // Make the frame visible
       frame.setVisible(true);
   }
}