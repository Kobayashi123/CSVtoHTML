PYTHON	= python
PYDOC	= pydoc
PYCS	= $(shell find . -name "*.pyc")
PYCACHE	= $(shell find . -name "__pycache__")
TARGET	= CSV2HTML.py
PACKAGE	= csv2html
INSTDIR	= CSV2HTML.app/Contents/Resources/Python/
ARCHIVE	= $(shell basename `pwd`)
WORKDIR	= ./
PYLINT	= pylint
LINTRCF	= pylintrc.txt
LINTRST	= pylintresult.txt

all:
	@rm -rf ~/Desktop/CSV2HTML_[A-Za-z]*_[0-9]*

clean:
	@for each in ${PYCS} ; do echo "rm -f $${each}" ; rm -f $${each} ; done
	@for each in ${PYCACHE} ; do echo "rm -f $${each}" ; rm -rf $${each} ; done
	@if [ -e $(INSTDIR) ] ; then echo "rm -f -r $(INSTDIR)" ; rm -f -r $(INSTDIR) ; fi
	@if [ -e $(LINTRST) ] ; then echo "rm -f $(LINTRST)" ; rm -f $(LINTRST) ; fi
	@if [ -e MANIFEST ] ; then echo "rm -f MANIFEST" ; rm -f MANIFEST ; fi
	@find . -name ".DS_Store" -exec rm {} ";" -exec echo rm -f {} ";"

test:
	$(PYTHON) $(TARGET)

install:
	@if [ ! -e $(INSTDIR) ] ; then echo "mkdir $(INSTDIR)" ; mkdir $(INSTDIR) ; fi
	cp -p -r $(TARGET) $(PACKAGE) $(INSTDIR)

wipe: clean
	@find . -name ".DS_Store" -exec rm {} ";" -exec echo rm -f {} ";"
	(cd ../ ; rm -f ./$(ARCHIVE).zip)

zip: wipe
	(cd ../ ; zip -r ./$(ARCHIVE).zip ./$(ARCHIVE)/ --exclude='*/.svn/*')

sdist: clean
	$(PYTHON) setup.py sdist

pydoc: clean
	(sleep 3 ; open http://localhost:9999/$(PACKAGE).html) & $(PYDOC) -p 9999

lint: pylint clean
	@if [ ! -e $(LINTRCF) ] ; then $(PYLINT) --generate-rcfile > $(LINTRCF) 2> /dev/null ; fi
	$(PYLINT) --rcfile=$(LINTRCF) `find . -name "*.py" | sort` > $(LINTRST) ; less $(LINTRST)

# 
# pip is the PyPA recommended tool for installing Python packages.
# 
pip:
	@if [ -z `which pip` ]; \
	then \
		(cd $(WORKDIR); curl -O https://bootstrap.pypa.io/get-pip.py); \
		(cd $(WORKDIR); sudo -H python get-pip.py); \
		(cd $(WORKDIR); rm -r get-pip.py); \
	else \
		(cd $(WORKDIR); sudo -H pip install -U pip); \
	fi

# 
# Pylint is a tool that checks for errors in Python code,
# tries to enforce a coding standard and looks for code smells.
# 
pylint:
	@if [ -z `pip list --format=freeze | grep pylint` ]; \
	then \
		(cd $(WORKDIR); sudo -H pip install pylint); \
	fi

# 
# Pillow is the friendly PIL fork. PIL is the Python Imaging Library.
# 
pil:
	@if [ -z `pip list --format=freeze | grep Pillow` ]; \
	then \
		(cd $(WORKDIR); sudo -H pip install Pillow); \
	else \
		(cd $(WORKDIR); sudo -H pip install -U Pillow); \
	fi

# 
# List of the required packages
# 
list: pip
	@(pip list --format=freeze | grep pip)
	@(pip list --format=freeze | grep pylint)
	@(pip list --format=freeze | grep Pillow)

prepare: pip pylint pil

update: pip pylint pil
