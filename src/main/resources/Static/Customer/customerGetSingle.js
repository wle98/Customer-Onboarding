async function customerGetSingle(customerID){

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/getSingle/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then(data => {
            document.getElementById('customerName').innerHTML = (JSON.stringify((data.name)).replaceAll('"',''));
            document.getElementById('customerID').innerHTML = (JSON.stringify(data.id).replaceAll('"',''));
            document.getElementById('registrationDate').innerHTML = (JSON.stringify(data.registrationDate).replaceAll('"',''));
        })
        .catch(error => console.log(error));
}