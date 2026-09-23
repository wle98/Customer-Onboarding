async function customerUpdate(id,name, email, phone, address, businessType){
    const ID =id.value;
    const Name = name.value;
    const Phone = phone.value;
    const Email = email.value;
    const Address = address.value;
    const BusinessType = businessType.value;

    const response = {"name": Name, "email": Email, "phone":Phone, "address":Address, "businessType":BusinessType};

    fetch('/api/customers/update/' + ID, {
        method: 'PUT',
        body: JSON.stringify(response),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}