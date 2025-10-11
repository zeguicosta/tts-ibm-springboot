package io.github.zeguicosta.tts.config;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IbmConfig {
    @Bean
    public TextToSpeech textToSpeech(
            @Value("${IBM_API_KEY}") String apiKey,
            @Value("${IBM_TTS_URL}") String url
    ) {
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        TextToSpeech tts = new TextToSpeech(authenticator);
        tts.setServiceUrl(url);
        return tts;
    }
}
