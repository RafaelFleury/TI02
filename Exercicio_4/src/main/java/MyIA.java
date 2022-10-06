import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVision;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageAnalysis;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageTag;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.VisualFeatureTypes;

import java.util.List;

public class MyIA {
    private static String key = "d28f90f7d0104dd38b88d3ca18903fb1";
    private static String endpoint = "https://visao-computacional-exercicio4.cognitiveservices.azure.com/";

    public static void main(String[] args) {
        ComputerVisionClient cvc = ComputerVisionManager.authenticate(key).withEndpoint(endpoint);
        for(int i = 1; i <= 5; i++) {
            System.out.println("\n\nImagem analizada" + i + ":\n");
            imagemAnalizada( "https://source.unsplash.com/random/400*400", cvc.computerVision());
        }
    }
    public static void imagemAnalizada(String imagem, ComputerVision cliente) {
        try {
            ImageAnalysis analysis = cliente.analyzeImage().withUrl(imagem)
                    .withVisualFeatures(List.of(VisualFeatureTypes.TAGS)).execute();

            for (ImageTag tag : analysis.tags()) 
                System.out.println("-> " + "[" + String.format("%.2f",tag.confidence()*100) + "%] " + tag.name());


            
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}