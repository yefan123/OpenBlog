window.onload = function () {
    //生成版权声明
    let copyright = document.createElement("h5");
    copyright.setAttribute("id", "copyright");
    copyright.innerHTML = "<br><br><br><br>版权声明：本文为站长原创作品不得转载; 如果非要转载请联系作者本人; 如果实在不想联系我那也请标明原作链接; 如果什么都不想麻烦那....随意吧,开心就好:D";
    document.querySelector('#mdView').appendChild(copyright);

    //计算标签数量
    document.getElementById('labels_num').innerHTML = "&nbsp;" + document.querySelectorAll('.skew').length + "&nbsp;";
}


/**
 * 获取ajax处理对象
 * @returns {xmlhttp}
 */
function getXHR() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {	//傻逼IE6
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

/**
 * 点赞这个ariticle
 * @param article_id
 */
function love_article(article_id, self) {
    var url = "/Blog/StarPostServlet?id=" + article_id;
    // 获取ajax
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            // 处理服务器收到的请求响应
            var res = xmlhttp.responseText;
            // 解析json对象
            var res = eval('(' + res + ')');
            if (res.msg == "success") {
                document.getElementById("love").innerHTML = "&nbsp;" + res.new_star + "&nbsp;&nbsp;";
            } else {
                alert("显然您已经赞过啦>_<");
            }
            self.firstChild.innerHTML = "&nbsp;已赞";
        }
    }
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

//管理员删除评论
function deletecm(component, comm_id) {
    var container = component.parentNode.parentNode;
    var url = "/Blog/CommentDropServlet?id=" + comm_id;
    // 获取ajax
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            // 处理服务器收到的请求响应
            var res = xmlhttp.responseText;
            // 解析json对象
            var res = eval('(' + res + ')');
            //alert( res.msg );
            if (res.msg == "success") {
                //删除评论的视图
                var p = container.parentNode;
                p.removeChild(container);
            }
        }
    }
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function logout() {
    var url = "/Blog/LogoutAjaxServlet";
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let res = xmlhttp.responseText;
            alert("注销成功,此时session的user属性值为" + res);
            window.location.reload();
        }
    }
    //GET无效...
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}