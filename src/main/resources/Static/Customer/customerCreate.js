async function customerCreate(name, email, phone, address, businessType) {

    document.getElementById('errorMessage').textContent = '';
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
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            return Promise.reject(response);
        })
        .then(data => {
            document.getElementById('response').setAttribute('value', JSON.stringify(data.id));
            localStorage.setItem('response', JSON.stringify(data.id));
        })
        .catch(error => {
            console.log(error);
            document.getElementById('errorMessage').textContent = 'Please enter valid information';
        });
}