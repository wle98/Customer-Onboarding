async function customerStatusHistory(customerID) {

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/status-history/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            if(response.ok) {
                return response.json()
                    .then(data => {
                        data.forEach(element => {
                            const table = document.getElementById("table1");
                            const row = table.insertRow(1);
                            const cell = row.insertCell(0);
                            const cell1 = row.insertCell(1);
                            const cell2 = row.insertCell(2);
                            cell.innerHTML = (JSON.stringify(element.newStatus)).replaceAll('"','');
                            cell1.innerHTML = (JSON.stringify(element.changedBy)).replaceAll('"','');
                            cell2.innerHTML = (JSON.stringify(element.changedAt)).replaceAll('"','');
                        });
                        pageChange(sessionStorage.getItem('table1'),'table1','pageSpan1');
                    })
            }
            else{
                return response.json()
                .then(data => {
                    document.getElementById('historyError').innerText = data.error;
                })
            }
        })
        .catch(error => console.log(error));
}