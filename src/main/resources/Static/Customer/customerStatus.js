function customerStatus(customerID,statusType,statusName){

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');
    const Type = statusType.value;
    const Name = statusName.value;

    const response = {"newStatus": Type, "changedBy": Name};

    fetch('/api/customers/status/' + newID, {
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