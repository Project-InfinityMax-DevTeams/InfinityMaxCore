# Logicの書き方
InfinityMaxシリーズでは、CoreAPI依存のMODとの柔軟な連携のためにLogicを共通管理するシステムを実装しています。
※ここで言うLogicとは、Block、ItemおよびEntityに埋め込むカスタムオブジェクトクラスのことを指します。

# Logicの一覧
Logicを書くには以下のクラスをimplementすることで作ることができます。
|implementするクラス|実行タイミング|
|---|---|
|BlockTickLogic|Tickでの呼び出しでの実行(1/20秒での呼び出し)|
|BlockUseLogic|Blockを右クリックした際に実行|
|BlockNeighbourUpdateLogic|Blockの隣接や自身の内部に変更が加えられた場合に実行(隣接にブロックを置くなど)|
|BlockRandomTickLogic|BlockがRandomTickによって抽選された時に実行|
|BlockEntityInsideLogic|エンティティがブロックにずっと触れている時に実行される|
|BlockStepOnLogic|ブロックにエンティティが乗っている時に実行される|
|ItemUseLogic|アイテムを持って右クリックした場合を示す|
|ItemInventoryTickLogic|アイテムを所持している間に実行|
|ItemReleaseUseLogic|アイテムを捨てた場合に実行|
|EntityAttackLogic|エンティティが攻撃した際に実行される|
|EntityHurtLogic|エンティティが攻撃を受けた場合に実行されに実行|
|EntityInteractLogic|エンティティを右クリックした時に実行される|
|EntityTickLogic|エンティティが存在している時のロジック|

# Block系Logicの書き方
Block系はブロック内部にロジックを入れ込みます。BlockEntityを使用する必要はありませんがBlockに情報を持たせる場合は使用を推奨します。
ここでは、Block系LogicをBlockEntityも生成する前提とします。

## BlockTickLogic
BlockTickLogicは、Blockが置かれている間に1/20秒毎に実行されます。
BlockTickLogicを使用する際はKotlinDSLでBlockにLogicを登録する必要があります。
block定義コードブロックに以下のメソッド実行文を実装してください。
```
block(“test_block”){
    blockTick("block_tick")
}
```
なお、blockTick内部のStringIDは他ブロックのIDでも動作します。また、同じInfinityMaxCore依存の他MODのBlockTickLogicのIDでも動作します。
**このIDが見つからない場合はエラーは出ません。**StringIDのミスを確実に無くしたい場合は以下のようにインスタンスを生成してID指定をしてください。
```
block(“test_block”){
    blockTick(TestBlockTickLogic.ID) //JavaクラスでIDをStringで定義すること。
}
```
そしてクラスでは以下のように記述してください。
```
public class TestBlockTickLogic implements BlockTickLogic {

    public static final Strihg ID = "test_block_tick";

    @Override
    public String id(){
        return ID;
    }

    @Override
    public void execute(@NotNull Level level, BlockPos pos, BlockState state){
        if(is.ClientSide) return;
        //ここにLogicを書く
    }
}
```
また、BlockEntityにDataを持たせておき、そのDataをLogicで使用したい場合、このように描きます。
```
@Override
public void execute(Level level, BlockPos pos, BlockState state){
    if(is.ClientSide) return;
    BlockEntity be = level.getBlockEntity(pos);
    if(!(be instanceof BaseBlockEntity base)) return;
    TestBlockData data = base.gaeData(TestBlockData.TYPE);
    //ここにLogicを書く
    base.setChanged();
}
```
そしてTestBlockDataというクラスを作り、以下を入力します。
```
public class TestBlockData{
    public static final DataType<TestBlockData> TYPE =
            new DataType("test_block",Codec.unit(new TestBlockData()),TestBlockData::new);

    public double a; //Blockに持たせたいDataを書く。ここの変数はゲームでセーブされる。
}
```
これで、Logicを操作した後にBloclEntityのDataTypeに数値情報やboolean値などが記録され、セーブしてもデータが消えなくなります。
なお、このTypeにHasPhysicalStateをimplementすると、他のBlockの物理情報値PhysicalStateの細かな数値情報を上書きすることができます。
上書き可能Dataクラスは以下のように書いてください。
```
public class TestBlockData implements HasPhysicalState{
    public static final DataType<TestBlockData> TYPE =
            new DataType("test_block",Codec.unit(new TestBlockData()),TestBlockData::new);

    //Blockに持たせたいData変数を宣言。ここの変数はゲームでセーブされる。
    public PhysicsState state;

    @Override
    public PhysicalState getPhysicsState(){
        return state;
    }

    @Override
    public void SetPhysicalState(PhysicalState state){
        this.state = state;
    }
}
```
