import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Peminjaman(
    val idPinjam: String,
    val buku: Buku,
    val tglPinjam: LocalDate = LocalDate.now()
) {
    var tglKembali: LocalDate? = null
        private set
    var status: String = "Dipinjam"
        private set
    var denda: Double = 0.0
        private set

    fun prosesPinjam(): Boolean = buku.kurangiStok(1)

    fun prosesKembali(tglDikembalikan: LocalDate = LocalDate.now()): Boolean {
        if (status == "Dikembalikan") return false
        tglKembali = tglDikembalikan
        buku.tambahStok(1)
        val batasTenggat = tglPinjam.plusDays(7)
        val hariTelat = batasTenggat.until(tglDikembalikan, ChronoUnit.DAYS)
        denda = if (hariTelat > 0) hariTelat * 1000.0 else 0.0
        status = "Dikembalikan"
        return true
    }
}
