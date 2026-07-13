package com.example.apkpemberianpakanikan.halaman;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HalamanJadwal {

    public VBox getKonten() {
        VBox layoutJadwal = new VBox(15);
        layoutJadwal.setPadding(new Insets(10));

        Label lblKet = new Label("📐 Kalkulator Penentu Waktu & Berat Pakan Harian Ideal:");
        lblKet.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField txtUkuranIkan = new TextField();
        txtUkuranIkan.setPromptText("Masukkan panjang rata-rata ikan (cm)...");
        txtUkuranIkan.setPrefWidth(300);

        Button btnHitung = new Button("🧮 Analisis Jadwal & Porsi");
        btnHitung.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");

        Label lblHasilPorsi = new Label("Hasil perhitungan akan muncul di sini.");
        lblHasilPorsi.setWrapText(true);
        lblHasilPorsi.setStyle("-fx-font-style: italic; -fx-text-fill: #7f8c8d; -fx-background-color: #fff; -fx-padding: 20; -fx-background-radius: 8;");

        btnHitung.setOnAction(event -> {
            try {
                double ukuran = Double.parseDouble(txtUkuranIkan.getText());
                double porsiGram = (ukuran * 0.15);
                lblHasilPorsi.setText(
                        String.format("💡 HASIL REKOMENDASI YANG PAS:\n\n" +
                                        "• Total Porsi Ideal: %.1f Gram / Hari\n\n" +
                                        "• Jadwal Pemberian yang Pas:\n" +
                                        "   1. Pagi (07:30) : %.1f Gram (Energi pagi)\n" +
                                        "   2. Siang (13:00) : %.1f Gram (Metabolisme puncak)\n" +
                                        "   3. Sore (17:00) : %.1f Gram (Pemberian terakhir ringan)",
                                porsiGram, porsiGram/3, porsiGram/3, porsiGram/3)
                );
                lblHasilPorsi.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-background-color: #fff; -fx-padding: 20; -fx-background-radius: 8;");
            } catch (NumberFormatException e) {
                lblHasilPorsi.setText("⚠️ Tolong masukkan input angka panjang ikan yang valid!");
                lblHasilPorsi.setStyle("-fx-text-fill: #e74c3c;");
            }
        });

        layoutJadwal.getChildren().addAll(lblKet, txtUkuranIkan, btnHitung, lblHasilPorsi);
        return layoutJadwal;
    }
}