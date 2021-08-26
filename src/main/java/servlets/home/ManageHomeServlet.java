package servlets.home;

import com.google.gson.Gson;
import common.APIResult;
import entity.HomePage;
import helper.ServletUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HomeModel;

public class ManageHomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        APIResult result = new APIResult(0, "Success");

        String action = request.getParameter("action");
        switch (action) {
            case "gethome": {
                List<HomePage> homePage = HomeModel.INSTANCE.getHomePage();
                if (homePage.size() > 0) {
                    result.setErrorCode(0);
                    result.setMessage("Success");
                    result.setData(homePage);
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
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String main_title = request.getParameter("main_title");
                String sub_title = request.getParameter("sub_title");
                String image = request.getParameter("image");

                HomePage homeByID = HomeModel.INSTANCE.getHomeByID(id);
                if (homeByID.getId() == 0) {
                    result.setErrorCode(-1);
                    result.setMessage("Thất bại!");
                    return;
                }

                int editHomePage = HomeModel.INSTANCE.editHomePage(id, main_title, sub_title, image);
                if (editHomePage >= 0) {
                    result.setErrorCode(0);
                    result.setMessage("Thành công!");
                } else {
                    result.setErrorCode(-1);
                    result.setMessage("Thất bại!");
                }
                break;
            }
            default:
                throw new AssertionError();
        }

        ServletUtil.printJson(request, response, gson.toJson(result));

    }
}
