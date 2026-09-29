async function documentDelete(documentID) {

    const ID = documentID.value;

    fetch('/api/documents/delete/' + ID, {
        method: 'DELETE',
    })
        .then(response => {
            console.log(response)
            location.reload();
        })
        .catch(error => console.log(error));


}