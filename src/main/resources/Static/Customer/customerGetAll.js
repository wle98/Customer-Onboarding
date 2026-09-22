async function customerGetAll(){

    try {
        fetch('http://localhost:8080/api/customers/getAll')
            .then((response) => {
                return response.text();
            })
            .then(data => {
                document.getElementById('customerList').innerHTML = JSON.stringify(data);
            })
    } catch (error) {
        console.error('Error:', error);
    }
}