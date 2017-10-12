package be.vdab;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BierenVanTotAlcoholMain {
    private static final String URL = "jdbc:mysql://localhost/bieren?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String BIEREN_VAN_TOT_ALCOHOL =
        "select alcohol, naam " +
        "from bieren " +
        "where alcohol between ? and ? " +
        "order by alcohol, naam";
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            BigDecimal minAlcohol, maxAlcohol;
            System.out.println("Min alcohol?: ");
            minAlcohol = scanner.nextBigDecimal();
            System.out.println("Max alcohol?: ");
            maxAlcohol = scanner.nextBigDecimal();
            try (Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                BIEREN_VAN_TOT_ALCOHOL)){
                statement.setBigDecimal(1, minAlcohol);
                statement.setBigDecimal(2, maxAlcohol);
                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        System.out.println(resultSet.getBigDecimal("alcohol")
                        + "\t" + resultSet.getString("naam"));
                    }
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }    
}
