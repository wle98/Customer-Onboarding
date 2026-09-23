async function getCustomerActivity(customerID) {

    const ID = customerID.value;

    fetch('/api/customers/activities/'+ID)
        .then((response) => {
            return response.text();
        })
        .then(data => {
            document.getElementById('getActivities').innerHTML = JSON.stringify(data);
        })
        .catch(error => console.log(error));
}