package model;

import client.MysqlClient;
import common.ErrorCode;
import entity.ContactPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InfoContactModel {

    private static final MysqlClient dbClient = MysqlClient.getMysqlCli();
    private final String NAMETABLE = "contactpage";
    public static ContactModel INSTANCE = new ContactModel();

    public List getContactPage() {
        List<ContactPage> listContactPage = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return listContactPage;
            }
            String sql = "SELECT * FROM `" + NAMETABLE + "`";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ContactPage contactPage = new ContactPage();
                contactPage.setId(rs.getInt("id"));
                contactPage.setMain_title(rs.getString("main_title"));
                contactPage.setSub_title(rs.getString("sub_title"));
                contactPage.setContent(rs.getString("content"));
                contactPage.setImage(rs.getString("image"));
                listContactPage.add(contactPage);

            }

            return listContactPage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }

        return listContactPage;
    }

    public ContactPage getContactByID(int id) {
        ContactPage result = new ContactPage();
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

}
