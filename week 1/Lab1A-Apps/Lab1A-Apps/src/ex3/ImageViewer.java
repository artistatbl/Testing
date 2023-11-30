package ex3;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageViewer extends JFrame {
    private JLabel imageLabel;
    private JButton openButton;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JFileChooser fileChooser;
    private File selectedFile;
    private BufferedImage originalImage;
    private double scaleFactor = 1.0;

    public ImageViewer() {
        // Initialise the GUI components
        imageLabel = new JLabel();
        openButton = new JButton("Open Image");
        zoomInButton = new JButton("Zoom In (-)");
        zoomOutButton = new JButton("Zoom Out (+)");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));

        // Create a JPanel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);


        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(buttonPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);

        // Add the content panel to the frame
        getContentPane().add(contentPanel);

        // Add action listeners to the buttons
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openImage();
            }
        });

        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomIn();
            }
        });

        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomOut();
            }
        });

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openImage() {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);
                // Display the original image with the current scale factor
                displayImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayImage() {
        if (originalImage != null) {
            int newWidth = (int) (originalImage.getWidth() * scaleFactor);
            int newHeight = (int) (originalImage.getHeight() * scaleFactor);
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(imageIcon);
        }
    }

    private void zoomIn() {
        scaleFactor += 0.5;
        displayImage();
    }

    private void zoomOut() {
        scaleFactor -= 0.5;
        displayImage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImageViewer();
            }
        });
    }
}
