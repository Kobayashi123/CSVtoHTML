package csv2html;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import utility.ImageUtility;

/**
 * 表：情報テーブル。
 */
public class Table extends Object
{
	/**
	 * 属性リストを記憶するフィールド。
	 */
	private Attributes attributes;

	/**
	 * タプル群を記憶するフィールド。
	 */
	private List<Tuple> tuples;

	/**
	 * 画像群を記憶するフィールド。
	 */
	private List<BufferedImage> images;

	/**
	 * サムネイル画像群を記憶するフィールド。
	 */
	private List<BufferedImage> thumbnails;

	/**
	 * テーブルのコンストラクタ。
	 * @param instanceOfAttributes 属性リスト
	 */
	public Table(Attributes instanceOfAttributes)
	{
		super();

		this.attributes = instanceOfAttributes;
		this.tuples = null;
		this.images = null;
		this.thumbnails = null;

		return;
	}

	/**
	 * タプルを追加する。
	 * @param aTuple タプル
	 */
	public void add(Tuple aTuple)
	{
		this.tuples().add(aTuple);

		return;
	}

	/**
	 * 属性リストを応答する。
	 * @return 属性リスト
	 */
	public Attributes attributes()
	{
		return this.attributes;
	}

	/**
	 * 属性リストを設定する。
	 * @param instanceOfAttributes 属性リスト
	 */
	public void attributes(Attributes instanceOfAttributes)
	{
		this.attributes = instanceOfAttributes;

		return;
	}

	/**
	 * 画像群を応答する。
	 * @return 画像群
	 */
	public List<BufferedImage> images()
	{
		if (this.images != null) { return this.images; }
		this.images = new ArrayList<BufferedImage>();
		for (Tuple aTuple : this.tuples())
		{
			String aString = aTuple.values().get(aTuple.attributes().indexOfImage());
			BufferedImage anImage = this.picture(aString);
			this.images.add(anImage);
		}

		return this.images;
	}

	/**
	 * 画像またはサムネイル画像の文字列を受けとって当該画像を応答する。
	 * @param aString 画像またはサムネイル画像の文字列
	 * @return 画像
	 */
	private BufferedImage picture(String aString)
	{
		return null;
	}

	/**
	 * サムネイル画像群を応答する。
	 * @return サムネイル画像群
	 */
	public List<BufferedImage> thumbnails()
	{
		if (thumbnails != null) { return this.thumbnails; }
		this.thumbnails = new ArrayList<BufferedImage>();
		for (Tuple aTuple : this.tuples())
		{
			String aString = aTuple.values().get(aTuple.attributes().indexOfThumbnail());
			BufferedImage anImage = this.picture(aString);
			this.thumbnails.add(anImage);
		}

		return this.thumbnails;
	}

	/**
	 * 自分自身を文字列にして、それを応答する。
	 * @return 自分自身の文字列
	 */
	public String toString()
	{
		StringBuffer aBuffer = new StringBuffer();
		Class<?> aClass = this.getClass();
		aBuffer.append(aClass.getName());
		aBuffer.append("[");
		aBuffer.append(this.attributes());
		for (Tuple aTuple : this.tuples())
		{
			aBuffer.append(",");
			aBuffer.append(aTuple);
		}
		aBuffer.append("]");

		return aBuffer.toString();
	}

	/**
	 * タプル群を応答する。
	 * @return タプル群
	 */
	public List<Tuple> tuples()
	{
		if (this.tuples != null) { return this.tuples; }
		this.tuples = new ArrayList<Tuple>();

		return this.tuples;
	}
}
