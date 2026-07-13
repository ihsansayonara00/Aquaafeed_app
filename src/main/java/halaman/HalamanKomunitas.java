package com.example.apkpemberianpakanikan.halaman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HalamanKomunitas {

    private String namaPenggunaAktif = "Anonim";
    private VBox wadahDaftarStatus;

    public HalamanKomunitas(String username) {
        if (username != null && !username.isEmpty()) {
            this.namaPenggunaAktif = username;
        }
    }

    public HalamanKomunitas() {
    }

    public VBox getKonten() {
        VBox layoutUtama = new VBox(15);
        layoutUtama.setPadding(new Insets(20));
        layoutUtama.setAlignment(Pos.TOP_LEFT);

        Label lblJudul = new Label("👥 Komunitas Ikan Hias");
        lblJudul.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextArea txtKontenStatus = new TextArea();
        txtKontenStatus.setPromptText("Bagikan progres atau cerita ikan hias Anda hari ini...");
        txtKontenStatus.setPrefHeight(80);
        txtKontenStatus.setMaxWidth(500);

        Button btnBagikan = new Button("🚀 Bagikan Progres");
        btnBagikan.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");

        Label lblStatusKirim = new Label("");
        lblStatusKirim.setStyle("-fx-font-weight: bold;");

        wadahDaftarStatus = new VBox(12);
        wadahDaftarStatus.setPadding(new Insets(10, 0, 0, 0));
        wadahDaftarStatus.setMaxWidth(500);

        // Muat data status saat halaman pertama kali dibuka
        muatUlangDaftarStatus();

        // LOGIKA TOMBOL BAGIKAN STATUS
        btnBagikan.setOnAction(e -> {
            String teksStatus = txtKontenStatus.getText().trim();

            if (teksStatus.isEmpty()) {
                lblStatusKirim.setText("⚠️ Status tidak boleh kosong!");
                lblStatusKirim.setStyle("-fx-text-fill: #e74c3c;");
            } else {
                boolean sukses = simpanStatusKeLaragon(this.namaPenggunaAktif, teksStatus);

                if (sukses) {
                    lblStatusKirim.setText("✅ Status berhasil dibagikan!");
                    lblStatusKirim.setStyle("-fx-text-fill: #27ae60;");
                    txtKontenStatus.clear();
                    muatUlangDaftarStatus(); // Refresh lini masa secara real-time
                } else {
                    lblStatusKirim.setText("❌ Gagal mengirim ke server database.");
                    lblStatusKirim.setStyle("-fx-text-fill: #e74c3c;");
                }
            }
        });

        layoutUtama.getChildren().addAll(lblJudul, txtKontenStatus, btnBagikan, lblStatusKirim, wadahDaftarStatus);
        return layoutUtama;
    }

    // =========================================================
    // 🔄 FUNGSI MEMBACA DATA DARI DATABASE LARAGON
    // =========================================================
    private void muatUlangDaftarStatus() {
        wadahDaftarStatus.getChildren().clear();

        String query = "SELECT username, isi_konten, info_suka FROM komunitas ORDER BY id DESC";

        try (Connection conn = com.example.apkpemberianpakanikan.KoneksiDB.ambilKoneksi();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String userStatus = rs.getString("username");
                String isiStatus = rs.getString("isi_konten");
                String suka = rs.getString("info_suka");

                VBox kotakPostingan = new VBox(6);
                kotakPostingan.setStyle("-fx-background-color: #ffffff; -fx-padding: 15; -fx-background-radius: 8; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.04), 4, 0, 0, 1);");

                Label lblUser = new Label("👤 " + userStatus);
                lblUser.setStyle("-fx-font-weight: bold; -fx-text-fill: #2980b9; -fx-font-size: 13px;");

                Label lblIsi = new Label(isiStatus);
                lblIsi.setWrapText(true);
                lblIsi.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 13px;");

                // 🌟 FIX: Memastikan teks dibungkus dengan tanda kutip String "" yang benar
                Label lblLike = new Label(suka != null ? suka : "👍 0 Suka");
                lblLike.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px;");

                kotakPostingan.getChildren().addAll(lblUser, lblIsi, lblLike);
                wadahDaftarStatus.getChildren().add(kotakPostingan);
            }

        } catch (SQLException e) {
            System.out.println("❌ Gagal memuat daftar status: " + e.getMessage());
        }
    }

    // =========================================================
    // 💾 FUNGSI MENYIMPAN DATA STATUS BARU
    // =========================================================
    private boolean simpanStatusKeLaragon(String username, String konten) {
        String query = "INSERT INTO komunitas (username, isi_konten, info_suka) VALUES (?, ?, ?)";
        try (Connection conn = com.example.apkpemberianpakanikan.KoneksiDB.ambilKoneksi();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, konten);
            ps.setString(3, "👍 0 Suka");

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Database Komunitas Error: " + e.getMessage());
            return false;
        }
    }
}