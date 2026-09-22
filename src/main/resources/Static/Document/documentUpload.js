async function documentUpload(customerID){

    const ID = customerID.value;
    const formdata = new FormData();
    formdata.append('file',document.getElementById('document').files[0]);


    fetch('http://localhost:8080/api/customers/documents/upload/'+ID,{
        method:'POST',
        body: formdata})
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));

}
