window.onload = function () {
    lock = true;
    appendItems(sheet + 1);
    sheet++;
};

let sheet = 0;  //当前显示了几页了(默认一页10条
let lock = false;
const device = window.innerWidth < window.innerHeight ? 'phone' : 'laptop';

//获取滚动条当前的位置
function getScrollTop() {
    let scrollTop = 0;
    if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
    }
    else if (document.body) {
        scrollTop = document.body.scrollTop;
    }
    return scrollTop;
}

//获取当前可视范围的高度
function getClientHeight() {
    let clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
    }
    else {
        clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
    }
    return clientHeight;
}

//获取文档完整的高度
function getScrollHeight() {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}

window.onscroll = function () {
    //检测滚动到快到底部了
    if (!lock && (getScrollTop() + getClientHeight() >= getScrollHeight() - 100)) {
        lock = true;
        appendItems(sheet + 1);
        sheet++;
    }
};


function getXHR() {
    let xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

//添加历史version条目
function appendItems(sheet) {
    let url = "/Blog/VersionAjaxServlet?sheet=" + sheet;
    let xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let res = xmlhttp.responseText;
            let versionArray = eval('(' + res + ')');
            if (versionArray == null) {
                lock = true;  //永久锁住    //或window.onscroll=null;
                sheet--;
                return;
            }
            let parent = document.querySelector('#axis_div');
            versionArray.forEach(function (item, index) {
                if (item.isBig) {
                    let longline_div = document.createElement('div');
                    longline_div.setAttribute("class", "longline_div");
                    let update = document.createElement('div');
                    update.setAttribute("class", "update");
                    let big_update = document.createElement("div");
                    big_update.setAttribute("class", "big_update");
                    //ES6表达式    //反引号中可嵌入其他引号
                    update.innerHTML = `&nbsp;&nbsp;<span class="date">${item.date}</span>&nbsp;&nbsp;&nbsp;<span class="version">${item.version}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="update_content">${item.content}</span>`;
                    big_update.innerHTML = "【重大更新】";
                    longline_div.appendChild(update);
                    longline_div.appendChild(big_update);
                    parent.appendChild(longline_div);
                } else {
                    let line_div = document.createElement('div');
                    line_div.setAttribute("class", "line_div");
                    let update = document.createElement('div');
                    update.setAttribute("class", "update");
                    update.innerHTML = `&nbsp;&nbsp;<span class="date">${item.date}</span>&nbsp;&nbsp;&nbsp;<span class="version">${item.version}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="update_content">${item.content}</span>`;
                    parent.appendChild(line_div).appendChild(update);
                }
            });
            lock = false;   //解锁
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}