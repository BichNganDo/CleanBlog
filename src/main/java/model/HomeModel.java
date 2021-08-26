package model;

import client.MysqlClient;
import common.ErrorCode;
import entity.HomePage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HomeModel {

    private static final MysqlClient dbClient = MysqlClient.getMysqlCli();
    private final String NAMETABLE = "homepage";
    public static HomeModel INSTANCE = new HomeModel();

    public List getHomePage() {
        List<HomePage> listHomePage = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return listHomePage;
            }
            String sql = "SELECT * FROM `" + NAMETABLE + "`";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                HomePage homePage = new HomePage();
                homePage.setId(rs.getInt("id"));
                homePage.setMain_title(rs.getString("main_title"));
                homePage.setSub_title(rs.getString("sub_title"));
                homePage.setImage(rs.getString("image"));
                listHomePage.add(homePage);

            }

            return listHomePage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }

        return listHomePage;
    }

    public HomePage getHomeByID(int id) {
        HomePage result = new HomePage();
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return result;
            }
            String sql = "SELECT * FROM `" + NAMETABLE + "` WHERE `id`='" + id + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setMain_title(rs.getString("main_title"));
                result.setSub_title(rs.getString("sub_title"));
                result.setImage(rs.getString("image"));
            }

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }
        return result;
    }

    public int editHomePage(int id, String main_title, String sub_title, String image) {
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return ErrorCode.CONNECTION_FAIL.getValue();
            }

            String sql = "UPDATE `" + NAMETABLE + "` "
                    + "SET `main_title`='" + main_title + "' , "
                    + "`sub_title`='" + sub_title + "', "
                    + "`image`='" + image + "'"
                    + "WHERE `id`='" + id + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int rs = preparedStatement.executeUpdate();

            return rs;
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }
        return ErrorCode.FAIL.getValue();
    }

}
