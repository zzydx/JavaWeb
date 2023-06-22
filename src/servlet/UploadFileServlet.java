package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        //文件上传
//        System.out.println("文件上传");
//        System.out.println(req.getParameter("photo"));
//        ServletInputStream inputStream = req.getInputStream();
//        byte[] bytes = new byte[102400000];
//        int read = inputStream.read(bytes);
//        System.out.println(new String(bytes,0,read));

        //首先判断上传的数据是否是多端的数据，只有是多段的数据才是文件上传
        if (ServletFileUpload.isMultipartContent(req)) {
            //创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //解析上传的数据，得到每一个表单项fileitem
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                //循环判断每一个表单项是普通类型还是上传的文件
                for (FileItem fileItem : fileItems) {
                    if(fileItem.isFormField()){
//                        //普通表单项
//                        System.out.println("表单项的name值" + fileItem.getFieldName());
//                        //参数utf-8解决乱码问题
//                        System.out.println("表单项的value属性值" + fileItem.getString("UTF-8"));
                    }else {
                        //上传的文件
                        System.out.println("表单项的name属性值" + fileItem.getFieldName());
                        System.out.println("上传的文件名" + fileItem.getName());
                        fileItem.write(new File("D:\\1Test\\" + fileItem.getName()));
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
