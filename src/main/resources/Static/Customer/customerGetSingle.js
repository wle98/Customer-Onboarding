async function customerGetSingle(customerId){

    const ID = customerId.value;

    fetch('http://localhost:8080/api/customers/getSingle/' + ID, {
        method: 'GET',
    })
        .then((response) => {
            return response.text();
        })
        .then(data => {
            document.getElementById('customer').innerHTML = JSON.stringify(data);
        })
}