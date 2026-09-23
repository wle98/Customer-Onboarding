async function deleteActivity(activityId) {
    const ID = activityId.value;

    fetch('/api/activities/delete/' + ID, {
        method: 'DELETE',
    })
        .then(res => res.json())
        .then(res => console.log(res))
        .catch(error => console.log(error));
}