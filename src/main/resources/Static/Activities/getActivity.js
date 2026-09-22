async function getActivity(activityId) {

    const ID = activityId.value;

    fetch('http://localhost:8080/api/activities/'+ID)
        .then((response) => {
            return response.text();
        })
        .then(data => {
            document.getElementById('getActivityBy').innerHTML = JSON.stringify(data);
        })
        .catch(error => console.log(error));

}