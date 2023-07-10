

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
    // add copy button to pre blocks

    let pre = document.getElementsByTagName('pre');
    for (let i = 0; i < pre.length; i++) {
        let button = document.createElement('button');
        button.className = 'btn btn-primary btn-xs';
        button.style.float = 'right';
        button.style.marginTop = '10px';
        button.style.marginRight = '10px';
        button.textContent = 'Copy';
        button.onclick = function() {
            copyToClipboard(this.nextElementSibling.textContent);
        };
        pre[i].insertAdjacentElement('beforebegin', button)
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