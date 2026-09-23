async function customerCreate(name, email, phone, address, businessType) {

    const Name = name.value;
    const Phone = phone.value;
    const Email = email.value;
    const Address = address.value;
    const BusinessType = businessType.value;

    const response = {"name": Name, "email": Email, "phone":Phone, "address":Address, "businessType":BusinessType};

    fetch('/api/customers/createCustomer', {
        method: 'POST',
        body: JSON.stringify(response),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('response').setAttribute('value', JSON.stringify(data.id));
        })
        .catch(error => console.log(error));
}