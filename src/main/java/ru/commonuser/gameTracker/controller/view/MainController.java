package ru.commonuser.gameTracker.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.commonuser.gameTracker.exception.ServersException;
import ru.commonuser.gameTracker.service.UrlService;

@Controller
public class MainController {

    private UrlService urlService;

    @Autowired
    public MainController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = {"/badbrowser"}, method = RequestMethod.GET)
    public String badBrowser() {
        return "errors/badBrowser";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getPublicPage(Authentication authentication) {
        return "index";
    }

    @PostMapping(value = {"/", "/index"})
    public ModelAndView getSearchPage(String searchName, Authentication authentication) throws ServersException  {
        ModelAndView page = new ModelAndView("/index");
        page.addObject("searchName", searchName);
        page.addObject("links", urlService.getLinks(searchName));
        return page;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}
