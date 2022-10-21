package csv2html;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import utility.StringUtility;

/**
 * 入出力：リーダ・ダウンローダ・ライタを抽象する。
 */
public abstract class IO extends Object
{
	/**
	 * テーブル（表：スプレッドシート）を記憶するフィールド。
	 */
	private Table table;

	/**
	 * 入出力のコンストラクタ。
	 * @param aTable テーブル
	 */
	public IO(Table aTable)
	{
		super();

		this.table = aTable;

		return;
	}

	/**
	 * 属性リストを応答する。
	 * @return 属性リスト
	 */
	public Attributes attributes()
	{
		return this.table().attributes();
	}

	/**
	 * ファイルやディレクトリを削除するクラスメソッド。
	 * @param aFile ファイルやディレクトリ
	 */
	public static void deleteFileOrDirectory(File aFile)
	{
		if (!aFile.exists()) { return; }
		if (aFile.isFile()) { aFile.delete(); }
		if (aFile.isDirectory())
		{
			File[] files = aFile.listFiles();
			for (File each : files) { IO.deleteFileOrDirectory(each); }
			aFile.delete();
		}

		return;
	}

	/**
	 * 指定された文字列をHTML内に記述できる正式な文字列に変換して応答する。
	 * @param aString 文字列
	 * @return HTML内に記述できる正式な文字列
	 */
	public static String htmlCanonicalString(String aString)
	{
		return null;
	}

	/**
	 * 指定されたファイルからテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param aFile ファイル
	 * @return 行リスト
	 */
	public static List<String> readTextFromFile(File aFile)
	{
		return StringUtility.readTextFromFile(aFile);
	}

	/**
	 * 指定されたファイル文字列からテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param fileString ファイル文字列
	 * @return 行リスト
	 */
	public static List<String> readTextFromFile(String fileString)
	{
		return StringUtility.readTextFromFile(fileString);
	}

	/**
	 * 指定されたURL文字列からテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param urlString URL文字列
	 * @return 行リスト
	 */
	public static List<String> readTextFromURL(String urlString)
	{
		return StringUtility.readTextFromURL(urlString);
	}

	/**
	 * 指定されたURLからテキストを読み込んで、それを行リストにして応答するクラスメソッド。
	 * @param aURL URL
	 * @return 行リスト
	 */
	public static List<String> readTextFromURL(URL aURL)
	{
		return StringUtility.readTextFromURL(aURL);
	}

	/**
	 * 文字列をセパレータで分割したトークン列を応答するクラスメソッド。
	 * @param string 文字列
	 * @param separators セパレータ文字列
	 * @return トークン列
	 */
	public static List<String> splitString(String string, String separators)
	{
		return StringUtility.splitString(string, separators);
	}

	/**
	 * テーブルを応答する。
	 * @return テーブル
	 */
	public Table table()
	{
		return this.table;
	}

	/**
	 * タプル群を応答する。
	 * @return タプル群
	 */
	public List<Tuple> tuples()
	{
		return this.table().tuples();
	}

	/**
	 * 指定された行リストを、指定されたファイルに書き出すクラスメソッド。
	 * @param aCollection 行リスト
	 * @param aFile ファイル
	 */
	public static void writeText(List<String> aCollection, File aFile)
	{
		StringUtility.writeText(aCollection, aFile);

		return;
	}

	/**
	 * 指定された行リストを、指定されたファイル名のファイルに書き出すクラスメソッド。
	 * @param aCollection 行リスト
	 * @param fileString ファイル名
	 */
	public static void writeText(List<String> aCollection, String fileString)
	{
		StringUtility.writeText(aCollection, fileString);

		return;
	}
}
