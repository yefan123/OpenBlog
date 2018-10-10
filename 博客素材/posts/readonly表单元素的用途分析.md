###先引出出现的bug:
input的字段当为diabled时时无法获取数值得，所以最近不要用这个，我们可以用readonly带替代，即可解决这类问题。
 
>1	<input name="country" id="country" size=12 value="disabled"disabled="disabled" 
 
放在form表单中提交后得不到该值。 
将disabled="disabled" 改为 readonly = "readonly" 即可 ,按照W3C的规范
 
设置为disabled的input将会有下面的限制： 
1.不能接收焦点 
2.使用tab键时将被跳过 
3.可能不是successful的 
 
设置为readonly的input将会有下面的限制： 
1.可以接收焦点但不能被修改 
2.可以使用tab键进行导航 
3.可能是successful的 
只有successful的表单元素才是有效数据，也即是可以进行提交。disabled和readonly的文本输入框只能通过脚本进行修改value属性。 
 
一、来看看这两个属性在效果和使用上的区别
 
1 readonly是要锁定这个控件，通过在界面上无法修改他（但是通过javascript可以修改他）。
 
2 disabled 和readonly有相同的地方也是可以锁定这个控件用户不能改变他的值，但是disabled的更彻底一些，他是要使你完全不能使用他，包括改变他的背景颜色（不信，你去修改一个被disabled掉的input文本框，你发现你是徒劳），如果是checkbox则不能选中他。
 
3 所有控件都有disabled 属性，但是不一定有readonly属性，如select 下拉框。
(1)text
 
 
(2) checkBox
 
 
(3)select下拉选择框（这里只能显示disabled和非disabled的区别，因为select 没有readonly属性
 
 
(4)button按钮
 
 
说明：点击被readonly掉的按钮照样可以触发事件，但是被disabled掉的按钮就无法使用了不管上面有没有事件。
 
(5)div
 
 
 ###然后谈一谈思想
以前不是很理解readonly表单元素存在的意义:既然它不能被用户修改,干嘛要多此一举呢?后来想到这是一种方便程序员逻辑的一种设计结构,即"完整性",程序员,尤其是前端程序员总是喜欢把东西分门别类,即使相差较大的东西,划分为同一类的代价就是体积的增大,或者说组成元素的扩充,html中有些元素或者属性,即使看不到,但它总是存在的.所以这种"多余的"元素从理论不是最优的,但却让整个系统更"可读",更易扩展,从而给用户带来更好的服务.
 
说明：将div设置disabled属性之后，整个div都灰掉了，但是文本框里面还是可以输入内容的。
注意：select下拉选择框是没有readonly属性的