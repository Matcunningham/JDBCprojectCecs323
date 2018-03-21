// Ryan Huey
// Mathew Cunningham
// CECS 323 
// Tue/Thurs

package jdbc.project;
import java.sql.*;
import java.util.*;

public class JDBCProject
{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/jdbcproject";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";
    
    public static void listGroups(Statement s, ResultSet rs) throws SQLException
    {
        rs = s.executeQuery("SELECT * FROM writingGroup");
        // Print column labels
        System.out.printf("%-25s  %-20s  %-12s %-15s \n", 
                "Group Name",
                "Head Writer", 
                "Year Formed",
                "Subject");
        // Print data
        while(rs.next())
        {
            System.out.printf("%-25s  %-20s  %-12d %-15s \n", 
                    rs.getString("groupName"),
                    rs.getString("headWriter"), 
                    rs.getInt("yearFormed"),
                    rs.getString("subject"));
        }
    }
        
    public static void groupQuery(Scanner in, PreparedStatement ps, ResultSet rs, Connection conn) throws SQLException
    {
        System.out.println("Enter group name: ");
        String gName = in.nextLine();
        String sql = "SELECT * FROM book NATURAL JOIN publisher NATURAL JOIN writingGroup WHERE UPPER(groupname) LIKE UPPER(?)"; //edit for books and pubs
        ps = conn.prepareStatement(sql);
        ps.setString(1, "%" +gName + "%");
        rs = ps.executeQuery();

        // Print column labels
        System.out.printf("%-25s  %-25s  %-25s %-12s %-12s  %-45s  %-15s %-40s %-20s  %-12s  %-15s \n", 
                "Group Name",
                "Pub Name", 
                "Book Title",
                "Pub Year",
                "PGS",
                "Pub Address", 
                "Pub Phone",
                "Pub Email", 
                "Head Writer", 
                "Year Formed",
                "Subject");                        
        // Print data
        while(rs.next())
        {
            System.out.printf("%-25s  %-25s  %-25s %-12s %-12s  %-45s  %-15s %-40s %-20s  %-12s  %-15s \n", 
                    rs.getString("groupName"),
                    rs.getString("pubName"), 
                    rs.getString("bookTitle"),
                    rs.getString("yearPublished"),
                    rs.getString("numberPages"),
                    rs.getString("pubAddress"), 
                    rs.getString("pubPhone"),
                    rs.getString("pubEmail"), 
                    rs.getString("headWriter"), 
                    rs.getString("yearFormed"),
                    rs.getString("subject"));
        }
    }
    
    public static void listPubs(Statement s, ResultSet rs) throws SQLException
    {
        rs = s.executeQuery("SELECT * FROM publisher");
        // Print column labels
        System.out.printf("%-25s  %-45s  %-15s %-40s \n", 
                "Pub Name",
                "Pub Address", 
                "Pub Phone",
                "Pub Email");  
        // Print data
        while(rs.next())
        {
            System.out.printf("%-25s  %-45s  %-15s %-40s \n", 
                    rs.getString("pubName"),
                    rs.getString("pubAddress"), 
                    rs.getString("pubPhone"),
                    rs.getString("pubEmail"));
        }    
    }
    
    public static void pubQuery(Scanner in, PreparedStatement ps, ResultSet rs, Connection conn) throws SQLException
    {
        System.out.println("Enter publisher name: ");
        String pName = in.nextLine();
        String sql = "SELECT * FROM book NATURAL JOIN writingGroup NATURAL JOIN publisher WHERE UPPER(pubname) LIKE UPPER(?)"; //edit for books and pubs
        ps = conn.prepareStatement(sql);
        ps.setString(1, "%" +pName + "%");
        rs = ps.executeQuery();
        System.out.printf("%-25s  %-25s  %-25s %-12s %-12s %-20s %-12s %-15s %-45s %-15s %-40s \n", 
                "Pub Name",
                "Group Name", 
                "Book Title",
                "Pub Year",
                "PGS",
                "Head Writer",
                "Year Formed",
                "Subject",
                "Pub Address", 
                "Pub Phone",
                "Pub Email");                        
        while(rs.next())
        {
            System.out.printf("%-25s  %-25s  %-25s %-12s %-12s %-20s %-12s %-15s %-45s %-15s %-40s \n", 
                    rs.getString("pubName"),
                    rs.getString("groupName"), 
                    rs.getString("bookTitle"),
                    rs.getString("yearPublished"),
                    rs.getString("numberPages"),
                    rs.getString("headWriter"),
                    rs.getString("yearFormed"),
                    rs.getString("subject"),
                    rs.getString("pubAddress"), 
                    rs.getString("pubPhone"),
                    rs.getString("pubEmail"));

        }    
    }
    
