async function customerNotifications(customerID) {

    document.getElementById('notifications').innerHTML = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/notifications/'+newID)
        .then((response) => {
            return response.json();
        })
        .then(data => {
            data.forEach(element => {
                const para = document.createElement("p");
                const node = document.createTextNode((JSON.stringify(element.message)).replaceAll('"',''));
                para.appendChild(node);
                document.getElementById('notifications').appendChild(para);
            });
        })
        .catch(error => console.log(error));
}