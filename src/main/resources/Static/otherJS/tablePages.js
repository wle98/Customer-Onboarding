sessionStorage.setItem('table','1');
sessionStorage.setItem('table1','1');

let perPage=5;
let buttonPrev = document.getElementsByClassName('prev');
let buttonNext = document.getElementsByClassName('next');

function prevPage(table,span){
    let i;
    if (table == 'table') {
        i = 0;
    } else {
        i = 1;
    }
    let currentPage = parseInt(sessionStorage.getItem(table));
    currentPage--;
    if (currentPage==1){
        buttonPrev[i].disabled = true;
        buttonNext[i].disabled = false;
    }
    else{
        buttonPrev[i].disabled = false;
        buttonNext[i].disabled = false;
    }
    sessionStorage.setItem(table,currentPage.toString());
    pageChange(currentPage,table,span);
}

function nextPage(table,span){
    let i;
    if (table == 'table') {
        i = 0;
    } else {
        i = 1;
    }
    let currentPage = parseInt(sessionStorage.getItem(table));
    currentPage++;
    if(currentPage>=numPages(document.getElementById(table).rows.length)){
        buttonNext[i].disabled = true;
        buttonPrev[i].disabled = false;
    }
    else{
        buttonNext[i].disabled = false;
        buttonPrev[i].disabled = false;
    }
    sessionStorage.setItem(table,currentPage.toString());
    pageChange(currentPage,table,span);
}

function pageChange(page,table,span){
    let currentTable = document.getElementById(table);
    let pageSpan = document.getElementById(span);
    let len = document.getElementById(table).rows.length;

    if(page<1){
        page=1;
    }
    if (page>numPages(len)){
        page = numPages(len);
    }

    [...currentTable.getElementsByTagName('tr')].forEach((tr) => {
        tr.style.display = 'none';
    })
    currentTable.rows[0].style.display = "";

    for (let i = (page-1)*perPage+1;i<(page*perPage)+1;i++){
        if(currentTable.rows[i]){
            currentTable.rows[i].style.display = "";
        }
    }

    pageSpan.innerHTML = page + "/"+ numPages(len);

}


function numPages(len){
    return Math.ceil((len-1)/perPage);
}
