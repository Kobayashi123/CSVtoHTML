package csv2html;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import utility.ImageUtility;

/**
 * ダウンローダ：CSVファイル・画像ファイル・サムネイル画像ファイルをダウンロードする。
 */
public class Downloader extends IO {
	/**
	 * ダウンローダのコンストラクタ。
	 *
	 * @param aTable テーブル
	 */
	public Downloader(Table aTable) {
		super(aTable);

		return;
	}

	/**
	 * 総理大臣の情報を記したCSVファイルをダウンロードする。
	 */
	public void downloadCSV() {
		AttributesForPrimeMinisters primeMinister = new AttributesForPrimeMinisters("input");
		String urlString = primeMinister.csvUrl();

		String fileString = urlString.substring(urlString.lastIndexOf("/") + 1);
		fileString = primeMinister.baseDirectory() + fileString;

		deleteFileOrDirectory(new File(fileString));

		List<String> aList = readTextFromURL(urlString);
		writeText(aList, fileString);

		return;
	}

	/**
	 * 総理大臣の画像群をダウンロードする。
	 */
	public void downloadImages() {
		int indexOfImage = this.attributes().indexOfImage();
		this.downloadPictures(indexOfImage);

		return;
	}

	/**
	 * 総理大臣の画像群またはサムネイル画像群をダウンロードする。
	 *
	 * @param indexOfPicture 画像のインデックス
	 */
	private void downloadPictures(int indexOfPicture) {
		AttributesForPrimeMinisters primeMinister = new AttributesForPrimeMinisters("input");
		String urlString = primeMinister.baseUrl() + Integer.valueOf(indexOfPicture).toString();

		URL aURL = null;
		try {
			aURL = new URL(urlString);
		} catch (MalformedURLException anException) {
			anException.printStackTrace();
		}

		BufferedImage anImage = null;
		try {
			anImage = ImageIO.read(aURL);
		} catch (IOException anException) {
			anException.printStackTrace();
		}

		String currentDirectory = System.getProperty("user.dir");
		String dirName = "PrimeMinisters";
		String fileString = urlString.substring(urlString.lastIndexOf("/") + 1);
		fileString = currentDirectory + File.separator + dirName + File.separator + fileString;

		File aFile = new File(fileString);
		deleteFileOrDirectory(aFile);

		String kindString = urlString.substring(urlString.lastIndexOf(".") + 1); // 画像ファイルの拡張子を求めます。
		try {
			ImageIO.write(anImage, kindString, aFile);
		} catch (IOException anException) {
			anException.printStackTrace();
		}

		return;
	}

	/**
	 * 総理大臣の画像群をダウンロードする。
	 */
	public void downloadThumbnails() {
		int indexOfThumbnail = this.attributes().indexOfThumbnail();
		this.downloadPictures(indexOfThumbnail);

		return;
	}

	/**
	 * 総理大臣の情報を記したCSVファイルをダウンロードして、画像群やサムネイル画像群もダウロードする。
	 */
	public void perform() {
		this.downloadCSV();
		this.downloadImages();
		this.downloadThumbnails();
		return;
	}
}
