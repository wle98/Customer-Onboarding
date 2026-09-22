async function documentbyID(documentID) {

    const ID = documentID.value;

    fetch('http://localhost:8080/api/documents/' + ID, {
        method: 'GET',
    })
        .then((response) => {
            return response.text();
        })
        .then(data => {
            document.getElementById('returnResult').innerHTML = JSON.stringify(data);
        })
        .catch(error => console.log(error));

}