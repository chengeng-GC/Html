package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.City;
import org.apache.ibatis.session.SqlSession;
import tools.MyBatisUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryCityServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //同province
        SqlSession ss= MyBatisUtil.getSqlSession();
        String proid =req.getParameter("proid");
        String json="{}";
        if (proid !=null&&!"".equals(proid.trim())) {
            List<City> cities = ss.selectList("Dao.QueryDao.queryCityList", Integer.valueOf(proid));
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(cities);
        }
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw=resp.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }
}
