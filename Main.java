import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

// Kelas dasar untuk menyimpan informasi faktur
class Faktur {
    protected String noFaktur;      // Nomor faktur
    protected String kodeBarang;     // Kode barang
    protected String namaBarang;     // Nama barang
    protected double hargaBarang;    // Harga per unit barang
    protected int jumlahBeli;        // Jumlah barang yang dibeli

    // Konstruktor untuk inisialisasi atribut
    public Faktur(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        this.noFaktur = noFaktur;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
    }

    // Metode untuk menghitung total harga
    public double hitungTotal() {
        return hargaBarang * jumlahBeli;
    }
}

// Kelas turunan dari Faktur yang khusus untuk faktur penjualan
class FakturPenjualan extends Faktur {

    // Konstruktor yang memanggil konstruktor kelas dasar (super)
    public FakturPenjualan(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
    }

    // Metode untuk menampilkan informasi faktur
    public void tampilkanFaktur(String namaKasir) {
        double total = hitungTotal(); // Menghitung total
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

        System.out.println("+----------------------------------------------------+");
        System.out.println("Selamat Datang di Supermarket");
        System.out.println("Tanggal dan Waktu: " + formatter.format(date));
        System.out.println("+----------------------------------------------------+");
        System.out.println("No. Faktur      : " + noFaktur);
        System.out.println("Kode Barang     : " + kodeBarang);
        System.out.println("Nama Barang     : " + namaBarang);
        System.out.println("Harga Barang    : " + hargaBarang);
        System.out.println("Jumlah Beli     : " + jumlahBeli);
        System.out.println("TOTAL           : " + total);
        System.out.println("+----------------------------------------------------+");
        System.out.println("Kasir           : " + namaKasir);
        System.out.println("+----------------------------------------------------+");
    }
}

public class Main {
    private static final String USERNAME = "Fariz"; // Username yang valid
    private static final String PASSWORD = "Jonnius"; // Password yang valid

    // Metode untuk menghasilkan captcha
    private static String generateCaptcha() {
        Random random = new Random();
        int captcha = 1000 + random.nextInt(9000); // Menghasilkan angka 4 digit
        return String.valueOf(captcha);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String captcha = generateCaptcha();
        boolean loginSuccess = false;

        // Proses login
        while (!loginSuccess) {
            try {
                System.out.println("+-----------------------------------------------------+");
                System.out.print("Username : ");
                String username = scanner.nextLine();
                System.out.print("Password : ");
                String password = scanner.nextLine();
                System.out.print("Captcha ( " + captcha + " ) : ");
                String inputCaptcha = scanner.nextLine();

                if (username.equals(USERNAME) && password.equals(PASSWORD) && inputCaptcha.equals(captcha)) {
                    loginSuccess = true;
                    System.out.println("Login berhasil!");
                } else {
                    System.out.println("Login gagal, silakan coba lagi.");
                }
            } catch (Exception e) {
                System.out.println("Kesalahan tidak terduga: " + e.getMessage());
            }
        }

        // Setelah login berhasil, meminta input faktur
        try {
            System.out.print("Masukkan No Faktur: ");
            String noFaktur = scanner.nextLine();
            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = scanner.nextLine();
            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = scanner.nextLine();

            System.out.print("Masukkan Harga Barang: ");
            double hargaBarang = scanner.nextDouble();
            if (hargaBarang < 0) {
                throw new IllegalArgumentException("Harga barang tidak boleh negatif.");
            }

            System.out.print("Masukkan Jumlah Beli: ");
 int jumlahBeli = scanner.nextInt();
            if (jumlahBeli < 0) {
                throw new IllegalArgumentException("Jumlah beli tidak boleh negatif.");
            }

            // Membuat objek FakturPenjualan dan menampilkan faktur
            FakturPenjualan faktur = new FakturPenjualan(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
            faktur.tampilkanFaktur("Kasir");

        } catch (IllegalArgumentException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Kesalahan tidak terduga: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}