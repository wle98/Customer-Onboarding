async function addCustomerActivity(customerID,activityName,assignedTo,duedate,priority) {

    const ID = customerID.value;
    const Activity = activityName.value;
    const Assignedto = assignedTo.value;
    const Duedate = duedate.value;
    const Priority = priority.value;

    const response = {"activityName": Activity, "assignedTo": Assignedto, "dueDate": Duedate, "priority": Priority};

    fetch('/api/customers/activities/create/' + ID, {
        method: 'POST',
        body: JSON.stringify(response),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));

    location.reload();
}