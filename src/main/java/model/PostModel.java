package model;

import client.MysqlClient;
import common.ErrorCode;
import entity.AboutPage;
import entity.PostPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostModel {

    private static final MysqlClient dbClient = MysqlClient.getMysqlCli();
    private final String NAMETABLE = "postpage";
    public static PostModel INSTANCE = new PostModel();

    public PostPage getPostByID(int id) {
        PostPage result = new PostPage();
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
                result.setStatus(rs.getString("status"));
                result.setAuthor(rs.getString("author"));

                long currentTimeMillis = rs.getLong("post_date");
                Date date = new Date(currentTimeMillis);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String dateString = sdf.format(date);
                result.setPost_date(dateString);
            }

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }
        return result;
    }

    public List getAllPostPage() {
        List<PostPage> listAllPostPage = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return listAllPostPage;
            }
            String sql = "SELECT * FROM `" + NAMETABLE + "`";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PostPage postPage = new PostPage();
                postPage.setId(rs.getInt("id"));
                postPage.setMain_title(rs.getString("main_title"));
                postPage.setSub_title(rs.getString("sub_title"));
                postPage.setContent(rs.getString("content"));

                long currentTimeMillis = rs.getLong("post_date");
                Date date = new Date(currentTimeMillis);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String dateString = sdf.format(date);
                postPage.setPost_date(dateString);

                postPage.setImage(rs.getString("image"));
                postPage.setStatus(rs.getString("status"));
                postPage.setAuthor(rs.getString("author"));
                listAllPostPage.add(postPage);

            }

            return listAllPostPage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }

        return listAllPostPage;
    }

    public int addPost(String main_title, String sub_title, String content, String image,
            String status, String author) {
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return ErrorCode.CONNECTION_FAIL.getValue();
            }
            String sql = "INSERT INTO `" + NAMETABLE + "`"
                    + "(`main_title`, `sub_title`, `content`, `image`,`post_date`, `status`,`author`) "
                    + "VALUES "
                    + "('" + main_title + "', '" + sub_title + "', '" + content + "','" + image + "', '"
                    + System.currentTimeMillis() + "', '" + status + "','" + author + "')";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int rs = preparedStatement.executeUpdate();

            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }

        return ErrorCode.FAIL.getValue();
    }

    public int editPost(int id, String main_title, String sub_title, String content, String image,
            String status, String author) {
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return ErrorCode.CONNECTION_FAIL.getValue();
            }

            String sql = "UPDATE `" + NAMETABLE + "` SET `main_title`='" + main_title + "', "
                    + "`sub_title`='" + sub_title + "', `content`='" + content + "', "
                    + "`image`='" + image + "', `status`='" + status + "', `author`='" + author + "'"
                    + " WHERE `id`='" + id + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int rs = preparedStatement.executeUpdate();

            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }
        return ErrorCode.FAIL.getValue();
    }

    public int deletePost(int id) {
        Connection conn = null;
        try {
            conn = dbClient.getDbConnection();
            if (null == conn) {
                return ErrorCode.CONNECTION_FAIL.getValue();
            }

            PostPage postByID = getPostByID(id);
            if (postByID.getId() == 0) {
                return ErrorCode.NOT_EXIST.getValue();
            }
            String sql = "DELETE FROM `" + NAMETABLE + "` WHERE `id`='" + id + "'";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            int rs = preparedStatement.executeUpdate();

            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.releaseDbConnection(conn);
        }
        return ErrorCode.FAIL.getValue();
    }
}
