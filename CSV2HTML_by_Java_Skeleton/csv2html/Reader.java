package csv2html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.util.List;
import utility.StringUtility;

/**
 * リーダ：情報を記したCSVファイルを読み込んでテーブルに仕立て上げる。
 */
public class Reader extends IO {
	/**
	 * リーダのコンストラクタ。
	 *
	 * @param aTable テーブル
	 */
	public Reader(Table aTable) {
		super(aTable);

		return;
	}

	/**
	 * ダウンロードしたCSVファイルを読み込む。
	 */
	public void perform() {
		String urlString = this.attributes().csvUrl();
		String csvFileName = this.attributes().baseDirectory()
				.concat(urlString.substring(urlString.lastIndexOf("/") + 1));

		try {
			FileInputStream fileInputStream = new FileInputStream(csvFileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String aString = null;
			while ((aString = bufferedReader.readLine()) != null) {
				List<String> aList = splitString(aString, ",");

				this.table().add(new Tuple(this.attributes(), aList));
			}

			bufferedReader.close();

		} catch (FileNotFoundException anException) {
			anException.printStackTrace();
		} catch (UnsupportedEncodingException anException) {
			anException.printStackTrace();
		} catch (IOException anException) {
			anException.printStackTrace();
		}
		return;
	}
}
