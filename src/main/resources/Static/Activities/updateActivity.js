async function updateActivity(activityId,activityName,assignedTo,dueDate,priority) {

    const ID = activityId.value;
    const Name = activityName.value;
    const AssignedTo = assignedTo.value;
    const Date = dueDate.value;
    const Priority = priority.value;

    const response = {"activityName": Name, "assignedTo": AssignedTo, "dueDate": Date, "priority": Priority};

    fetch('http://localhost:8080/api/activities/update/' + ID, {
        method: 'PUT',
        body: JSON.stringify(response),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));

}