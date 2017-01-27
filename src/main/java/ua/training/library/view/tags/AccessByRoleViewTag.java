package ua.training.library.view.tags;

import ua.training.library.model.entity.User;
import ua.training.library.model.entity.states.Role;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AccessByRoleViewTag extends TagSupport {

    private static final String SPLIT_REGEX = "[,;]";
    private static final String WHITESPACE_REGEX = "\\s+";

    private User user;
    private String roles = "";
    private Boolean noUser = false;

    public AccessByRoleViewTag() {
    }

    @Override
    public int doStartTag() throws JspException {
        if((user == null || user.getId() == 0) && noUser) {
            return EVAL_BODY_INCLUDE;
        } else {
            List<Role> roleList = getSelectedRoles();
            if (user != null && !roleList.isEmpty()) {
                final boolean contains = roleList
                        .stream()
                        .filter(x -> x == user.getRole())
                        .findFirst()
                        .isPresent();
                if (contains)
                return EVAL_BODY_INCLUDE;
            }
        }
        return SKIP_BODY;
    }

    private List<Role> getSelectedRoles() {
        final String[] inputRoles = roles.replaceAll(WHITESPACE_REGEX, "").toUpperCase().split(SPLIT_REGEX);
        List<String> roleList = Arrays.asList(Role.values())
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        return Arrays.asList(inputRoles)
                .stream()
                .filter(roleList::contains)
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getNoUser() {
        return noUser;
    }

    public void setNoUser(Boolean noUser) {
        this.noUser = noUser;
    }
}
