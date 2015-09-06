package otts.test.work.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.messages.UserClickColorButton;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 06.09.2015.<br/>
 * Controller reacting on color buttons click
 */
@Controller
public class ButtonController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ResponseBody
    @RequestMapping(value = "/buttons/click", method = RequestMethod.POST)
    public void buttonClick(
        @RequestBody UserClickColorButton userClickColorButton
    ) {
        rabbitTemplate.convertAndSend(QueueNames.BUTTON_CLICK, userClickColorButton);
    }
}
