class Buku(
    val idBuku: String,
    val judul: String,
    val penulis: String,
    val penerbit: String,
    val tahunTerbit: Int,
    var stok: Int
) {
    fun kurangiStok(jumlah: Int) {
        stok -= jumlah
    }
    fun tambahStok(jumlah: Int) {
        stok += jumlah
    }
}
