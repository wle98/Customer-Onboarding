async function customerCurrentStatus(customerID) {

    document.getElementById("StatusID").innerHTML = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"', '');

    fetch('/api/customers/getSingle/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then(data => {
            document.getElementById('StatusID').innerHTML = (JSON.stringify((data.status)).replaceAll('"', ''));
            })
        .catch(error => console.log(error));
}