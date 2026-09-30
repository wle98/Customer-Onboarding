async function documentDelete(documentID) {

    const ID = documentID.value;

    fetch('/api/documents/delete/' + ID, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                console.log(response);
                location.reload();
            }
            return Promise.reject(response);
        })
        .catch(error => {
            console.log(error);
            document.getElementById('errorMessage').textContent = 'Please enter valid ID for deletion';
        });


}