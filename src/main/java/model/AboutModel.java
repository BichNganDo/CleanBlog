package model;

import client.MysqlClient;
import common.ErrorCode;
import entity.AboutPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AboutModel {

    private static final MysqlClient dbClient = MysqlClient.getMysqlCli();
    private final String NAMETABLE = "aboutpage";
    public static AboutModel INSTANCE = new AboutModel();

    public List getAboutPage() {
        List<AboutPage> listAboutPage = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return listAboutPage;
            }
            String sql = "SELECT * FROM `" + NAMETABLE + "`";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                AboutPage aboutPage = new AboutPage();
                aboutPage.setId(rs.getInt("id"));
                aboutPage.setMain_title(rs.getString("main_title"));
                aboutPage.setSub_title(rs.getString("sub_title"));
                aboutPage.setContent(rs.getString("content"));
                aboutPage.setImage(rs.getString("image"));
                listAboutPage.add(aboutPage);

            }

            return listAboutPage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }

        return listAboutPage;
    }

    public AboutPage getAboutByID(int id) {
        AboutPage result = new AboutPage();
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
                result.setContent(rs.getString("content"));
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

    public int editAboutPage(int id, String main_title, String sub_title, String content, String image) {
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return ErrorCode.CONNECTION_FAIL.getValue();
            }

            String sql = "UPDATE `" + NAMETABLE + "` "
                    + "SET `main_title`='" + main_title + "' , "
                    + "`sub_title`='" + sub_title + "', "
                    + "`content`='" + content + "', "
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
