async function documentUpload(customerID,documentID,documentType){

    const ID = customerID.value;
    const newID = ID.replaceAll('"','');
    const formdata = new FormData();
    formdata.append('file',document.getElementById(documentID).files[0]);

    fetch('/api/customers/documents/upload/'+newID,{
        method:'POST',
        body: formdata})
        .then(response => {
            if (response.ok) {
                return response.json()
                    .then(data => {
                        console.log(data);
                        if(sessionStorage.getItem(documentType)==undefined){
                            sessionStorage.setItem(documentType,'1');
                        }
                        else{
                            let amount = parseInt(sessionStorage.getItem(documentType));
                            amount+=1;
                            sessionStorage.setItem(documentType,amount.toString());
                        }
                        let webID = data.id;
                        sessionStorage.setItem(webID,documentType);
                        location.reload();
                    })
            }
            else{
                return response.json()
                    .then(data => {
                        if(data.message=='An unexpected error occurred'){
                            document.getElementById('errorMessage').innerText=data.message+'. Please choose a file to upload';
                        }
                        else{
                            document.getElementById('errorMessage').innerText=data.message;
                        }
                    })
            }
        })
        .catch(error => {
            console.log(error);
            document.getElementById('errorMessage').textContent = 'Major Error';
        });

}
