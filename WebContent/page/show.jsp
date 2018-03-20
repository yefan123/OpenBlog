<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>md2html</title>
<!-- 这个文件用来转换 Markdown 到Html 显示 -->
</head>

<!-- preview的css -->
<link rel="stylesheet" href="./editormd/css/style.css" />
<link rel="stylesheet" href="./editormd/css/editormd.preview.css" />

<!-- 引入editormd相关 -->
<!-- js文件顺序不能调换! -->
<script src="./editormd/js/zepto.min.js"></script>
<script src="./editormd/js/editormd.js"></script>
<script src="./editormd/js/jquery.min.js"></script>
<script src="./editormd/lib/marked.min.js"></script>
<script src="./editormd/lib/prettify.min.js"></script>
<script src="./editormd/lib/underscore.min.js"></script>
<!-- 以下js和各种图有关 -->
<!-- "./editormd/lib/raphael.min.js" -->
<!-- "./editormd/lib/sequence-diagram.min.js" -->
<!-- "./editormd/lib/flowchart.min.js" -->
<!-- "./editormd/lib/jquery.flowchart.min.js" -->

<body>
 <div id="mdView">  	
  <textarea id="article_content">${article.content}</textarea>  
 </div>  
<br />
<script type="text/javascript">
	$(function mdToHtml() {		
		//获取要显示的内容
		//var content = $("#article_content").text();			
		editormd.markdownToHTML("mdView", {
			htmlDecode : "style,script,iframe", 
			emoji : true,
			taskList : true,
			tex : false, // 默认不解析
			flowChart : false, // 默认不解析
			sequenceDiagram : false, // 默认不解析			
		});
	});	
</script>

</body>
</html>