package servlet;

import Dao.BaseDao;
import entity.Man;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PictureDifferentiate pictureDifferentiate;

    public UploadServlet() {
        super();
        this.pictureDifferentiate = new PictureDifferentiate();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseDao db = new BaseDao();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //String id1 = request.getParameter("id");

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Error: 表单必须包含 enctype=multipart/form-data");
            return;
        }

        // 获取路径来存储文件
        //String path = "D:";
        String path = this.getServletContext().getRealPath("/") + "image";
        System.out.println("图片存储路径：" + path);

        // 根据路径名创建一个 File实例
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();       // 如果不存在则创建此路径的目录
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 中文处理
        upload.setHeaderEncoding("utf-8");

        boolean flag = false;
        try {

            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        System.out.println("提交的是文件！");
                        String fileName = item.getName();
                        System.out.println("上传的文件名：" + fileName);

                        // 获取文件名后缀, 返回 "."在文件名最后出现的索引, 就是文件后缀名
                        // 给文件设置名字
                        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
                        String id = request.getParameter("id");
                        if (null == id){
                            List<Man> manList = new ArrayList<>();
                            InputStream inputStream = item.getInputStream();
                            String hashCode = pictureDifferentiate.getHash(inputStream);
                            Map<Man, String> res = db.getAllHashCode();
                            for (Map.Entry<Man, String> entry : res.entrySet()){
                                if (entry.getValue() != null) {
                                    if (pictureDifferentiate.distance(hashCode, entry.getValue()) < 6) {
                                        manList.add(entry.getKey());
                                    }
                                }
                            }
                            request.getSession().setAttribute("content", manList);
                            request.getSession().setAttribute("dataNum", 3);//每页显示的数据条数，用于控制jsp中循环输出结果的条数
                            request.getSession().setAttribute("totalPage", Math.ceil(manList.size() / 3.0));//一共有多少页
                            request.getSession().setAttribute("pageNum", 1);//初始状态下的页码，从第一页开始
                            request.getSession().setAttribute("totalNum", manList.size());
                            request.getRequestDispatcher("/index2.jsp").include(request, response);
                        }
                        System.out.println("id"+id);
                        String fileSaveName = id + "." + prefix; // id.后缀
                        System.out.println("文件名："+fileSaveName);
                        // 获取文件输入流
                        InputStream inputStream = item.getInputStream();
                        // 创建文件输出流，用于向指定文件名的文件写入数据
                        FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + fileSaveName);
                        int index = 0;

                        // 从输入流读取数据的下一个字节，到末尾时返回 -1
                        while ((index = inputStream.read()) != -1) {
                            fileOutputStream.write(index);  // 将指定字节写入此文件输出流
                        }

                        //计算hash值并写入数据库
                        String hashCode = pictureDifferentiate.getHash(new FileInputStream(new File(path + "/" + fileSaveName)));

                        db.setHash(id, hashCode);

                        // 关闭流
                        inputStream.close();
                        fileOutputStream.close();
                        item.delete();

                        request.getRequestDispatcher("/Index1.jsp").include(request, response);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
