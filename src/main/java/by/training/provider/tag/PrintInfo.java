package by.training.provider.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PrintInfo extends TagSupport {
    private static final String LOGIN = "login";
    private static final String ROLE = "role";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        try {
            JspWriter out = pageContext.getOut();
            out.write("login:" + session.getAttribute(LOGIN) + "</br>");
            out.write("role:" + session.getAttribute(ROLE));
        } catch (IOException e) {
            throw new JspTagException(e);
        }
        return super.doStartTag();
    }
}
