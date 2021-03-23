package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Province;
import org.apache.ibatis.session.SqlSession;
import tools.MyBatisUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class QueryProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SqlSession ss= MyBatisUtil.getSqlSession();
        //调用Dao获取所有省份信息
        List<Province> provinces=ss.selectList("Dao.QueryDao.queryProvinceList");
        //把list转为json格式数据，输出给ajax
        String json="{}";
        if (provinces!=null){
            //调用jackson工具库，实现list转json
            ObjectMapper om=new ObjectMapper();
            json=om.writeValueAsString(provinces);
        }
        //输出json数据，响应ajax请求,返回数据
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw=resp.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }


}
