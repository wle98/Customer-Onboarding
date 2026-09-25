async function getCustomerActivity(customerID) {

    document.getElementById("ActivityList").innerHTML = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/activities/'+newID)
        .then((response) => {
            return response.json();
        })
        .then(data => {
            data.forEach(element => {const para = document.createElement("p");
                const node = document.createTextNode("Activity: "+(JSON.stringify(element.activityName)).replaceAll('"',''));
                para.appendChild(node);
                document.getElementById('ActivityList').appendChild(para);});
            })
        .catch(error => console.log(error));
}