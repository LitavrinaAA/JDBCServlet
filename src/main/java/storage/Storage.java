package storage;

import lombok.extern.slf4j.Slf4j;
import model.Princess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class Storage {
    public List<Princess> getAll()  {
        List<Princess> princessList = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/juprincess";
        String username = "root";
        String password = "root";


        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            log.info("conn - ок ");
            String sql = "SELECT * FROM princess";
            log.info("sql - ок");
            Statement statement = conn.createStatement();
            log.info("conn.createStatement() - ок ");
            ResultSet result = statement.executeQuery(sql);
            log.debug("statement.executeQuery(sql) - ок ");
            while (result.next()) {
                Princess princess = new Princess();
                princess.setName(result.getString("name"));
                princess.setAge(result.getInt("age"));
                princessList.add(princess);
            }
        } catch (Exception e) {
            log.error("Sudenly, it is error:(");
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