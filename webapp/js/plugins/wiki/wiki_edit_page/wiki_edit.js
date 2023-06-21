
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
const addAlertButton = document.getElementsByClassName("alert alert-danger editor")[0];
addAlertButton.addEventListener('click', function() {
    document.getElementById("selectAlertType").style.display = "block";
});
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

const addBadgeButton = document.getElementsByClassName("badge badge-primary editor")[0];
addBadgeButton.addEventListener('click', function() {
    document.getElementById("selectBadge").style.display = "block";
});


document.getElementById("selectIcon").addEventListener("change", function() {
    const icon = document.getElementById("selectIcon").value;
    document.getElementById("iconIsSelected").style.display = "block";
    document.getElementById("selectedIcon").innerHTML = '<span class="fa fa-' + icon + '"></span><br/>';
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
function closeToastUiModal() {
    const popupNumber = document.getElementsByClassName("toastui-editor-popup").length
    for (let i = 0; i < popupNumber; i++) {
        document.getElementsByClassName("toastui-editor-popup")[i].style.display = "none";
    }
}
document.getElementsByClassName("toastui-editor-mode-switch")[0].remove();
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
const tableOfContentButton = document.getElementsByClassName("fa fa-list editor")[0];
tableOfContentButton.addEventListener('click', function() {
    editorHasToc();
    document.getElementById("tableOfContentModal").style.display = "block";
});
function changeToc() {
    let wikiHtmlContent = document.getElementsByClassName("ProseMirror")[0].innerHTML;
    let parser = new DOMParser();
    let doc = parser.parseFromString(wikiHtmlContent, 'text/html');
    let toc = document.getElementsByClassName("ProseMirror")[0].getElementsByClassName('toastui-editor-md-custom-block-line-background')
    let hasToc = false;
    for(let i = 0; i < toc.length; i++) {
        if (toc[i].firstElementChild.innerText.indexOf('<span class="toc"></span>') > -1){
            document.getElementsByClassName("ProseMirror")[0].getElementsByClassName('toastui-editor-md-custom-block-line-background')[i].nextElementSibling.remove();
            document.getElementsByClassName("ProseMirror")[0].getElementsByClassName('toastui-editor-md-custom-block-line-background')[i].previousElementSibling.remove();
            document.getElementsByClassName("ProseMirror")[0].getElementsByClassName('toastui-editor-md-custom-block-line-background')[i].previousElementSibling.remove();
            hasToc = true;
        }
    }
    if(!hasToc){
        let tocHtml = '<span class="toc"></span>';
        editor.insertText("$$span\n"+tocHtml+ "\n$$");
    }
    setTimeout(function(){
        closeToastUiModal()
    }, 1000);

}
function editorHasToc (){
    const wikiHtmlContent = document.getElementsByClassName("toastui-editor-md-preview")[0].innerHTML;
    let parser = new DOMParser();
    let doc = parser.parseFromString(wikiHtmlContent, 'text/html');

    let toc = doc.getElementsByClassName('toc');
    if(toc !== undefined && toc.length > 0){
        console.log(toc);
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
    // check for elements with class toc
    let toc = doc.getElementsByClassName('toc')[0];
    if(toc !== undefined){
        let tableOfContent = createTableOfContent(doc);
        doc.getElementsByClassName('toc')[0].replaceWith(tableOfContent)
    }
    doc.querySelectorAll(".toastui-editor-md-preview-highlight").forEach(function (element) {
        element.classList.remove("toastui-editor-md-preview-highlight");
    });
    wikiHtmlContent = doc.documentElement.innerHTML;
    return wikiHtmlContent;
}
function createTableOfContent(doc) {
    let thisPageurl = window.location.href;
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
        linkElement.href = thisPageurl + '#' + headerText;
        linkElement.innerText = headerText;
        linkElement.className = "nav-link";
        let navItem = document.createElement("li");
        if (headerLevel === "H1" || headerLevel === "H2" || headerLevel === "H3") {
            navItem.className = "nav-item";
            if (headers[i + 1] !== undefined) {
                if (headers[i + 1].tagName === "H1" || headers[i + 1].tagName === "H2" || headers[i + 1].tagName === "H3") {
                    tableOfContent.appendChild(linkElement);
                } else if (headers[i + 1].tagName === "H4" || headers[i + 1].tagName === "H5" || headers[i + 1].tagName === "H6") {
                    let divContainer = document.createElement("div");
                    divContainer.style = "display: flex; flex-direction: row; spacing: 10px; align-items: center;";
                    let icon = document.createElement("i");
                    icon.className = "fa fa-chevron-right";
                    divContainer.appendChild(icon);
                    divContainer.appendChild(linkElement);
                    navItem.appendChild(divContainer);
                    tableOfContent.appendChild(navItem);
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
            console.log(subLinkContainer);
            subLinkContainer.appendChild(linkElement);
        }
    }
    let tocContainer = document.createElement("span");
    tocContainer.style = "position: fixed; left: 20px; width: 20px";
    tocContainer.appendChild(tableOfContent);
    return tocContainer;
}

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
    console.log('saveContent')
    let response = await fetch(baseUrl+'/jsp/site/plugins/wiki/WikiDynamicInputs.jsp?actionName=saveWiki', {
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

    const parentPage = document.getElementById("parent_page_name").value;
    const title = document.getElementById("page_title_" + localeJs).value;
    let topicTitle = escapeSpecialCharsFromContent(title);
    let wikiHtmlContent = document.getElementsByClassName("toastui-editor-md-preview")[0].innerHTML;
    wikiHtmlContent = checkforTOC(wikiHtmlContent);
    wikiHtmlContent = escapeSpecialCharsFromContent(wikiHtmlContent);
    if(saveType === "autoSave") {
        if (version !== undefined && version !== null && version !== "") {
            const topicContent = escapeSpecialCharsFromContent(document.getElementById("wiki_content").value);
            saveContent(version, parentPage, topic_id, topicTitle, topicContent, wikiHtmlContent)
        }
    } else if (saveType === "saveInNewVersion" || saveType === "publish") {
        const topicContent = escapeSpecialCharsFromContent(document.getElementById("wiki_content").value);
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
    fetch(baseUrl+'/jsp/site/plugins/wiki/WikiDynamicInputs.jsp?actionName=lastOpenModify', {
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