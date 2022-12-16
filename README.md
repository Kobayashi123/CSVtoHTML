# CSVtoHTML

## リモートリポジトリを取得する
git clone リモートリポジトリのURL

## リモートから変更を取得
git pull

## ブランチを作成して変更
git switch -c ブランチ名

## ファイルをステージングエリアに追加
git add .

## addの取り消し
git reset HEAD <ファイル名>

## コミット
git commit -m "コメント"

## 直前のcommitを取り消し(コミット取り消した上でワークディレクトリの内容も書き換え)
git reset --hard HEAD^

## リモートリポジトリに、ローカルの指定したブランチを送信
git push origin ブランチ名

## ログの表示
git log --oneline --graph

## ワーキングディレクトリの内容を元に戻す
git checkout .
