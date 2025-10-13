package io.github.zeguicosta.tts.controller;

import io.github.zeguicosta.tts.model.TTSRequest;
import io.github.zeguicosta.tts.dto.TTSRequestDTO;
import io.github.zeguicosta.tts.service.TTSService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tts")
@RequiredArgsConstructor
public class TTSController {
    private final TTSService ttsService;

    @PostMapping(value = "/synthesize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "audio/mpeg")
    public ResponseEntity<byte[]> synthesize(@Valid @RequestBody TTSRequestDTO request) {
        byte[] audio = ttsService.synthesizeMp3(request.text(), request.voice());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"output.mp3\"")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audio);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<TTSRequest>> getHistory(Pageable pageable) {
        return ResponseEntity.ok(ttsService.getHistory(pageable));
    }
}
