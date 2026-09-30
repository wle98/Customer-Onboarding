function documentUpload(customerID,documentID){

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');
    const formdata = new FormData();
    formdata.append('file',document.getElementById(documentID).files[0]);


    fetch('/api/customers/documents/upload/'+newID,{
        method:'POST',
        body: formdata})
        .then(response => {
            if (response.ok) {
                console.log(response);
                location.reload();
            }
            return Promise.reject(response);
        })
        .then(data => {
            console.log(data);
            location.reload();
        })
        .catch(error => {
            console.log(error);
            document.getElementById('errorMessage').textContent = 'Please enter valid document';
        });

}
