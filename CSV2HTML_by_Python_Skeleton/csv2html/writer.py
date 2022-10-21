#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

# import datetime
import os

from csv2html.io import IO

# pylint: disable=R0201
# R0201: Method could be a function (no-self-use)

class Writer(IO):
	"""ライタ：情報のテーブルをHTMLページとして書き出す。"""

	def __init__(self, output_table):
		"""ライタのコンストラクタ。HTMLページを基にするテーブルを受け取る。"""

		super().__init__(output_table)
		(lambda x: x)(output_table) # NOP

	def perform(self):
		"""HTMLページを基にするテーブルから、インデックスファイル(index_html)に書き出す。"""

		class_attributes = self.attributes().__class__
		base_directory = class_attributes.base_directory()
		index_html = class_attributes.index_html()

		html_filename = os.path.join(base_directory, index_html)
		with open(html_filename, 'w', encoding='utf-8') as a_file:
			self.write_header(a_file)
			self.write_body(a_file)
			self.write_footer(a_file)

	def write_body(self, file):
		"""ボディを書き出す。つまり、属性リストを書き出し、タプル群を書き出す。"""

		(lambda x: x)(file) # NOP

	def write_footer(self, file):
		"""フッタを書き出す。"""

		(lambda x: x)(file) # NOP

	def write_header(self, file):
		"""ヘッダを書き出す。"""

		(lambda x: x)(file) # NOP
