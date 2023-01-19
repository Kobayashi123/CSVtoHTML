ANT	= env LC_ALL=ja_JP.UTF-8 ant
ARCHIVE	= $(shell basename `pwd`)

all:
	$(ANT) all

clean:
	$(ANT) clean

test: all
	$(ANT) test

install:
	$(ANT) install

doc:
	$(ANT) doc

#zip:
#	$(ANT) zip
zip: clean
	(cd ../ ; zip -r ./$(ARCHIVE).zip ./$(ARCHIVE)/)
