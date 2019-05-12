package com.example.springsecuity.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.security.Principal;

@Controller
public class SpringSecurityController
{
    @RequestMapping(path = {"/","/home"})
    public String gotToHomeDefault()
    {
        return "home";
    }


    @RequestMapping(path = "/hello")
    public String gotToHello(Principal principal,Model model,Authentication authentication)
    {

     Object details=authentication.getDetails();

     WebAuthenticationDetails webAuthenticationDetails=(WebAuthenticationDetails)details;

       model.addAttribute("name",principal.getName());
        return "hello";
    }
    @RequestMapping(path = "/login")
    public String gotToLogin()
    {
        return "login";
    }


}
