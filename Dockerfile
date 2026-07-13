# Gunakan base image Linux yang sudah terinstal Java 17 dan Xvfb (Virtual Display)
FROM ubuntu:22.04

# Install Java dan dependensi visual agar JavaFX desktop bisa berjalan di server web
RUN apt-get update && apt-get install -y \
    openjdk-17-jre \
    xvfb \
    x11vnc \
    fluxbox \
    websockify \
    && rm -rf /var/lib/apt/lists/*

# Salin file .jar hasil Maven package Anda ke dalam server
COPY target/*.jar app.jar

# Expose port untuk akses web internet
EXPOSE 7860

# Jalankan virtual display server agar aplikasi desktop JavaFX Anda bisa dirender menjadi halaman web
CMD ["sh", "-c", "Xvfb :99 -screen 0 1024x768x16 & export DISPLAY=:99 && java -jar /app.jar"]