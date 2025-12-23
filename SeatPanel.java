package tiketbioskop;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class SeatPanel extends JPanel {

    // Kursi yang terjual
    public static Set<String> bookedSeat = new HashSet<>();

    // Kursi yang lagi dipilih 
    public Set<String> selectedSeats = new HashSet<>();

    private Runnable seatChangeListener;
    private final Color CREAM_BG  = new Color(245, 245, 220); 
    private final Color AVAILABLE = new Color (255, 193, 7);   
    private final Color DEEP_PINK = new Color(255, 64, 129);     

    public SeatPanel() {

        setLayout(new BorderLayout(5, 5));
        setOpaque(false); 

        // jlabel screen utama
        JLabel lblScreen = new JLabel("SCREEN", SwingConstants.CENTER);
        lblScreen.setOpaque(true);
        lblScreen.setBackground(Color.GRAY);
        lblScreen.setForeground(Color.WHITE);
        lblScreen.setFont(new Font("Arial", Font.BOLD, 14));
        lblScreen.setPreferredSize(new Dimension(400, 30));
        add(lblScreen, BorderLayout.NORTH);

        //grid seat 
        JPanel seatGrid = new JPanel(new GridLayout(5, 12, 5, 5));
        seatGrid.setOpaque(false);

        int seatNumber = 1;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 12; col++) {

                if (col == 5 || col == 6) {
                    JPanel aisle = new JPanel();
                    aisle.setOpaque(false);
                    seatGrid.add(aisle);
                    continue;
                }

                String code = "S" + seatNumber++;
                JButton btn = new JButton(code);

                btn.setPreferredSize(new Dimension(55, 35));
                btn.setFont(new Font("Arial", Font.BOLD, 11));
                btn.setFocusPainted(false);
                btn.setHorizontalAlignment(SwingConstants.CENTER);
                btn.setMargin(new Insets(0, 0, 0, 0));


                if (bookedSeat.contains(code)) {
                    btn.setBackground(Color.LIGHT_GRAY); 
                    btn.setEnabled(false);
                } else {
                    btn.setBackground(AVAILABLE); 
                }

                btn.addActionListener(e -> {

                    if (bookedSeat.contains(code)) {
                        return;
                    }

                    // Toggle pilih
                    if (selectedSeats.contains(code)) {
                        selectedSeats.remove(code);
                        btn.setBackground(AVAILABLE); 
                    } else {
                        selectedSeats.add(code);
                        btn.setBackground(DEEP_PINK); 
                    }

                    if (seatChangeListener != null) {
                        seatChangeListener.run();
                    }
                });

                seatGrid.add(btn);
            }
        }

        add(seatGrid, BorderLayout.CENTER);
    }


    public void setSeatChangeListener(Runnable r) {
        this.seatChangeListener = r;
    }

    // updet warna seat di tiap aksi
    public void updateSeatColors() {
        Component[] comps = ((JPanel) getComponent(1)).getComponents();

        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String code = btn.getText();

                if (bookedSeat.contains(code)) {
                    btn.setBackground(Color.LIGHT_GRAY);
                    btn.setEnabled(false);
                } else if (selectedSeats.contains(code)) {
                    btn.setBackground(DEEP_PINK);
                    btn.setEnabled(true);
                } else {
                    btn.setBackground(AVAILABLE);
                    btn.setEnabled(true);
                }
            }
        }
    }
}