package io.github.zeguicosta.tts.dto;

import jakarta.validation.constraints.NotBlank;

public record SynthesizeRequest(
        @NotBlank(message = "O campo 'text' é obrigatório.")
        String text
) {}
