package io.github.zeguicosta.tts.dto;

import jakarta.validation.constraints.NotBlank;

public record TTSRequestDTO(
        @NotBlank(message = "O campo 'text' é obrigatório.")
        String text,
        String voice
) {}
