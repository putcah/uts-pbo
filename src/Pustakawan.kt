class Pustakawan(
    val idPustakawan: String,
    val nama: String,
    private val username: String,
    private val kataSandi: String
) {
    private val daftarBuku = mutableListOf<Buku>()

    fun tambahBuku(buku: Buku) {
        daftarBuku.add(buku)
    }
}
