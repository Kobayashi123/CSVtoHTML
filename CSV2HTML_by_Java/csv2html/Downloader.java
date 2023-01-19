package csv2html;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.nio.file.Files;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;

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
		String urlString = this.attributes().csvUrl();
		String fileString = this.attributes().baseDirectory()
				.concat(urlString.substring(urlString.lastIndexOf("/") + 1));

		File aFile = new File(fileString);
		if (aFile.exists()) {
			deleteFileOrDirectory(aFile);
		}

		List<String> aList = readTextFromURL(urlString);
		writeText(aList, fileString);

		return;
	}

	/**
	 * 総理大臣の画像群をダウンロードする。
	 */
	public void downloadImages() {
		int indexOfImage = this.attributes().indexOfImage();

		File aDirectory = new File(this.attributes().baseDirectory().concat("images"));
		if (aDirectory.exists()) {
			IO.deleteFileOrDirectory(aDirectory);
		}
		aDirectory.mkdir();

		this.downloadPictures(indexOfImage);

		return;
	}

	/**
	 * 総理大臣の画像群またはサムネイル画像群をダウンロードする。
	 *
	 * @param indexOfPicture 画像のインデックス
	 */
	private void downloadPictures(int indexOfPicture) {
		for (Tuple aTuple : this.tuples()) {
			String imageString = aTuple.values().get(indexOfPicture);

			String urlString = this.attributes().baseUrl().concat(imageString);

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

			String fileString = this.attributes().baseDirectory().concat(imageString);

			File aFile = new File(fileString);
			if (aFile.exists()) {
				IO.deleteFileOrDirectory(aFile);
			}

			String kindString = urlString.substring(urlString.lastIndexOf(".") + 1);
			try {
				ImageIO.write(anImage, kindString, aFile);
			} catch (IOException anException) {
				anException.printStackTrace();
			}
		}

		return;
	}

	/**
	 * 総理大臣の画像群をダウンロードする。
	 */
	public void downloadThumbnails() {
		int indexOfThumbnail = this.attributes().indexOfThumbnail();

		File aDirectory = new File(this.attributes().baseDirectory().concat("thumbnails"));
		if (aDirectory.exists()) {
			IO.deleteFileOrDirectory(aDirectory);
		}
		aDirectory.mkdir();

		this.downloadPictures(indexOfThumbnail);

		return;
	}

	/**
	 * 総理大臣の情報を記したCSVファイルをダウンロードして、画像群やサムネイル画像群もダウンロードする。
	 */
	public void perform() {
		this.downloadCSV();

		Reader aReader = new Reader(this.table());
		aReader.perform();

		this.downloadImages();
		this.downloadThumbnails();

		return;
	}
}
