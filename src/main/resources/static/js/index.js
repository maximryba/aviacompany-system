function fetchCities(field) {
        const query = document.getElementById(field).value;
        const suggestionsList = document.getElementById(field + 'Suggestions');

        if (query.length < 2) {
            suggestionsList.innerHTML = '';
            return;
        }

        fetch('/search/cities?field=' + field + '&query=' + query)
            .then(response => response.json())
            .then(cities => {
				console.log(cities);
				console.log(query);
                suggestionsList.innerHTML = '';
                cities.forEach(city => {
                    const li = document.createElement('li');
                    li.textContent = city;
                    li.onclick = () => selectCity(field, city);
                    suggestionsList.appendChild(li);
                });
            })
            .catch(error => console.error('Error fetching cities:', error));
    }

    function selectCity(field, cityName) {
        document.getElementById(field).value = cityName;
        document.getElementById(field + 'Suggestions').innerHTML = '';
    }

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