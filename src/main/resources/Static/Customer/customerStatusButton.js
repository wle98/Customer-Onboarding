async function customerStatusButton(){

    const ID = document.getElementById('responseID');
    const Type = document.getElementById('statusType');
    const name = document.getElementById('admin');

    customerStatus(ID,Type,name);
    window.location.reload();
    await customerCurrentStatus(ID);
    await customerStatusHistory(ID);
    console.log('clicked')
}