package tiketbioskop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StrukDialog extends JDialog {

    public StrukDialog(String title, String studio, String day,
                       String time, String type, String paymentMethod, String seat,
                       String totalPrice) {

        int hargaPerSeat = type.equals("VIP") ? 100000 : 50000;
        int jumlahSeat = seat.split(",").length;

        setTitle("STRUK PEMBAYARAN");
        setSize(480, 260);
        setLocationRelativeTo(null);
        setModal(true);

        CustomPanel panel = new CustomPanel();
        panel.setLayout(null);
        setResizable(false);
        setContentPane(panel);

        JLabel lblTitle = new JLabel("", SwingConstants.CENTER);
        lblTitle.setBounds(30, 20, 280, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.BLACK);
        panel.add(lblTitle);
        
        int y = 70;
        panel.add(makeLabel("Studio : " + studio , y)); y += 28;
        panel.add(makeLabel("Movie : " + title, y)); y += 28;
        panel.add(makeLabel("Day : " + day, y)); y += 28;
        panel.add(makeLabel("Time : " + time, y)); y += 28;
        panel.add(makeLabel("Price : Rp " + totalPrice, y)); y += 40;
    }

    private JLabel makeLabel(String text, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(140, y, 300, 25);
        lbl.setForeground(Color.BLACK);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        return lbl;
    }

    // custom beckground
    private static class CustomPanel extends JPanel {
        private static BufferedImage backgroundImage;

        static {
            try {
                backgroundImage = ImageIO.read(CustomPanel.class.getResource("struk.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 0, 139), 0, height, Color.BLACK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        }
    }
}