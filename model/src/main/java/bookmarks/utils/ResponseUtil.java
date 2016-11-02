package bookmarks.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by liaoxiang on 2016/9/27.
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o) throws Exception{

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();

    }

    public static void responseSuccess(HttpServletResponse response, Object o) throws Exception{

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();

    }

    public static void responseFail(HttpServletResponse response, Object o) throws Exception{

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();

    }
}
