class Pustakawan(
    val idPustakawan: String,
    val nama: String,
    private val username: String,
    private val kataSandi: String
) {
    private val daftarBuku = mutableListOf<Buku>()

    fun login(usernameInput: String, passwordInput: String): Boolean {
        if (usernameInput == username && passwordInput == kataSandi) {
            println("Pustakawan $nama ($idPustakawan) berhasil masuk ke sistem.")
            return true
        }
        println("Login gagal. Username atau password salah.")
        return false
    }

    fun tambahBuku(buku: Buku) {
        daftarBuku.add(buku)
        println("Buku '${buku.judul}' berhasil ditambahkan ke perpustakaan.")
    }

    fun hapusBuku(idBuku: String) {
        val dihapus = daftarBuku.removeIf { it.idBuku == idBuku }
        if (dihapus) {
            println("Buku dengan ID $idBuku berhasil dihapus.")
        } else {
            println("Penghapusan gagal: Buku dengan ID $idBuku tidak ditemukan.")
        }
    }

    fun tampilkanDaftarBuku() {
        println("\n=== Daftar Koleksi Buku ===")
        if (daftarBuku.isEmpty()) {
            println("Belum ada buku di perpustakaan.")
            return
        }
        for (buku in daftarBuku) {
            println(buku.getInfoBuku())
        }
        println("===========================")
    }
}
