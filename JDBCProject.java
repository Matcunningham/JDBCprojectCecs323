package jdbcproject;
import java.sql.*;
import java.util.*;

public class JDBCProject
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Connection conn = null; //connect to driver
        Statement s = null;
        PreparedStatement ps = null; //used for user input
        ResultSet rs = null;
        int choice = 0; //initialize choice value
        char again = 'n'; //n or y for menu continuation
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //change localhost:1527/JBDCProject to match your database
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JBDCProject;create=true");
            s = conn.createStatement();
            while(choice != 10)
            {
                choice = menu();
                switch(choice)
                {
                    case 1: //list all writing groups
                        rs = s.executeQuery("SELECT * FROM writingGroup");
                        while(rs.next())
                        {
                            System.out.printf("%-25s  %-17s  %-5d %-10s \n", 
                                    rs.getString("groupName"),
                                    rs.getString("headWriter"), 
                                    rs.getInt("yearFormed"),
                                    rs.getString("subject"));
                        }
                        break;
                    case 2: //list data for group specified, + all data for assoc. books and pubs
                        rs = ps.executeQuery("SELECT *FROM writingGroup where groupName = ? ");//edit for books and pubs
                    case 3: //list all publishers
                        rs = s.executeQuery("SELECT * FROM publisher");
                        while(rs.next())
                        {
                            System.out.printf("%-25s  %-38s  %-14s %-35s \n", 
                                    rs.getString("pubName"),
                                    rs.getString("pubAddress"), 
                                    rs.getString("pubPhone"),
                                    rs.getString("pubEmail"));
                        }
                        break;
                    case 4: //list all data for specified publisher + data for assoc. books and groups
                        rs = ps.executeQuery("SELECT * FROM publisher WHERE pubName = ?");//edit for book and group
                    case 5: //list all book titles
                         rs = s.executeQuery("SELECT bookTitle FROM book");
                        while(rs.next())
                        {
                            System.out.printf("%-25s \n", 
                                    rs.getString("bookTitle"));
                        }
                        break;
                    case 6://list all data for book specified + all data for assoc. pub and group
                        rs = ps.executeQuery("SELECT * FROM book where bookTitle = ?");//edit for pub and group
                    case 7://insert new book
                         ps = conn.prepareStatement("INSERT INTO book (groupName, pubName, "
                                 + "bookTitle, yearPublished, numberPages"
                                 + " VALUES(?,?,?,?,?)");
                         System.out.println("Enter group name:");
                         String group = in.nextLine();
                         System.out.println("Enter publisher name:");
                         String pub = in.nextLine();
                         System.out.println("Enter book title:");
                         String title = in.nextLine();
                         System.out.println("Enter year published:");
                         int yr = in.nextInt();
                         System.out.println("Enter number of pages:");
                         int pages = in.nextInt();
                         //set the prepared statement ? to values
                         ps.setString(1, group);
                         ps.setString(2, pub);
                         ps.setString(3, title);
                         ps.setInt(4, yr);
                         ps.setInt(5, pages);
                         
                         int res = ps.executeUpdate();
                         if(res == 0)
                         {
                             System.out.println("Failed insertion");
                         }
                         else
                         {
                             System.out.println("Insertion succeeded");
                         }
                         break;
                    case 8://insert new pub, choose a pub to replace with
                         String sql1 = "INSERT INTO publisher(pubName, pubAddress, "
                                 + "pubPhone, pubEmail)"
                                 + " VALUES(?,?,?,?)";
                         
                         ps = conn.prepareStatement(sql1);
                          System.out.println("Enter publisher name:");
                         String nm = in.nextLine();
                         System.out.println("Enter publisher address:");
                         String add = in.nextLine();
                         System.out.println("Enter publisher phone number:");
                         String phone = in.nextLine();
                         System.out.println("Enter publisher email:");
                         String email = in.nextLine();
                         System.out.println("Enter publisher to be replaced:");
                         String replace = in.nextLine();
                         //set the prepared statement ? to values
                         ps.setString(1, nm);
                         ps.setString(2, add);
                         ps.setString(3, phone);
                         ps.setString(4, email);
                         
                         int res2 = ps.executeUpdate();
                         if(res2 == 0)
                         {
                             System.out.println("Failed insertion");
                         }
                         else
                         {
                             System.out.println("Insertion succeeded");
                         }
                         String sql2 = "UPDATE publisher SET pubName = ?, pubAddress = ?,"
                                 + " pubPhone = ?, pubEmail = ?"
                                 + "WHERE pubName = ?";
                         ps = conn.prepareStatement(sql2);
                         ps.setString(1, replace );
                         ps.executeUpdate();
                         
                    case 9://delete book
                         String del = "DELETE FROM book where bookTitle = ?";
                         System.out.println("Enter a book title to be deleted:");
                         String b = in.nextLine();
                         ps = conn.prepareStatement(del);
                         ps.setString(1, b);
                         ps.executeUpdate();
                         System.out.println(b + " has been deleted.");
                         
                    case 10://end
                        break;

                }
            }
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(s != null)
                {
                    s.close();
                }
            }
            catch(SQLException se)
            {
                //nothing
            }
            try
            {
                if(conn != null)
                {
                    conn.close();
                }
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
            try
            {
                if(ps != null)
                {
                    ps.close();
                }
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
    }
    public static int menu()
    {
	int choice;//holds user choice
	do
	{
            Scanner in = new Scanner(System.in);
            System.out.println("1: List all Writing Groups");
            System.out.println("2: List data for a specified Group ");
            System.out.println("3: List all Publishers");
            System.out.println("4: Choose a Publisher's data to be listed");
            System.out.println("5: List all Book Titles");
            System.out.println("6: Choose a Book to be listed");
            System.out.println("7: Insert a new Book");
            System.out.println("8: Insert new Publisher");
            System.out.println("9: Remove a book");
            System.out.println("10:Exit");
            System.out.println("Enter your choice");
            choice = in.nextInt();
	}
	while(choice < 1 || choice > 10);//validation
	if(choice < 1 || choice > 10)
	{
            System.out.println("Not an option, try again");
	}
            return choice;			
    }
}
