#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

class Tuple:
	"""タプル：情報テーブルの中の各々のレコード。"""

	def __init__(self, attributes, values):
		"""属性リストと値リストからタプルを作るコンストラクタ。"""

		super().__init__()
		self._attributes = attributes
		self._values = values

	def __str__(self):
		"""自分自身を文字列にして、それを応答する。"""

		keys = self.attributes().keys()
		string = '['
		# for index in range(0, len(keys)):
		for index, _ in enumerate(keys):
			if index != 0:
				string += ', '
			string += keys[index] + ':' + self._values[index]
		string += ']'

		return string

	def attributes(self):
		"""属性リストを応答する。"""

		return self._attributes

	def values(self):
		"""値リストを応答する。"""

		return self._values
