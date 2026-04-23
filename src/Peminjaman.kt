import java.time.LocalDate

class Peminjaman(
    val idPinjam: String,
    val buku: Buku,
    val tglPinjam: LocalDate = LocalDate.now()
) {
    var tglKembali: LocalDate? = null
    var status: String = "Dipinjam"
    var denda: Double = 0.0

    fun prosesPinjam() {
        buku.kurangiStok(1)
    }

    fun prosesKembali(tglDikembalikan: LocalDate) {
        tglKembali = tglDikembalikan
        buku.tambahStok(1)
        status = "Dikembalikan"
    }
}
