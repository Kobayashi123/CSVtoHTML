#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

import os
# import shutil
import urllib.request
from urllib.error import HTTPError
import traceback

from csv2html.io import IO
from csv2html.reader import Reader

class Downloader(IO):
	"""ダウンローダ：CSVファイル・画像ファイル・サムネイル画像ファイルをダウンロードする。"""

	def __init__(self, input_table):
		"""ダウンローダのコンストラクタ。"""

		super().__init__(input_table)

	def download_csv(self):
		"""情報を記したCSVファイルをダウンロードする。"""
		url_string = self.attributes().csv_url()
		csv_file = os.path.join(self.attributes().base_directory(), url_string.split('/')[-1])

		try:
			urllib.request.urlretrieve(url_string, csv_file)
		except HTTPError:
			traceback.print_exc()

	def download_images(self, image_filenames):
		"""画像ファイル群または縮小画像ファイル群をダウンロードする。"""

		url_string = os.path.join(self.attributes().base_url(), image_filenames)
		a_directory = os.path.join(self.attributes().base_directory(), url_string.split('/')[-2])
		a_file = os.path.join(a_directory, url_string.split('/')[-1])

		if not os.path.exists(a_directory):
			os.makedirs(a_directory)

		try:
			urllib.request.urlretrieve(url_string, a_file)
		except HTTPError:
			traceback.print_exc()

	def perform(self):
		"""すべて（情報を記したCSVファイル・画像ファイル群・縮小画像ファイル群）をダウンロードする。"""

		self.download_csv()

		Reader(self._table).perform()

		image_key = self.attributes().keys().index('image')
		thumbnail_key = self.attributes().keys().index('thumbnail')
		for a_tuple in self.tuples():
			self.download_images(a_tuple.values()[image_key])
			self.download_images(a_tuple.values()[thumbnail_key])