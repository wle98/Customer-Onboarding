async function customerStatusHistory(customerID) {

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/status-history/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then(data => {
            document.getElementById('statusHistory').innerHTML = JSON.stringify(data);
        })
        .catch(error => console.log(error));
}