package br.com.salomaotech.model.servicos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ImageLoader {

    public static byte[] lerBytes() {

        Path path = FileSystems.getDefault().getPath("amelia.jpg");

        try {

            return Files.readAllBytes(path);

        } catch (Exception ex) {

            return null;

        }

    }

    public static void construirImagem(byte[] bytes) {

        if (bytes != null) {

            try {

                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));

            } catch (Exception ex) {

            }

        }

    }

}
