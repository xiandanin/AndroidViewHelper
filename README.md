# CheckableHelper
快速赋予Adapter单选/多选能力


### __Gradle引入__
```
compile 'com.dyhdyh:checkable-helper:1.0.0'
```

### __单选Adapter__
将自定义的Adapter实现`SingleCheckableAdapter`   

```
SingleCheckableHelper singleHelper = new SingleCheckableHelper(this);

//设置position为选中，null则清除选中
singleHelper.setCheckedPosition(position);


//获取选中的position，null表示都没有选中
Integer checkedPosition = singleHelper.getCheckedPosition();
```

### __多Adapter__
将自定义的Adapter实现`MultipleCheckableAdapter`  

```
MultipleCheckableHelper multipleHelper = new MultipleCheckableHelper(this);

//设置多个为checked multipleHelper.setCheckedPositionArray(position,checked);

//获取选中的position数组
int[] positionArray = multipleHelper.getCheckedPositionArray();

//获取选中的数据集合
List<Object> checkedList = multipleHelper.getCheckedList();

//清空所有选中
multipleHelper.clear();;

```