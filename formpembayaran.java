package tiketbioskop;

import javax.swing.*;

public class formpembayaran extends JDialog {

    private JTextField txtAmount;
    private int totalPrice;
    private String title, studio, day, time, type, paymentMethod, seats;
    private FormTiket formTiket;

    public formpembayaran(FormTiket formTiket, String title, String studio, String day, String time, String type, String paymentMethod, String seats, int totalPrice) {
        this.formTiket = formTiket;
        this.title = title;
        this.studio = studio;
        this.day = day;
        this.time = time;
        this.type = type;
        this.paymentMethod = paymentMethod;
        this.seats = seats;
        this.totalPrice = totalPrice;

        setTitle("PEMBAYARAN CASH");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(null);

        add(new JLabel("Total Harga: Rp " + totalPrice)).setBounds(50, 30, 200, 25);
        add(new JLabel("Masukkan Uang:")).setBounds(50, 70, 150, 25);

        txtAmount = new JTextField();
        txtAmount.setBounds(50, 100, 200, 25);
        add(txtAmount);

        JButton btnBayar = new JButton("BAYAR");
        btnBayar.setBounds(50, 150, 100, 30);
        add(btnBayar);

        JButton btnBatal = new JButton("BATAL");
        btnBatal.setBounds(170, 150, 100, 30);
        add(btnBatal);

        btnBayar.addActionListener(e -> {
            try {
                int amount = Integer.parseInt(txtAmount.getText());
                if (amount >= totalPrice) {
                    int change = amount - totalPrice;
                    String message = "Pembayaran berhasil!";
                    if (change > 0) {
                        message += "\nKembalian: Rp " + change;
                    }
                    JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    formTiket.finalizeOrder(seats, paymentMethod);
                } else {
                    JOptionPane.showMessageDialog(this, "Uang tidak cukup! Transaksi gagal.", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnBatal.addActionListener(e -> dispose());
    }
}
