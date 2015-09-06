package otts.test.work.service.impl;

import org.springframework.stereotype.Service;
import otts.test.work.service.MessagesStorageService;

import java.util.List;

/**
 * Created by alex on 06.09.2015.<br/>
 * Implementation of {@link MessagesStorageService}
 */
@Service
public class MessagesStorageServiceImpl implements MessagesStorageService {
    @Override
    public void storeMessage(Object message) {
        //TODO implement
        throw new RuntimeException("Method is not implemented!");
    }

    @Override
    public List<Object> getExpectedMessages() {
        //TODO implement
        throw new RuntimeException("Method is not implemented!");
    }

    @Override
    public List<Object> getFinishedMessages() {
        //TODO implement
        throw new RuntimeException("Method is not implemented!");
    }
}
