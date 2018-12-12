package by.training.provider.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CheckRoleTag extends TagSupport {
    private static final Logger LOG = LogManager.getLogger(CheckRoleTag.class);
    private static final String ROLE = "role";
    private static final String ADMIN = "admin";
    private static final String ADMIN_ADD_TARIFF = "/jsp/common/Header.jsp";

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        if (session.getAttribute(ROLE) != null) {
            String role = session.getAttribute(ROLE).toString();
            try {
                if (ADMIN.equalsIgnoreCase(role)) {
                    pageContext.include(ADMIN_ADD_TARIFF);
                }
            } catch (ServletException | IOException e) {
                LOG.error(e);
            }
        }
        return super.doStartTag();
    }
}
