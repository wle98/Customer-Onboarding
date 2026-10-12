async function documentDelete(documentID) {

    fetch('/api/documents/delete/' + documentID, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                console.log(response);
                let type = sessionStorage.getItem(documentID);
                let amount = parseInt(sessionStorage.getItem(type));
                amount-=1;
                sessionStorage.setItem(type,amount.toString());
                sessionStorage.removeItem(documentID);
                location.reload();
            }
            else{
                return response.json()
                .then(data => {
                    document.getElementById('errorMessage').innerText=data.message;
                })
            }
        })
        .catch(error => {
            console.log(error);
            document.getElementById('errorMessage').textContent = 'Major Error';
        });


}