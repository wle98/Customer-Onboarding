async function changeCustomerActivityStatus(activityID,activityStatus) {

    const ID = activityID.value;
    const formdata = new FormData();
    formdata.append('status',activityStatus.value);

    fetch('http://localhost:8080/api/activities/status/updateStatus/'+ID,{
        method: 'PATCH',
        body: formdata
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}