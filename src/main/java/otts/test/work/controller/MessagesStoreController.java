package otts.test.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.dto.MessageStoreState;
import otts.test.work.messages.UserClickAnyButton;
import otts.test.work.messages.UserClickColorButton;
import otts.test.work.messages.UserVisitsPage;
import otts.test.work.service.MessagesStorageService;

/**
 * Created by alex on 06.09.2015.<br/>
 * Controller returning messages store state
 */
@Controller
@ResponseBody
public class MessagesStoreController {

    @Autowired
    private MessagesStorageService messagesStorageService;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public MessageStoreState getStoreState() {
        return new MessageStoreState(
            messagesStorageService.getExpectedMessages(),
            messagesStorageService.getFinishedMessages()
        );
    }

    @RequestMapping(value = "/messages/visit", method = RequestMethod.POST)
    public void storeUserVisitPageMessage(
        @RequestBody UserVisitsPage userVisitsPage
    ) {
        messagesStorageService.storeMessage(userVisitsPage);
    }

    @RequestMapping(value = "/messages/button/click/any", method = RequestMethod.POST)
    public void storeUserClickAnyButtonMessage(
            @RequestBody UserClickAnyButton userClickAnyButton
    ) {
        messagesStorageService.storeMessage(userClickAnyButton);
    }

    @RequestMapping(value = "/messages/button/click/color", method = RequestMethod.POST)
    public void storeUserClickColorButtonMessage(
            @RequestBody UserClickColorButton userClickColorButton
    ) {
        messagesStorageService.storeMessage(userClickColorButton);
    }
}
