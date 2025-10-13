package io.github.zeguicosta.tts.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tts_request")
public class TTSRequest {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.UUID) // ajuda o hibernate a mapear UUID no postgres
    private UUID id;

    @Column(nullable = false, length = 5000)
    private String text;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false, length = 16)
    private String status;

    @Column
    private Long durationMs;

    @Column(length = 64)
    private String voice;

    @Column(length = 32)
    private String format;

    public TTSRequest() {}

    public TTSRequest(String text, String status, Long durationMs, String voice, String format) {
        this.text = text;
        this.status = status;
        this.durationMs = durationMs;
        this.voice = voice;
        this.format = format;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
