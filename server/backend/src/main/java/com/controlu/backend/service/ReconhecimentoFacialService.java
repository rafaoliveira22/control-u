package com.controlu.backend.service;

import com.controlu.backend.vo.ReconhecimentoFacialRequestVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class ReconhecimentoFacialService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> enviarImagensParaReconhecimento(byte[] faceCapturada, byte[] faceArmazenada) {
        System.out.println("\nENVIAR IMAGENS PARA RECONHECIMENTO - ReconhecimentoService");

        // Converter ambas as imagens para Base64
        String faceCapturadaBase64 = Base64.getEncoder().encodeToString(faceCapturada);
        String faceArmazenadaBase64 = Base64.getEncoder().encodeToString(faceArmazenada);

        // Construir o payload para o request
        ReconhecimentoFacialRequestVO reconhecimentoFacialRequestVO = new ReconhecimentoFacialRequestVO();
        reconhecimentoFacialRequestVO.setFaceCapturada(faceCapturadaBase64);
        reconhecimentoFacialRequestVO.setFaceArmazenada(faceArmazenadaBase64);

        // Configurar os headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Criar o objeto HttpEntity com o payload e os headers
        HttpEntity<ReconhecimentoFacialRequestVO> requestEntity = new HttpEntity<>(reconhecimentoFacialRequestVO, headers);

        // Fazer a requisição para a API de reconhecimento facial
        String url = "http://localhost:8082/api/facial/reconhecer";
        System.out.println("enviarImagensParaReconhecimento, urlRequest: " + url);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Verificar a resposta
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Reconhecimento facial bem-sucedido");
        } else {
            System.out.println("Falha no reconhecimento facial: " + response.getBody());
        }

        return response;
    }
}
