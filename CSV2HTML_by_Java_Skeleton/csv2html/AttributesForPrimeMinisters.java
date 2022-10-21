package csv2html;

import java.io.File;

/**
 * 属性リスト：総理大臣の情報テーブルを入出力する際の属性情報を記憶。
 */
public class AttributesForPrimeMinisters extends Attributes
{
	/**
	 * 入力用("input")または出力用("output")で総理大臣の属性リストを作成するコンストラクタ。
	 * @param aString 入力用("input")または出力用("output")
	 */
	public AttributesForPrimeMinisters(String aString)
	{
		super();

		if (aString.compareTo("input") == 0)
		{
			String[] aCollection = new String[] { "no", "order", "name", "kana", "period", "school", "party", "place", "image", "thumbnail" };
			for (String each : aCollection) { this.keys().add(each); this.names().add(new String()); }
		}
		if (aString.compareTo("output") == 0)
		{
			String[] aCollection = new String[] { "no", "order", "name", "kana", "period", "days", "school", "party", "place", "image" };
			for (String each : aCollection) { this.keys().add(each); this.names().add(new String()); }
		}

		return;
	}

	/**
	 * 標題文字列を応答する。
	 * @return 標題文字列
	 */
	public String captionString()
	{
		return "総理大臣";
	}

	/**
	 * 総理大臣のページのためのディレクトリを応答する。
	 * @return 総理大臣のページのためのディレクトリ
	 */
	public String baseDirectory()
	{
		return this.baseDirectory("PrimeMinisters");
	}

	/**
	 * 総理大臣の情報の在処(URL)を文字列で応答する。
	 * @return 総理大臣の情報の在処の文字列
	 */
	public String baseUrl()
	{
		return "http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/VisualWorks/CSV2HTML/PrimeMinisters/";
	}

	/**
	 * 総理大臣の情報を記したCSVファイルの在処(URL)を文字列で応答する。
	 * @return 情報を記したCSVファイル文字列
	 */
	public String csvUrl()
	{
		return this.baseUrl() + "PrimeMinisters.csv";
		// return this.baseUrl() + "PrimeMinisters2.csv";
	}

	/**
	 * 政党のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfParty()
	{
		return this.indexOf("party");
	}

	/**
	 * 出身地のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfPlace()
	{
		return this.indexOf("place");
	}

	/**
	 * 代のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfOrder()
	{
		return this.indexOf("order");
	}

	/**
	 * 出身校のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfSchool()
	{
		return this.indexOf("school");
	}

	/**
	 * タイトル文字列を応答する。
	 * @return タイトル文字列
	 */
	public String titleString()
	{
		return "Prime Ministers";
	}
}
