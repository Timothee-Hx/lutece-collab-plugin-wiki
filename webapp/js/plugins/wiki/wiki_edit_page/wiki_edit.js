/* -------------- EDITOR -------------- */

const { Editor } = toastui;

const { chart, codeSyntaxHighlight, colorSyntax, tableMergedCell } = Editor.plugin;

let wikiContent = document.getElementById('wiki_content').value;

const chartOptions = {
    minWidth: 100,
    maxWidth: 600,
    minHeight: 100,
    maxHeight: 300
};

const editor = new Editor({
    el: document.querySelector('#editor'),
    customHTMLRenderer: {
        latex(node) {
            const generator = new latexjs.HtmlGenerator({ hyphenate: false });
            const { body } = latexjs.parse(node.literal, { generator }).htmlDocument();
            document.head.appendChild(generator.stylesAndScripts(""))
            return [
                { type: 'openTag', tagName: 'div', outerNewLine: true },
                { type: 'html', content: body.innerHTML },
                { type: 'closeTag', tagName: 'div', outerNewLine: true }
            ];
        },
        span(node) {
            return [
                { type: 'openTag', tagName: 'div', outerNewLine: false, className: node.className },
                { type: 'html', content: node.literal },
                { type: 'closeTag', tagName: 'div', outerNewLine: false },
            ];
        },
        htmlBlock: {
            iframe(node) {
                return [
                    { type: 'openTag', tagName: 'iframe', outerNewLine: true, attributes: node.attrs },
                    { type: 'html', content: node.childrenHTML },
                    { type: 'closeTag', tagName: 'iframe', outerNewLine: true },
                ];
            },
        },
    },
    previewStyle: 'vertical',
    height: '500px',
    initialValue: wikiContent,
    scrollSync: false,
    plugins: [[chart, chartOptions], [codeSyntaxHighlight, { highlighter: Prism }], colorSyntax, tableMergedCell],
    toolbarItems: [
        ['heading', 'bold', 'italic', 'strike'],
        ['hr', 'quote'],
        ['ul', 'ol', 'task', 'indent', 'outdent'],
        ['table', 'image', 'link', 'codeblock'],
        ['scrollSync'],
    ],
    events: {
        change: () => {
            document.getElementById('wiki_content').value = editor.getMarkdown();
            autoSave();
        },
    },
});
/* -------------- EDITOR CUSTOM BUTTONS IN TOOLBAR-------------- */

editor.insertToolbarItem({ groupIndex: 0, itemIndex: 0 }, {
    name: 'IconButton',
    tooltip: 'FA Icons',
    text: 'FA',
    className: 'fa fa-search editor',
    style: { backgroundImage: 'none' },
});
editor.insertToolbarItem({ groupIndex: 0, itemIndex: 1 }, {
    name: 'Alert',
    tooltip: 'Alert Boxes',
    text: 'alert',
    className: 'alert alert-danger editor',
    style: { backgroundImage: 'none' },
});
editor.insertToolbarItem({ groupIndex: 0, itemIndex: 2 }, {
    name: 'BadgeButton',
    tooltip: 'Badges',
    text: 'Badge',
    className: 'badge badge-primary editor',
    style: { backgroundImage: 'none' },
});
editor.insertToolbarItem({ groupIndex: 0, itemIndex: 3 }, {
    name: 'toc',
    tooltip: 'Table of Contents',
    text: 'TOC',
    className: 'fa fa-list editor',
    style: { backgroundImage: 'none' },
});
editor.insertToolbarItem({ groupIndex: 0, itemIndex: 4 }, {
    name: 'Jumbotron',
    tooltip: 'Jumbotron',
    text: 'Jum',
    className: 'jumbotron editor',
    style: { backgroundImage: 'none' },
});
editor.insertToolbarItem({ groupIndex: 0, itemIndex: 5 }, {
    name: 'DarkMode',
    tooltip: 'Dark Mode',
    text: 'Dark',
    className: 'fa fa-moon editor',
    style: { backgroundImage: 'none' },
});


