
 function tags_click(btn,id){	
	
	//获取标签输入框	
	let tags_input = document.getElementById("tags");
	
	//判断是否已经包含	//容易出bug
	//if(tags_input.value.indexOf(id) > -1)return ;
	
	//选中
	btn.style.background='gray';
			
	//不可再按
	btn.disabled='disabled';
	
	//新增新的值
	tags_input.value = tags_input.value+" "+id;
	
 }

