package com.example.apkpemberianpakanikan.halaman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class HalamanProfil {

    // 🌟 Menyiapkan variabel lokal untuk menampung username
    private String usernameLogin = "admin";

    // 🌟 CONSTRUCTOR BARU: Dipanggil oleh DashboardUtama untuk menitipkan nama
    public HalamanProfil(String username) {
        if (username != null && !username.isEmpty()) {
            this.usernameLogin = username;
        }
    }

    // Constructor kosong bawaan (supaya aman jika dipanggil tanpa parameter)
    public HalamanProfil() {
    }

    public VBox getKonten() {
        VBox layoutUtama = new VBox(20);
        layoutUtama.setPadding(new Insets(10));
        layoutUtama.setAlignment(Pos.TOP_LEFT);

        Label lblJudul = new Label("👤 Profil Pengguna");
        lblJudul.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        VBox kartuProfil = new VBox(15);
        kartuProfil.setAlignment(Pos.CENTER);
        kartuProfil.setPrefWidth(450);
        kartuProfil.setMaxWidth(450);
        kartuProfil.setStyle("-fx-background-color: #ffffff; -fx-padding: 30; -fx-background-radius: 12; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.06), 8, 0, 0, 2);");

        // 🔘 MENGATUR AVATAR DAN TEKS INISIAL OTOMATIS
        StackPane wadahAvatar = new StackPane();
        Circle avatarLingkaran = new Circle(50);
        avatarLingkaran.setFill(Color.web("#3498db"));

        // 🌟 OTOMATIS: Mengambil huruf pertama dari username lalu dijadikan huruf kapital
        String inisialHuruf = usernameLogin.substring(0, 1).toUpperCase();
        Label lblInisial = new Label(inisialHuruf);
        lblInisial.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");
        wadahAvatar.getChildren().addAll(avatarLingkaran, lblInisial);

        // 🌟 OTOMATIS: Mengubah nama lengkap menjadi sesuai username
        Label lblNamaLengkap = new Label(usernameLogin.toUpperCase() + " AQUAFEED");
        lblNamaLengkap.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label lblRole = new Label("Premium Member • Fish Enthusiast");
        lblRole.setStyle("-fx-font-size: 13px; -fx-text-fill: #9b59b6; -fx-font-weight: bold;");

        VBox barisDetail = new VBox(10);
        barisDetail.setAlignment(Pos.CENTER_LEFT);
        barisDetail.setPadding(new Insets(15, 0, 0, 0));
        barisDetail.setStyle("-fx-border-color: #f1f2f6; -fx-border-width: 1 0 0 0;");

        // 🌟 OTOMATIS: Label username berubah berdasarkan isian login
        Label lblDataUsername = new Label("✉️ Username : " + usernameLogin);
        lblDataUsername.setStyle("-fx-font-size: 13px; -fx-text-fill: #57606f;");

        // 🌟 OTOMATIS: Email juga menyesuaikan agar lebih realistis
        Label lblDataEmail = new Label("📧 Email       : " + usernameLogin.toLowerCase() + "@aquafeed.com");
        lblDataEmail.setStyle("-fx-font-size: 13px; -fx-text-fill: #57606f;");

        Label lblDataStatus = new Label("🛡️ Status Akun : Aktif (Verified)");
        lblDataStatus.setStyle("-fx-font-size: 13px; -fx-text-fill: #2ed573; -fx-font-weight: bold;");

        barisDetail.getChildren().addAll(lblDataUsername, lblDataEmail, lblDataStatus);

        kartuProfil.getChildren().addAll(wadahAvatar, lblNamaLengkap, lblRole, barisDetail);
        layoutUtama.getChildren().addAll(lblJudul, kartuProfil);

        return layoutUtama;
    }
}