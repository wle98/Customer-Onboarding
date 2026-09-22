async function documentDelete(documentID) {

    const ID = documentID.value;

    fetch('http://localhost:8080/api/documents/delete/' + ID, {
        method: 'DELETE',
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}