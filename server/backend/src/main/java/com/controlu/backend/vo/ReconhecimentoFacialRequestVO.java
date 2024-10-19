package com.controlu.backend.vo;

public class ReconhecimentoFacialRequestVO {
    private String faceCapturada;
    private String faceArmazenada;

    public ReconhecimentoFacialRequestVO(){}

    public ReconhecimentoFacialRequestVO(String faceCapturada, String faceArmazenada) {
        this.faceCapturada = faceCapturada;
        this.faceArmazenada = faceArmazenada;
    }

    public String getFaceCapturada() {
        return faceCapturada;
    }

    public void setFaceCapturada(String faceCapturada) {
        this.faceCapturada = faceCapturada;
    }

    public String getFaceArmazenada() {
        return faceArmazenada;
    }

    public void setFaceArmazenada(String faceArmazenada) {
        this.faceArmazenada = faceArmazenada;
    }
}
