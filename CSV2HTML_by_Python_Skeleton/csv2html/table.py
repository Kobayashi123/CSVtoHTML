#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

class Table:
	"""表：情報テーブル。"""

	def __init__(self, kind_string, class_attributes):
		"""テーブルのコンストラクタ。"""

		super().__init__()
		self._attributes = class_attributes(kind_string)
		self._tuples = []

	def __str__(self):
		"""自分自身を文字列にして、それを応答する。"""

		string = '0: ' + str(self.attributes())
		countup_number = 1
		for a_tuple in self._tuples:
			string += '\n' + str(countup_number) + ': ' + str(a_tuple)
			countup_number += 1

		return string

	def add(self, a_tuple):
		"""タプルを追加する。"""

		self._tuples.append(a_tuple)

	def attributes(self):
		"""属性リストを応答する。"""

		return self._attributes

	def image_filenames(self):
		"""画像ファイル群をリストにして応答する。"""

		(lambda x: x)(self) # NOP

	def thumbnail_filenames(self):
		"""縮小画像ファイル群をリストにして応答する。"""

		return (lambda x: x)(self) # answer something


	def tuples(self):
		"""タプル群を応答する。"""

		return self._tuples
