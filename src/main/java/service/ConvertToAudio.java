package service;

import java.beans.Encoder;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.VideoAttributes;
import ws.schild.jave.VideoSize;
import ws.schild.jave.VideoAttributes.X264_PROFILE;

@Service
public class ConvertToAudio {
	@Autowired
	private VideoEncoder videoEncoder;
	
	public void getAudioFromVideo(Path p) {
		try {
			AudioAttributes audio = new AudioAttributes();
			 audio.setCodec("libmp3lame");                               
			 audio.setBitRate(128000);                                   
			 audio.setChannels(2);                                       
			 audio.setSamplingRate(44100);
			
			VideoAttributes video = new VideoAttributes();
			System.out.println();
			video.setCodec("h264");
			video.setX264Profile(X264_PROFILE.BASELINE);
			// Here 160 kbps video is 160000
			video.setBitRate(160000);
			// More the frames more quality and size, but keep it low based on devices like mobile
			video.setFrameRate(15);
			video.setSize(new VideoSize(400, 300));
			
			videoEncoder.encodeVideo(p, audio, video, p.toAbsolutePath().toFile());
		} catch( Exception e) {
			System.out.println("***************");
			System.out.println(e.toString());
		}
		
	}
}
