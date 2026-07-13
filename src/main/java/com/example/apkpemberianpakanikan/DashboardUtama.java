package com.example.apkpemberianpakanikan;

import com.example.apkpemberianpakanikan.halaman.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;

public class DashboardUtama extends Application {

    // --- VARIABEL GLOBAL DATA ---
    private final ArrayList<String> daftarKeranjangBelanja = new ArrayList<>();
    private String usernameLogin = "Pengguna"; // Penampung username yang sedang aktif

    // 🌟 CONSTRUCTOR: Menerima nama dari Halaman Login saat sukses masuk
    public DashboardUtama(String username) {
        this.usernameLogin = username;
    }

    // Constructor kosong bawaan JavaFX agar tidak eror di bagian lain aplikasi
    public DashboardUtama() {
    }

    // --- KOMPONEN UI GLOBAL ---
    private Button btnLihatKeranjang;
    private BorderPane kontainerUtamaKanan;

    // Tombol Navigasi Samping + Tombol Keluar Akun
    private Button btnM1, btnM2, btnM3, btnM4, btnM5, btnM6, btnLogout;
    private Stage panggungDashboard; // Menyimpan referensi stage untuk proses close/logout

    @Override
    public void start(Stage panggungUtama) {
        this.panggungDashboard = panggungUtama;

        // =========================================================
        // 🏢 1. HEADER UTAMA (STAY DI ATAS KANAN SCREEN)
        // =========================================================
        Label lblJudulAplikasi = new Label(" AQUAFEED - ECOSYSTEM DASHBOARD");
        lblJudulAplikasi.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        btnLihatKeranjang = new Button("🛒 Keranjang: 0 Item (Rp 0)");
        btnLihatKeranjang.setStyle("-fx-font-weight: bold; -fx-background-color: #27ae60; -fx-text-fill: white; -fx-cursor: hand;");
        btnLihatKeranjang.setOnAction(event -> tampilkanIsiKeranjangPopUp());

        HBox areaHeaderAtas = new HBox(20);
        areaHeaderAtas.getChildren().addAll(lblJudulAplikasi, btnLihatKeranjang);
        areaHeaderAtas.setAlignment(Pos.CENTER_RIGHT);
        areaHeaderAtas.setPadding(new Insets(15, 20, 15, 20));
        areaHeaderAtas.setStyle("-fx-background-color: #ffffff; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");


        // =========================================================
        // 🗂️ 2. MEMBUAT MENU NAVIGASI SAMPING LEBIH BESAR (SIDEBAR)
        // =========================================================
        VBox sidebarKiri = new VBox();
        sidebarKiri.getStyleClass().add("sidebar");
        sidebarKiri.setPrefWidth(260);

        Label lblMenuTitle = new Label("MAIN MENU");
        lblMenuTitle.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 0 0 10 10;");

        // Definisi tombol fitur utama
        btnM1 = new Button("🛍️  Toko Pakan Hias");
        btnM2 = new Button("🎛️  Pustaka IKAN dan jenis");
        btnM3 = new Button("🎨  Panduan Gizi Hias");
        btnM4 = new Button("📐  Jadwal & Porsi Pas");
        btnM5 = new Button("👥  Komunitas Hias");
        btnM6 = new Button("👤  Profil Pengguna");

        // 🚪 Tombol Keluar Akun (Diberi warna merah khas tanda log out)
        btnLogout = new Button("🚪  Keluar Akun");
        btnLogout.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-padding: 12px 20px; -fx-background-radius: 8px; -fx-cursor: hand;");
        btnLogout.setPrefWidth(240);

        // Masukkan tombol fitur ke dalam array untuk diatur ukurannya secara massal
        Button[] tombolFitur = {btnM1, btnM2, btnM3, btnM4, btnM5, btnM6};
        for (Button btn : tombolFitur) {
            btn.getStyleClass().add("tombol-menu-samping");
            btn.setPrefWidth(240);
        }

        // Gabungkan komponen ke dalam sidebar
        sidebarKiri.getChildren().add(lblMenuTitle);
        sidebarKiri.getChildren().addAll(tombolFitur);

        // Membuat region kosong penolak agar tombol logout terdorong ke bagian bawah sidebar
        VBox areaPendorongBawah = new VBox();
        VBox.setVgrow(areaPendorongBawah, javafx.scene.layout.Priority.ALWAYS);
        sidebarKiri.getChildren().addAll(areaPendorongBawah, btnLogout);


        // =========================================================
        // 🔄 3. INISIALISASI HALAMAN MODULAR & LOGIKA NAVIGASI
        // =========================================================
        kontainerUtamaKanan = new BorderPane();
        kontainerUtamaKanan.getStyleClass().add("area-konten-kanan");

        HalamanToko halamanToko = new HalamanToko(daftarKeranjangBelanja, this::refreshTampilanKeranjang);
        HalamanKontrol halamanKontrol = new HalamanKontrol();
        HalamanGizi halamanGizi = new HalamanGizi();
        HalamanJadwal halamanJadwal = new HalamanJadwal();
        HalamanKomunitas halamanKomunitas = new HalamanKomunitas(this.usernameLogin);

        // 🌟 PERBAIKAN: Kirim variabel usernameLogin ke dalam parameter HalamanProfil
        HalamanProfil halamanProfil = new HalamanProfil(this.usernameLogin);

        // Set Halaman Default awal
        setHalamanAktif(halamanToko.getKonten(), btnM1);

        // Aksi klik navigasi untuk menukar isi halaman
        btnM1.setOnAction(e -> setHalamanAktif(halamanToko.getKonten(), btnM1));
        btnM2.setOnAction(e -> setHalamanAktif(halamanKontrol.getKonten(), btnM2));
        btnM3.setOnAction(e -> setHalamanAktif(halamanGizi.getKonten(), btnM3));
        btnM4.setOnAction(e -> setHalamanAktif(halamanJadwal.getKonten(), btnM4));
        btnM5.setOnAction(e -> setHalamanAktif(halamanKomunitas.getKonten(), btnM5));
        btnM6.setOnAction(e -> setHalamanAktif(halamanProfil.getKonten(), btnM6));

        // 🔥 LOGIKA AKSI TOMBOL KELUAR AKUN (LOGOUT)
        btnLogout.setOnAction(e -> aksiKeluarAkun());


        // =========================================================
        // 📦 4. ARSITEKTUR STRUKTUR DAN SCENE DISPLAY
        // =========================================================
        BorderPane layoutDashboardMurni = new BorderPane();
        layoutDashboardMurni.setTop(areaHeaderAtas);
        layoutDashboardMurni.setLeft(sidebarKiri);
        layoutDashboardMurni.setCenter(kontainerUtamaKanan);

        Scene adeganUtama = new Scene(layoutDashboardMurni, 1100, 750);

        try {
            String css = this.getClass().getResource("/com/example/apkpemberianpakanikan/gaya.css").toExternalForm();
            adeganUtama.getStylesheets().add(css);
        } catch (Exception ignored) {}

        panggungUtama.setTitle("AquaFeed Premium Ecosystem Dashboard v3.5");
        panggungUtama.setScene(adeganUtama);
        panggungUtama.show();
    }


