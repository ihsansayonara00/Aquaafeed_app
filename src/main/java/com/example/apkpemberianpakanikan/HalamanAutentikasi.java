package com.example.apkpemberianpakanikan;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HalamanAutentikasi extends Application {

    private Stage panggungUtama;

    @Override
    public void start(Stage stage) {
        this.panggungUtama = stage;

        // Langsung jalankan form login sebagai tampilan utama
        tampilkanFormLogin();
    }

    /**
     * 🔐 RENDERING: Tampilan Form Login
     */
    private void tampilkanFormLogin() {
        VBox layoutLogin = new VBox(12);
        layoutLogin.setAlignment(Pos.CENTER);
        layoutLogin.setPadding(new Insets(30));
        layoutLogin.setStyle("-fx-background-color: #f5f6fa;");

        Label lblJudul = new Label("🔑 LOGIN AQUAFEED");
        lblJudul.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-padding: 0 0 10 0;");

        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Masukkan Username...");
        txtUsername.setPrefWidth(280);
        txtUsername.setMaxWidth(280);

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Masukkan Password...");
        txtPassword.setMaxWidth(280);

        Button btnLogin = new Button("Masuk Aplikasi");
        btnLogin.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnLogin.setPrefWidth(280);

        Hyperlink linkDaftar = new Hyperlink("Belum punya akun? Daftar di sini");
        linkDaftar.setStyle("-fx-font-size: 11px;");

        Label lblPesanError = new Label("");
        lblPesanError.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");

        layoutLogin.getChildren().addAll(lblJudul, txtUsername, txtPassword, btnLogin, linkDaftar, lblPesanError);

        // 🚀 LOGIKA AKSI TOMBOL LOGIN (VALIDASI KE LARAGON)
        btnLogin.setOnAction(e -> {
            String user = txtUsername.getText().trim();
            String pass = txtPassword.getText();

            if (user.isEmpty() || pass.isEmpty()) {
                lblPesanError.setText("⚠️ Username dan password wajib diisi!");
            } else if (validasiLoginKeDatabase(user, pass)) {
                // BUKA DASHBOARD UTAMA JIKA VALIDASI LARAGON SUKSES
                try {
                    // 🌟 PERBAIKAN: Mengirim nama 'user' ke dalam constructor DashboardUtama
                    DashboardUtama dashboard = new DashboardUtama(user);
                    dashboard.start(new Stage());
                    panggungUtama.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                lblPesanError.setText("⚠️ Username atau Password salah!");
            }
        });

        linkDaftar.setOnAction(e -> tampilkanFormRegister());

        Scene sceneLogin = new Scene(layoutLogin, 400, 350);

        try {
            String css = this.getClass().getResource("/com/example/apkpemberianpakanikan/gaya.css").toExternalForm();
            sceneLogin.getStylesheets().add(css);
        } catch (Exception ignored) {}

        panggungUtama.setTitle("AquaFeed - Secure System Login");
        panggungUtama.setScene(sceneLogin);
        panggungUtama.show();
    }

    /**
     * 📝 RENDERING: Tampilan Form Pendaftaran Akun (Register)
     */
    private void tampilkanFormRegister() {
        VBox layoutDaftar = new VBox(12);
        layoutDaftar.setAlignment(Pos.CENTER);
        layoutDaftar.setPadding(new Insets(30));
        layoutDaftar.setStyle("-fx-background-color: #f5f6fa;");

        Label lblJudul = new Label("📝 REGISTRASI AKUN");
        lblJudul.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-padding: 0 0 10 0;");

        TextField txtNewUsername = new TextField();
        txtNewUsername.setPromptText("Buat Username Baru...");
        txtNewUsername.setMaxWidth(280);

        PasswordField txtNewPassword = new PasswordField();
        txtNewPassword.setPromptText("Buat Password...");
        txtNewPassword.setMaxWidth(280);

        PasswordField txtConfirmPassword = new PasswordField();
        txtConfirmPassword.setPromptText("Ulangi Password...");
        txtConfirmPassword.setMaxWidth(280);

        Button btnRegister = new Button("Daftar Sekarang");
        btnRegister.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnRegister.setPrefWidth(280);

        Hyperlink linkLogin = new Hyperlink("Sudah punya akun? Login di sini");
        linkLogin.setStyle("-fx-font-size: 11px;");

        Label lblPesanStatus = new Label("");
        lblPesanStatus.setStyle("-fx-font-weight: bold;");

        layoutDaftar.getChildren().addAll(lblJudul, txtNewUsername, txtNewPassword, txtConfirmPassword, btnRegister, linkLogin, lblPesanStatus);

        // 🚀 LOGIKA AKSI TOMBOL REGISTER (SIMPAN KE LARAGON)
        btnRegister.setOnAction(e -> {
            String userBaru = txtNewUsername.getText().trim();
            String passBaru = txtNewPassword.getText();
            String konfirmasiPass = txtConfirmPassword.getText();

            if (userBaru.isEmpty() || passBaru.isEmpty()) {
                lblPesanStatus.setText("⚠️ Data tidak boleh kosong!");
                lblPesanStatus.setStyle("-fx-text-fill: #e74c3c;");
            } else if (!passBaru.equals(konfirmasiPass)) {
                lblPesanStatus.setText("⚠️ Konfirmasi password tidak cocok!");
                lblPesanStatus.setStyle("-fx-text-fill: #e74c3c;");
            } else {
                // Proses penyimpanan data permanen ke Laragon
                boolean suksesDaftar = daftarAkunBaruKeDatabase(userBaru, passBaru);

                if (suksesDaftar) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registrasi Berhasil");
                    alert.setHeaderText(null);
                    alert.setContentText("Akun " + userBaru + " berhasil didaftarkan ke server! Silakan login.");
                    alert.showAndWait();

                    tampilkanFormLogin(); // Lempar kembali ke halaman login
                } else {
                    lblPesanStatus.setText("⚠️ Username sudah terdaftar di server!");
                    lblPesanStatus.setStyle("-fx-text-fill: #e74c3c;");
                }
            }
        });

        linkLogin.setOnAction(e -> tampilkanFormLogin());

        Scene sceneRegister = new Scene(layoutDaftar, 400, 400);

        try {
            String css = this.getClass().getResource("/com/example/apkpemberianpakanikan/gaya.css").toExternalForm();
            sceneRegister.getStylesheets().add(css);
        } catch (Exception ignored) {}

        panggungUtama.setScene(sceneRegister);
    }

    // ==========================================
    // 💾 FUNGSI SQL: MENDAFTARKAN AKUN BARU
    // ==========================================
    private boolean daftarAkunBaruKeDatabase(String username, String password) {
        String query = "INSERT INTO akun (username, password) VALUES (?, ?)";
        try (Connection conn = com.example.apkpemberianpakanikan.KoneksiDB.ambilKoneksi();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Database Register Error: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // 🔍 FUNGSI SQL: MEMVALIDASI MASUKNYA USER
    // ==========================================
    private boolean validasiLoginKeDatabase(String username, String password) {
        String query = "SELECT * FROM akun WHERE username = ? AND password = ?";
        try (Connection conn = com.example.apkpemberianpakanikan.KoneksiDB.ambilKoneksi();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("❌ Database Login Error: " + e.getMessage());
            return false;
        }
    }
}