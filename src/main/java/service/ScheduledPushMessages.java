package service;

import DTO.ProgressTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Service
@EnableWebSocketMessageBroker
@EnableScheduling
public class ScheduledPushMessages {
    @Autowired
    private ProgressListener progressListener;
    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;
    private double progress;

    @Scheduled(fixedRate = 1000)
    public void sendProgress() {
        simpleMessagingTemplate.convertAndSend("/progress/percent", new ProgressTransfer("progress",this.progress));
    }

    @Scheduled(fixedRate = 1000)
    public void setProgress() {
        this.progress = progressListener.getProgress();
    }
}
