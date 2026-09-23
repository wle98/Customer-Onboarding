async function customerDelete(deleteId) {

    const ID = deleteId.value;
    const newID = ID.replaceAll('"','');

    fetch('/api/customers/delete/' + newID, {
        method: 'DELETE',
    })
        .then(res => res.json())
        .then(res => console.log(res))
        .catch(error => console.log(error));
}