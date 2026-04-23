import java.time.LocalDate

fun main() {
    println("=== Sistem Perpustakaan ITK-Library ===\n")

    val pustakawan = Pustakawan("P001", "Ibu Sari", "admin", "admin123")

    val buku1 = Buku("B001", "Clean Code", "Robert C. Martin", "Prentice Hall", 2008, 2)
    val buku2 = Buku("B002", "Kotlin in Action", "Dmitry Jemerov", "Manning", 2017, 1)
    val buku3 = Buku("B003", "Design Patterns", "Erich Gamma", "Addison-Wesley", 1994, 1)

    val anggota1 = Anggota("A001", "Budi Santoso", "081111111")
    val anggota2 = Anggota("A002", "Citra Dewi", "082222222")

    // Skenario 1: Pustakawan kelola data
    println("-- Pengelolaan Data oleh Pustakawan --")
    if (pustakawan.login("admin", "admin123")) {
        pustakawan.tambahBuku(buku1)
        pustakawan.tambahBuku(buku2)
        pustakawan.tambahBuku(buku3)
        pustakawan.tampilkanDaftarBuku()
    }

    // Skenario 2: Peminjaman normal
    println("\n-- Skenario Peminjaman Normal --")
    anggota1.pinjamBuku(buku1)
    anggota1.pinjamBuku(buku2)

    // Skenario 3: Meminjam saat stok kosong
    println("\n-- Skenario Meminjam Buku Habis --")
    anggota2.pinjamBuku(buku2)

    // Skenario 4: Pengembalian terlambat dan kena denda
    println("\n-- Skenario Pengembalian Terlambat --")
    // Simulasi pinjam 10 hari yang lalu (terlambat)
    anggota1.pinjamBuku(buku3, LocalDate.now().minusDays(10))

    // Anggota 1 mengembalikan hari ini (lewat 3 hari dari batas 7 hari)
    anggota1.kembalikanBuku(buku3, LocalDate.now())
    println(anggota1.tampilkanInfo())

    // Skenario 5: Coba pinjam saat masih punya denda
    println("\n-- Skenario Validasi Denda --")
    anggota1.pinjamBuku(buku2)

    // Anggota 1 membayar lunas denda
    anggota1.bayarDenda(3000.0)

    // Coba pinjam lagi setelah denda beres
    anggota1.pinjamBuku(buku2)
    println(anggota1.tampilkanInfo())

    // Skenario 6: Pustakawan menghapus buku
    println("\n-- Skenario Menghapus Buku --")
    pustakawan.hapusBuku("B002")
    pustakawan.tampilkanDaftarBuku()

    println("\n=== Simulasi Selesai ===")
}