async function documentbyCustomer(customerID) {

    document.getElementById("documentList").innerHTML = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/documents/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then(data => {
            data.forEach(element => {
                let type = '';
                if(sessionStorage.getItem((JSON.stringify(element.id)).replaceAll('"', ''))=='IDcount'){
                    type = 'Identification Document';
                }
                else if(sessionStorage.getItem((JSON.stringify(element.id)).replaceAll('"', ''))=='agreementCount'){
                    type = 'Signed Agreement';
                }
                else{
                    type = 'Unknown';
                }

                const table = document.getElementById('table');
                const row = table.insertRow(1);
                const cell = row.insertCell(0);
                const cell1 = row.insertCell(1);
                const cell2 = row.insertCell(2);
                const cell3 = row.insertCell(3);
                cell.innerHTML = (JSON.stringify(element.fileName)).replaceAll('"', '');
                cell1.innerHTML = type;
                cell2.innerHTML = (JSON.stringify(element.fileSize)).replaceAll('"', '');
                cell3.innerHTML = (JSON.stringify(element.status)).replaceAll('"', '');

                const Delete = (JSON.stringify(element.id)).replaceAll('"', '');
                const cell4 = row.insertCell(4);
                cell4.innerHTML = '<button>Delete</button>';
                cell4.onclick = function () {
                    documentDelete(Delete);
                }
            });
            pageChange(sessionStorage.getItem('table'),'table','pageSpan');
        })
        .catch(error => console.log(error));

}