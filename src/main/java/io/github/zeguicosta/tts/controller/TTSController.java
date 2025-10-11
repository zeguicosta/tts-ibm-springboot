package io.github.zeguicosta.tts.controller;

import io.github.zeguicosta.tts.domain.TTSRequest;
import io.github.zeguicosta.tts.dto.SynthesizeRequest;
import io.github.zeguicosta.tts.service.TTSService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tts")
@RequiredArgsConstructor
public class TTSController {
    private final TTSService ttsService;

    @PostMapping(value = "/synthesize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> synthesize(@Valid @RequestBody SynthesizeRequest request) {
        byte[] audio = ttsService.synthesizeMp3(request.text());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"output.mp3\"")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audio);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TTSRequest>> getHistory() {
        List<TTSRequest> history = ttsService.getHistory();
        return ResponseEntity.ok(history);
    }
}
