package io.github.zeguicosta.tts.repository;

import io.github.zeguicosta.tts.domain.TTSRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TTSRequestRepository extends JpaRepository<TTSRequest, UUID> {}
