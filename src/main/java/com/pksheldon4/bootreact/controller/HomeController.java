package com.pksheldon4.bootreact.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@ConditionalOnProperty(prefix = "example", name = "react-ui-active", havingValue = "blah")
public class HomeController {
    /**
     * ꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!
     * ꩜#%@*!                                                     ꩜#%@*!
     * ꩜#%@*!     Do not remove even though I look useless        ꩜#%@*!
     * ꩜#%@*!                                                     ꩜#%@*!
     * ꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!꩜#%@*!
     * <p>
     * This is required to address a strange issue where the first time
     * navigating to the base url results in a whitepage error
     */
    @GetMapping("/app/")
    public String web() {
        log.info("Calling HomeController with /");
        return "index";
    }

    //Note: If you use a path other than / you need to add this endpoint to ensure it contains the trailing /
    @GetMapping("/app")
    public RedirectView redirect(RedirectAttributes attributes) {
        log.info("Calling HomeController with /app");
        return new RedirectView("/app/");
    }

}
