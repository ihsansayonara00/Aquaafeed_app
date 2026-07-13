package com.example.apkpemberianpakanikan.halaman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class HalamanToko {

    private final ArrayList<String> keranjangShared;
    private final Runnable callbackRefreshKeranjang;

    // Struktur Data Produk Toko
    static class ProdukPakan {
        String nama;
        int hargaPer10g;
        double nilaiGizi; // Kandungan Protein (%) per 10 gram

        public ProdukPakan(String nama, int hargaPer10g, double nilaiGizi) {
            this.nama = nama;
            this.hargaPer10g = hargaPer10g;
            this.nilaiGizi = nilaiGizi;
        }
    }

    // Daftar Variasi Pakan Hias
    private final ProdukPakan[] daftarProduk = {
            new ProdukPakan("Pellet Premium Goldfish", 4500, 45.0),
            new ProdukPakan("Cacing Darah Steril (Bloodworms)", 6000, 60.0),
            new ProdukPakan("Kutu Air Super (Daphnia)", 3500, 38.0),
            new ProdukPakan("Artemia Salina High Protein", 7500, 65.0),
            new ProdukPakan("Flakes Warna Tropikal", 4000, 42.0),
            new ProdukPakan("Maggot Kering Booster", 5000, 55.0),
            new ProdukPakan("Pelet Mikro Guppy Fry", 5500, 50.0),
            new ProdukPakan("Burger Jantung Sapi Premium", 9000, 70.0)
    };

    public HalamanToko(ArrayList<String> keranjang, Runnable callback) {
        this.keranjangShared = keranjang;
        this.callbackRefreshKeranjang = callback;
    }

    public VBox getKonten() {
        VBox layoutUtama = new VBox(20);
        layoutUtama.setPadding(new Insets(20));
        layoutUtama.setAlignment(Pos.TOP_LEFT);

        Label lblJudul = new Label("🛍️ Toko Pakan Hias Premium & Asisten Greedy");
        lblJudul.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // --- SECTION 1: E-SHOPPING (PILIHAN 10G & 50G) ---
        Label lblSubToko = new Label("Belanja Mandiri (Pilih Porsi Ukuran):");
        lblSubToko.setStyle("-fx-font-weight: bold; -fx-text-fill: #34495e;");

        GridPane gridProduk = new GridPane();
        gridProduk.setHgap(15);
        gridProduk.setVgap(15);

        for (int i = 0; i < daftarProduk.length; i++) {
            ProdukPakan p = daftarProduk[i];

            VBox card = new VBox(8);
            card.setStyle("-fx-background-color: #ffffff; -fx-padding: 15; -fx-background-radius: 8; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.04), 5, 0, 0, 1);");
            card.setPrefWidth(260);

            Label lblNama = new Label(p.nama);
            lblNama.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");

            // Hitung harga grosir untuk porsi 50 gram (Harga 10g dikali 5)
            int harga50g = p.hargaPer10g * 5;

            Label lblDetail = new Label("Protein: " + p.nilaiGizi + "%\n" +
                    "• Porsi 10g: Rp " + p.hargaPer10g + "\n" +
                    "• Porsi 50g: Rp " + harga50g);
            lblDetail.setStyle("-fx-font-size: 11px; -fx-text-fill: #7f8c8d;");

            // Baris Tombol Aksi (Berdampingan Kiri-Kanan)
            HBox tombolBar = new HBox(8);

            Button btnTambah10g = new Button("➕ 10 Gram");
            btnTambah10g.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 11px; -fx-cursor: hand;");

            Button btnTambah50g = new Button("➕ 50 Gram");
            btnTambah50g.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 11px; -fx-cursor: hand;");

            // Aksi Tambah 10 Gram
            btnTambah10g.setOnAction(e -> {
                keranjangShared.add(p.nama + " (10 Gram)|" + p.hargaPer10g);
                callbackRefreshKeranjang.run();
            });

            // 🌟 OTOMATIS: Aksi Tambah 50 Gram (Memasukkan porsi dan total harga yang sesuai)
            btnTambah50g.setOnAction(e -> {
                keranjangShared.add(p.nama + " (50 Gram)|" + harga50g);
                callbackRefreshKeranjang.run();
            });

            tombolBar.getChildren().addAll(btnTambah10g, btnTambah50g);
            card.getChildren().addAll(lblNama, lblDetail, tombolBar);
            gridProduk.add(card, i % 3, i / 3);
        }

        // --- SECTION 2: IMPLEMENTASI ALGORITMA GREEDY ---
        Separator sep = new Separator();

        VBox areaGreedy = new VBox(10);
        areaGreedy.setStyle("-fx-background-color: #e8f4f8; -fx-padding: 15; -fx-background-radius: 8;");
        areaGreedy.setMaxWidth(810);

        Label lblGreedyTitle = new Label("💡 Rekomendasi Asisten Pintar (Algoritma Greedy - Protein Terbanyak)");
        lblGreedyTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #2980b9; -fx-font-size: 14px;");

        Label lblGreedyDesc = new Label("Masukkan budget Anda, algoritma Greedy akan memprioritaskan paket pakan ekonomis dengan protein tertinggi.");
        lblGreedyDesc.setStyle("-fx-font-size: 12px; -fx-text-fill: #57606f;");

        HBox inputBar = new HBox(10);
        inputBar.setAlignment(Pos.CENTER_LEFT);
        TextField txtBudget = new TextField();
        txtBudget.setPromptText("Contoh budget: 50000");
        Button btnHitungGreedy = new Button("🤖 Hitung Rekomendasi Terbaik");
        btnHitungGreedy.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");

        inputBar.getChildren().addAll(txtBudget, btnHitungGreedy);

        TextArea txtHasilGreedy = new TextArea();
        txtHasilGreedy.setEditable(false);
        txtHasilGreedy.setPrefHeight(140);
        txtHasilGreedy.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12px;");

        // LOGIKA ALGORITMA GREEDY (Berdasarkan Densitas Protein per Rupiah)
        btnHitungGreedy.setOnAction(e -> {
            try {
                int budget = Integer.parseInt(txtBudget.getText().trim());
                if (budget <= 0) throw new NumberFormatException();

                ProdukPakan[] items = Arrays.copyOf(daftarProduk, daftarProduk.length);

                // Urutkan menurun berdasarkan rasio Densitas = Gizi / Harga
                Arrays.sort(items, new Comparator<ProdukPakan>() {
                    @Override
                    public int compare(ProdukPakan a, ProdukPakan b) {
                        double rasioA = a.nilaiGizi / a.hargaPer10g;
                        double rasioB = b.nilaiGizi / b.hargaPer10g;
                        return Double.compare(rasioB, rasioA);
                    }
                });

                StringBuilder sb = new StringBuilder();
                sb.append("=== HASIL REKOMENDASI OLEH ALGORITMA GREEDY ===\n");
                int sisaBudget = budget;
                double totalProteinDidapat = 0;

                for (ProdukPakan item : items) {
                    int harga50g = item.hargaPer10g * 5;
                    double protein50g = item.nilaiGizi * 5;

                    // 1. Cek greedy apakah uangnya cukup borong paket besar (50 gram) terlebih dahulu
                    if (sisaBudget >= harga50g) {
                        int jumlahPaket50g = sisaBudget / harga50g;
                        sisaBudget -= (jumlahPaket50g * harga50g);
                        totalProteinDidapat += (jumlahPaket50g * protein50g);
                        sb.append(String.format("• Ambil %d paket besar (%d Gram) dari: %s\n", jumlahPaket50g, (jumlahPaket50g * 50), item.nama));
                    }

                    // 2. Cek apakah sisa uangnya masih cukup untuk membeli eceran (10 gram)
                    if (sisaBudget >= item.hargaPer10g) {
                        int jumlahPaket10g = sisaBudget / item.hargaPer10g;
                        sisaBudget -= (jumlahPaket10g * item.hargaPer10g);
                        totalProteinDidapat += (jumlahPaket10g * item.nilaiGizi);
                        sb.append(String.format("• Ambil %d porsi eceran (%d Gram) dari: %s\n", jumlahPaket10g, (jumlahPaket10g * 10), item.nama));
                    }
                }

                sb.append("-----------------------------------------------\n");
                sb.append("💰 Sisa Uang Budget      : Rp " + sisaBudget + "\n");
                sb.append(String.format("💥 Estimasi Total Nutrisi Protein: %.2f %%\n", totalProteinDidapat));
                txtHasilGreedy.setText(sb.toString());

            } catch (NumberFormatException ex) {
                txtHasilGreedy.setText("⚠️ Tolong masukkan nominal budget berupa angka bulat yang valid!");
            }
        });

        areaGreedy.getChildren().addAll(lblGreedyTitle, lblGreedyDesc, inputBar, txtHasilGreedy);

        layoutUtama.getChildren().addAll(lblJudul, lblSubToko, gridProduk, sep, areaGreedy);
        return layoutUtama;
    }
}