document.getElementsByClassName("toastui-editor-mode-switch")[0].remove();


/* -------------- CLOSE MODALES -------------- */
function closeToastUiModal() {
    const popupNumber = document.getElementsByClassName("toastui-editor-popup").length
    for (let i = 0; i < popupNumber; i++) {
        document.getElementsByClassName("toastui-editor-popup")[i].style.display = "none";
    }
}


/* -------------- ALERT  -------------- */
const addAlertButton = document.getElementsByClassName("alert alert-danger editor")[0];
addAlertButton.addEventListener('click', function() {
    document.getElementById("selectAlertType").style.display = "block";
});

function selectAlertBoxType(alertValue) {
    let alertIcon = "fa-solid fa-info"
    switch (alertValue) {
        case "danger":
            alertIcon = "fa-solid fa-triangle-exclamation"
            break;
        case "warning":
            alertIcon = "fa-solid fa-exclamation"
            break;
        case "info":
            alertIcon = "fa-solid fa-info"
            break;
        case "success":
            alertIcon = "fa-solid fa-check"
            break;
        default:
            alertIcon = "fa-solid fa-info"
    }

    const alertHtml = '<div class="alert alert-' + alertValue + '"><span class="'+alertIcon+'"></span>  <span> </span>My alert message goes here.</div>';
    editor.insertText("$$span\n"+alertHtml+ "\n$$");
    closeToastUiModal();
}


/* -------------- ICONS -------------- */


let addIconButton = document.getElementsByClassName("fa fa-search")[0];
addIconButton.addEventListener('click', function() {
    document.getElementsByClassName("toastui-editor-popup")[0].style.display = "block";
    document.getElementsByClassName("ss-option").forEach(function(option) {
        if (option.innerText.indexOf("[brands]") > -1) {
            option.style.display = "none";
        }
    });


    document.getElementsByClassName("ss-search")[0].children[0].addEventListener("keyup", function() {
        document.getElementsByClassName("ss-option").forEach(function(option) {
            if (option.innerText.indexOf("[brands]") > -1) {
                option.style.display = "none";
            }
        });
    });

});


function changeIconSize() {
    const icon = document.getElementById("selectIcon").value;
    const iconSize = document.getElementById("iconSize").value;
    document.getElementById("selectedIcon").style.display = "none";
    document.getElementById("selectedIcon").innerHTML = '<span class="fa fa-' + icon +" " + iconSize + '"></span><br/>';
    document.getElementById("selectedIcon").style.display = "block";
}
function insertIcon() {
    const icon =  document.getElementById("selectedIcon").children[0].outerHTML;
    editor.insertText("$$span\n"+icon+ "\n$$");
    document.getElementsByClassName("toastui-editor-popup")[0].style.display = "none";
}

document.getElementById("selectIcon").addEventListener("change", function() {
    const icon = document.getElementById("selectIcon").value;
    document.getElementById("iconIsSelected").style.display = "block";
    document.getElementById("selectedIcon").innerHTML = '<span class="fa fa-' + icon + '"></span><br/>';
});


/* -------------- BADGES -------------- */

const addBadgeButton = document.getElementsByClassName("badge badge-primary editor")[0];
addBadgeButton.addEventListener('click', function() {
    document.getElementById("selectBadge").style.display = "block";
});

