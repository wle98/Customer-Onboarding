async function getCustomerActivity(customerID) {

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/activities/'+newID)
        .then((response) => {
            if(response.ok){
                return response.json()
            .then(data => {
                    data.forEach(element => {
                        const table = document.getElementById('table');
                        const row = table.insertRow(1);
                        const cell = row.insertCell(0);
                        const cell1 = row.insertCell(1);
                        const cell2 = row.insertCell(2);
                        const cell3 = row.insertCell(3);
                        const cell4 = row.insertCell(4);
                        cell.innerHTML = (JSON.stringify(element.activityName)).replaceAll('"','');
                        cell1.innerHTML = (JSON.stringify(element.assignedTo)).replaceAll('"','');
                        cell2.innerHTML = (JSON.stringify(element.dueDate)).replaceAll('"','');
                        cell3.innerHTML = (JSON.stringify(element.priority)).replaceAll('"','');
                        cell4.innerHTML = (JSON.stringify(element.status)).replaceAll('"','');

                        const Delete = (JSON.stringify(element.id)).replaceAll('"', '');
                        const cell5 = row.insertCell(5);
                        cell5.innerHTML = '<button>Delete</button>';
                        cell5.onclick = function () {
                            deleteActivity(Delete);
                        }
                    });
                    pageChange(sessionStorage.getItem('table'),'table','pageSpan');
                })
            }
            else{
                return response.json()
                    .then(data =>{
                        document.getElementById('activityError').innerHTML = data.error;
                    })
            }
        })
        .catch(error => console.log(error));
}