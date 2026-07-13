package com.example.apkpemberianpakanikan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    private static final String URL = "jdbc:mysql://localhost:3306/db_aquafeed";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection ambilKoneksi() {
        Connection koneksi = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Koneksi Gagal: " + e.getMessage());
        }
        return koneksi;
    }
}