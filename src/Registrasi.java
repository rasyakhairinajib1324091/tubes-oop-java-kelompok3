package tiketbioskop;

import javax.swing.JOptionPane;

public class Registrasi extends javax.swing.JFrame {

    public static String username;
    public static String password;

    public Registrasi() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Registrasi");
        setSize(300, 220);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        javax.swing.JLabel lblUser = new javax.swing.JLabel("Username");
        javax.swing.JLabel lblPass = new javax.swing.JLabel("Password");
        javax.swing.JTextField txtUser = new javax.swing.JTextField();
        javax.swing.JPasswordField txtPass = new javax.swing.JPasswordField();
        javax.swing.JButton btnDaftar = new javax.swing.JButton("Daftar");

        lblUser.setBounds(30, 30, 80, 25);
        txtUser.setBounds(120, 30, 120, 25);
        lblPass.setBounds(30, 70, 80, 25);
        txtPass.setBounds(120, 70, 120, 25);
        btnDaftar.setBounds(90, 120, 100, 30);

        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(btnDaftar);

        btnDaftar.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Username dan Password wajib diisi!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            username = user;
            password = pass;

            JOptionPane.showMessageDialog(this, "Registrasi berhasil");
            new Login().setVisible(true);
            dispose();
        });
    }
}
