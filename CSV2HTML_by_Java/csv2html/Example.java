package csv2html;

import java.util.ArrayList;
import java.util.List;

/**
 * 例題プログラム：総理大臣と徳川幕府のCSVファイルをHTMLページへと変換する。
 */
public class Example extends Object
{
	/**
	 * CSVファイルをHTMLページへと変換するメインプログラム。
	 * @param arguments 引数文字列群
	 */
	public static void main(String[] arguments)
	{
		// 総理大臣と徳川幕府の属性リストのクラス群を作る。
		List<Class<? extends Attributes>> classes = new ArrayList<Class<? extends Attributes>>();
		classes.add(AttributesForPrimeMinisters.class);
		classes.add(AttributesForTokugawaShogunate.class);

		for (Class<? extends Attributes> classOfAttributes : classes)
		{
			Translator.perform(classOfAttributes);
		}

		return;
	}
}
