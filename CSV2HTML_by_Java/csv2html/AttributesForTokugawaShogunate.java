package csv2html;

import java.io.File;

/**
 * 属性リスト：徳川幕府の情報テーブルを入出力する際の属性情報を記憶。
 */
public class AttributesForTokugawaShogunate extends Attributes
{
	/**
	 * 入力用("input")または出力用("output")で徳川幕府の属性リストを作成するコンストラクタ。
	 * @param aString 入力用("input")または出力用("output")
	 */
	public AttributesForTokugawaShogunate(String aString)
	{
		super();

		if (aString.compareTo("input") == 0)
		{
			String[] aCollection = new String[] { "no", "name", "kana", "period", "family", "rank", "image", "thumbnail", "former", "cemetery" };
			for (String each : aCollection) { this.keys().add(each); this.names().add(new String()); }
		}
		if (aString.compareTo("output") == 0)
		{
			String[] aCollection = new String[] { "no", "name", "kana", "period", "days", "family", "rank", "image", "former", "cemetery" };
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
		return "徳川幕府";
	}

	/**
	 * 徳川幕府のページのためのディレクトリを応答する。
	 * @return 徳川幕府のページのためのディレクトリ
	 */
	public String baseDirectory()
	{
		return this.baseDirectory("TokugawaShogunate");
	}

	/**
	 * 徳川幕府の情報の在処(URL)を文字列で応答する。
	 * @return 徳川幕府の情報の在処の文字列
	 */
	public String baseUrl()
	{
		return "http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/VisualWorks/CSV2HTML/TokugawaShogunate/";
	}

	/**
	 * 徳川幕府の情報を記したCSVファイルの在処(URL)を文字列で応答する。
	 * @return 情報を記したCSVファイル文字列
	 */
	public String csvUrl()
	{
		return this.baseUrl() + "TokugawaShogunate.csv";
		// return this.baseUrl() + "TokugawaShogunate2.csv";
	}

	/**
	 * 墓所のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfCemetery()
	{
		return this.indexOf("cemetery");
	}

	/**
	 * 出身家のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfFamily()
	{
		return this.indexOf("family");
	}

	/**
	 * 院号のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfFormer()
	{
		return this.indexOf("former");
	}

	/**
	 * 官位のインデックスを応答する。
	 * @return インデックス
	 */
	public int indexOfRank()
	{
		return this.indexOf("rank");
	}

	/**
	 * タイトル文字列を応答する。
	 * @return タイトル文字列
	 */
	public String titleString()
	{
		return "Tokugawa Shogunate";
	}
}
