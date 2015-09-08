package otts.test.work.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import otts.test.work.dto.MessageStoreState;
import otts.test.work.messages.UserVisitsPage;
import otts.test.work.service.MessagesStorageService;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 03.09.2015.<br/>
 * View controller
 */
@Controller
public class MainController {

    /**
     * Users count supported in application
     */
    private final static int USERS_COUNT = 10;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessagesStorageService messagesStorageService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("usersCount", USERS_COUNT);
        return "index";
    }

    @RequestMapping(value = {"/{userId}", "/{userId}/"}, method = RequestMethod.GET)
    public String userPage(
            @PathVariable int userId,
            Model model
    ) {
        if(userId < 1 || userId > USERS_COUNT) {
            throw new IllegalArgumentException("User must be between 1 and " + USERS_COUNT + " but " + userId + " found!");
        }
        rabbitTemplate.convertAndSend(QueueNames.USER_VISIT_PAGE, new UserVisitsPage(userId));
        model.addAttribute("messagesStore", new MessageStoreState(
            messagesStorageService.getExpectedMessages(),
            messagesStorageService.getFinishedMessages()
        ));
        return "userPage";
    }

}
