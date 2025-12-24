package tiketbioskop;

import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setSize(300, 220);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        javax.swing.JLabel lblUser = new javax.swing.JLabel("Username");
        javax.swing.JLabel lblPass = new javax.swing.JLabel("Password");
        javax.swing.JTextField txtUser = new javax.swing.JTextField();
        javax.swing.JPasswordField txtPass = new javax.swing.JPasswordField();
        javax.swing.JButton btnLogin = new javax.swing.JButton("Login");
        javax.swing.JButton btnReg = new javax.swing.JButton("Registrasi");

        lblUser.setBounds(30, 30, 80, 25);
        txtUser.setBounds(120, 30, 120, 25);
        lblPass.setBounds(30, 70, 80, 25);
        txtPass.setBounds(120, 70, 120, 25);
        btnLogin.setBounds(40, 120, 90, 30);
        btnReg.setBounds(150, 120, 100, 30);

        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(btnLogin);
        add(btnReg);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Username dan Password wajib diisi!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Registrasi.username == null || Registrasi.password == null) {
                JOptionPane.showMessageDialog(this,
                        "Silakan registrasi terlebih dahulu!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (user.equals(Registrasi.username)
                    && pass.equals(Registrasi.password)) {
                JOptionPane.showMessageDialog(this, "Login berhasil");
                new FormTiket().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Username atau Password salah!",
                        "Login gagal",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnReg.addActionListener(e -> {
            new Registrasi().setVisible(true);
            dispose();
        });
    }
}