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
                return response.json()
                    .then(data => {
                        document.getElementById('response').setAttribute('value', JSON.stringify(data.id));
                        sessionStorage.setItem('response', JSON.stringify(data.id));
                        document.getElementById('nextPage').disabled = false;
                        document.getElementById('cancel').disabled = false;
                    })
            }
            else{
                return response.json()
                    .then(data => {
                        try{
                            let errors = data.errors;
                            Object.keys(errors).forEach(key => {
                                const para = document.createElement("p");
                                const node = document.createTextNode(errors[key]);
                                para.appendChild(node);
                                document.getElementById('errorMessage').appendChild(para);
                            })
                        }
                        catch(err){
                            const para = document.createElement("p");
                            const node = document.createTextNode(data.message);
                            para.appendChild(node);
                            document.getElementById('errorMessage').appendChild(para);
                        }
                    })
            }
        })
        .catch(error => {
            console.log(error);
            document.getElementById('errorMessage').textContent = 'There was an error creating customer';
        });
}