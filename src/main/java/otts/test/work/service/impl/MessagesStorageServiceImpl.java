package otts.test.work.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otts.test.work.messages.Alert;
import otts.test.work.messages.MessageChecker;
import otts.test.work.messages.UserMessage;
import otts.test.work.service.MessagesStorageService;
import otts.test.work.util.QueueNames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by alex on 06.09.2015.<br/>
 * Implementation of {@link MessagesStorageService}
 */
@Service
public class MessagesStorageServiceImpl implements MessagesStorageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Store for expected messages
     */
    private final List<UserMessage> expectedMessagesStore = new ArrayList<>();

    /**
     * Store for finished messages
     */
    private final List<UserMessage> finishedMessagesStore = new ArrayList<>();

    /**
     * Lock for expected messages
     */
    private final ReadWriteLock expectedStoreLock = new ReentrantReadWriteLock();

    /**
     * Lock for finished messages
     */
    private final ReadWriteLock finishedStoreLock = new ReentrantReadWriteLock();

    @Override
    public void storeMessage(UserMessage message) {
        expectedStoreLock.writeLock().lock();
        try {
            expectedMessagesStore.add(message);
        } finally {
            expectedStoreLock.writeLock().unlock();
        }
    }

    @Override
    public void checkExpectedMessages(MessageChecker messageChecker) {
        expectedStoreLock.writeLock().lock();
        try {
            List<UserMessage> messagesToDelete = new ArrayList<>();
            for (UserMessage message : expectedMessagesStore) {
                if(messageChecker.check(message)) {
                    messagesToDelete.add(message);
                    rabbitTemplate.convertAndSend(QueueNames.ALERT, new Alert(message.presentation() + " just happened"));
                }
            }
            expectedMessagesStore.removeAll(messagesToDelete);
            finishedStoreLock.writeLock().lock();
            try {
                finishedMessagesStore.addAll(messagesToDelete);
            } finally {
                finishedStoreLock.writeLock().unlock();
            }
        } finally {
            expectedStoreLock.writeLock().unlock();
        }
    }

    @Override
    public List<UserMessage> getExpectedMessages() {
        expectedStoreLock.readLock().lock();
        try {
            return new ArrayList<>(expectedMessagesStore);
        } finally {
            expectedStoreLock.readLock().unlock();
        }
    }

    @Override
    public List<UserMessage> getFinishedMessages() {
        finishedStoreLock.readLock().lock();
        try {
            return new ArrayList<>(finishedMessagesStore);
        } finally {
            finishedStoreLock.readLock().unlock();
        }
    }
}
