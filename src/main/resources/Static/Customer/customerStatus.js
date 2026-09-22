async function customerStatus(customerId,statusType,statusName){

    const ID = customerId.value;
    const Type = statusType.value;
    const Name = statusName.value;

    const response = {"newStatus": Type, "changedBy": Name};

    fetch('http://localhost:8080/api/customers/status/' + ID, {
        method: 'PATCH',
        body: JSON.stringify(response),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}