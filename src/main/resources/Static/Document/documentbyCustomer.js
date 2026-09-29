async function documentbyCustomer(customerID) {

    document.getElementById("documentList").innerHTML = '';
    const ID = customerID.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/documents/' + newID, {
        method: 'GET',
    })
        .then((response) => {
            return response.json();
        })
        .then(data => {
            data.forEach(element => {
                const para = document.createElement("p");
                const node = document.createTextNode("Name: "+(JSON.stringify(element.fileName)).replaceAll('"','')+" ID: "+(JSON.stringify(element.id)).replaceAll('"',''));
                para.appendChild(node);
                document.getElementById('documentList').appendChild(para);});
        })
        .catch(error => console.log(error));

}