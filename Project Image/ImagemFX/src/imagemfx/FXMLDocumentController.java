/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemfx;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author irineualmeidajunior
 */
public class FXMLDocumentController implements Initializable {
   
    @FXML
    private ImageView imageView;
    private Image image;
    private BufferedImage bimage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtAbrir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("/Users/irineualmeidajunior"));
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null) {
            image = new Image(file.toURI().toString());
            imageView.setFitHeight(image.getHeight());
            imageView.setFitWidth(image.getWidth());
            imageView.setImage(image);
        }
      
    }   

    @FXML
    private void evtSalvar(ActionEvent event) {
    }

    @FXML
    private void evtSalvarComo(ActionEvent event) {
    }

    @FXML
    private void evtFechar(ActionEvent event) {
    }

    @FXML
    private void evtSair(ActionEvent event) {
    }

    @FXML
    private void evtTonsCinza(ActionEvent event) {
        int media;
        if(image != null) {
            bimage = SwingFXUtils.fromFXImage(image, bimage);
            int pixel[] = {0, 0 ,0 ,0};
            WritableRaster raster = bimage.getRaster();
            
            for(int y=0; y < image.getHeight(); y++) {
                for(int x=0; x < image.getWidth(); x++) {
                    raster.getPixel(x, y, pixel);
                    //  As posições do vetor é RGB, R[0], G[1], B[2], ai multiplico, só que ele sai
                    //em valor floar, mas como eu uso inteiro, faço um casting para inteiro.
                    media = (int)(pixel[0] * 0.3 + pixel[1] *0.59 + pixel[2] * 0.11) / 3;
                    pixel[0] = pixel[1] = pixel[2] = media;
                    raster.setPixel(x, y, pixel);
                }
            }
            
            image = SwingFXUtils.toFXImage(bimage, null);
            imageView.setImage(image);
            
        }         
        
    }

    @FXML
    private void evtSobre(ActionEvent event) {
    }

    @FXML
    private void evtPretoBranco(ActionEvent event) {
        //  Fazer aqui como no converter para Tons de Cinza só que em Preto e Branco, para
        //isso fazer uma média, ao exemplo, maior que 150 coloco Preto, menor que esse valor
        //eu coloco Branco.
        //  Então eu posso utilizar essa média para definir se será preto ou branco pixel
    }
    
}
