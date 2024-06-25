import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 任意のオブジェクトを保存・読み込みするクラス.
 */
public class Serializer<T> {
    /**
     * 引数で指定した名前のファイルへオブジェクトを保存します.
     */
    public void writeFile(String filename, T obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj); // オブジェクトをファイルへ書き出す
        } catch (NotSerializableException e) { // オブジェクトがSerializableインタフェースを実装していない
            e.printStackTrace();
        } catch (IOException e) { // ファイルへの書き込みができない
            e.printStackTrace();
        }
    }

    /**
     * 引数で指定した名前のファイルから読み込んだオブジェクトを返します。
     */
    public T readFile(String filename) {
        T obj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            obj = (T) ois.readObject(); // ファイルからオブジェクトを読み込んでTに型変換する
            return obj;
        } catch (ClassNotFoundException e) { // 読み込んだファイルを型Tのオブジェクトに変換できない
            e.printStackTrace();
        } catch (IOException e) { // ファイルからの読み込みができない
            e.printStackTrace();
        }
        return obj;
    }
}