    // =========================================================
    // 🛠️ KUMPULAN LOGIKA PEMBANTU (HELPER METHODS)
    // =========================================================

    /**
     * Fungsi untuk memproses konfirmasi logout dan mengembalikan ke layar login
     */
    private void aksiKeluarAkun() {
        Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION);
        konfirmasi.setTitle("Konfirmasi Keluar");
        konfirmasi.setHeaderText(null);
        konfirmasi.setContentText("Apakah Anda yakin ingin keluar dari akun ini?");

        ButtonType btnYa = new ButtonType("Ya, Keluar", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnBatal = new ButtonType("Batal", ButtonBar.ButtonData.CANCEL_CLOSE);
        konfirmasi.getButtonTypes().setAll(btnYa, btnBatal);

        Optional<ButtonType> pilihan = konfirmasi.showAndWait();
        if (pilihan.isPresent() && pilihan.get() == btnYa) {
            try {
                // 1. Panggil kembali HalamanAutentikasi (Form Login)
                HalamanAutentikasi halamanLogin = new HalamanAutentikasi();
                halamanLogin.start(new Stage());

                // 2. Tutup jendela Dashboard Utama saat ini
                panggungDashboard.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metode tunggal untuk mengatur halaman aktif.
     * Menerima VBox murni dari halaman, lalu secara otomatis membungkusnya ke dalam ScrollPane terpadu.
     */
    private void setHalamanAktif(VBox kontenBaru, Button tombolAktif) {
        ScrollPane sp = new ScrollPane(kontenBaru);
        sp.setFitToWidth(true);
        sp.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        kontainerUtamaKanan.setCenter(sp);

        Button[] semuaTombol = {btnM1, btnM2, btnM3, btnM4, btnM5, btnM6};
        for (Button btn : semuaTombol) {
            btn.getStyleClass().remove("tombol-menu-aktif");
        }
        tombolAktif.getStyleClass().add("tombol-menu-aktif");
    }

    private void refreshTampilanKeranjang() {
        int qty = 0; int total = 0;
        for (String item : daftarKeranjangBelanja) {
            qty++;
            String[] split = item.split("\\|");
            total += Integer.parseInt(split[1]);
        }
        btnLihatKeranjang.setText("🛒 Keranjang: " + qty + " Item (Rp " + String.format("%,d", total) + ")");
    }

    private void tampilkanIsiKeranjangPopUp() {
        Alert infoBox = new Alert(Alert.AlertType.CONFIRMATION);
        infoBox.setTitle("Detail Isi Keranjang Belanja");
        infoBox.setHeaderText("Daftar Belanja Pakan Premium Anda:");

        ButtonType btnSelesai = new ButtonType("Kembali Ke Dashboard", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnKosongkan = new ButtonType("❌ Kosongkan Keranjang", ButtonBar.ButtonData.LEFT);
        infoBox.getButtonTypes().setAll(btnKosongkan, btnSelesai);

        if (daftarKeranjangBelanja.isEmpty()) {
            infoBox.setContentText("Keranjang belanjaan Anda masih kosong...");
        } else {
            StringBuilder sb = new StringBuilder();
            int total = 0; int qty = 0;
            for (String b : daftarKeranjangBelanja) {
                qty++;
                String[] split = b.split("\\|");
                sb.append(split[0]).append("\n");
                total += Integer.parseInt(split[1]);
            }
            sb.append("\n-------------------------------------");
            sb.append("\n📦 Total Qty: ").append(qty).append(" Item");
            sb.append("\n💰 Total Tagihan: Rp ").append(String.format("%,d", total));
            infoBox.setContentText(sb.toString());
        }

        Optional<ButtonType> res = infoBox.showAndWait();
        if (res.isPresent() && res.get() == btnKosongkan) {
            daftarKeranjangBelanja.clear();
            refreshTampilanKeranjang();
        }
    }
}