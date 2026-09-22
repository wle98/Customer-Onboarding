async function changeCustomerActivityStatus(activityID,activityStatus) {

    const ID = activityID.value;
    const STATUS = activityStatus.value;

    fetch('api/activities/status/updateStatus/'+ID,{
        method: 'PATCH',
        body: STATUS,
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}