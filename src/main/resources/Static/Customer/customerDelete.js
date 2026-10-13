async function customerDelete(deleteId) {

    document.getElementById('errorMessage').textContent = '';
    const ID = deleteId.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/delete/' + newID, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok){
                sessionStorage.removeItem('response');
                document.getElementById('cancel').disabled = true;
                document.getElementById('nextPage').disabled = true;
                return response.json();
            }
            else{
                return response.json()
                    .then((data) => {
                        const para = document.createElement("p");
                        const node = document.createTextNode(data.message);
                        para.appendChild(node);
                        document.getElementById('errorMessage').appendChild(para);
                    })
            }
        })
        .catch(error => console.log(error));
}