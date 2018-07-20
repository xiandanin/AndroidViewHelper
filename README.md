# CheckableHelper
快速赋予Adapter单选/多选能力


### __Gradle引入__
```
compile 'com.dyhdyh:checkable-helper:1.0.0'
```

### __单选Adapter__
将自定义的Adapter实现`SingleCheckableAdapter`，具体例子[SingleExampleAdapter](https://github.com/dengyuhan/CheckableHelper/blob/master/app/src/main/java/com/dyhdyh/helper/checkable/example/SingleExampleAdapter.java)   

```
SingleCheckableHelper singleHelper = new SingleCheckableHelper(this);

//设置position为选中，null则清除选中
singleHelper.setCheckedPosition(position);

//获取选中的position，null表示都没有选中
Integer checkedPosition = singleHelper.getCheckedPosition();
```

### __多Adapter__
将自定义的Adapter实现`MultipleCheckableAdapter`，具体例子[MultipleExampleAdapter](https://github.com/dengyuhan/CheckableHelper/blob/master/app/src/main/java/com/dyhdyh/helper/checkable/example/MultipleExampleAdapter.java)  

```
MultipleCheckableHelper multipleHelper = new MultipleCheckableHelper(this);

//设置多个为checked
multipleHelper.setCheckedPositionArray(position,checked);

//获取选中的position数组
int[] positionArray = multipleHelper.getCheckedPositionArray();

//获取选中的数据集合
List<Object> checkedList = multipleHelper.getCheckedList();

//清空所有选中
multipleHelper.clear();;

```

### RecyclerViewVisibleHelper
可以获取RecyclerView中子View的可见信息

```
RecyclerViewVisibleHelper mHelper = new RecyclerViewVisibleHelper(new RecyclerViewVisibleHelper.ViewVisibleCallback() {
    @Override
    public View getItemTargetView(View itemView) {
        //return你需要的子View可见信息 如果需要整个Item,则直接return itemView
        //return itemView.findViewById(R.id.video);
        return itemView;
    }
});
final List<ViewVisibleInfo> visibleInfo = mHelper.getLocalVisibleInfo(recyclerView);
```

### OnAutoPlayScrollListener
列表自动播放Listener

```
//自动播放
recyclerView.addOnScrollListener(new OnAutoPlayScrollListener(mHelper) {
    @Override
    public void startPlay(View itemView) {
        VideoView player = itemView.findViewById(R.id.video);
        player.start();
    }
});
```