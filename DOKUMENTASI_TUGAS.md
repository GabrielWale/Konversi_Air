# DOKUMENTASI APLIKASI ANDROID DASAR: DICE ROLLER (PENGOCOK DADU)

**Mata Kuliah / Tugas**: Tugas 4 - Pengembangan Aplikasi Android Dasar (Dice Roller)  
**Nama**: [Nama Lengkap Anda]  
**NIM**: [NIM Anda]  

---

## 1. Deskripsi Singkat Aplikasi
Aplikasi **Dice Roller** adalah aplikasi Android berbasis Kotlin yang mensimulasikan pelemparan dadu bersisi 6. Pengguna dapat menekan tombol **"Kocok Dadu"**, dan aplikasi secara otomatis mengacak angka 1 hingga 6 serta langsung memperbarui gambar dadu di layar secara real-time.

---

## 2. Struktur Proyek & Komponen Wajib
Sesuai dengan kriteria lembar tugas, aplikasi ini memuat komponen-komponen wajib:
1. **User Interface (UI)**:
   - File: `app/src/main/res/layout/activity_main.xml`
   - Menggunakan `LinearLayout` dengan posisi elemen di tengah layar (`gravity="center"`).
   - Terdapat 1 komponen `ImageView` (`@+id/diceImageView`) untuk menampilkan visual sisi dadu.
   - Terdapat 1 komponen `Button` (`@+id/rollButton`) dengan teks `"Kocok Dadu"`.
2. **Logika Kode (Kotlin)**:
   - File: `app/src/main/java/com/example/diceroller/MainActivity.kt`
   - Menggunakan fungsi `(1..6).random()` untuk menghasilkan angka acak antara 1 sampai 6.
   - Menggunakan percabangan `when (randomNumber)` untuk memetakan angka dadu ke resource gambar yang sesuai.
3. **Resource Asset**:
   - File: `app/src/main/res/drawable/`
   - Memiliki 6 aset gambar dadu (`dice_1.xml` hingga `dice_6.xml`) yang merepresentasikan titik 1 sampai 6.

---

## 3. Penjelasan Alur Kode Pemrograman

### A. Tampilan (`activity_main.xml`)
Tata letak diatur vertikal dan presisi di tengah layar:
- `diceImageView` diinisialisasi menampilkan `dice_1` sebagai tampilan awal sebelum tombol ditekan.
- `rollButton` diletakkan di bawah gambar dengan jarak `layout_marginTop="24dp"`.

### B. Aktivitas Utama (`MainActivity.kt`)
1. Pada metode `onCreate()`, aplikasi memuat layout XML dan mendaftarkan event listener pada tombol:
   ```kotlin
   val rollButton: Button = findViewById(R.id.rollButton)
   rollButton.setOnClickListener {
       rollDice()
   }
   ```
2. Fungsi `rollDice()` dijalankan saat tombol ditekan:
   - Menghasilkan nilai acak:
     ```kotlin
     val randomNumber = (1..6).random()
     ```
   - Memetakan angka ke drawable:
     ```kotlin
     val drawableResource = when (randomNumber) {
         1 -> R.drawable.dice_1
         2 -> R.drawable.dice_2
         3 -> R.drawable.dice_3
         4 -> R.drawable.dice_4
         5 -> R.drawable.dice_5
         else -> R.drawable.dice_6
     }
     ```
   - Memperbarui gambar di layar secara langsung:
     ```kotlin
     diceImageView.setImageResource(drawableResource)
     ```

---

## 4. Tangkapan Layar Aplikasi (Screenshots)

> *Silakan masukkan tangkapan layar saat aplikasi dijalankan di emulator atau smartphone:*

1. **Tampilan Awal Aplikasi (Sebelum Dadu Dikocok)**:  
   *(Tempatkan screenshot awal di sini)*

2. **Tampilan Saat Tombol Diklik (Hasil Kocokan Dadu)**:  
   *(Tempatkan screenshot hasil kocokan di sini)*
