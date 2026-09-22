async function customerDelete(deleteId) {

    const ID = deleteId.value;

    fetch('http://localhost:8080/api/customers/delete/' + ID, {
        method: 'DELETE',
    })
        .then(res => res.json())
        .then(res => console.log(res))
}