package br.com.salomaotech.model.servicos;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLoader {

    public static byte[] lerBytes(String caminhoDaImagem) {

        Path path = Paths.get(caminhoDaImagem);

        try {

            return Files.readAllBytes(path);

        } catch (Exception ex) {

            return null;

        }

    }

    public static void construirImagem(byte[] bytes, JLabel imagem) {

        try {

            if (bytes != null) {

                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));

                if (img != null) {

                    int labelWidth = imagem.getWidth();
                    int labelHeight = imagem.getHeight();

                    if (labelWidth > 0 && labelHeight > 0) {

                        Image scaledImage = redimensionarImagem(img, labelWidth, labelHeight);
                        ImageIcon icon = new ImageIcon(scaledImage);
                        icon.setImageObserver(imagem);
                        imagem.setIcon(icon);
                        imagem.setHorizontalAlignment(JLabel.CENTER);
                        imagem.setVerticalAlignment(JLabel.CENTER);
                        return;

                    }

                }

            }

        } catch (IOException ex) {

        }

        imagem.setIcon(null);

    }

    private static Image redimensionarImagem(BufferedImage originalImage, int width, int height) {

        double scale = Math.min((double) width / originalImage.getWidth(), (double) height / originalImage.getHeight());
        int scaledWidth = (int) (originalImage.getWidth() * scale);
        int scaledHeight = (int) (originalImage.getHeight() * scale);
        return originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

    }

}
