#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

import datetime
# import locale
import os
import os.path
# import re
import subprocess

from PIL import Image

from csv2html.downloader import Downloader
from csv2html.io import IO
from csv2html.table import Table
from csv2html.tuple import Tuple
from csv2html.writer import Writer

# pylint: disable=R0201
# R0201: Method could be a function (no-self-use)

class Translator:
	"""トランスレータ：CSVファイルをHTMLページへと変換するプログラム。"""

	def __init__(self, classOfAttributes):
		"""トランスレータのコンストラクタ。"""

		super().__init__()
		classOfAttributes.flush_base_directory()
		self._input_table = Table('input', classOfAttributes)
		self._output_table = Table('output', classOfAttributes)

	def compute_string_of_days(self, period):
		"""在位日数を計算して、それを文字列にして応答する。"""

		start, end = period.split('〜')
		start = datetime.datetime.strptime(start, '%Y年%m月%d日')
		if end == '':
			end = datetime.datetime.now()
			return f'{(end - start).days:,}'

		end = datetime.datetime.strptime(end, '%Y年%m月%d日')

		return f'{(end - start + datetime.timedelta(days=1)).days:,}'

	def compute_string_of_image(self, a_tuple):
		"""サムネイル画像から画像へ飛ぶためのHTML文字列を作成して、それを応答する。"""

		image_name = a_tuple[1][a_tuple[1].find("/")+1:]
		image = Image.open(self._input_table.attributes().base_directory() + os.sep + a_tuple[2])

		a_string = f'<a name=\"{a_tuple[0]}\" href=\"{a_tuple[1]}\">\
<img class=\"borderless\" src=\"{a_tuple[2]}\" width=\"{image.width}\" height=\"{image.height}\" alt=\"{image_name}\"></a>'

		return a_string

	def execute(self):
		"""CSVファイルをHTMLページへと変換する。"""

		# ダウンローダに必要なファイル群をすべてダウンロードしてもらい、
		# 入力となるテーブルを獲得する。
		a_downloader = Downloader(self._input_table)
		a_downloader.perform()

		# トランスレータに入力となるテーブルを渡して変換してもらい、
		# 出力となるテーブルを獲得する。
		print(self._input_table)
		self.translate()
		print(self._output_table)

		# ライタに出力となるテーブルを渡して、
		# Webページを作成してもらう。
		a_writer = Writer(self._output_table)
		a_writer.perform()

		# 作成したページをウェブブラウザで閲覧する。
		class_attributes = self._output_table.attributes().__class__
		base_directory = class_attributes.base_directory()
		index_html = class_attributes.index_html()
		a_command = "open -a 'Safari' " + base_directory + os.sep + index_html
		subprocess.getoutput(a_command)

	@classmethod
	def perform(cls, class_attributes):
		"""属性リストのクラスを受け取り、CSVファイルをHTMLページへと変換する。"""

		# トランスレータのインスタンスを生成する。
		a_translator = cls(class_attributes)
		# トランスレータにCSVファイルをHTMLページへ変換するように依頼する。
		a_translator.execute()

	def translate(self):
		"""CSVファイルを基にしたテーブルから、HTMLページを基にするテーブルに変換する。"""

		if self._input_table.attributes().caption_string() == '総理大臣':
			a_tuple = (self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("no")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("order")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("name")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("kana")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("period")],
				"在位日数",
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("school")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("party")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("place")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("image")],
				)
			self._output_table.attributes().names(a_tuple)

			for aTuple in self._input_table.tuples():
				image_tuple = (aTuple.values()[self._input_table.attributes().keys().index("no")],
						aTuple.values()[self._input_table.attributes().keys().index("image")],
						aTuple.values()[self._input_table.attributes().keys().index("thumbnail")])
				a_tuple =(
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("no")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("order")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("name")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("kana")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("period")]),
					IO.html_canonical_string(self.compute_string_of_days(aTuple.values()[self._output_table.attributes().keys().index("period")])),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("school")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("party")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("place")]),
					self.compute_string_of_image(image_tuple)
					)
				self._output_table.add(Tuple(self._output_table.attributes(), a_tuple))
		else:
			a_tuple = (
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("no")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("name")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("kana")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("period")],
				"在位日数",
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("family")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("rank")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("image")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("former")],
				self._input_table.attributes().return_names()[self._input_table.attributes().keys().index("cemetery")],
				)
			self._output_table.attributes().names(a_tuple)

			for aTuple in self._input_table.tuples():
				image_tuple = (
					aTuple.values()[self._input_table.attributes().keys().index("no")],
					aTuple.values()[self._input_table.attributes().keys().index("image")],
					aTuple.values()[self._input_table.attributes().keys().index("thumbnail")]
					)
				a_tuple = (
					aTuple.values()[self._input_table.attributes().keys().index("no")],
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("name")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("kana")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("period")]),
					IO.html_canonical_string(self.compute_string_of_days(aTuple.values()[self._output_table.attributes().keys().index("period")])),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("family")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("rank")]),
					self.compute_string_of_image(image_tuple),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("former")]),
					IO.html_canonical_string(aTuple.values()[self._input_table.attributes().keys().index("cemetery")]),
					)
				self._output_table.add(Tuple(self._output_table.attributes(), a_tuple))
