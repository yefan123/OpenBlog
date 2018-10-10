下面我们用数学归纳法来解决这个填值的问题。

这里我们借鉴数学归纳法的三个步骤（或者说是动态规划？）：

- 1、初始状态

- 2、假设第j位以及第j位之前的我们都填完了

- 3、推论第j+1位该怎么填

初始状态我们稍后再说，我们这里直接假设第j位以及第j位之前的我们都填完了。也就是说，从上图来看，我们有如下已知条件：
```
next[j] == k;

next[k] == 绿色色块所在的索引;

next[绿色色块所在的索引] == 黄色色块所在的索引;
```

这里要做一个说明：图上的色块大小是一样的（没骗我？好吧，请忽略色块大小，色块只是代表数组中的一位）。

我们来看下面一个图，可以得到更多的信息：
![这里写图片描述](http://img.blog.csdn.net/20180205195850445?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZ2l0aHViXzM4ODg1Mjk2/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
>1.由"next[j] == k;"这个条件，我们可以得到A1子串 == A2子串（根据next数组的定义，前后缀那个）。

>2.由"next[k] == 绿色色块所在的索引;"这个条件，我们可以得到B1子串 == B2子串。

>3.由"next[绿色色块所在的索引] == 黄色色块所在的索引;"这个条件，我们可以得到C1子串 == C2子串。

>4.由1和2(A1 == A2，B1 == B2)可以得到B1 == B2 == B3。

>5.由2和3(B1 == B2， C1 == C2)可以得到C1 == C2 == C3。

>6.B2 == B3可以得到C3 == C4 == C1 == C2

上面这个就是很简单的几何数学，仔细看看都能看懂的。我这里用相同颜色的线段表示完全相同的子数组，方便观察。

 

接下来，我们开始用上面得到的条件来推导如果第j+1位失配时，我们应该填写next[j+1]为多少？

next[j+1]即是找strKey从0到j这个子串的最大前后缀：

#######：(#:在这里是个标记，后面会用)我们已知A1 == A2，那么A1和A2分别往后增加一个字符后是否还相等呢？我们得分情况讨论：

- (1)如果str[k] == str[j]，很明显，我们的next[j+1]就直接等于k+1。

　　用代码来写就是next[++j] = ++k;

- (2)如果str[k] != str[j]，那么我们只能从已知的，除了A1，A2之外，最长的B1，B3这个前后缀来做文章了。

那么B1和B3分别往后增加一个字符后是否还相等呢？

由于next[k] == 绿色色块所在的索引，我们先让k = next[k]，把k挪到绿色色块的位置，这样我们就可以递归调用"#："标记处的逻辑了。

 

由于j+1位之前的next数组我们都是假设已经求出来了的，因此，上面这个递归总会结束，从而得到next[j+1]的值。

 

我们唯一欠缺的就是初始条件了：

next[0] = -1,  k = -1, j = 0

另外有个特殊情况是k为-1时，不能继续递归了，此时next[j+1]应该等于0，即把j回退到首位。

即 next[j+1] = 0; 也可以写成next[++j] = ++k;

 这里我们用Java来描述：
 ```
 public static int[] getNext(String ps)

{

    char[] strKey = ps.toCharArray();

    int[] next = new int[strKey.length];



    // 初始条件

    int j = 0;

    int k = -1;

    next[0] = -1;

 

    // 根据已知的前j位推测第j+1位

    while (j < strKey.length - 1)

    {

        if (k == -1 || strKey[j] == strKey[k])

        {

            next[++j] = ++k;

        }

        else

        {

            k = next[k];

        }

    }



     return next;

}

```
##三。KMP算法的优化和改进



KMP算法是可以被进一步优化的。

我们以一个例子来说明。譬如我们给的P字符串是“abcdaabcab”，经过KMP算法，应当得到“特征向量”如下表所示：
![这里写图片描述](http://img.blog.csdn.net/20180205200048696?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZ2l0aHViXzM4ODg1Mjk2/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
但是，如果此时发现p(i) == p(k），那么应当将相应的next[i]的值更改为next[k]的值。经过优化后可以得到下面的表格：
![这里写图片描述](http://img.blog.csdn.net/20180205200107143?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZ2l0aHViXzM4ODg1Mjk2/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
- （1）next[0]= -1 意义：任何串的第一个字符的模式值规定为-1。

- （2）next[j]= -1 意义：模式串T中下标为j的字符，如果与首字符

相同，且j的前面的1—k个字符与开头的1—k

个字符不等（或者相等但T[k]==T[j]）（1≤k

如：T=”abCabCad” 则 next[6]=-1，因T[3]=T[6]

-（3）next[j]=k 意义：模式串T中下标为j的字符，如果j的前面k个

字符与开头的k个字符相等，且T[j] != T[k] （1≤k

即T[0]T[1]T[2]。。。T[k-1]==

T[j-k]T[j-k+1]T[j-k+2]…T[j-1]

且T[j] != T[k].（1≤k

- (4) next[j]=0 意义：除（1）（2）（3）的其他情况。



于是乎我们修正的NEXT数组的求法如下：
```
public static int[] getNext(String ps)

{

    char[] strKey = ps.toCharArray();

    int[] next = new int[strKey.length];



    // 初始条件

    int j = 0;

    int k = -1;

    next[0] = -1;

 

    // 根据已知的前j位推测第j+1位

    while (j < strKey.length - 1)

    {

        if (k == -1 || strKey[j] == strKey[k])

        {

            // 如果str[j + 1] == str[k + 1]，回退后仍然失配，所以要继续回退

            if (str[j + 1] == str[k + 1])

            {

                next[++j] = next[++k];

            }

            else

            {

                next[++j] = ++k;

            }

        }

        else

        {

            k = next[k];

        }

    }



     return next;

}
```
      好了，以上就是KMP算法的所有内容，我们可以看出，KMP算法的关键就是：利用匹配失败后的信息，利用递归的思想为每一个字符算出一个“特征值”。最后，KMP算法适合在字符种类很稀疏的情况下适用：仅当模式与主串之间存在许多“部分匹配”的情况下才显得比“暴力匹配”快，但是如果模式串中有太多相同的字符，就会略微降低KMP的优化效果。KMP算法还有一个进步特点就是：指示主串的指针不需要回溯，对主串仅需从头至尾扫描一遍。



（如需转载请标明出处）