package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderProgressListener;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;

@Service
public class VideoEncoder {
	
	@Autowired
	private ProgressListener listener;

	public void encodeVideo(Path p, AudioAttributes audioAttributes, VideoAttributes videoAttributes, File source) {
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audioAttributes);
//		attrs.setVideoAttributes(videoAttributes);
		File sources = new File(p.toFile().toString());
		File target = new File(Paths.get(Paths.get("").toAbsolutePath().toString() + "/destin.mp3").toString());
		System.out.println("target:" + target.getName());
		System.out.println("sorce:" + sources.getName());
		try {
			Encoder encoder = new Encoder();
			System.out.println("audio Enco"+Arrays.toString(encoder.getAudioEncoders())+"\n");
			System.out.println("supported enco"+Arrays.toString(encoder.getSupportedEncodingFormats())+"\n");
			System.out.println("supported denco video"+Arrays.toString(encoder.getVideoDecoders())+"\n");
			encoder.encode(new MultimediaObject(new File(sources.getName())), new File(target.getName()), attrs);
			listener.sourceInfo(new MultimediaInfo());
			double progress = listener.getProgress();
			encoder.encode(new MultimediaObject(new File(sources.getName())), new File(target.getName()), attrs, listener);
		} catch (Exception e) {
			System.out.println("***************");
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
	}
}
