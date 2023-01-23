package csv2html;

import java.util.Arrays;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;

/**
 * トランスレータ：CSVファイルをHTMLページへと変換するプログラム。
 */
public class Translator extends Object {
	/**
	 * CSVに由来するテーブルを記憶するフィールド。
	 */
	private Table inputTable;

	/**
	 * HTMLに由来するテーブルを記憶するフィールド。
	 */
	private Table outputTable;

	/**
	 * 属性リストのクラスを指定したトランスレータのコンストラクタ。
	 *
	 * @param classOfAttributes 属性リストのクラス
	 */
	public Translator(Class<? extends Attributes> classOfAttributes) {
		super();

		Attributes.flushBaseDirectory();

		try {
			Constructor<? extends Attributes> aConstructor = classOfAttributes.getConstructor(String.class);

			this.inputTable = new Table(aConstructor.newInstance("input"));
			this.outputTable = new Table(aConstructor.newInstance("output"));
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException anException) {
			anException.printStackTrace();
		}

		return;
	}

	/**
	 * 在位日数を計算して、それを文字列にして応答する。
	 *
	 * @param periodString 在位期間の文字列
	 * @return 在位日数の文字列
	 */
	public String computeNumberOfDays(String periodString) {
		Integer datecal;
		List<String> startEnd = new ArrayList<>();
		String[] spritDate = periodString.split("〜");
		for (String temp : spritDate) {
			startEnd.add(temp);
		}
		if (startEnd.size() == 1) {
			Date date = new Date();
			String nowDate = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(date);
			startEnd.add(nowDate);
		}
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			Date date1 = sdFormat.parse(startEnd.get(0));
			Date date2 = sdFormat.parse(startEnd.get(1));
			datecal = Integer.valueOf(
					String.valueOf(TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS)));
			return String.format("%,d", datecal + 1);
		} catch (ParseException anException) {
			anException.printStackTrace();
		}

		return null;
	}

	/**
	 * サムネイル画像から画像へ飛ぶためのHTML文字列を作成して、それを応答する。
	 *
	 * @param aString 画像の文字列
	 * @param aTuple  タプル
	 * @param no      番号
	 * @return サムネイル画像から画像へ飛ぶためのHTML文字列
	 */
	public String computeStringOfImage(String aString, Tuple aTuple, int no) {
		String anImageString = this.inputTable.attributes().baseDirectory().concat(aString);
		BufferedImage anImage = null;
		try {
			anImage = ImageIO.read(new File(anImageString));
		} catch (Exception anException) {
			anException.printStackTrace();
		}
		String anotherString = "<a name=\"%d\" href=\"%s\" >".formatted(no, aString);
		anotherString = anotherString
				.concat("<img class=\"borderless\" src=\" %s \" width=\"%d\" height=\"%d\" alt=\" %s \">"
						.formatted(aTuple.values().get(this.inputTable.attributes().indexOfThumbnail()),
								anImage.getWidth(), anImage.getHeight(),
								aString.substring(aString.lastIndexOf("/") + 1)));

		return anotherString;
	}

	/**
	 * CSVファイルをHTMLページへ変換する。
	 */
	public void execute() {
		// 必要な情報をダウンロードする。
		Downloader aDownloader = new Downloader(this.inputTable);
		aDownloader.perform();

		// CSVに由来するテーブルをHTMLに由来するテーブルへと変換する。
		System.out.println(this.inputTable);
		this.translate();
		System.out.println(this.outputTable);

		// HTMLに由来するテーブルから書き出す。
		Writer aWriter = new Writer(this.outputTable);
		aWriter.perform();

		// ブラウザを立ち上げて閲覧する。
		try {
			Attributes attributes = this.outputTable.attributes();
			String fileStringOfHTML = attributes.baseDirectory() + attributes.indexHTML();
			ProcessBuilder aProcessBuilder = new ProcessBuilder("open", "-a", "Safari", fileStringOfHTML);
			aProcessBuilder.start();
		} catch (Exception anException) {
			anException.printStackTrace();
		}

		return;
	}

	/**
	 * 属性リストのクラスを受け取って、CSVファイルをHTMLページへと変換するクラスメソッド。
	 *
	 * @param classOfAttributes 属性リストのクラス
	 */
	public static void perform(Class<? extends Attributes> classOfAttributes) {
		// トランスレータのインスタンスを生成する。
		Translator aTranslator = new Translator(classOfAttributes);
		// トランスレータにCSVファイルをHTMLページへ変換するように依頼する。
		aTranslator.execute();

		return;
	}

	/**
	 * CSVファイルを基にしたテーブルから、HTMLページを基にするテーブルに変換する。
	 */
	public void translate() {
		List<String> aList;
		if (this.inputTable.attributes().captionString().equals("総理大臣")) {
			aList = new ArrayList<String>();
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfNo()));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOf("order")));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfName()));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfKana()));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfPeriod()));
			aList.add("在位日数");
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOf("school")));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOf("party")));
			aList.add(this.inputTable.attributes().nameAt((this.inputTable.attributes().indexOf("place"))));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfImage()));
			this.outputTable.attributes().names(aList);

			for (Tuple aTuple : this.inputTable.tuples()) {
				aList = new ArrayList<String>();
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfNo()));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOf("order")));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfName()));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfKana()));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfPeriod()));
				aList.add(this.computeNumberOfDays(aTuple.values().get(this.outputTable.attributes().indexOfPeriod())));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOf("school")));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOf("party")));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOf("place")));
				aList.add(this.computeStringOfImage(
						aTuple.values().get(this.inputTable.attributes().indexOfThumbnail()),
						aTuple, Integer.valueOf(aTuple.values().get(this.inputTable.attributes().indexOfNo()))));
				this.outputTable.add(new Tuple(this.outputTable.attributes(), aList));
			}
		} else {
			aList = new ArrayList<String>();
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfNo()));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfName()));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfKana()));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfPeriod()));
			aList.add("在位日数");
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOf("family")));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOf("rank")));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOfImage()));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOf("former")));
			aList.add(this.inputTable.attributes().nameAt(this.inputTable.attributes().indexOf("cemetery")));
			this.outputTable.attributes().names(aList);

			for (Tuple aTuple : this.inputTable.tuples()) {
				aList = new ArrayList<String>();
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfNo()));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfName()));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfKana()));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOfPeriod()));
				aList.add(this.computeNumberOfDays(aTuple.values().get(this.outputTable.attributes().indexOfPeriod())));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOf("family")));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOf("rank")));
				aList.add(this.computeStringOfImage(
						aTuple.values().get(this.inputTable.attributes().indexOfThumbnail()),
						aTuple, Integer.valueOf(aTuple.values().get(this.inputTable.attributes().indexOfNo()))));
				aList.add(aTuple.values().get(this.inputTable.attributes().indexOf("former")));
				aList.add(aTuple.values().get((this.inputTable.attributes().indexOf("cemetery"))));
				this.outputTable.add(new Tuple(this.outputTable.attributes(), aList));
			}
		}

		return;
	}
}
