async function deleteActivity(activityId) {
    const ID = activityId.value;

    fetch('/api/activities/delete/' + ID, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                console.log(response);
                location.reload();
            }
            return Promise.reject(response);
        })
        .then(res => console.log(res))
        .catch(error => {
            console.log(error);
            document.getElementById('errorMessage').textContent = 'Please enter valid ID for deletion';
        });
}