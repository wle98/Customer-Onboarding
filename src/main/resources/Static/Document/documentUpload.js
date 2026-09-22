async function documentUpload(customerID){

    const ID = customerID.value;
    const uploadElement = document.getElementById("document");
    const file = uploadElement.files[0];
    const formData = new FormData();
    formData.append("file", file, file.name);

    fetch('api/customers/documents/upload'+ID,{
        method:'POST',
        body: formData})
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error));

}
