### 一行代码实现RecyclerView的多选模式

[MultiSelectAdapter项目地址](https://github.com/goyourfly/MultiSelectAdapter)
### 装B部分
>在Android上古时代，如果人类想要实现列表展示，需要用到ListView，虽然用起来很简单，但是很不灵活，随后，Google推出了新的替代品RecyclerView，那RecyclerView确实很灵活，只有你想不到的没有它做不到的，但是呢，天下没有免费的午餐，灵活带来的代价就是更多的Coding，灵活的本质就是只抽象更底层的逻辑，至于具体的场景就由你们这些码农去做吧，唔哈哈哈...

依稀记得，在ListView的时代，多选是ListView自带的功能，Item的点击回调也是自带的功能，用起来还算方便，但是到了RecyclerView的时代呢，虽然很灵活，但是一切都得自己去实现，多选就是其中需要自己实现的功能之一。

据我了解，如果你想在RecyclerView上实现多选功能，正规的做法是

1. 在定义Item的时候，要把`选中状态View埋进Item的布局中`
2. 在Adapter中定义一个全局变量暂且叫`isSelectMode`来表示当前状态
3. 在Adapter中定义一个记录选中状态的集合（HashMap或SparseBooleanArray）
4. 监听Item的点击事件，被点击时往上面的集合中插入选中或者未选中记录
5. 刷新列表，在onBindViewHolder中显示选中或未选中的标记
6. ...

可以看到，我只想实现一个简单的多选却要写这么多的代码，着实很码农

为了解决这个问题，我设计了一个Library，可以通过一行代码实现从一个普通的RecyclerView到一个支持多选的RecyclerView的华丽转换

✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨

✨✨✨✨✨✨✨<font color="RED"> MultiSelectAdapter </font>✨✨✨✨✨✨✨✨✨

✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨

虽然名字很土，但是用起来很舒服🙂，谁用谁知道

“那怎么使用呢？真的只需要一行代码就能实现吗？”

“额，O__O "… 其实，理论上讲，不管什么程序，都可以一行搞定，不是吗？”

先说一下具体的怎么使用吧

### 简单介绍
````java
recycler.adapter = MultipleSelect.with(Activity).adapter(YourAdapter).build();
````

对应的显示效果如下：

<img src="./screenshot/Demo0.png" width="200" title="普通模式"/>
<img src="./screenshot/Demo1.png" width="200" title="多选模式"/>

我擦嘞，还真的是一行耶，不过，如果你想要更多不同的显示效果，就得多敲几行了，具体怎么使用我就不多bb了，本文主要想讲一讲这个Library的源码部分，
##### 至于使用方法请看这里，写的很详细，喜欢的话请记得点个Star：[MultiSelectAdapter项目地址](https://github.com/goyourfly/MultiSelectAdapter)

### 源码部分
[MultiSelectAdapter源码](https://github.com/goyourfly/MultiSelectAdapter/tree/master/library/src/main/java/com/goyourfly/multiple/adapter)

其实这个项目本身没有用到任何复杂的技术，都是些很常用的东西，只不过是对普普通通的一些代码的封装，我觉得作为程序员，对语言使用的熟练度，高级的技巧固然重要，但更重要的是封装的思想，这种思想是通用的，这就像我们学习外语，会很多单词，很多句式，很多语法结构很重要，但是如果不能用这些学到的东西来准确的表达你的意思，那学这些也没有意义。


##### 整体结构：
<img src="./screenshot/MultiSelectAdapter.svg" width="400" title="结构"/>

从上面的图可以看出MultipleAdapter使用装饰模式，将用户的Adapter进行加工，然后将装饰后的ViewHolder返回RecyclerView，而装饰的具体过程交给DecorateFactory执行。



