# InfinityMaxCore-SupportDSLの使い方
InfinityMaxCoreには、ゲームオブジェクトをわかりやすく構造的に登録することができます。
InfinityMaxCore依存環境ではDSLを使用してオブジェクトを登録します。
従来の方法でも登録はできますが、DataGenが必要になったりするので使用を推奨します。

# DSLの使い方
DSLはKotlinDSLを使用しています。
DSLで登録できるものは以下の要素です。
|ゲームオブジェクト|DSLブロック|
|---|---|
|Block|block(String: ID){ ItemDSLBuilder}|
|BlockEntity|blockEntity{ BlockEntityBuilder }|
|Item|item(String: ID){}|
|Entity|entity(String: ID){ EntityDSLBuilder }
|CreativeTab|creativeTab(String: ID){ CreativeTabScpoe }|
|Recipe|recipe{ child Recipe Unit }|
|Loot|loot{ drop(Item: item, Int: min, Int max, Int: weight, Float: chance }|
|Food|food{ FoodScope }|

# DSLの使用方法
実際にDSLの各オブジェクト別構文を説明します。

## Block
Blockは以下のように登録できます。
```
block(“test_block”){
    hardness(2.0f)
    resistance(4.0f)
    light(4)
    friction(3.0f)
    blockIssuance()
    mapColor(MapColor.COLOR_RED)
    blockSoundType(SoundType.METAL)
    breakingToolRequired(true)
    doLavaFire(true)
    hasBlockItem(true)
    creativeTab("infinitymaxtest:test")
    blockEntity{
        packetSync()
        dataType(TestBlockData.TYPE)
        ticker(TestBlockTicker.INSTANCE)
    }
    lang("Test Block")
    tag("minecraft:mineable_pickel")
    tag("minecraft:need_iron_pickel")
}
```