function selectBadge(badgeValue) {
    const badgeHtml = '<span class="badge badge-' + badgeValue + '">My badge message goes here.</span>';
    document.getElementById("selectedBadge").innerHTML = badgeHtml;
    document.getElementById("badgeIsSelected").style.display = "block";
}
function changeBadgeSize(badgeSize) {
    const badgeClass = document.getElementById("badgeType").value;
    document.getElementById("badgeIsSelected").style.display = "none";
    const badgeHtml = '<' + badgeSize + '>' + '<span class="badge badge-' + badgeClass + '">My badge message goes here.</span>' + '</' + badgeSize + '>';
    document.getElementById("selectedBadge").innerHTML = badgeHtml;
    document.getElementById("badgeIsSelected").style.display = "block";
}
function insertBadge() {
    const badgeHtml = document.getElementById("selectedBadge").innerHTML;
    editor.insertText("$$span\n"+badgeHtml+ "\n$$");
    closeToastUiModal();
}
/* -------------- jumbotron -------------- */
const addJumbotronButton = document.getElementsByClassName("jumbotron editor")[0];
addJumbotronButton.addEventListener('click', function() {
    insertJumbotron();
});
function insertJumbotron() {
    const jumbotronTitle = "My jumbotron title";
    const jumbotronText = "My jumbotron text, it can be long or short !";
    let bootStrap5Jumbotron = '<div class="h-100 p-5 text-bg-dark rounded-3">\n' +
        '          <h2 class="text-white">' + jumbotronTitle + '</h2>\n' +
        '          <p class="text-light">' + jumbotronText + '</p>\n' +
        '        </div>';
    editor.insertText("$$span\n"+bootStrap5Jumbotron+ "\n$$");
    document.getElementsByClassName("toastui-editor-popup")[0].style.display = "none";
}

/* -------------- TABLE OF CONTENT -------------- */

const tableOfContentButton = document.getElementsByClassName("fa fa-list editor")[0];
tableOfContentButton.addEventListener('click', function() {
    let tocHtml = '<span class="toc"></span>';
    editor.insertText("$$span\n"+tocHtml+ "\n$$");

});
/* -------------- DARK MODE -------------- */
const darkModeButton = document.getElementsByClassName("fa fa-moon editor")[0];
darkModeButton.addEventListener('click', function() {
    const el = document.getElementById("editor");
    if(el.classList.contains("toastui-editor-dark")){
        el.classList.remove("toastui-editor-dark");}
    else{ el.classList.add("toastui-editor-dark");}
});

function checkForDarkMode(html){
    const el = document.getElementById("editor");

    if(el.classList.contains('toastui-editor-dark')){
        // add class on first element
        // take the first element
        html.classList.add('toastui-editor-dark');
        alert(html)
        setTimeout(function(){
            // remove class on first element

        }, 30000);
    }
    return html;
}
function editorHasToc (){
    const wikiHtmlContent = document.getElementsByClassName("toastui-editor-md-preview")[0].innerHTML;
    let parser = new DOMParser();
    let doc = parser.parseFromString(wikiHtmlContent, 'text/html');

    let toc = doc.getElementsByClassName('toc');
    if(toc !== undefined && toc.length > 0){
        document.getElementById("tableOfContentCheckBox").checked = true;
    } else {
        document.getElementById("tableOfContentCheckBox").checked = false;
    }
}

document.onload = (function() {
    editorHasToc();
});
function checkforTOC(wikiHtmlContent){
    // parse the html content
    let parser = new DOMParser();
    let doc = parser.parseFromString(wikiHtmlContent, 'text/html');
    // parse headers and add them an id
    let headers = doc.querySelectorAll('h1, h2, h3, h4, h5, h6');
    for(let i = 0; i < headers.length; i++) {
        let headerText = headers[i].innerText;
        // replace white spaces with _
        headerText = replaceAll( ' ' , '_' , headerText );
        headers[i].setAttribute('id', headerText);
    }
    let preElements = doc.querySelectorAll('pre');
    for(let i = 0; i < preElements.length; i++) {
        preElements[i].style.backgroundColor = "#282a36";
    }
    doc.querySelectorAll(".toastui-editor-md-preview-highlight").forEach(function (element) {
        element.classList.remove("toastui-editor-md-preview-highlight");
    });        // check for white spaces and replace them with -
    let toc = doc.getElementsByClassName('toc')[0];
    if(toc !== undefined){
        let tableOfContent = createTableOfContent(doc);
        doc.getElementsByClassName('toc')[0].replaceWith('')
        // add parent element to the class below
        let flexDiv = document.createElement("div");
        flexDiv.classList.add("flex");
        flexDiv.classList.add("flex-column");
        flexDiv.classList.add("flex-grow-1");
        flexDiv.classList.add("flex-shrink-1");
        flexDiv.classList.add("flex-basis-auto");
        flexDiv.classList.add("overflow-auto");
        flexDiv.appendChild(tableOfContent);
        flexDiv.appendChild(doc.getElementsByClassName('toastui-editor-contents')[0]);
        doc = flexDiv.outerHTML;
        return doc;
    } else {
        wikiHtmlContent = doc.documentElement.innerHTML;
        return wikiHtmlContent;
    }
}

