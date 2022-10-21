#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

import datetime
import os
import shutil

class Attributes:
	"""属性リスト：情報テーブルを入出力する際の属性情報を記憶。"""

	# ベースとなるディレクトリ（ページを生成するためのフォルダ）を記憶するクラス変数
	__BASE_DIRECTORY = None

	def __init__(self, kind_string):
		"""入力用("input")または出力用("output")で属性リストを作成するコンストラクタ。"""

		super().__init__()

		self._keys = ''
		self._names = ''
		(lambda x: x)(kind_string)

	def __str__(self):
		"""自分自身を文字列にして、それを応答する。"""

		string = '['
		for index in range(0, len(self._keys)):
			if index != 0:
				string += ', '
			string += self._keys[index] + ':' + str(self._names[index])
		string += ']'

		return string

	@classmethod
	def base_directory(cls, kind_string):
		"""ベースとなるディレクトリ（ページを生成するためのフォルダ）を応答する。"""

		# ベースとなるディレクトリ（ページを生成するためのフォルダ）の記憶が
		# シングルトン（1回だけ）であることを保証する。
		if cls.__BASE_DIRECTORY is not None:
			return cls.__BASE_DIRECTORY

		# ホームディレクトリのデスクトップの直下のディレクトリに、
		# 生成するページを格納するためのディレクトリを作成する。
		# すでに存在すれば、当該ディレクトリを消して、新たに作り、
		# 存在しなければ、当該ディレクトリを作成する。
		home_directory = os.environ['HOME']
		date_string = datetime.date.today().strftime("%Y%m%d")
		# time_string = datetime.datetime.now().strftime("%H%M%S")
		base_directory = home_directory + os.sep + 'Desktop' + os.sep + 'CSV2HTML' + '_' + kind_string + '_' + date_string
		if os.path.isdir(base_directory):
			shutil.rmtree(base_directory)
		os.makedirs(base_directory)

		cls.__BASE_DIRECTORY = base_directory

		return cls.__BASE_DIRECTORY

	@classmethod
	def flush_base_directory(cls):
		"""ベースとなるディレクトリの記憶を水に流す。"""

		cls.__BASE_DIRECTORY = None

	@classmethod
	def index_html(cls):
		"""インデックスページの名前（ファイル名：通常はindex.html）を応答する。"""

		return 'index.html'

	def keys(self):
		"""キー群を応答する。"""

		return self._keys

	def names(self):
		"""名前群を応答する。"""

		return self._names

class AttributesForPrimeMinisters(Attributes):
	"""属性リスト：総理大臣の情報テーブルを入出力する際の属性情報を記憶。"""

	def __init__(self, kind_string):
		"""入力用("input")または出力用("output")で属性リストを作成するコンストラクタ。"""

		super().__init__(kind_string)

		if kind_string == 'input':
			self._keys = ["no", "order", "name", "kana", "period", "school", "party", "place", "image", "thumbnail"]
		if kind_string == 'output':
			self._keys = ["no", "order", "name", "kana", "period", "days", "school", "party", "place", "image"]
		self._names = [None] * len(self._keys)

	@classmethod
	def base_directory(cls, *ignore):
		"""ベースとなるディレクトリ（ページを生成するためのフォルダ）を応答する。"""

		(lambda x: x)(ignore) # NOP
		return super().base_directory('PrimeMinisters')

	@classmethod
	def base_url(cls):
		"""CSVファイルの在り処をURLで応答する。"""

		return 'http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/VisualWorks/CSV2HTML/PrimeMinisters/'

	@classmethod
	def caption_string(cls):
		"""ページの題目文字列を応答する。"""

		return '総理大臣'

	@classmethod
	def csv_url(cls):
		"""CSVファイルをURLで応答する。"""

		# return cls.base_url() + 'PrimeMinisters.csv'
		return cls.base_url() + 'PrimeMinisters2.csv'

	@classmethod
	def title_string(cls):
		"""ページのタイトル文字列を応答する。"""

		return 'Prime Ministers'

class AttributesForTokugawaShogunate(Attributes):
	"""属性リスト：徳川幕府の情報テーブルを入出力する際の属性情報を記憶。"""

	def __init__(self, kind_string):
		"""入力用("input")または出力用("output")で属性リストを作成するコンストラクタ。"""

		super().__init__(kind_string)

		if kind_string == 'input':
			self._keys = ["no", "name", "kana", "period", "family", "rank", "image", "thumbnail", "former", "cemetery"]
		if kind_string == 'output':
			self._keys = ["no", "name", "kana", "period", "days", "family", "rank", "image", "former", "cemetery"]
		self._names = [None] * len(self._keys)

	@classmethod
	def base_directory(cls, *ignore):
		"""ベースとなるディレクトリ（ページを生成するためのフォルダ）を応答する。"""

		(lambda x: x)(ignore) # NOP
		return super().base_directory('TokugawaShogunate')

	@classmethod
	def base_url(cls):
		"""CSVファイルの在り処をURLで応答する。"""

		return 'http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/VisualWorks/CSV2HTML/TokugawaShogunate/'

	@classmethod
	def caption_string(cls):
		"""ページの題目文字列を応答する。"""

		return '徳川幕府'

	@classmethod
	def csv_url(cls):
		"""CSVファイルをURLで応答する。"""

		# return cls.base_url() + 'TokugawaShogunate.csv'
		return cls.base_url() + 'TokugawaShogunate2.csv'

	@classmethod
	def title_string(cls):
		"""ページのタイトル文字列を応答する。"""

		return 'Tokugawa Shogunate'
