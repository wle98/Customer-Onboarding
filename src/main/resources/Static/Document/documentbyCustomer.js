async function documentbyCustomer(customerID) {

    const ID = customerID.value;

    fetch('http://localhost:8080/api/customers/documents/' + ID, {
        method: 'GET',
    })
        .then((response) => {
            return response.text();
        })
        .then(data => {
            document.getElementById('customerFileReturn').innerHTML = JSON.stringify(data);
        })
        .catch(error => console.log(error));

}