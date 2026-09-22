async function customerCreate(name, email, phone, address, businessType) {

    const Name = name.value;
    const Phone = phone.value;
    const Email = email.value;
    const Address = address.value;
    const BusinessType = "string";

    const response = {"name": Name, "email": Email, "phone":Phone, "address":Address, "businessType":BusinessType};

    fetch('http://localhost:8080/api/customers/createCustomer', {
        method: 'POST',
        body: JSON.stringify(response),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}