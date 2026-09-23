async function documentUpload(customerID,documentID){

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');
    const formdata = new FormData();
    formdata.append('file',document.getElementById(documentID).files[0]);


    fetch('/api/customers/documents/upload/'+newID,{
        method:'POST',
        body: formdata})
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));

}
