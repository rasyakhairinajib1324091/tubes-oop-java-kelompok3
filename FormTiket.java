package tiketbioskop;

import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FormTiket extends JFrame {

    JComboBox<String> cbTitle = new JComboBox<>(new String[]{
            "Agak Laen", "Avengers", "Zootopia 2", "Avatar: Fire and Ash", "Bila Esok Ibu Tiada"
    });
    JComboBox<String> cbStudio = new JComboBox<>(new String[]{
            "Studio 1", "Studio 2"
    });
    JComboBox<String> cbDay = new JComboBox<>(new String[]{
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    });
    JComboBox<String> cbTime = new JComboBox<>(new String[]{
            "09.30", "13.00", "16.30", "20.00"
    });
    JComboBox<TicketType> cbType = new JComboBox<>(new TicketType[]{
            new RegularTicket(), new VIPTicket()
    });
    JComboBox<String> cbPayment = new JComboBox<>(new String[]{
            "Cash", "Debit"
    });

    JLabel lblSeat = new JLabel("-");
    JLabel lblPrice = new JLabel("50000");
    JLabel lblTotal = new JLabel("0");

    SeatPanel seatPanel = new SeatPanel();

    DefaultTableModel model = new DefaultTableModel(
            new String[]{"Title", "Studio", "Day", "Time", "Type", "Payment Method", "Seat", "Total Price"}, 0
    );
    JTable table = new JTable(model);

    public FormTiket() {

        setTitle("MINI - LADERA CINEMA");
        setSize(1200, 740);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        CustomPanel panel = new CustomPanel();
        setContentPane(panel);
        panel.setLayout(null);
        
        panel.add(new JLabel("Movie Title")).setBounds(500, 30, 100, 25);
        cbTitle.setBounds(620, 30, 200, 25);
        panel.add(cbTitle);

        panel.add(new JLabel("Studio")).setBounds(500, 70, 100, 25);
        cbStudio.setBounds(620, 70, 200, 25);
        panel.add(cbStudio);

        panel.add(new JLabel("Showtime")).setBounds(500, 110, 100, 25);
        cbTime.setBounds(620, 110, 200, 25);
        panel.add(cbTime);

        panel.add(new JLabel("Day")).setBounds(500, 150, 100, 25);
        cbDay.setBounds(620, 150, 200, 25);
        panel.add(cbDay);

        panel.add(new JLabel("Ticket Type")).setBounds(500, 190, 100, 25);
        cbType.setBounds(620, 190, 200, 25);
        panel.add(cbType);

        panel.add(new JLabel("Payment Method")).setBounds(500, 230, 120, 25);
        cbPayment.setBounds(620, 230, 200, 25);
        panel.add(cbPayment);

        panel.add(new JLabel("Seat")).setBounds(500, 270, 100, 25);
        lblSeat.setBounds(620, 270, 450, 25);
        panel.add(lblSeat);

        panel.add(new JLabel("Price / Seat")).setBounds(500, 310, 100, 25);
        lblPrice.setBounds(620, 310, 200, 25);
        panel.add(lblPrice);

        panel.add(new JLabel("Total Price")).setBounds(500, 350, 100, 25);
        lblTotal.setBounds(620, 350, 200, 25);
        panel.add(lblTotal);

        seatPanel.setBounds(20, 60, 420, 230);
        panel.add(seatPanel);

        JButton btnCheckout = new JButton("CHECKOUT");
        btnCheckout.setBounds(620, 390, 200, 26);
        panel.add(btnCheckout);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 420, 1150, 240);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        table.setOpaque(false);
        table.setBackground(new Color(255, 255, 255, 77)); 
        panel.add(scroll);

        cbType.addActionListener(e -> updateTotal());

        seatPanel.setSeatChangeListener(this::updateTotal);

        btnCheckout.addActionListener(e -> {

            if (seatPanel.selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Silakan pilih seat terlebih dahulu!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (String s : seatPanel.selectedSeats) {
                if (SeatPanel.bookedSeat.contains(s)) {
                    JOptionPane.showMessageDialog(this,
                            "Seat " + s + " sudah dibooking!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            SeatPanel.bookedSeat.addAll(seatPanel.selectedSeats);

            String seats = String.join(", ", seatPanel.selectedSeats);
            lblSeat.setText(seats);

            String paymentMethod = cbPayment.getSelectedItem().toString();

            if (paymentMethod.equals("Cash")) {
                new formpembayaran(
                        this,
                        cbTitle.getSelectedItem().toString(),
                        cbStudio.getSelectedItem().toString(),
                        cbDay.getSelectedItem().toString(),
                        cbTime.getSelectedItem().toString(),
                        cbType.getSelectedItem().toString(),
                        paymentMethod,
                        seats,
                        Integer.parseInt(lblTotal.getText())
                ).setVisible(true);
            } else {
                finalizeOrder(seats, paymentMethod);
            }
        });
    }

    public void finalizeOrder(String seats, String paymentMethod) {
        model.addRow(new Object[]{
                cbTitle.getSelectedItem(),
                cbStudio.getSelectedItem(),
                cbDay.getSelectedItem(),
                cbTime.getSelectedItem(),
                cbType.getSelectedItem(),
                paymentMethod,
                seats,
                "Rp " + lblTotal.getText()
        });

        new StrukDialog(
                cbTitle.getSelectedItem().toString(),
                cbStudio.getSelectedItem().toString(),
                cbDay.getSelectedItem().toString(),
                cbTime.getSelectedItem().toString(),
                cbType.getSelectedItem().toString(),
                paymentMethod,
                seats,
                lblTotal.getText()
        ).setVisible(true);

        seatPanel.selectedSeats.clear();
        lblSeat.setText("-");
        lblTotal.setText("0");

        seatPanel.updateSeatColors();
    }

    private void updateTotal() {
        TicketType selectedType = (TicketType) cbType.getSelectedItem();
        int harga = selectedType.getPrice();
        lblPrice.setText(String.valueOf(harga));

        int total = harga * seatPanel.selectedSeats.size();
        lblTotal.setText(String.valueOf(total));

        String seatsText = seatPanel.selectedSeats.isEmpty() ? "-" : String.join(", ", seatPanel.selectedSeats);
        lblSeat.setText(seatsText);
    }

    private static class CustomPanel extends JPanel {
        private static BufferedImage backgroundImage;

        static {
            try {
                backgroundImage = ImageIO.read(CustomPanel.class.getResource("bgmain.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}