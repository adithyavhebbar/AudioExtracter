package service;

import DTO.ProgressTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import ws.schild.jave.EncoderProgressListener;
import ws.schild.jave.MultimediaInfo;

@Service
public class ProgressListener implements EncoderProgressListener {

    private double progress = 0.0;

    @Autowired
    private ScheduledPushMessages scheduledPushMessages;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    public ProgressListener() {

    }

    @Override
    public void sourceInfo(MultimediaInfo info) {

    }

    @Override
    public void progress(int permil) {
        //Find %100 progress
        this.progress = permil / 1000.00;
        System.out.println(progress);
//		this.scheduledPushMessages.sendProgress();
    }

    @Override
    public void message(String message) {
        // TODO Auto-generated method stub

    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }


}
