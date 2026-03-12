// Bu sinif verilen veriyi hash degerine donusturur.
// HyperLogLog algoritmasi verinin kendisiyle degil hash degeriyle calisir.

public class HashFunction {

    public static int hash(String deger) {

        // Java'nin hashCode fonksiyonu kullaniliyor
        int h = deger.hashCode();

        // Negatif degerleri pozitife cevirmek icin bit maskesi
        return h & 0x7fffffff;
    }

}