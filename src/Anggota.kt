import java.time.LocalDate

class Anggota(
    val idAnggota: String,
    val nama: String,
    val noTelp: String
) {
    var jumlahPinjam: Int = 0
        private set
    var hasDenda: Boolean = false
        private set
    var totalDenda: Double = 0.0
        private set

    private val riwayatPeminjaman = mutableListOf<Peminjaman>()

    fun pinjamBuku(buku: Buku, tglPinjam: LocalDate = LocalDate.now()): Boolean {
        if (jumlahPinjam >= 3 || hasDenda || !buku.isTersedia) return false
        val idPinjam = "P-"+"-"+(riwayatPeminjaman.size + 1)
        val peminjaman = Peminjaman(idPinjam, buku, tglPinjam)
        if (peminjaman.prosesPinjam()) {
            riwayatPeminjaman.add(peminjaman)
            jumlahPinjam++
            return true
        }
        return false
    }
}
