#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
ソースコードディストリビューション（sdist）のための設え（しつらえ：setup）です。
$ python setup.py sdist
"""

__author__ = 'AOKI Atsushi'
__version__ = '1.0.7'
__date__ = '2021/01/10 (Created: 2016/01/24)'

from distutils.core import setup
import os
import platform
import re

setup( \
	name=re.sub(r"\-[0-9]+\.[0-9]+\.[0-9]+$", "", os.path.basename(os.getcwd())), \
	version=__version__, \
	description='CSV2HTML written by Python ' + '.'.join(platform.python_version_tuple()), \
	url='http://www.cc.kyoto-su.ac.jp/~atsushi/', \
	author=__author__, \
	author_email='atsushi@cc.kyoto-su.ac.jp', \
	license='The BSD 2-Clause License', \
	long_description='総理大臣ページや徳川幕府ページを生成します。', \
	platforms='macOS ' + platform.mac_ver()[0], \
	packages=['csv2html'], \
)
