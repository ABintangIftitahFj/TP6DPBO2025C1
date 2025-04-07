# TP6DPBO2025C1

## Janji  
Saya **A Bintang Iftitah FJ** dengan NIM **2305995** mengerjakan soal TP 6 dalam mata kuliah **Desain dan Pemrograman Berorientasi Objek** untuk keberkahanNya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

---

## Penjelasan Kelas

### 1. `MainMenu`
Sebagai tampilan awal game. Menampilkan:
- Background
- Judul "Flappy Bird"
- Tombol **Start Game**  
Saat ditekan, akan membuka game utama (`App`) dan menutup menu.

### 2. `App`
- Membuka `JFrame` utama game
- Menambahkan panel `FlappyBird`
- Menjalankan game loop
- Fokus pada input keyboard

### 3. `FlappyBird`
Panel utama game. Menangani:
- Animasi background, player, dan pipa
- Gravity, lompatan, dan gerak objek
- Deteksi tabrakan (collision)
- Game Over dan restart (`R`)
- Penambahan skor saat melewati pipa

### 4. `Player`
Representasi burung yang dikendalikan pemain. Menyimpan:
- Posisi, ukuran, gambar
- Kecepatan jatuh (`velocityY`)

### 5. `Pipe`
Representasi rintangan pipa. Menyimpan:
- Posisi, ukuran, gambar, dan kecepatan horizontal
- Status apakah sudah dilewati (untuk skor)

---

## Alur Program

1. Program dimulai dari `MainMenu.java`
2. User klik **Start Game**
3. Game dimulai di jendela baru
4. Burung bergerak jatuh (gravity), tekan **spasi** untuk lompat
5. Pipa muncul dan bergerak ke kiri
6. Jika burung menyentuh pipa atau jatuh → **Game Over**
7. Tekan **R** untuk memulai ulang
8. Skor bertambah setiap kali burung melewati satu pasang pipa

---

## Penjelasan Fitur

- ✅ Main menu sebelum game dimulai
- ✅ Background dan asset grafis
- ✅ Animasi burung dan pipa
- ✅ Gravity dan lompatan
- ✅ Deteksi tabrakan (collision)
- ✅ Game Over saat tabrakan
- ✅ Restart dengan tombol **R**
- ✅ Skor bertambah setiap melewati satu pasang pipa
- ✅ Tampilan skor dengan `JLabel`

---

## Dokumentasi
![Recording 2025-04-07 225234](https://github.com/user-attachments/assets/0dd37c1e-e2d1-4617-af43-c9ee043116e9)


- **Bahasa**: Java
- **IDE**: IntelliJ IDEA
- **GUI Library**: Swing (`JFrame`, `JPanel`, `JLabel`, `JButton`, `Timer`)
- **Assets**: Folder `assets/` untuk gambar
- **Main Class**: `MainMenu.java`
- **Class Pendukung**: `App.java`, `FlappyBird.java`, `Player.java`, `Pipe.java`
