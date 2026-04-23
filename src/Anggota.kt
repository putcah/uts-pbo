import java.time.LocalDate

class Anggota(
    val idAnggota: String,
    val nama: String,
    val noTelp: String
) {
    var jumlahPinjam: Int = 0
    private val riwayatPeminjaman = mutableListOf<Peminjaman>()

    fun pinjamBuku(buku: Buku) {
        val peminjaman = Peminjaman("P-TEMP", buku)
        peminjaman.prosesPinjam()
        riwayatPeminjaman.add(peminjaman)
        jumlahPinjam++
    }
}
