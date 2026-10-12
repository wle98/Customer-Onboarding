async function deleteActivity(activityId) {

    fetch('/api/activities/delete/' + activityId, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                console.log(response);
                location.reload();
            }
            else {
                return response.json()
                    .then(data => {
                        document.getElementById('activityError').textContent = data.message;
                    })
            }
        })
        .catch(error => {
            console.log(error);
            document.getElementById('activityError').textContent = 'Major Error';
        });
}