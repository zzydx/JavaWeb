package servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class DownFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //获取要下载的文件名
        String downloadFilename = "我.jpg";
        //通过ServletContext对象读取要下载的文件内容
        ServletContext servletContext = getServletContext();
        //获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/imgs/" + downloadFilename);
        System.out.println(mimeType);
        //回传前通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);
        //告诉客户端收到的数据是用于下载使用
        //Content-Disposition表示内容处理
        //attachment表示附件,URL编码重新对文件名进行编码把汉字转换成%XX%XX(十六进制)
        resp.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode("中国.jpg","UTF-8"));
       // resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(downloadFilename, "UTF-8").replaceAll("\\+", "%20"));

        InputStream inputStream = servletContext.getResourceAsStream("/imgs/" + downloadFilename);
        //获取响应的输出流
        OutputStream outputStream = resp.getOutputStream();
        //读取输入流复制给输出流，输出给客户端
        IOUtils.copy(inputStream, outputStream);

    }
}
