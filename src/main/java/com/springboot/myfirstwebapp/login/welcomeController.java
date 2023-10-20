package com.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") // model attribute that needs to be used
// in other controllers have to be put here in SessionAttributes
public class welcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWelcomePage(ModelMap model) {

        model.put("name", getLoggedInUsername());
        return "Welcome";
    }

    // we need to get rid of the hardcoded values from here, and we get the value of
    // username from the
    // spring security, spring security will have the user name and password of
    // currently signed in user.

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
        // since our authentication details are saved in this variable we use it to get
        // the username
    }

}
