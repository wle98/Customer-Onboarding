async function customerGetSingle(customerID){

    document.getElementById('errorMessage').textContent = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/getSingle/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            if(response.ok){
                return response.json()
                    .then(data => {
                        document.getElementById('customerName').innerHTML = (JSON.stringify((data.name)).replaceAll('"',''));
                        document.getElementById('customerID').innerHTML = (JSON.stringify(data.id).replaceAll('"',''));
                        document.getElementById('registrationDate').innerHTML = (JSON.stringify(data.registrationDate).replaceAll('"',''));
                        document.getElementById('StatusID').innerHTML = (JSON.stringify((data.status)).replaceAll('"', ''));
                    })
            }
            else{
                response.json()
                .then(data => {
                    document.getElementById('errorMessage').textContent = data.message;
                })
            }
        })
        .catch(error => console.log(error));
}