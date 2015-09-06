package otts.test.work.dto;

import otts.test.work.messages.UserMessage;
import otts.test.work.service.MessagesStorageService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 06.09.2015.<br/>
 * DTO keeping state of messages store
 */
public class MessageStoreState {

    /**
     * Value of {@link MessagesStorageService#getExpectedMessages()} formatted by {@link UserMessage#presentation()}
     */
    private final List<String> expectedMessages;

    /**
     * Value of {@link MessagesStorageService#getFinishedMessages()} formatted by {@link UserMessage#presentation()}
     */
    private final List<String> finishedMessages;

    public MessageStoreState(List<UserMessage> expectedMessages, List<UserMessage> finishedMessages) {
        this.expectedMessages = new ArrayList<>(expectedMessages.size());
        this.finishedMessages = new ArrayList<>(finishedMessages.size());
        for (UserMessage expectedMessage : expectedMessages) {
            this.expectedMessages.add(expectedMessage.presentation());
        }
        for (UserMessage finishedMessage : finishedMessages) {
            this.finishedMessages.add(finishedMessage.presentation());
        }
    }

    /**
     * Gets Value of {@link MessagesStorageService#getFinishedMessages} formatted by {@link UserMessage#presentation()}.
     *
     * @return Value of Value of {@link MessagesStorageService#getFinishedMessages} formatted by {@link UserMessage#presentation()}.
     */
    public List<String> getFinishedMessages() {
        return finishedMessages;
    }

    /**
     * Gets Value of {@link MessagesStorageService#getExpectedMessages} formatted by {@link UserMessage#presentation()}.
     *
     * @return Value of Value of {@link MessagesStorageService#getExpectedMessages} formatted by {@link UserMessage#presentation()}.
     */
    public List<String> getExpectedMessages() {
        return expectedMessages;
    }
}
