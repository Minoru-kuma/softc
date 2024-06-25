import softc.Bookshelf;
import softc.LookupFrame;

/**
 *  総合演習課題のGUIアプリケーションを起動するためのクラス.
 */
public class MyBookshelf {
    public static void main(String[] args) {
        LookupFrame frame = new LookupFrame("私の本棚");
        Bookshelf bs = load("bookshelf.data");

        frame.setup(bs);
        frame.setVisible(true);
    }

    /**
     * シリアライズされた本のリストのオブジェクトを読み込みます.
     */
    private static Bookshelf load(String filename) {
        Serializer<Bookshelf> se = new Serializer<>();
        Bookshelf bs = se.readFile(filename);
        return bs;
    }
}
