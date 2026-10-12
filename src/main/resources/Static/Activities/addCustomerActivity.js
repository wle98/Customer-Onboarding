async function addCustomerActivity(customerID,activityName,assignedTo,duedate,priority) {

    document.getElementById('activityError').textContent = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"','');
    const Activity = activityName.value;
    const Assignedto = assignedTo.value;
    const Duedate = duedate.value;
    const Priority = priority.value;

    const response = {"activityName": Activity, "assignedTo": Assignedto, "dueDate": Duedate, "priority": Priority};

    fetch('/api/customers/activities/create/' + newID, {
        method: 'POST',
        body: JSON.stringify(response),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                location.reload();
            }
            else {
                return response.json()
                    .then(data => {
                        try{
                            let errors = data.errors;
                            Object.keys(errors).forEach(key => {
                                const para = document.createElement("p");
                                const node = document.createTextNode(errors[key]);
                                para.appendChild(node);
                                document.getElementById('activityError').appendChild(para);
                            })
                        }
                        catch(err){
                            const para = document.createElement("p");
                            const node = document.createTextNode(data.message);
                            para.appendChild(node);
                            document.getElementById('activityError').appendChild(para);
                        }
                    })
            }
        })
        .catch(error => console.log(error));
}