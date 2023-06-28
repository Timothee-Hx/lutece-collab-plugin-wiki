function createTableOfContent(doc) {
    let thisPageurl = window.location.href;
    thisPageurl = "jsp/" + thisPageurl.split("jsp/")[1];
    thisPageurl = thisPageurl.replace("view=modifyPage", "view=viewPage");
    thisPageurl = thisPageurl.replace(/&id_topic_version=[0-9]*/g, "");
    if(thisPageurl.includes("locale=")){
        thisPageurl = thisPageurl.replace(/&locale=[a-z]*/g, "");
    }
    if(thisPageurl.includes("&version=")){
        thisPageurl = thisPageurl.replace(/&id_topic=[0-9]*/g, "");
    }
    let headers = doc.querySelectorAll("h1, h2, h3, h4");
    console.log(headers)
    let tableOfContent = document.createElement("ul")
    tableOfContent.id = "tableOfContent";
    tableOfContent.className = "nav flex-column wiki-topic-nav";
    let lastLevelChange = 1;
    for (let i = 0; i < headers.length; i++) {
        let header = headers[i];
        let headerText = header.innerText;
        let headerLevel = header.tagName;
        console.log(headerLevel);

        let linkElement = document.createElement("a");
        linkElement.href = thisPageurl + '#' + header.id;
        linkElement.innerText = headerText;
        linkElement.className = "nav-link";
        let navItem = document.createElement("li");
        navItem.className = "nav-item";
        const navLevel = parseInt(headerLevel.substring(1, 2));
        let navLevelNext = 0;
        if (headers[i + 1] !== undefined) {
            navLevelNext = parseInt(headers[i + 1].tagName.substring(1, 2));
        } else {
            navLevelNext = 0;
        }
        let navLevelPrevious = 0;
        if (headers[i - 1] !== undefined) {
            navLevelPrevious = parseInt(headers[i - 1].tagName.substring(1, 2));
        }
        if (navLevel === 1 && navLevelNext !== 0 && navLevel < navLevelNext && lastLevelChange === 1) {
            let divContainer = document.createElement("div");
            divContainer.style = "display: flex; flex-direction: row; spacing: 5px;";
            divContainer.appendChild(linkElement);
            let icon = document.createElement("span");
            icon.className = "fa fa-caret-down";
            icon.style = "margin-left: 5px; margin-top: 5px;";
            divContainer.appendChild(icon);
            const subListLevel = navLevel + 1;
            let subList = document.createElement("ul");
            subList.className = "nav flex-column sub-nav " + subListLevel;
            divContainer.appendChild(navItem);
            divContainer.appendChild(subList);
            tableOfContent.appendChild(divContainer);
            lastLevelChange = navLevel + 1;
        }
        else if (navLevel !== 4 && navLevelNext !== 0 && navLevel < navLevelNext && lastLevelChange !== 1) {
            console.log("coucou: ");
            let divContainer = document.createElement("div");
            divContainer.style = "display: flex; flex-direction: row; spacing: 5px;";
            divContainer.appendChild(linkElement);
            let icon = document.createElement("span");
            icon.className = "fa fa-caret-down";
            icon.style = "margin-left: 5px; margin-top: 5px;";
            divContainer.appendChild(navItem);
            divContainer.appendChild(icon);
            const subListLevel = navLevel + 1;
            let subsubList = document.createElement("ul");
            subsubList.className = "nav flex-column sub-nav " + subListLevel;
            divContainer.appendChild(navItem);
            divContainer.appendChild(subsubList);
            let subList = tableOfContent.getElementsByClassName("nav flex-column sub-nav " +  lastLevelChange.toString());
            if(subList.length > 0){
                subList = subList[subList.length - 1];
                subList.appendChild(divContainer);
            }
            lastLevelChange = navLevel + 1;

        }

        if (navLevel >= navLevelPrevious && lastLevelChange !== 1 && navLevel >= navLevelNext) {
            console.log("coucou");
            let subList = tableOfContent.getElementsByClassName("nav flex-column sub-nav " +  lastLevelChange.toString());
            console.log("subList: " + subList);
            if(subList.length > 0){
                subList = subList[subList.length - 1];
                subList.appendChild(navItem);
            }
        }
        else if (lastLevelChange !== 1 && navLevel < navLevelPrevious) {
            console.log("upgrading ");
            console.log("navLevel: " + navLevel);
            lastLevelChange = navLevel;
            let subList = tableOfContent.getElementsByClassName("nav flex-column sub-nav " + navLevel.toString());
            if (subList.length > 0) {
                subList = subList[subList.length - 1];
                subList.appendChild(navItem);
            }
        }

        else if(navLevel === 1 && navLevelNext === 1){
            tableOfContent.appendChild(navItem);
        }

        console.log(tableOfContent);

        return tableOfContent;
    }
}