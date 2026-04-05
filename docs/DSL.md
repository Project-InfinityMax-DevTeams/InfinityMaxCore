# InfinityMaxCore-SupportDSLの使い方
InfinityMaxCoreには、ゲームオブジェクトをわかりやすく構造的に登録することができます。
InfinityMaxCore依存環境ではDSLを使用してオブジェクトを登録します。
従来の方法でも登録はできますが、DataGenが必要になったりするので使用を推奨します。

# DSLの使い方
DSLはKotlinDSLを使用しています。
DSLで登録できるものは以下の要素です。
|ゲームオブジェクト|DSLブロック|
|---|---|
|Block|block(String: ID){}|
|Item|item(String: ID){}|
|Entity|entity(String: ID){}
|CreativeTab|creativeTab(String: ID){}|
|Recipe|recipe(){ child Recipe Unit }|
|Loot|loot(){ values }|