package com.controlu.facial;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/api/facial")
public class ReconhecimentoFacialController {
    private CascadeClassifier faceDetector;

    public ReconhecimentoFacialController() {
        this.faceDetector = new CascadeClassifier("src/main/resources/haarcascade_frontalface_alt.xml");
    }

    @PostMapping("/reconhecer")
    public ResponseEntity<String> reconhecerFace(@RequestBody ReconhecimentoFacialRequest reconhecimentoFacialRequest) {
        System.out.println("\nPOST /reconhecer");
        try {
            // Validar as imagens no payload
            if (reconhecimentoFacialRequest.getFaceCapturada() == null || reconhecimentoFacialRequest.getFaceArmazenada() == null) {
                return ResponseEntity.badRequest().body("Imagens não fornecidas");
            }

            // Converta as imagens de Base64 para byte[]
            byte[] faceCapturadaBytes = Base64.getDecoder().decode(reconhecimentoFacialRequest.getFaceCapturada());
            byte[] faceArmazenadaBytes = Base64.getDecoder().decode(reconhecimentoFacialRequest.getFaceArmazenada());

            System.out.println("reconhecer,  faceCapturadaBytes:  " + Arrays.toString(faceCapturadaBytes));
            System.out.println("reconhecer,  faceArmazenadaBytes:  " + Arrays.toString(faceArmazenadaBytes));


            // Converta os bytes em Mat (formato do OpenCV)
            Mat faceCapturadaImage = Imgcodecs.imdecode(new MatOfByte(faceCapturadaBytes), Imgcodecs.IMREAD_COLOR);
            Mat faceArmazenadaImage = Imgcodecs.imdecode(new MatOfByte(faceArmazenadaBytes), Imgcodecs.IMREAD_COLOR);

            // Verificar se as imagens foram decodificadas corretamente
            if (faceCapturadaImage.empty() || faceArmazenadaImage.empty()) {
                return ResponseEntity.badRequest().body("Erro ao decodificar as imagens");
            }

            // Processamento de reconhecimento facial
            boolean isRecognized = processarReconhecimentoFacial(faceCapturadaImage, faceArmazenadaImage);

            // Retornar o resultado do reconhecimento
            if (isRecognized) {
                System.out.println("reconhecerFace, resultado: ROSTO RECONHECIDOO");
                return ResponseEntity.ok("OK");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ERRO");
            }
        } catch (IllegalArgumentException e) {
            // Captura problemas com a decodificação Base64 ou outros erros de argumento inválido
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro de argumento inválido: " + e.getMessage());
        } catch (Exception e) {
            // Exceções genéricas
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem: " + e.getMessage());
        }
    }


    public boolean processarReconhecimentoFacial(Mat faceCapturada, Mat faceArmazenada) throws IOException {
        System.out.println("\nPROCESSAR RECONHECIMENTO FACIAL");

        // Detectar faces na imagem capturada
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(faceCapturada, faceDetections, 1.1, 10, 0, new Size(30, 30), new Size());

        if (faceDetections.toArray().length == 0) {
            // Nenhuma face foi detectada
            System.out.println("Nenhuma face detectada.");
            return false;
        }

        Rect rect = faceDetections.toArray()[0];
        Mat detectedFace = new Mat(faceCapturada, rect);

        // Pré-processar as imagens
        Mat processedDetectedFace = processarImagem(detectedFace);
        Mat processedStoredFace = processarImagem(faceArmazenada);

        // Comparar as faces
        double similarity = compararFaces(processedDetectedFace, processedStoredFace);

        System.out.println("Pontuação de similaridade: " + similarity);

        // Ajuste do limiar de similaridade baseado em testes empíricos
        double limiarSimilaridade = 0.7;
        return similarity >= limiarSimilaridade;
    }

    private Mat processarImagem(Mat face) {
        Mat resizedFace = new Mat();
        Size size = new Size(100, 100);
        Imgproc.resize(face, resizedFace, size);

        Mat grayFace = new Mat();
        Imgproc.cvtColor(resizedFace, grayFace, Imgproc.COLOR_BGR2GRAY);

        Imgproc.equalizeHist(grayFace, grayFace); // Equalização do histograma
        Core.normalize(grayFace, grayFace, 0, 255, Core.NORM_MINMAX);

        return grayFace;
    }

    private double compararFaces(Mat face1, Mat face2) {
        System.out.println("\nCOMPARAR FACES");

        Mat diff = new Mat();
        Core.absdiff(face1, face2, diff);

        Scalar sum = Core.sumElems(diff);
        double score = sum.val[0];

        // Similaridade: quanto menor o score, mais similares as faces
        double similarity = 1.0 - (score / (100 * 100 * 255));
        System.out.println("Score de diferença: " + score);
        System.out.println("Similaridade calculada: " + similarity);

        return similarity;
    }

}
