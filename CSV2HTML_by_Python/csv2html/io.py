#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

import csv

class IO:
	"""入出力：リーダ・ダウンローダ・ライタを抽象する。"""

	def __init__(self, a_table):
		"""入出力のコンストラクタ。"""

		super().__init__()
		self._table = a_table

	def attributes(self):
		"""属性リストを応答する。"""

		return self.table().attributes()

	def read_csv(self, filename):
		"""指定されたファイルをCSVとして読み込み、行リストを応答する。"""

		a_list = []
		with open(filename, 'r', encoding='utf-8', newline='') as a_file:
			csv_reader = csv.reader(a_file, delimiter=',', quotechar='"')
			for a_line in csv_reader:
				a_list.append(a_line)
		return a_list

	@classmethod
	def html_canonical_string(cls, a_string):
		"""指定された文字列をHTML内に記述できる正式な文字列に変換して応答する。"""

		table = {
			'&'  : '&amp;',
			'>'  : '&gt;',
			'<'  : '&lt;',
			'"'  : '&quot;',
			' '  : '&nbsp;',
			'\t' : '',
			'\r' : '',
			'\n' : '<br>',
			'\f' : '',
		}

		for a_key in table:
			a_string = a_string.replace(a_key, table[a_key])

		return a_string

	def table(self):
		"""テーブルを応答する。"""

		return self._table

	def tuples(self):
		"""タプル群を応答する。"""

		return self.table().tuples()

	def write_csv(self, filename, rows):
		"""指定されたファイルにCSVとして行たち(rows)を書き出す。"""

		with open(filename, 'w', encoding='utf-8') as a_file:
			for a_line in rows:
				a_file.write(a_line)
