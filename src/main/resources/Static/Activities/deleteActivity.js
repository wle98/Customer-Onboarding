async function deleteActivity(activityId) {
    const ID = activityId.value;

    fetch('http://localhost:8080/api/activities/delete/' + ID, {
        method: 'DELETE',
    })
        .then(res => res.json())
        .then(res => console.log(res))
        .catch(error => console.log(error));
}