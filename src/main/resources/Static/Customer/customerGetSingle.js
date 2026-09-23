async function customerGetSingle(customerId){

    const ID = customerId.value;

    fetch('/api/customers/getSingle/' + ID, {
        method: 'GET',
    })
        .then((response) => {
            return response.text();
        })
        .then(data => {
            document.getElementById('customer').innerHTML = JSON.stringify(data);
        })
}