function createTableOfContent(doc) {
    let thisPageurl = window.location.href;
    thisPageurl = "jsp/"+ thisPageurl.split("jsp/")[1];
    thisPageurl = thisPageurl.replace("view=modifyPage", "view=viewPage");
    thisPageurl = thisPageurl.replace(/&id_topic_version=[0-9]*/g, "");
    let headers = doc.querySelectorAll("h1, h2, h3, h4, h5, h6");

    let tableOfContent = document.createElement("ul")
    tableOfContent.id = "tableOfContent";
    tableOfContent.className = "nav flex-column";
    for (let i = 0; i < headers.length; i++) {
        let header = headers[i];
        let headerText = header.innerText;
        let headerLevel = header.tagName;
        let linkElement = document.createElement("a");
        linkElement.href = thisPageurl + '#' + header.id;
        linkElement.innerText = headerText;
        linkElement.style = "color: black; text-decoration: none;";
        linkElement.className = "nav-link";
        let navItem = document.createElement("li");
        if (headerLevel === "H1" || headerLevel === "H2" || headerLevel === "H3") {
            navItem.className = "nav-item";
            if (headers[i + 1] !== undefined) {
                if (headers[i + 1].tagName === "H1" || headers[i + 1].tagName === "H2" || headers[i + 1].tagName === "H3") {
                    tableOfContent.appendChild(linkElement);
                } else if (headers[i + 1].tagName === "H4" || headers[i + 1].tagName === "H5" || headers[i + 1].tagName === "H6") {
                    let divContainer = document.createElement("div");
                    divContainer.style = "display: flex; flex-direction: row; spacing: 5px;";
                    divContainer.appendChild(linkElement);
                    navItem.appendChild(divContainer);
                    tableOfContent.appendChild(navItem);
                    let icon = document.createElement("i");
                    icon.className = "fa fa-chevron-left";
                    icon.style = "padding: 5px;";
                    divContainer.appendChild(icon);
                    let subLinkContainer = document.createElement("ul");
                    subLinkContainer.className = "subLinkContainer";
                    subLinkContainer.style = "margin-left: 20px; display: none;";
                    tableOfContent.appendChild(subLinkContainer);
                } else {
                    tableOfContent.appendChild(linkElement);
                }
            }
        }
        if (headerLevel === "H4" || headerLevel === "H5" || headerLevel === "H6") {
            // get the last div with class subLinkContainer
            let subLinkContainers = tableOfContent.getElementsByClassName("subLinkContainer");
            let subLinkContainer = subLinkContainers[subLinkContainers.length - 1];
            subLinkContainer.appendChild(linkElement);
        }
    }
    let tocContainer = document.createElement("span");
    tocContainer.appendChild(tableOfContent);
    tocContainer.style = "position: fixed; left:15px; width: 40px ";
    return tocContainer;
}


/*___________________________ ON POST  ___________________________*/

function replaceAll(find, replace, str)
{
    return str.replace(new RegExp(find, 'g'), replace);
};

