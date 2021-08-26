package servlets.post;

import com.google.gson.Gson;
import common.APIResult;
import entity.PostPage;
import helper.HttpHelper;
import helper.ServletUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PostModel;
import org.json.JSONObject;

public class ManagePostServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        APIResult result = new APIResult(0, "Success");

        String action = request.getParameter("action");
        switch (action) {
            case "getpost": {
                List<PostPage> allPostPage = PostModel.INSTANCE.getAllPostPage();
                if (allPostPage.size() > 0) {
                    result.setErrorCode(0);
                    result.setMessage("Success");
                    result.setData(allPostPage);
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Fail");
                }
                break;
            }

            default:
                throw new AssertionError();
        }

        ServletUtil.printJson(request, response, gson.toJson(result));

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        APIResult result = new APIResult(0, "Success");

        String action = request.getParameter("action");
        switch (action) {
            case "add": {
                String main_title = request.getParameter("main_title");
                String sub_title = request.getParameter("sub_title");
                String content = request.getParameter("content");
                String image = request.getParameter("image");
                String status = request.getParameter("status");
                String author = request.getParameter("author");

                int addPost = PostModel.INSTANCE.addPost(main_title, sub_title, content, image, status, author);
                if (addPost >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Thêm post thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Thêm post thất bại!");
                }
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String main_title = request.getParameter("main_title");
                String sub_title = request.getParameter("sub_title");
                String content = request.getParameter("content");
                String image = request.getParameter("image");
                String status = request.getParameter("status");
                String author = request.getParameter("author");

                PostPage postByID = PostModel.INSTANCE.getPostByID(id);
                if (postByID.getId() == 0) {
                    result.setErrorCode(-1);
                    result.setMessage("Thất bại!");
                    return;
                }

                int editPost = PostModel.INSTANCE.editPost(id, main_title, sub_title, content, image, status, author);
                if (editPost >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Thất bại!");
                }
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                int deletePost = PostModel.INSTANCE.deletePost(id);
                if (deletePost >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Xóa post thành công!");
                } else {
                    result.setErrorCode(-2);
                    result.setMessage("Xóa post thất bại!");
                }
                break;
            }
            default:
                throw new AssertionError();
        }

        ServletUtil.printJson(request, response, gson.toJson(result));

    }

}
