package org.xuspi.xuspiurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.xuspi.xuspiurl.model.Url;
import org.xuspi.xuspiurl.service.UrlService;

@Controller
public class XuspiurlViewController {

    private final UrlService urlService;

    @Autowired
    public XuspiurlViewController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/")
    public String form() {
        return "form";
    }

    @PostMapping("/short")
    public ModelAndView shorten(@RequestParam String originalUrl) {
        Url url = urlService.generateShortUrl(originalUrl);
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("shortUrl", url.getShortUrl());
        return modelAndView;
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getShortUrl(@PathVariable String shortUrl) {
        Url url = urlService.getOriginalUrl(shortUrl);
        return url == null ? new RedirectView("notFound") : new RedirectView(url.getOriginalUrl());
    }
}