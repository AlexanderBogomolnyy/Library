package ua.training.library.view.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class AccessControlViewAcceptTag extends BodyTagSupport {

    private static final Logger logger = Logger.getLogger(AccessControlViewAcceptTag.class);

    @Override
    public int doStartTag() throws JspException {
        AccessControlViewTag parent = (AccessControlViewTag) findAncestorWithClass(this, AccessControlViewTag.class);
        if (parent == null) {
            throw new JspTagException("accept tag not inside acv tag");
        }
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        AccessControlViewTag parent = (AccessControlViewTag) findAncestorWithClass(this, AccessControlViewTag.class);
        if (parent.isAccept()) {
            try {
                BodyContent body = getBodyContent();
                JspWriter out = body.getEnclosingWriter();
                out.print(body.getString());
            } catch (IOException ioe) {
                logger.error("Error in \"acv\" -> \"accept\" Tag: " + ioe);
            }
        }
        return SKIP_BODY;
    }
}
