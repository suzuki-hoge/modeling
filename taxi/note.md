### モデリングのポイント
+ タクシーは状態毎にクラスを分け、実行例外を無くす
  + リポジトリはエンティティ毎に存在する
+ タクシーで共通のInterfaceは設けない
+ repositoryを除く、domain層での副作用を禁じる
  + それらはモックなしでテスト出来る事
+ ロジックはDomainServiceか、ValueObjectに書く
  + 必要であればdomain層の関数のテストを書く
+ 業務上発生する異常はEitherで、システム上発生する異常は例外で表現する
  + Eitherはドメインモデルに現れ、例外は現れない

### 感想・補足
+ パッケージも考えるとなお良い訓練になる気がした
+ 時間は直接お題に必要では無かったので省略した
  + 必要なら、要請受領時、乗車時、等クラスを分け、タクシーと同じように次を吐く様な作りにする
+ 待たせているユーザは管理しない