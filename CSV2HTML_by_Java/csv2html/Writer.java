package csv2html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
	 * ボディを書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeTableBodyOn(BufferedWriter aWriter) {

		try {
			aWriter.write("<body>\n");
			aWriter.write("<div class=\"belt\">\n");
			aWriter.write("<h2>" + this.attributes().captionString() + "</h2>\n");
			aWriter.write("</div>\n");
			aWriter.write("<table class=\"belt\" summary=\"table\">\n");
			aWriter.write("	<tbody>\n");
			aWriter.write("		<tr>\n");
			aWriter.write("			<td>\n");
			aWriter.write("				<table class=\"content\" summary=\"table\">\n");
			aWriter.write("					<tbody>\n");

			this.writeAttributesOn(aWriter);
			this.writeTuplesOn(aWriter);

			aWriter.write("					</tbody>\n");
			aWriter.write("				</table>\n");
			aWriter.write("			</td>\n");
			aWriter.write("		</tr>\n");
			aWriter.write("	</tbody>\n");
			aWriter.write("</table>\n");

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
		String aTab = "						";
		String aStereotypedPhrase = "	<td class=\"center-pink\"><strong>";
		try {
			aWriter.write(aTab + "<tr>");
			aWriter.newLine();

			for (String aString : this.table().attributes().names()) {
				aWriter.write(aTab + aStereotypedPhrase + aString);
				aWriter.write("</strong></td>");
				aWriter.newLine();
			}
			aWriter.write(aTab + "</tr>");
			aWriter.newLine();
		} catch (IOException anException) {
			anException.printStackTrace();
		}

		return;
	}

	/**
	 * タプル群を書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeTuplesOn(BufferedWriter aWriter) {

		String aTab = "						";
		String aStereotypedPhrase;

		try {

			boolean color = true;
			for (Tuple aTuple : this.table().tuples()) {
				aWriter.write(aTab + "<tr>");
				aWriter.newLine();

				if (color) {
					aStereotypedPhrase = "	<td class=\"center-blue\">";
					color = false;
				} else {
					aStereotypedPhrase = "	<td class=\"center-yellow\">";
					color = true;
				}

				for (String aString : aTuple.values()) {
					aWriter.write(aTab + aStereotypedPhrase + aString);
					aWriter.write("</td>");
					aWriter.newLine();
				}
				aWriter.write(aTab + "</tr>");
				aWriter.newLine();
			}
		} catch (Exception anException) {
			anException.printStackTrace();
		}

		return;
	}

	/**
	 * フッタを書き出す。
	 *
	 * @param aWriter ライタ
	 */
	public void writeFooterOn(BufferedWriter aWriter) {
		SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar aCalendar = Calendar.getInstance();
		String footer = """
				<hr>
				<div class="right-small">Created by csv2html.Translator on %s</div>
				</body>
				</html>

				""".formatted(aSimpleDateFormat.format(aCalendar.getTime()));

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
		String doctype = """
				<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
				<html lang="ja">
				<head>
				""";
		String meta = """
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<meta http-equiv="Content-Style-Type" content="text/css">
				<meta http-equiv="Content-Script-Type" content="text/javascript">
				<meta name="keywords" content="Smalltalk,Oriented,Programming">
				<meta name="description" content="Prime Ministers">
				<meta name="author" content="AOKI Atsushi">
				""";
		String link = """
				<link rev="made" href="http://www.cc.kyoto-su.ac.jp/~atsushi/">
				<link rel="index" href="index.html">
				""";
		String css = """
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
				""";
		String title = """
				<title>%s</title>
				</head>
				""".formatted(this.attributes().titleString());

		try {
			aWriter.write(doctype + meta + link + css + title);
		} catch (IOException anException) {
			anException.printStackTrace();
		}
		return;
	}
}
