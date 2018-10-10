点赞的时候发现一个bug,即点完右上角的数字并没有增加,刷新后才出现(〃'▽'〃),所以寻找中间被中断的js函数,并查看异常:
>java.lang.IllegalArgumentException: An invalid character [32] was present in the Cookie value

求助google，了解到 An invalid character [32]  中32对应的编码是空格，Stack Overflow上的回答：This is due to Tomcat's cookie processing being changed to a RFC 6265 compliant implementation by default in 8.5, which does not allow space (character 32), among others. 我的tomcat版本正好最近刚升级到8.5.0(8.5以上的版本不再允许cookie对象中有空格),
遂查找函数,并将空格改为其他的分隔符,如'_':
>Cookie cookie = new Cookie("star_arti" + id, System.currentTimeMillis() + "_");

自古以来,空格符" "都是很尴尬的一个字符,不仅在cookie中被封杀,其他的地方比如url中,文件系统里还有表单处理中,空格都经常被无情地trim()掉,所以为了减少bug,还是少在数据结构中掺入空格吧.
(◕ᴗ◕✿)