function validate()
{
    let htmlContent = document.getElementsByClassName("toastui-editor-md-preview")[0].innerHTML;
    //  htmlContent = removeCustomInputsDiv(htmlContent);
    htmlContent = escapeSpecialCharsFromContent(htmlContent);
    document.getElementById('wiki_html_content').value = htmlContent;
    escapeSpecialChars( 'wiki_content' );
    return true;
};
/*
    function removeCustomInputsDiv(htmlContent) {
        let parser = new DOMParser();
        let doc = parser.parseFromString(htmlContent, 'text/html');
        for (let i = 0; i < doc.body.children.length; i++) {
            if (doc.body.children[i].className === "toastui-editor-custom-block") {
                let keep = doc.body.children[i].children[1].children[0];
                doc.body.children[i].replaceWith(keep);
            }
        }
        return doc.body.innerHTML;
    }
 */

function escapeSpecialChars( id )
{
    let content = document.getElementById( id ).value;
    content = replaceAll( '<' , '[lt;' , content );
    content = replaceAll( '>' , '[gt;' , content );
    content = replaceAll( '"' , '[quot;' , content );
    content = replaceAll( '&nbsp;' , '[nbsp;' , content );
    content = replaceAll( '&' , '[amp;' , content );
    content = replaceAll( '#' , '[hashmark;' , content );
    content = replaceAll('`', '[codeQuote;', content)
    content = replaceAll("'", '[simpleQuote;', content)
    document.getElementById( id ).value = content;
}



function publishVersion()
{
    const saveType = "publish";
    document.getElementById('publish_version').value = 'true';
    callInputs(saveType);
}

function createVersion()
{  const saveType = "saveNewVersion";
    document.getElementById('create_version').value = 'true';
    callInputs(saveType);
}

function toggleAutosave(){
    if(document.getElementById('autoSaveMode').checked){
        document.getElementById('autoSaveLabel').innerText =  'Auto-save mode is activated';
    } else{
        document.getElementById('autoSaveLabel').innerText = 'Auto-save mode is deactivated';
    }
}
function escapeSpecialCharsFromContent( content )
{
    content = replaceAll( '<' , '[lt;' , content );
    content = replaceAll( '>' , '[gt;' , content );
    content = replaceAll( '"' , '[quot;' , content );
    content = replaceAll( '&nbsp;' , '[nbsp;' , content );
    content = replaceAll( '&' , '[amp;' , content );
    content = replaceAll( '#' , '[hashmark;' , content );
    content = replaceAll('`', '[codeQuote;', content)
    content = replaceAll("'", '[simpleQuote;', content)
    return content
}
function displayAutoSave(result){
    if(result){
        document.getElementById('autoSave').style.display = 'block';
        document.getElementById('autoSaveSucess').style.display = 'block';
    } else{
        document.getElementById('autoSave').style.display = 'block';
        document.getElementById('autoSaveFailed').style.display = 'block';
    }
    setTimeout(function(){
        document.getElementById('autoSave').style.display = 'none';
        document.getElementById('autoSaveSucess').style.display = 'none';
        document.getElementById('autoSaveFailed').style.display = 'none';
    }, 3000);
}
// auto save
async function saveContent(version, parentPage, topic_id, topicTitle, topicContent, wikiHtmlContent) {
    let response = await fetch('jsp/site/plugins/wiki/WikiDynamicInputs.jsp?actionName=saveWiki', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            credentials: "same-origin"
        },
        body: JSON.stringify({topicVersion:version, parentPageName:parentPage, topicId:topic_id, topicTitle: topicTitle, topicContent: topicContent, language: localeJs, wikiHtmlContent: wikiHtmlContent})
    });
    let result = await response;
    displayAutoSave(result);
}


