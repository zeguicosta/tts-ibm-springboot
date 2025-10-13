package io.github.zeguicosta.tts.service;

import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import io.github.zeguicosta.tts.model.TTSRequest;
import io.github.zeguicosta.tts.repository.TTSRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TTSService {
    private final TextToSpeech tts;
    private final TTSRequestRepository repository;

    public byte[] synthesizeMp3(String text, String newVoice) {
        long start = System.currentTimeMillis();
        String voice = (newVoice == null || newVoice.isBlank()) ? SynthesizeOptions.Voice.PT_BR_ISABELAV3VOICE : newVoice;
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

    public Page<TTSRequest> getHistory(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
