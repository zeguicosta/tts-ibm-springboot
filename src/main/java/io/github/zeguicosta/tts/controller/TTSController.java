package io.github.zeguicosta.tts.controller;

import io.github.zeguicosta.tts.dto.SynthesizeRequest;
import io.github.zeguicosta.tts.service.TTSService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tts")
public class TTSController {
    private final TTSService ttsService;

    public TTSController(TTSService ttsService) {
        this.ttsService = ttsService;
    }

    @PostMapping(value = "/synthesize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> synthesize(@Valid @RequestBody SynthesizeRequest request) {
        byte[] audio = ttsService.synthesizeMp3(request.text());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"output.mp3\"")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audio);
    }
}
