async function documentbyID(documentID) {

    const ID = documentID.value;

    fetch('/api/documents/' + ID, {
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