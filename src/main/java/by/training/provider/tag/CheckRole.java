package by.training.provider.tag;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CheckRole extends TagSupport {
    private static final String ROLE = "role";
    private static final String ADMIN = "admin";
    private static final String ADMIN_ADD_TARIFF = "/jsp/common/Header.jsp";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String role = session.getAttribute(ROLE).toString();
        try {
            if (ADMIN.equalsIgnoreCase(role)) {
                pageContext.include(ADMIN_ADD_TARIFF);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
