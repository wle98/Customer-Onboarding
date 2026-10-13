function showAddActivity() {
    const visibility = document.getElementById('addActivity');

    if (visibility.style.display === 'none') {
        visibility.style.display = 'block';
    }
    else{
        visibility.style.display = 'none';
    }
}