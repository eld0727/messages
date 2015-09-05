package otts.test.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by alex on 03.09.2015.<br/>
 * Test controller
 */
@Controller
@ResponseBody
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String postHome() {
        return "Post OK!";
    }
}
