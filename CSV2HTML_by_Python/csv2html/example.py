#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

from csv2html.attributes import AttributesForPrimeMinisters
from csv2html.attributes import AttributesForTokugawaShogunate
from csv2html.translator import Translator

# pylint: disable=R0201,R0903
# R0201: Method could be a function (no-self-use)
# R0903: Too few public methods (1/2) (too-few-public-methods)

class Example:
	"""例題プログラム：CSVファイルをHTMLページへと変換する。"""

	def main(self):
		"""CSVファイルをHTMLページへと変換するメインプログラム。"""

		# 総理大臣と徳川幕府の属性リストのクラス群を作る。
		classes = []
		classes.append(AttributesForPrimeMinisters)
		classes.append(AttributesForTokugawaShogunate)

		for class_attributes in classes:
			Translator.perform(class_attributes)

		return 0

if __name__ == "__main__":
	an_example = Example()
	import sys
	sys.exit(an_example.main())
