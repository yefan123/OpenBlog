let device = window.innerWidth < window.innerHeight ? "phone" : "laptop";


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

function add_tag() {
    //获得新tag的名字
    let tagName = document.querySelector('#newTag input').value.trim();
    if (tagName == '') {
        alert("标签名不能为空!");
    } else {
        let url = "/Blog/AdminAjaxServlet?op=add_tag&&tagName=" + tagName;
        let xmlhttp = getXHR();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                alert("success");
            }
        };
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
    }
}

function delet_tag(id) {
    // let key = confirm("确认删除标签?");
    if (!confirm("确认删除标签?")) {
        return;
    }

    let url = "/Blog/AdminAjaxServlet?op=tag_delete&&tagId=" + id;
    // 获取ajax
    let xmlhttp = getXHR();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            alert("success");
        }
    };
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}