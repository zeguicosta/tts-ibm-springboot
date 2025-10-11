package io.github.zeguicosta.tts.service;

import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import org.springframework.stereotype.Service;

@Service
public class TTSService {
    private final TextToSpeech tts;

    public TTSService(TextToSpeech tts) {
        this.tts = tts;
    }

    public byte[] synthesizeMp3(String text) {
        try (var in = tts.synthesize(new SynthesizeOptions.Builder()
                .text(text)
                .voice(SynthesizeOptions.Voice.PT_BR_LUCASEXPRESSIVE)
                .accept("audio/mp3")
                .build()
        ).execute().getResult()) {
            return in.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao sintetizar áudio: " + e.getMessage(), e);
        }
    }
}