    public static void listBooks(Statement s, ResultSet rs) throws SQLException
    {
        rs = s.executeQuery("SELECT bookTitle, pubName, groupName, yearPublished, numberPages FROM book");
        System.out.printf("%-25s %-25s %-25s %-15s %-12s \n", 
                "Book Title",
                "Pub Name",
                "Group Name",
                "Year Published",
                "Number Pages");
        while(rs.next())
        {
            System.out.printf("%-25s %-25s %-25s %-15s %-12s \n", 
                    rs.getString("bookTitle"),
                    rs.getString("pubName"),
                    rs.getString("groupName"),
                    rs.getString("yearPublished"),
                    rs.getString("numberPages"));
        }    
    }
    
    public static void bookQuery(Scanner in, PreparedStatement ps, ResultSet rs, Connection conn) throws SQLException
    {
        System.out.println("Enter book name: ");
        String bName = in.nextLine();
        String sql = "SELECT * FROM book NATURAL JOIN writingGroup NATURAL JOIN publisher WHERE UPPER(bookTitle) LIKE UPPER(?)"; //edit for books and pubs
        ps = conn.prepareStatement(sql);
        ps.setString(1, "%" +bName + "%");
        rs = ps.executeQuery();

        System.out.printf("%-25s  %-25s  %-25s %-12s %-12s %-20s %-12s %-15s %-45s %-15s %-40s \n", 
                "Pub Name",
                "Group Name", 
                "Book Title",
                "Pub Year",
                "PGS",
                "Head Writer",
                "Year Formed",
                "Subject",
                "Pub Address", 
                "Pub Phone",
                "Pub Email");                        

        while(rs.next())
        {
            System.out.printf("%-25s  %-25s  %-25s %-12s %-12s %-20s %-12s %-15s %-45s %-15s %-40s \n", 
                    rs.getString("pubName"),
                    rs.getString("groupName"), 
                    rs.getString("bookTitle"),
                    rs.getString("yearPublished"),
                    rs.getString("numberPages"),
                    rs.getString("headWriter"),
                    rs.getString("yearFormed"),
                    rs.getString("subject"),
                    rs.getString("pubAddress"), 
                    rs.getString("pubPhone"),
                    rs.getString("pubEmail"));
        }    
    }
    
    public static void insertBook(Scanner in, PreparedStatement ps, ResultSet rs, Connection conn) throws SQLException
    {
        ps = conn.prepareStatement("INSERT INTO book (groupName, pubName, "
                + "bookTitle, yearPublished, numberPages)"
                + " VALUES(?,?,?,?,?)");
        System.out.println("Enter book title:");
        String title = in.nextLine();
        System.out.println("Enter year published:");
        int yr = in.nextInt();
        System.out.println("Enter number of pages:");                                
        int pages = in.nextInt();
        in.nextLine();
        System.out.println("Enter group name:");
        String group = in.nextLine();
        System.out.println("Enter publisher name:");
        String pub = in.nextLine();

        ps.setString(1, group);
        ps.setString(2, pub);
        ps.setString(3, title);
        ps.setInt(4, yr);
        ps.setInt(5, pages );

        int res = ps.executeUpdate();
        if(res == 0)
        {
            System.out.println("Failed insertion");
        }
        else
        {
            System.out.println("Insertion succeeded");
        }    
    }
    
