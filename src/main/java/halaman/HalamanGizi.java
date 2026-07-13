package com.example.apkpemberianpakanikan.halaman;


import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class HalamanGizi {

    private Label lblInfoGizi;
    private TextArea txtDetailGizi;

    public VBox getKonten() {
        VBox layoutGizi = new VBox(15);
        layoutGizi.setPadding(new Insets(10));

        Label lblSub = new Label("🎨 Panduan Nutrisi & Pigmen Warna Spesifik:");
        lblSub.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ComboBox<String> menuIkanHias = new ComboBox<>();
        menuIkanHias.getItems().addAll(
                "Ikan Koi (Pigmen Merah & Putih)", "Ikan Mas Koki (Jambul & Tubuh Bulat)",
                "Ikan Discus / Arwana (Warna & Imunitas)", "Ikan Guppy (Vitalitas & Keindahan Ekor)",
                "Ikan Neon Tetra (Warna Neon & Koloni)", "Ikan Angelfish (Pertumbuhan Sirip & Karakter)"
        );
        menuIkanHias.setPromptText("- Pilih Jenis Ikan Hias -");
        menuIkanHias.setPrefWidth(500);

        lblInfoGizi = new Label("📊 Analisis Nutrisi :");
        lblInfoGizi.setStyle("-fx-font-weight: bold;");

        txtDetailGizi = new TextArea("Silakan pilih jenis ikan untuk melihat panduan gizi lengkap...");
        txtDetailGizi.setEditable(false); txtDetailGizi.setWrapText(true);
        txtDetailGizi.setPrefHeight(250);

        VBox boxDua = new VBox(10);
        boxDua.getChildren().addAll(menuIkanHias, lblInfoGizi, txtDetailGizi);
        boxDua.getStyleClass().add("kotak-gizi-hias");

        layoutGizi.getChildren().addAll(lblSub, boxDua);

        menuIkanHias.setOnAction(event -> updatePanduanGizi(menuIkanHias.getValue()));
        return layoutGizi;
    }

    private void updatePanduanGizi(String pilihan) {
        if (pilihan == null) return;
        if (pilihan.contains("Koi")) {
            txtDetailGizi.setText("🎯 NUTRISI KOI:\n- Protein (40%): Pembentukan bodi ideal.\n- Spirulina: Menajamkan pigmen sel warna merah.");
        } else if (pilihan.contains("Mas Koki")) {
            txtDetailGizi.setText("🎯 NUTRISI MAS KOKI:\n- Protein Tinggi (45%): Mempercepat penumbuhan jambul.\n- Sinking Type: Wajib pakan tenggelam agar terhindar dari kembung udara.");
        } else if (pilihan.contains("Discus")) {
            txtDetailGizi.setText("🎯 NUTRISI DISCUS/ARWANA:\n- High Animal Protein (50%): Kebutuhan wajib karnivora murni.\n- Astaxanthin: Merangsang warna kilau metalik sisik.");
        } else if (pilihan.contains("Guppy")) {
            txtDetailGizi.setText("🎯 NUTRISI GUPPY:\n- Vitamin A & C: Menjaga elastisitas ekor agar tidak robek.\n- Ukuran Micro: Menyesuaikan bukaan mulut guppy.");
        } else if (pilihan.contains("Neon Tetra")) {
            txtDetailGizi.setText("🎯 NUTRISI NEON TETRA:\n- Vegetable Protein Blend: Seimbang dengan omnivora kecil.\n- Garlic Essence: Ekstrak bawang putih penolak penyakit.");
        } else if (pilihan.contains("Angelfish")) {
            txtDetailGizi.setText("🎯 NUTRISI ANGELFISH:\n- Kalsium Tinggi: Memperkuat tulang sirip panjang.\n- Serat Tinggi: Mencegah penyumbatan organ.");
        }
    }
}