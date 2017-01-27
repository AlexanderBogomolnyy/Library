package ua.training.library.view.tags;

import ua.training.library.model.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AccessControlViewTag extends TagSupport {
    private User user;

    public AccessControlViewTag() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_INCLUDE;
    }

    public boolean isAccept() {
        return user != null && user.getId() != 0;
    }
}
