package by.training.provider.servlet;

import by.training.provider.command.PagePath;
import by.training.provider.command.ParameterName;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("uploadImage")
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "image";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = PagePath.PROFILE;
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for (FileItem item : multipart) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        System.out.println(getServletContext().getContextPath()+ File.separator + UPLOAD_DIRECTORY);
                        item.write(new File(getServletContext().getContextPath() + UPLOAD_DIRECTORY));
                    }
                }
            } catch (Exception e) {
                req.setAttribute(ParameterName.ERROR, e);
                page = PagePath.ERROR;
            }
        }
        req.getRequestDispatcher(getServletContext().getContextPath() + page).forward(req, resp);
    }
}
