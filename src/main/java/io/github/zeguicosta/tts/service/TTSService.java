package io.github.zeguicosta.tts.service;

import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import io.github.zeguicosta.tts.domain.TTSRequest;
import io.github.zeguicosta.tts.repository.TTSRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class TTSService {
    private final TextToSpeech tts;
    private final TTSRequestRepository repository;

    public TTSService(TextToSpeech tts, TTSRequestRepository repository) {
        this.tts = tts;
        this.repository = repository;
    }

    public byte[] synthesizeMp3(String text) {
        long start = System.currentTimeMillis();
        String voice = SynthesizeOptions.Voice.PT_BR_ISABELAV3VOICE;
        String format = "audio/mp3";

        try (var in = tts.synthesize(new SynthesizeOptions.Builder()
                .text(text)
                .voice(voice)
                .accept(format)
                .build()
        ).execute().getResult()) {
            byte[] bytes = in.readAllBytes();
            repository.save(new TTSRequest(text, "OK", System.currentTimeMillis() - start, voice, format));
            return bytes;
        } catch (Exception e) {
            repository.save(new TTSRequest(text, "ERROR", System.currentTimeMillis() - start, voice, format));
            throw new RuntimeException("Falha ao sintetizar áudio: " + e.getMessage(), e);
        }
    }
}
