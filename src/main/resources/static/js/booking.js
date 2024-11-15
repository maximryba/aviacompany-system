function toggleProfileMenu() {
    const menu = document.getElementById('profileMenu');
    menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
}

// Закрытие меню при клике вне его
window.onclick = function(event) {
    if (!event.target.matches('.profile-container *')) {
        const menu = document.getElementById('profileMenu');
        if (menu.style.display === 'block') {
            menu.style.display = 'none';
        }
    }
}