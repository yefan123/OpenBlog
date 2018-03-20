window.onresize = function () {
    let list = document.querySelectorAll('.label_container');
    if (document.querySelector('#right_content').clientWidth < 660) {
        for (let i of list) {
            i.style.width = '25%';
        }
    } else {
        for (let i of list) {
            i.style.width = '20%';
        }
    }
}

function getXHR() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}


function refresh() {
    var url = "/Blog/AdminAjaxServlet?op=refresh";
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            window.location.reload();
        }
    }
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}

function add_tag() {
    //获得新tag的名字
    let tagName = document.querySelector('#newTag input').value.trim();
    if (tagName == '') {
        alert("标签名不能为空!");
    } else {
        var url = "/Blog/AdminAjaxServlet?op=add_tag&&tagName=" + tagName;
        var xmlhttp = getXHR();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                alert("success");
            }
        }
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
    }
}

function delet_tag(id) {

    let key = confirm("确认删除标签?");
    if (key == false) {
        return;
    }

    var url = "/Blog/AdminAjaxServlet?op=tag_delete&&tagId=" + id;
    // 获取ajax
    var xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            alert("success");
        }
    }
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}