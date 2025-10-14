package io.github.zeguicosta.tts.exception;

public class SynthesisException extends RuntimeException {
    public SynthesisException(String message, Throwable cause) { super(message, cause); }
    public SynthesisException(String message) { super(message); }
}
