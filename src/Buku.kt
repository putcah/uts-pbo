class Buku(
    val idBuku: String,
    val judul: String,
    val penulis: String,
    val penerbit: String,
    val tahunTerbit: Int,
    stokAwal: Int
) {
    var stok: Int = stokAwal
        private set

    val isTersedia: Boolean
        get() = stok > 0

    fun kurangiStok(jumlah: Int): Boolean {
        if (jumlah <= 0 || stok < jumlah) {
            return false
        }
        stok -= jumlah
        return true
    }

    fun tambahStok(jumlah: Int) {
        if (jumlah > 0) {
            stok += jumlah
        }
    }

    fun getInfoBuku(): String {
        return "[$idBuku] $judul oleh $penulis, $penerbit ($tahunTerbit) - Stok: $stok"
    }
}
