package storage;

import model.Princess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public List<Princess> getAll()  {
        List<Princess> princessList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/juprincess";
        String username = "root";
        String password = "root";


        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("conn - ок ");
            String sql = "SELECT * FROM princess";
            System.out.println("sql - ок");
            Statement statement = conn.createStatement();
            System.out.println("conn.createStatement() - ок ");
            ResultSet result = statement.executeQuery(sql);
            System.out.println("statement.executeQuery(sql) - ок ");
            while (result.next()) {
                Princess princess = new Princess();
                princess.setName(result.getString("name"));
                princess.setAge(result.getInt("age"));
                princessList.add(princess);
            }
        } catch (Exception e) {
            System.out.println("Sudenly, it is error:(");
            e.printStackTrace();
        }
        return princessList;
    }
}

//        try(Connection conn = DriverManager.getConnection(url, username, password)){
//            String sql = "INSERT INTO princess (name, age, country) VALUES (?, ?, ?)";
//            PreparedStatement statement = conn.prepareStatement(sql);
//
//            statement.setString(1, "Anna");
//            statement.setString(2, "39");
//            statement.setString(3, "1");
//            int rowsInserted = statement.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("Данные успешно добавлены в БД!");
//            }
//        } catch (SQLException e){
//                System.err.println("Ошибка при попытке вставки данных в БД: " + e.getMessage());
//            }