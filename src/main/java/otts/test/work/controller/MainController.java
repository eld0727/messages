package otts.test.work.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.messages.UserVisitPage;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 03.09.2015.<br/>
 * Test controller
 */
@Controller
@ResponseBody
public class MainController {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home() {
        rabbitTemplate.convertAndSend(QueueNames.USER_VISIT_PAGE, new UserVisitPage(1));
        return "Hello World!";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String postHome() {
        return "Post OK!";
    }
}
