async function pageLoad() {
    const value = localStorage.getItem('response');
    document.getElementById('responseID').setAttribute('value', value);
    const id = document.getElementById('responseID');
    await customerGetSingle(id);
    await customerStatusHistory(id);
    await getCustomerActivity(id);
}