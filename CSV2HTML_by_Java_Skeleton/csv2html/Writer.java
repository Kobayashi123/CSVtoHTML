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
public class Writer extends IO {
	/**
	 * ライタのコンストラクタ。
	 *
	 * @param aTable テーブル
	 */
	public Writer(Table aTable) {
		super(aTable);

		return;
	}

	/**
	 * HTMLページを基にするテーブルからインデックスファイル(index.html)に書き出す。
	 */
	public void perform() {
		try {
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
		} catch (UnsupportedEncodingException | FileNotFoundException anException) {
			anException.printStackTrace();
		} catch (IOException anException) {
			anException.printStackTrace();
		}

		return;
	}

	/**
	 * 属性リストを書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeAttributesOn(BufferedWriter aWriter) {
		return;
	}

	/**
	 * フッタを書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeFooterOn(BufferedWriter aWriter) {
		String footer = """
				<hr>
				<div class="right-small">Created by csv2html.Translator on 2021/10/21 at 13:04:23</div>
				</body>
				</html>

				""";

		try {
			aWriter.write(footer);
		} catch (IOException anException) {
			anException.printStackTrace();
		}
		return;
	}

	/**
	 * ヘッダを書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeHeaderOn(BufferedWriter aWriter) {
		String header = """
				<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
				<html lang="ja">
				<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<meta http-equiv="Content-Style-Type" content="text/css">
				<meta http-equiv="Content-Script-Type" content="text/javascript">
				<meta name="keywords" content="Smalltalk,Oriented,Programming">
				<meta name="description" content="Prime Ministers">
				<meta name="author" content="AOKI Atsushi">
				<link rev="made" href="http://www.cc.kyoto-su.ac.jp/~atsushi/">
				<link rel="index" href="index.html">
				<style type="text/css">
				<!--
				body {
					background-color : #ffffff;
					margin : 20px;
					padding : 10px;
					font-family : serif;
					font-size : 10pt;
				}
				a {
					text-decoration : underline;
					color : #000000;
				}
				a:link {
					background-color : #ffddbb;
				}
				a:visited {
					background-color : #ccffcc;
				}
				a:hover {
					background-color : #dddddd;
				}
				a:active {
					background-color : #dddddd;
				}
				div.belt {
					background-color : #eeeeee;
					padding : 0px 4px;
				}
				div.right-small {
					text-align : right;
					font-size : 8pt;
				}
				img.borderless {
					border-width : 0px;
					vertical-align : middle;
				}
				table.belt {
					border-style : solid;
					border-width : 0px;
					border-color : #000000;
					background-color : #ffffff;
					padding : 0px 0px;
					width : 100%;
				}
				table.content {
					border-style : solid;
					border-width : 0px;
					border-color : #000000;
					padding : 2px 2px;
				}
				td.center-blue {
					padding : 2px 2px;
					text-align : center;
					background-color : #ddeeff;
				}
				td.center-pink {
					padding : 2px 2px;
					text-align : center;
					background-color : #ffddee;
				}
				td.center-yellow {
					padding : 2px 2px;
					text-align : center;
					background-color : #ffffcc;
				}
				-->
				</style>
				<title>Prime Ministers</title>
				</head>
				""";

		try {
			aWriter.write(header);
		} catch (IOException anException) {
			anException.printStackTrace();
		}
		return;
	}

	/**
	 * ボディを書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeTableBodyOn(BufferedWriter aWriter) {
		this.writeAttributesOn(aWriter);
		this.writeTuplesOn(aWriter);

		return;
	}

	/**
	 * タプル群を書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeTuplesOn(BufferedWriter aWriter) {
		return;
	}
}
