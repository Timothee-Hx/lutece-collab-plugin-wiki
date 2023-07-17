
jQuery(document).ready(function() {
    $("#btn-new-page").click(function() {
        $("#div-search").hide();
        $("#div-new-page").show("slow");
        $("#input-new-page").focus();
    });
    $("#btn-search").click(function() {
        $("#div-new-page").hide();
        $("#div-search").show("slow");
        $("#input-search").focus();
    });
});
window.addEventListener("load", (event) => {
    if(document.getElementsByClassName('darkModeClassOn').length > 0){
        document.getElementById('darkModeSwitch').style.display = 'block';
    }
    let darkMode = localStorage.getItem('darkMode');
    let darkModeId = document.getElementById('darkModeId');
    let darkModeLabel = document.getElementById('darkModeLabel');
        if (darkMode === 'true') {
            darkModeId.checked = true;
            darkModeLabel.innerHTML = '<span class="fa fa-moon fa-2x"></span>';
            document.body.classList.add('darkmode');
        } else {
            darkModeId.checked = false;
            darkModeLabel.innerHTML = '<span class="fa fa-sun fa-2x"></span>';
            document.body.classList.remove('darkmode');
        }
});
function toggleDarkMode() {
    let darkMode = localStorage.getItem('darkMode');
    let darkModeId = document.getElementById('darkModeId');
    let darkModeLabel = document.getElementById('darkModeLabel');
        if (darkMode === 'true') {
            darkModeId.checked = false;
            darkModeLabel.innerHTML = '<span class="fa fa-sun fa-2x"></span>';
            document.body.classList.remove('darkmode');
            localStorage.setItem('darkMode', 'false');
        } else {
            darkModeId.checked = true;
            darkModeLabel.innerHTML = '<span class="fa fa-moon fa-2x"></span>';
            document.body.classList.add('darkmode');
            localStorage.setItem('darkMode', 'true');
        }
}

window.addEventListener("load", (event) => {
        let pre = document.getElementsByTagName('pre');
        for (let i = 0; i < pre.length; i++) {
                let button = document.createElement('button');
                button.className = 'btn btn-primary btn-xs';
                button.style.cssFloat = 'right';
                button.textContent = 'Copy';
                button.onclick = function () {
                    copyToClipboard(this.nextSibling.textContent);
                };
                  pre[i].firstChild.before(button);
        }
});

function copyToClipboard(text) {
    let dummy = document.createElement('textarea');
    document.body.appendChild(dummy);
    dummy.value = text;
    dummy.select();
    document.execCommand('copy');
    document.body.removeChild(dummy);
}