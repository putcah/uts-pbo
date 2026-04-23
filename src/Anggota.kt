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
        if (jumlahPinjam >= 3) {
            println("Peminjaman gagal: $nama sudah meminjam batas maksimal 3 buku.")
            return false
        }

        if (hasDenda) {
            println("Peminjaman gagal: $nama masih memiliki denda yang belum dibayar.")
            return false
        }

        if (!buku.isTersedia) {
            println("Peminjaman gagal: Stok buku '${buku.judul}' sedang kosong.")
            return false
        }

        val idPinjam = "P-$idAnggota-${riwayatPeminjaman.size + 1}"
        val peminjaman = Peminjaman(idPinjam, buku, tglPinjam)

        if (peminjaman.prosesPinjam()) {
            riwayatPeminjaman.add(peminjaman)
            jumlahPinjam++
            println("$nama berhasil meminjam buku '${buku.judul}'.")
            return true
        }

        return false
    }

    fun kembalikanBuku(buku: Buku, tglKembali: LocalDate = LocalDate.now()): Boolean {
        val peminjaman = riwayatPeminjaman.find { it.buku.idBuku == buku.idBuku && it.status == "Dipinjam" }

        if (peminjaman != null) {
            if (peminjaman.prosesKembali(tglKembali)) {
                jumlahPinjam--

                if (peminjaman.denda > 0) {
                    totalDenda += peminjaman.denda
                    hasDenda = true
                    println("$nama mengembalikan buku terlambat. Dikenakan denda: Rp${peminjaman.denda.toInt()}")
                } else {
                    println("$nama mengembalikan buku '${peminjaman.buku.judul}' tepat waktu.")
                }
                return true
            }
        } else {
            println("Gagal: Buku '${buku.judul}' tidak sedang dipinjam oleh $nama.")
        }
        return false
    }

    fun bayarDenda(nominal: Double): Boolean {
        if (!hasDenda || totalDenda == 0.0) {
            println("$nama tidak memiliki tanggungan denda.")
            return false
        }

        if (nominal < totalDenda) {
            println("Pembayaran gagal. Uang tidak cukup. Total denda: Rp${totalDenda.toInt()}")
            return false
        }

        println("$nama berhasil melunasi denda sebesar Rp${totalDenda.toInt()}")
        totalDenda = 0.0
        hasDenda = false
        return true
    }

    fun tampilkanInfo(): String {
        val dendaInfo = if (hasDenda) "Ya (Rp${totalDenda.toInt()})" else "Tidak"
        return "Anggota: $nama ($noTelp) | Buku dipinjam: $jumlahPinjam/3 | Denda: $dendaInfo"
    }
}
