#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""CSV2HTML：総理大臣と徳川幕府の情報「CSV」からWebページ「HTML」を生成。"""
__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/01)'

import datetime
import os
import textwrap

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

		body = textwrap.indent(textwrap.dedent(f"""\
			<body>
				<div class=\"belt\">
					<h2>{self.attributes().caption_string()}</h2>
				</div>
				<table class=\"belt\" summary=\"table\">
					<tbody>
						<tr>
							<td>
								<table class=\"content\" summary=\"table\">
									<tbody>
			"""), '	')

		body += "								<tr>\n"
		# 属性リストを書き出す
		for a_string in self.table().attributes().return_names():
			body += f"									<td class=\"center-pink\"><strong>{a_string}</strong></td>\n"
		body += "								</tr>\n"

		# タプル群を書き出す
		color = True
		for a_tuple in self.table().tuples():
			if(color):
				a_stereotyped_phrase = "									<td class=\"center-blue\">"
				color = False
			else:
				a_stereotyped_phrase = "									<td class=\"center-yellow\">"
				color = True

			body += "								<tr>\n"
			for a_string in a_tuple.values():
				body += a_stereotyped_phrase
				body += a_string
				body += "</td>\n"
			body += "								</tr>\n"

		body += textwrap.indent(textwrap.dedent("""\
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		"""), '		')

		file.write(body)

	def write_footer(self, file):
		"""フッタを書き出す。"""

		footer = textwrap.dedent(f"""\
				<hr>
				<div class="right-small">Created by csv2html.Translator on {datetime.datetime.now().isoformat(' ', 'seconds')} </div>
			</body>
		</html>
		""")
		file.write(footer)

	def write_header(self, file):
		"""ヘッダを書き出す。"""

		doctype = textwrap.dedent("""\
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<html lang="ja">
				<head>
			""")
		meta = textwrap.indent(textwrap.dedent("""\
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<meta http-equiv="Content-Style-Type" content="text/css">
			<meta http-equiv="Content-Script-Type" content="text/javascript">
			<meta name="keywords" content="Smalltalk,Oriented,Programming">
			<meta name="description" content="Prime Ministers">
			<meta name="author" content="AOKI Atsushi">
			"""), '		')
		link = textwrap.indent(textwrap.dedent("""\
			<link rev="made" href="http://www.cc.kyoto-su.ac.jp/~atsushi/">
			<link rel="index" href="index.html">
			"""), '		')
		css = textwrap.indent(textwrap.dedent("""\
			<style type="text/css">
				<!--
				body {
					background-color : #ffffff;
					margin : 20px;
					padding : 10px;
					font-family : serif;
					font-size : 10pt;
				}
				a {
					text-decoration : underline;
					color : #000000;
				}
				a:link {
					background-color : #ffddbb;
				}
				a:visited {
					background-color : #ccffcc;
				}
				a:hover {
					background-color : #dddddd;
				}
				a:active {
					background-color : #dddddd;
				}
				div.belt {
					background-color : #eeeeee;
					padding : 0px 4px;
				}
				div.right-small {
					text-align : right;
					font-size : 8pt;
				}
				img.borderless {
					border-width : 0px;
					vertical-align : middle;
				}
				table.belt {
					border-style : solid;
					border-width : 0px;
					border-color : #000000;
					background-color : #ffffff;
					padding : 0px 0px;
					width : 100%;
				}
				table.content {
					border-style : solid;
					border-width : 0px;
					border-color : #000000;
					padding : 2px 2px;
				}
				td.center-blue {
					padding : 2px 2px;
					text-align : center;
					background-color : #ddeeff;
				}
				td.center-pink {
					padding : 2px 2px;
					text-align : center;
					background-color : #ffddee;
				}
				td.center-yellow {
					padding : 2px 2px;
					text-align : center;
					background-color : #ffffcc;
				}
				-->
			</style>
			"""), '		')
		title = textwrap.indent(textwrap.dedent(f"""\
				<title>
					{self.attributes().title_string()}
				</title>
			</head>
			"""), '	')
		file.write(doctype + meta + link + css + title)