function callInputs(saveType) {
    const version = document.getElementById("topic_version").value;
    const topic_id = document.getElementById("topic_id").value;
    const topicContent = escapeSpecialCharsFromContent(document.getElementById("wiki_content").value);
    const parentPage = document.getElementById("parent_page_name").value;
    const title = document.getElementById("page_title_" + localeJs).value;
    let topicTitle = escapeSpecialCharsFromContent(title);
    let wikiHtmlContent = document.getElementsByClassName("toastui-editor-md-preview")[0].innerHTML;
    wikiHtmlContent = checkforTOC(wikiHtmlContent);
    wikiHtmlContent = checkForDarkMode(wikiHtmlContent);
    wikiHtmlContent = escapeSpecialCharsFromContent(wikiHtmlContent);
    if(saveType === "autoSave") {
        if (version !== undefined && version !== null && version !== "") {
            saveContent(version, parentPage, topic_id, topicTitle, topicContent, wikiHtmlContent)
        }
    } else if (saveType === "saveNewVersion" || saveType === "publish") {
        document.getElementById('wiki_content').value = topicContent;
        document.getElementById('wiki_html_content').value = wikiHtmlContent;

    }
}
// if auto save mode is activated and auto is done
let hasToWait = false;
function autoSave() {
    if(document.getElementById('autoSaveMode').checked) {
        if (!hasToWait) {
            hasToWait = true;
            setTimeout(function () {
                const saveType = "autoSave";
                callInputs(saveType);
                hasToWait = false;
            }, 2000);
        }
    }
}

// register last user on modify page
function saveLastUserOnModifyPage(){
    const topic_id = document.getElementById("topic_id").value;

    fetch('jsp/site/plugins/wiki/WikiDynamicInputs.jsp?actionName=lastOpenModify', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            credentials: "same-origin"
        },
        body: JSON.stringify(topic_id)
    });
}
// do when page is loaded
document.onreadystatechange = function() {
    if (document.readyState === 'complete') {
        setInterval(function (){
                saveLastUserOnModifyPage();}
            , 4000);
    }
}

/*_________________________ SHOW/HIDE LINKS OF TABLE OF CONTENT ___________________________*/

let spanIconMoreLink = document.getElementsByClassName('fa fa-chevron-right');
for (let i = 0; i < spanIconMoreLink.length; i++) {
    spanIconMoreLink[i].addEventListener('click', function () {
        let parent = document.getElementsByClassName('fa fa-chevron-right')[i].parentNode
        spanIconMoreLink[i].class = 'fa fa-chevron-down';

        let SubLinks = parent.nextSibling;
        for(let i = 0; i < SubLinks.childNodes.length; i++){
            SubLinks.childNodes[i].style.display = 'block';
        }
    });
}
let spanIconLessLink = document.getElementsByClassName('fa fa-chevron-down');
for (let i = 0; i < spanIconLessLink.length; i++) {
    spanIconLessLink[i].addEventListener('click', function () {
        let parent = document.getElementsByClassName('fa fa-chevron-down')[i].parentNode
        spanIconLessLink[i].class = 'fa fa-chevron-right';

        let SubLinks = parent.nextSibling;
        for(let i = 0; i < SubLinks.childNodes.length; i++){
            SubLinks.childNodes[i].style.display = 'none';
        }
    });
}
function removeUnderLineHeadings (underLineWithEqual){
    for(let i = 0; i < underLineWithEqual.length; i++){
        let previousElement = underLineWithEqual[i].parentElement.previousElementSibling.firstElementChild;
        let textpreviousElement = previousElement.innerText;
        // grab the last character of the class of the previous element
        let headerLevelMk = ''
        let lastCharacter = previousElement.className.slice(-1);

        for(let j = 0; j < lastCharacter; j++){
            headerLevelMk += "#";
        }
        let newText = headerLevelMk + " " + textpreviousElement ;
        previousElement.innerText = newText;
        underLineWithEqual[i].remove();
    }
}
window.addEventListener("load", (event) => {
    let underLineWithEqual = document.getElementsByClassName("toastui-editor-md-heading toastui-editor-md-heading1 toastui-editor-md-delimiter toastui-editor-md-setext");
    removeUnderLineHeadings(underLineWithEqual);
    underLineWithEqual = document.getElementsByClassName("toastui-editor-md-heading toastui-editor-md-heading1 toastui-editor-md-delimiter toastui-editor-md-setext");
    removeUnderLineHeadings(underLineWithEqual);
});



