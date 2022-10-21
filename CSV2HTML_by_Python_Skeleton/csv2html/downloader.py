#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

# import os
# import shutil
# import urllib.request

from csv2html.io import IO
# from csv2html.reader import Reader

class Downloader(IO):
	"""ダウンローダ：CSVファイル・画像ファイル・サムネイル画像ファイルをダウンロードする。"""

	def __init__(self, input_table):
		"""ダウンローダのコンストラクタ。"""

		super().__init__(input_table)
		(lambda x: x)(input_table) # NOP

	def download_csv(self):
		"""情報を記したCSVファイルをダウンロードする。"""

		(lambda x: x)(self) # NOP

	def download_images(self, image_filenames):
		"""画像ファイル群または縮小画像ファイル群をダウンロードする。"""

		(lambda x: x)(self) # NOP
		(lambda x: x)(image_filenames) # NOP

	def perform(self):
		"""すべて（情報を記したCSVファイル・画像ファイル群・縮小画像ファイル群）をダウンロードする。"""

		(lambda x: x)(self) # NOP
