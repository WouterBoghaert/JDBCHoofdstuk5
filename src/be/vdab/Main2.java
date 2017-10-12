package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main2 {
    private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String UPDATE_PRIJS =
        "update planten set verkoopprijs = verkoopprijs * 1.1 where naam = ?";
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Naam: ");
            String naam = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(
                URL,USER,PASSWORD);
                //Statement statement = connection.createStatement()){
                PreparedStatement statement = 
                connection.prepareStatement(UPDATE_PRIJS)){
                statement.setString(1,naam);
                System.out.println(statement.executeUpdate());
                //"update planten set verkoopprijs = verkoopprijs * 1.1 where naam = '"
                //+ naam + "'"));
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }    
}
