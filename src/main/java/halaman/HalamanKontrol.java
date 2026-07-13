package com.example.apkpemberianpakanikan.halaman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HalamanKontrol {

    public VBox getKonten() {
        VBox layoutUtama = new VBox(15);
        layoutUtama.setPadding(new Insets(20));
        layoutUtama.setAlignment(Pos.TOP_LEFT);

        Label lblJudul = new Label("📑 Biodata & Ensiklopedia Ikan Hias (15 Spesies)");
        lblJudul.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label lblDeskripsi = new Label("Daftar lengkap karakteristik, masa hidup, dan pakan ideal untuk 15 jenis ikan hias.");
        lblDeskripsi.setStyle("-fx-font-size: 13px; -fx-text-fill: #7f8c8d;");

        Accordion menuAkordionIkan = new Accordion();
        menuAkordionIkan.setMaxWidth(600);

        // 1-5 Ikan Utama
        TitledPane p1 = buatKartuIkan("🐠 Ikan Mas Koki", "Carassius auratus", "Asia Timur", "Cyprinidae", "5-10 Tahun", "Pelet tenggelam, sayuran", "Filter kuat, kuras 20% seminggu", "Jinak dan rakus.");
        TitledPane p2 = buatKartuIkan("🐟 Ikan Cupang", "Betta splendens", "Asia Tenggara", "Osphronemidae", "2-5 Tahun", "Pelet khusus, jentik nyamuk", "Tanpa aerator, air tenang", "Agresif & teritorial.");
        TitledPane p3 = buatKartuIkan("🐠 Ikan Guppy", "Poecilia reticulata", "Amerika Selatan", "Poeciliidae", "1-3 Tahun", "Pelet mikro, kutu air", "Mudah dirawat, berkelompok", "Livebearer (melahirkan).");
        TitledPane p4 = buatKartuIkan("👑 Ikan Discus", "Symphysodon", "Sungai Amazon", "Cichlidae", "10-15 Tahun", "Burger jantung sapi, pelet premium", "Suhu hangat (28-31°C), pH asam", "Anggun namun penakut.");
        TitledPane p5 = buatKartuIkan("🎏 Ikan Koi", "Cyprinus rubrofuscus", "Jepang", "Cyprinidae", "20-30 Tahun", "Pelet apung, maggot", "Kolam luas, dalam min 1 meter", "Bisa dilatih makan di tangan.");

        // 6-10 Ikan Tambahan
        TitledPane p6 = buatKartuIkan("🦈 Ikan Arwana", "Scleropages formosus", "Asia Tenggara / Indo", "Osteoglossidae", "15-20 Tahun", "Jangkrik, kodok kecil, udang pasar", "Akuarium super besar, tutup rapat", "Simbol keberuntungan & mewah.");
        TitledPane p7 = buatKartuIkan("🦓 Ikan Zebra Danio", "Danio rerio", "Asia Selatan", "Danionidae", "2-5 Tahun", "Serpihan pakan (flakes), artemia", "Sangat aktif renang, kelompok min 5", "Garis hitam-putih mirip zebra.");
        TitledPane p8 = buatKartuIkan("Neon Tetra", "Paracheirodon innesi", "Sungai Amazon", "Characidae", "3-5 Tahun", "Pelet serbuk halus, daphnia", "Pencahayaan redup, tanaman air", "Warna neon menyala berkelompok.");
        TitledPane p9 = buatKartuIkan("🐡 Ikan Buntal Kerdil", "Carinotetraodon travancoricus", "India", "Tetraodontidae", "3-5 Tahun", "Siput hama kecil, cacing darah", "Butuh dekorasi batu/kayu sembunyi", "Mampu menggembungkan tubuh.");
        TitledPane p10 = buatKartuIkan("🐠 Ikan Manvis (Angelfish)", "Pterophyllum scalare", "Sungai Amazon", "Cichlidae", "10-12 Tahun", "Pelet serpihan, cacing sutra", "Akuarium tinggi, arus lambat", "Bentuk tubuh pipih segitiga.");

        // 11-15 Ikan Tambahan
        TitledPane p11 = buatKartuIkan("🐱 Ikan Corydoras", "Corydoras", "Amerika Selatan", "Callichthyidae", "5-7 Tahun", "Pelet tenggelam khusus dasar (wafer)", "Substrat pasir halus agar kumis aman", "Pembersih sisa makanan di dasar.");
        TitledPane p12 = buatKartuIkan("🐉 Ikan Oscar", "Astronotus ocellatus", "Sungai Amazon", "Cichlidae", "10-12 Tahun", "Pelet predator, ikan mas kecil", "Filterasi ekstra kuat, agresif", "Sangat pintar, mengenali pemilik.");
        TitledPane p13 = buatKartuIkan("🐠 Ikan Platy", "Xiphophorus maculatus", "Amerika Tengah", "Poeciliidae", "2-3 Tahun", "Pelet biasa, alga kering", "Campuran tanaman hidup, air bersih", "Ramah, cocok untuk pemula.");
        TitledPane p14 = buatKartuIkan("🦐 Ikan Molly", "Poecilia sphenops", "Meksiko", "Poeciliidae", "3-5 Tahun", "Pelet sayur, jentik nyamuk", "Bisa hidup di air agak payau", "Pembersih lapisan minyak air.");
        TitledPane p15 = buatKartuIkan("🦎 Ikan Black Ghost", "Apteronotus albifrons", "Amazon", "Apteronotidae", "10-15 Tahun", "Cacing darah beku, artemia", "Nokturnal, butuh pipa sembunyi", "Berenang mundur menggunakan gelombang sirip.");

        menuAkordionIkan.getPanes().addAll(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15);
        menuAkordionIkan.setExpandedPane(p1);

        layoutUtama.getChildren().addAll(lblJudul, lblDeskripsi, menuAkordionIkan);
        return layoutUtama;
    }

    private TitledPane buatKartuIkan(String namaDisplay, String namaIlmiah, String asal, String jenis,
                                     String umur, String pakan, String rawat, String catatan) {
        VBox isiKartu = new VBox(10);
        isiKartu.setPadding(new Insets(15));
        isiKartu.setStyle("-fx-background-color: #ffffff;");

        Label lblSub = new Label("Nama Ilmiah: " + namaIlmiah);
        lblSub.setStyle("-fx-font-size: 11px; -fx-font-style: italic; -fx-text-fill: #95a5a6;");

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(8);

        Label t1 = new Label("🌍 Asal"); Label t2 = new Label("🧬 Famili");
        Label t3 = new Label("⏳ Umur"); Label t4 = new Label("🍖 Pakan");
        Label t5 = new Label("🧼 Rawat"); Label t6 = new Label("📝 Catatan");

        Label[] titles = {t1, t2, t3, t4, t5, t6};
        for (Label l : titles) l.setStyle("-fx-font-weight: bold; -fx-text-fill: #34495e; -fx-font-size: 12px;");

        Label i1 = new Label(": " + asal); Label i2 = new Label(": " + jenis);
        Label i3 = new Label(": " + umur); Label i4 = new Label(": " + pakan);
        Label i5 = new Label(": " + rawat); Label i6 = new Label(": " + catatan);

        Label[] contents = {i1, i2, i3, i4, i5, i6};
        for (Label l : contents) {
            l.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 12px;");
            l.setWrapText(true);
            l.setMaxWidth(420);
        }

        grid.add(t1, 0, 0); grid.add(i1, 1, 0);
        grid.add(t2, 0, 1); grid.add(i2, 1, 1);
        grid.add(t3, 0, 2); grid.add(i3, 1, 2);
        grid.add(t4, 0, 3); grid.add(i4, 1, 3);
        grid.add(t5, 0, 4); grid.add(i5, 1, 4);
        grid.add(t6, 0, 5); grid.add(i6, 1, 5);

        isiKartu.getChildren().addAll(lblSub, grid);
        return new TitledPane(namaDisplay, isiKartu);
    }
}