    public static void insertUpdatePub(Scanner in, PreparedStatement ps, ResultSet rs, Connection conn) throws SQLException
    {
        String sql1 = "INSERT INTO publisher(pubName, pubAddress, "
                + "pubPhone, pubEmail)"
                + " VALUES(?,?,?,?)";

        ps = conn.prepareStatement(sql1);
        System.out.println("Enter new publisher name:");
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

        // Updating books for new publisher
        String sql2 = "UPDATE book SET pubName = ? WHERE pubName = ?";
        ps = conn.prepareStatement(sql2);
        ps.setString(1, nm);
        ps.setString(2, replace);

        res2 = ps.executeUpdate();
        if(res2 == 0)
        {
            System.out.println("Failed to update book publisher, check that you spelled the old publisher correctly.");
        }
        else
        {
            System.out.println("All books published by " + replace + " updated, now published by: " + nm);
        }    
    }
    
    public static void delBook(Scanner in, PreparedStatement ps, Connection conn) throws SQLException
    {
        String del = "DELETE FROM book where bookTitle = ?";
        System.out.println("Enter a book title to be deleted:");
        String b = in.nextLine();
        ps = conn.prepareStatement(del);
        ps.setString(1, b);
        int res = ps.executeUpdate();
        if(res == 0)
        {
            System.out.println(b + " could not be deleted");
                    
        }
        else
        {
            System.out.println(b + " has been deleted.");
        }
    }
      
    public static int menu()
    {
        int choice = 0;//holds user choice
        try
        {
            do
            {
                Scanner in = new Scanner(System.in);
                System.out.println("\n1: List all Writing Groups");
                System.out.println("2: List data for a specified Group ");
                System.out.println("3: List all Publishers");
                System.out.println("4: Choose a Publisher's data to be listed");
                System.out.println("5: List all Book Titles");
                System.out.println("6: Choose a Book to be listed");
                System.out.println("7: Insert a new Book");
                System.out.println("8: Insert new Publisher and update books to that publisher");
                System.out.println("9: Remove a book");
                System.out.println("10:Exit");
                System.out.println("Enter your choice\n");
                choice = in.nextInt();
                if((choice < 1 || choice > 10))
                {
                    System.out.println("You must enter a number 1 - 10");
                }
            }
            while(choice < 1 || choice > 10);//validation
        }
        catch(InputMismatchException e)
        {
            System.out.println("You must enter a number 1 - 10");
        }
        return choice;   
    }
    
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Connection conn = null; //connect to driver
        Statement s = null;
        PreparedStatement ps = null; //used for user input
        ResultSet rs = null;
        int choice = 0; //initialize choice value
        try
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            s = conn.createStatement();
            while(choice != 10)
            {
                choice = menu();
                switch(choice)
                {
                    case 1: //list all writing groups
                        listGroups(s, rs);
                        break;

                    case 2: //list data for group specified, + all data for assoc. books and pubs
                        groupQuery(in, ps, rs, conn);
                        break;

                    case 3: //list all publishers
                        listPubs(s, rs);
                        break;

                    case 4: //list all data for specified publisher + data for assoc. books and groups
                        pubQuery(in, ps, rs, conn);
                        break;

                    case 5: //list all book titles
                        listBooks(s, rs);
                        break;

                    case 6://list all data for book specified + all data for assoc. pub and group
                        bookQuery(in, ps, rs, conn);
                        break;

                    case 7://insert new book
                        try
                        {
                            insertBook(in, ps, rs, conn);
                        }
                        catch(SQLException e)
                        {
                            System.out.println("ERROR: Please Try Again, You must enter the group/"
                                    + "publisher name if they don't already exist in the database\n");
                        }
                        catch(InputMismatchException ime)
                        {
                            System.out.println("Wrong Input Type, Please Try Again");
                            in.nextLine();
                        }
                        break;

                    case 8://insert new publisher and update books from old pub to the new pub
                        try
                        {
                            insertUpdatePub(in, ps, rs, conn);
                        }
                        catch(SQLException se)
                        {
                              System.out.println("DATABASE ERROR, TRY AGAIN");    
                        }
                        break;

                    case 9://delete book
                        delBook(in, ps, conn);
                        break;

                    case 10://end
                        break;
                }
            }
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("ERROR: Please Try Again");
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            System.out.println("Java Database Connection Error");
        }
        finally
        {
            try
            {
                if(rs != null)
                {
                    rs.close();
                }
            }
            catch(SQLException se)
            {
                //nothing
            }
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
}