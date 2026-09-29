async function customerStatusHistory(customerID) {

    document.getElementById("History").innerHTML = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/status-history/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then(data => {
            data.forEach(element => {
                const para = document.createElement("p");
                const node = document.createTextNode("Time: "+(JSON.stringify(element.changedAt)).replaceAll('"','')+" Status: "+(JSON.stringify(element.newStatus)).replaceAll('"',''));
                para.appendChild(node);
                document.getElementById('History').appendChild(para);});
        })
        .catch(error => console.log(error));
}