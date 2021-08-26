package servlets.about;

import com.google.gson.Gson;
import common.APIResult;
import entity.AboutPage;
import helper.ServletUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AboutModel;

public class ManageAboutPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        APIResult result = new APIResult(0, "Success");

        String action = request.getParameter("action");
        switch (action) {
            case "getabout": {
                List<AboutPage> aboutPage = AboutModel.INSTANCE.getAboutPage();
                if (aboutPage.size() > 0) {
                    result.setErrorCode(0);
                    result.setMessage("Success");
                    result.setData(aboutPage);
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
                String content = request.getParameter("content");
                String image = request.getParameter("image");

                AboutPage aboutByID = AboutModel.INSTANCE.getAboutByID(id);
                if (aboutByID.getId() == 0) {
                    result.setErrorCode(-1);
                    result.setMessage("Thất bại!");
                    return;
                }

                int editAboutPage = AboutModel.INSTANCE.editAboutPage(id, main_title, sub_title, content, image);
                if (editAboutPage >= 0) {
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
