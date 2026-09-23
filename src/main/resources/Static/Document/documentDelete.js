async function documentDelete(documentID) {

    const ID = documentID.value;

    fetch('/api/documents/delete/' + ID, {
        method: 'DELETE',
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));
}