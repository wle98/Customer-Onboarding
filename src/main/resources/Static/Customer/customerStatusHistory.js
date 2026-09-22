async function customerStatusHistory(customerID) {

    const ID = customerID.value;

    fetch('http://localhost:8080/api/customers/status-history/' + ID, {
        method: 'GET',
    })
        .then((response) => {
            return response.text();
        })
        .then(data => {
            document.getElementById('statusHistory').innerHTML = JSON.stringify(data);
        })
}