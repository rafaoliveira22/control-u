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
        System.out.println("reconhecer,  faceCapturada:  " + reconhecimentoFacialRequest.getFaceCapturada().substring(0, 100));
        System.out.println("reconhecer,  faceArmazenada:  " + reconhecimentoFacialRequest.getFaceArmazenada().substring(0, 100));
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
                return ResponseEntity.ok("Reconhecimento facial bem-sucedido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Rosto não reconhecido");
            }
        } catch (IllegalArgumentException e) {
            // Captura problemas com a decodificação Base64 ou outros erros de argumento inválido
            return ResponseEntity.badRequest().body("Erro de argumento inválido: " + e.getMessage());
        } catch (Exception e) {
            // Exceções genéricas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem: " + e.getMessage());
        }
    }


    public boolean processarReconhecimentoFacial(Mat faceCapturada, Mat faceArmazenada) throws IOException {
        System.out.println("\nPROCESSAR RECONHECIMENTO FACIAL");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(faceCapturada, faceDetections);

        if (faceDetections.toArray().length == 0) {
            // Nenhuma face foi detectada
            return false;
        }

        // Pegar a primeira face detectada
        Rect rect = faceDetections.toArray()[0];
        Mat detectedFace = new Mat(faceCapturada, rect);

        // Processar a imagem da face detectada
        Mat processedDetectedFace = processarImagem(detectedFace);


        Mat storedFaceMat = Imgcodecs.imdecode(new MatOfByte(faceArmazenada), Imgcodecs.IMREAD_COLOR);

        // Processar a imagem do banco de dados
        Mat processedStoredFace = processarImagem(storedFaceMat);

        // Comparar as duas imagens (usando um método como a distância euclidiana)
        double similarity = compararFaces(processedDetectedFace, processedStoredFace);

        if (similarity > 0.7) {  // Defina um limite de similaridade adequado
            return true;  // Face reconhecida
        }


        return false;
    }

    /**
     * normalizar e preparar a imagem para a comparação
     * @param face
     * @return
     */
    private Mat processarImagem(Mat face) {
        // Redimensiona a face para um tamanho fixo (por exemplo, 100x100 pixels)
        Mat resizedFace = new Mat();
        Size size = new Size(100, 100);
        Imgproc.resize(face, resizedFace, size);

        // Converte para cinza (se necessário, dependendo do algoritmo)
        Mat grayFace = new Mat();
        Imgproc.cvtColor(resizedFace, grayFace, Imgproc.COLOR_BGR2GRAY);

        // Normalizar a face ou extrair características (aqui usando a normalização simples)
        Core.normalize(grayFace, grayFace, 0, 255, Core.NORM_MINMAX);

        return grayFace;
    }

    /**
     * comparar as faces usando um algoritmo como a distância euclidiana entre os vetores das duas imagens processadas
     * @param face1
     * @param face2
     * @return
     */
    private double compararFaces(Mat face1, Mat face2) {
        System.out.println("\nCOMPARAR FACES");
        System.out.println("compararFaces face1: " + face1);
        System.out.println("compararFaces face2: " + face2);

        // Calcular a diferença entre os dois vetores de imagem
        Mat diff = new Mat();
        Core.absdiff(face1, face2, diff);

        // Somar todos os valores absolutos da matriz de diferença
        Scalar sum = Core.sumElems(diff);
        System.out.println("compararFaces sum: " + sum);

        // Calcular a pontuação de similaridade (quanto menor, mais similares)
        double score = sum.val[0] + sum.val[1] + sum.val[2];
        System.out.println("compararFaces score: " + score);

        // Converter a pontuação em uma porcentagem de similaridade
        double similarity = 1.0 - (score / (100 * 100 * 255));
        System.out.println("compararFaces similarity: " + similarity);


        return similarity;
    }
}
