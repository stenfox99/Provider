package by.training.provider.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserLink extends TagSupport {
    private static final String ROLE = "role";
    private static final String ADMIN = "admin";
    private static final String LOCALE = "locale";
    private static final String LANGUAGE = "en_EN";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        try {
            if (session.getAttribute(ROLE) != null && session.getAttribute(ROLE).toString().equals(ADMIN)) {
                JspWriter out = pageContext.getOut();
                if (session.getAttribute(LOCALE) != null && session.getAttribute(LOCALE).toString().equals(LANGUAGE)) {
                    out.write("<a class=\"navbar-brand\" href=\"" + pageContext.getServletContext().getContextPath() + "/controller?command=print_user&pageNumber=0\">Users</a>");
                }else{
                    out.write("<a class=\"navbar-brand\" href=\"" + pageContext.getServletContext().getContextPath() + "/controller?command=print_user&pageNumber=0\">Пользователи</a>");
                }
            }
        } catch (IOException e) {
            throw new JspTagException(e);
        }
        return super.doStartTag();
    }
}