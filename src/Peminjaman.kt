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

    fun prosesPinjam(): Boolean {
        // Kurangi stok buku saat dipinjam
        if (buku.kurangiStok(1)) {
            return true
        }
        return false
    }

    fun prosesKembali(tglDikembalikan: LocalDate = LocalDate.now()): Boolean {
        if (status == "Dikembalikan") return false

        tglKembali = tglDikembalikan
        buku.tambahStok(1)

        // Batas pinjam 7 hari, denda Rp 1000 per hari keterlambatan
        val batasTenggat = tglPinjam.plusDays(7)
        denda = hitungDenda(tglDikembalikan, batasTenggat)
        status = "Dikembalikan"

        return true
    }

    private fun hitungDenda(kembali: LocalDate, tenggat: LocalDate): Double {
        val hariTelat = tenggat.until(kembali, ChronoUnit.DAYS)
        return if (hariTelat > 0) hariTelat * 1000.0 else 0.0
    }

    fun getDetailPeminjaman(): String {
        return "ID: $idPinjam | Buku: ${buku.judul} | Status: $status | Denda: Rp${denda.toInt()}"
    }
}
