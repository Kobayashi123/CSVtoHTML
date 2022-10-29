# CSVtoHTML

## ローカルリポジトリの初期化
git init

##　リモートリポジトリとの紐付け
git remote add origin {gitのURL}

##　指定ディレクトリ配下の変更や、新規作成した全てのファイルをステージエリアに追加する(ファイルを登録)
git add .

## コミット
git commit -m "{コメント}"

## リモートリポジトリに、ローカルのmainブランチを送信する。リモートリポジトリ上の、同名のブランチ（ここではmainブランチ）が更新される。
git push origin main

##　リモートリポジトリを取得する
git clone {gitのURL}

## 指定されたブランチに切り替える
git switch {branch}

##　リモートからの変更を取得する
git pull

## addの取り消し
git reset HEAD <ファイル名>

## 直前のcommitを取り消し(コミット取り消した上でワークディレクトリの内容も書き換え)
$ git reset --hard HEAD^
