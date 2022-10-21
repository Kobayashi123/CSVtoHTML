package csv2html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import utility.StringUtility;

/**
 * ライタ：情報のテーブルをHTMLページとして書き出す。
 */
public class Writer extends IO
{
	/**
	 * ライタのコンストラクタ。
	 * @param aTable テーブル
	 */
	public Writer(Table aTable)
	{
		super(aTable);

		return;
	}

	/**
	 * HTMLページを基にするテーブルからインデックスファイル(index.html)に書き出す。
	 */
	public void perform()
	{
		try
		{
			Attributes attributes = this.attributes();
			String fileStringOfHTML = attributes.baseDirectory() + attributes.indexHTML();
			File aFile = new File(fileStringOfHTML);
			FileOutputStream outputStream = new FileOutputStream(aFile);
			OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream, StringUtility.encodingSymbol());
			BufferedWriter aWriter = new BufferedWriter(outputWriter);

			this.writeHeaderOn(aWriter);
			this.writeTableBodyOn(aWriter);
			this.writeFooterOn(aWriter);

			aWriter.close();
		}
		catch (UnsupportedEncodingException | FileNotFoundException anException) { anException.printStackTrace(); }
		catch (IOException anException) { anException.printStackTrace(); }

		return;
	}

	/**
	 * 属性リストを書き出す。
	 * @param aWriter ライタ
	 */
	public void writeAttributesOn(BufferedWriter aWriter)
	{
		return;
	}

	/**
	 * フッタを書き出す。
	 * @param aWriter ライタ
	 */
	public void writeFooterOn(BufferedWriter aWriter)
	{
		return;
	}

	/**
	 * ヘッダを書き出す。
	 * @param aWriter ライタ
	 */
	public void writeHeaderOn(BufferedWriter aWriter)
	{
		return;
	}

	/**
	 * ボディを書き出す。
	 * @param aWriter ライタ
	 */
	public void writeTableBodyOn(BufferedWriter aWriter)
	{
		this.writeAttributesOn(aWriter);
		this.writeTuplesOn(aWriter);

		return;
	}

	/**
	 * タプル群を書き出す。
	 * @param aWriter ライタ
	 */
	public void writeTuplesOn(BufferedWriter aWriter)
	{
		return;
	}
}
