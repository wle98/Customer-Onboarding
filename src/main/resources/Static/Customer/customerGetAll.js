async function customerGetAll(){

    try {
        fetch('/api/customers/getAll')
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