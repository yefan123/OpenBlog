/*
* 获取ajax处理对象		
 * @returns {xmlhttp}
 */
function getXHR() {
    let xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}


/**
 * 切换到smallList第几页
 * @param page
 */
function smallList(page) {
    let url = "/Blog/ArticleAjaxServlet?page=" + page;
    // 获取ajax
    let xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            let list = document.querySelector('#realList');
            list.innerHTML = "";
            // 处理服务器收到的请求响应
            let res = xmlhttp.responseText;
            // 解析json对象
            let jo = eval('(' + res + ')');


            for (let index in jo) {
                //appendChild必须附加新创建的元素,不能是已经有位置的元素
                let item = document.createElement("div");
                item.setAttribute("class", "list-group-item");
                item.setAttribute("style", "border-color:#eee");
                let time = document.createElement("span");
                time.setAttribute("class", "small-time");
                let title = document.createElement("h4");
                title.setAttribute("class", "small-title");

                time.innerHTML = "";
                title.innerHTML = "";
                item.innerHTML = "";

                time.innerHTML = jo[index].time + "&nbsp;&nbsp;";
                title.innerHTML = `• <a href='javascript:void(0)' onclick="location.href='/Blog/ArticleServlet?id=${jo[index].id}&device=${device}'">${jo[index].title}</a>`;
                // title.innerHTML = "• <a href='/Blog/ArticleServlet?id=" + jo[index].id + "'>" + jo[index].title + "</a>";
                item.appendChild(time);
                item.appendChild(title);
                list.appendChild(item);

                let prev=document.querySelector('#prevPage');
                let next=document.querySelector('#nextPage');

                if (page <= 1) {
                    prev.style.display = 'none';
                    next.style.display = 'inline';
                } else if (page >= Math.ceil(article_number / arti_page_num)) {
                    prev.style.display = 'inline';
                    next.style.display = 'none';
                } else {
                    prev.style.display = 'inline';
                    next.style.display = 'inline';
                }

                document.querySelector('#list-bar span#bar-word').innerHTML = `第 ${page} 页`;
                document.querySelector('#list-bar').onclick = function () {
                    window.location.href = "/Blog/MainServlet?device=laptop";
                };

                document.querySelector('#hidden-word').innerHTML = "&gt;&gt;&gt;点击查看摘要&lt;&lt;&lt;";

            }

        }
    };
    //GET无效...
    xmlhttp.open("POST", url, true);
    xmlhttp.send();

}


function prevPage() {
    if (page <= 1) {
        return;
    }
    page--;
    smallList(page);

}

function nextPage() {
    if (page >= Math.ceil(article_number / arti_page_num)) {
        return;
    }
    page++;
    smallList(page);
}


function logout() {
    let url = "/Blog/LogoutAjaxServlet";
    // 获取ajax
    let xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let res = xmlhttp.responseText;
            alert("注销成功,此时session的user属性值为" + res);
            window.location.reload();
        }
    };
    //GET无效...
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function refresh() {
    let url = "/Blog/AdminAjaxServlet?op=refresh";
    let xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            window.location.reload();
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}